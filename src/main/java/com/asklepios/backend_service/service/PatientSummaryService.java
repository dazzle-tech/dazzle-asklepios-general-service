package com.asklepios.backend_service.service;

import com.asklepios.backend_service.model.DTO.PatientSummaryDTO;
import com.asklepios.backend_service.model.generated.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.*;
import java.util.*;

@Service
public class PatientSummaryService {

    @Autowired private ApPatientObservationSummaryService apPatientObservationSummaryService;
    @Autowired private ApVisitAllergiesService apVisitAllergiesService;
    @Autowired private ApVisitWarningService apVisitWarningService;
    @Autowired private ApPatientDiagnoseService apPatientDiagnoseService;
    @Autowired private ApIcdCodeService apIcdCodeService;
    @Autowired private ApPatientSurgicalHistoryService apPatientSurgicalHistoryService;
    @Autowired private ApPatientProblemsService apPatientProblemsService;
    @Autowired private ApEncounterService apEncounterService;
    @Autowired private ApPatientService apPatientService;


    // =====================================================================
    // MAIN
    // =====================================================================
    public PatientSummaryDTO buildPatientSummary(String patientKey,
                                                 String visitKey,
                                                 String lang,
                                                 List<String> medications) {

        PatientSummaryDTO dto = new PatientSummaryDTO();

        ApPatient patient = safeGet(() -> apPatientService.getRecord(patientKey));
        ApEncounter encounter = safeGet(() -> apEncounterService.getRecord(visitKey));

        dto.setAge(encounter.getPatientAge());
        dto.setGender(buildGenderString(patient));

        ApPatientObservationSummary obs =
                safeGet(() -> getLatestObservation(patientKey, visitKey, lang));

        String chiefComplaint = encounter != null ? safe(encounter.getChiefComplaint()) : "";

        dto.setSymptoms(buildSymptomsString(obs, chiefComplaint));
        dto.setVitals(buildVitalsString(obs));
        dto.setDiagnosis(safeGet(() -> buildMajorDiagnosisString(patientKey, visitKey, lang)));
        dto.setAllergies(safeGet(() -> buildAllergiesList(patientKey, visitKey, lang)));
        dto.setMedicalWarnings(safeGet(() -> buildWarningsString(patientKey, visitKey, lang)));
        dto.setSurgeries(safeGet(() -> buildSurgeriesList(patientKey, lang)));
        dto.setProblems(safeGet(() -> buildProblemsList(patientKey, lang)));

        if (medications != null) {
            dto.setMedications(medications);
        }

        return dto;
    }


    // =====================================================================
    // SAFE SQL WRAPPER
    // =====================================================================
    private <T> T safeGet(SqlSupplier<T> supplier) {
        try { return supplier.get(); }
        catch (Exception e) { return null; }
    }

    interface SqlSupplier<T> { T get() throws SQLException; }


    // =====================================================================
    // OBSERVATION
    // =====================================================================
    private ApPatientObservationSummary getLatestObservation(String patientKey,
                                                             String visitKey,
                                                             String lang) throws SQLException {

        String where = " patient_key='" + patientKey + "' AND visit_key='" + visitKey + "' AND is_valid=true ORDER BY last_date DESC";

        List<ApPatientObservationSummary> list =
                apPatientObservationSummaryService.getList(where);

        if (list == null || list.isEmpty()) return null;

        ApPatientObservationSummary obs = list.get(0);

        apPatientObservationSummaryService.populateLovFields(obs, lang);
        obs.setEncounter(apEncounterService.getRecord(obs.getVisitKey()));

        return obs;
    }


    // =====================================================================
    // AGE
    // =====================================================================
    private String buildAgeString(ApPatient p, ApEncounter e) {

        try {
            Object age = e.getPatientAge();
            if (age != null) {
                return safe(age);
            }
        } catch (Exception ignored) {}

        return "";
    }

    // =====================================================================
    // GENDER
    // =====================================================================
    private String buildGenderString(ApPatient p) {
        try {
            var method = p.getClass().getMethod("getGenderLvalue");
            Object lov = method.invoke(p);
            if (lov != null) {
                String value = (String) lov.getClass()
                        .getMethod("getLovDisplayVale")
                        .invoke(lov);

                return value;
            }
        } catch (Exception ignored){}
        return "";
    }


    private String safe(Object o) { return o == null ? "" : String.valueOf(o); }


    // =====================================================================
    // SYMPTOMS
    // =====================================================================
    private String buildSymptomsString(ApPatientObservationSummary obs,
                                       String chiefComplaint) {

        if (obs == null) return chiefComplaint;

        StringBuilder sb = new StringBuilder();

        append(sb, "Chief complaint", chiefComplaint);
        append(sb, "Reason of visit", obs.getReasonOfVisit());
        append(sb, "Functional status", obs.getLatestFunctionalStatus());
        append(sb, "Cognitive check", obs.getLatestCognitiveCheck());

        // Pain
        if (obs.getLatestpainlevelLvalue() != null)
            append(sb, "Pain level", obs.getLatestpainlevelLvalue().getLovDisplayVale());
        else
            append(sb, "Pain level", obs.getLatestpainlevel() + "/10");

        append(sb, "Pain description", obs.getLatestpaindescription());

        return sb.toString().trim();
    }

