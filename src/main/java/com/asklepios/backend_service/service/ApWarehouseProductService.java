package com.asklepios.backend_service.service;

import java.io.Serializable;

import com.asklepios.backend_service.model.generated.pojo.ApGenericMedication;
import com.asklepios.backend_service.model.generated.pojo.ApProducts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApWarehouseProductDAO;

@Service
@Slf4j
public class ApWarehouseProductService extends ApWarehouseProductDAO implements Serializable {

    public ApProducts getProduct(String key) throws Exception {

        if(key == null || key.isEmpty()){
            return null;
        }
        ApProductsService apProducts = new ApProductsService();
        return  apProducts.getRecord(key);
    }

}