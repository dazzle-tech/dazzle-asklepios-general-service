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
import com.asklepios.backend_service.model.generated.pojo.ApAudiometryPuretone;
import com.asklepios.backend_service.model.generated.entity.ApAudiometryPuretoneEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApAudiometryPuretoneDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApAudiometryPuretone getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_audiometry_puretone where key = '"+key+"'");) {
ApAudiometryPuretone record = new ApAudiometryPuretone();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setTestEnvironment(rs.getString("test_environment"));
record.setTestReason(rs.getString("test_reason"));
record.setEarExamFindingsLkey(rs.getString("ear_exam_findings_lkey"));
record.setAirConductionFrequenciesLeft(rs.getBigDecimal("air_conduction_frequencies_left"));
record.setAirConductionFrequenciesRight(rs.getBigDecimal("air_conduction_frequencies_right"));
record.setHearingThresholdsLeft(rs.getBigDecimal("hearing_thresholds_left"));
record.setHearingThresholdsRight(rs.getBigDecimal("hearing_thresholds_right"));
record.setBoneConductionFrequenciesLeft(rs.getBigDecimal("bone_conduction_frequencies_left"));
record.setBoneConductionFrequenciesRight(rs.getBigDecimal("bone_conduction_frequencies_right"));
record.setBoneConductionThresholdsLeft(rs.getBigDecimal("bone_conduction_thresholds_left"));
record.setBoneConductionThresholdsRight(rs.getBigDecimal("bone_conduction_thresholds_right"));
record.setMaskedUsed(rs.getBoolean("masked_used"));
record.setHearingLossTypeLkey(rs.getString("hearing_loss_type_lkey"));
record.setHearingLossDegreeLkey(rs.getString("hearing_loss_degree_lkey"));
record.setRecommendations(rs.getString("recommendations"));
record.setAdditionalNotes(rs.getString("additional_notes"));
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
public void updateRecord(ApAudiometryPuretone record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_audiometry_puretone set key = ?, patient_key = ?, encounter_key = ?, test_environment = ?, test_reason = ?, ear_exam_findings_lkey = ?, air_conduction_frequencies_left = ?, air_conduction_frequencies_right = ?, hearing_thresholds_left = ?, hearing_thresholds_right = ?, bone_conduction_frequencies_left = ?, bone_conduction_frequencies_right = ?, bone_conduction_thresholds_left = ?, bone_conduction_thresholds_right = ?, masked_used = ?, hearing_loss_type_lkey = ?, hearing_loss_degree_lkey = ?, recommendations = ?, additional_notes = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, cancellation_reason = ?, status_lkey = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getEncounterKey());
ps.setString(4, record.getTestEnvironment());
ps.setString(5, record.getTestReason());
ps.setString(6, record.getEarExamFindingsLkey());
ps.setBigDecimal(7, record.getAirConductionFrequenciesLeft());
ps.setBigDecimal(8, record.getAirConductionFrequenciesRight());
ps.setBigDecimal(9, record.getHearingThresholdsLeft());
ps.setBigDecimal(10, record.getHearingThresholdsRight());
ps.setBigDecimal(11, record.getBoneConductionFrequenciesLeft());
ps.setBigDecimal(12, record.getBoneConductionFrequenciesRight());
ps.setBigDecimal(13, record.getBoneConductionThresholdsLeft());
ps.setBigDecimal(14, record.getBoneConductionThresholdsRight());
ps.setBoolean(15, record.getMaskedUsed());
ps.setString(16, record.getHearingLossTypeLkey());
ps.setString(17, record.getHearingLossDegreeLkey());
ps.setString(18, record.getRecommendations());
ps.setString(19, record.getAdditionalNotes());
ps.setString(20, record.getCreatedBy());
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
public void deleteRecord(ApAudiometryPuretone record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_audiometry_puretone set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApAudiometryPuretone> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_audiometry_puretone where "+ where);) {
List<ApAudiometryPuretone> list = new ArrayList<ApAudiometryPuretone>();
while(rs.next()){
ApAudiometryPuretone record = new ApAudiometryPuretone();
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setTestEnvironment(rs.getString("test_environment"));
record.setTestReason(rs.getString("test_reason"));
record.setEarExamFindingsLkey(rs.getString("ear_exam_findings_lkey"));
record.setAirConductionFrequenciesLeft(rs.getBigDecimal("air_conduction_frequencies_left"));
record.setAirConductionFrequenciesRight(rs.getBigDecimal("air_conduction_frequencies_right"));
record.setHearingThresholdsLeft(rs.getBigDecimal("hearing_thresholds_left"));
record.setHearingThresholdsRight(rs.getBigDecimal("hearing_thresholds_right"));
record.setBoneConductionFrequenciesLeft(rs.getBigDecimal("bone_conduction_frequencies_left"));
record.setBoneConductionFrequenciesRight(rs.getBigDecimal("bone_conduction_frequencies_right"));
record.setBoneConductionThresholdsLeft(rs.getBigDecimal("bone_conduction_thresholds_left"));
record.setBoneConductionThresholdsRight(rs.getBigDecimal("bone_conduction_thresholds_right"));
record.setMaskedUsed(rs.getBoolean("masked_used"));
record.setHearingLossTypeLkey(rs.getString("hearing_loss_type_lkey"));
record.setHearingLossDegreeLkey(rs.getString("hearing_loss_degree_lkey"));
record.setRecommendations(rs.getString("recommendations"));
record.setAdditionalNotes(rs.getString("additional_notes"));
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
public String saveRecord(ApAudiometryPuretone record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_audiometry_puretone values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getEncounterKey());
ps.setString(4, record.getTestEnvironment());
ps.setString(5, record.getTestReason());
ps.setString(6, record.getEarExamFindingsLkey());
ps.setBigDecimal(7, record.getAirConductionFrequenciesLeft());
ps.setBigDecimal(8, record.getAirConductionFrequenciesRight());
ps.setBigDecimal(9, record.getHearingThresholdsLeft());
ps.setBigDecimal(10, record.getHearingThresholdsRight());
ps.setBigDecimal(11, record.getBoneConductionFrequenciesLeft());
ps.setBigDecimal(12, record.getBoneConductionFrequenciesRight());
ps.setBigDecimal(13, record.getBoneConductionThresholdsLeft());
ps.setBigDecimal(14, record.getBoneConductionThresholdsRight());
ps.setBoolean(15, record.getMaskedUsed());
ps.setString(16, record.getHearingLossTypeLkey());
ps.setString(17, record.getHearingLossDegreeLkey());
ps.setString(18, record.getRecommendations());
ps.setString(19, record.getAdditionalNotes());
ps.setString(20, record.getCreatedBy());
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
public void populateLovFields(ApAudiometryPuretoneEntity entity, String lang) {
        Class<?> myClass = ApAudiometryPuretoneEntity.class;
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
public void translateObject(ApAudiometryPuretoneEntity entity, String lang) {
        ApAudiometryPuretoneEntity translated = (ApAudiometryPuretoneEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}