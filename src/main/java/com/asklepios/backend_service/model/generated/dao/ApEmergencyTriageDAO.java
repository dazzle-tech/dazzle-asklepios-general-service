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
import com.asklepios.backend_service.model.generated.pojo.ApEmergencyTriage;
import com.asklepios.backend_service.model.generated.entity.ApEmergencyTriageEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApEmergencyTriageDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApEmergencyTriage getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_emergency_triage where key = '"+key+"'");) {
ApEmergencyTriage record = new ApEmergencyTriage();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setEmergencyLevelLkey(rs.getString("emergency_level_lkey"));
record.setRightEyeLightResponse(rs.getBoolean("right_eye_light_response"));
record.setRightEyePupilSizeLkey(rs.getString("right_eye_pupil_size_lkey"));
record.setLeftEyeLightResponse(rs.getBoolean("left_eye_light_response"));
record.setLeftEyePupilSizeLkey(rs.getString("left_eye_pupil_size_lkey"));
record.setIsPregnancy(rs.getBoolean("is_pregnancy"));
record.setHistoryOfPresentIllness(rs.getString("history_of_present_illness"));
record.setAdditionalNotes(rs.getString("additional_notes"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setLifeSavingLkey(rs.getString("life_saving_lkey"));
record.setUnresponsiveLkey(rs.getString("unresponsive_lkey"));
record.setHighRiskLkey(rs.getString("high_risk_lkey"));
record.setAvpuScaleLkey(rs.getString("avpu_scale_lkey"));
record.setPainScoreLkey(rs.getString("pain_score_lkey"));
record.setLabsLkey(rs.getString("labs_lkey"));
record.setImagingLkey(rs.getString("imaging_lkey"));
record.setIvFluidsLkey(rs.getString("iv_fluids_lkey"));
record.setMedicationLkey(rs.getString("medication_lkey"));
record.setEcgLkey(rs.getString("ecg_lkey"));
record.setConsultationLkey(rs.getString("consultation_lkey"));
record.setDestinationLkey(rs.getString("destination_lkey"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApEmergencyTriage record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_emergency_triage set key = ?, patient_key = ?, encounter_key = ?, emergency_level_lkey = ?, right_eye_light_response = ?, right_eye_pupil_size_lkey = ?, left_eye_light_response = ?, left_eye_pupil_size_lkey = ?, is_pregnancy = ?, history_of_present_illness = ?, additional_notes = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, life_saving_lkey = ?, unresponsive_lkey = ?, high_risk_lkey = ?, avpu_scale_lkey = ?, pain_score_lkey = ?, labs_lkey = ?, imaging_lkey = ?, iv_fluids_lkey = ?, medication_lkey = ?, ecg_lkey = ?, consultation_lkey = ?, destination_lkey = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getEncounterKey());
ps.setString(4, record.getEmergencyLevelLkey());
ps.setBoolean(5, record.getRightEyeLightResponse());
ps.setString(6, record.getRightEyePupilSizeLkey());
ps.setBoolean(7, record.getLeftEyeLightResponse());
ps.setString(8, record.getLeftEyePupilSizeLkey());
ps.setBoolean(9, record.getIsPregnancy());
ps.setString(10, record.getHistoryOfPresentIllness());
ps.setString(11, record.getAdditionalNotes());
ps.setString(12, record.getCreatedBy());
ps.setString(13, record.getUpdatedBy());
ps.setString(14, record.getDeletedBy());
ps.setBigDecimal(15, record.getCreatedAt());
ps.setBigDecimal(16, record.getUpdatedAt());
ps.setBigDecimal(17, record.getDeletedAt());
ps.setString(18, record.getLifeSavingLkey());
ps.setString(19, record.getUnresponsiveLkey());
ps.setString(20, record.getHighRiskLkey());
ps.setString(21, record.getAvpuScaleLkey());
ps.setString(22, record.getPainScoreLkey());
ps.setString(23, record.getLabsLkey());
ps.setString(24, record.getImagingLkey());
ps.setString(25, record.getIvFluidsLkey());
ps.setString(26, record.getMedicationLkey());
ps.setString(27, record.getEcgLkey());
ps.setString(28, record.getConsultationLkey());
ps.setString(29, record.getDestinationLkey());
ps.setString(30, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApEmergencyTriage record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_emergency_triage set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApEmergencyTriage> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_emergency_triage where "+ where);) {
List<ApEmergencyTriage> list = new ArrayList<ApEmergencyTriage>();
while(rs.next()){
ApEmergencyTriage record = new ApEmergencyTriage();
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setEmergencyLevelLkey(rs.getString("emergency_level_lkey"));
record.setRightEyeLightResponse(rs.getBoolean("right_eye_light_response"));
record.setRightEyePupilSizeLkey(rs.getString("right_eye_pupil_size_lkey"));
record.setLeftEyeLightResponse(rs.getBoolean("left_eye_light_response"));
record.setLeftEyePupilSizeLkey(rs.getString("left_eye_pupil_size_lkey"));
record.setIsPregnancy(rs.getBoolean("is_pregnancy"));
record.setHistoryOfPresentIllness(rs.getString("history_of_present_illness"));
record.setAdditionalNotes(rs.getString("additional_notes"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setLifeSavingLkey(rs.getString("life_saving_lkey"));
record.setUnresponsiveLkey(rs.getString("unresponsive_lkey"));
record.setHighRiskLkey(rs.getString("high_risk_lkey"));
record.setAvpuScaleLkey(rs.getString("avpu_scale_lkey"));
record.setPainScoreLkey(rs.getString("pain_score_lkey"));
record.setLabsLkey(rs.getString("labs_lkey"));
record.setImagingLkey(rs.getString("imaging_lkey"));
record.setIvFluidsLkey(rs.getString("iv_fluids_lkey"));
record.setMedicationLkey(rs.getString("medication_lkey"));
record.setEcgLkey(rs.getString("ecg_lkey"));
record.setConsultationLkey(rs.getString("consultation_lkey"));
record.setDestinationLkey(rs.getString("destination_lkey"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApEmergencyTriage record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_emergency_triage values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getEncounterKey());
ps.setString(4, record.getEmergencyLevelLkey());
ps.setBoolean(5, record.getRightEyeLightResponse());
ps.setString(6, record.getRightEyePupilSizeLkey());
ps.setBoolean(7, record.getLeftEyeLightResponse());
ps.setString(8, record.getLeftEyePupilSizeLkey());
ps.setBoolean(9, record.getIsPregnancy());
ps.setString(10, record.getHistoryOfPresentIllness());
ps.setString(11, record.getAdditionalNotes());
ps.setString(12, record.getCreatedBy());
ps.setString(13, record.getUpdatedBy());
ps.setString(14, record.getDeletedBy());
ps.setBigDecimal(15, record.getCreatedAt());
ps.setBigDecimal(16, record.getUpdatedAt());
ps.setBigDecimal(17, record.getDeletedAt());
ps.setString(18, record.getLifeSavingLkey());
ps.setString(19, record.getUnresponsiveLkey());
ps.setString(20, record.getHighRiskLkey());
ps.setString(21, record.getAvpuScaleLkey());
ps.setString(22, record.getPainScoreLkey());
ps.setString(23, record.getLabsLkey());
ps.setString(24, record.getImagingLkey());
ps.setString(25, record.getIvFluidsLkey());
ps.setString(26, record.getMedicationLkey());
ps.setString(27, record.getEcgLkey());
ps.setString(28, record.getConsultationLkey());
ps.setString(29, record.getDestinationLkey());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApEmergencyTriageEntity entity, String lang) {
        Class<?> myClass = ApEmergencyTriageEntity.class;
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
public void translateObject(ApEmergencyTriageEntity entity, String lang) {
        ApEmergencyTriageEntity translated = (ApEmergencyTriageEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}