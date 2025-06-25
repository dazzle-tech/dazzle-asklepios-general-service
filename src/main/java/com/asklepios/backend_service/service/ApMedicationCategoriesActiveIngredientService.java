package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.asklepios.backend_service.model.generated.pojo.ApMedicationCategoriesActiveIngredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApMedicationCategoriesActiveIngredientDAO;

@Service
@Slf4j
public class ApMedicationCategoriesActiveIngredientService extends ApMedicationCategoriesActiveIngredientDAO implements Serializable {
    public void saveActiveIngredient(List<String> activeIngredientKey, ApMedicationCategoriesActiveIngredient obj) throws SQLException {
        try{
            if(activeIngredientKey.isEmpty())  {
                return;
            }
            List <ApMedicationCategoriesActiveIngredient> allList = getList("medication_class_key='"+obj.getMedicationClassKey()+"'");
            List <String> existingLovKeys = new ArrayList<>();

            for (ApMedicationCategoriesActiveIngredient key : allList) {
                existingLovKeys.add(key.getActiveIngredientKey());
                if (!activeIngredientKey.contains(key.getActiveIngredientKey())) {
                    new ApMedicationCategoriesActiveIngredientDAO().deleteRecord(key);
                }
            }
            for(String key : activeIngredientKey) {
                if (!existingLovKeys.contains(key)) {
                    ApMedicationCategoriesActiveIngredient activeIng = new ApMedicationCategoriesActiveIngredient();
                    activeIng.setActiveIngredientKey(key);
                    activeIng.setMedicationClassKey(obj.getMedicationClassKey());
                    new ApMedicationCategoriesActiveIngredientDAO().saveRecord(activeIng);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

}