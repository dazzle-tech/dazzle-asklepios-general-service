package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import com.asklepios.backend_service.model.generated.pojo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApInventoryTransactionProductDAO;

@Service
@Slf4j
public class ApInventoryTransactionProductService extends ApInventoryTransactionProductDAO implements Serializable {


    private final ApInventoryTransactionService apInventoryTransactionService;
    private final ApInventoryTransactionProductDAO apInventoryTransactionProductDAO;
    private final ApWarehouseProductService apWarehouseProductService;
    private final ApProductsService apProductsService;
    private final ApUomGroupsUnitsService apUomGroupsUnitsService;
    private final ApUomGroupsRelationService apUomGroupsRelationService;
    private final ApWarehouseProductDetailsService apWarehouseProductDetailsService;

    public ApInventoryTransactionProductService(ApInventoryTransactionService apInventoryTransactionService, @Qualifier("apInventoryTransactionProductDAO") ApInventoryTransactionProductDAO apInventoryTransactionProductDAO, ApWarehouseProductService apWarehouseProductService, ApProductsService apProductsService, ApUomGroupsUnitsService apUomGroupsUnitsService, ApUomGroupsRelationService apUomGroupsRelationService, ApWarehouseProductDetailsService apWarehouseProductDetailsService) {
        super();
        this.apInventoryTransactionService = apInventoryTransactionService;
        this.apInventoryTransactionProductDAO = apInventoryTransactionProductDAO;
        this.apWarehouseProductService = apWarehouseProductService;
        this.apProductsService = apProductsService;
        this.apUomGroupsUnitsService = apUomGroupsUnitsService;
        this.apUomGroupsRelationService = apUomGroupsRelationService;
        this.apWarehouseProductDetailsService = apWarehouseProductDetailsService;
    }

    //conversion from uom to another one ( using for convert from trans to base)
    public BigDecimal convert(BigDecimal quantity, String transUnit, String toBaseUnit, String uomGroup) throws SQLException {

        // Step 1: Get units and sort by order
        List<ApUomGroupsUnits> uoms = apUomGroupsUnitsService.getList("uom_group_key = '" + uomGroup + "' AND deleted_at IS NULL");
        uoms.sort(Comparator.comparing(ApUomGroupsUnits::getUomOrder));

        // Build a unit map for lookup
        Map<String, ApUomGroupsUnits> uomMap = new HashMap<>();
        for (ApUomGroupsUnits uom : uoms) {
            uomMap.put(uom.getKey(), uom);
        }

        // Step 2: Load relations
        List<ApUomGroupsRelation> relations = apUomGroupsRelationService.getList("uom_group_key = '" + uomGroup + "' AND is_valid = true");
        Map<String, String> toLowerMap = new HashMap<>();
        Map<String, BigDecimal> factorMap = new HashMap<>();

        for (ApUomGroupsRelation rel : relations) {
            String from = rel.getUomUnitFromKey();  // e.g., Box
            String to = rel.getUomUnitToKey();      // e.g., Sheet
            BigDecimal factor = rel.getRelation();  // e.g., 2

            toLowerMap.put(from, to);
            factorMap.put(from, factor);
        }

        // Step 3: Convert from source unit to smallest
        BigDecimal inSmallest = quantity;
        String current = transUnit;

        while (uomMap.get(current).getUomOrder().intValue() > 1) {
            String lower = toLowerMap.get(current);
            BigDecimal factor = factorMap.get(current);
            inSmallest = inSmallest.multiply(factor);
            current = lower;
        }

        // Step 4: Convert from smallest to base unit
        BigDecimal result = inSmallest;
        int baseOrder = uomMap.get(toBaseUnit).getUomOrder().intValue();

        // Go upward from smallest to base
        for (ApUomGroupsUnits u : uoms) {
            int order = u.getUomOrder().intValue();
            if (order <= baseOrder) continue;

            String upper = u.getUomLkey(); // e.g., Sheet
            // find the relation pointing to this
            for (Map.Entry<String, String> entry : toLowerMap.entrySet()) {
                if (entry.getValue().equals(upper)) {
                    BigDecimal factor = factorMap.get(entry.getKey());
                    result = result.divide(factor, 6, RoundingMode.HALF_UP);
                    break;
                }
            }
        }

        return result;
    }

