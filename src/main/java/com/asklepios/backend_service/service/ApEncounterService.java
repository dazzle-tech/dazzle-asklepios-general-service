package com.asklepios.backend_service.service;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.ApDiagnosticTest;
import com.asklepios.backend_service.model.generated.pojo.ApEncounter;
import com.asklepios.backend_service.model.generated.pojo.ApPatientDiagnose;
import com.asklepios.backend_service.model.generated.pojo.ApPatient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApEncounterDAO;

import java.util.ArrayList;
import java.lang.reflect.Field;





@Service
@Slf4j
public class ApEncounterService extends ApEncounterDAO implements Serializable {

    public String getDiagnosis(String visitKey) throws SQLException {
        String result = "";
        String query1 = "SELECT diagnose_code FROM ap_patient_diagnose WHERE visit_key = ?";
        String query2 = "SELECT icd_code,description FROM ap_icd_code WHERE key = ?";

        try (
                Connection con = DS.getConnection();
                PreparedStatement ps1 = con.prepareStatement(query1)
        ) {
            ps1.setString(1, visitKey);
            try (ResultSet rs1 = ps1.executeQuery()) {
                if (rs1.next()) {
                    String diagnoseCode = rs1.getString("diagnose_code");


                    try (PreparedStatement ps2 = con.prepareStatement(query2)) {
                        ps2.setString(1, diagnoseCode);
                        try (ResultSet rs2 = ps2.executeQuery()) {
                            if (rs2.next()) {
                                result = rs2.getString("icd_code")+","+rs2.getString("description");
                            } else {
                                result = "No icd_code";
                            }
                        }
                    }
                } else {
                    result = "No diagnose code found";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return result;

    }
    public boolean getHasOrder(String visitKey) throws SQLException {
        String query = "SELECT COUNT(visit_key) AS count FROM ap_patient_encounter_order WHERE visit_key = ?";

        try (
                Connection con = DS.getConnection();
                PreparedStatement ps = con.prepareStatement(query)
        ) {
            ps.setString(1, visitKey);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt("count");
                    return count > 0;
                }
            }
        } catch (SQLException e) {

            e.printStackTrace();
            throw e;
        }
        return false;
    }
    public boolean getHasPrescription(String visitKey) throws SQLException {
        String query = "SELECT COUNT(visit_key) AS count FROM ap_prescription_medications WHERE visit_key = ?";

        try (
                Connection con = DS.getConnection();
                PreparedStatement ps = con.prepareStatement(query)
        ) {
            ps.setString(1, visitKey);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt("count");
                    return count > 0;
                }
            }
        } catch (SQLException e) {

            e.printStackTrace();
            throw e;
        }
        return false;
    }

    public boolean getHasAllergy(String visitKey) throws SQLException {
        String query = "SELECT COUNT(visit_key) AS count FROM ap_visit_allergies WHERE visit_key = ?";

        try (
                Connection con = DS.getConnection();
                PreparedStatement ps = con.prepareStatement(query)
        ) {
            ps.setString(1, visitKey);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt("count");
                    return count > 0;
                }
            }
        } catch (SQLException e) {

            e.printStackTrace();
            throw e;
        }
        return false;
    }
    public boolean getHasObservation(String visitKey) throws SQLException {
        String query = "SELECT SUM(count) AS total_count " +
                "FROM ( " +
                "         SELECT COUNT(*) AS count " +
                "         FROM ap_visit_allergies " +
                "         WHERE visit_key = ? " +
                "         UNION ALL " +
                "         SELECT COUNT(*) AS count " +
                "         FROM ap_visit_warning " +
                "         WHERE visit_key = ? " +
                "         UNION ALL " +
                "         SELECT COUNT(*) AS count " +
                "         FROM ap_patient_observation_summary " +
                "         WHERE visit_key = ? " +
                "         UNION ALL " +
                "         SELECT COUNT(*) AS count " +
                "         FROM ap_encounter_vaccination " +
                "         WHERE encounter_key = ? " +
                "     ) AS combined_counts";

        try (
                Connection con = DS.getConnection();
                PreparedStatement ps = con.prepareStatement(query)
        ) {
            ps.setString(1, visitKey);
            ps.setString(2, visitKey);
            ps.setString(3, visitKey);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt("total_count");
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return false;
    }









    public List<ApEncounter> getListWithDepartmentName(String where) throws SQLException {
        if (where == null || where.isEmpty()) where = "1=1";

        try (
                Connection con = DS.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(
                        "SELECT *, department_name AS departmentName FROM apv_encounter WHERE " + where
                );
        ) {
            List<ApEncounter> list = new ArrayList<>();

            while (rs.next()) {
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
                // Explicitly set departmentName if available
                record.setDepartmentName(rs.getString("departmentName"));
                list.add(record);
            }
            return list;
        }
    }

    public BigDecimal getLatestQueueNumber(Date date, String departmentID) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        BigDecimal queue = DS.executeDecimalResultQuery("select max(queue_number) from ap_encounter where planned_start_date = '" + sdf.format(date) + "'" +
                " and department_key = '" + departmentID + "'");
        if (queue == null)
            queue = BigDecimal.ONE;
        else
            queue = queue.add(BigDecimal.ONE);
        return queue;
    }
    public void processPatientObservationStatus(List<ApEncounter> encounters) throws SQLException {
        if (encounters == null || encounters.isEmpty()) {
            return;
        }
        ApPatientObservationSummaryService observationService = new ApPatientObservationSummaryService();
        for (ApEncounter encounter : encounters) {
            boolean isObserved = (observationService.getRecordByVisit(encounter.getKey())!=null);
             encounter.setObservations(isObserved);
        }
    }

}