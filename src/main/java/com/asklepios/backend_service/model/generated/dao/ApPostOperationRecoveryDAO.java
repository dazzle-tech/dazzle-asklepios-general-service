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
import com.asklepios.backend_service.model.generated.pojo.ApPostOperationRecovery;
import com.asklepios.backend_service.model.generated.entity.ApPostOperationRecoveryEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApPostOperationRecoveryDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApPostOperationRecovery getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_post_operation_recovery where key = '"+key+"'");) {
ApPostOperationRecovery record = new ApPostOperationRecovery();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setPatientKey(rs.getString("patient_key"));
record.setArrivalTime(rs.getBigDecimal("arrival_time"));
record.setPainScoreLkey(rs.getString("pain_score_lkey"));
record.setNausea(rs.getBoolean("nausea"));
record.setVomiting(rs.getBoolean("vomiting"));
record.setRecoveryStatus(rs.getString("recovery_status"));
record.setNursingNotes(rs.getString("nursing_notes"));
record.setActivityLkey(rs.getString("activity_lkey"));
record.setRespirationLkey(rs.getString("respiration_lkey"));
record.setCirculationLkey(rs.getString("circulation_lkey"));
record.setConsciousnessLkey(rs.getString("consciousness_lkey"));
record.setOxygenSaturationLkey(rs.getString("oxygen_saturation_lkey"));
record.setAldreteScore(rs.getBigDecimal("aldrete_score"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsvalid(rs.getBoolean("isvalid"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApPostOperationRecovery record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_post_operation_recovery set key = ?, encounter_key = ?, patient_key = ?, arrival_time = ?, pain_score_lkey = ?, nausea = ?, vomiting = ?, recovery_status = ?, nursing_notes = ?, activity_lkey = ?, respiration_lkey = ?, circulation_lkey = ?, consciousness_lkey = ?, oxygen_saturation_lkey = ?, aldrete_score = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, isvalid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getEncounterKey());
ps.setString(3, record.getPatientKey());
ps.setBigDecimal(4, record.getArrivalTime());
ps.setString(5, record.getPainScoreLkey());
ps.setBoolean(6, record.getNausea());
ps.setBoolean(7, record.getVomiting());
ps.setString(8, record.getRecoveryStatus());
ps.setString(9, record.getNursingNotes());
ps.setString(10, record.getActivityLkey());
ps.setString(11, record.getRespirationLkey());
ps.setString(12, record.getCirculationLkey());
ps.setString(13, record.getConsciousnessLkey());
ps.setString(14, record.getOxygenSaturationLkey());
ps.setBigDecimal(15, record.getAldreteScore());
ps.setString(16, record.getCreatedBy());
ps.setString(17, record.getUpdatedBy());
ps.setString(18, record.getDeletedBy());
ps.setBigDecimal(19, record.getCreatedAt());
ps.setBigDecimal(20, record.getUpdatedAt());
ps.setBigDecimal(21, record.getDeletedAt());
ps.setBoolean(22, record.getIsvalid());
ps.setString(23, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApPostOperationRecovery record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_post_operation_recovery set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApPostOperationRecovery> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_post_operation_recovery where "+ where);) {
List<ApPostOperationRecovery> list = new ArrayList<ApPostOperationRecovery>();
while(rs.next()){
ApPostOperationRecovery record = new ApPostOperationRecovery();
record.setKey(rs.getString("key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setPatientKey(rs.getString("patient_key"));
record.setArrivalTime(rs.getBigDecimal("arrival_time"));
record.setPainScoreLkey(rs.getString("pain_score_lkey"));
record.setNausea(rs.getBoolean("nausea"));
record.setVomiting(rs.getBoolean("vomiting"));
record.setRecoveryStatus(rs.getString("recovery_status"));
record.setNursingNotes(rs.getString("nursing_notes"));
record.setActivityLkey(rs.getString("activity_lkey"));
record.setRespirationLkey(rs.getString("respiration_lkey"));
record.setCirculationLkey(rs.getString("circulation_lkey"));
record.setConsciousnessLkey(rs.getString("consciousness_lkey"));
record.setOxygenSaturationLkey(rs.getString("oxygen_saturation_lkey"));
record.setAldreteScore(rs.getBigDecimal("aldrete_score"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsvalid(rs.getBoolean("isvalid"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApPostOperationRecovery record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_post_operation_recovery values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getEncounterKey());
ps.setString(3, record.getPatientKey());
ps.setBigDecimal(4, record.getArrivalTime());
ps.setString(5, record.getPainScoreLkey());
ps.setBoolean(6, record.getNausea());
ps.setBoolean(7, record.getVomiting());
ps.setString(8, record.getRecoveryStatus());
ps.setString(9, record.getNursingNotes());
ps.setString(10, record.getActivityLkey());
ps.setString(11, record.getRespirationLkey());
ps.setString(12, record.getCirculationLkey());
ps.setString(13, record.getConsciousnessLkey());
ps.setString(14, record.getOxygenSaturationLkey());
ps.setBigDecimal(15, record.getAldreteScore());
ps.setString(16, record.getCreatedBy());
ps.setString(17, record.getUpdatedBy());
ps.setString(18, record.getDeletedBy());
ps.setBigDecimal(19, record.getCreatedAt());
ps.setBigDecimal(20, record.getUpdatedAt());
ps.setBigDecimal(21, record.getDeletedAt());
ps.setBoolean(22, record.getIsvalid());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApPostOperationRecoveryEntity entity, String lang) {
        Class<?> myClass = ApPostOperationRecoveryEntity.class;
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
public void translateObject(ApPostOperationRecoveryEntity entity, String lang) {
        ApPostOperationRecoveryEntity translated = (ApPostOperationRecoveryEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}