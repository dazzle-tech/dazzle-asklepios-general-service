package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.asklepios.backend_service.model.generated.pojo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApInventoryTransferProductDAO;

@Service
@Slf4j
public class ApInventoryTransferProductService extends ApInventoryTransferProductDAO implements Serializable {


    private final ApWarehouseProductService apWarehouseProductService;
    private final ApInventoryTransferService apInventoryTransferService;
    private final ApProductsService apProductsService;
    private final ApInventoryTransactionProductService apInventoryTransactionProductService;
    private final ApWarehouseProductDetailsService apWarehouseProductDetailsService;
    private final ApInventoryTransactionService apInventoryTransactionService;

    public ApInventoryTransferProductService(ApWarehouseProductService apWarehouseProductService, ApInventoryTransferService apInventoryTransferService, ApProductsService apProductsService, ApInventoryTransactionProductService apInventoryTransactionProductService, ApWarehouseProductDetailsService apWarehouseProductDetailsService, ApInventoryTransactionService apInventoryTransactionService) {
        super();
        this.apWarehouseProductService = apWarehouseProductService;
        this.apInventoryTransferService = apInventoryTransferService;
        this.apProductsService = apProductsService;
        this.apInventoryTransactionService = apInventoryTransactionService;
        this.apInventoryTransactionProductService = apInventoryTransactionProductService;
        this.apWarehouseProductDetailsService = apWarehouseProductDetailsService;
    }

