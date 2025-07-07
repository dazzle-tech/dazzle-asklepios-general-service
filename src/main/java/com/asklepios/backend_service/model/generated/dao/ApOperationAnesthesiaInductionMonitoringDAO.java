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
import com.asklepios.backend_service.model.generated.pojo.ApOperationAnesthesiaInductionMonitoring;
import com.asklepios.backend_service.model.generated.entity.ApOperationAnesthesiaInductionMonitoringEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApOperationAnesthesiaInductionMonitoringDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApOperationAnesthesiaInductionMonitoring getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_operation_anesthesia_induction_monitoring where key = '"+key+"'");) {
ApOperationAnesthesiaInductionMonitoring record = new ApOperationAnesthesiaInductionMonitoring();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setOperationRequestKey(rs.getString("operation_request_key"));
record.setWeight(rs.getBigDecimal("weight"));
record.setFastingDuration(rs.getBigDecimal("fasting_duration"));
record.setIvLineEstablished(rs.getBoolean("iv_line_established"));
record.setMonitorsConnected(rs.getString("monitors_connected"));
record.setInductionStartTime(rs.getDate("induction_start_time"));
record.setIntubationDone(rs.getBoolean("intubation_done"));
record.setTubeSize(rs.getString("tube_size"));
record.setTubeType(rs.getString("tube_type"));
record.setSecuredBy(rs.getString("secured_by"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setPatientKey(rs.getString("patient_key"));
record.setAdverseEventsLkey(rs.getString("adverse_events_lkey"));
record.setAdverseEventsNote(rs.getString("adverse_events_note"));
record.setActionsTaken(rs.getString("actions_taken"));
record.setSurgeonNotified(rs.getBoolean("surgeon_notified"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApOperationAnesthesiaInductionMonitoring record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_operation_anesthesia_induction_monitoring set key = ?, operation_request_key = ?, weight = ?, fasting_duration = ?, iv_line_established = ?, monitors_connected = ?, induction_start_time = ?, intubation_done = ?, tube_size = ?, tube_type = ?, secured_by = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, encounter_key = ?, patient_key = ?, adverse_events_lkey = ?, adverse_events_note = ?, actions_taken = ?, surgeon_notified = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getOperationRequestKey());
ps.setBigDecimal(3, record.getWeight());
ps.setBigDecimal(4, record.getFastingDuration());
ps.setBoolean(5, record.getIvLineEstablished());
ps.setString(6, record.getMonitorsConnected());
if (record.getInductionStartTime() != null) ps.setDate(7, new java.sql.Date(record.getInductionStartTime().getTime()));
else ps.setDate(7, null); 
ps.setBoolean(8, record.getIntubationDone());
ps.setString(9, record.getTubeSize());
ps.setString(10, record.getTubeType());
ps.setString(11, record.getSecuredBy());
ps.setString(12, record.getCreatedBy());
ps.setString(13, record.getUpdatedBy());
ps.setString(14, record.getDeletedBy());
ps.setBigDecimal(15, record.getCreatedAt());
ps.setBigDecimal(16, record.getUpdatedAt());
ps.setBigDecimal(17, record.getDeletedAt());
ps.setString(18, record.getEncounterKey());
ps.setString(19, record.getPatientKey());
ps.setString(20, record.getAdverseEventsLkey());
ps.setString(21, record.getAdverseEventsNote());
ps.setString(22, record.getActionsTaken());
ps.setBoolean(23, record.getSurgeonNotified());
ps.setString(24, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApOperationAnesthesiaInductionMonitoring record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_operation_anesthesia_induction_monitoring set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApOperationAnesthesiaInductionMonitoring> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_operation_anesthesia_induction_monitoring where "+ where);) {
List<ApOperationAnesthesiaInductionMonitoring> list = new ArrayList<ApOperationAnesthesiaInductionMonitoring>();
while(rs.next()){
ApOperationAnesthesiaInductionMonitoring record = new ApOperationAnesthesiaInductionMonitoring();
record.setKey(rs.getString("key"));
record.setOperationRequestKey(rs.getString("operation_request_key"));
record.setWeight(rs.getBigDecimal("weight"));
record.setFastingDuration(rs.getBigDecimal("fasting_duration"));
record.setIvLineEstablished(rs.getBoolean("iv_line_established"));
record.setMonitorsConnected(rs.getString("monitors_connected"));
record.setInductionStartTime(rs.getDate("induction_start_time"));
record.setIntubationDone(rs.getBoolean("intubation_done"));
record.setTubeSize(rs.getString("tube_size"));
record.setTubeType(rs.getString("tube_type"));
record.setSecuredBy(rs.getString("secured_by"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setPatientKey(rs.getString("patient_key"));
record.setAdverseEventsLkey(rs.getString("adverse_events_lkey"));
record.setAdverseEventsNote(rs.getString("adverse_events_note"));
record.setActionsTaken(rs.getString("actions_taken"));
record.setSurgeonNotified(rs.getBoolean("surgeon_notified"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApOperationAnesthesiaInductionMonitoring record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_operation_anesthesia_induction_monitoring values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getOperationRequestKey());
ps.setBigDecimal(3, record.getWeight());
ps.setBigDecimal(4, record.getFastingDuration());
ps.setBoolean(5, record.getIvLineEstablished());
ps.setString(6, record.getMonitorsConnected());
if (record.getInductionStartTime() != null) ps.setDate(7, new java.sql.Date(record.getInductionStartTime().getTime()));
else ps.setDate(7, null); 
ps.setBoolean(8, record.getIntubationDone());
ps.setString(9, record.getTubeSize());
ps.setString(10, record.getTubeType());
ps.setString(11, record.getSecuredBy());
ps.setString(12, record.getCreatedBy());
ps.setString(13, record.getUpdatedBy());
ps.setString(14, record.getDeletedBy());
ps.setBigDecimal(15, record.getCreatedAt());
ps.setBigDecimal(16, record.getUpdatedAt());
ps.setBigDecimal(17, record.getDeletedAt());
ps.setString(18, record.getEncounterKey());
ps.setString(19, record.getPatientKey());
ps.setString(20, record.getAdverseEventsLkey());
ps.setString(21, record.getAdverseEventsNote());
ps.setString(22, record.getActionsTaken());
ps.setBoolean(23, record.getSurgeonNotified());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApOperationAnesthesiaInductionMonitoringEntity entity, String lang) {
        Class<?> myClass = ApOperationAnesthesiaInductionMonitoringEntity.class;
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
public void translateObject(ApOperationAnesthesiaInductionMonitoringEntity entity, String lang) {
        ApOperationAnesthesiaInductionMonitoringEntity translated = (ApOperationAnesthesiaInductionMonitoringEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}