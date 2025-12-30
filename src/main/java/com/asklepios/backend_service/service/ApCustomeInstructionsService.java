package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.ApCustomeInstructions;
import com.asklepios.backend_service.model.generated.pojo.ApPrescriptionMedications;
import com.asklepios.backend_service.model.pojo.response.ParentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
 import lombok.extern.slf4j.Slf4j;
 import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApCustomeInstructionsDAO;

@Service
@Slf4j
public class ApCustomeInstructionsService extends ApCustomeInstructionsDAO implements Serializable {

 public ApCustomeInstructions findByPrescriptionMedicationsKey(String prescriptionMedicationsKey) throws SQLException {
  String sql = "SELECT key, prescription_medications_key, dose, unit_lkey, frequency_lkey, roa_lkey " +
          "FROM ap_custome_instructions " +
          "WHERE prescription_medications_key = ? " +
          "  AND (is_valid = true OR is_valid IS NULL) " +
          "LIMIT 1";

  try (Connection con = DS.getConnection();
       PreparedStatement ps = con.prepareStatement(sql)) {

   ps.setString(1, prescriptionMedicationsKey);

   try (ResultSet rs = ps.executeQuery()) {
    if (rs.next()) {
     ApCustomeInstructions instructions = new ApCustomeInstructions();
     instructions.setKey(rs.getString("key"));
     instructions.setPrescriptionMedicationsKey(rs.getString("prescription_medications_key"));
     instructions.setDose(rs.getBigDecimal("dose"));
     instructions.setUnitLkey(rs.getString("unit_lkey"));
     instructions.setFrequencyLkey(rs.getString("frequency_lkey"));
     instructions.setRoaLkey(rs.getString("roa_lkey"));
     return instructions;
    } else {
     return null;
    }
   }
  }
 }


}