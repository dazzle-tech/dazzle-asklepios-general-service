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
import com.asklepios.backend_service.model.generated.pojo.ApPatientAdministrativeWarnings;
import com.asklepios.backend_service.model.generated.entity.ApPatientAdministrativeWarningsEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApPatientAdministrativeWarningsDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApPatientAdministrativeWarnings getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_patient_administrative_warnings where key = '"+key+"'");) {
ApPatientAdministrativeWarnings record = new ApPatientAdministrativeWarnings();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setWarningTypeLkey(rs.getString("warning_type_lkey"));
record.setDescription(rs.getString("description"));
record.setResolutionStatusLkey(rs.getString("resolution_status_lkey"));
record.setDateResolved(rs.getDate("date_resolved"));
record.setResolvedBy(rs.getString("resolved_by"));
record.setResolutionUndoDate(rs.getDate("resolution_undo_date"));
record.setResolvedUndoBy(rs.getString("resolved_undo_by"));
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
public void updateRecord(ApPatientAdministrativeWarnings record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_patient_administrative_warnings set key = ?, patient_key = ?, warning_type_lkey = ?, description = ?, resolution_status_lkey = ?, date_resolved = ?, resolved_by = ?, resolution_undo_date = ?, resolved_undo_by = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getWarningTypeLkey());
ps.setString(4, record.getDescription());
ps.setString(5, record.getResolutionStatusLkey());
if (record.getDateResolved() != null) ps.setDate(6, new java.sql.Date(record.getDateResolved().getTime()));
else ps.setDate(6, null); 
ps.setString(7, record.getResolvedBy());
if (record.getResolutionUndoDate() != null) ps.setDate(8, new java.sql.Date(record.getResolutionUndoDate().getTime()));
else ps.setDate(8, null); 
ps.setString(9, record.getResolvedUndoBy());
ps.setString(10, record.getCreatedBy());
ps.setString(11, record.getUpdatedBy());
ps.setString(12, record.getDeletedBy());
ps.setBigDecimal(13, record.getCreatedAt());
ps.setBigDecimal(14, record.getUpdatedAt());
ps.setBigDecimal(15, record.getDeletedAt());
ps.setBoolean(16, record.getIsValid());
ps.setString(17, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApPatientAdministrativeWarnings record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_patient_administrative_warnings set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApPatientAdministrativeWarnings> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_patient_administrative_warnings where "+ where);) {
List<ApPatientAdministrativeWarnings> list = new ArrayList<ApPatientAdministrativeWarnings>();
while(rs.next()){
ApPatientAdministrativeWarnings record = new ApPatientAdministrativeWarnings();
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setWarningTypeLkey(rs.getString("warning_type_lkey"));
record.setDescription(rs.getString("description"));
record.setResolutionStatusLkey(rs.getString("resolution_status_lkey"));
record.setDateResolved(rs.getDate("date_resolved"));
record.setResolvedBy(rs.getString("resolved_by"));
record.setResolutionUndoDate(rs.getDate("resolution_undo_date"));
record.setResolvedUndoBy(rs.getString("resolved_undo_by"));
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
public String saveRecord(ApPatientAdministrativeWarnings record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_patient_administrative_warnings values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getWarningTypeLkey());
ps.setString(4, record.getDescription());
ps.setString(5, record.getResolutionStatusLkey());
if (record.getDateResolved() != null) ps.setDate(6, new java.sql.Date(record.getDateResolved().getTime()));
else ps.setDate(6, null); 
ps.setString(7, record.getResolvedBy());
if (record.getResolutionUndoDate() != null) ps.setDate(8, new java.sql.Date(record.getResolutionUndoDate().getTime()));
else ps.setDate(8, null); 
ps.setString(9, record.getResolvedUndoBy());
ps.setString(10, record.getCreatedBy());
ps.setString(11, record.getUpdatedBy());
ps.setString(12, record.getDeletedBy());
ps.setBigDecimal(13, record.getCreatedAt());
ps.setBigDecimal(14, record.getUpdatedAt());
ps.setBigDecimal(15, record.getDeletedAt());
ps.setBoolean(16, record.getIsValid());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApPatientAdministrativeWarningsEntity entity, String lang) {
        Class<?> myClass = ApPatientAdministrativeWarningsEntity.class;
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
public void translateObject(ApPatientAdministrativeWarningsEntity entity, String lang) {
        ApPatientAdministrativeWarningsEntity translated = (ApPatientAdministrativeWarningsEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}