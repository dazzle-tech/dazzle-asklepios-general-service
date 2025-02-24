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
import com.asklepios.backend_service.model.generated.pojo.ApOptometricExam;
import com.asklepios.backend_service.model.generated.entity.ApOptometricExamEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApOptometricExamDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApOptometricExam getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_optometric_exam where key = '"+key+"'");) {
ApOptometricExam record = new ApOptometricExam();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setMedicalHistoryLkey(rs.getString("medical_history_lkey"));
record.setTestReason(rs.getString("test_reason"));
record.setPerformedWithLkey(rs.getString("performed_with_lkey"));
record.setDistanceAcuity(rs.getBigDecimal("distance_acuity"));
record.setRightEyeOd(rs.getBigDecimal("right_eye_od"));
record.setLeftEyeOd(rs.getBigDecimal("left_eye_od"));
record.setRightEyeOs(rs.getBigDecimal("right_eye_os"));
record.setLeftEyeOs(rs.getBigDecimal("left_eye_os"));
record.setNearAcuity(rs.getBigDecimal("near_acuity"));
record.setPinholeTestResultLkey(rs.getString("pinhole_test_result_lkey"));
record.setNumberOfPlatesTested(rs.getBigDecimal("number_of_plates_tested"));
record.setCorrectAnswersCount(rs.getBigDecimal("correct_answers_count"));
record.setDeficiencyTypeLkey(rs.getString("deficiency_type_lkey"));
record.setRightEyeSphere(rs.getBigDecimal("right_eye_sphere"));
record.setLeftEyeSphere(rs.getBigDecimal("left_eye_sphere"));
record.setRightCylinder(rs.getBigDecimal("right_cylinder"));
record.setLeftCylinder(rs.getBigDecimal("left_cylinder"));
record.setRightAxis(rs.getBigDecimal("right_axis"));
record.setLeftAxis(rs.getBigDecimal("left_axis"));
record.setRightEye(rs.getBigDecimal("right_eye"));
record.setLeftEye(rs.getBigDecimal("left_eye"));
record.setMeasurementMethod(rs.getString("measurement_method"));
record.setTimeOfMeasurement(rs.getBigDecimal("time_of_measurement"));
record.setCornealThickness(rs.getBigDecimal("corneal_thickness"));
record.setGlaucomaRiskAssessmentLkey(rs.getString("glaucoma_risk_assessment_lkey"));
record.setFundoscopySlitlampDone(rs.getBoolean("fundoscopy_slitlamp_done"));
record.setExamFindings(rs.getString("exam_findings"));
record.setVisionDiagnosis(rs.getString("vision_diagnosis"));
record.setColorVisionDiagnosis(rs.getString("color_vision_diagnosis"));
record.setRecommendations(rs.getString("recommendations"));
record.setAdditionalNotes(rs.getString("additional_notes"));
record.setFollowUpRequired(rs.getBoolean("follow_up_required"));
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
public void updateRecord(ApOptometricExam record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_optometric_exam set key = ?, patient_key = ?, encounter_key = ?, medical_history_lkey = ?, test_reason = ?, performed_with_lkey = ?, distance_acuity = ?, right_eye_od = ?, left_eye_od = ?, right_eye_os = ?, left_eye_os = ?, near_acuity = ?, pinhole_test_result_lkey = ?, number_of_plates_tested = ?, correct_answers_count = ?, deficiency_type_lkey = ?, right_eye_sphere = ?, left_eye_sphere = ?, right_cylinder = ?, left_cylinder = ?, right_axis = ?, left_axis = ?, right_eye = ?, left_eye = ?, measurement_method = ?, time_of_measurement = ?, corneal_thickness = ?, glaucoma_risk_assessment_lkey = ?, fundoscopy_slitlamp_done = ?, exam_findings = ?, vision_diagnosis = ?, color_vision_diagnosis = ?, recommendations = ?, additional_notes = ?, follow_up_required = ?, follow_up_date = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, cancellation_reason = ?, status_lkey = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getEncounterKey());
ps.setString(4, record.getMedicalHistoryLkey());
ps.setString(5, record.getTestReason());
ps.setString(6, record.getPerformedWithLkey());
ps.setBigDecimal(7, record.getDistanceAcuity());
ps.setBigDecimal(8, record.getRightEyeOd());
ps.setBigDecimal(9, record.getLeftEyeOd());
ps.setBigDecimal(10, record.getRightEyeOs());
ps.setBigDecimal(11, record.getLeftEyeOs());
ps.setBigDecimal(12, record.getNearAcuity());
ps.setString(13, record.getPinholeTestResultLkey());
ps.setBigDecimal(14, record.getNumberOfPlatesTested());
ps.setBigDecimal(15, record.getCorrectAnswersCount());
ps.setString(16, record.getDeficiencyTypeLkey());
ps.setBigDecimal(17, record.getRightEyeSphere());
ps.setBigDecimal(18, record.getLeftEyeSphere());
ps.setBigDecimal(19, record.getRightCylinder());
ps.setBigDecimal(20, record.getLeftCylinder());
ps.setBigDecimal(21, record.getRightAxis());
ps.setBigDecimal(22, record.getLeftAxis());
ps.setBigDecimal(23, record.getRightEye());
ps.setBigDecimal(24, record.getLeftEye());
ps.setString(25, record.getMeasurementMethod());
ps.setBigDecimal(26, record.getTimeOfMeasurement());
ps.setBigDecimal(27, record.getCornealThickness());
ps.setString(28, record.getGlaucomaRiskAssessmentLkey());
ps.setBoolean(29, record.getFundoscopySlitlampDone());
ps.setString(30, record.getExamFindings());
ps.setString(31, record.getVisionDiagnosis());
ps.setString(32, record.getColorVisionDiagnosis());
ps.setString(33, record.getRecommendations());
ps.setString(34, record.getAdditionalNotes());
ps.setBoolean(35, record.getFollowUpRequired());
ps.setBigDecimal(36, record.getFollowUpDate());
ps.setString(37, record.getCreatedBy());
ps.setString(38, record.getUpdatedBy());
ps.setString(39, record.getDeletedBy());
ps.setBigDecimal(40, record.getCreatedAt());
ps.setBigDecimal(41, record.getUpdatedAt());
ps.setBigDecimal(42, record.getDeletedAt());
ps.setString(43, record.getCancellationReason());
ps.setString(44, record.getStatusLkey());
ps.setString(45, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApOptometricExam record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_optometric_exam set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApOptometricExam> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_optometric_exam where "+ where);) {
List<ApOptometricExam> list = new ArrayList<ApOptometricExam>();
while(rs.next()){
ApOptometricExam record = new ApOptometricExam();
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setMedicalHistoryLkey(rs.getString("medical_history_lkey"));
record.setTestReason(rs.getString("test_reason"));
record.setPerformedWithLkey(rs.getString("performed_with_lkey"));
record.setDistanceAcuity(rs.getBigDecimal("distance_acuity"));
record.setRightEyeOd(rs.getBigDecimal("right_eye_od"));
record.setLeftEyeOd(rs.getBigDecimal("left_eye_od"));
record.setRightEyeOs(rs.getBigDecimal("right_eye_os"));
record.setLeftEyeOs(rs.getBigDecimal("left_eye_os"));
record.setNearAcuity(rs.getBigDecimal("near_acuity"));
record.setPinholeTestResultLkey(rs.getString("pinhole_test_result_lkey"));
record.setNumberOfPlatesTested(rs.getBigDecimal("number_of_plates_tested"));
record.setCorrectAnswersCount(rs.getBigDecimal("correct_answers_count"));
record.setDeficiencyTypeLkey(rs.getString("deficiency_type_lkey"));
record.setRightEyeSphere(rs.getBigDecimal("right_eye_sphere"));
record.setLeftEyeSphere(rs.getBigDecimal("left_eye_sphere"));
record.setRightCylinder(rs.getBigDecimal("right_cylinder"));
record.setLeftCylinder(rs.getBigDecimal("left_cylinder"));
record.setRightAxis(rs.getBigDecimal("right_axis"));
record.setLeftAxis(rs.getBigDecimal("left_axis"));
record.setRightEye(rs.getBigDecimal("right_eye"));
record.setLeftEye(rs.getBigDecimal("left_eye"));
record.setMeasurementMethod(rs.getString("measurement_method"));
record.setTimeOfMeasurement(rs.getBigDecimal("time_of_measurement"));
record.setCornealThickness(rs.getBigDecimal("corneal_thickness"));
record.setGlaucomaRiskAssessmentLkey(rs.getString("glaucoma_risk_assessment_lkey"));
record.setFundoscopySlitlampDone(rs.getBoolean("fundoscopy_slitlamp_done"));
record.setExamFindings(rs.getString("exam_findings"));
record.setVisionDiagnosis(rs.getString("vision_diagnosis"));
record.setColorVisionDiagnosis(rs.getString("color_vision_diagnosis"));
record.setRecommendations(rs.getString("recommendations"));
record.setAdditionalNotes(rs.getString("additional_notes"));
record.setFollowUpRequired(rs.getBoolean("follow_up_required"));
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
public String saveRecord(ApOptometricExam record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_optometric_exam values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getEncounterKey());
ps.setString(4, record.getMedicalHistoryLkey());
ps.setString(5, record.getTestReason());
ps.setString(6, record.getPerformedWithLkey());
ps.setBigDecimal(7, record.getDistanceAcuity());
ps.setBigDecimal(8, record.getRightEyeOd());
ps.setBigDecimal(9, record.getLeftEyeOd());
ps.setBigDecimal(10, record.getRightEyeOs());
ps.setBigDecimal(11, record.getLeftEyeOs());
ps.setBigDecimal(12, record.getNearAcuity());
ps.setString(13, record.getPinholeTestResultLkey());
ps.setBigDecimal(14, record.getNumberOfPlatesTested());
ps.setBigDecimal(15, record.getCorrectAnswersCount());
ps.setString(16, record.getDeficiencyTypeLkey());
ps.setBigDecimal(17, record.getRightEyeSphere());
ps.setBigDecimal(18, record.getLeftEyeSphere());
ps.setBigDecimal(19, record.getRightCylinder());
ps.setBigDecimal(20, record.getLeftCylinder());
ps.setBigDecimal(21, record.getRightAxis());
ps.setBigDecimal(22, record.getLeftAxis());
ps.setBigDecimal(23, record.getRightEye());
ps.setBigDecimal(24, record.getLeftEye());
ps.setString(25, record.getMeasurementMethod());
ps.setBigDecimal(26, record.getTimeOfMeasurement());
ps.setBigDecimal(27, record.getCornealThickness());
ps.setString(28, record.getGlaucomaRiskAssessmentLkey());
ps.setBoolean(29, record.getFundoscopySlitlampDone());
ps.setString(30, record.getExamFindings());
ps.setString(31, record.getVisionDiagnosis());
ps.setString(32, record.getColorVisionDiagnosis());
ps.setString(33, record.getRecommendations());
ps.setString(34, record.getAdditionalNotes());
ps.setBoolean(35, record.getFollowUpRequired());
ps.setBigDecimal(36, record.getFollowUpDate());
ps.setString(37, record.getCreatedBy());
ps.setString(38, record.getUpdatedBy());
ps.setString(39, record.getDeletedBy());
ps.setBigDecimal(40, record.getCreatedAt());
ps.setBigDecimal(41, record.getUpdatedAt());
ps.setBigDecimal(42, record.getDeletedAt());
ps.setString(43, record.getCancellationReason());
ps.setString(44, record.getStatusLkey());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApOptometricExamEntity entity, String lang) {
        Class<?> myClass = ApOptometricExamEntity.class;
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
public void translateObject(ApOptometricExamEntity entity, String lang) {
        ApOptometricExamEntity translated = (ApOptometricExamEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}