package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.ApDepartment;
import com.asklepios.backend_service.model.generated.pojo.ApGenericMedicationRoa;
import com.asklepios.backend_service.model.generated.pojo.ApPatientAllergies;
import com.asklepios.backend_service.model.generated.pojo.ApPractitioner;
import com.asklepios.backend_service.model.pojo.response.ParentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApGenericMedicationRoaDAO;

@Service
@Slf4j
public class ApGenericMedicationRoaService extends ApGenericMedicationRoaDAO implements Serializable {

    public void saveROA(List<String> roaLKey, String medicationKey) throws SQLException {
       try{
        if(roaLKey.isEmpty())  {
            return;
        }
        List <ApGenericMedicationRoa> apGenericMedicationRoaList = getList("generic_medication_key='"+medicationKey+"'");
        List <String> existingRoaKeys = new ArrayList<>();
           for (ApGenericMedicationRoa apGenericMedicationRoa : apGenericMedicationRoaList) {
               existingRoaKeys.add(apGenericMedicationRoa.getRoaLkey());
               if (!roaLKey.contains(apGenericMedicationRoa.getRoaLkey())) {
                   new ApGenericMedicationRoaDAO().deleteRecord(apGenericMedicationRoa);
               }
           }
        for(String roa : roaLKey) {
            if (!existingRoaKeys.contains(roa)) {
                ApGenericMedicationRoa apGenericMedicationRoa = new ApGenericMedicationRoa();
                apGenericMedicationRoa.setGenericMedicationKey(medicationKey);
                apGenericMedicationRoa.setRoaLkey(roa);
                new ApGenericMedicationRoaDAO().saveRecord(apGenericMedicationRoa);
            }
        }
       } catch (Exception e) {
           e.printStackTrace();
           log.error(e.getMessage());
       }
    }

    public void deleteAllROA(String medicationKey) throws SQLException {
      try {
          if(medicationKey == null)  {
              return;
          }
          List <ApGenericMedicationRoa> apGenericMedicationRoaList = getList("generic_medication_key='"+medicationKey+"'");
          if(!apGenericMedicationRoaList.isEmpty())  {
              for(ApGenericMedicationRoa apGenericMedicationRoa : apGenericMedicationRoaList){
                  new ApGenericMedicationRoaDAO().deleteRecord(apGenericMedicationRoa);
              }
          }

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

}