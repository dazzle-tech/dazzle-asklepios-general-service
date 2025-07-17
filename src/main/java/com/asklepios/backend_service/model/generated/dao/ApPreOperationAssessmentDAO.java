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
import com.asklepios.backend_service.model.generated.pojo.ApPreOperationAssessment;
import com.asklepios.backend_service.model.generated.entity.ApPreOperationAssessmentEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApPreOperationAssessmentDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApPreOperationAssessment getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_pre_operation_assessment where key = '"+key+"'");) {
ApPreOperationAssessment record = new ApPreOperationAssessment();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setNopStatusConfirmed(rs.getBoolean("nop_status_confirmed"));
record.setPainScoreLkey(rs.getString("pain_score_lkey"));
record.setAllergiesReviewed(rs.getBoolean("allergies_reviewed"));
record.setConsentForProcedureSigned(rs.getBoolean("consent_for_procedure_signed"));
record.setConsentForAnesthesiaSigned(rs.getBoolean("consent_for_anesthesia_signed"));
record.setIvAccessStatus(rs.getBoolean("iv_access_status"));
record.setSiteMarkedBySurgeon(rs.getBoolean("site_marked_by_surgeon"));
record.setLabImagingReviewed(rs.getBoolean("lab_imaging_reviewed"));
record.setAnesthetistAssessmentDone(rs.getBoolean("anesthetist_assessment_done"));
record.setAsaClassificationLkey(rs.getString("asa_classification_lkey"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApPreOperationAssessment record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_pre_operation_assessment set key = ?, patient_key = ?, encounter_key = ?, nop_status_confirmed = ?, pain_score_lkey = ?, allergies_reviewed = ?, consent_for_procedure_signed = ?, consent_for_anesthesia_signed = ?, iv_access_status = ?, site_marked_by_surgeon = ?, lab_imaging_reviewed = ?, anesthetist_assessment_done = ?, asa_classification_lkey = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getEncounterKey());
ps.setBoolean(4, record.getNopStatusConfirmed());
ps.setString(5, record.getPainScoreLkey());
ps.setBoolean(6, record.getAllergiesReviewed());
ps.setBoolean(7, record.getConsentForProcedureSigned());
ps.setBoolean(8, record.getConsentForAnesthesiaSigned());
ps.setBoolean(9, record.getIvAccessStatus());
ps.setBoolean(10, record.getSiteMarkedBySurgeon());
ps.setBoolean(11, record.getLabImagingReviewed());
ps.setBoolean(12, record.getAnesthetistAssessmentDone());
ps.setString(13, record.getAsaClassificationLkey());
ps.setString(14, record.getCreatedBy());
ps.setString(15, record.getUpdatedBy());
ps.setString(16, record.getDeletedBy());
ps.setBigDecimal(17, record.getCreatedAt());
ps.setBigDecimal(18, record.getUpdatedAt());
ps.setBigDecimal(19, record.getDeletedAt());
ps.setString(20, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApPreOperationAssessment record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_pre_operation_assessment set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApPreOperationAssessment> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_pre_operation_assessment where "+ where);) {
List<ApPreOperationAssessment> list = new ArrayList<ApPreOperationAssessment>();
while(rs.next()){
ApPreOperationAssessment record = new ApPreOperationAssessment();
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setNopStatusConfirmed(rs.getBoolean("nop_status_confirmed"));
record.setPainScoreLkey(rs.getString("pain_score_lkey"));
record.setAllergiesReviewed(rs.getBoolean("allergies_reviewed"));
record.setConsentForProcedureSigned(rs.getBoolean("consent_for_procedure_signed"));
record.setConsentForAnesthesiaSigned(rs.getBoolean("consent_for_anesthesia_signed"));
record.setIvAccessStatus(rs.getBoolean("iv_access_status"));
record.setSiteMarkedBySurgeon(rs.getBoolean("site_marked_by_surgeon"));
record.setLabImagingReviewed(rs.getBoolean("lab_imaging_reviewed"));
record.setAnesthetistAssessmentDone(rs.getBoolean("anesthetist_assessment_done"));
record.setAsaClassificationLkey(rs.getString("asa_classification_lkey"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApPreOperationAssessment record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_pre_operation_assessment values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getEncounterKey());
ps.setBoolean(4, record.getNopStatusConfirmed());
ps.setString(5, record.getPainScoreLkey());
ps.setBoolean(6, record.getAllergiesReviewed());
ps.setBoolean(7, record.getConsentForProcedureSigned());
ps.setBoolean(8, record.getConsentForAnesthesiaSigned());
ps.setBoolean(9, record.getIvAccessStatus());
ps.setBoolean(10, record.getSiteMarkedBySurgeon());
ps.setBoolean(11, record.getLabImagingReviewed());
ps.setBoolean(12, record.getAnesthetistAssessmentDone());
ps.setString(13, record.getAsaClassificationLkey());
ps.setString(14, record.getCreatedBy());
ps.setString(15, record.getUpdatedBy());
ps.setString(16, record.getDeletedBy());
ps.setBigDecimal(17, record.getCreatedAt());
ps.setBigDecimal(18, record.getUpdatedAt());
ps.setBigDecimal(19, record.getDeletedAt());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApPreOperationAssessmentEntity entity, String lang) {
        Class<?> myClass = ApPreOperationAssessmentEntity.class;
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
public void translateObject(ApPreOperationAssessmentEntity entity, String lang) {
        ApPreOperationAssessmentEntity translated = (ApPreOperationAssessmentEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}