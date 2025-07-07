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
import com.asklepios.backend_service.model.generated.pojo.ApOperationIntraoperativeMonitoring;
import com.asklepios.backend_service.model.generated.entity.ApOperationIntraoperativeMonitoringEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApOperationIntraoperativeMonitoringDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApOperationIntraoperativeMonitoring getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_operation_intraoperative_monitoring where key = '"+key+"'");) {
ApOperationIntraoperativeMonitoring record = new ApOperationIntraoperativeMonitoring();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setOperationRequestKey(rs.getString("operation_request_key"));
record.setPeriod(rs.getBigDecimal("period"));
record.setSpo2(rs.getBigDecimal("spo2"));
record.setBpSystolic(rs.getBigDecimal("bp_systolic"));
record.setBpDiastolic(rs.getBigDecimal("bp_diastolic"));
record.setRespiratoryRate(rs.getBigDecimal("respiratory_rate"));
record.setTemperature(rs.getBigDecimal("temperature"));
record.setEtco2(rs.getBigDecimal("etco2"));
record.setFluidsGiven(rs.getBigDecimal("fluids_given"));
record.setBloodGiven(rs.getBigDecimal("blood_given"));
record.setUrineOutput(rs.getBigDecimal("urine_output"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setPatientKey(rs.getString("patient_key"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApOperationIntraoperativeMonitoring record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_operation_intraoperative_monitoring set key = ?, operation_request_key = ?, period = ?, spo2 = ?, bp_systolic = ?, bp_diastolic = ?, respiratory_rate = ?, temperature = ?, etco2 = ?, fluids_given = ?, blood_given = ?, urine_output = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, encounter_key = ?, patient_key = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getOperationRequestKey());
ps.setBigDecimal(3, record.getPeriod());
ps.setBigDecimal(4, record.getSpo2());
ps.setBigDecimal(5, record.getBpSystolic());
ps.setBigDecimal(6, record.getBpDiastolic());
ps.setBigDecimal(7, record.getRespiratoryRate());
ps.setBigDecimal(8, record.getTemperature());
ps.setBigDecimal(9, record.getEtco2());
ps.setBigDecimal(10, record.getFluidsGiven());
ps.setBigDecimal(11, record.getBloodGiven());
ps.setBigDecimal(12, record.getUrineOutput());
ps.setString(13, record.getCreatedBy());
ps.setString(14, record.getUpdatedBy());
ps.setString(15, record.getDeletedBy());
ps.setBigDecimal(16, record.getCreatedAt());
ps.setBigDecimal(17, record.getUpdatedAt());
ps.setBigDecimal(18, record.getDeletedAt());
ps.setString(19, record.getEncounterKey());
ps.setString(20, record.getPatientKey());
ps.setString(21, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApOperationIntraoperativeMonitoring record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_operation_intraoperative_monitoring set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApOperationIntraoperativeMonitoring> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_operation_intraoperative_monitoring where "+ where);) {
List<ApOperationIntraoperativeMonitoring> list = new ArrayList<ApOperationIntraoperativeMonitoring>();
while(rs.next()){
ApOperationIntraoperativeMonitoring record = new ApOperationIntraoperativeMonitoring();
record.setKey(rs.getString("key"));
record.setOperationRequestKey(rs.getString("operation_request_key"));
record.setPeriod(rs.getBigDecimal("period"));
record.setSpo2(rs.getBigDecimal("spo2"));
record.setBpSystolic(rs.getBigDecimal("bp_systolic"));
record.setBpDiastolic(rs.getBigDecimal("bp_diastolic"));
record.setRespiratoryRate(rs.getBigDecimal("respiratory_rate"));
record.setTemperature(rs.getBigDecimal("temperature"));
record.setEtco2(rs.getBigDecimal("etco2"));
record.setFluidsGiven(rs.getBigDecimal("fluids_given"));
record.setBloodGiven(rs.getBigDecimal("blood_given"));
record.setUrineOutput(rs.getBigDecimal("urine_output"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setPatientKey(rs.getString("patient_key"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApOperationIntraoperativeMonitoring record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_operation_intraoperative_monitoring values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getOperationRequestKey());
ps.setBigDecimal(3, record.getPeriod());
ps.setBigDecimal(4, record.getSpo2());
ps.setBigDecimal(5, record.getBpSystolic());
ps.setBigDecimal(6, record.getBpDiastolic());
ps.setBigDecimal(7, record.getRespiratoryRate());
ps.setBigDecimal(8, record.getTemperature());
ps.setBigDecimal(9, record.getEtco2());
ps.setBigDecimal(10, record.getFluidsGiven());
ps.setBigDecimal(11, record.getBloodGiven());
ps.setBigDecimal(12, record.getUrineOutput());
ps.setString(13, record.getCreatedBy());
ps.setString(14, record.getUpdatedBy());
ps.setString(15, record.getDeletedBy());
ps.setBigDecimal(16, record.getCreatedAt());
ps.setBigDecimal(17, record.getUpdatedAt());
ps.setBigDecimal(18, record.getDeletedAt());
ps.setString(19, record.getEncounterKey());
ps.setString(20, record.getPatientKey());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApOperationIntraoperativeMonitoringEntity entity, String lang) {
        Class<?> myClass = ApOperationIntraoperativeMonitoringEntity.class;
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
public void translateObject(ApOperationIntraoperativeMonitoringEntity entity, String lang) {
        ApOperationIntraoperativeMonitoringEntity translated = (ApOperationIntraoperativeMonitoringEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}