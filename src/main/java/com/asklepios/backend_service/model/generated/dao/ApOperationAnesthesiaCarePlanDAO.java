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
import com.asklepios.backend_service.model.generated.pojo.ApOperationAnesthesiaCarePlan;
import com.asklepios.backend_service.model.generated.entity.ApOperationAnesthesiaCarePlanEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApOperationAnesthesiaCarePlanDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApOperationAnesthesiaCarePlan getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_operation_anesthesia_care_plan where key = '"+key+"'");) {
ApOperationAnesthesiaCarePlan record = new ApOperationAnesthesiaCarePlan();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setPatientKey(rs.getString("patient_key"));
record.setAnesthesiaConsentSigned(rs.getBoolean("anesthesia_consent_signed"));
record.setUnderstandsRisks(rs.getBoolean("understands_risks"));
record.setPreviousAnesthesia(rs.getBoolean("previous_anesthesia"));
record.setAnesthesiaHistory(rs.getString("anesthesia_history"));
record.setOperationKey(rs.getString("operation_key"));
record.setMallampatiClassificationLkey(rs.getString("mallampati_classification_lkey"));
record.setAirwayGradesLkey(rs.getString("airway_grades_lkey"));
record.setPlannedAirwayApproachLkey(rs.getString("planned_airway_approach_lkey"));
record.setNasalPatencyLkey(rs.getString("nasal_patency_lkey"));
record.setThyromentalDistance(rs.getBigDecimal("thyromental_distance"));
record.setMouthOpening(rs.getBigDecimal("mouth_opening"));
record.setNeckMobility(rs.getString("neck_mobility"));
record.setFacialOrNeckAbnormalities(rs.getString("facial_or_neck_abnormalities"));
record.setBeardOrFacialHair(rs.getBoolean("beard_or_facial_hair"));
record.setAnticipatedDifficultAirway(rs.getBoolean("anticipated_difficult_airway"));
record.setPreviousDifficultIntubation(rs.getBoolean("previous_difficult_intubation"));
record.setDifficultIntubationNotes(rs.getString("difficult_intubation_notes"));
record.setCreatedBy(rs.getString("created_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApOperationAnesthesiaCarePlan record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_operation_anesthesia_care_plan set key = ?, encounter_key = ?, patient_key = ?, anesthesia_consent_signed = ?, understands_risks = ?, previous_anesthesia = ?, anesthesia_history = ?, operation_key = ?, mallampati_classification_lkey = ?, airway_grades_lkey = ?, planned_airway_approach_lkey = ?, nasal_patency_lkey = ?, thyromental_distance = ?, mouth_opening = ?, neck_mobility = ?, facial_or_neck_abnormalities = ?, beard_or_facial_hair = ?, anticipated_difficult_airway = ?, previous_difficult_intubation = ?, difficult_intubation_notes = ?, created_by = ?, created_at = ?, updated_by = ?, updated_at = ?, deleted_by = ?, deleted_at = ?, is_valid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getEncounterKey());
ps.setString(3, record.getPatientKey());
ps.setBoolean(4, record.getAnesthesiaConsentSigned());
ps.setBoolean(5, record.getUnderstandsRisks());
ps.setBoolean(6, record.getPreviousAnesthesia());
ps.setString(7, record.getAnesthesiaHistory());
ps.setString(8, record.getOperationKey());
ps.setString(9, record.getMallampatiClassificationLkey());
ps.setString(10, record.getAirwayGradesLkey());
ps.setString(11, record.getPlannedAirwayApproachLkey());
ps.setString(12, record.getNasalPatencyLkey());
ps.setBigDecimal(13, record.getThyromentalDistance());
ps.setBigDecimal(14, record.getMouthOpening());
ps.setString(15, record.getNeckMobility());
ps.setString(16, record.getFacialOrNeckAbnormalities());
ps.setBoolean(17, record.getBeardOrFacialHair());
ps.setBoolean(18, record.getAnticipatedDifficultAirway());
ps.setBoolean(19, record.getPreviousDifficultIntubation());
ps.setString(20, record.getDifficultIntubationNotes());
ps.setString(21, record.getCreatedBy());
ps.setBigDecimal(22, record.getCreatedAt());
ps.setString(23, record.getUpdatedBy());
ps.setBigDecimal(24, record.getUpdatedAt());
ps.setString(25, record.getDeletedBy());
ps.setBigDecimal(26, record.getDeletedAt());
ps.setBoolean(27, record.getIsValid());
ps.setString(28, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApOperationAnesthesiaCarePlan record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_operation_anesthesia_care_plan set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApOperationAnesthesiaCarePlan> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_operation_anesthesia_care_plan where "+ where);) {
List<ApOperationAnesthesiaCarePlan> list = new ArrayList<ApOperationAnesthesiaCarePlan>();
while(rs.next()){
ApOperationAnesthesiaCarePlan record = new ApOperationAnesthesiaCarePlan();
record.setKey(rs.getString("key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setPatientKey(rs.getString("patient_key"));
record.setAnesthesiaConsentSigned(rs.getBoolean("anesthesia_consent_signed"));
record.setUnderstandsRisks(rs.getBoolean("understands_risks"));
record.setPreviousAnesthesia(rs.getBoolean("previous_anesthesia"));
record.setAnesthesiaHistory(rs.getString("anesthesia_history"));
record.setOperationKey(rs.getString("operation_key"));
record.setMallampatiClassificationLkey(rs.getString("mallampati_classification_lkey"));
record.setAirwayGradesLkey(rs.getString("airway_grades_lkey"));
record.setPlannedAirwayApproachLkey(rs.getString("planned_airway_approach_lkey"));
record.setNasalPatencyLkey(rs.getString("nasal_patency_lkey"));
record.setThyromentalDistance(rs.getBigDecimal("thyromental_distance"));
record.setMouthOpening(rs.getBigDecimal("mouth_opening"));
record.setNeckMobility(rs.getString("neck_mobility"));
record.setFacialOrNeckAbnormalities(rs.getString("facial_or_neck_abnormalities"));
record.setBeardOrFacialHair(rs.getBoolean("beard_or_facial_hair"));
record.setAnticipatedDifficultAirway(rs.getBoolean("anticipated_difficult_airway"));
record.setPreviousDifficultIntubation(rs.getBoolean("previous_difficult_intubation"));
record.setDifficultIntubationNotes(rs.getString("difficult_intubation_notes"));
record.setCreatedBy(rs.getString("created_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApOperationAnesthesiaCarePlan record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_operation_anesthesia_care_plan values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getEncounterKey());
ps.setString(3, record.getPatientKey());
ps.setBoolean(4, record.getAnesthesiaConsentSigned());
ps.setBoolean(5, record.getUnderstandsRisks());
ps.setBoolean(6, record.getPreviousAnesthesia());
ps.setString(7, record.getAnesthesiaHistory());
ps.setString(8, record.getOperationKey());
ps.setString(9, record.getMallampatiClassificationLkey());
ps.setString(10, record.getAirwayGradesLkey());
ps.setString(11, record.getPlannedAirwayApproachLkey());
ps.setString(12, record.getNasalPatencyLkey());
ps.setBigDecimal(13, record.getThyromentalDistance());
ps.setBigDecimal(14, record.getMouthOpening());
ps.setString(15, record.getNeckMobility());
ps.setString(16, record.getFacialOrNeckAbnormalities());
ps.setBoolean(17, record.getBeardOrFacialHair());
ps.setBoolean(18, record.getAnticipatedDifficultAirway());
ps.setBoolean(19, record.getPreviousDifficultIntubation());
ps.setString(20, record.getDifficultIntubationNotes());
ps.setString(21, record.getCreatedBy());
ps.setBigDecimal(22, record.getCreatedAt());
ps.setString(23, record.getUpdatedBy());
ps.setBigDecimal(24, record.getUpdatedAt());
ps.setString(25, record.getDeletedBy());
ps.setBigDecimal(26, record.getDeletedAt());
ps.setBoolean(27, record.getIsValid());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApOperationAnesthesiaCarePlanEntity entity, String lang) {
        Class<?> myClass = ApOperationAnesthesiaCarePlanEntity.class;
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
public void translateObject(ApOperationAnesthesiaCarePlanEntity entity, String lang) {
        ApOperationAnesthesiaCarePlanEntity translated = (ApOperationAnesthesiaCarePlanEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}