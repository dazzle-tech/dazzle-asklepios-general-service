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
import com.asklepios.backend_service.model.generated.pojo.ApPatientDiagnose;
import com.asklepios.backend_service.model.generated.entity.ApPatientDiagnoseEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApPatientDiagnoseDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApPatientDiagnose getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_patient_diagnose where key = '"+key+"'");) {
ApPatientDiagnose record = new ApPatientDiagnose();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setVisitKey(rs.getString("visit_key"));
record.setDiagnoseCode(rs.getString("diagnose_code"));
record.setDescription(rs.getString("description"));
record.setDiagnoseCodingLkey(rs.getString("diagnose_coding_lkey"));
record.setDateDiagnosed(rs.getDate("date_diagnosed"));
record.setOnsetDate(rs.getDate("onset_date"));
record.setDiagnoseStatusLkey(rs.getString("diagnose_status_lkey"));
record.setDiagnoseTypeLkey(rs.getString("diagnose_type_lkey"));
record.setDiagnoseSiteLkey(rs.getString("diagnose_site_lkey"));
record.setProviderTypeLkey(rs.getString("provider_type_lkey"));
record.setProviderLkey(rs.getString("provider_lkey"));
record.setProviderUserName(rs.getString("provider_user_name"));
record.setProviderRoleLkey(rs.getString("provider_role_lkey"));
record.setNotes(rs.getString("notes"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setIsMajor(rs.getBoolean("is_major"));
record.setIsSuspected(rs.getBoolean("is_suspected"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApPatientDiagnose record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_patient_diagnose set key = ?, patient_key = ?, visit_key = ?, diagnose_code = ?, description = ?, diagnose_coding_lkey = ?, date_diagnosed = ?, onset_date = ?, diagnose_status_lkey = ?, diagnose_type_lkey = ?, diagnose_site_lkey = ?, provider_type_lkey = ?, provider_lkey = ?, provider_user_name = ?, provider_role_lkey = ?, notes = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ?, is_major = ?, is_suspected = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getVisitKey());
ps.setString(4, record.getDiagnoseCode());
ps.setString(5, record.getDescription());
ps.setString(6, record.getDiagnoseCodingLkey());
if (record.getDateDiagnosed() != null) ps.setDate(7, new java.sql.Date(record.getDateDiagnosed().getTime()));
else ps.setDate(7, null); 
if (record.getOnsetDate() != null) ps.setDate(8, new java.sql.Date(record.getOnsetDate().getTime()));
else ps.setDate(8, null); 
ps.setString(9, record.getDiagnoseStatusLkey());
ps.setString(10, record.getDiagnoseTypeLkey());
ps.setString(11, record.getDiagnoseSiteLkey());
ps.setString(12, record.getProviderTypeLkey());
ps.setString(13, record.getProviderLkey());
ps.setString(14, record.getProviderUserName());
ps.setString(15, record.getProviderRoleLkey());
ps.setString(16, record.getNotes());
ps.setString(17, record.getCreatedBy());
ps.setString(18, record.getUpdatedBy());
ps.setString(19, record.getDeletedBy());
ps.setBigDecimal(20, record.getCreatedAt());
ps.setBigDecimal(21, record.getUpdatedAt());
ps.setBigDecimal(22, record.getDeletedAt());
ps.setBoolean(23, record.getIsValid());
ps.setBoolean(24, record.getIsMajor());
ps.setBoolean(25, record.getIsSuspected());
ps.setString(26, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApPatientDiagnose record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_patient_diagnose set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApPatientDiagnose> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_patient_diagnose where "+ where);) {
List<ApPatientDiagnose> list = new ArrayList<ApPatientDiagnose>();
while(rs.next()){
ApPatientDiagnose record = new ApPatientDiagnose();
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setVisitKey(rs.getString("visit_key"));
record.setDiagnoseCode(rs.getString("diagnose_code"));
record.setDescription(rs.getString("description"));
record.setDiagnoseCodingLkey(rs.getString("diagnose_coding_lkey"));
record.setDateDiagnosed(rs.getDate("date_diagnosed"));
record.setOnsetDate(rs.getDate("onset_date"));
record.setDiagnoseStatusLkey(rs.getString("diagnose_status_lkey"));
record.setDiagnoseTypeLkey(rs.getString("diagnose_type_lkey"));
record.setDiagnoseSiteLkey(rs.getString("diagnose_site_lkey"));
record.setProviderTypeLkey(rs.getString("provider_type_lkey"));
record.setProviderLkey(rs.getString("provider_lkey"));
record.setProviderUserName(rs.getString("provider_user_name"));
record.setProviderRoleLkey(rs.getString("provider_role_lkey"));
record.setNotes(rs.getString("notes"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setIsMajor(rs.getBoolean("is_major"));
record.setIsSuspected(rs.getBoolean("is_suspected"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApPatientDiagnose record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_patient_diagnose values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getVisitKey());
ps.setString(4, record.getDiagnoseCode());
ps.setString(5, record.getDescription());
ps.setString(6, record.getDiagnoseCodingLkey());
if (record.getDateDiagnosed() != null) ps.setDate(7, new java.sql.Date(record.getDateDiagnosed().getTime()));
else ps.setDate(7, null); 
if (record.getOnsetDate() != null) ps.setDate(8, new java.sql.Date(record.getOnsetDate().getTime()));
else ps.setDate(8, null); 
ps.setString(9, record.getDiagnoseStatusLkey());
ps.setString(10, record.getDiagnoseTypeLkey());
ps.setString(11, record.getDiagnoseSiteLkey());
ps.setString(12, record.getProviderTypeLkey());
ps.setString(13, record.getProviderLkey());
ps.setString(14, record.getProviderUserName());
ps.setString(15, record.getProviderRoleLkey());
ps.setString(16, record.getNotes());
ps.setString(17, record.getCreatedBy());
ps.setString(18, record.getUpdatedBy());
ps.setString(19, record.getDeletedBy());
ps.setBigDecimal(20, record.getCreatedAt());
ps.setBigDecimal(21, record.getUpdatedAt());
ps.setBigDecimal(22, record.getDeletedAt());
ps.setBoolean(23, record.getIsValid());
ps.setBoolean(24, record.getIsMajor());
ps.setBoolean(25, record.getIsSuspected());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApPatientDiagnoseEntity entity, String lang) {
        Class<?> myClass = ApPatientDiagnoseEntity.class;
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
public void translateObject(ApPatientDiagnoseEntity entity, String lang) {
        ApPatientDiagnoseEntity translated = (ApPatientDiagnoseEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}