    public void confirmProductTransferInWarehouse(ApInventoryTransferProduct transferProduct) throws SQLException {
        ApInventoryTransfer transfer = apInventoryTransferService.getRecord(transferProduct.getTransferKey());
        List<ApWarehouseProduct> warehouseProducts = apWarehouseProductService.getList("warehouse_key = '"+transfer.getToWarehouseKey()+"' and product_key ='"+transferProduct.getProductKey()+"'");
        if(!warehouseProducts.isEmpty()) {
            ApProducts apProduct = apProductsService.getRecord(warehouseProducts.get(0).getProductKey());
            String uomGroup= apProduct.getUomGroupKey();
            BigDecimal quantityInBaseUnit = apInventoryTransactionProductService.convert(transferProduct.getQuentityApproved()  , transferProduct.getTransUomKey() , apProduct.getBaseUomKey() , uomGroup);
            BigDecimal denominator = warehouseProducts.get(0).getQuantity().add(quantityInBaseUnit);
            warehouseProducts.get(0).setQuantity(denominator);
            transferProduct.setQuentityApprovedBaseUom(quantityInBaseUnit);
            // Insert Lot/Serial Details if applicable
            List<ApWarehouseProductDetails> details = apWarehouseProductDetailsService.getList(
                    "warehouse_product_key = '" +  warehouseProducts.get(0).getKey() + "'");

            //Update the warehouse product and product details
            List<ApWarehouseProductDetails> matchingDetails = details.stream()
                    .filter(d -> transferProduct.getLotserialnumber().equalsIgnoreCase(d.getKey()))
                    .collect(Collectors.toList());


            if (!matchingDetails.isEmpty()) {

                ApWarehouseProductDetails warehouseDetail = new ApWarehouseProductDetails();
                BigDecimal newQuantity =matchingDetails.get(0).getQuantity().add(quantityInBaseUnit);
                warehouseDetail.setQuantity(newQuantity);
                warehouseDetail.setWarehouseProductKey(warehouseProducts.get(0).getKey());
                warehouseDetail.setQuantity(quantityInBaseUnit);
                warehouseDetail.setLotSerialNum(matchingDetails.get(0).getLotSerialNum());
                warehouseDetail.setExpiryDate(matchingDetails.get(0).getExpiryDate());
                apWarehouseProductDetailsService.saveRecord(warehouseDetail);
                transferProduct.setLotserialnumber(warehouseDetail.getKey());
            }

            //Add transaction to inventory transaction as stock in
            List<ApInventoryTransaction> allTrans = apInventoryTransactionService.getList("trans_id = '"+transfer.getKey()+"'");
            ApInventoryTransaction trans = new ApInventoryTransaction();
            if(allTrans.isEmpty()) {
                ApInventoryTransaction newTransaction = new ApInventoryTransaction();
                newTransaction.setTransTypeLkey("6509244814441399"); // Transaction type Stock In
                newTransaction.setWarehouseKey(transfer.getToWarehouseKey());
                newTransaction.setTransReasonLkey("7762344057592335"); //Internal Transfer
                newTransaction.setTransId(transfer.getKey());
                apInventoryTransactionService.saveRecord(newTransaction);
                trans = newTransaction ;
            }else{
                trans = allTrans.get(0);
            }

            // Add product
            ApInventoryTransactionProduct newTransactionProduct = new ApInventoryTransactionProduct();
            newTransactionProduct.setProductKey(transferProduct.getProductKey());
            newTransactionProduct.setInventoryTransKey(trans.getKey());
            newTransactionProduct.setNewQuentity(transferProduct.getQuentityApproved());
            newTransactionProduct.setLotserialnumber(transferProduct.getLotserialnumber());
            newTransactionProduct.setTransUomKey(transferProduct.getTransUomKey());
            newTransactionProduct.setNewQuentityBaseUom(quantityInBaseUnit);
            apInventoryTransactionProductService.saveRecord(newTransactionProduct);

        }
    }


public void confirmProductTransferOutWarehouse(ApInventoryTransferProduct transferProduct) throws SQLException {
    ApInventoryTransfer transfer = apInventoryTransferService.getRecord(transferProduct.getTransferKey());
    List<ApWarehouseProduct> warehouseProducts = apWarehouseProductService.getList("warehouse_key = '"+transfer.getFromWarehouseKey()+"' and product_key ='"+transferProduct.getProductKey()+"'");
    if(!warehouseProducts.isEmpty()) {
        ApProducts apProduct = apProductsService.getRecord(warehouseProducts.get(0).getProductKey());
        String uomGroup= apProduct.getUomGroupKey();
        BigDecimal quantityInBaseUnit = apInventoryTransactionProductService.convert(transferProduct.getQuentityApproved()  , transferProduct.getTransUomKey() , apProduct.getBaseUomKey() , uomGroup);
        BigDecimal denominator = warehouseProducts.get(0).getQuantity().subtract(quantityInBaseUnit);
        warehouseProducts.get(0).setQuantity(denominator);
        transferProduct.setQuentityApprovedBaseUom(quantityInBaseUnit);
        // Insert Lot/Serial Details if applicable
        List<ApWarehouseProductDetails> details = apWarehouseProductDetailsService.getList(
                "warehouse_product_key = '" +  warehouseProducts.get(0).getKey() + "'");

        //Update the warehouse product and product details
        List<ApWarehouseProductDetails> matchingDetails = details.stream()
                .filter(d -> transferProduct.getLotserialnumber().equalsIgnoreCase(d.getKey()))
                .collect(Collectors.toList());


        if (!matchingDetails.isEmpty()) {
            BigDecimal newQuantity =matchingDetails.get(0).getQuantity().subtract(quantityInBaseUnit);
            matchingDetails.get(0).setQuantity(newQuantity);
            apWarehouseProductDetailsService.saveRecord(matchingDetails.get(0));
        }

        //Add Transaction to inventory transaction as stock out
        List<ApInventoryTransaction> allTrans = apInventoryTransactionService.getList("trans_id = '"+transfer.getKey()+"'");
        ApInventoryTransaction trans = new ApInventoryTransaction();
        if(allTrans.isEmpty()) {
            ApInventoryTransaction newTransaction = new ApInventoryTransaction();
            newTransaction.setTransTypeLkey("6509266518641689"); // Transaction type Stock Out
            newTransaction.setWarehouseKey(transfer.getFromWarehouseKey());
            newTransaction.setTransReasonLkey("7762280996386777"); //Transfer to Another Warehouse
            newTransaction.setTransId(transfer.getKey());
            apInventoryTransactionService.saveRecord(newTransaction);
            trans = newTransaction ;
        }else{
            trans = allTrans.get(0);
        }

        //Add in product
        ApInventoryTransactionProduct newTransactionProduct = new ApInventoryTransactionProduct();
        newTransactionProduct.setProductKey(transferProduct.getProductKey());
        newTransactionProduct.setInventoryTransKey(trans.getKey());
        newTransactionProduct.setNewQuentity(transferProduct.getQuentityApproved());
        newTransactionProduct.setLotserialnumber(transferProduct.getLotserialnumber());
        newTransactionProduct.setTransUomKey(transferProduct.getTransUomKey());
        newTransactionProduct.setNewQuentityBaseUom(quantityInBaseUnit);
        apInventoryTransactionProductService.saveRecord(newTransactionProduct);

    }
}

    }