    private void append(StringBuilder sb, String label, Object v) {
        if (v == null) return;
        if (safe(v).isEmpty()) return;
        sb.append(label).append(": ").append(v).append("\n");
    }


    // =====================================================================
    // VITALS
    // =====================================================================
    private String buildVitalsString(ApPatientObservationSummary obs) {
        if (obs == null) return "";

        StringBuilder sb = new StringBuilder();

        append(sb, "Blood pressure",
                obs.getLatestbpSystolic() + "/" + obs.getLatestbpDiastolic());

        append(sb, "Heart rate", obs.getLatestheartrate());
        append(sb, "Temperature", obs.getLatesttemperature());
        append(sb, "Oxygen saturation", obs.getLatestoxygensaturation());
        append(sb, "Respiratory rate", obs.getLatestrespiratoryrate());
        append(sb, "Notes", obs.getLatestnotes());

        return sb.toString().trim();
    }


    private String buildMajorDiagnosisString(String patientKey,
                                             String visitKey,
                                             String lang) throws SQLException {

        String where = " patient_key='" + patientKey + "' AND visit_key='" + visitKey + "' AND is_major=true";

        List<ApPatientDiagnose> list = apPatientDiagnoseService.getList(where);
        if (list == null || list.isEmpty()) return "";

        List<String> out = new ArrayList<>();

        for (ApPatientDiagnose d : list) {

            apPatientDiagnoseService.populateLovFields(d, lang);
            d.setDiagnosisObject(apIcdCodeService.getRecord(d.getDiagnoseCode()));

            String desc = d.getDiagnosisObject() != null
                    ? d.getDiagnosisObject().getDescription()
                    : d.getDescription();

            out.add(d.getDiagnoseCode() + " - " + desc);
        }

        return String.join("; ", out);
    }


    // =====================================================================
    // ALLERGIES
    // =====================================================================
    private List<String> buildAllergiesList(String patientKey,
                                            String visitKey,
                                            String lang) throws SQLException {

        String where = " patient_key='" + patientKey + "'";

        List<ApVisitAllergies> list = apVisitAllergiesService.getList(where);
        List<String> out = new ArrayList<>();

        for (ApVisitAllergies a : list) {
            apVisitAllergiesService.populateLovFields(a, lang);
            a.setAllergensName(apVisitAllergiesService.getAllergenName(a.getAllergenKey()));
            out.add(a.getAllergensName());
        }

        return out;
    }


    // =====================================================================
    // WARNINGS
    // =====================================================================
    private String buildWarningsString(String patientKey,
                                       String visitKey,
                                       String lang) throws SQLException {

        String where = " patient_key='" + patientKey + "' AND visit_key='" + visitKey + "'";

        List<ApVisitWarning> list = apVisitWarningService.getList(where);
        List<String> out = new ArrayList<>();

        for (ApVisitWarning w : list) {

            apVisitWarningService.populateLovFields(w, lang);

            String type = w.getWarningTypeLvalue() != null
                    ? w.getWarningTypeLvalue().getLovDisplayVale()
                    : "";

            out.add(type + " - " + safe(w.getWarning()));
        }

        return String.join("; ", out);
    }


    // =====================================================================
    // SURGERIES
    // =====================================================================
    private List<String> buildSurgeriesList(String patientKey,
                                            String lang) throws SQLException {

        String where = " patient_key='" + patientKey + "'";

        List<ApPatientSurgicalHistory> list =
                apPatientSurgicalHistoryService.getList(where);

        List<String> out = new ArrayList<>();

        for (ApPatientSurgicalHistory s : list) {

            apPatientSurgicalHistoryService.populateLovFields(s, lang);

            String name = safe(s.getSurgery());
            String year = "";

            if (s.getDateOfSurgery() != null) {
                long millis = s.getDateOfSurgery().longValue();
                if (millis > 0) {
                    LocalDate date = Instant.ofEpochMilli(millis)
                            .atZone(ZoneId.systemDefault()).toLocalDate();
                    year = String.valueOf(date.getYear());
                }
            }

            out.add(year.isEmpty() ? name : name + " (" + year + ")");
        }

        return out;
    }

    private List<String> buildProblemsList(String patientKey,
                                           String lang) throws SQLException {

        String where = " patient_key='" + patientKey + "'";

        List<ApPatientProblems> list =
                apPatientProblemsService.getList(where);

        List<String> out = new ArrayList<>();

        for (ApPatientProblems p : list) {
            apPatientProblemsService.populateLovFields(p, lang);
            out.add(safe(p.getCondition()));
        }

        return out;
    }
}