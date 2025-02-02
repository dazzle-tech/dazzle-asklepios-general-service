package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.ApEncounter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApPrescriptionDAO;

@Service
@Slf4j
public class ApPrescriptionService extends ApPrescriptionDAO implements Serializable {

    public ApEncounter getEncounter(String key) throws SQLException {
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
                record.setPlanInstructions(rs.getString("plan_instructions"));
                record.setVisitTypeLkey(rs.getString("visit_type_lkey"));
                record.setPhysicianKey(rs.getString("physician_key"));
            } else { record = null; }
            return record;
        }
    }
}