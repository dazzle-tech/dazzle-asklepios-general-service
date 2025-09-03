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
import com.asklepios.backend_service.model.generated.pojo.ApMedicalSheets;
import com.asklepios.backend_service.model.generated.entity.ApMedicalSheetsEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApMedicalSheetsDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApMedicalSheets getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_medical_sheets where key = '"+key+"'");) {
ApMedicalSheets record = new ApMedicalSheets();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setDepartmentKey(rs.getString("department_key"));
record.setFacilityKey(rs.getString("facility_key"));
record.setPatientDashboard(rs.getBoolean("patient_dashboard"));
record.setClinicalVisit(rs.getBoolean("clinical_visit"));
record.setDiagnosticsOrder(rs.getBoolean("diagnostics_order"));
record.setPrescription(rs.getBoolean("prescription"));
record.setDrugOrder(rs.getBoolean("drug_order"));
record.setConsultation(rs.getBoolean("consultation"));
record.setProcedures(rs.getBoolean("procedures"));
record.setPatientHistory(rs.getBoolean("patient_history"));
record.setAllergies(rs.getBoolean("allergies"));
record.setMedicalWarnings(rs.getBoolean("medical_warnings"));
record.setMedicationsRecord(rs.getBoolean("medications_record"));
record.setPsychologicalExam(rs.getBoolean("psychological_exam"));
record.setAudiometryPuretone(rs.getBoolean("audiometry_puretone"));
record.setOptometricExam(rs.getBoolean("optometric_exam"));
record.setVaccineReccord(rs.getBoolean("vaccine_reccord"));
record.setDiagnosticsResult(rs.getBoolean("diagnostics_result"));
record.setDentalCare(rs.getBoolean("dental_care"));
record.setCardiology(rs.getBoolean("cardiology"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setObservation(rs.getBoolean("observation"));
record.setVaccination(rs.getBoolean("vaccination"));
record.setOperationRequests(rs.getBoolean("operation_requests"));
record.setDoctorRound(rs.getBoolean("doctor_round"));
record.setDayCase(rs.getBoolean("day_case"));
record.setBedsideProceduresRequest(rs.getBoolean("bedside_procedures_request"));
record.setReferralRequest(rs.getBoolean("referral_request"));
record.setBloodOrder(rs.getBoolean("blood_order"));
record.setIvFluidOrder(rs.getBoolean("iv_fluid_order"));
record.setIntakeOutputBalance(rs.getBoolean("intake_output_balance"));
record.setRiskAssessments(rs.getBoolean("risk_assessments"));
record.setMultidisciplinaryTeamNotes(rs.getBoolean("multidisciplinary_team_notes"));
record.setNutritionStateAssessment(rs.getBoolean("nutrition_state_assessment"));
record.setPhysicianOrderSummary(rs.getBoolean("physician_order_summary"));
record.setCarePlanAndGoals(rs.getBoolean("care_plan_and_goals"));
record.setDischargePlanning(rs.getBoolean("discharge_planning"));
record.setPregnancyFollowUp(rs.getBoolean("pregnancy_follow_up"));
record.setMorseFallScale(rs.getBoolean("morse_fall_scale"));
record.setHendrichFallRisk(rs.getBoolean("hendrich_fall_risk"));
record.setStratifyScale(rs.getBoolean("stratify_scale"));
record.setJohnsHopkinsFallRiskAssessmentTool(rs.getBoolean("johns_hopkins_fall_risk_assessment_tool"));
record.setBradenScaleForPressureUlcer(rs.getBoolean("braden_scale_for_pressure_ulcer"));
record.setGlasgowComaScale(rs.getBoolean("glasgow_coma_scale"));
record.setVteRiskAssessment(rs.getBoolean("vte_risk_assessment"));
record.setProgressNotes(rs.getBoolean("progress_notes"));
record.setIvFluidAdministration(rs.getBoolean("iv_fluid_administration"));
record.setDietaryRequest(rs.getBoolean("dietary_request"));
record.setPediatric(rs.getBoolean("pediatric"));
record.setGynecology(rs.getBoolean("gynecology"));
record.setSpeechTherapy(rs.getBoolean("speech_therapy"));
record.setRehabilitationPlan(rs.getBoolean("rehabilitation_plan"));
record.setOccupationalTherapy(rs.getBoolean("occupational_therapy"));
record.setPhysiotherapyPlan(rs.getBoolean("physiotherapy_plan"));
record.setMedicationAdministrationRecord(rs.getBoolean("medication_administration_record"));
record.setContinuousObservations(rs.getBoolean("continuous_observations"));
record.setDialysisRequest(rs.getBoolean("dialysis_request"));
record.setSlidingScale(rs.getBoolean("sliding_scale"));
record.setPointOfCareCests(rs.getBoolean("point_of_care_cests"));
record.setHospitalCourse(rs.getBoolean("hospital_course"));
record.setChildGrowth (rs.getBoolean("child_growth "));
record.setFlaccNeonatesPainAssessment(rs.getBoolean("flacc_neonates_pain_assessment"));
record.setUniversalPainAssessment(rs.getBoolean("universal_pain_assessment"));
record.setPatientRestraint (rs.getBoolean("patient_restraint "));
record.setInfectionControl(rs.getBoolean("infection_control"));
record.setSofa(rs.getBoolean("sofa"));
record.setMedicalCalculators(rs.getBoolean("medical_calculators"));
record.setCpoeResultsManager(rs.getBoolean("cpoe_results_manager"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApMedicalSheets record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_medical_sheets set key = ?, department_key = ?, facility_key = ?, patient_dashboard = ?, clinical_visit = ?, diagnostics_order = ?, prescription = ?, drug_order = ?, consultation = ?, procedures = ?, patient_history = ?, allergies = ?, medical_warnings = ?, medications_record = ?, psychological_exam = ?, audiometry_puretone = ?, optometric_exam = ?, vaccine_reccord = ?, diagnostics_result = ?, dental_care = ?, cardiology = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ?, observation = ?, vaccination = ?, operation_requests = ?, doctor_round = ?, day_case = ?, bedside_procedures_request = ?, referral_request = ?, blood_order = ?, iv_fluid_order = ?, intake_output_balance = ?, risk_assessments = ?, multidisciplinary_team_notes = ?, nutrition_state_assessment = ?, physician_order_summary = ?, care_plan_and_goals = ?, discharge_planning = ?, pregnancy_follow_up = ?, morse_fall_scale = ?, hendrich_fall_risk = ?, stratify_scale = ?, johns_hopkins_fall_risk_assessment_tool = ?, braden_scale_for_pressure_ulcer = ?, glasgow_coma_scale = ?, vte_risk_assessment = ?, progress_notes = ?, iv_fluid_administration = ?, dietary_request = ?, pediatric = ?, gynecology = ?, speech_therapy = ?, rehabilitation_plan = ?, occupational_therapy = ?, physiotherapy_plan = ?, medication_administration_record = ?, continuous_observations = ?, dialysis_request = ?, sliding_scale = ?, point_of_care_cests = ?, hospital_course = ?, child_growth  = ?, flacc_neonates_pain_assessment = ?, universal_pain_assessment = ?, patient_restraint  = ?, infection_control = ?, sofa = ?, medical_calculators = ?, cpoe_results_manager = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getDepartmentKey());
ps.setString(3, record.getFacilityKey());
ps.setBoolean(4, record.getPatientDashboard());
ps.setBoolean(5, record.getClinicalVisit());
ps.setBoolean(6, record.getDiagnosticsOrder());
ps.setBoolean(7, record.getPrescription());
ps.setBoolean(8, record.getDrugOrder());
ps.setBoolean(9, record.getConsultation());
ps.setBoolean(10, record.getProcedures());
ps.setBoolean(11, record.getPatientHistory());
ps.setBoolean(12, record.getAllergies());
ps.setBoolean(13, record.getMedicalWarnings());
ps.setBoolean(14, record.getMedicationsRecord());
ps.setBoolean(15, record.getPsychologicalExam());
ps.setBoolean(16, record.getAudiometryPuretone());
ps.setBoolean(17, record.getOptometricExam());
ps.setBoolean(18, record.getVaccineReccord());
ps.setBoolean(19, record.getDiagnosticsResult());
ps.setBoolean(20, record.getDentalCare());
ps.setBoolean(21, record.getCardiology());
ps.setString(22, record.getCreatedBy());
ps.setString(23, record.getUpdatedBy());
ps.setString(24, record.getDeletedBy());
ps.setBigDecimal(25, record.getCreatedAt());
ps.setBigDecimal(26, record.getUpdatedAt());
ps.setBigDecimal(27, record.getDeletedAt());
ps.setBoolean(28, record.getIsValid());
ps.setBoolean(29, record.getObservation());
ps.setBoolean(30, record.getVaccination());
ps.setBoolean(31, record.getOperationRequests());
ps.setBoolean(32, record.getDoctorRound());
ps.setBoolean(33, record.getDayCase());
ps.setBoolean(34, record.getBedsideProceduresRequest());
ps.setBoolean(35, record.getReferralRequest());
ps.setBoolean(36, record.getBloodOrder());
ps.setBoolean(37, record.getIvFluidOrder());
ps.setBoolean(38, record.getIntakeOutputBalance());
ps.setBoolean(39, record.getRiskAssessments());
ps.setBoolean(40, record.getMultidisciplinaryTeamNotes());
ps.setBoolean(41, record.getNutritionStateAssessment());
ps.setBoolean(42, record.getPhysicianOrderSummary());
ps.setBoolean(43, record.getCarePlanAndGoals());
ps.setBoolean(44, record.getDischargePlanning());
ps.setBoolean(45, record.getPregnancyFollowUp());
ps.setBoolean(46, record.getMorseFallScale());
ps.setBoolean(47, record.getHendrichFallRisk());
ps.setBoolean(48, record.getStratifyScale());
ps.setBoolean(49, record.getJohnsHopkinsFallRiskAssessmentTool());
ps.setBoolean(50, record.getBradenScaleForPressureUlcer());
ps.setBoolean(51, record.getGlasgowComaScale());
ps.setBoolean(52, record.getVteRiskAssessment());
ps.setBoolean(53, record.getProgressNotes());
ps.setBoolean(54, record.getIvFluidAdministration());
ps.setBoolean(55, record.getDietaryRequest());
ps.setBoolean(56, record.getPediatric());
ps.setBoolean(57, record.getGynecology());
ps.setBoolean(58, record.getSpeechTherapy());
ps.setBoolean(59, record.getRehabilitationPlan());
ps.setBoolean(60, record.getOccupationalTherapy());
ps.setBoolean(61, record.getPhysiotherapyPlan());
ps.setBoolean(62, record.getMedicationAdministrationRecord());
ps.setBoolean(63, record.getContinuousObservations());
ps.setBoolean(64, record.getDialysisRequest());
ps.setBoolean(65, record.getSlidingScale());
ps.setBoolean(66, record.getPointOfCareCests());
ps.setBoolean(67, record.getHospitalCourse());
ps.setBoolean(68, record.getChildGrowth ());
ps.setBoolean(69, record.getFlaccNeonatesPainAssessment());
ps.setBoolean(70, record.getUniversalPainAssessment());
ps.setBoolean(71, record.getPatientRestraint ());
ps.setBoolean(72, record.getInfectionControl());
ps.setBoolean(73, record.getSofa());
ps.setBoolean(74, record.getMedicalCalculators());
ps.setBoolean(75, record.getCpoeResultsManager());
ps.setString(76, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApMedicalSheets record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_medical_sheets set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApMedicalSheets> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_medical_sheets where "+ where);) {
List<ApMedicalSheets> list = new ArrayList<ApMedicalSheets>();
while(rs.next()){
ApMedicalSheets record = new ApMedicalSheets();
record.setKey(rs.getString("key"));
record.setDepartmentKey(rs.getString("department_key"));
record.setFacilityKey(rs.getString("facility_key"));
record.setPatientDashboard(rs.getBoolean("patient_dashboard"));
record.setClinicalVisit(rs.getBoolean("clinical_visit"));
record.setDiagnosticsOrder(rs.getBoolean("diagnostics_order"));
record.setPrescription(rs.getBoolean("prescription"));
record.setDrugOrder(rs.getBoolean("drug_order"));
record.setConsultation(rs.getBoolean("consultation"));
record.setProcedures(rs.getBoolean("procedures"));
record.setPatientHistory(rs.getBoolean("patient_history"));
record.setAllergies(rs.getBoolean("allergies"));
record.setMedicalWarnings(rs.getBoolean("medical_warnings"));
record.setMedicationsRecord(rs.getBoolean("medications_record"));
record.setPsychologicalExam(rs.getBoolean("psychological_exam"));
record.setAudiometryPuretone(rs.getBoolean("audiometry_puretone"));
record.setOptometricExam(rs.getBoolean("optometric_exam"));
record.setVaccineReccord(rs.getBoolean("vaccine_reccord"));
record.setDiagnosticsResult(rs.getBoolean("diagnostics_result"));
record.setDentalCare(rs.getBoolean("dental_care"));
record.setCardiology(rs.getBoolean("cardiology"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setObservation(rs.getBoolean("observation"));
record.setVaccination(rs.getBoolean("vaccination"));
record.setOperationRequests(rs.getBoolean("operation_requests"));
record.setDoctorRound(rs.getBoolean("doctor_round"));
record.setDayCase(rs.getBoolean("day_case"));
record.setBedsideProceduresRequest(rs.getBoolean("bedside_procedures_request"));
record.setReferralRequest(rs.getBoolean("referral_request"));
record.setBloodOrder(rs.getBoolean("blood_order"));
record.setIvFluidOrder(rs.getBoolean("iv_fluid_order"));
record.setIntakeOutputBalance(rs.getBoolean("intake_output_balance"));
record.setRiskAssessments(rs.getBoolean("risk_assessments"));
record.setMultidisciplinaryTeamNotes(rs.getBoolean("multidisciplinary_team_notes"));
record.setNutritionStateAssessment(rs.getBoolean("nutrition_state_assessment"));
record.setPhysicianOrderSummary(rs.getBoolean("physician_order_summary"));
record.setCarePlanAndGoals(rs.getBoolean("care_plan_and_goals"));
record.setDischargePlanning(rs.getBoolean("discharge_planning"));
record.setPregnancyFollowUp(rs.getBoolean("pregnancy_follow_up"));
record.setMorseFallScale(rs.getBoolean("morse_fall_scale"));
record.setHendrichFallRisk(rs.getBoolean("hendrich_fall_risk"));
record.setStratifyScale(rs.getBoolean("stratify_scale"));
record.setJohnsHopkinsFallRiskAssessmentTool(rs.getBoolean("johns_hopkins_fall_risk_assessment_tool"));
record.setBradenScaleForPressureUlcer(rs.getBoolean("braden_scale_for_pressure_ulcer"));
record.setGlasgowComaScale(rs.getBoolean("glasgow_coma_scale"));
record.setVteRiskAssessment(rs.getBoolean("vte_risk_assessment"));
record.setProgressNotes(rs.getBoolean("progress_notes"));
record.setIvFluidAdministration(rs.getBoolean("iv_fluid_administration"));
record.setDietaryRequest(rs.getBoolean("dietary_request"));
record.setPediatric(rs.getBoolean("pediatric"));
record.setGynecology(rs.getBoolean("gynecology"));
record.setSpeechTherapy(rs.getBoolean("speech_therapy"));
record.setRehabilitationPlan(rs.getBoolean("rehabilitation_plan"));
record.setOccupationalTherapy(rs.getBoolean("occupational_therapy"));
record.setPhysiotherapyPlan(rs.getBoolean("physiotherapy_plan"));
record.setMedicationAdministrationRecord(rs.getBoolean("medication_administration_record"));
record.setContinuousObservations(rs.getBoolean("continuous_observations"));
record.setDialysisRequest(rs.getBoolean("dialysis_request"));
record.setSlidingScale(rs.getBoolean("sliding_scale"));
record.setPointOfCareCests(rs.getBoolean("point_of_care_cests"));
record.setHospitalCourse(rs.getBoolean("hospital_course"));
record.setChildGrowth (rs.getBoolean("child_growth "));
record.setFlaccNeonatesPainAssessment(rs.getBoolean("flacc_neonates_pain_assessment"));
record.setUniversalPainAssessment(rs.getBoolean("universal_pain_assessment"));
record.setPatientRestraint (rs.getBoolean("patient_restraint "));
record.setInfectionControl(rs.getBoolean("infection_control"));
record.setSofa(rs.getBoolean("sofa"));
record.setMedicalCalculators(rs.getBoolean("medical_calculators"));
record.setCpoeResultsManager(rs.getBoolean("cpoe_results_manager"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApMedicalSheets record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_medical_sheets values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getDepartmentKey());
ps.setString(3, record.getFacilityKey());
ps.setBoolean(4, record.getPatientDashboard());
ps.setBoolean(5, record.getClinicalVisit());
ps.setBoolean(6, record.getDiagnosticsOrder());
ps.setBoolean(7, record.getPrescription());
ps.setBoolean(8, record.getDrugOrder());
ps.setBoolean(9, record.getConsultation());
ps.setBoolean(10, record.getProcedures());
ps.setBoolean(11, record.getPatientHistory());
ps.setBoolean(12, record.getAllergies());
ps.setBoolean(13, record.getMedicalWarnings());
ps.setBoolean(14, record.getMedicationsRecord());
ps.setBoolean(15, record.getPsychologicalExam());
ps.setBoolean(16, record.getAudiometryPuretone());
ps.setBoolean(17, record.getOptometricExam());
ps.setBoolean(18, record.getVaccineReccord());
ps.setBoolean(19, record.getDiagnosticsResult());
ps.setBoolean(20, record.getDentalCare());
ps.setBoolean(21, record.getCardiology());
ps.setString(22, record.getCreatedBy());
ps.setString(23, record.getUpdatedBy());
ps.setString(24, record.getDeletedBy());
ps.setBigDecimal(25, record.getCreatedAt());
ps.setBigDecimal(26, record.getUpdatedAt());
ps.setBigDecimal(27, record.getDeletedAt());
ps.setBoolean(28, record.getIsValid());
ps.setBoolean(29, record.getObservation());
ps.setBoolean(30, record.getVaccination());
ps.setBoolean(31, record.getOperationRequests());
ps.setBoolean(32, record.getDoctorRound());
ps.setBoolean(33, record.getDayCase());
ps.setBoolean(34, record.getBedsideProceduresRequest());
ps.setBoolean(35, record.getReferralRequest());
ps.setBoolean(36, record.getBloodOrder());
ps.setBoolean(37, record.getIvFluidOrder());
ps.setBoolean(38, record.getIntakeOutputBalance());
ps.setBoolean(39, record.getRiskAssessments());
ps.setBoolean(40, record.getMultidisciplinaryTeamNotes());
ps.setBoolean(41, record.getNutritionStateAssessment());
ps.setBoolean(42, record.getPhysicianOrderSummary());
ps.setBoolean(43, record.getCarePlanAndGoals());
ps.setBoolean(44, record.getDischargePlanning());
ps.setBoolean(45, record.getPregnancyFollowUp());
ps.setBoolean(46, record.getMorseFallScale());
ps.setBoolean(47, record.getHendrichFallRisk());
ps.setBoolean(48, record.getStratifyScale());
ps.setBoolean(49, record.getJohnsHopkinsFallRiskAssessmentTool());
ps.setBoolean(50, record.getBradenScaleForPressureUlcer());
ps.setBoolean(51, record.getGlasgowComaScale());
ps.setBoolean(52, record.getVteRiskAssessment());
ps.setBoolean(53, record.getProgressNotes());
ps.setBoolean(54, record.getIvFluidAdministration());
ps.setBoolean(55, record.getDietaryRequest());
ps.setBoolean(56, record.getPediatric());
ps.setBoolean(57, record.getGynecology());
ps.setBoolean(58, record.getSpeechTherapy());
ps.setBoolean(59, record.getRehabilitationPlan());
ps.setBoolean(60, record.getOccupationalTherapy());
ps.setBoolean(61, record.getPhysiotherapyPlan());
ps.setBoolean(62, record.getMedicationAdministrationRecord());
ps.setBoolean(63, record.getContinuousObservations());
ps.setBoolean(64, record.getDialysisRequest());
ps.setBoolean(65, record.getSlidingScale());
ps.setBoolean(66, record.getPointOfCareCests());
ps.setBoolean(67, record.getHospitalCourse());
ps.setBoolean(68, record.getChildGrowth ());
ps.setBoolean(69, record.getFlaccNeonatesPainAssessment());
ps.setBoolean(70, record.getUniversalPainAssessment());
ps.setBoolean(71, record.getPatientRestraint ());
ps.setBoolean(72, record.getInfectionControl());
ps.setBoolean(73, record.getSofa());
ps.setBoolean(74, record.getMedicalCalculators());
ps.setBoolean(75, record.getCpoeResultsManager());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApMedicalSheetsEntity entity, String lang) {
        Class<?> myClass = ApMedicalSheetsEntity.class;
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
public void translateObject(ApMedicalSheetsEntity entity, String lang) {
        ApMedicalSheetsEntity translated = (ApMedicalSheetsEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}