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
import com.asklepios.backend_service.model.generated.pojo.ApFunctionalAssessment;
import com.asklepios.backend_service.model.generated.entity.ApFunctionalAssessmentEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApFunctionalAssessmentDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApFunctionalAssessment getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_functional_assessment where key = '"+key+"'");) {
ApFunctionalAssessment record = new ApFunctionalAssessment();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setMobilityAmbulation(rs.getBoolean("mobility_ambulation"));
record.setTransferringBedChair(rs.getBoolean("transferring_bed_chair"));
record.setStairClimbingAbility(rs.getBoolean("stair_climbing_ability"));
record.setFeeding(rs.getBoolean("feeding"));
record.setToiletingAbility(rs.getBoolean("toileting_ability"));
record.setBathingAbility(rs.getBoolean("bathing_ability"));
record.setDressingAbility(rs.getBoolean("dressing_ability"));
record.setGroomingAbility(rs.getBoolean("grooming_ability"));
record.setWalkingDistance(rs.getBoolean("walking_distance"));
record.setBalance(rs.getBoolean("balance"));
record.setUrinaryContinence(rs.getBoolean("urinary_continence"));
record.setBowelContinence(rs.getBoolean("bowel_continence"));
record.setUseOfAssistiveDevices(rs.getBoolean("use_of_assistive_devices"));
record.setNeedForAssistance(rs.getBoolean("need_for_assistance"));
record.setFallHistory(rs.getBoolean("fall_history"));
record.setPainDuringMovement(rs.getBoolean("pain_during_movement"));
record.setNeedForRehab(rs.getBoolean("need_for_rehab"));
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
public void updateRecord(ApFunctionalAssessment record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_functional_assessment set key = ?, patient_key = ?, encounter_key = ?, mobility_ambulation = ?, transferring_bed_chair = ?, stair_climbing_ability = ?, feeding = ?, toileting_ability = ?, bathing_ability = ?, dressing_ability = ?, grooming_ability = ?, walking_distance = ?, balance = ?, urinary_continence = ?, bowel_continence = ?, use_of_assistive_devices = ?, need_for_assistance = ?, fall_history = ?, pain_during_movement = ?, need_for_rehab = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, cancellation_reason = ?, status_lkey = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getEncounterKey());
ps.setBoolean(4, record.getMobilityAmbulation());
ps.setBoolean(5, record.getTransferringBedChair());
ps.setBoolean(6, record.getStairClimbingAbility());
ps.setBoolean(7, record.getFeeding());
ps.setBoolean(8, record.getToiletingAbility());
ps.setBoolean(9, record.getBathingAbility());
ps.setBoolean(10, record.getDressingAbility());
ps.setBoolean(11, record.getGroomingAbility());
ps.setBoolean(12, record.getWalkingDistance());
ps.setBoolean(13, record.getBalance());
ps.setBoolean(14, record.getUrinaryContinence());
ps.setBoolean(15, record.getBowelContinence());
ps.setBoolean(16, record.getUseOfAssistiveDevices());
ps.setBoolean(17, record.getNeedForAssistance());
ps.setBoolean(18, record.getFallHistory());
ps.setBoolean(19, record.getPainDuringMovement());
ps.setBoolean(20, record.getNeedForRehab());
ps.setString(21, record.getCreatedBy());
ps.setString(22, record.getUpdatedBy());
ps.setString(23, record.getDeletedBy());
ps.setBigDecimal(24, record.getCreatedAt());
ps.setBigDecimal(25, record.getUpdatedAt());
ps.setBigDecimal(26, record.getDeletedAt());
ps.setString(27, record.getCancellationReason());
ps.setString(28, record.getStatusLkey());
ps.setString(29, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApFunctionalAssessment record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_functional_assessment set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApFunctionalAssessment> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_functional_assessment where "+ where);) {
List<ApFunctionalAssessment> list = new ArrayList<ApFunctionalAssessment>();
while(rs.next()){
ApFunctionalAssessment record = new ApFunctionalAssessment();
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setMobilityAmbulation(rs.getBoolean("mobility_ambulation"));
record.setTransferringBedChair(rs.getBoolean("transferring_bed_chair"));
record.setStairClimbingAbility(rs.getBoolean("stair_climbing_ability"));
record.setFeeding(rs.getBoolean("feeding"));
record.setToiletingAbility(rs.getBoolean("toileting_ability"));
record.setBathingAbility(rs.getBoolean("bathing_ability"));
record.setDressingAbility(rs.getBoolean("dressing_ability"));
record.setGroomingAbility(rs.getBoolean("grooming_ability"));
record.setWalkingDistance(rs.getBoolean("walking_distance"));
record.setBalance(rs.getBoolean("balance"));
record.setUrinaryContinence(rs.getBoolean("urinary_continence"));
record.setBowelContinence(rs.getBoolean("bowel_continence"));
record.setUseOfAssistiveDevices(rs.getBoolean("use_of_assistive_devices"));
record.setNeedForAssistance(rs.getBoolean("need_for_assistance"));
record.setFallHistory(rs.getBoolean("fall_history"));
record.setPainDuringMovement(rs.getBoolean("pain_during_movement"));
record.setNeedForRehab(rs.getBoolean("need_for_rehab"));
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
public String saveRecord(ApFunctionalAssessment record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_functional_assessment values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getEncounterKey());
ps.setBoolean(4, record.getMobilityAmbulation());
ps.setBoolean(5, record.getTransferringBedChair());
ps.setBoolean(6, record.getStairClimbingAbility());
ps.setBoolean(7, record.getFeeding());
ps.setBoolean(8, record.getToiletingAbility());
ps.setBoolean(9, record.getBathingAbility());
ps.setBoolean(10, record.getDressingAbility());
ps.setBoolean(11, record.getGroomingAbility());
ps.setBoolean(12, record.getWalkingDistance());
ps.setBoolean(13, record.getBalance());
ps.setBoolean(14, record.getUrinaryContinence());
ps.setBoolean(15, record.getBowelContinence());
ps.setBoolean(16, record.getUseOfAssistiveDevices());
ps.setBoolean(17, record.getNeedForAssistance());
ps.setBoolean(18, record.getFallHistory());
ps.setBoolean(19, record.getPainDuringMovement());
ps.setBoolean(20, record.getNeedForRehab());
ps.setString(21, record.getCreatedBy());
ps.setString(22, record.getUpdatedBy());
ps.setString(23, record.getDeletedBy());
ps.setBigDecimal(24, record.getCreatedAt());
ps.setBigDecimal(25, record.getUpdatedAt());
ps.setBigDecimal(26, record.getDeletedAt());
ps.setString(27, record.getCancellationReason());
ps.setString(28, record.getStatusLkey());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApFunctionalAssessmentEntity entity, String lang) {
        Class<?> myClass = ApFunctionalAssessmentEntity.class;
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
public void translateObject(ApFunctionalAssessmentEntity entity, String lang) {
        ApFunctionalAssessmentEntity translated = (ApFunctionalAssessmentEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}