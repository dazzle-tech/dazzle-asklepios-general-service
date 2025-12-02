package com.asklepios.backend_service.service;

import com.asklepios.backend_service.model.generated.pojo.ApEncounter;
import com.asklepios.backend_service.model.generated.pojo.ApEncounterVaccination;
import com.asklepios.backend_service.model.generated.pojo.ApNurseServiceProduct;
import com.asklepios.backend_service.model.generated.pojo.ApPatient;
import com.asklepios.backend_service.model.generated.pojo.ApPatientObservationSummary;
import com.asklepios.backend_service.model.generated.pojo.ApVisitAllergies;
import com.asklepios.backend_service.model.generated.pojo.ApVisitWarning;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NurseSummaryReportService implements Serializable {

    private final ApPatientObservationSummaryService apPatientObservationSummaryService;
    private final ApVisitAllergiesService apVisitAllergiesService;
    private final ApVisitWarningService apVisitWarningService;
    private final ApEncounterVaccinationService apEncounterVaccinationService;
    private final ApNurseServiceProductService apNurseServiceProductService;
    private final ApVaccineService apVaccineService;
    private final ApVaccineDoseService apVaccineDoseService;
    private final ApVaccineBrandsService apVaccineBrandsService;

    public byte[] generateNurseSummaryPdf(ApPatient patient,
                                          ApEncounter encounter,
                                          String facilityId,
                                          Integer accessLevel,
                                          String lang) {

        try {
            log.info("Generating Nurse Summary PDF for encounterKey={} patientKey={}",
                    encounter.getKey(), patient.getKey());

            String visitKey = encounter.getKey();
            String patientKey = patient.getKey();

            ApPatientObservationSummary observation =
                    fetchObservation(visitKey, patientKey, lang);

            List<ApVisitAllergies> allergies =
                    fetchAllergies(visitKey, patientKey, lang);

            List<ApVisitWarning> warnings =
                    fetchWarnings(visitKey, patientKey, lang);

            List<ApEncounterVaccination> vaccines =
                    fetchVaccines(visitKey, lang);

            List<ApNurseServiceProduct> nurseServices =
                    fetchNurseServices(visitKey, patientKey, lang);

            String html = buildHtmlContent(patient, encounter,
                    observation, allergies, warnings, vaccines, nurseServices);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ConverterProperties converterProperties = new ConverterProperties();
            HtmlConverter.convertToPdf(html, outputStream, converterProperties);

            byte[] pdfBytes = outputStream.toByteArray();
            log.info("Nurse summary PDF generated, size={} bytes", pdfBytes.length);
            return pdfBytes;

        } catch (Exception e) {
            log.error("Error generating nurse summary PDF: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to generate nurse summary PDF: " + e.getMessage(), e);
        }
    }

    // ================== FETCH HELPERS ==================

    private ApPatientObservationSummary fetchObservation(String visitKey,
                                                         String patientKey,
                                                         String lang) {

        String where =
                " visit_key = '" + visitKey + "'" +
                        " and patient_key = '" + patientKey + "'" +
                        " and deleted_at is null " +
                        " order by last_date desc limit 1";

        try {
            List<ApPatientObservationSummary> list =
                    apPatientObservationSummaryService.getList(where);

            if (list == null || list.isEmpty()) {
                return null;
            }

            ApPatientObservationSummary obs = list.get(0);
            apPatientObservationSummaryService.populateLovFields(obs, lang);
            return obs;

        } catch (SQLException e) {
            log.error("Error fetching observation: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    private List<ApVisitAllergies> fetchAllergies(String visitKey,
                                                  String patientKey,
                                                  String lang) {

        String where =
                " visit_key = '" + visitKey + "'" +
                        " and patient_key = '" + patientKey + "'" +
                        " and deleted_at is null";

        try {
            List<ApVisitAllergies> allergies =
                    apVisitAllergiesService.getList(where);

            if (allergies != null) {
                for (ApVisitAllergies row : allergies) {
                    apVisitAllergiesService.populateLovFields(row, lang);
                    if (row.getAllergenKey() != null) {
                        row.setAllergensName(
                                apVisitAllergiesService.getAllergenName(row.getAllergenKey())
                        );
                    }
                }
            }
            return allergies;

        } catch (SQLException e) {
            log.error("Error fetching allergies: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    private List<ApVisitWarning> fetchWarnings(String visitKey,
                                               String patientKey,
                                               String lang) {

        String where =
                " visit_key = '" + visitKey + "'" +
                        " and patient_key = '" + patientKey + "'" +
                        " and deleted_at is null";

        try {
            List<ApVisitWarning> warnings =
                    apVisitWarningService.getList(where);

            if (warnings != null) {
                for (ApVisitWarning row : warnings) {
                    apVisitWarningService.populateLovFields(row, lang);
                }
            }
            return warnings;

        } catch (SQLException e) {
            log.error("Error fetching warnings: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    private List<ApEncounterVaccination> fetchVaccines(String encounterKey,
                                                       String lang) {

        String where = " encounter_key = '" + encounterKey + "' and deleted_at is null";

        try {
            List<ApEncounterVaccination> list =
                    apEncounterVaccinationService.getList(where);

            if (list != null) {
                for (ApEncounterVaccination all : list) {

                    apEncounterVaccinationService.populateLovFields(all, lang);

                    if (all.getVaccineKey() != null) {
                        all.setVaccine(apVaccineService.getRecord(all.getVaccineKey()));
                        if (all.getVaccine() != null) {
                            apVaccineService.populateLovFields(all.getVaccine(), lang);
                        }
                    }

                    if (all.getVaccineDoseKey() != null) {
                        all.setVaccineDose(apVaccineDoseService.getRecord(all.getVaccineDoseKey()));
                        if (all.getVaccineDose() != null) {
                            apVaccineDoseService.populateLovFields(all.getVaccineDose(), lang);
                        }
                    }

                    if (all.getVaccineBrandKey() != null) {
                        all.setVaccineBrands(apVaccineBrandsService.getRecord(all.getVaccineBrandKey()));
                        if (all.getVaccineBrands() != null) {
                            apVaccineBrandsService.populateLovFields(all.getVaccineBrands(), lang);
                        }
                    }
                }
            }
            return list;

        } catch (SQLException e) {
            log.error("Error fetching vaccines: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    private List<ApNurseServiceProduct> fetchNurseServices(String visitKey,
                                                           String patientKey,
                                                           String lang) {

        String where =
                " encounter_key = '" + visitKey + "'" +
                        " and patient_key = '" + patientKey + "'" +
                        " and deleted_at is null";

        try {
            List<ApNurseServiceProduct> list =
                    apNurseServiceProductService.getList(where);

            if (list != null) {
                for (ApNurseServiceProduct row : list) {
                    apNurseServiceProductService.populateLovFields(row, lang);
                }
            }
            return list;

        } catch (SQLException e) {
            log.error("Error fetching nurse services: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }


    private String buildHtmlContent(ApPatient patient,
                                    ApEncounter encounter,
                                    ApPatientObservationSummary obs,
                                    List<ApVisitAllergies> allergies,
                                    List<ApVisitWarning> warnings,
                                    List<ApEncounterVaccination> vaccines,
                                    List<ApNurseServiceProduct> nurseServices) {

        SimpleDateFormat sdfTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String currentDate = sdfTime.format(new Date());

        String dobStr = formatDate(patient.getDob(), "yyyy-MM-dd");
        Date visitDate = chooseVisitDate(encounter);
        String visitDateStr = formatDate(visitDate, "yyyy-MM-dd");

        String priorityText = encounter.getEncounterPriorityLvalue() != null
                ? nullSafe(encounter.getEncounterPriorityLvalue().getLovDisplayVale())
                : "";

        String visitType = encounter.getVisitTypeLvalue() != null
                ? nullSafe(encounter.getVisitTypeLvalue().getLovDisplayVale())
                : "";

        String origin = encounter.getOriginLvalue() != null
                ? nullSafe(encounter.getOriginLvalue().getLovDisplayVale())
                : "";

        String visitReason = (obs != null)
                ? nullSafe(obs.getReasonOfVisit())
                : "";

        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html><html><head>");
        html.append("<meta charset='UTF-8'/>");
        html.append("<meta name='viewport' content='width=device-width, initial-scale=1.0'/>");
        html.append("<title>Nurse Observation Report</title>");
        html.append("<style>").append(getCssStyles()).append("</style>");
        html.append("</head><body>");

        html.append("<div class='header'>");
        html.append("<div class='header-main'>");
        html.append("<h1>Nurse Observation Report</h1>");
        html.append("<p class='hospital-name'>Health Organization</p>");
        html.append("</div>");

        html.append("<div class='patient-header-info'>");
        html.append("<table class='header-table'>");

        html.append("<tr>");
        html.append("<th>Patient:</th>");
        html.append("<td colspan='3'>").append(nullSafe(patient.getFullName())).append("</td>");
        html.append("</tr>");

        html.append("<tr>");
        html.append("<th>MRN:</th>");
        html.append("<td>").append(nullSafe(patient.getPatientMrn())).append("</td>");
        html.append("<th>DOB:</th>");
        html.append("<td>").append(dobStr).append("</td>");
        html.append("</tr>");

        html.append("<tr>");
        html.append("<th>Age:</th>");
        html.append("<td>").append(nullSafe(encounter.getPatientAge())).append("</td>");
        html.append("<th></th>");
        html.append("<td></td>");
        html.append("</tr>");

        // separator line between patient info و visit info
        html.append("<tr class='header-separator-row'>");
        html.append("<td colspan='4'></td>");
        html.append("</tr>");

        html.append("<tr>");
        html.append("<th>Visit ID:</th>");
        html.append("<td>").append(nullSafe(encounter.getVisitId())).append("</td>");
        html.append("<th>Visit Date:</th>");
        html.append("<td>").append(visitDateStr).append("</td>");
        html.append("</tr>");

        html.append("<tr>");
        html.append("<th>Visit Type:</th>");
        html.append("<td>").append(visitType).append("</td>");
        html.append("<th>Priority:</th>");
        html.append("<td>").append(priorityText).append("</td>");
        html.append("</tr>");

        html.append("<tr>");
        html.append("<th>Origin:</th>");
        html.append("<td>").append(origin).append("</td>");
        html.append("<th>Reason:</th>");
        html.append("<td colspan='3'>").append(visitReason).append("</td>");
        html.append("</tr>");

        html.append("</table>");
        html.append("</div>");
        html.append("</div>");

        html.append("<div class='section'>");
        html.append("<h2 class='section-number'>1. Patient Observations &amp; Complaints</h2>");
        if (obs != null) {
            html.append("<div class='content-box'>");
            html.append("<div class='info-row'><span class='info-label'>Reason Of Visit:</span>")
                    .append("<span class='info-value'>")
                    .append(nullSafe(obs.getReasonOfVisit()))
                    .append("</span></div>");

            html.append("<div class='info-row'><span class='info-label'>Functional Status:</span>")
                    .append("<span class='info-value'>")
                    .append(nullSafe(obs.getLatestFunctionalStatus()))
                    .append("</span></div>");

            html.append("<div class='info-row'><span class='info-label'>Cognitive Check:</span>")
                    .append("<span class='info-value'>")
                    .append(nullSafe(obs.getLatestCognitiveCheck()))
                    .append("</span></div>");

            html.append("<div class='info-row'><span class='info-label'>Priority:</span>")
                    .append("<span class='info-value'>")
                    .append(priorityText)
                    .append("</span></div>");
            html.append("</div>");
        } else {
            html.append("<div class='content-box'><p>No records found.</p></div>");
        }
        html.append("</div>");

        html.append("<div class='section'>");
        html.append("<h2 class='section-number'>2. Vital Signs</h2>");
        html.append("<div class='content-box'>");
        if (obs != null) {
            html.append("<div class='info-row'><span class='info-label'>Blood Pressure:</span>")
                    .append("<span class='info-value'>")
                    .append(nullSafeNumber(obs.getLatestbpSystolic()))
                    .append(" / ")
                    .append(nullSafeNumber(obs.getLatestbpDiastolic()))
                    .append(" mmHg</span></div>");

            html.append("<div class='info-row'><span class='info-label'>Heart Rate:</span>")
                    .append("<span class='info-value'>")
                    .append(nullSafeNumber(obs.getLatestheartrate()))
                    .append(" bpm</span></div>");

            html.append("<div class='info-row'><span class='info-label'>Temperature:</span>")
                    .append("<span class='info-value'>")
                    .append(nullSafeNumber(obs.getLatesttemperature()))
                    .append("</span></div>");

            html.append("<div class='info-row'><span class='info-label'>Oxygen Saturation:</span>")
                    .append("<span class='info-value'>")
                    .append(nullSafeNumber(obs.getLatestoxygensaturation()))
                    .append(" %</span></div>");

            html.append("<div class='info-row'><span class='info-label'>Respiratory Rate:</span>")
                    .append("<span class='info-value'>")
                    .append(nullSafeNumber(obs.getLatestrespiratoryrate()))
                    .append(" /min</span></div>");

            html.append("<div class='info-row'><span class='info-label'>Notes:</span>")
                    .append("<span class='info-value'>")
                    .append(nullSafe(obs.getLatestnotes()))
                    .append("</span></div>");
        } else {
            html.append("<p>No records found.</p>");
        }
        html.append("</div>");
        html.append("</div>");

        html.append("<div class='section'>");
        html.append("<h2 class='section-number'>3. Body Measurements</h2>");
        html.append("<div class='content-box'>");
        if (obs != null) {
            html.append("<div class='info-row'><span class='info-label'>Weight:</span>")
                    .append("<span class='info-value'>")
                    .append(nullSafeNumber(obs.getLatestweight()))
                    .append(" Kg</span></div>");

            html.append("<div class='info-row'><span class='info-label'>Height:</span>")
                    .append("<span class='info-value'>")
                    .append(nullSafeNumber(obs.getLatestheight()))
                    .append(" Cm</span></div>");

            html.append("<div class='info-row'><span class='info-label'>Head circumference:</span>")
                    .append("<span class='info-value'>")
                    .append(nullSafeNumber(obs.getLatestheadcircumference()))
                    .append(" Cm</span></div>");

            html.append("<div class='info-row'><span class='info-label'>BMI:</span>")
                    .append("<span class='info-value'>")
                    .append(nullSafeNumber(obs.getLatestbmi()))
                    .append("</span></div>");
        } else {
            html.append("<p>No records found.</p>");
        }
        html.append("</div>");
        html.append("</div>");

        html.append("<div class='section'>");
        html.append("<h2 class='section-number'>4. Pain Assessment</h2>");
        html.append("<div class='content-box'>");
        if (obs != null) {
            String painDegree = "";
            if (obs.getLatestpainlevelLvalue() != null) {
                painDegree = nullSafe(obs.getLatestpainlevelLvalue().getLovDisplayVale());
            }
            html.append("<div class='info-row'><span class='info-label'>Pain Degree:</span>")
                    .append("<span class='info-value'>")
                    .append(painDegree)
                    .append("</span></div>");

            html.append("<div class='info-row'><span class='info-label'>Pain Level (0-10):</span>")
                    .append("<span class='info-value'>")
                    .append(nullSafeNumber(obs.getLatestpainlevel()))
                    .append("</span></div>");

            html.append("<div class='info-row'><span class='info-label'>Pain Description:</span>")
                    .append("<span class='info-value'>")
                    .append(nullSafe(obs.getLatestpaindescription()))
                    .append("</span></div>");
        } else {
            html.append("<p>No records found.</p>");
        }
        html.append("</div>");
        html.append("</div>");

        html.append("<div class='section'>");
        html.append("<h2 class='section-number'>5. Additional Measurements (Infant / Neonate)</h2>");
        html.append("<div class='content-box'>");
        if (obs != null) {
            html.append("<div class='info-row'><span class='info-label'>Hearing Test:</span>")
                    .append("<span class='info-value'>")
                    .append(nullSafe(obs.getLatesthearingtest()))
                    .append("</span></div>");

            html.append("<div class='info-row'><span class='info-label'>Dehydration:</span>")
                    .append("<span class='info-value'>")
                    .append(booleanLabel(obs.getLatestDehydration()))
                    .append("</span></div>");

            html.append("<div class='info-row'><span class='info-label'>Nasal Flaring:</span>")
                    .append("<span class='info-value'>")
                    .append(booleanLabel(obs.getLatestNasalFlaring()))
                    .append("</span></div>");

            html.append("<div class='info-row'><span class='info-label'>Response to Light:</span>")
                    .append("<span class='info-value'>")
                    .append(booleanLabel(obs.getLatestResponseToLight()))
                    .append("</span></div>");

            html.append("<div class='info-row'><span class='info-label'>Pupil Response:</span>")
                    .append("<span class='info-value'>")
                    .append(booleanLabel(obs.getLatestPupilResponse()))
                    .append("</span></div>");

            html.append("<div class='info-row'><span class='info-label'>Ability to Follow Target:</span>")
                    .append("<span class='info-value'>")
                    .append(booleanLabel(obs.getLatestAbilityToFollowTarget()))
                    .append("</span></div>");

            html.append("<div class='info-row'><span class='info-label'>Color Testing:</span>")
                    .append("<span class='info-value'>")
                    .append(booleanLabel(obs.getLatestColorTesting()))
                    .append("</span></div>");
        } else {
            html.append("<p>No records found.</p>");
        }
        html.append("</div>");
        html.append("</div>");

        html.append("<div class='section'>");
        html.append("<h2 class='section-number'>6. Additional Measurements (GER)</h2>");
        html.append("<div class='content-box'>");
        if (obs != null) {
            html.append("<div class='info-row'><span class='info-label'>Fall Risk:</span>")
                    .append("<span class='info-value'>")
                    .append(booleanLabel(obs.getLatestFallRisk()))
                    .append("</span></div>");

            html.append("<div class='info-row'><span class='info-label'>Fall Risk Details:</span>")
                    .append("<span class='info-value'>")
                    .append(nullSafe(obs.getLatestFallRiskDetails()))
                    .append("</span></div>");

            html.append("<div class='info-row'><span class='info-label'>Action to Take:</span>")
                    .append("<span class='info-value'>")
                    .append(nullSafe(obs.getLatestActionToTake()))
                    .append("</span></div>");
        } else {
            html.append("<p>No records found.</p>");
        }
        html.append("</div>");
        html.append("</div>");

        html.append("<div class='section'>");
        html.append("<h2 class='section-number'>7. Allergies</h2>");
        html.append("<div class='content-box'>");
        if (allergies != null && !allergies.isEmpty()) {
            html.append("<table class='table'>");
            html.append("<thead><tr>")
                    .append("<th>Allergen</th>")
                    .append("<th>Type</th>")
                    .append("<th>Severity</th>")
                    .append("<th>Criticality</th>")
                    .append("<th>Onset</th>")
                    .append("<th>Status</th>")
                    .append("<th>Treatment Strategy</th>")
                    .append("<th>Notes</th>")
                    .append("</tr></thead><tbody>");

            for (ApVisitAllergies a : allergies) {
                html.append("<tr>");
                html.append("<td>").append(nullSafe(a.getAllergensName())).append("</td>");
                html.append("<td>").append(lovDisplay(a.getAllergyTypeLvalue())).append("</td>");
                html.append("<td>").append(lovDisplay(a.getSeverityLvalue())).append("</td>");
                html.append("<td>").append(lovDisplay(a.getCriticalityLvalue())).append("</td>");
                html.append("<td>").append(lovDisplay(a.getOnsetLvalue())).append("</td>");
                html.append("<td>").append(lovDisplay(a.getStatusLvalue())).append("</td>");
                html.append("<td>").append(lovDisplay(a.getTreatmentStrategyLvalue())).append("</td>");
                html.append("<td>").append(nullSafe(a.getNotes())).append("</td>");
                html.append("</tr>");
            }

            html.append("</tbody></table>");
        } else {
            html.append("<p>No records found.</p>");
        }
        html.append("</div>");
        html.append("</div>");

        html.append("<div class='section'>");
        html.append("<h2 class='section-number'>8. Medical Warnings</h2>");
        html.append("<div class='content-box'>");
        if (warnings != null && !warnings.isEmpty()) {
            html.append("<table class='table'>");
            html.append("<thead><tr>")
                    .append("<th>Warning</th>")
                    .append("<th>Type</th>")
                    .append("<th>Severity</th>")
                    .append("<th>Source</th>")
                    .append("<th>Status</th>")
                    .append("<th>First Time Recorded</th>")
                    .append("<th>Action To Take</th>")
                    .append("<th>Notes</th>")
                    .append("</tr></thead><tbody>");

            for (ApVisitWarning w : warnings) {
                String firstTime = formatEpochDate(w.getFirstTimeRecorded());

                html.append("<tr>");
                html.append("<td>").append(nullSafe(w.getWarning())).append("</td>");
                html.append("<td>").append(lovDisplay(w.getWarningTypeLvalue())).append("</td>");
                html.append("<td>").append(lovDisplay(w.getSeverityLvalue())).append("</td>");
                html.append("<td>").append(lovDisplay(w.getSourceOfInformationLvalue())).append("</td>");
                html.append("<td>").append(lovDisplay(w.getStatusLvalue())).append("</td>");
                html.append("<td>").append(firstTime).append("</td>");
                html.append("<td>").append(nullSafe(w.getActionTake())).append("</td>");
                html.append("<td>").append(nullSafe(w.getNotes())).append("</td>");
                html.append("</tr>");
            }

            html.append("</tbody></table>");
        } else {
            html.append("<p>No records found.</p>");
        }
        html.append("</div>");
        html.append("</div>");

        html.append("<div class='section'>");
        html.append("<h2 class='section-number'>9. Vaccinations</h2>");
        html.append("<div class='content-box'>");
        if (vaccines != null && !vaccines.isEmpty()) {
            html.append("<table class='table'>");
            html.append("<thead><tr>")
                    .append("<th>Vaccine Name</th>")
                    .append("<th>Type</th>")
                    .append("<th>Number of Doses</th>")
                    .append("<th>Route of Admin.</th>")
                    .append("<th>Site</th>")
                    .append("<th>Possible Reactions</th>")
                    .append("</tr></thead><tbody>");

            for (ApEncounterVaccination v : vaccines) {
                String vaccineName = v.getVaccine() != null ? nullSafe(v.getVaccine().getVaccineName()) : "";
                String type = (v.getVaccine() != null && v.getVaccine().getTypeLvalue() != null)
                        ? nullSafe(v.getVaccine().getTypeLvalue().getLovDisplayVale()) : "";
                String doses = (v.getVaccine() != null && v.getVaccine().getNumberOfDosesLvalue() != null)
                        ? nullSafe(v.getVaccine().getNumberOfDosesLvalue().getLovDisplayVale()) : "";
                String roa = (v.getVaccine() != null && v.getVaccine().getRoaLvalue() != null)
                        ? nullSafe(v.getVaccine().getRoaLvalue().getLovDisplayVale()) : "";
                String site = v.getVaccine() != null ? nullSafe(v.getVaccine().getSiteOfAdministration()) : "";
                String reactions = v.getVaccine() != null ? nullSafe(v.getVaccine().getPossibleReactions()) : "";

                html.append("<tr>");
                html.append("<td>").append(vaccineName).append("</td>");
                html.append("<td>").append(type).append("</td>");
                html.append("<td>").append(doses).append("</td>");
                html.append("<td>").append(roa).append("</td>");
                html.append("<td>").append(site).append("</td>");
                html.append("<td>").append(reactions).append("</td>");
                html.append("</tr>");
            }

            html.append("</tbody></table>");
        } else {
            html.append("<p>No records found.</p>");
        }
        html.append("</div>");
        html.append("</div>");

        html.append("<div class='section'>");
        html.append("<h2 class='section-number'>10. Nurse Services &amp; Products</h2>");
        html.append("<div class='content-box'>");
        if (nurseServices != null && !nurseServices.isEmpty()) {
            html.append("<table class='table'>");
            html.append("<thead><tr>")
                    .append("<th>Category</th>")
                    .append("<th>Service/Product ID</th>")
                    .append("<th>Quantity</th>")
                    .append("<th>Unit Price</th>")
                    .append("<th>Total Price</th>")
                    .append("</tr></thead><tbody>");

            for (ApNurseServiceProduct n : nurseServices) {
                String category = n.getCategoryLvalue() != null
                        ? nullSafe(n.getCategoryLvalue().getLovDisplayVale())
                        : "";

                BigDecimal serviceId = n.getServiceId();
                BigDecimal productId = n.getWarehouseProductId();

                String id;
                if ("Service".equalsIgnoreCase(category)) {
                    id = serviceId != null ? serviceId.toPlainString() : "";
                } else if ("Product".equalsIgnoreCase(category)) {
                    id = productId != null ? productId.toPlainString() : "";
                } else {
                    if (serviceId != null && serviceId.compareTo(BigDecimal.ZERO) != 0) {
                        id = serviceId.toPlainString();
                    } else if (productId != null) {
                        id = productId.toPlainString();
                    } else {
                        id = "";
                    }
                }

                html.append("<tr>");
                html.append("<td>").append(category).append("</td>");
                html.append("<td>").append(id).append("</td>");
                html.append("<td>").append(nullSafeNumber(n.getQuantity())).append("</td>");
                html.append("<td>").append(nullSafeNumber(n.getUnitPrice())).append("</td>");
                html.append("<td>").append(nullSafeNumber(n.getTotalPrice())).append("</td>");
                html.append("</tr>");
            }

            html.append("</tbody></table>");
        } else {
            html.append("<p>No records found.</p>");
        }
        html.append("</div>");
        html.append("</div>");

        html.append("<div class='footer'>");
        html.append("<p class='footer-text'>Confidential medical record - Health Organization</p>");
        html.append("<p class='footer-date'>Report Generated: ").append(currentDate).append("</p>");
        html.append("</div>");

        html.append("</body></html>");
        return html.toString();
    }

    private String nullSafe(Object o) {
        return o == null ? "" : o.toString();
    }

    private String nullSafeNumber(Number n) {
        return n == null ? "" : n.toString();
    }

    private String booleanLabel(Boolean b) {
        if (b == null) return "";
        return Boolean.TRUE.equals(b) ? "Positive" : "Negative";
    }

    private String lovDisplay(Object lvalue) {
        try {
            if (lvalue == null) return "";
            return (String) lvalue.getClass().getMethod("getLovDisplayVale").invoke(lvalue);
        } catch (Exception e) {
            return "";
        }
    }

    private Date chooseVisitDate(ApEncounter encounter) {
        if (encounter.getActualStartDate() != null) {
            Object actual = encounter.getActualStartDate();
            if (actual instanceof Date) {
                return (Date) actual;
            }
            if (actual instanceof Number) {
                return new Date(((Number) actual).longValue());
            }
        }
        if (encounter.getPlannedStartDate() != null) {
            Object planned = encounter.getPlannedStartDate();
            if (planned instanceof Date) {
                return (Date) planned;
            }
            if (planned instanceof Number) {
                return new Date(((Number) planned).longValue());
            }
        }
        if (encounter.getCreatedAt() != null) {
            Object created = encounter.getCreatedAt();
            if (created instanceof Date) {
                return (Date) created;
            }
            if (created instanceof Number) {
                return new Date(((Number) created).longValue());
            }
        }
        return null;
    }

    private String formatDate(Object dateObj, String pattern) {
        if (dateObj == null) return "";
        Date d;
        if (dateObj instanceof Date) {
            d = (Date) dateObj;
        } else if (dateObj instanceof Number) {
            d = new Date(((Number) dateObj).longValue());
        } else {
            return dateObj.toString();
        }
        return new SimpleDateFormat(pattern).format(d);
    }

    private String formatEpochDate(Number n) {
        if (n == null) return "";
        long ms = n.longValue();
        if (ms == 0L) return "";
        Date d = new Date(ms);
        return new SimpleDateFormat("dd/MM/yyyy").format(d);
    }

    private String getCssStyles() {
        return """
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }
            body {
                font-family: 'Calibri', 'Arial', sans-serif;
                padding: 30px;
                color: #000000;
                line-height: 1.4;
                background: #ffffff;
                font-size: 9pt;
            }
            .header {
                margin-bottom: 20px;
                padding-bottom: 15px;
                border-bottom: 2px solid #000000;
            }
            .header-main {
                text-align: center;
                margin-bottom: 15px;
            }
            .header h1 {
                font-size: 18pt;
                margin-bottom: 5px;
                font-weight: bold;
                color: #000000;
            }
            .hospital-name {
                font-size: 10pt;
                color: #333333;
                font-weight: 600;
            }
            .patient-header-info {
                background: #f5f5f5;
                padding: 10px 15px;
                border-radius: 5px;
                border-left: 4px solid #4a90e2;
            }
            .header-table {
                width: 100%;
                border-collapse: collapse;
                font-size: 9pt;
            }
            .header-table th {
                text-align: left;
                padding: 4px 8px;
                font-weight: bold;
                color: #000000;
                white-space: nowrap;
                width: 90px;
            }
            .header-table td {
                padding: 4px 8px;
                color: #333333;
            }
            .header-table tr:nth-child(odd) {
                background-color: #fdfdfd;
            }
            .header-separator-row td {
                border-top: 1px solid #cccccc;
                padding-top: 6px;
                padding-bottom: 4px;
                background-color: #f5f5f5;
            }
            .section {
                margin-top: 18px;
                margin-bottom: 10px;
                page-break-inside: avoid;
            }
            .section-number {
                font-size: 10pt;
                margin-bottom: 6px;
                font-weight: bold;
                color: #000000;
                border-bottom: 1px solid #cccccc;
                padding-bottom: 3px;
            }
            .content-box {
                padding: 8px 10px;
                background: #f9f9f9;
                border-left: 3px solid #4a90e2;
                border-radius: 3px;
            }
            .content-box p {
                margin: 2px 0;
                line-height: 1.5;
            }
            .info-row {
                padding: 3px 0;
                border-bottom: 1px solid #e0e0e0;
                font-size: 9pt;
            }
            .info-row:last-child {
                border-bottom: none;
            }
            .info-label {
                font-weight: bold;
                color: #000000;
                display: inline-block;
                min-width: 180px;
            }
            .info-value {
                color: #333333;
            }
            .table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 5px;
            }
            .table th,
            .table td {
                border: 1px solid #dddddd;
                padding: 4px 6px;
                font-size: 8.5pt;
                text-align: left;
            }
            .table th {
                background: #f0f0f0;
                font-weight: bold;
            }
            .footer {
                margin-top: 30px;
                padding-top: 12px;
                border-top: 2px solid #cccccc;
                text-align: center;
            }
            .footer-text {
                font-size: 8pt;
                color: #666666;
                margin-bottom: 4px;
            }
            .footer-date {
                font-size: 7pt;
                color: #999999;
            }
        """;
    }
}
