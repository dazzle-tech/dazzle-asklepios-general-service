package com.asklepios.backend_service.model.generated.dao;

import java.io.Serializable;
import lombok.extern.slf4j.Slf4j;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;
import java.lang.System;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.asklepios.backend_service.controller.PublicServices;
import java.lang.reflect.Field;
import com.asklepios.backend_service.model.generated.pojo.ApPreProcedureAssessment;
import com.asklepios.backend_service.model.generated.entity.ApPreProcedureAssessmentEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApPreProcedureAssessmentDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApPreProcedureAssessment getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_pre_procedure_assessment where key = '"+key+"'");) {
ApPreProcedureAssessment record = new ApPreProcedureAssessment();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setProcedureKey(rs.getString("procedure_key"));
record.setFastingRequired(rs.getBoolean("fasting_required"));
record.setPatientPrepared(rs.getBoolean("patient_prepared"));
record.setSpecialInstructions(rs.getString("special_instructions"));
record.setBloodPressureSystolic(rs.getBigDecimal("blood_pressure_systolic"));
record.setBloodPressureDiastolic(rs.getBigDecimal("blood_pressure_diastolic"));
record.setHeartRate(rs.getBigDecimal("heart_rate"));
record.setTemperature(rs.getBigDecimal("temperature"));
record.setOxygenSaturation(rs.getBigDecimal("oxygen_saturation"));
record.setPatientIdentityVerified(rs.getBoolean("patient_identity_verified"));
record.setConsentConfirmed(rs.getBoolean("consent_confirmed"));
record.setProcedureSiteMarked(rs.getBoolean("procedure_site_marked"));
record.setAllergiesConfirmed(rs.getBoolean("allergies_confirmed"));
record.setPatientPremedicated(rs.getBoolean("patient_premedicated"));
record.setEquipmentCountingDone(rs.getBoolean("equipment_counting_done"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsvalid(rs.getBoolean("isvalid"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApPreProcedureAssessment record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_pre_procedure_assessment set key = ?, procedure_key = ?, fasting_required = ?, patient_prepared = ?, special_instructions = ?, blood_pressure_systolic = ?, blood_pressure_diastolic = ?, heart_rate = ?, temperature = ?, oxygen_saturation = ?, patient_identity_verified = ?, consent_confirmed = ?, procedure_site_marked = ?, allergies_confirmed = ?, patient_premedicated = ?, equipment_counting_done = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, isvalid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getProcedureKey());
ps.setBoolean(3, record.getFastingRequired());
ps.setBoolean(4, record.getPatientPrepared());
ps.setString(5, record.getSpecialInstructions());
ps.setBigDecimal(6, record.getBloodPressureSystolic());
ps.setBigDecimal(7, record.getBloodPressureDiastolic());
ps.setBigDecimal(8, record.getHeartRate());
ps.setBigDecimal(9, record.getTemperature());
ps.setBigDecimal(10, record.getOxygenSaturation());
ps.setBoolean(11, record.getPatientIdentityVerified());
ps.setBoolean(12, record.getConsentConfirmed());
ps.setBoolean(13, record.getProcedureSiteMarked());
ps.setBoolean(14, record.getAllergiesConfirmed());
ps.setBoolean(15, record.getPatientPremedicated());
ps.setBoolean(16, record.getEquipmentCountingDone());
ps.setString(17, record.getCreatedBy());
ps.setString(18, record.getUpdatedBy());
ps.setString(19, record.getDeletedBy());
ps.setBigDecimal(20, record.getCreatedAt());
ps.setBigDecimal(21, record.getUpdatedAt());
ps.setBigDecimal(22, record.getDeletedAt());
ps.setBoolean(23, record.getIsvalid());
ps.setString(24, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApPreProcedureAssessment record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_pre_procedure_assessment set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApPreProcedureAssessment> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_pre_procedure_assessment where "+ where);) {
List<ApPreProcedureAssessment> list = new ArrayList<ApPreProcedureAssessment>();
while(rs.next()){
ApPreProcedureAssessment record = new ApPreProcedureAssessment();
record.setKey(rs.getString("key"));
record.setProcedureKey(rs.getString("procedure_key"));
record.setFastingRequired(rs.getBoolean("fasting_required"));
record.setPatientPrepared(rs.getBoolean("patient_prepared"));
record.setSpecialInstructions(rs.getString("special_instructions"));
record.setBloodPressureSystolic(rs.getBigDecimal("blood_pressure_systolic"));
record.setBloodPressureDiastolic(rs.getBigDecimal("blood_pressure_diastolic"));
record.setHeartRate(rs.getBigDecimal("heart_rate"));
record.setTemperature(rs.getBigDecimal("temperature"));
record.setOxygenSaturation(rs.getBigDecimal("oxygen_saturation"));
record.setPatientIdentityVerified(rs.getBoolean("patient_identity_verified"));
record.setConsentConfirmed(rs.getBoolean("consent_confirmed"));
record.setProcedureSiteMarked(rs.getBoolean("procedure_site_marked"));
record.setAllergiesConfirmed(rs.getBoolean("allergies_confirmed"));
record.setPatientPremedicated(rs.getBoolean("patient_premedicated"));
record.setEquipmentCountingDone(rs.getBoolean("equipment_counting_done"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsvalid(rs.getBoolean("isvalid"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApPreProcedureAssessment record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_pre_procedure_assessment values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getProcedureKey());
ps.setBoolean(3, record.getFastingRequired());
ps.setBoolean(4, record.getPatientPrepared());
ps.setString(5, record.getSpecialInstructions());
ps.setBigDecimal(6, record.getBloodPressureSystolic());
ps.setBigDecimal(7, record.getBloodPressureDiastolic());
ps.setBigDecimal(8, record.getHeartRate());
ps.setBigDecimal(9, record.getTemperature());
ps.setBigDecimal(10, record.getOxygenSaturation());
ps.setBoolean(11, record.getPatientIdentityVerified());
ps.setBoolean(12, record.getConsentConfirmed());
ps.setBoolean(13, record.getProcedureSiteMarked());
ps.setBoolean(14, record.getAllergiesConfirmed());
ps.setBoolean(15, record.getPatientPremedicated());
ps.setBoolean(16, record.getEquipmentCountingDone());
ps.setString(17, record.getCreatedBy());
ps.setString(18, record.getUpdatedBy());
ps.setString(19, record.getDeletedBy());
ps.setBigDecimal(20, record.getCreatedAt());
ps.setBigDecimal(21, record.getUpdatedAt());
ps.setBigDecimal(22, record.getDeletedAt());
ps.setBoolean(23, record.getIsvalid());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApPreProcedureAssessmentEntity entity, String lang) {
        Class<?> myClass = ApPreProcedureAssessmentEntity.class;
        Field[] fields = myClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            if (fieldName.contains("Lkey")) {
                try {
                    Object fieldValue = field.get(entity);
                    if (fieldValue != null) {
                        String _lovKey = fieldValue.toString();
                        Field valueField = myClass.getDeclaredField (fieldName.replaceAll("Lkey", "Lvalue"));
                        valueField.setAccessible(true);
                        if (lang == null) {
                            valueField.set(entity, publicServices.getFromRedisLovValue(_lovKey));
                        } else {
                            valueField.set(entity, publicServices.getFromRedisLovValue(_lovKey,lang));
                        }                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
public void translateObject(ApPreProcedureAssessmentEntity entity, String lang) {
        ApPreProcedureAssessmentEntity translated = (ApPreProcedureAssessmentEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}