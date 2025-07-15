package com.asklepios.backend_service.service;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApEncounterDAO;

import java.lang.reflect.Field;
import java.util.Date;


@Service
@Slf4j
public class ApEncounterService extends ApEncounterDAO implements Serializable {
    @Autowired
    private ApPractitionerService apPractitionerService;
    @Autowired
    private ApDepartmentService apDepartmentService;
    @Autowired
    private ApDiagnosticTestService apDiagnosticTestService;
    @Autowired
    private ApProcedureSetupService apProcedureSetupService;
     @Autowired
     private ApResourcesService apResourcesService;

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
                                result = " ";
                            }
                        }
                    }
                } else {
                    result = " ";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return result;

    }
    public boolean getHasOrder(String visitKey) throws SQLException {
        String query = "SELECT COUNT(visit_key) AS count FROM ap_diagnostic_orders WHERE visit_key = ?";
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
            ps.setString(4, visitKey);

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
                record.setOriginLkey(rs.getString("origin_lkey"));
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
    public Map<String, Integer> countNewInpatientOrOngoingVisits(String patientKey) throws SQLException {
        String query = """
        SELECT 
           COUNT(CASE
                       WHEN encounter_status_lkey = '91084250213000'
                           THEN 1
                 END) AS count_status_ongoing,
          COUNT(CASE
                       WHEN encounter_status_lkey = '91063195286200'  
                          AND resource_type_lkey != '4217389643435490'     
                           THEN 1
                 END) AS count_status_outpatient_new,
           COUNT(CASE
                       WHEN resource_type_lkey = '4217389643435490'
                           AND encounter_status_lkey = '91063195286200'
                           AND discharge = false
                           THEN 1
                 END) AS count_resource_and_status
        FROM ap_encounter
        WHERE patient_key = ? 
    """;

        Map<String, Integer> result = new HashMap<>();

        try (
                Connection con = DS.getConnection();
                PreparedStatement ps = con.prepareStatement(query)
        ) {
            ps.setString(1, patientKey);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result.put("count_status_ongoing", rs.getInt("count_status_ongoing"));
                    result.put("count_status_outpatient_new", rs.getInt("count_status_outpatient_new"));
                    result.put("count_resource_and_status", rs.getInt("count_resource_and_status"));
                } else {
                    result.put("count_status_ongoing", 0);
                    result.put("count_status_outpatient_new", 0);
                    result.put("count_resource_and_status", 0);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return result;
    }
    public Map<String, Integer> countOngoingVisits(String patientKey) throws SQLException {
        String query = """
        SELECT 
            COUNT(CASE
                WHEN encounter_status_lkey = '91084250213000'
                THEN 1
            END) AS count_status_ongoing
        FROM ap_encounter
        WHERE patient_key = ?
    """;

        Map<String, Integer> result = new HashMap<>();

        try (
                Connection con = DS.getConnection();
                PreparedStatement ps = con.prepareStatement(query)
        ) {
            ps.setString(1, patientKey);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result.put("count_status_ongoing", rs.getInt("count_status_ongoing"));
                } else {
                    result.put("count_status_ongoing", 0);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return result;
    }
    public Object  getResource(String resourceTypeKey, String key ,String lang ) throws SQLException {
        String resourcekey = apResourcesService.getRecord(key).getResourceKey();
        if(resourceTypeKey == null || resourceTypeKey.isEmpty()) {
            return null;
        }
        // TODO update status to be a LOV value
        if(resourceTypeKey.equals("2039534205961578")) //Practitioner
        {
            ApPractitioner PractitionerObject = apPractitionerService.getRecord(resourcekey);
            if (PractitionerObject != null ) {
                apPractitionerService.populateLovFields(PractitionerObject,lang);
                return PractitionerObject;
            }
        }

        // TODO update status to be a LOV value
        else if(resourceTypeKey.equals("2039516279378421")) //Department
        {
             ApDepartment DepartmentObject = apDepartmentService.getRecord(resourcekey);

            if (DepartmentObject != null) {
                apDepartmentService.populateLovFields(DepartmentObject,lang);
                return DepartmentObject;
            }

        }

        // TODO update status to be a LOV value
        else if(resourceTypeKey.equals("2039620472612029")) //Medical Test
        {
            ApDiagnosticTest DiagnosticObject = apDiagnosticTestService.getRecord(resourcekey);
            if (DiagnosticObject != null) {
                apDiagnosticTestService.populateLovFields(DiagnosticObject,lang);
                return DiagnosticObject;
            }

        }
        // TODO update status to be a LOV value
        else if(resourceTypeKey.equals("2039548173192779")) //Procedure
        {
             ApProcedureSetup ProcedureObject =apProcedureSetupService.getRecord(resourcekey);
            if (ProcedureObject != null) {
                apProcedureSetupService.populateLovFields(ProcedureObject,lang);
                return ProcedureObject;
            }

        }
        // TODO update status to be a LOV value
        else if(resourceTypeKey.equals("4217389643435490")) //Department Inpatient Ward
        {
           List<ApDepartment> listDep = apDepartmentService.getList("key = '"+ resourcekey +"' AND department_type_lkey = '5673990729647001'");
            if (listDep != null && !listDep.isEmpty()) {
                apDepartmentService.populateLovFields(listDep.get(0),lang);
                return listDep.get(0);
            }

        }

        // TODO update status to be a LOV value
        else if(resourceTypeKey.equals("5433343011954425"))  //Department Day Case
        {
            System.out.println("resourceTypeKey====>"+resourceTypeKey);
            List<ApDepartment> listDep = new ApDepartmentService().getList("key = '"+ resourcekey +"' AND department_type_lkey = '5673990729647005'");
            if (listDep != null && !listDep.isEmpty()) {
                return listDep.get(0);
            }

        }
        // TODO update status to be a LOV value
        else if(resourceTypeKey.equals("6743167799449277")) //Department Emergency
        {
            List<ApDepartment> listDep = new ApDepartmentService().getList("key = '"+ resourcekey +"' AND department_type_lkey = '5673990729647004'");
            if (listDep != null && !listDep.isEmpty()) {
                return listDep.get(0);
            }

        }
        return null ;
    }


}