package com.asklepios.backend_service.service;

import java.io.Serializable;

import com.asklepios.backend_service.model.generated.pojo.ApDepartment;
import com.asklepios.backend_service.model.generated.pojo.ApProducts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApWarehouseDAO;

@Service
@Slf4j
public class ApWarehouseService extends ApWarehouseDAO implements Serializable {


    public ApDepartment getDepartment(String key) throws Exception {

        if(key == null || key.isEmpty()){
            return null;
        }
        ApDepartmentService apDepartmentService = new ApDepartmentService();
        return  apDepartmentService.getRecord(key);
    }

}