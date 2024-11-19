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
import com.asklepios.backend_service.model.generated.pojo.ApEncounter;
import com.asklepios.backend_service.model.generated.entity.ApEncounterEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApEncounterDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApEncounter getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_encounter where key = '"+key+"'");) {
ApEncounter record = new ApEncounter();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setPatientFullName(rs.getString("patient_full_name"));
record.setPatientAge(rs.getString("patient_age"));
record.setEncounterStatusLkey(rs.getString("encounter_status_lkey"));
record.setEncounterClassLkey(rs.getString("encounter_class_lkey"));
record.setEncounterPriorityLkey(rs.getString("encounter_priority_lkey"));
record.setEncounterTypeLkey(rs.getString("encounter_type_lkey"));
record.setServiceTypeLkey(rs.getString("service_type_lkey"));
record.setPatientStatusLkey(rs.getString("patient_status_lkey"));
record.setEpisodeCareKey(rs.getString("episode_care_key"));
record.setBasedOnLkey(rs.getString("based_on_lkey"));
record.setBasedOnKey(rs.getString("based_on_key"));
record.setPartOfEncounterKey(rs.getString("part_of_encounter_key"));
record.setAttendingPhysicianKey(rs.getString("attending_physician_key"));
record.setResponsiblePhysicianKey(rs.getString("responsible_physician_key"));
record.setFacilityKey(rs.getString("facility_key"));
record.setAppointmentKey(rs.getString("appointment_key"));
record.setVirtualService(rs.getBoolean("virtual_service"));
record.setPlannedStartDate(rs.getDate("planned_start_date"));
record.setPlannedEndDate(rs.getDate("planned_end_date"));
record.setActualStartDate(rs.getDate("actual_start_date"));
record.setActualEndDate(rs.getDate("actual_end_date"));
record.setActualLengthHrs(rs.getBigDecimal("actual_length_hrs"));
record.setReasonLkey(rs.getString("reason_lkey"));
record.setPrimaryDiagnoseKey(rs.getString("primary_diagnose_key"));
record.setDietPreferenceLkey(rs.getString("diet_preference_lkey"));
record.setDietPreferenceText(rs.getString("diet_preference_text"));
record.setValuableItemsText(rs.getString("valuable_items_text"));
record.setSpecialArrangementLkey(rs.getString("special_arrangement_lkey"));
record.setSpecialArrangementText(rs.getString("special_arrangement_text"));
record.setSpecialCourtesyLkey(rs.getString("special_courtesy_lkey"));
record.setAdmissionOrigin(rs.getString("admission_origin"));
record.setAdmissionSource(rs.getString("admission_source"));
record.setReadmission(rs.getBoolean("readmission"));
record.setDischargeDestination(rs.getString("discharge_destination"));
record.setDischargeDisposition(rs.getString("discharge_disposition"));
record.setLocationTypeLkey(rs.getString("location_type_lkey"));
record.setLocationKey(rs.getString("location_key"));
record.setFollowUpEncounterKey(rs.getString("follow_up_encounter_key"));
record.setQueueNumber(rs.getBigDecimal("queue_number"));
record.setBillingAccountKey(rs.getString("billing_account_key"));
record.setPaymentTypeLkey(rs.getString("payment_type_lkey"));
record.setPayerTypeLkey(rs.getString("payer_type_lkey"));
record.setPayerKey(rs.getString("payer_key"));
record.setInsurancePlan(rs.getString("insurance_plan"));
record.setPayerMemberId(rs.getString("payer_member_id"));
record.setReferralNumber(rs.getString("referral_number"));
record.setAccessLevel(rs.getBigDecimal("access_level"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setDepartmentKey(rs.getString("department_key"));
record.setDischargeTypeLkey(rs.getString("discharge_type_lkey"));
record.setActualLengthMinutes(rs.getBigDecimal("actual_length_minutes"));
record.setChiefComplaint(rs.getString("chief_complaint"));
record.setHpiSummery(rs.getString("hpi_summery"));
record.setHpiKey(rs.getString("hpi_key"));
record.setPastMedicalHistorySummery(rs.getString("past_medical_history_summery"));
record.setPastMedicalHistoryKey(rs.getString("past_medical_history_key"));
record.setRosSummery(rs.getString("ros_summery"));
record.setRosKey(rs.getString("ros_key"));
record.setAssessmentSummery(rs.getString("assessment_summery"));
record.setAssessmentKey(rs.getString("assessment_key"));
record.setPhysicalExamSummery(rs.getString("physical_exam_summery"));
record.setPhysicalExamSummeryKey(rs.getString("physical_exam_summery_key"));
record.setProgressNote(rs.getString("progress_note"));
record.setDischargeNote(rs.getString("discharge_note"));
record.setDischargeSummery(rs.getString("discharge_summery"));
record.setVisitId(rs.getString("visit_id"));
record.setEncounterNotes(rs.getString("encounter_notes"));
record.setSourceName(rs.getString("source_name"));
record.setPhysicalExamNote(rs.getString("physical_exam_note"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApEncounter record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_encounter set key = ?, patient_key = ?, patient_full_name = ?, patient_age = ?, encounter_status_lkey = ?, encounter_class_lkey = ?, encounter_priority_lkey = ?, encounter_type_lkey = ?, service_type_lkey = ?, patient_status_lkey = ?, episode_care_key = ?, based_on_lkey = ?, based_on_key = ?, part_of_encounter_key = ?, attending_physician_key = ?, responsible_physician_key = ?, facility_key = ?, appointment_key = ?, virtual_service = ?, planned_start_date = ?, planned_end_date = ?, actual_start_date = ?, actual_end_date = ?, actual_length_hrs = ?, reason_lkey = ?, primary_diagnose_key = ?, diet_preference_lkey = ?, diet_preference_text = ?, valuable_items_text = ?, special_arrangement_lkey = ?, special_arrangement_text = ?, special_courtesy_lkey = ?, admission_origin = ?, admission_source = ?, readmission = ?, discharge_destination = ?, discharge_disposition = ?, location_type_lkey = ?, location_key = ?, follow_up_encounter_key = ?, queue_number = ?, billing_account_key = ?, payment_type_lkey = ?, payer_type_lkey = ?, payer_key = ?, insurance_plan = ?, payer_member_id = ?, referral_number = ?, access_level = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ?, department_key = ?, discharge_type_lkey = ?, actual_length_minutes = ?, chief_complaint = ?, hpi_summery = ?, hpi_key = ?, past_medical_history_summery = ?, past_medical_history_key = ?, ros_summery = ?, ros_key = ?, assessment_summery = ?, assessment_key = ?, physical_exam_summery = ?, physical_exam_summery_key = ?, progress_note = ?, discharge_note = ?, discharge_summery = ?, visit_id = ?, encounter_notes = ?, source_name = ?, physical_exam_note = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getPatientFullName());
ps.setString(4, record.getPatientAge());
ps.setString(5, record.getEncounterStatusLkey());
ps.setString(6, record.getEncounterClassLkey());
ps.setString(7, record.getEncounterPriorityLkey());
ps.setString(8, record.getEncounterTypeLkey());
ps.setString(9, record.getServiceTypeLkey());
ps.setString(10, record.getPatientStatusLkey());
ps.setString(11, record.getEpisodeCareKey());
ps.setString(12, record.getBasedOnLkey());
ps.setString(13, record.getBasedOnKey());
ps.setString(14, record.getPartOfEncounterKey());
ps.setString(15, record.getAttendingPhysicianKey());
ps.setString(16, record.getResponsiblePhysicianKey());
ps.setString(17, record.getFacilityKey());
ps.setString(18, record.getAppointmentKey());
ps.setBoolean(19, record.getVirtualService());
if (record.getPlannedStartDate() != null) ps.setDate(20, new java.sql.Date(record.getPlannedStartDate().getTime()));
else ps.setDate(20, null); 
if (record.getPlannedEndDate() != null) ps.setDate(21, new java.sql.Date(record.getPlannedEndDate().getTime()));
else ps.setDate(21, null); 
if (record.getActualStartDate() != null) ps.setDate(22, new java.sql.Date(record.getActualStartDate().getTime()));
else ps.setDate(22, null); 
if (record.getActualEndDate() != null) ps.setDate(23, new java.sql.Date(record.getActualEndDate().getTime()));
else ps.setDate(23, null); 
ps.setBigDecimal(24, record.getActualLengthHrs());
ps.setString(25, record.getReasonLkey());
ps.setString(26, record.getPrimaryDiagnoseKey());
ps.setString(27, record.getDietPreferenceLkey());
ps.setString(28, record.getDietPreferenceText());
ps.setString(29, record.getValuableItemsText());
ps.setString(30, record.getSpecialArrangementLkey());
ps.setString(31, record.getSpecialArrangementText());
ps.setString(32, record.getSpecialCourtesyLkey());
ps.setString(33, record.getAdmissionOrigin());
ps.setString(34, record.getAdmissionSource());
ps.setBoolean(35, record.getReadmission());
ps.setString(36, record.getDischargeDestination());
ps.setString(37, record.getDischargeDisposition());
ps.setString(38, record.getLocationTypeLkey());
ps.setString(39, record.getLocationKey());
ps.setString(40, record.getFollowUpEncounterKey());
ps.setBigDecimal(41, record.getQueueNumber());
ps.setString(42, record.getBillingAccountKey());
ps.setString(43, record.getPaymentTypeLkey());
ps.setString(44, record.getPayerTypeLkey());
ps.setString(45, record.getPayerKey());
ps.setString(46, record.getInsurancePlan());
ps.setString(47, record.getPayerMemberId());
ps.setString(48, record.getReferralNumber());
ps.setBigDecimal(49, record.getAccessLevel());
ps.setString(50, record.getCreatedBy());
ps.setString(51, record.getUpdatedBy());
ps.setString(52, record.getDeletedBy());
ps.setBigDecimal(53, record.getCreatedAt());
ps.setBigDecimal(54, record.getUpdatedAt());
ps.setBigDecimal(55, record.getDeletedAt());
ps.setBoolean(56, record.getIsValid());
ps.setString(57, record.getDepartmentKey());
ps.setString(58, record.getDischargeTypeLkey());
ps.setBigDecimal(59, record.getActualLengthMinutes());
ps.setString(60, record.getChiefComplaint());
ps.setString(61, record.getHpiSummery());
ps.setString(62, record.getHpiKey());
ps.setString(63, record.getPastMedicalHistorySummery());
ps.setString(64, record.getPastMedicalHistoryKey());
ps.setString(65, record.getRosSummery());
ps.setString(66, record.getRosKey());
ps.setString(67, record.getAssessmentSummery());
ps.setString(68, record.getAssessmentKey());
ps.setString(69, record.getPhysicalExamSummery());
ps.setString(70, record.getPhysicalExamSummeryKey());
ps.setString(71, record.getProgressNote());
ps.setString(72, record.getDischargeNote());
ps.setString(73, record.getDischargeSummery());
ps.setString(74, record.getVisitId());
ps.setString(75, record.getEncounterNotes());
ps.setString(76, record.getSourceName());
ps.setString(77, record.getPhysicalExamNote());
ps.setString(78, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApEncounter record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_encounter set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApEncounter> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_encounter where "+ where);) {
List<ApEncounter> list = new ArrayList<ApEncounter>();
while(rs.next()){
ApEncounter record = new ApEncounter();
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setPatientFullName(rs.getString("patient_full_name"));
record.setPatientAge(rs.getString("patient_age"));
record.setEncounterStatusLkey(rs.getString("encounter_status_lkey"));
record.setEncounterClassLkey(rs.getString("encounter_class_lkey"));
record.setEncounterPriorityLkey(rs.getString("encounter_priority_lkey"));
record.setEncounterTypeLkey(rs.getString("encounter_type_lkey"));
record.setServiceTypeLkey(rs.getString("service_type_lkey"));
record.setPatientStatusLkey(rs.getString("patient_status_lkey"));
record.setEpisodeCareKey(rs.getString("episode_care_key"));
record.setBasedOnLkey(rs.getString("based_on_lkey"));
record.setBasedOnKey(rs.getString("based_on_key"));
record.setPartOfEncounterKey(rs.getString("part_of_encounter_key"));
record.setAttendingPhysicianKey(rs.getString("attending_physician_key"));
record.setResponsiblePhysicianKey(rs.getString("responsible_physician_key"));
record.setFacilityKey(rs.getString("facility_key"));
record.setAppointmentKey(rs.getString("appointment_key"));
record.setVirtualService(rs.getBoolean("virtual_service"));
record.setPlannedStartDate(rs.getDate("planned_start_date"));
record.setPlannedEndDate(rs.getDate("planned_end_date"));
record.setActualStartDate(rs.getDate("actual_start_date"));
record.setActualEndDate(rs.getDate("actual_end_date"));
record.setActualLengthHrs(rs.getBigDecimal("actual_length_hrs"));
record.setReasonLkey(rs.getString("reason_lkey"));
record.setPrimaryDiagnoseKey(rs.getString("primary_diagnose_key"));
record.setDietPreferenceLkey(rs.getString("diet_preference_lkey"));
record.setDietPreferenceText(rs.getString("diet_preference_text"));
record.setValuableItemsText(rs.getString("valuable_items_text"));
record.setSpecialArrangementLkey(rs.getString("special_arrangement_lkey"));
record.setSpecialArrangementText(rs.getString("special_arrangement_text"));
record.setSpecialCourtesyLkey(rs.getString("special_courtesy_lkey"));
record.setAdmissionOrigin(rs.getString("admission_origin"));
record.setAdmissionSource(rs.getString("admission_source"));
record.setReadmission(rs.getBoolean("readmission"));
record.setDischargeDestination(rs.getString("discharge_destination"));
record.setDischargeDisposition(rs.getString("discharge_disposition"));
record.setLocationTypeLkey(rs.getString("location_type_lkey"));
record.setLocationKey(rs.getString("location_key"));
record.setFollowUpEncounterKey(rs.getString("follow_up_encounter_key"));
record.setQueueNumber(rs.getBigDecimal("queue_number"));
record.setBillingAccountKey(rs.getString("billing_account_key"));
record.setPaymentTypeLkey(rs.getString("payment_type_lkey"));
record.setPayerTypeLkey(rs.getString("payer_type_lkey"));
record.setPayerKey(rs.getString("payer_key"));
record.setInsurancePlan(rs.getString("insurance_plan"));
record.setPayerMemberId(rs.getString("payer_member_id"));
record.setReferralNumber(rs.getString("referral_number"));
record.setAccessLevel(rs.getBigDecimal("access_level"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setDepartmentKey(rs.getString("department_key"));
record.setDischargeTypeLkey(rs.getString("discharge_type_lkey"));
record.setActualLengthMinutes(rs.getBigDecimal("actual_length_minutes"));
record.setChiefComplaint(rs.getString("chief_complaint"));
record.setHpiSummery(rs.getString("hpi_summery"));
record.setHpiKey(rs.getString("hpi_key"));
record.setPastMedicalHistorySummery(rs.getString("past_medical_history_summery"));
record.setPastMedicalHistoryKey(rs.getString("past_medical_history_key"));
record.setRosSummery(rs.getString("ros_summery"));
record.setRosKey(rs.getString("ros_key"));
record.setAssessmentSummery(rs.getString("assessment_summery"));
record.setAssessmentKey(rs.getString("assessment_key"));
record.setPhysicalExamSummery(rs.getString("physical_exam_summery"));
record.setPhysicalExamSummeryKey(rs.getString("physical_exam_summery_key"));
record.setProgressNote(rs.getString("progress_note"));
record.setDischargeNote(rs.getString("discharge_note"));
record.setDischargeSummery(rs.getString("discharge_summery"));
record.setVisitId(rs.getString("visit_id"));
record.setEncounterNotes(rs.getString("encounter_notes"));
record.setSourceName(rs.getString("source_name"));
record.setPhysicalExamNote(rs.getString("physical_exam_note"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApEncounter record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_encounter values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getPatientFullName());
ps.setString(4, record.getPatientAge());
ps.setString(5, record.getEncounterStatusLkey());
ps.setString(6, record.getEncounterClassLkey());
ps.setString(7, record.getEncounterPriorityLkey());
ps.setString(8, record.getEncounterTypeLkey());
ps.setString(9, record.getServiceTypeLkey());
ps.setString(10, record.getPatientStatusLkey());
ps.setString(11, record.getEpisodeCareKey());
ps.setString(12, record.getBasedOnLkey());
ps.setString(13, record.getBasedOnKey());
ps.setString(14, record.getPartOfEncounterKey());
ps.setString(15, record.getAttendingPhysicianKey());
ps.setString(16, record.getResponsiblePhysicianKey());
ps.setString(17, record.getFacilityKey());
ps.setString(18, record.getAppointmentKey());
ps.setBoolean(19, record.getVirtualService());
if (record.getPlannedStartDate() != null) ps.setDate(20, new java.sql.Date(record.getPlannedStartDate().getTime()));
else ps.setDate(20, null); 
if (record.getPlannedEndDate() != null) ps.setDate(21, new java.sql.Date(record.getPlannedEndDate().getTime()));
else ps.setDate(21, null); 
if (record.getActualStartDate() != null) ps.setDate(22, new java.sql.Date(record.getActualStartDate().getTime()));
else ps.setDate(22, null); 
if (record.getActualEndDate() != null) ps.setDate(23, new java.sql.Date(record.getActualEndDate().getTime()));
else ps.setDate(23, null); 
ps.setBigDecimal(24, record.getActualLengthHrs());
ps.setString(25, record.getReasonLkey());
ps.setString(26, record.getPrimaryDiagnoseKey());
ps.setString(27, record.getDietPreferenceLkey());
ps.setString(28, record.getDietPreferenceText());
ps.setString(29, record.getValuableItemsText());
ps.setString(30, record.getSpecialArrangementLkey());
ps.setString(31, record.getSpecialArrangementText());
ps.setString(32, record.getSpecialCourtesyLkey());
ps.setString(33, record.getAdmissionOrigin());
ps.setString(34, record.getAdmissionSource());
ps.setBoolean(35, record.getReadmission());
ps.setString(36, record.getDischargeDestination());
ps.setString(37, record.getDischargeDisposition());
ps.setString(38, record.getLocationTypeLkey());
ps.setString(39, record.getLocationKey());
ps.setString(40, record.getFollowUpEncounterKey());
ps.setBigDecimal(41, record.getQueueNumber());
ps.setString(42, record.getBillingAccountKey());
ps.setString(43, record.getPaymentTypeLkey());
ps.setString(44, record.getPayerTypeLkey());
ps.setString(45, record.getPayerKey());
ps.setString(46, record.getInsurancePlan());
ps.setString(47, record.getPayerMemberId());
ps.setString(48, record.getReferralNumber());
ps.setBigDecimal(49, record.getAccessLevel());
ps.setString(50, record.getCreatedBy());
ps.setString(51, record.getUpdatedBy());
ps.setString(52, record.getDeletedBy());
ps.setBigDecimal(53, record.getCreatedAt());
ps.setBigDecimal(54, record.getUpdatedAt());
ps.setBigDecimal(55, record.getDeletedAt());
ps.setBoolean(56, record.getIsValid());
ps.setString(57, record.getDepartmentKey());
ps.setString(58, record.getDischargeTypeLkey());
ps.setBigDecimal(59, record.getActualLengthMinutes());
ps.setString(60, record.getChiefComplaint());
ps.setString(61, record.getHpiSummery());
ps.setString(62, record.getHpiKey());
ps.setString(63, record.getPastMedicalHistorySummery());
ps.setString(64, record.getPastMedicalHistoryKey());
ps.setString(65, record.getRosSummery());
ps.setString(66, record.getRosKey());
ps.setString(67, record.getAssessmentSummery());
ps.setString(68, record.getAssessmentKey());
ps.setString(69, record.getPhysicalExamSummery());
ps.setString(70, record.getPhysicalExamSummeryKey());
ps.setString(71, record.getProgressNote());
ps.setString(72, record.getDischargeNote());
ps.setString(73, record.getDischargeSummery());
ps.setString(74, record.getVisitId());
ps.setString(75, record.getEncounterNotes());
ps.setString(76, record.getSourceName());
ps.setString(77, record.getPhysicalExamNote());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApEncounterEntity entity, String lang) {
        Class<?> myClass = ApEncounterEntity.class;
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
public void translateObject(ApEncounterEntity entity, String lang) {
        ApEncounterEntity translated = (ApEncounterEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}