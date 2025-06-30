package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.asklepios.backend_service.model.generated.dao.ApGenericMedicationDAO;
import com.asklepios.backend_service.model.generated.pojo.ApGenericMedication;
import com.asklepios.backend_service.model.generated.pojo.ApTranslation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApProductsDAO;

@Service
@Slf4j
public class ApProductsService extends ApProductsDAO implements Serializable {

    public ApGenericMedication getMedication(String key) throws Exception {

        if(key == null || key.isEmpty()){
          return null;
        }
        ApGenericMedicationService apGenericMedicationService = new ApGenericMedicationService();
        return  apGenericMedicationService.getRecord(key);
    }
}