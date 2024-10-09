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
import com.asklepios.backend_service.model.generated.pojo.ApPatientAlerts;
import com.asklepios.backend_service.model.generated.entity.ApPatientAlertsEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApPatientAlertsDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApPatientAlerts getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_patient_alerts where key = '"+key+"'");) {
ApPatientAlerts record = new ApPatientAlerts();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setAlertTypeLkey(rs.getString("alert_type_lkey"));
record.setAlertSourceLkey(rs.getString("alert_source_lkey"));
record.setAlertDescription(rs.getString("alert_description"));
record.setAlertSeverityLkey(rs.getString("alert_severity_lkey"));
record.setIsResolved(rs.getBoolean("is_resolved"));
record.setAlertDate(rs.getDate("alert_date"));
record.setDateResolved(rs.getDate("date_resolved"));
record.setNotes(rs.getString("notes"));
record.setSourceOfInfoLkey(rs.getString("source_of_info_lkey"));
record.setSourceKey(rs.getString("source_key"));
record.setLifeThreating(rs.getBoolean("life_threating"));
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
public void updateRecord(ApPatientAlerts record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_patient_alerts set key = ?, patient_key = ?, alert_type_lkey = ?, alert_source_lkey = ?, alert_description = ?, alert_severity_lkey = ?, is_resolved = ?, alert_date = ?, date_resolved = ?, notes = ?, source_of_info_lkey = ?, source_key = ?, life_threating = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getAlertTypeLkey());
ps.setString(4, record.getAlertSourceLkey());
ps.setString(5, record.getAlertDescription());
ps.setString(6, record.getAlertSeverityLkey());
ps.setBoolean(7, record.getIsResolved());
if (record.getAlertDate() != null) ps.setDate(8, new java.sql.Date(record.getAlertDate().getTime()));
else ps.setDate(8, null); 
if (record.getDateResolved() != null) ps.setDate(9, new java.sql.Date(record.getDateResolved().getTime()));
else ps.setDate(9, null); 
ps.setString(10, record.getNotes());
ps.setString(11, record.getSourceOfInfoLkey());
ps.setString(12, record.getSourceKey());
ps.setBoolean(13, record.getLifeThreating());
ps.setString(14, record.getCreatedBy());
ps.setString(15, record.getUpdatedBy());
ps.setString(16, record.getDeletedBy());
ps.setBigDecimal(17, record.getCreatedAt());
ps.setBigDecimal(18, record.getUpdatedAt());
ps.setBigDecimal(19, record.getDeletedAt());
ps.setBoolean(20, record.getIsValid());
ps.setString(21, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApPatientAlerts record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_patient_alerts set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApPatientAlerts> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_patient_alerts where "+ where);) {
List<ApPatientAlerts> list = new ArrayList<ApPatientAlerts>();
while(rs.next()){
ApPatientAlerts record = new ApPatientAlerts();
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setAlertTypeLkey(rs.getString("alert_type_lkey"));
record.setAlertSourceLkey(rs.getString("alert_source_lkey"));
record.setAlertDescription(rs.getString("alert_description"));
record.setAlertSeverityLkey(rs.getString("alert_severity_lkey"));
record.setIsResolved(rs.getBoolean("is_resolved"));
record.setAlertDate(rs.getDate("alert_date"));
record.setDateResolved(rs.getDate("date_resolved"));
record.setNotes(rs.getString("notes"));
record.setSourceOfInfoLkey(rs.getString("source_of_info_lkey"));
record.setSourceKey(rs.getString("source_key"));
record.setLifeThreating(rs.getBoolean("life_threating"));
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
public String saveRecord(ApPatientAlerts record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_patient_alerts values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getAlertTypeLkey());
ps.setString(4, record.getAlertSourceLkey());
ps.setString(5, record.getAlertDescription());
ps.setString(6, record.getAlertSeverityLkey());
ps.setBoolean(7, record.getIsResolved());
if (record.getAlertDate() != null) ps.setDate(8, new java.sql.Date(record.getAlertDate().getTime()));
else ps.setDate(8, null); 
if (record.getDateResolved() != null) ps.setDate(9, new java.sql.Date(record.getDateResolved().getTime()));
else ps.setDate(9, null); 
ps.setString(10, record.getNotes());
ps.setString(11, record.getSourceOfInfoLkey());
ps.setString(12, record.getSourceKey());
ps.setBoolean(13, record.getLifeThreating());
ps.setString(14, record.getCreatedBy());
ps.setString(15, record.getUpdatedBy());
ps.setString(16, record.getDeletedBy());
ps.setBigDecimal(17, record.getCreatedAt());
ps.setBigDecimal(18, record.getUpdatedAt());
ps.setBigDecimal(19, record.getDeletedAt());
ps.setBoolean(20, record.getIsValid());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApPatientAlertsEntity entity, String lang) {
        Class<?> myClass = ApPatientAlertsEntity.class;
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
public void translateObject(ApPatientAlertsEntity entity, String lang) {
        ApPatientAlertsEntity translated = (ApPatientAlertsEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}