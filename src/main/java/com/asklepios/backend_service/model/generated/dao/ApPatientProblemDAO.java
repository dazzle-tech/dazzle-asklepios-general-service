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
import com.asklepios.backend_service.model.generated.pojo.ApPatientProblem;
import com.asklepios.backend_service.model.generated.entity.ApPatientProblemEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApPatientProblemDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApPatientProblem getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_patient_problem where key = '"+key+"'");) {
ApPatientProblem record = new ApPatientProblem();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setProblemCode(rs.getString("problem_code"));
record.setDescription(rs.getString("description"));
record.setProblemCodingLkey(rs.getString("problem_coding_lkey"));
record.setDateDiagnosed(rs.getDate("date_diagnosed"));
record.setProblemStatusLkey(rs.getString("problem_status_lkey"));
record.setSeverityLkey(rs.getString("severity_lkey"));
record.setOnSetDate(rs.getDate("on_set_date"));
record.setProviderTypeLkey(rs.getString("provider_type_lkey"));
record.setProviderLkey(rs.getString("provider_lkey"));
record.setProviderUserName(rs.getString("provider_user_name"));
record.setProviderRoleLkey(rs.getString("provider_role_lkey"));
record.setResolvedDate(rs.getDate("resolved_date"));
record.setDateAdded(rs.getDate("date_added"));
record.setNotes(rs.getString("notes"));
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
public void updateRecord(ApPatientProblem record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_patient_problem set key = ?, patient_key = ?, problem_code = ?, description = ?, problem_coding_lkey = ?, date_diagnosed = ?, problem_status_lkey = ?, severity_lkey = ?, on_set_date = ?, provider_type_lkey = ?, provider_lkey = ?, provider_user_name = ?, provider_role_lkey = ?, resolved_date = ?, date_added = ?, notes = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getProblemCode());
ps.setString(4, record.getDescription());
ps.setString(5, record.getProblemCodingLkey());
if (record.getDateDiagnosed() != null) ps.setDate(6, new java.sql.Date(record.getDateDiagnosed().getTime()));
else ps.setDate(6, null); 
ps.setString(7, record.getProblemStatusLkey());
ps.setString(8, record.getSeverityLkey());
if (record.getOnSetDate() != null) ps.setDate(9, new java.sql.Date(record.getOnSetDate().getTime()));
else ps.setDate(9, null); 
ps.setString(10, record.getProviderTypeLkey());
ps.setString(11, record.getProviderLkey());
ps.setString(12, record.getProviderUserName());
ps.setString(13, record.getProviderRoleLkey());
if (record.getResolvedDate() != null) ps.setDate(14, new java.sql.Date(record.getResolvedDate().getTime()));
else ps.setDate(14, null); 
if (record.getDateAdded() != null) ps.setDate(15, new java.sql.Date(record.getDateAdded().getTime()));
else ps.setDate(15, null); 
ps.setString(16, record.getNotes());
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
public void deleteRecord(ApPatientProblem record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_patient_problem set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApPatientProblem> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_patient_problem where "+ where);) {
List<ApPatientProblem> list = new ArrayList<ApPatientProblem>();
while(rs.next()){
ApPatientProblem record = new ApPatientProblem();
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setProblemCode(rs.getString("problem_code"));
record.setDescription(rs.getString("description"));
record.setProblemCodingLkey(rs.getString("problem_coding_lkey"));
record.setDateDiagnosed(rs.getDate("date_diagnosed"));
record.setProblemStatusLkey(rs.getString("problem_status_lkey"));
record.setSeverityLkey(rs.getString("severity_lkey"));
record.setOnSetDate(rs.getDate("on_set_date"));
record.setProviderTypeLkey(rs.getString("provider_type_lkey"));
record.setProviderLkey(rs.getString("provider_lkey"));
record.setProviderUserName(rs.getString("provider_user_name"));
record.setProviderRoleLkey(rs.getString("provider_role_lkey"));
record.setResolvedDate(rs.getDate("resolved_date"));
record.setDateAdded(rs.getDate("date_added"));
record.setNotes(rs.getString("notes"));
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
public String saveRecord(ApPatientProblem record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_patient_problem values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getProblemCode());
ps.setString(4, record.getDescription());
ps.setString(5, record.getProblemCodingLkey());
if (record.getDateDiagnosed() != null) ps.setDate(6, new java.sql.Date(record.getDateDiagnosed().getTime()));
else ps.setDate(6, null); 
ps.setString(7, record.getProblemStatusLkey());
ps.setString(8, record.getSeverityLkey());
if (record.getOnSetDate() != null) ps.setDate(9, new java.sql.Date(record.getOnSetDate().getTime()));
else ps.setDate(9, null); 
ps.setString(10, record.getProviderTypeLkey());
ps.setString(11, record.getProviderLkey());
ps.setString(12, record.getProviderUserName());
ps.setString(13, record.getProviderRoleLkey());
if (record.getResolvedDate() != null) ps.setDate(14, new java.sql.Date(record.getResolvedDate().getTime()));
else ps.setDate(14, null); 
if (record.getDateAdded() != null) ps.setDate(15, new java.sql.Date(record.getDateAdded().getTime()));
else ps.setDate(15, null); 
ps.setString(16, record.getNotes());
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
public void populateLovFields(ApPatientProblemEntity entity, String lang) {
        Class<?> myClass = ApPatientProblemEntity.class;
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
public void translateObject(ApPatientProblemEntity entity, String lang) {
        ApPatientProblemEntity translated = (ApPatientProblemEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}