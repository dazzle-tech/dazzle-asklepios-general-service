package com.asklepios.backend_service.service;

import java.io.Serializable;

import com.asklepios.backend_service.model.generated.pojo.ApGenericMedication;
import com.asklepios.backend_service.model.generated.pojo.ApLovValues;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApUomGroupsUnitsDAO;

@Service
@Slf4j
public class ApUomGroupsUnitsService extends ApUomGroupsUnitsDAO implements Serializable {

    public String getUnitName(String key) throws Exception {

        if(key == null || key.isEmpty()){
            return null;
        }
        ApLovValues apLovValues = new ApLovValuesService().getRecord(key);

        if(apLovValues == null){
            return null;
        }
        return apLovValues.getLovDisplayVale();
    }

}