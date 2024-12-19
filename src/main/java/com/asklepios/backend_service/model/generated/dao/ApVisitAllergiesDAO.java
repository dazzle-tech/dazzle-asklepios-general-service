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
import com.asklepios.backend_service.model.generated.pojo.ApVisitAllergies;
import com.asklepios.backend_service.model.generated.entity.ApVisitAllergiesEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApVisitAllergiesDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApVisitAllergies getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_visit_allergies where key = '"+key+"'");) {
ApVisitAllergies record = new ApVisitAllergies();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setVisitKey(rs.getString("visit_key"));
record.setStatusLkey(rs.getString("status_lkey"));
record.setAllergyTypeLkey(rs.getString("allergy_type_lkey"));
record.setAllergenKey(rs.getString("allergen_key"));
record.setOnsetLkey(rs.getString("onset_lkey"));
record.setOnsetDate(rs.getBigDecimal("onset_date"));
record.setTreatmentStrategyLkey(rs.getString("treatment_strategy_lkey"));
record.setSourceOfInformationLkey(rs.getString("source_of_information_lkey"));
record.setReactionDescription(rs.getString("reaction_description"));
record.setNotes(rs.getString("notes"));
record.setCancellationReason(rs.getString("cancellation_reason"));
record.setResolvedBy(rs.getString("resolved_by"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setResolvedAt(rs.getBigDecimal("resolved_at"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setSeverityLkey(rs.getString("severity_lkey"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApVisitAllergies record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_visit_allergies set key = ?, patient_key = ?, visit_key = ?, status_lkey = ?, allergy_type_lkey = ?, allergen_key = ?, onset_lkey = ?, onset_date = ?, treatment_strategy_lkey = ?, source_of_information_lkey = ?, reaction_description = ?, notes = ?, cancellation_reason = ?, resolved_by = ?, created_by = ?, updated_by = ?, deleted_by = ?, resolved_at = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ?, severity_lkey = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getVisitKey());
ps.setString(4, record.getStatusLkey());
ps.setString(5, record.getAllergyTypeLkey());
ps.setString(6, record.getAllergenKey());
ps.setString(7, record.getOnsetLkey());
ps.setBigDecimal(8, record.getOnsetDate());
ps.setString(9, record.getTreatmentStrategyLkey());
ps.setString(10, record.getSourceOfInformationLkey());
ps.setString(11, record.getReactionDescription());
ps.setString(12, record.getNotes());
ps.setString(13, record.getCancellationReason());
ps.setString(14, record.getResolvedBy());
ps.setString(15, record.getCreatedBy());
ps.setString(16, record.getUpdatedBy());
ps.setString(17, record.getDeletedBy());
ps.setBigDecimal(18, record.getResolvedAt());
ps.setBigDecimal(19, record.getCreatedAt());
ps.setBigDecimal(20, record.getUpdatedAt());
ps.setBigDecimal(21, record.getDeletedAt());
ps.setBoolean(22, record.getIsValid());
ps.setString(23, record.getSeverityLkey());
ps.setString(24, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApVisitAllergies record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_visit_allergies set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApVisitAllergies> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_visit_allergies where "+ where);) {
List<ApVisitAllergies> list = new ArrayList<ApVisitAllergies>();
while(rs.next()){
ApVisitAllergies record = new ApVisitAllergies();
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setVisitKey(rs.getString("visit_key"));
record.setStatusLkey(rs.getString("status_lkey"));
record.setAllergyTypeLkey(rs.getString("allergy_type_lkey"));
record.setAllergenKey(rs.getString("allergen_key"));
record.setOnsetLkey(rs.getString("onset_lkey"));
record.setOnsetDate(rs.getBigDecimal("onset_date"));
record.setTreatmentStrategyLkey(rs.getString("treatment_strategy_lkey"));
record.setSourceOfInformationLkey(rs.getString("source_of_information_lkey"));
record.setReactionDescription(rs.getString("reaction_description"));
record.setNotes(rs.getString("notes"));
record.setCancellationReason(rs.getString("cancellation_reason"));
record.setResolvedBy(rs.getString("resolved_by"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setResolvedAt(rs.getBigDecimal("resolved_at"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setSeverityLkey(rs.getString("severity_lkey"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApVisitAllergies record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_visit_allergies values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getVisitKey());
ps.setString(4, record.getStatusLkey());
ps.setString(5, record.getAllergyTypeLkey());
ps.setString(6, record.getAllergenKey());
ps.setString(7, record.getOnsetLkey());
ps.setBigDecimal(8, record.getOnsetDate());
ps.setString(9, record.getTreatmentStrategyLkey());
ps.setString(10, record.getSourceOfInformationLkey());
ps.setString(11, record.getReactionDescription());
ps.setString(12, record.getNotes());
ps.setString(13, record.getCancellationReason());
ps.setString(14, record.getResolvedBy());
ps.setString(15, record.getCreatedBy());
ps.setString(16, record.getUpdatedBy());
ps.setString(17, record.getDeletedBy());
ps.setBigDecimal(18, record.getResolvedAt());
ps.setBigDecimal(19, record.getCreatedAt());
ps.setBigDecimal(20, record.getUpdatedAt());
ps.setBigDecimal(21, record.getDeletedAt());
ps.setBoolean(22, record.getIsValid());
ps.setString(23, record.getSeverityLkey());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApVisitAllergiesEntity entity, String lang) {
        Class<?> myClass = ApVisitAllergiesEntity.class;
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
public void translateObject(ApVisitAllergiesEntity entity, String lang) {
        ApVisitAllergiesEntity translated = (ApVisitAllergiesEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}