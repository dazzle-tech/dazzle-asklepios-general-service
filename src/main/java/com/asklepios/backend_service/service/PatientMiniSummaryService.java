package com.asklepios.backend_service.service;

import com.asklepios.backend_service.model.DTO.PatientMiniSummaryDto;
import com.asklepios.backend_service.model.generated.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientMiniSummaryService {

    @Autowired private ApPatientObservationSummaryService apPatientObservationSummaryService;
    @Autowired private ApVisitAllergiesService apVisitAllergiesService;
    @Autowired private ApVisitWarningService apVisitWarningService;
    @Autowired private ApPatientDiagnoseService apPatientDiagnoseService;
    @Autowired private ApIcdCodeService apIcdCodeService; // optional (if you want ICD description)

    // =====================================================================
    // MAIN
    // =====================================================================
    public PatientMiniSummaryDto buildPatientMiniSummary(String patientKey,
                                                     String visitKey,
                                                     String lang) {

        PatientMiniSummaryDto dto = new PatientMiniSummaryDto();

        // Only the requested parts:
        ApPatientObservationSummary obs = safeGet(() -> getLatestObservation(patientKey, visitKey, lang));
        dto.setSymptoms(buildSymptomsString(obs, ""));   // no encounter chief complaint here
        dto.setVitals(buildVitalsString(obs));

        dto.setDiagnosis(safeGet(() -> buildMajorDiagnosisString(patientKey, visitKey, lang)));
        dto.setAllergies(safeGet(() -> buildAllergiesList(patientKey, visitKey, lang)));
        dto.setMedicalWarnings(safeGet(() -> buildWarningsString(patientKey, visitKey, lang)));

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

    private String safe(Object o) { return o == null ? "" : String.valueOf(o); }

    private void append(StringBuilder sb, String label, Object v) {
        if (v == null) return;
        if (safe(v).isEmpty()) return;
        sb.append(label).append(": ").append(v).append("\n");
    }

    // =====================================================================
    // OBSERVATION
    // =====================================================================
    private ApPatientObservationSummary getLatestObservation(String patientKey,
                                                             String visitKey,
                                                             String lang) throws SQLException {

        String where =
                " patient_key='" + patientKey + "' AND visit_key='" + visitKey + "'" +
                        " AND is_valid=true ORDER BY last_date DESC";

        List<ApPatientObservationSummary> list = apPatientObservationSummaryService.getList(where);
        if (list == null || list.isEmpty()) return null;

        ApPatientObservationSummary obs = list.get(0);
        apPatientObservationSummaryService.populateLovFields(obs, lang);

        return obs;
    }

    // =====================================================================
    // SYMPTOMS
    // =====================================================================
    private String buildSymptomsString(ApPatientObservationSummary obs, String chiefComplaint) {
        if (obs == null) return chiefComplaint;

        StringBuilder sb = new StringBuilder();

        // chief complaint removed (not available without encounter)
        append(sb, "Reason of visit", obs.getReasonOfVisit());
        append(sb, "Functional status", obs.getLatestFunctionalStatus());
        append(sb, "Cognitive check", obs.getLatestCognitiveCheck());

        if (obs.getLatestpainlevelLvalue() != null)
            append(sb, "Pain level", obs.getLatestpainlevelLvalue().getLovDisplayVale());
        else
            append(sb, "Pain level", obs.getLatestpainlevel() + "/10");

        append(sb, "Pain description", obs.getLatestpaindescription());

        return sb.toString().trim();
    }

    // =====================================================================
    // VITALS
    // =====================================================================
    private String buildVitalsString(ApPatientObservationSummary obs) {
        if (obs == null) return "";

        StringBuilder sb = new StringBuilder();

        append(sb, "Blood pressure", obs.getLatestbpSystolic() + "/" + obs.getLatestbpDiastolic());
        append(sb, "Heart rate", obs.getLatestheartrate());
        append(sb, "Temperature", obs.getLatesttemperature());
        append(sb, "Oxygen saturation", obs.getLatestoxygensaturation());
        append(sb, "Respiratory rate", obs.getLatestrespiratoryrate());
        append(sb, "Notes", obs.getLatestnotes());

        return sb.toString().trim();
    }

    // =====================================================================
    // DIAGNOSIS (major only)
    // =====================================================================
    private String buildMajorDiagnosisString(String patientKey,
                                             String visitKey,
                                             String lang) throws SQLException {

        String where =
                " patient_key='" + patientKey + "' AND visit_key='" + visitKey + "' AND is_major=true";

        List<ApPatientDiagnose> list = apPatientDiagnoseService.getList(where);
        if (list == null || list.isEmpty()) return "";

        List<String> out = new ArrayList<>();

        for (ApPatientDiagnose d : list) {
            apPatientDiagnoseService.populateLovFields(d, lang);

            // If you DON'T want ICD description, comment the next 8 lines and keep code/description from d
            ApIcdCode icd = null;
            try { icd = apIcdCodeService.getRecord(d.getDiagnoseCode()); } catch (Exception ignored) {}

            String desc = icd != null ? icd.getDescription() : d.getDescription();
            out.add(d.getDiagnoseTypeLvalue().getLovDisplayVale() + " - " + desc);
        }

        return String.join("; ", out);
    }

    // =====================================================================
    // ALLERGIES
    // =====================================================================
    private List<String> buildAllergiesList(String patientKey,
                                            String visitKey,
                                            String lang) throws SQLException {

        String where = " patient_key='" + patientKey + "' AND visit_key='" + visitKey + "'";
        List<ApVisitAllergies> list = apVisitAllergiesService.getList(where);

        List<String> out = new ArrayList<>();
        if (list == null) return out;

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
        if (list == null) return "";

        for (ApVisitWarning w : list) {
            apVisitWarningService.populateLovFields(w, lang);

            String type = w.getWarningTypeLvalue() != null
                    ? w.getWarningTypeLvalue().getLovDisplayVale()
                    : "";

            out.add(type + " - " + safe(w.getWarning()));
        }

        return String.join("; ", out);
    }
}
