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
import com.asklepios.backend_service.model.generated.pojo.ApOperationRecoveryVitalsMonitoring;
import com.asklepios.backend_service.model.generated.entity.ApOperationRecoveryVitalsMonitoringEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApOperationRecoveryVitalsMonitoringDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApOperationRecoveryVitalsMonitoring getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_operation_recovery_vitals_monitoring where key = '"+key+"'");) {
ApOperationRecoveryVitalsMonitoring record = new ApOperationRecoveryVitalsMonitoring();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setOperationRequestKey(rs.getString("operation_request_key"));
record.setRecordedTime(rs.getBigDecimal("recorded_time"));
record.setBloodPressureSystolic(rs.getBigDecimal("blood_pressure_systolic"));
record.setBloodPressureDiastolic(rs.getBigDecimal("blood_pressure_diastolic"));
record.setHeartRate(rs.getBigDecimal("heart_rate"));
record.setTemperature(rs.getBigDecimal("temperature"));
record.setOxygenSaturation(rs.getBigDecimal("oxygen_saturation"));
record.setCreatedBy(rs.getString("created_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsvalid(rs.getBoolean("isvalid"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApOperationRecoveryVitalsMonitoring record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_operation_recovery_vitals_monitoring set key = ?, operation_request_key = ?, recorded_time = ?, blood_pressure_systolic = ?, blood_pressure_diastolic = ?, heart_rate = ?, temperature = ?, oxygen_saturation = ?, created_by = ?, created_at = ?, updated_by = ?, updated_at = ?, deleted_by = ?, deleted_at = ?, isvalid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getOperationRequestKey());
ps.setBigDecimal(3, record.getRecordedTime());
ps.setBigDecimal(4, record.getBloodPressureSystolic());
ps.setBigDecimal(5, record.getBloodPressureDiastolic());
ps.setBigDecimal(6, record.getHeartRate());
ps.setBigDecimal(7, record.getTemperature());
ps.setBigDecimal(8, record.getOxygenSaturation());
ps.setString(9, record.getCreatedBy());
ps.setBigDecimal(10, record.getCreatedAt());
ps.setString(11, record.getUpdatedBy());
ps.setBigDecimal(12, record.getUpdatedAt());
ps.setString(13, record.getDeletedBy());
ps.setBigDecimal(14, record.getDeletedAt());
ps.setBoolean(15, record.getIsvalid());
ps.setString(16, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApOperationRecoveryVitalsMonitoring record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_operation_recovery_vitals_monitoring set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApOperationRecoveryVitalsMonitoring> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_operation_recovery_vitals_monitoring where "+ where);) {
List<ApOperationRecoveryVitalsMonitoring> list = new ArrayList<ApOperationRecoveryVitalsMonitoring>();
while(rs.next()){
ApOperationRecoveryVitalsMonitoring record = new ApOperationRecoveryVitalsMonitoring();
record.setKey(rs.getString("key"));
record.setOperationRequestKey(rs.getString("operation_request_key"));
record.setRecordedTime(rs.getBigDecimal("recorded_time"));
record.setBloodPressureSystolic(rs.getBigDecimal("blood_pressure_systolic"));
record.setBloodPressureDiastolic(rs.getBigDecimal("blood_pressure_diastolic"));
record.setHeartRate(rs.getBigDecimal("heart_rate"));
record.setTemperature(rs.getBigDecimal("temperature"));
record.setOxygenSaturation(rs.getBigDecimal("oxygen_saturation"));
record.setCreatedBy(rs.getString("created_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsvalid(rs.getBoolean("isvalid"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApOperationRecoveryVitalsMonitoring record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_operation_recovery_vitals_monitoring values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getOperationRequestKey());
ps.setBigDecimal(3, record.getRecordedTime());
ps.setBigDecimal(4, record.getBloodPressureSystolic());
ps.setBigDecimal(5, record.getBloodPressureDiastolic());
ps.setBigDecimal(6, record.getHeartRate());
ps.setBigDecimal(7, record.getTemperature());
ps.setBigDecimal(8, record.getOxygenSaturation());
ps.setString(9, record.getCreatedBy());
ps.setBigDecimal(10, record.getCreatedAt());
ps.setString(11, record.getUpdatedBy());
ps.setBigDecimal(12, record.getUpdatedAt());
ps.setString(13, record.getDeletedBy());
ps.setBigDecimal(14, record.getDeletedAt());
ps.setBoolean(15, record.getIsvalid());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApOperationRecoveryVitalsMonitoringEntity entity, String lang) {
        Class<?> myClass = ApOperationRecoveryVitalsMonitoringEntity.class;
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
public void translateObject(ApOperationRecoveryVitalsMonitoringEntity entity, String lang) {
        ApOperationRecoveryVitalsMonitoringEntity translated = (ApOperationRecoveryVitalsMonitoringEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}