    public void confirmProductTransactionOnWarehouse(String trans_id) throws SQLException {
        List<ApInventoryTransactionProduct> allProductInTrans = apInventoryTransactionProductDAO.getList("inventory_trans_key = '" + trans_id + "' and is_effected_warehouse = false");

        for (ApInventoryTransactionProduct product : allProductInTrans) {
            ApInventoryTransaction trans = apInventoryTransactionService.getRecord(product.getInventoryTransKey());
            if (trans != null) {
                String warehouseKey = trans.getWarehouseKey();
                if (warehouseKey != null) {
                    List<ApWarehouseProduct> warehouseProducts = apWarehouseProductService.getList("product_key = '" + product.getProductKey() + "' and warehouse_key = '" + warehouseKey + "'");
                    if (warehouseProducts.size() > 0) {
                        ApProducts apProduct = apProductsService.getRecord(warehouseProducts.get(0).getProductKey());
                        String uomGroup = apProduct.getUomGroupKey();
                        BigDecimal quantityInBaseUnit = convert(product.getNewQuentity(), product.getTransUomKey(), apProduct.getBaseUomKey(), uomGroup);
                        BigDecimal oldtotalCost = warehouseProducts.get(0).getQuantity().multiply(warehouseProducts.get(0).getAvgCost() != null ? warehouseProducts.get(0).getAvgCost() : BigDecimal.ZERO);
//                BigDecimal newtotalCost = product.getNewQuentity().multiply(product.getNewCost());
                        BigDecimal newtotalCost = quantityInBaseUnit.multiply(product.getNewCost());
                        BigDecimal denominator = warehouseProducts.get(0).getQuantity().add(quantityInBaseUnit);
                        product.setOldAvgCost(warehouseProducts.get(0).getAvgCost());
                        product.setTotalCost(newtotalCost);
                        if (denominator.equals(new BigDecimal(0))) {
                            warehouseProducts.get(0).setAvgCost(new BigDecimal(0));
                        } else {
                            //Avg cost
                            warehouseProducts.get(0).setAvgCost((oldtotalCost.add(newtotalCost)).divide(denominator, 4, RoundingMode.HALF_UP));
                        }
                        product.setNewAvgCost(warehouseProducts.get(0).getAvgCost());
                        warehouseProducts.get(0).setQuantity(denominator);
                        product.setNewQuentityBaseUom(quantityInBaseUnit);
                        // Insert Lot/Serial Details if applicable
                        List<ApWarehouseProductDetails> details = apWarehouseProductDetailsService.getList(
                                "warehouse_product_key = '" + warehouseProducts.get(0).getKey() + "'");

                        List<ApWarehouseProductDetails> matchingDetails = details.stream()
                                .filter(d -> product.getLotserialnumber().equalsIgnoreCase(d.getKey())
                                        && product.getExpiryDate().equals(d.getExpiryDate()))
                                .collect(Collectors.toList());


                        if (!matchingDetails.isEmpty()) {
                            matchingDetails.get(0).getQuantity().add(quantityInBaseUnit);
                            apWarehouseProductDetailsService.saveRecord(matchingDetails.get(0));
                        } else {
                            ApWarehouseProductDetails warehouseDetail = new ApWarehouseProductDetails();
                            warehouseDetail.setWarehouseProductKey(warehouseProducts.get(0).getKey());
                            warehouseDetail.setQuantity(quantityInBaseUnit);
                            warehouseDetail.setLotSerialNum(product.getLotserialnumber());
                            warehouseDetail.setExpiryDate(product.getExpiryDate());

                            apWarehouseProductDetailsService.saveRecord(warehouseDetail);
                        }
                        product.setIsEffectedWarehouse(true);
                        product.setStatusLkey("1804482322306061"); //Submitted status //TODO Convert LOV key to code
                        apInventoryTransactionProductDAO.saveRecord(product);
                        apWarehouseProductService.saveRecord(warehouseProducts.get(0));
                    }
                }
            }
        }
    }

    public boolean confirmProductTransactionOutWarehouse(String trans_id) throws SQLException {
        List<ApInventoryTransactionProduct> allProductOutTrans = apInventoryTransactionProductDAO.getList("inventory_trans_key = '" + trans_id + "' and is_effected_warehouse = false");

        for (ApInventoryTransactionProduct product : allProductOutTrans) {
            ApInventoryTransaction trans = apInventoryTransactionService.getRecord(product.getInventoryTransKey());
            if (trans != null) {
                String warehouseKey = trans.getWarehouseKey();
                if (warehouseKey != null) {
                    List<ApWarehouseProduct> warehouseProducts = apWarehouseProductService.getList("product_key = '" + product.getProductKey() + "' and warehouse_key = '" + warehouseKey + "'");
                    if (warehouseProducts.size() > 0) {
                        ApProducts apProduct = apProductsService.getRecord(warehouseProducts.get(0).getProductKey());
                        String uomGroup = apProduct.getUomGroupKey();
                        BigDecimal quantityInBaseUnit = convert(product.getNewQuentity(), product.getTransUomKey(), apProduct.getBaseUomKey(), uomGroup);
                        BigDecimal denominator = warehouseProducts.get(0).getQuantity().subtract(quantityInBaseUnit);

                        warehouseProducts.get(0).setQuantity(denominator);
                        product.setNewQuentityBaseUom(quantityInBaseUnit);
                        // Insert Lot/Serial Details if applicable
                        List<ApWarehouseProductDetails> details = apWarehouseProductDetailsService.getList(
                                "warehouse_product_key = '" + warehouseProducts.get(0).getKey() + "'");

                        List<ApWarehouseProductDetails> matchingDetails = details.stream()
                                .filter(d -> product.getLotserialnumber().equalsIgnoreCase(d.getKey()))
                                .collect(Collectors.toList());


                        if (!matchingDetails.isEmpty()) {
                            if (matchingDetails.get(0).getQuantity().compareTo(quantityInBaseUnit) > -1) {
                                BigDecimal newQuantity = matchingDetails.get(0).getQuantity().subtract(quantityInBaseUnit);
                                matchingDetails.get(0).setQuantity(newQuantity);
                                apWarehouseProductDetailsService.saveRecord(matchingDetails.get(0));
                                product.setIsEffectedWarehouse(true);
                                product.setStatusLkey("1804482322306061"); //Submitted status //TODO Convert LOV key to code
                                apInventoryTransactionProductDAO.saveRecord(product);
                                apWarehouseProductService.saveRecord(warehouseProducts.get(0));
                                return true;
                            } else {
                                log.error("Transaction quantity exceeds available stock.");
                                return false;
                            }

                        }


                    }
                }
            }
        }
        return true;
    }




}