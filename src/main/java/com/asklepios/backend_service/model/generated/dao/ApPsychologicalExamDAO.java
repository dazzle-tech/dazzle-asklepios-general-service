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
import com.asklepios.backend_service.model.generated.pojo.ApPsychologicalExam;
import com.asklepios.backend_service.model.generated.entity.ApPsychologicalExamEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApPsychologicalExamDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApPsychologicalExam getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_psychological_exam where key = '"+key+"'");) {
ApPsychologicalExam record = new ApPsychologicalExam();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setTestTypeLkey(rs.getString("test_type_lkey"));
record.setReason(rs.getString("reason"));
record.setTestDuration(rs.getBigDecimal("test_duration"));
record.setUnitLkey(rs.getString("unit_lkey"));
record.setScoreLkey(rs.getString("score_lkey"));
record.setResultInterpretationLkey(rs.getString("result_interpretation_lkey"));
record.setClinicalObservations(rs.getString("clinical_observations"));
record.setTreatmentPlan(rs.getString("treatment_plan"));
record.setAdditionalNotes(rs.getString("additional_notes"));
record.setRequireFollowUp(rs.getBoolean("require_follow_up"));
record.setFollowUpDate(rs.getBigDecimal("follow_up_date"));
record.setCreatedBy(rs.getString("created_by"));
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
public void updateRecord(ApPsychologicalExam record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_psychological_exam set key = ?, patient_key = ?, encounter_key = ?, test_type_lkey = ?, reason = ?, test_duration = ?, unit_lkey = ?, score_lkey = ?, result_interpretation_lkey = ?, clinical_observations = ?, treatment_plan = ?, additional_notes = ?, require_follow_up = ?, follow_up_date = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, cancellation_reason = ?, status_lkey = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getEncounterKey());
ps.setString(4, record.getTestTypeLkey());
ps.setString(5, record.getReason());
ps.setBigDecimal(6, record.getTestDuration());
ps.setString(7, record.getUnitLkey());
ps.setString(8, record.getScoreLkey());
ps.setString(9, record.getResultInterpretationLkey());
ps.setString(10, record.getClinicalObservations());
ps.setString(11, record.getTreatmentPlan());
ps.setString(12, record.getAdditionalNotes());
ps.setBoolean(13, record.getRequireFollowUp());
ps.setBigDecimal(14, record.getFollowUpDate());
ps.setString(15, record.getCreatedBy());
ps.setString(16, record.getUpdatedBy());
ps.setString(17, record.getDeletedBy());
ps.setBigDecimal(18, record.getCreatedAt());
ps.setBigDecimal(19, record.getUpdatedAt());
ps.setBigDecimal(20, record.getDeletedAt());
ps.setString(21, record.getCancellationReason());
ps.setString(22, record.getStatusLkey());
ps.setString(23, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApPsychologicalExam record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_psychological_exam set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApPsychologicalExam> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_psychological_exam where "+ where);) {
List<ApPsychologicalExam> list = new ArrayList<ApPsychologicalExam>();
while(rs.next()){
ApPsychologicalExam record = new ApPsychologicalExam();
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setTestTypeLkey(rs.getString("test_type_lkey"));
record.setReason(rs.getString("reason"));
record.setTestDuration(rs.getBigDecimal("test_duration"));
record.setUnitLkey(rs.getString("unit_lkey"));
record.setScoreLkey(rs.getString("score_lkey"));
record.setResultInterpretationLkey(rs.getString("result_interpretation_lkey"));
record.setClinicalObservations(rs.getString("clinical_observations"));
record.setTreatmentPlan(rs.getString("treatment_plan"));
record.setAdditionalNotes(rs.getString("additional_notes"));
record.setRequireFollowUp(rs.getBoolean("require_follow_up"));
record.setFollowUpDate(rs.getBigDecimal("follow_up_date"));
record.setCreatedBy(rs.getString("created_by"));
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
public String saveRecord(ApPsychologicalExam record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_psychological_exam values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getEncounterKey());
ps.setString(4, record.getTestTypeLkey());
ps.setString(5, record.getReason());
ps.setBigDecimal(6, record.getTestDuration());
ps.setString(7, record.getUnitLkey());
ps.setString(8, record.getScoreLkey());
ps.setString(9, record.getResultInterpretationLkey());
ps.setString(10, record.getClinicalObservations());
ps.setString(11, record.getTreatmentPlan());
ps.setString(12, record.getAdditionalNotes());
ps.setBoolean(13, record.getRequireFollowUp());
ps.setBigDecimal(14, record.getFollowUpDate());
ps.setString(15, record.getCreatedBy());
ps.setString(16, record.getUpdatedBy());
ps.setString(17, record.getDeletedBy());
ps.setBigDecimal(18, record.getCreatedAt());
ps.setBigDecimal(19, record.getUpdatedAt());
ps.setBigDecimal(20, record.getDeletedAt());
ps.setString(21, record.getCancellationReason());
ps.setString(22, record.getStatusLkey());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApPsychologicalExamEntity entity, String lang) {
        Class<?> myClass = ApPsychologicalExamEntity.class;
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
public void translateObject(ApPsychologicalExamEntity entity, String lang) {
        ApPsychologicalExamEntity translated = (ApPsychologicalExamEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}