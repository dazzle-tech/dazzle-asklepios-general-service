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
import com.asklepios.backend_service.model.generated.pojo.ApPatientAllergies;
import com.asklepios.backend_service.model.generated.entity.ApPatientAllergiesEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApPatientAllergiesDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApPatientAllergies getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_patient_allergies where key = '"+key+"'");) {
ApPatientAllergies record = new ApPatientAllergies();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setAllergyKey(rs.getString("allergy_key"));
record.setAllergenTypeLkey(rs.getString("allergen_type_lkey"));
record.setSeverityLkey(rs.getString("severity_lkey"));
record.setReaction(rs.getString("reaction"));
record.setDateDiagnosed(rs.getDate("date_diagnosed"));
record.setResolutionStatusLkey(rs.getString("resolution_status_lkey"));
record.setDateResolved(rs.getDate("date_resolved"));
record.setTreatmentPlan(rs.getString("treatment_plan"));
record.setNotes(rs.getString("notes"));
record.setSourceOfInfoLkey(rs.getString("source_of_info_lkey"));
record.setLifeThreating(rs.getBoolean("life_threating"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setAddedByVisitKey(rs.getString("added_by_visit_key"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApPatientAllergies record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_patient_allergies set key = ?, patient_key = ?, allergy_key = ?, allergen_type_lkey = ?, severity_lkey = ?, reaction = ?, date_diagnosed = ?, resolution_status_lkey = ?, date_resolved = ?, treatment_plan = ?, notes = ?, source_of_info_lkey = ?, life_threating = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ?, added_by_visit_key = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getAllergyKey());
ps.setString(4, record.getAllergenTypeLkey());
ps.setString(5, record.getSeverityLkey());
ps.setString(6, record.getReaction());
if (record.getDateDiagnosed() != null) ps.setDate(7, new java.sql.Date(record.getDateDiagnosed().getTime()));
else ps.setDate(7, null); 
ps.setString(8, record.getResolutionStatusLkey());
if (record.getDateResolved() != null) ps.setDate(9, new java.sql.Date(record.getDateResolved().getTime()));
else ps.setDate(9, null); 
ps.setString(10, record.getTreatmentPlan());
ps.setString(11, record.getNotes());
ps.setString(12, record.getSourceOfInfoLkey());
ps.setBoolean(13, record.getLifeThreating());
ps.setString(14, record.getCreatedBy());
ps.setString(15, record.getUpdatedBy());
ps.setString(16, record.getDeletedBy());
ps.setBigDecimal(17, record.getCreatedAt());
ps.setBigDecimal(18, record.getUpdatedAt());
ps.setBigDecimal(19, record.getDeletedAt());
ps.setBoolean(20, record.getIsValid());
ps.setString(21, record.getAddedByVisitKey());
ps.setString(22, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApPatientAllergies record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_patient_allergies set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApPatientAllergies> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_patient_allergies where "+ where);) {
List<ApPatientAllergies> list = new ArrayList<ApPatientAllergies>();
while(rs.next()){
ApPatientAllergies record = new ApPatientAllergies();
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setAllergyKey(rs.getString("allergy_key"));
record.setAllergenTypeLkey(rs.getString("allergen_type_lkey"));
record.setSeverityLkey(rs.getString("severity_lkey"));
record.setReaction(rs.getString("reaction"));
record.setDateDiagnosed(rs.getDate("date_diagnosed"));
record.setResolutionStatusLkey(rs.getString("resolution_status_lkey"));
record.setDateResolved(rs.getDate("date_resolved"));
record.setTreatmentPlan(rs.getString("treatment_plan"));
record.setNotes(rs.getString("notes"));
record.setSourceOfInfoLkey(rs.getString("source_of_info_lkey"));
record.setLifeThreating(rs.getBoolean("life_threating"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setAddedByVisitKey(rs.getString("added_by_visit_key"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApPatientAllergies record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_patient_allergies values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getAllergyKey());
ps.setString(4, record.getAllergenTypeLkey());
ps.setString(5, record.getSeverityLkey());
ps.setString(6, record.getReaction());
if (record.getDateDiagnosed() != null) ps.setDate(7, new java.sql.Date(record.getDateDiagnosed().getTime()));
else ps.setDate(7, null); 
ps.setString(8, record.getResolutionStatusLkey());
if (record.getDateResolved() != null) ps.setDate(9, new java.sql.Date(record.getDateResolved().getTime()));
else ps.setDate(9, null); 
ps.setString(10, record.getTreatmentPlan());
ps.setString(11, record.getNotes());
ps.setString(12, record.getSourceOfInfoLkey());
ps.setBoolean(13, record.getLifeThreating());
ps.setString(14, record.getCreatedBy());
ps.setString(15, record.getUpdatedBy());
ps.setString(16, record.getDeletedBy());
ps.setBigDecimal(17, record.getCreatedAt());
ps.setBigDecimal(18, record.getUpdatedAt());
ps.setBigDecimal(19, record.getDeletedAt());
ps.setBoolean(20, record.getIsValid());
ps.setString(21, record.getAddedByVisitKey());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApPatientAllergiesEntity entity, String lang) {
        Class<?> myClass = ApPatientAllergiesEntity.class;
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
public void translateObject(ApPatientAllergiesEntity entity, String lang) {
        ApPatientAllergiesEntity translated = (ApPatientAllergiesEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}