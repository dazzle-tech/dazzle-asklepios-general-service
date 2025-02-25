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
import com.asklepios.backend_service.model.generated.pojo.ApTreadmillStress;
import com.asklepios.backend_service.model.generated.entity.ApTreadmillStressEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApTreadmillStressDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApTreadmillStress getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_treadmill_stress where key = '"+key+"'");) {
ApTreadmillStress record = new ApTreadmillStress();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setIndication(rs.getString("indication"));
record.setPreTestSystolicBp(rs.getBigDecimal("pre_test_systolic_bp"));
record.setPreTestDiastolicBp(rs.getBigDecimal("pre_test_diastolic_bp"));
record.setBaselineEcgFindingsLkey(rs.getString("baseline_ecg_findings_lkey"));
record.setBruceProtocolStageLkey(rs.getString("bruce_protocol_stage_lkey"));
record.setExerciseDuration(rs.getBigDecimal("exercise_duration"));
record.setMaximumHeartRateAchieved(rs.getBigDecimal("maximum_heart_rate_achieved"));
record.setTargetHeartRate(rs.getBigDecimal("target_heart_rate"));
record.setSegmentChangeLkey(rs.getString("segment_change_lkey"));
record.setArrhythmiaNoted(rs.getBoolean("arrhythmia_noted"));
record.setTypeLkey(rs.getString("type_lkey"));
record.setTestOutcomeLkey(rs.getString("test_outcome_lkey"));
record.setPostTestSystolicBp(rs.getBigDecimal("post_test_systolic_bp"));
record.setPostTestDiastolicBp(rs.getBigDecimal("post_test_diastolic_bp"));
record.setRecoveryTime(rs.getBigDecimal("recovery_time"));
record.setCreatedBy(rs.getString("created_by"));
record.setCardiologistNotes(rs.getString("cardiologist_notes"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setCancellationReason(rs.getString("cancellation_reason"));
record.setStatusLkey(rs.getString("status_lkey"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApTreadmillStress record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_treadmill_stress set key = ?, patient_key = ?, encounter_key = ?, indication = ?, pre_test_systolic_bp = ?, pre_test_diastolic_bp = ?, baseline_ecg_findings_lkey = ?, bruce_protocol_stage_lkey = ?, exercise_duration = ?, maximum_heart_rate_achieved = ?, target_heart_rate = ?, segment_change_lkey = ?, arrhythmia_noted = ?, type_lkey = ?, test_outcome_lkey = ?, post_test_systolic_bp = ?, post_test_diastolic_bp = ?, recovery_time = ?, created_by = ?, cardiologist_notes = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, cancellation_reason = ?, status_lkey = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getEncounterKey());
ps.setString(4, record.getIndication());
ps.setBigDecimal(5, record.getPreTestSystolicBp());
ps.setBigDecimal(6, record.getPreTestDiastolicBp());
ps.setString(7, record.getBaselineEcgFindingsLkey());
ps.setString(8, record.getBruceProtocolStageLkey());
ps.setBigDecimal(9, record.getExerciseDuration());
ps.setBigDecimal(10, record.getMaximumHeartRateAchieved());
ps.setBigDecimal(11, record.getTargetHeartRate());
ps.setString(12, record.getSegmentChangeLkey());
ps.setBoolean(13, record.getArrhythmiaNoted());
ps.setString(14, record.getTypeLkey());
ps.setString(15, record.getTestOutcomeLkey());
ps.setBigDecimal(16, record.getPostTestSystolicBp());
ps.setBigDecimal(17, record.getPostTestDiastolicBp());
ps.setBigDecimal(18, record.getRecoveryTime());
ps.setString(19, record.getCreatedBy());
ps.setString(20, record.getCardiologistNotes());
ps.setString(21, record.getUpdatedBy());
ps.setString(22, record.getDeletedBy());
ps.setBigDecimal(23, record.getCreatedAt());
ps.setBigDecimal(24, record.getUpdatedAt());
ps.setBigDecimal(25, record.getDeletedAt());
ps.setString(26, record.getCancellationReason());
ps.setString(27, record.getStatusLkey());
ps.setString(28, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApTreadmillStress record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_treadmill_stress set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApTreadmillStress> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_treadmill_stress where "+ where);) {
List<ApTreadmillStress> list = new ArrayList<ApTreadmillStress>();
while(rs.next()){
ApTreadmillStress record = new ApTreadmillStress();
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setIndication(rs.getString("indication"));
record.setPreTestSystolicBp(rs.getBigDecimal("pre_test_systolic_bp"));
record.setPreTestDiastolicBp(rs.getBigDecimal("pre_test_diastolic_bp"));
record.setBaselineEcgFindingsLkey(rs.getString("baseline_ecg_findings_lkey"));
record.setBruceProtocolStageLkey(rs.getString("bruce_protocol_stage_lkey"));
record.setExerciseDuration(rs.getBigDecimal("exercise_duration"));
record.setMaximumHeartRateAchieved(rs.getBigDecimal("maximum_heart_rate_achieved"));
record.setTargetHeartRate(rs.getBigDecimal("target_heart_rate"));
record.setSegmentChangeLkey(rs.getString("segment_change_lkey"));
record.setArrhythmiaNoted(rs.getBoolean("arrhythmia_noted"));
record.setTypeLkey(rs.getString("type_lkey"));
record.setTestOutcomeLkey(rs.getString("test_outcome_lkey"));
record.setPostTestSystolicBp(rs.getBigDecimal("post_test_systolic_bp"));
record.setPostTestDiastolicBp(rs.getBigDecimal("post_test_diastolic_bp"));
record.setRecoveryTime(rs.getBigDecimal("recovery_time"));
record.setCreatedBy(rs.getString("created_by"));
record.setCardiologistNotes(rs.getString("cardiologist_notes"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setCancellationReason(rs.getString("cancellation_reason"));
record.setStatusLkey(rs.getString("status_lkey"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApTreadmillStress record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_treadmill_stress values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getEncounterKey());
ps.setString(4, record.getIndication());
ps.setBigDecimal(5, record.getPreTestSystolicBp());
ps.setBigDecimal(6, record.getPreTestDiastolicBp());
ps.setString(7, record.getBaselineEcgFindingsLkey());
ps.setString(8, record.getBruceProtocolStageLkey());
ps.setBigDecimal(9, record.getExerciseDuration());
ps.setBigDecimal(10, record.getMaximumHeartRateAchieved());
ps.setBigDecimal(11, record.getTargetHeartRate());
ps.setString(12, record.getSegmentChangeLkey());
ps.setBoolean(13, record.getArrhythmiaNoted());
ps.setString(14, record.getTypeLkey());
ps.setString(15, record.getTestOutcomeLkey());
ps.setBigDecimal(16, record.getPostTestSystolicBp());
ps.setBigDecimal(17, record.getPostTestDiastolicBp());
ps.setBigDecimal(18, record.getRecoveryTime());
ps.setString(19, record.getCreatedBy());
ps.setString(20, record.getCardiologistNotes());
ps.setString(21, record.getUpdatedBy());
ps.setString(22, record.getDeletedBy());
ps.setBigDecimal(23, record.getCreatedAt());
ps.setBigDecimal(24, record.getUpdatedAt());
ps.setBigDecimal(25, record.getDeletedAt());
ps.setString(26, record.getCancellationReason());
ps.setString(27, record.getStatusLkey());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApTreadmillStressEntity entity, String lang) {
        Class<?> myClass = ApTreadmillStressEntity.class;
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
public void translateObject(ApTreadmillStressEntity entity, String lang) {
        ApTreadmillStressEntity translated = (ApTreadmillStressEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}