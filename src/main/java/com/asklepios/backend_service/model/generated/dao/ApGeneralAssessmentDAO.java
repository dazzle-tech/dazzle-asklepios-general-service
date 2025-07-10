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
import com.asklepios.backend_service.model.generated.pojo.ApGeneralAssessment;
import com.asklepios.backend_service.model.generated.entity.ApGeneralAssessmentEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApGeneralAssessmentDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApGeneralAssessment getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_general_assessment where key = '"+key+"'");) {
ApGeneralAssessment record = new ApGeneralAssessment();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setPositionStatusLkey(rs.getString("position_status_lkey"));
record.setBodyMovementsLkey(rs.getString("body_movements_lkey"));
record.setLevelOfConsciousnessLkey(rs.getString("level_of_consciousness_lkey"));
record.setFacialExpressionLkey(rs.getString("facial_expression_lkey"));
record.setSpeechLkey(rs.getString("speech_lkey"));
record.setMoodBehaviorLkey(rs.getString("mood_behavior_lkey"));
record.setMemoryRecent(rs.getBoolean("memory_recent"));
record.setMemoryRemote(rs.getBoolean("memory_remote"));
record.setSignsOfAgitation(rs.getBoolean("signs_of_agitation"));
record.setSignsOfDepression(rs.getBoolean("signs_of_depression"));
record.setSignsOfSuicidalIdeation(rs.getBoolean("signs_of_suicidal_ideation"));
record.setSignsOfSubstanceUse(rs.getBoolean("signs_of_substance_use"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setCancellationReason(rs.getString("cancellation_reason"));
record.setStatusLkey(rs.getString("status_lkey"));
record.setLivingCondition(rs.getString("living_condition"));
record.setPatientNeedHelp(rs.getBoolean("patient_need_help"));
record.setSupportingMembers(rs.getString("supporting_members"));
record.setFamilyLocationLkey(rs.getString("family_location_lkey"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApGeneralAssessment record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_general_assessment set key = ?, patient_key = ?, encounter_key = ?, position_status_lkey = ?, body_movements_lkey = ?, level_of_consciousness_lkey = ?, facial_expression_lkey = ?, speech_lkey = ?, mood_behavior_lkey = ?, memory_recent = ?, memory_remote = ?, signs_of_agitation = ?, signs_of_depression = ?, signs_of_suicidal_ideation = ?, signs_of_substance_use = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, cancellation_reason = ?, status_lkey = ?, living_condition = ?, patient_need_help = ?, supporting_members = ?, family_location_lkey = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getEncounterKey());
ps.setString(4, record.getPositionStatusLkey());
ps.setString(5, record.getBodyMovementsLkey());
ps.setString(6, record.getLevelOfConsciousnessLkey());
ps.setString(7, record.getFacialExpressionLkey());
ps.setString(8, record.getSpeechLkey());
ps.setString(9, record.getMoodBehaviorLkey());
ps.setBoolean(10, record.getMemoryRecent());
ps.setBoolean(11, record.getMemoryRemote());
ps.setBoolean(12, record.getSignsOfAgitation());
ps.setBoolean(13, record.getSignsOfDepression());
ps.setBoolean(14, record.getSignsOfSuicidalIdeation());
ps.setBoolean(15, record.getSignsOfSubstanceUse());
ps.setString(16, record.getCreatedBy());
ps.setString(17, record.getUpdatedBy());
ps.setString(18, record.getDeletedBy());
ps.setBigDecimal(19, record.getCreatedAt());
ps.setBigDecimal(20, record.getUpdatedAt());
ps.setBigDecimal(21, record.getDeletedAt());
ps.setString(22, record.getCancellationReason());
ps.setString(23, record.getStatusLkey());
ps.setString(24, record.getLivingCondition());
ps.setBoolean(25, record.getPatientNeedHelp());
ps.setString(26, record.getSupportingMembers());
ps.setString(27, record.getFamilyLocationLkey());
ps.setString(28, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApGeneralAssessment record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_general_assessment set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApGeneralAssessment> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_general_assessment where "+ where);) {
List<ApGeneralAssessment> list = new ArrayList<ApGeneralAssessment>();
while(rs.next()){
ApGeneralAssessment record = new ApGeneralAssessment();
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setPositionStatusLkey(rs.getString("position_status_lkey"));
record.setBodyMovementsLkey(rs.getString("body_movements_lkey"));
record.setLevelOfConsciousnessLkey(rs.getString("level_of_consciousness_lkey"));
record.setFacialExpressionLkey(rs.getString("facial_expression_lkey"));
record.setSpeechLkey(rs.getString("speech_lkey"));
record.setMoodBehaviorLkey(rs.getString("mood_behavior_lkey"));
record.setMemoryRecent(rs.getBoolean("memory_recent"));
record.setMemoryRemote(rs.getBoolean("memory_remote"));
record.setSignsOfAgitation(rs.getBoolean("signs_of_agitation"));
record.setSignsOfDepression(rs.getBoolean("signs_of_depression"));
record.setSignsOfSuicidalIdeation(rs.getBoolean("signs_of_suicidal_ideation"));
record.setSignsOfSubstanceUse(rs.getBoolean("signs_of_substance_use"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setCancellationReason(rs.getString("cancellation_reason"));
record.setStatusLkey(rs.getString("status_lkey"));
record.setLivingCondition(rs.getString("living_condition"));
record.setPatientNeedHelp(rs.getBoolean("patient_need_help"));
record.setSupportingMembers(rs.getString("supporting_members"));
record.setFamilyLocationLkey(rs.getString("family_location_lkey"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApGeneralAssessment record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_general_assessment values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getEncounterKey());
ps.setString(4, record.getPositionStatusLkey());
ps.setString(5, record.getBodyMovementsLkey());
ps.setString(6, record.getLevelOfConsciousnessLkey());
ps.setString(7, record.getFacialExpressionLkey());
ps.setString(8, record.getSpeechLkey());
ps.setString(9, record.getMoodBehaviorLkey());
ps.setBoolean(10, record.getMemoryRecent());
ps.setBoolean(11, record.getMemoryRemote());
ps.setBoolean(12, record.getSignsOfAgitation());
ps.setBoolean(13, record.getSignsOfDepression());
ps.setBoolean(14, record.getSignsOfSuicidalIdeation());
ps.setBoolean(15, record.getSignsOfSubstanceUse());
ps.setString(16, record.getCreatedBy());
ps.setString(17, record.getUpdatedBy());
ps.setString(18, record.getDeletedBy());
ps.setBigDecimal(19, record.getCreatedAt());
ps.setBigDecimal(20, record.getUpdatedAt());
ps.setBigDecimal(21, record.getDeletedAt());
ps.setString(22, record.getCancellationReason());
ps.setString(23, record.getStatusLkey());
ps.setString(24, record.getLivingCondition());
ps.setBoolean(25, record.getPatientNeedHelp());
ps.setString(26, record.getSupportingMembers());
ps.setString(27, record.getFamilyLocationLkey());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApGeneralAssessmentEntity entity, String lang) {
        Class<?> myClass = ApGeneralAssessmentEntity.class;
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
public void translateObject(ApGeneralAssessmentEntity entity, String lang) {
        ApGeneralAssessmentEntity translated = (ApGeneralAssessmentEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}