package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.List;

import com.asklepios.backend_service.model.generated.pojo.ApIcdCode;
import com.asklepios.backend_service.model.generated.pojo.ApInventoryTransaction;
import com.asklepios.backend_service.model.generated.pojo.ApInventoryTransactionProduct;
import com.asklepios.backend_service.model.generated.pojo.ApWarehouseProduct;
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

    public ApInventoryTransactionProductService(ApInventoryTransactionService apInventoryTransactionService, @Qualifier("apInventoryTransactionProductDAO") ApInventoryTransactionProductDAO apInventoryTransactionProductDAO, ApWarehouseProductService apWarehouseProductService) {
        super();
        this.apInventoryTransactionService = apInventoryTransactionService;
        this.apInventoryTransactionProductDAO = apInventoryTransactionProductDAO;
        this.apWarehouseProductService = apWarehouseProductService;
    }

    public void confirmProductTransactionOnWarehouse(String trans_id) throws SQLException {
      List<ApInventoryTransactionProduct> allProductInTrans = apInventoryTransactionProductDAO.getList("inventory_trans_key = '"+trans_id+"' and is_effected_warehouse = false");

      for(ApInventoryTransactionProduct product : allProductInTrans) {
         ApInventoryTransaction trans = apInventoryTransactionService.getRecord(product.getInventoryTransKey());
          if(trans != null ) {
              String warehouseKey  = trans.getWarehouseKey();
              if(warehouseKey != null) {
                  List<ApWarehouseProduct> warehouseProducts = apWarehouseProductService.getList("product_key = '"+product.getProductKey()+"' and warehouse_key = '"+warehouseKey+"'");
              if(warehouseProducts.size() > 0) {
                  BigDecimal oldtotalCost = warehouseProducts.get(0).getQuantity().multiply(warehouseProducts.get(0).getAvgCost() != null ? warehouseProducts.get(0).getAvgCost() : BigDecimal.ZERO);
                  BigDecimal newtotalCost = product.getNewQuentity().multiply(product.getNewCost());

                  BigDecimal denominator = warehouseProducts.get(0).getQuantity().add(product.getNewQuentity());

                  if(denominator.equals(new BigDecimal(0))) {
                      warehouseProducts.get(0).setAvgCost(new BigDecimal(0));
                  }else{
                      //Avg cost
                      warehouseProducts.get(0).setAvgCost((oldtotalCost.add(newtotalCost)).divide(denominator,  4, RoundingMode.HALF_UP));
                  }

                  warehouseProducts.get(0).setQuantity(denominator );
                  product.setIsEffectedWarehouse(true);
                  apInventoryTransactionProductDAO. saveRecord(product);
                  apWarehouseProductService.saveRecord( warehouseProducts.get(0));
                                    }
              }
          }
      }
    }

}