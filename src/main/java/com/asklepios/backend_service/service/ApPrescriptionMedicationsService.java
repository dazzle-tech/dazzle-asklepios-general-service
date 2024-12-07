package com.asklepios.backend_service.service;

import java.io.Serializable;
 import java.sql.SQLException;

import com.asklepios.backend_service.model.generated.pojo.ApCustomeInstructions;
import com.asklepios.backend_service.model.generated.pojo.ApPrescriptionMedications;
import com.asklepios.backend_service.model.pojo.response.ParentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
 import lombok.extern.slf4j.Slf4j;
 import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApPrescriptionMedicationsDAO;

@Service
@Slf4j
public class ApPrescriptionMedicationsService extends ApPrescriptionMedicationsDAO implements Serializable {

     ApCustomeInstructionsService apCustomeInstructionsService ;
    public ResponseEntity<?> saveCustomeInstruction(ApPrescriptionMedications pm,String key) throws SQLException {
        try{
            ApCustomeInstructions customeInstructions = new ApCustomeInstructions();
            customeInstructions.setPrescriptionMedicationsKey(key);
            customeInstructions.setFrequencyLkey(pm.getFrequencyLkey());
            customeInstructions.setDose(pm.getDose());
            customeInstructions.setUnitLkey(pm.getUnitLkey());
            ParentResponse<ApCustomeInstructions> response = new ParentResponse<>();

            apCustomeInstructionsService.saveRecord(customeInstructions);
            response.setObject(customeInstructions);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }

    }


}