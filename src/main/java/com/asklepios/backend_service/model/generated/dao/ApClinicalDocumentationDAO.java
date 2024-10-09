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
import com.asklepios.backend_service.model.generated.pojo.ApClinicalDocumentation;
import com.asklepios.backend_service.model.generated.entity.ApClinicalDocumentationEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApClinicalDocumentationDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApClinicalDocumentation getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_clinical_documentation where key = '"+key+"'");) {
ApClinicalDocumentation record = new ApClinicalDocumentation();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setDocTypeLkey(rs.getString("doc_type_lkey"));
record.setDocCategoryLkey(rs.getString("doc_category_lkey"));
record.setDocContent(rs.getString("doc_content"));
record.setUserKey(rs.getString("user_key"));
record.setUserRoleLkey(rs.getString("user_role_lkey"));
record.setFacilityKey(rs.getString("facility_key"));
record.setDocStatusLkey(rs.getString("doc_status_lkey"));
record.setDocFormTypeKey(rs.getString("doc_form_type_key"));
record.setDocFormKey(rs.getString("doc_form_key"));
record.setCreatedDatetime(rs.getDate("created_datetime"));
record.setApprovedDatetime(rs.getDate("approved_datetime"));
record.setApprovedByUserKey(rs.getString("approved_by_user_key"));
record.setApprovedByUserRoleLkey(rs.getString("approved_by_user_role_lkey"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApClinicalDocumentation record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_clinical_documentation set key = ?, patient_key = ?, encounter_key = ?, doc_type_lkey = ?, doc_category_lkey = ?, doc_content = ?, user_key = ?, user_role_lkey = ?, facility_key = ?, doc_status_lkey = ?, doc_form_type_key = ?, doc_form_key = ?, created_datetime = ?, approved_datetime = ?, approved_by_user_key = ?, approved_by_user_role_lkey = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getEncounterKey());
ps.setString(4, record.getDocTypeLkey());
ps.setString(5, record.getDocCategoryLkey());
ps.setString(6, record.getDocContent());
ps.setString(7, record.getUserKey());
ps.setString(8, record.getUserRoleLkey());
ps.setString(9, record.getFacilityKey());
ps.setString(10, record.getDocStatusLkey());
ps.setString(11, record.getDocFormTypeKey());
ps.setString(12, record.getDocFormKey());
if (record.getCreatedDatetime() != null) ps.setDate(13, new java.sql.Date(record.getCreatedDatetime().getTime()));
else ps.setDate(13, null); 
if (record.getApprovedDatetime() != null) ps.setDate(14, new java.sql.Date(record.getApprovedDatetime().getTime()));
else ps.setDate(14, null); 
ps.setString(15, record.getApprovedByUserKey());
ps.setString(16, record.getApprovedByUserRoleLkey());
ps.setString(17, record.getCreatedBy());
ps.setString(18, record.getUpdatedBy());
ps.setString(19, record.getDeletedBy());
ps.setBigDecimal(20, record.getCreatedAt());
ps.setBigDecimal(21, record.getUpdatedAt());
ps.setBigDecimal(22, record.getDeletedAt());
ps.setBoolean(23, record.getIsValid());
ps.setString(24, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApClinicalDocumentation record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_clinical_documentation set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApClinicalDocumentation> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_clinical_documentation where "+ where);) {
List<ApClinicalDocumentation> list = new ArrayList<ApClinicalDocumentation>();
while(rs.next()){
ApClinicalDocumentation record = new ApClinicalDocumentation();
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setDocTypeLkey(rs.getString("doc_type_lkey"));
record.setDocCategoryLkey(rs.getString("doc_category_lkey"));
record.setDocContent(rs.getString("doc_content"));
record.setUserKey(rs.getString("user_key"));
record.setUserRoleLkey(rs.getString("user_role_lkey"));
record.setFacilityKey(rs.getString("facility_key"));
record.setDocStatusLkey(rs.getString("doc_status_lkey"));
record.setDocFormTypeKey(rs.getString("doc_form_type_key"));
record.setDocFormKey(rs.getString("doc_form_key"));
record.setCreatedDatetime(rs.getDate("created_datetime"));
record.setApprovedDatetime(rs.getDate("approved_datetime"));
record.setApprovedByUserKey(rs.getString("approved_by_user_key"));
record.setApprovedByUserRoleLkey(rs.getString("approved_by_user_role_lkey"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApClinicalDocumentation record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_clinical_documentation values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getEncounterKey());
ps.setString(4, record.getDocTypeLkey());
ps.setString(5, record.getDocCategoryLkey());
ps.setString(6, record.getDocContent());
ps.setString(7, record.getUserKey());
ps.setString(8, record.getUserRoleLkey());
ps.setString(9, record.getFacilityKey());
ps.setString(10, record.getDocStatusLkey());
ps.setString(11, record.getDocFormTypeKey());
ps.setString(12, record.getDocFormKey());
if (record.getCreatedDatetime() != null) ps.setDate(13, new java.sql.Date(record.getCreatedDatetime().getTime()));
else ps.setDate(13, null); 
if (record.getApprovedDatetime() != null) ps.setDate(14, new java.sql.Date(record.getApprovedDatetime().getTime()));
else ps.setDate(14, null); 
ps.setString(15, record.getApprovedByUserKey());
ps.setString(16, record.getApprovedByUserRoleLkey());
ps.setString(17, record.getCreatedBy());
ps.setString(18, record.getUpdatedBy());
ps.setString(19, record.getDeletedBy());
ps.setBigDecimal(20, record.getCreatedAt());
ps.setBigDecimal(21, record.getUpdatedAt());
ps.setBigDecimal(22, record.getDeletedAt());
ps.setBoolean(23, record.getIsValid());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApClinicalDocumentationEntity entity, String lang) {
        Class<?> myClass = ApClinicalDocumentationEntity.class;
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
public void translateObject(ApClinicalDocumentationEntity entity, String lang) {
        ApClinicalDocumentationEntity translated = (ApClinicalDocumentationEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}