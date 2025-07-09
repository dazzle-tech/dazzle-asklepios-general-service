package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.asklepios.backend_service.model.generated.pojo.ApGenericMedication;
import com.asklepios.backend_service.model.generated.pojo.ApProducts;
import com.asklepios.backend_service.model.generated.pojo.ApWarehouse;
import com.asklepios.backend_service.model.generated.pojo.ApWarehouseProduct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApWarehouseProductDAO;

@Service
@Slf4j
public class ApWarehouseProductService extends ApWarehouseProductDAO implements Serializable {

    private final ApWarehouseProductDAO apWarehouseProductDAO;
    private final ApWarehouseService apWarehouseService;

    public ApWarehouseProductService(@Qualifier("apWarehouseProductDAO") ApWarehouseProductDAO apWarehouseProductDAO, ApWarehouseService apWarehouseService) {
        super();
        this.apWarehouseProductDAO = apWarehouseProductDAO;
        this.apWarehouseService = apWarehouseService;
    }

    public ApProducts getProduct(String key) throws Exception {

        if(key == null || key.isEmpty()){
            return null;
        }
        ApProductsService apProducts = new ApProductsService();
        return  apProducts.getRecord(key);
    }

    public List<ApWarehouse> warehouseList(List<ApWarehouseProduct> list )throws Exception {

        List<String> keys = list.stream()
                .map(ApWarehouseProduct::getWarehouseKey)
                .collect(Collectors.toList());

        if (!keys.isEmpty()) {
            List<ApWarehouse> warehouseList = apWarehouseService.getList("key IN ('" + String.join("','", keys) + "')");

            return warehouseList;
        }
            return null;
    }

}