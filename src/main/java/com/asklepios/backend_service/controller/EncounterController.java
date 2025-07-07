package com.asklepios.backend_service.controller;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.entity.ApPatientEntity;
import com.asklepios.backend_service.model.generated.pojo.*;
import com.asklepios.backend_service.model.pojo.ValidationResult;
import com.asklepios.backend_service.model.pojo.request.ListRequest;
import com.asklepios.backend_service.model.pojo.request.ListRequestAllValues;
import com.asklepios.backend_service.model.pojo.request.PhysicalExamAreaRequest;
import com.asklepios.backend_service.model.pojo.request.ReviewOfSystemRequest;
import com.asklepios.backend_service.model.pojo.response.ParentResponse;
import com.asklepios.backend_service.service.*;
import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/encounter")
//@CrossOrigin
@Slf4j
public class EncounterController {

    private final ApPatientService apPatientService;
    private final PublicServices publicServices;
    private final ValidationService validationService;
    private final ApEncounterService apEncounterService;
    private final ApEncounterAppliedServiceService apEncounterAppliedServiceService;
    private final ApServiceService apServiceService;
    private final ApReviewOfSystemService apReviewOfSystemService;
    private final ApPhysicalExamAreaService apPhysicalExamAreaService;
    private final ApIcdCodeService apIcdCodeService;
    private final ApPatientDiagnoseService apPatientDiagnoseService;
    private final ApPatientPlanService apPatientPlanService;
    private final ApPatientEncounterOrderService apPatientEncounterOrderService;
    private final ApPrescriptionService apPrescriptionService;
    private final ApPrescriptionInstructionService apPrescriptionInstructionService;
    private final ApCustomeInstructionsService apCustomeInstructionsService ;
    private final ApPrescriptionMedicationsService apPrescriptionMedicationsService ;
    private final ApConsultationOrderService apConsultationOrderService ;
    private final ApVisitAllergiesService apVisitAllergiesService;
    private  final  ApDrugOrderService apDrugOrderService;
    private  final  ApDrugOrderMedicationsService apDrugOrderMedicationsService;
    private  final  ApProcedureService apProcedureService;
    private  final  ApDiagnosticOrdersService apDiagnosticOrdersService;
    private final  ApDiagnosticOrderTestsService apDiagnosticOrderTestsService;
    private final ApDiagnosticTestService apDiagnosticTestService;
    private final ApPractitionerService apPractitionerService;
    private  final ApDiagnosticOrderTestsNotesService apDiagnosticOrderTestsNotesService;
    private final  ApDiagnosticOrderTestsSamplesService apDiagnosticOrderTestsSamplesService;
    private final ApDiagnosticOrderTestsResultService apDiagnosticOrderTestsResultService;
    private  final  ApDiagnosticOrderTestsResultNotesService apDiagnosticOrderTestsResultNotesService;
    private final  ApDiagnosticTestNormalRangeService apDiagnosticTestNormalRangeService;
    private final ApPsychologicalExamService apPsychologicalExamService;
    private final ApUserService apUserService;
    private final ApAudiometryPuretoneService apAudiometryPuretoneService;
    private final ApOptometricExamService apOptometricExamService;
    private final ApIcdCodeService icondCodeService;
    private final ApDiagnosticTestProfileService apDiagnosticTestProfileService;
    private final ApTreadmillStressService apTreadmillStressService;
    private final ApComplaintSymptomsService apComplaintSymptomsService;
    private final ApElectrocardiogramEcgService apElectrocardiogramEcgService;
    private final ApDiagnosticOrderTestsResultService apDiagnosticTestResultsService;
    private final ApLovValuesService apLovValuesService;
    private final ApFacilityService apFacilityService;
    private final ApDepartmentService apDepartmentService;
    private final ApAdmitOutpatientInpatientService apAdmitOutpatientInpatientService;
    private final ApResourcesService apResourcesService;
    private final ApBedService apBedService;
    private final ApRoomService apRoomService;
    private final ApBedTransactionsService apBedTransactionsService;
    private final ApPainAssessmentService apPainAssessmentService;
    private final ApInpatientChiefComplainService apInpatientChiefComplainService;
    private final ApGeneralAssessmentService apGeneralAssessmentService;
    private final ApFunctionalAssessmentService apFunctionalAssessmentService;
    private final ApMedicationReconciliationService apMedicationReconciliationService;
    private final ApActiveIngredientService apActiveIngredientService;
    private final ApTransferPatientService apTransferPatientService;

    public EncounterController(ApPatientService apPatientService, RestTemplate restTemplate, PublicServices publicServices, ValidationService validationService, ApEncounterService apEncounterService, ApEncounterAppliedServiceService apEncounterAppliedServiceService, ApServiceService apServiceService, ApReviewOfSystemService apReviewOfSystemService, ApPhysicalExamAreaService apPhysicalExamAreaService, ApIcdCodeService apIcdCodeService, ApPatientDiagnoseService apPatientDiagnoseService, ApPatientPlanService apPatientPlanService, ApPatientEncounterOrderService apPatientEncounterOrderService, ApPrescriptionService apPrescriptionService, ApPrescriptionInstructionService apPrescriptionInstructionService, ApCustomeInstructionsService apCustomeInstructionsService, ApPrescriptionMedicationsService apPrescriptionMedicationsService, ApConsultationOrderService apConsultationOrderService, ApVisitAllergiesService apVisitAllergiesService, ApDrugOrderService apDrugOrderService, ApDrugOrderMedicationsService apDrugOrderMedicationsService, ApProcedureService apProcedureService, ApDiagnosticOrdersService apDiagnosticOrdersService, ApDiagnosticOrderTestsService apDiagnosticOrderTestsService, ApDiagnosticTestService apDiagnosticTestService, ApPractitionerService apPractitionerService, ApDiagnosticOrderTestsNotesService apDiagnosticOrderTestsNotesService, ApDiagnosticOrderTestsSamplesService apDiagnosticOrderTestsSamplesService, ApDiagnosticOrderTestsResultService apDiagnosticOrderTestsResultService, ApDiagnosticOrderTestsResultNotesService apDiagnosticOrderTestsResultNotesService, ApDiagnosticTestNormalRangeService apDiagnosticTestNormalRangeService, ApPsychologicalExamService apPsychologicalExamService, ApUserService apUserService, ApAudiometryPuretoneService apAudiometryPuretoneService, ApOptometricExamService apOptometricExamService, ApIcdCodeService icondCodeService, ApDiagnosticTestProfileService apDiagnosticTestProfileService, ApTreadmillStressService apTreadmillStressService, ApComplaintSymptomsService apComplaintSymptomsService, ApElectrocardiogramEcgService apElectrocardiogramEcgService, ApDiagnosticOrderTestsResultService apDiagnosticTestResultsService, ApLovValuesService apLovValuesService, ApFacilityService apFacilityService, ApDepartmentService apDepartmentService, ApAdmitOutpatientInpatientService apAdmitOutpatientInpatientService, ApResourcesService apResourcesService, ApBedService apBedService, ApRoomService apRoomService, ApBedTransactionsService apBedTransactionsService, ApPainAssessmentService apPainAssessmentService, ApInpatientChiefComplainService apInpatientChiefComplainService, ApGeneralAssessmentService apGeneralAssessmentService, ApFunctionalAssessmentService apFunctionalAssessmentService, ApMedicationReconciliationService apMedicationReconciliationService, ApActiveIngredientService apActiveIngredientService, ApTransferPatientService apTransferPatientService) {
        this.apPatientService = apPatientService;
        this.publicServices = publicServices;
        this.validationService = validationService;
        this.apEncounterService = apEncounterService;
        this.apEncounterAppliedServiceService = apEncounterAppliedServiceService;
        this.apServiceService = apServiceService;
        this.apReviewOfSystemService = apReviewOfSystemService;
        this.apPhysicalExamAreaService = apPhysicalExamAreaService;
        this.apIcdCodeService = apIcdCodeService;
        this.apPatientDiagnoseService = apPatientDiagnoseService;
        this.apPatientPlanService=apPatientPlanService ;
        this.apPatientEncounterOrderService = apPatientEncounterOrderService;
        this.apPrescriptionService = apPrescriptionService;
        this.apPrescriptionInstructionService = apPrescriptionInstructionService;
        this.apCustomeInstructionsService = apCustomeInstructionsService;
        this.apPrescriptionMedicationsService = apPrescriptionMedicationsService;
        this.apConsultationOrderService = apConsultationOrderService;
        this.apVisitAllergiesService = apVisitAllergiesService;
        this.apDrugOrderService = apDrugOrderService;
        this.apDrugOrderMedicationsService = apDrugOrderMedicationsService;
        this.apProcedureService = apProcedureService;
        this.apDiagnosticOrdersService = apDiagnosticOrdersService;
        this.apDiagnosticOrderTestsService = apDiagnosticOrderTestsService;
        this.apDiagnosticTestService = apDiagnosticTestService;
        this.apPractitionerService = apPractitionerService;
        this.apDiagnosticOrderTestsNotesService = apDiagnosticOrderTestsNotesService;
        this.apDiagnosticOrderTestsSamplesService = apDiagnosticOrderTestsSamplesService;
        this.apDiagnosticOrderTestsResultService = apDiagnosticOrderTestsResultService;
        this.apDiagnosticOrderTestsResultNotesService = apDiagnosticOrderTestsResultNotesService;
        this.apDiagnosticTestNormalRangeService = apDiagnosticTestNormalRangeService;
        this.apPsychologicalExamService = apPsychologicalExamService;
        this.apUserService = apUserService;
        this.apAudiometryPuretoneService = apAudiometryPuretoneService;
        this.apOptometricExamService = apOptometricExamService;
        this.icondCodeService = icondCodeService;
        this.apDiagnosticTestProfileService = apDiagnosticTestProfileService;
        this.apTreadmillStressService = apTreadmillStressService;
        this.apComplaintSymptomsService = apComplaintSymptomsService;
        this.apElectrocardiogramEcgService = apElectrocardiogramEcgService;
        this.apDiagnosticTestResultsService = apDiagnosticTestResultsService;
        this.apLovValuesService = apLovValuesService;
        this.apFacilityService = apFacilityService;
        this.apDepartmentService = apDepartmentService;
        this.apAdmitOutpatientInpatientService = apAdmitOutpatientInpatientService;
        this.apResourcesService = apResourcesService;
        this.apBedService = apBedService;
        this.apRoomService = apRoomService;
        this.apBedTransactionsService = apBedTransactionsService;
        this.apPainAssessmentService = apPainAssessmentService;
        this.apInpatientChiefComplainService = apInpatientChiefComplainService;
        this.apGeneralAssessmentService = apGeneralAssessmentService;
        this.apFunctionalAssessmentService = apFunctionalAssessmentService;
        this.apMedicationReconciliationService = apMedicationReconciliationService;
        this.apActiveIngredientService = apActiveIngredientService;
        this.apTransferPatientService = apTransferPatientService;
    }

    @GetMapping(value = "/encounter-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> encounterList(@RequestParam Map<String, String> queryParams,
                                           @Nullable @RequestHeader String facility_id,
                                           @Nullable @RequestHeader String access_token,
                                           @Nullable @RequestHeader Integer access_level,
                                           @Nullable @RequestHeader String lang) {
        try {

            ParentResponse<List<ApEncounter>> response = new ParentResponse<>();

            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }

            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false,false);
            List<ApEncounter> encounters = apEncounterService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_encounter where " + whereForTotal);
            for (ApEncounter encounter : encounters) {
                encounter.setPractitionerObject(apPractitionerService.getRecord(encounter.getPhysicianKey()));
               if(encounter.getResourceKey() != null){
                 if(encounter.getResourceTypeLkey().equals("2039534205961578")){
                     ApDepartment department = apDepartmentService.getRecord(encounter.getDepartmentKey());
                     if(department != null){
                     encounter.setDepartmentName(department.getName());}
                 }
                   encounter.setResourceObject(apEncounterService.getResource(encounter.getResourceTypeLkey(),encounter.getResourceKey(),lang));
               }
                if (encounter.getPractitionerObject() != null) {
                    apPractitionerService.populateLovFields(encounter.getPractitionerObject(),lang);
                }
                ApPatient patient = apPatientService.getRecord(encounter.getPatientKey());
                patient.setHasAllergy(apPatientService.getHasAllergy(encounter.getPatientKey()));
                patient.setHasWarning(apPatientService.getHasWarning(encounter.getPatientKey()));
                apPatientService.populateLovFields(patient, lang);
                encounter.setPatientObject(patient);
                encounter.setDiagnosis(apEncounterService.getDiagnosis(encounter.getKey()));
                encounter.setHasOrder(apEncounterService.getHasOrder(encounter.getKey()));
                encounter.setHasPrescription(apEncounterService.getHasPrescription(encounter.getKey()));
                encounter.setHasAllergy(apEncounterService.getHasAllergy(encounter.getKey()));
                encounter.setHasObservation(apEncounterService.getHasObservation(encounter.getKey()));
                
                apEncounterService.populateLovFields(encounter, lang);

            }
            apEncounterService.processPatientObservationStatus(encounters);
            response.setObject(encounters);
            response.setExtraNumeric(totalRecord);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);

        }
    }
    @GetMapping("/get-encounter-by-id")
    public ResponseEntity<?> getEncounterById(
            @RequestParam String key,
            @RequestHeader(name = "lang", required = false) String lang) throws SQLException {

        ApEncounter encounter = apEncounterService.getRecord(key);
        ParentResponse<ApEncounter> response = new ParentResponse<>();

        if (encounter != null && encounter.getKey() != null && !encounter.getKey().isEmpty()) {
            response.setObject(encounter);
            return ResponseEntity.ok(response);
        } else {
            response.setObject(new ApEncounter());
            return ResponseEntity.ok(response);
        }
    }
    @GetMapping(value = "/encounter-service-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> encounterServiceList(@RequestParam Map<String, String> queryParams,
                                                  @Nullable @RequestHeader String facility_id,
                                                  @Nullable @RequestHeader String access_token,
                                                  @Nullable @RequestHeader Integer access_level,
                                                  @Nullable @RequestHeader String lang) {
        try {

            ParentResponse<List<ApEncounterAppliedService>> response = new ParentResponse<>();

            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }

            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false,false);
            List<ApEncounterAppliedService> encounters = apEncounterAppliedServiceService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_encounter_applied_service where " + whereForTotal);
            for (ApEncounterAppliedService appliedService : encounters) {
                appliedService.setServiceObject(apServiceService.getRecord(appliedService.getServiceKey()));
                apEncounterAppliedServiceService.populateLovFields(appliedService, lang);
            }
            response.setObject(encounters);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }


    @PostMapping(value = "/complete-encounter-registration", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> completeEncounterRegistration(@RequestBody ApEncounter apEncounter,
                                                           @Nullable @RequestHeader String facility_id,
                                                           @Nullable @RequestHeader String access_token,
                                                           @Nullable @RequestHeader Integer access_level,
                                                           @Nullable @RequestHeader String lang,
                                                           @Nullable @RequestHeader String screenKey
    ) {
        try {
            ParentResponse<ApEncounter> response = new ParentResponse<>();
            // TODO implement a better solution on passing metadata key of objects (redis service perhaps based on object name)
            ValidationResult validationResult = validationService.validateRecord(screenKey, "271469650359300", ApPatientEntity.class, apEncounter);
            if (validationResult.isPass()) {
                if (apEncounter.getDepartmentKey() != null) {
                    apEncounter.setQueueNumber(apEncounterService.getLatestQueueNumber(new Date(), apEncounter.getDepartmentKey()));
                }
                String patientKey = apEncounter.getPatientKey();

                Map<String, Integer> counts =apEncounterService.countNewInpatientOrOngoingVisits(patientKey);

                int countOngoing = counts.getOrDefault("count_status_ongoing", 0);
                int countResourceAndStatus = counts.getOrDefault("count_resource_and_status", 0);
                int countOutpatientNewEncounter = counts.getOrDefault("count_status_outpatient_new", 0);
                if (countOngoing > 0 && apEncounter.getResourceTypeLkey().equals("4217389643435490")) {
                    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                            .body(Map.of("message", "The Patient has Ongoing Encounter."));
                }
                if (countResourceAndStatus > 0 && apEncounter.getResourceTypeLkey().equals("4217389643435490")) {
                    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                            .body(Map.of("message", "Patient is already inpatient."));
                }

                if (countResourceAndStatus > 0 ) {
                    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                            .body(Map.of("message", "This Patient Has Inpatient Encounter"));
                }
                if(countOutpatientNewEncounter > 0 && apEncounter.getResourceTypeLkey().equals("4217389643435490")){
                    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                            .body(Map.of("message", "This Patient Has New Outpatient Encounter"));
                }

                BigDecimal lastVisitId = DS.executeDecimalResultQuery("select max(visit_id) from ap_encounter");

                BigDecimal newVisitId;
                 if (lastVisitId == null) {
                    newVisitId = BigDecimal.valueOf(100);
                } else {
                    newVisitId = lastVisitId.add(BigDecimal.ONE);
                }

                apEncounter.setVisitId(newVisitId.toString());

                String appointmentDate = String.valueOf(apEncounter.getPlannedStartDate());

                String query = "patient_key =  '" + apEncounter.getPatientKey() + "'" +
                        " and DATE('" + appointmentDate + "') = DATE(planned_start_date)" +
                        " and (" + (apEncounter.getResourceKey() == null
                        ? "resource_key IS NULL"
                        : "resource_key = '" + apEncounter.getResourceKey() + "'") + ")";

                if (apEncounter.getKey() != null && !apEncounter.getKey().equals("null")) {
                    query += " and encounter_key <> '" + apEncounter.getKey() + "'";
                }

                List<ApEncounter> existingEncounter = apEncounterService
                        .getList(query);


                if (!existingEncounter.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                            .body(Map.of("message", "Patient Already Registered on this Resource."));
                }


                apEncounterService.saveRecord(apEncounter);
                response.setObject(apEncounter);
                return ResponseEntity.ok(response);
            } else {
                response.addGeneralError("Validation error");
                response.setValidationResult(validationResult);
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/start-encounter", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> startEncounter(@RequestBody ApEncounter apEncounter,
                                            @Nullable @RequestHeader String facility_id,
                                            @Nullable @RequestHeader String access_token,
                                            @Nullable @RequestHeader Integer access_level,
                                            @Nullable @RequestHeader String lang,
                                            @Nullable @RequestHeader String screenKey
    ) {
        try {
            ParentResponse<ApEncounter> response = new ParentResponse<>();
            if (apEncounter.getEncounterStatusLkey().equals("91063195286200")) { // TODO replace with redis by lov code (ENC_STATUS/NEW)
                // update status to in-progress when encounter is new
                apEncounter.setEncounterStatusLkey("91084250213000"); // TODO replace with redis by lov code (ENC_STATUS/IN_PROGRESS)
                Map<String, Integer> counts =apEncounterService.countOngoingVisits(apEncounter.getPatientKey());

                int countOngoing = counts.getOrDefault("count_status_ongoing", 0);
                if (countOngoing > 0) {
                    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                            .body(Map.of("message", "There Patient Has Encounter Ongoing."));
                }
//                BigDecimal isExistingVisit = DS.executeDecimalResultQuery("select count(0) from ap_encounter where key ='" + apEncounter.getKey() + "'");

                apEncounterService.saveRecord(apEncounter);

            }
            apEncounterService.populateLovFields(apEncounter, lang);
            ApPatient patient = apPatientService.getRecord(apEncounter.getPatientKey());
            apPatientService.populateLovFields(patient, lang);
            apEncounter.setPatientObject(patient);
            response.setObject(apEncounter);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/complete-encounter", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> completeEncounter(@RequestBody ApEncounter apEncounter,
                                               @Nullable @RequestHeader String facility_id,
                                               @Nullable @RequestHeader String access_token,
                                               @Nullable @RequestHeader Integer access_level,
                                               @Nullable @RequestHeader String lang,
                                               @Nullable @RequestHeader String screenKey
    ) {
        try {
            ParentResponse<ApEncounter> response = new ParentResponse<>();
            apEncounter.setEncounterStatusLkey("91109811181900"); // TODO replace with redis by lov code (ENC_STATUS/CLOSED)
            apEncounterService.saveRecord(apEncounter);
            apEncounterService.populateLovFields(apEncounter, lang);
            ApPatient patient = apPatientService.getRecord(apEncounter.getPatientKey());
            apPatientService.populateLovFields(patient, lang);
            apEncounter.setPatientObject(patient);
            response.setObject(apEncounter);
            response.setMsg("Visit Closed");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/save-encounter-changes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveEncounterChanges(@RequestBody ApEncounter apEncounter,
                                                  @Nullable @RequestHeader String facility_id,
                                                  @Nullable @RequestHeader String access_token,
                                                  @Nullable @RequestHeader Integer access_level,
                                                  @Nullable @RequestHeader String lang,
                                                  @Nullable @RequestHeader String screenKey
    ) {
        try {
            ParentResponse<ApEncounter> response = new ParentResponse<>();


            if (apEncounter.getKey() == null) {
                response.addGeneralError("Encounter doesn't exist");
                return ResponseEntity.status(400).body(response);
            }

            apEncounterService.saveRecord(apEncounter);
            apEncounterService.populateLovFields(apEncounter, lang);
            ApPatient patient = apPatientService.getRecord(apEncounter.getPatientKey());
            apPatientService.populateLovFields(patient, lang);
            apEncounter.setPatientObject(patient);
            response.setObject(apEncounter);
            response.setMsg("Changes saved successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(value = "/encounter-review-of-systems", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> encounterReviewOfSystems(@Nullable @RequestHeader String encounterKey,
                                                      @Nullable @RequestHeader String facility_id,
                                                      @Nullable @RequestHeader String access_token,
                                                      @Nullable @RequestHeader Integer access_level,
                                                      @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApReviewOfSystem>> response = new ParentResponse<>();
            ApEncounter encounter = apEncounterService.getRecord(encounterKey);
            if (encounter == null) {
                response.addGeneralError("Invalid Visit");
                return ResponseEntity.status(400).body(response);
            }
            List<ApReviewOfSystem> systems = apReviewOfSystemService.getList("encounter_key = '" + encounterKey + "'");
            for (ApReviewOfSystem all : systems) {
                apReviewOfSystemService.populateLovFields(all, lang);
            }

            response.setObject(systems);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/save-review-of-system", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveReviewOfSystems(@RequestBody ReviewOfSystemRequest request,
                                                 @Nullable @RequestHeader String facility_id,
                                                 @Nullable @RequestHeader String access_token,
                                                 @Nullable @RequestHeader Integer access_level,
                                                 @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApReviewOfSystem>> response = new ParentResponse<>();
            ApEncounter encounter = apEncounterService.getRecord(request.getEncounterKey());
            if (encounter == null) {
                response.addGeneralError("Invalid Visit");
                return ResponseEntity.status(400).body(response);
            }

            if (request.getKey() == null) {
                // recently checked, add new
                ApReviewOfSystem system = new ApReviewOfSystem();
                system.setEncounterKey(encounter.getKey());
                system.setPatientKey(encounter.getPatientKey());
                system.setNotes(request.getNotes());
                system.setSystemDetailLkey(request.getBodySystemDetailKey());
                system.setSystemLkey(request.getSystemLkey());
                apReviewOfSystemService.saveRecord(system);
            } else {
                // already here, save note
                ApReviewOfSystem system = apReviewOfSystemService.getRecord(request.getKey());
                if (system == null) {
                    response.addGeneralError("Invalid record");
                    return ResponseEntity.status(400).body(response);
                }
                system.setNotes(request.getNotes());
                apReviewOfSystemService.saveRecord(system);
            }

            List<ApReviewOfSystem> systems = apReviewOfSystemService.getList("encounter_key = '" + request.getEncounterKey() + "'");
            response.setObject(systems);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/remove-review-of-system", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeReviewOfSystems(@RequestBody ReviewOfSystemRequest request,
                                                   @Nullable @RequestHeader String facility_id,
                                                   @Nullable @RequestHeader String access_token,
                                                   @Nullable @RequestHeader Integer access_level,
                                                   @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApReviewOfSystem>> response = new ParentResponse<>();
            ApEncounter encounter = apEncounterService.getRecord(request.getEncounterKey());
            if (encounter == null) {
                response.addGeneralError("Invalid Visit");
                return ResponseEntity.status(400).body(response);
            }

            if (request.getKey() == null) {
                response.addGeneralError("Invalid record");
            } else {
                ApReviewOfSystem system = apReviewOfSystemService.getRecord(request.getKey());
                if (system == null) {
                    response.addGeneralError("Invalid record");
                    return ResponseEntity.status(400).body(response);
                }
                int res = DS.executeQuery("delete from ap_review_of_system where key = '" + request.getKey() + "'");
                if (res < 1) {
                    response.addGeneralError("Delete failed");
                    return ResponseEntity.status(400).body(response);
                }
            }

            List<ApReviewOfSystem> systems = apReviewOfSystemService.getList("encounter_key = '" + request.getEncounterKey() + "'");
            response.setObject(systems);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(value = "/encounter-physical-exam-areas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> encounterPhysicalExamAreas(@Nullable @RequestHeader String encounterKey,
                                                        @Nullable @RequestHeader String facility_id,
                                                        @Nullable @RequestHeader String access_token,
                                                        @Nullable @RequestHeader Integer access_level,
                                                        @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApPhysicalExamArea>> response = new ParentResponse<>();
            ApEncounter encounter = apEncounterService.getRecord(encounterKey);
            if (encounter == null) {
                response.addGeneralError("Invalid Visit");
                return ResponseEntity.status(400).body(response);
            }

            List<ApPhysicalExamArea> areas = apPhysicalExamAreaService.getList("encounter_key = '" + encounterKey + "'");
            response.setObject(areas);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/save-physical-exam-area", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> savePhysicalExamArea(@RequestBody PhysicalExamAreaRequest request,
                                                  @Nullable @RequestHeader String facility_id,
                                                  @Nullable @RequestHeader String access_token,
                                                  @Nullable @RequestHeader Integer access_level,
                                                  @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApPhysicalExamArea>> response = new ParentResponse<>();
            ApEncounter encounter = apEncounterService.getRecord(request.getEncounterKey());
            if (encounter == null) {
                response.addGeneralError("Invalid Visit");
                return ResponseEntity.status(400).body(response);
            }

            if (request.getKey() == null) {
                // recently checked, add new
                ApPhysicalExamArea area = new ApPhysicalExamArea();
                area.setEncounterKey(encounter.getKey());
                area.setPatientKey(encounter.getPatientKey());
                area.setPhysicalExamAreaDetailLkey(request.getPhysicalExamAreaDetailKey());
                area.setNotes(request.getNotes());
                area.setPass(request.isPass());
                area.setPassReasonLkey(request.getPassReason());
                area.setSourceOfAnswerLkey(request.getSourceOfAnswer());
                apPhysicalExamAreaService.saveRecord(area);
            } else {
                // already here, save details
                ApPhysicalExamArea area = apPhysicalExamAreaService.getRecord(request.getKey());
                if (area == null) {
                    response.addGeneralError("Invalid record");
                    return ResponseEntity.status(400).body(response);
                }
                area.setNotes(request.getNotes());
                area.setPass(request.isPass());
                area.setPassReasonLkey(request.getPassReason());
                area.setSourceOfAnswerLkey(request.getSourceOfAnswer());
                apPhysicalExamAreaService.saveRecord(area);
            }

            List<ApPhysicalExamArea> areas = apPhysicalExamAreaService.getList("encounter_key = '" + request.getEncounterKey() + "'");
            response.setObject(areas);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/remove-physical-exam-area", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removePhysicalExamArea(@RequestBody PhysicalExamAreaRequest request,
                                                    @Nullable @RequestHeader String facility_id,
                                                    @Nullable @RequestHeader String access_token,
                                                    @Nullable @RequestHeader Integer access_level,
                                                    @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApPhysicalExamArea>> response = new ParentResponse<>();
            ApEncounter encounter = apEncounterService.getRecord(request.getEncounterKey());
            if (encounter == null) {
                response.addGeneralError("Invalid Visit");
                return ResponseEntity.status(400).body(response);
            }

            if (request.getKey() == null) {
                response.addGeneralError("Invalid record");
            } else {
                ApPhysicalExamArea area = apPhysicalExamAreaService.getRecord(request.getKey());
                if (area == null) {
                    response.addGeneralError("Invalid record");
                    return ResponseEntity.status(400).body(response);
                }
                int res = DS.executeQuery("delete from ap_physical_exam_area where key = '" + request.getKey() + "'");
                if (res < 1) {
                    response.addGeneralError("Delete failed");
                    return ResponseEntity.status(400).body(response);
                }
            }

            List<ApPhysicalExamArea> areas = apPhysicalExamAreaService.getList("encounter_key = '" + request.getEncounterKey() + "'");
            response.setObject(areas);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(value = "/patient-diagnosis-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> patientDiagnosisList(@RequestParam Map<String, String> queryParams,
                                                  @Nullable @RequestHeader String facility_id,
                                                  @Nullable @RequestHeader String access_token,
                                                  @Nullable @RequestHeader Integer access_level,
                                                  @Nullable @RequestHeader String lang) {
        try {

            ParentResponse<List<ApPatientDiagnose>> response = new ParentResponse<>();

            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }

            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false,false);
            List<ApPatientDiagnose> patientDiagnoseList = apPatientDiagnoseService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_patient_diagnose where " + whereForTotal);

            for (ApPatientDiagnose pdiag : patientDiagnoseList) {
                apPatientDiagnoseService.populateLovFields(pdiag, lang);
                pdiag.setDiagnosisObject(apIcdCodeService.getRecord(pdiag.getDiagnoseCode()));
            }
            response.setObject(patientDiagnoseList);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);

        }

    }

    @PostMapping(value = "/save-patient-diagnose", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> savePatientDiagnose(@RequestBody ApPatientDiagnose request,
                                                 @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                 @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                 @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                 @jakarta.annotation.Nullable @RequestHeader String lang,
                                                 @jakarta.annotation.Nullable @RequestHeader String screenKey
    ) {
        try {
            ParentResponse<ApPatientDiagnose> response = new ParentResponse<>();
            apPatientDiagnoseService.saveRecord(request);
            response.setObject(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/remove-patient-diagnose", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removePatientDiagnose(@RequestBody ApPatientDiagnose request,
                                                   @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                   @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                   @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                   @jakarta.annotation.Nullable @RequestHeader String lang,
                                                   @jakarta.annotation.Nullable @RequestHeader String screenKey
    ) {
        try {
            ParentResponse<ApPatientDiagnose> response = new ParentResponse<>();

            int res = DS.executeQuery("delete from ap_patient_diagnose where key = '" + request.getKey() + "'");
            if (res < 1) {
                response.addGeneralError("Delete failed");
                return ResponseEntity.status(400).body(response);
            }

            response.setObject(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @PostMapping(value = "/save-patient-plan", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> savePatientPlan(@RequestBody ApPatientPlan request,
                                             @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                             @jakarta.annotation.Nullable @RequestHeader String access_token,
                                             @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                             @jakarta.annotation.Nullable @RequestHeader String lang

    ) {
        try {
            ParentResponse<ApPatientPlan> response = new ParentResponse<>();
            apPatientPlanService.saveRecord(request);
            response.setObject(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @GetMapping(value = "/patient-plan-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPatientPlanList(@RequestParam Map<String, String> queryParams,
                                                @Nullable @RequestHeader String facility_id,
                                                @Nullable @RequestHeader String access_token,
                                                @Nullable @RequestHeader Integer access_level,
                                                @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApPatientPlan>> response = new ParentResponse<>();


            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }


            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);


            List<ApPatientPlan> patientPlans = apPatientPlanService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_patient_plan where " + whereForTotal);


            for (ApPatientPlan plan : patientPlans) {
                apPatientPlanService.populateLovFields(plan, lang);
            }


            response.setObject(patientPlans);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @PostMapping(value = "/save-patient-encounter-order", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> savePatientEncounterOrder(@RequestBody ApPatientEncounterOrder request,
                                                       @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                       @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                       @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                       @jakarta.annotation.Nullable @RequestHeader String lang

    ) {
        try {
            ParentResponse<ApPatientEncounterOrder> response = new ParentResponse<>();
            if(request.getStatusLkey().equals("1804482322306061")){
                request.setSubmitDate(new BigDecimal(System.currentTimeMillis()));
            }
            apPatientEncounterOrderService.saveRecord(request);
            response.setObject(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(value = "/patient-encounter-order-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPatientEncounterOrderList(@RequestParam Map<String, String> queryParams,
                                                          @Nullable @RequestHeader String facility_id,
                                                          @Nullable @RequestHeader String access_token,
                                                          @Nullable @RequestHeader Integer access_level,
                                                          @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApPatientEncounterOrder>> response = new ParentResponse<>();

            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }

            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);

            List<ApPatientEncounterOrder> patientEncounterOrders = apPatientEncounterOrderService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_patient_encounter_order where " + whereForTotal);

            for (ApPatientEncounterOrder order : patientEncounterOrders) {
                order.setTestName(apPatientEncounterOrderService.getTest(order.getTestKey()).getTestName()) ;
                order.setInternalCode(apPatientEncounterOrderService.getTest(order.getTestKey()).getInternalCode());
                order.setOrderTypeLkey(apPatientEncounterOrderService.getTest(order.getTestKey()).getTestTypeLkey());

                order.setInternationalCodeOne(apPatientEncounterOrderService.getTest(order.getTestKey()).getInternationalCodeOne());
                order.setInternationalCodeTwo(apPatientEncounterOrderService.getTest(order.getTestKey()).getInternationalCodeTwo());
                order.setInternationalCodeThree(apPatientEncounterOrderService.getTest(order.getTestKey()).getInternationalCodeThree()); ;
                apPatientEncounterOrderService.populateLovFields(order, lang);
            }

            response.setObject(patientEncounterOrders);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/remove-encounter-order", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteOrder(@RequestBody ApPatientEncounterOrder Order,
                                         @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                         @jakarta.annotation.Nullable @RequestHeader String access_token,
                                         @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                         @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApPatientEncounterOrder> response = new ParentResponse<>();
            apPatientEncounterOrderService.deleteRecord(Order);
            response.setObject(Order);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }


    @GetMapping(value = "/prescription-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPrescriptionList(@RequestParam Map<String, String> queryParams,
                                                 @Nullable @RequestHeader String facility_id,
                                                 @Nullable @RequestHeader String access_token,
                                                 @Nullable @RequestHeader Integer access_level,
                                                 @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApPrescription>> response = new ParentResponse<>();

            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }

            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);

            List<ApPrescription> prescriptions = apPrescriptionService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_prescription where " + whereForTotal);

            for (ApPrescription pre : prescriptions) {

                apPrescriptionService.populateLovFields(pre, lang);
                pre.setEncounter(apEncounterService.getRecord(pre.getVisitKey()));
            }

            response.setObject(prescriptions);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(value = "/prescription-medic-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPrescriptionMedicationList(@RequestParam Map<String, String> queryParams,
                                                           @Nullable @RequestHeader String facility_id,
                                                           @Nullable @RequestHeader String access_token,
                                                           @Nullable @RequestHeader Integer access_level,
                                                           @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApPrescriptionMedications>> response = new ParentResponse<>();

            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }

            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);

            List<ApPrescriptionMedications> prescriptionMedications =  apPrescriptionMedicationsService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_prescription_medications where " + whereForTotal);

            for (ApPrescriptionMedications medic: prescriptionMedications) {

                apPrescriptionMedicationsService.populateLovFields(medic, lang);
                medic.setActiveIngredientKeys(apPrescriptionMedicationsService.getActiveIngredientKeys(medic.getGenericMedicationsKey()));
               medic.setActiveIngredient(apPrescriptionMedicationsService.getListOfActiveIngredient(medic.getGenericMedicationsKey()));
            }

            response.setObject(prescriptionMedications);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @PostMapping(value = "/save-prescription-medication", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> savePrescriptionMedication(@RequestBody ApPrescriptionMedications request,
                                                        @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                        @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                        @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                        @jakarta.annotation.Nullable @RequestHeader String lang

    ) {
        try {
            ParentResponse<ApPrescriptionMedications> response = new ParentResponse<>();
            apPrescriptionMedicationsService.saveRecord(request);
            response.setObject(request);

            if(request.getInstructionsTypeLkey().equals("3010606785535008")  ){

                ApCustomeInstructions customeInstructions = new ApCustomeInstructions();
                customeInstructions.setPrescriptionMedicationsKey(response.getObject().getKey());
                customeInstructions.setFrequencyLkey(request.getFrequencyLkey());
                customeInstructions.setDose(request.getDose());
                customeInstructions.setUnitLkey(request.getUnitLkey());
                customeInstructions.setRoaLkey(request.getRoaLkey());
                saveCustomeInstructions(customeInstructions,facility_id,access_token,access_level,lang);
            }
            return ResponseEntity.ok(response);

        } catch (Exception e) {

            e.printStackTrace();
            log.error(e.getMessage());

            return ResponseEntity.status(500).body(e);
        }
    }
    @GetMapping(value = "/custome-instructions-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCustomeInstructionsList(@RequestParam Map<String, String> queryParams,
                                                        @Nullable @RequestHeader String facility_id,
                                                        @Nullable @RequestHeader String access_token,
                                                        @Nullable @RequestHeader Integer access_level,
                                                        @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApCustomeInstructions>> response = new ParentResponse<>();

            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }

            ListRequestAllValues listRequest = new ListRequestAllValues(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);

            List<ApCustomeInstructions> customeInstructions = apCustomeInstructionsService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_custome_instructions where " + whereForTotal);

            for (ApCustomeInstructions ci : customeInstructions) {

                apCustomeInstructionsService.populateLovFields(ci, lang);
            }

            response.setObject(customeInstructions);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @PostMapping(value = "/save-custome-instructions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveCustomeInstructions(@RequestBody ApCustomeInstructions request,
                                                     @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                     @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                     @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                     @jakarta.annotation.Nullable @RequestHeader String lang

    ) {
        try {
            ParentResponse<ApCustomeInstructions> response = new ParentResponse<>();
            apCustomeInstructionsService.saveRecord(request);
            response.setObject(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @PostMapping(value = "/remove-prescription-medication", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deletePrescriptionMedication(@RequestBody ApPrescriptionMedications prescriptionMedications,
                                                          @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                          @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                          @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                          @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApPrescriptionMedications> response = new ParentResponse<>();
            prescriptionMedications.setIsValid(false);
            prescriptionMedications.setStatusLkey("1804447528780744");
           
            apPrescriptionMedicationsService.deleteRecord(prescriptionMedications);
            response.setObject(prescriptionMedications);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @PostMapping(value = "/save-consultation-orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveConsultationOrders(@RequestBody ApConsultationOrder request,
                                                    @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                    @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                    @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                    @jakarta.annotation.Nullable @RequestHeader String lang

    ) {
        try {
            ParentResponse<ApConsultationOrder> response = new ParentResponse<>();
            apConsultationOrderService.saveRecord(request);
            response.setObject(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @GetMapping(value = "/consultation-orders-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getConsultationOrdersList(@RequestParam Map<String, String> queryParams,
                                                       @Nullable @RequestHeader String facility_id,
                                                       @Nullable @RequestHeader String access_token,
                                                       @Nullable @RequestHeader Integer access_level,
                                                       @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApConsultationOrder>> response = new ParentResponse<>();

            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }

            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);

            List<ApConsultationOrder> consultationOrders = apConsultationOrderService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_consultation_order where " + whereForTotal);

            for (ApConsultationOrder co : consultationOrders) {

                apConsultationOrderService.populateLovFields(co, lang);
            }

            response.setObject(consultationOrders);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }



    @PostMapping(value = "/save-prescription", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> savePrescription(@RequestBody ApPrescription request,
                                              @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                              @jakarta.annotation.Nullable @RequestHeader String access_token,
                                              @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                              @jakarta.annotation.Nullable @RequestHeader String lang
    ) {
        try {
            if(request.getKey()==null){
            BigDecimal lastpreId = DS.executeDecimalResultQuery("select max(prescription_id) from ap_prescription");
            BigDecimal newpreId;
            if (lastpreId == null) {
                newpreId= BigDecimal.valueOf(100);
            } else {
                newpreId = lastpreId.add(BigDecimal.ONE);
            }
            request.setPrescriptionId(newpreId.toString());}
            ParentResponse<ApPrescription> response = new ParentResponse<>();
            apPrescriptionService.saveRecord(request);
            response.setObject(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @PostMapping(value = "/save-drug-order", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveDrugOrder(@RequestBody ApDrugOrder request,
                                              @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                              @jakarta.annotation.Nullable @RequestHeader String access_token,
                                              @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                              @jakarta.annotation.Nullable @RequestHeader String lang
    ) {
        try {
            if(request.getKey()==null){
                BigDecimal lastpreId = DS.executeDecimalResultQuery("select max(drugorder_id) from ap_drug_order");
                BigDecimal newpreId;
                if (lastpreId == null) {
                    newpreId= BigDecimal.valueOf(100);
                } else {
                    newpreId = lastpreId.add(BigDecimal.ONE);
                }
                request.setDrugorderId(newpreId.toString());}
            ParentResponse<ApDrugOrder> response = new ParentResponse<>();
            apDrugOrderService.saveRecord(request);
            response.setObject(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @GetMapping(value = "/drug_order-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDrugOrderList(@RequestParam Map<String, String> queryParams,
                                                 @Nullable @RequestHeader String facility_id,
                                                 @Nullable @RequestHeader String access_token,
                                                 @Nullable @RequestHeader Integer access_level,
                                                 @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApDrugOrder>> response = new ParentResponse<>();

            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }

            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);
            List<ApDrugOrder> orders = apDrugOrderService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_drug_order where " + whereForTotal);

            for (ApDrugOrder o:orders) {

                apDrugOrderService.populateLovFields(o, lang);
                o.setEncounter(apEncounterService.getRecord(o.getVisitKey()));
            }

            response.setObject(orders);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(value = "/drug-order-medic-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDrugOrderMedicationList(@RequestParam Map<String, String> queryParams,
                                                           @Nullable @RequestHeader String facility_id,
                                                           @Nullable @RequestHeader String access_token,
                                                           @Nullable @RequestHeader Integer access_level,
                                                           @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApDrugOrderMedications>> response = new ParentResponse<>();

            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }

            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);

            List<ApDrugOrderMedications> orderMedications =  apDrugOrderMedicationsService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_drug_order_medications where " + whereForTotal);

            for (ApDrugOrderMedications medic: orderMedications) {

                apDrugOrderMedicationsService.populateLovFields(medic, lang);
                medic.setActiveIngredientKeys(apDrugOrderMedicationsService.getActiveIngredientKeys(medic.getGenericMedicationsKey()));
                medic.setActiveIngredient(apPrescriptionMedicationsService.getListOfActiveIngredient(medic.getGenericMedicationsKey()));
            }

            response.setObject(orderMedications);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @PostMapping(value = "/save-drug-order-medic", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveDrugOrderMedication(@RequestBody ApDrugOrderMedications request,
                                                     @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                     @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                     @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                     @jakarta.annotation.Nullable @RequestHeader String lang

    ) {
        try {
            ParentResponse<ApDrugOrderMedications> response = new ParentResponse<>();
            apDrugOrderMedicationsService.saveRecord(request);
            response.setObject(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(value = "/procedures-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProcedureList(@RequestParam Map<String, String> queryParams,
                                                        @Nullable @RequestHeader String facility_id,
                                                        @Nullable @RequestHeader String access_token,
                                                        @Nullable @RequestHeader Integer access_level,
                                                        @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApProcedure>> response = new ParentResponse<>();

            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }

            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);

            List<ApProcedure> pro =  apProcedureService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_procedure where " + whereForTotal);

            for (ApProcedure all : pro) {
                if (all != null) {
                    apProcedureService.populateLovFields(all, lang);
                    if (all.getFacilityKey() != null) {
                        ApFacility facility = apFacilityService.getRecord(all.getFacilityKey());
                        if (facility != null) {
                            apFacilityService.populateLovFields(facility, lang);
                            all.setFacility(facility);
                        }
                    }
                    if (all.getDepartmentKey() != null) {
                        ApDepartment department = apDepartmentService.getRecord(all.getDepartmentKey());
                        if (department != null) {
                            apDepartmentService.populateLovFields(department, lang);
                            all.setDepartment(department);
                        }
                    }
                    if (all.getProcedureNameKey() != null) {
                        String procedureName = apProcedureService.getProcedureName(all.getProcedureNameKey());
                        if (procedureName != null && !procedureName.isEmpty()) {
                            all.setProcedureName(procedureName);
                        }
                    }

                }
            }


            response.setObject(pro);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @PostMapping(value = "/save-procedures", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveProcedure(@RequestBody ApProcedure request,
                                                     @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                     @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                     @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                     @jakarta.annotation.Nullable @RequestHeader String lang

    ) {
        try {
            if(request.getKey()==null){
                BigDecimal lastpreId = DS.executeDecimalResultQuery("select max(procedure_id) from ap_procedure");
                BigDecimal newpreId;
                if (lastpreId == null) {
                    newpreId= BigDecimal.valueOf(100);
                } else {
                    newpreId = lastpreId.add(BigDecimal.ONE);
                }
                request.setProcedureId(newpreId.toString());}
            ParentResponse<ApProcedure> response = new ParentResponse<>();
            apProcedureService.saveRecord(request);
            response.setObject(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @PostMapping(value = "/save-diagnostic-order", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveDiagnosticOrder(@RequestBody ApDiagnosticOrders request,
                                              @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                              @jakarta.annotation.Nullable @RequestHeader String access_token,
                                              @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                              @jakarta.annotation.Nullable @RequestHeader String lang
    ) {
        try {
            if(request.getKey()==null){
                BigDecimal lastpreId = DS.executeDecimalResultQuery("select max(order_id) from ap_diagnostic_orders");
                BigDecimal newpreId;
                if (lastpreId == null) {
                    newpreId= BigDecimal.valueOf(100);
                } else {
                    newpreId = lastpreId.add(BigDecimal.ONE);
                }
                request.setOrderId(newpreId.toString());}
            ParentResponse<ApDiagnosticOrders> response = new ParentResponse<>();
            apDiagnosticOrdersService.saveRecord(request);
            response.setObject(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @GetMapping(value = "/diagnostic-order-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDiagnosticOrderList(@RequestParam Map<String, String> queryParams,
                                                    @Nullable @RequestHeader String facility_id,
                                                    @Nullable @RequestHeader String access_token,
                                                    @Nullable @RequestHeader Integer access_level,
                                                    @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApDiagnosticOrders>> response = new ParentResponse<>();

            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }

            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);
            List<ApDiagnosticOrders> orders = apDiagnosticOrdersService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_diagnostic_orders where " + whereForTotal);

            for (ApDiagnosticOrders o : orders) {
                o.setPatient(apPatientService.getRecord(o.getPatientKey()));
                o.setEncounter(apEncounterService.getRecord(o.getVisitKey()));

                if(o.getEncounter()!=null){
                o.getEncounter().setDiagnosis(apEncounterService.getDiagnosis( o.getEncounter().getKey()));
                    apEncounterService.populateLovFields(o.getEncounter(), lang);}
                if(o.getPatient()!=null){
                o.getPatient().setHasAllergy(apPatientService.getHasAllergy(o.getPatient().getKey()));
                o.getPatient().setHasWarning(apPatientService.getHasWarning(o.getPatient().getKey()));}
                o.setHasLaboratory(!apDiagnosticOrderTestsService.getList("order_type_lkey = '862810597620632' and order_key = '"+o.getKey()+"'").isEmpty());
                o.setHasRadiology(!apDiagnosticOrderTestsService.getList("order_type_lkey = '862828331135792' and order_key = '"+o.getKey()+"'").isEmpty());
                apDiagnosticOrdersService.populateLovFields(o, lang);
                apPatientService.populateLovFields(o.getPatient(), lang);


            }

            response.setObject(orders);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @PostMapping(value = "/save-diagnostic-order-tests", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveDiagnosticOrderTests(@RequestBody ApDiagnosticOrderTests request,
                                                      @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                      @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                      @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                      @jakarta.annotation.Nullable @RequestHeader String lang

    ) {
        try {
            ParentResponse<ApDiagnosticOrderTests> response = new ParentResponse<>();

            apDiagnosticOrderTestsService.saveRecord(request);
            List<ApDiagnosticOrderTests> tests= apDiagnosticOrderTestsService.getList("order_key ='"+request.getOrderKey()+"'");

            Set<String> statusSet = tests.stream()
                    .filter(test -> "862810597620632".equals(test.getOrderTypeLkey()))
                    .map(ApDiagnosticOrderTests::getProcessingStatusLkey)
                    .collect(Collectors.toSet());
            Set<String> statusRadSet = tests.stream()
                    .filter(test -> "862828331135792".equals(test.getOrderTypeLkey()))
                    .map(ApDiagnosticOrderTests::getProcessingStatusLkey)
                    .collect(Collectors.toSet());
            if (statusSet.size() == 1) {

                String firstElement = statusSet.iterator().next();
                ApDiagnosticOrders order = apDiagnosticOrdersService.getRecord(request.getOrderKey());
                order.setLabStatusLkey(firstElement);
                apDiagnosticOrdersService.saveRecord(order);
            } else {

                ApDiagnosticOrders order = apDiagnosticOrdersService.getRecord(request.getOrderKey());
                order.setLabStatusLkey("6472790341892805");
                apDiagnosticOrdersService.saveRecord(order);
            }
            if (statusRadSet.size() == 1) {
                String value = statusRadSet.iterator().next();
                String firstElement = statusRadSet.iterator().next();
                ApDiagnosticOrders order = apDiagnosticOrdersService.getRecord(request.getOrderKey());
                order.setRadStatusLkey(firstElement);
                apDiagnosticOrdersService.saveRecord(order);
            } else {

                ApDiagnosticOrders order = apDiagnosticOrdersService.getRecord(request.getOrderKey());
                order.setRadStatusLkey("6472790341892805");
                apDiagnosticOrdersService.saveRecord(order);
            }
            response.setObject(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @GetMapping(value = "/diagnostic-order-test-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDiagnosticOrderTestList(@RequestParam Map<String, String> queryParams,
                                                    @Nullable @RequestHeader String facility_id,
                                                    @Nullable @RequestHeader String access_token,
                                                    @Nullable @RequestHeader Integer access_level,
                                                    @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApDiagnosticOrderTests>> response = new ParentResponse<>();

            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }

            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);
            List<ApDiagnosticOrderTests> orders = apDiagnosticOrderTestsService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_diagnostic_order_tests where " + whereForTotal);
            for (ApDiagnosticOrderTests o : orders) {

                 o.setTest( apDiagnosticTestService.getRecord(o.getTestKey()));
                 o.setProfileList(apDiagnosticTestProfileService.getList("diagnostic_test_key= '"+o.getTestKey()+"'"));
                 o.setOrderId(apDiagnosticOrdersService.getRecord(o.getOrderKey()).getOrderId());
                apDiagnosticOrderTestsService.populateLovFields(o, lang);
                apDiagnosticTestService.populateLovFields(o.getTest(),lang );
            }
            response.setObject(orders);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/save-diagnostic-order-tests-notes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveDiagnosticOrderTestsNotes(@RequestBody ApDiagnosticOrderTestsNotes request,
                                                           @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                           @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                           @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                           @jakarta.annotation.Nullable @RequestHeader String lang

    ) {
        try {
            ParentResponse<ApDiagnosticOrderTestsNotes> response = new ParentResponse<>();
            apDiagnosticOrderTestsNotesService.saveRecord(request);
            response.setObject(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @GetMapping(value = "/diagnostic-order-test-notes-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDiagnosticOrderTestNotesList(@RequestHeader  String testid,
                                                             @Nullable @RequestHeader String facility_id,
                                                             @Nullable @RequestHeader String access_token,
                                                             @Nullable @RequestHeader Integer access_level,
                                                             @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApDiagnosticOrderTestsNotes>> response = new ParentResponse<>();



            List<ApDiagnosticOrderTestsNotes> list = apDiagnosticOrderTestsNotesService.getList(
                    " test_key = '" + testid + "'"
            );

            for(ApDiagnosticOrderTestsNotes all : list){
                apDiagnosticOrderTestsNotesService.populateLovFields(all, lang);

            }
            response.setObject(list);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/save-diagnostic-order-tests-sample", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveDiagnosticOrderTestsSample(@RequestBody ApDiagnosticOrderTestsSamples request,
                                                            @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                            @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                            @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                            @jakarta.annotation.Nullable @RequestHeader String lang

    ) {
        try {
            ParentResponse<ApDiagnosticOrderTestsSamples> response = new ParentResponse<>();
            apDiagnosticOrderTestsSamplesService.saveRecord(request);
            response.setObject(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @GetMapping(value = "/diagnostic-order-test-samples-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDiagnosticOrderTestSamplesList(@RequestHeader  String testid,
                                                               @Nullable @RequestHeader String facility_id,
                                                               @Nullable @RequestHeader String access_token,
                                                               @Nullable @RequestHeader Integer access_level,
                                                               @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApDiagnosticOrderTestsSamples>> response = new ParentResponse<>();



            List<ApDiagnosticOrderTestsSamples> list = apDiagnosticOrderTestsSamplesService.getList(
                    " test_key = '" + testid + "'"
            );

            for(ApDiagnosticOrderTestsSamples all : list){
                apDiagnosticOrderTestsSamplesService.populateLovFields(all, lang);

            }
            response.setObject(list);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @PostMapping(value = "/save-psychological-exam", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> savePsychologicalExam(@RequestBody ApPsychologicalExam psychologicalExam ,
                                                     @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                     @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                     @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                     @jakarta.annotation.Nullable @RequestHeader String lang

    ) {
        try {
            ParentResponse<ApPsychologicalExam> response = new ParentResponse<>();
            apPsychologicalExamService.saveRecord(psychologicalExam);
            response.setObject(psychologicalExam);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @GetMapping(value = "/psychological-exam-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPsychologicaExamsList(@RequestParam Map<String, String> queryParams,
                                                        @Nullable @RequestHeader String facility_id,
                                                        @Nullable @RequestHeader String access_token,
                                                        @Nullable @RequestHeader Integer access_level,
                                                        @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApPsychologicalExam>> response = new ParentResponse<>();

            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }

            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);

            List<ApPsychologicalExam> psychologicalExams = apPsychologicalExamService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_psychological_exam where " + whereForTotal);

            for (ApPsychologicalExam psychologicalExam : psychologicalExams) {
                if (psychologicalExam.getCreatedBy() != null) {
                    psychologicalExam.setCreateByUser(apUserService.getRecord(psychologicalExam.getCreatedBy()));
                }
                if (psychologicalExam.getUpdatedBy() != null) {
                    psychologicalExam.setUpdateByUser(apUserService.getRecord(psychologicalExam.getUpdatedBy()));
                }
                if (psychologicalExam.getDeletedBy() != null) {
                    psychologicalExam.setDeleteByUser(apUserService.getRecord(psychologicalExam.getDeletedBy()));
                }
                apPsychologicalExamService.populateLovFields(psychologicalExam, lang);
            }

            response.setObject(psychologicalExams);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @PostMapping(value = "/save-audiometry-puretone", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveAudiometryPuretone(@RequestBody ApAudiometryPuretone audiometryPuretone ,
                                                   @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                   @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                   @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                   @jakarta.annotation.Nullable @RequestHeader String lang

    ) {
        try {
            ParentResponse<ApAudiometryPuretone> response = new ParentResponse<>();
            apAudiometryPuretoneService.saveRecord(audiometryPuretone);
            response.setObject(audiometryPuretone);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @GetMapping(value = "/audiometry-puretone-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAudiometryPuretoneList(@RequestParam Map<String, String> queryParams,
                                                      @Nullable @RequestHeader String facility_id,
                                                      @Nullable @RequestHeader String access_token,
                                                      @Nullable @RequestHeader Integer access_level,
                                                      @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApAudiometryPuretone>> response = new ParentResponse<>();

            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }

            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);

            List<ApAudiometryPuretone> audiometryPuretones = apAudiometryPuretoneService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_audiometry_puretone where " + whereForTotal);

            for (ApAudiometryPuretone audiometryPuretone : audiometryPuretones) {
                if (audiometryPuretone.getCreatedBy() != null) {
                    audiometryPuretone.setCreateByUser(apUserService.getRecord(audiometryPuretone.getCreatedBy()));
                }
                if (audiometryPuretone.getUpdatedBy() != null) {
                    audiometryPuretone.setUpdateByUser(apUserService.getRecord(audiometryPuretone.getUpdatedBy()));
                }
                if (audiometryPuretone.getDeletedBy() != null) {
                    audiometryPuretone.setDeleteByUser(apUserService.getRecord(audiometryPuretone.getDeletedBy()));
                }
                apAudiometryPuretoneService.populateLovFields(audiometryPuretone, lang);
            }

            response.setObject(audiometryPuretones);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @PostMapping(value = "/save-optometric-exam", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveOptometricExam(@RequestBody ApOptometricExam optometricExam ,
                                                @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                @jakarta.annotation.Nullable @RequestHeader String lang

    ) {
        try {
            ParentResponse<ApOptometricExam> response = new ParentResponse<>();
            apOptometricExamService.saveRecord(optometricExam);
            response.setObject(optometricExam);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @GetMapping(value = "/optometric-exam-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOptometricExamList(@RequestParam Map<String, String> queryParams,
                                                   @Nullable @RequestHeader String facility_id,
                                                   @Nullable @RequestHeader String access_token,
                                                   @Nullable @RequestHeader Integer access_level,
                                                   @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApOptometricExam>> response = new ParentResponse<>();

            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }

            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);

            List<ApOptometricExam> optometricExams = apOptometricExamService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_optometric_exam where " + whereForTotal);

            for (ApOptometricExam optometricExam : optometricExams) {
                if (optometricExam.getCreatedBy() != null) {
                    optometricExam.setCreateByUser(apUserService.getRecord(optometricExam.getCreatedBy()));
                }
                if (optometricExam.getUpdatedBy() != null) {
                    optometricExam.setUpdateByUser(apUserService.getRecord(optometricExam.getUpdatedBy()));
                }
                if (optometricExam.getDeletedBy() != null) {
                    optometricExam.setDeleteByUser(apUserService.getRecord(optometricExam.getDeletedBy()));
                }
                if(optometricExam.getVisionDiagnosis() != null) {
                    optometricExam.setIcdCode(icondCodeService.getRecord(optometricExam.getVisionDiagnosis()));
                }
                if(optometricExam.getColorVisionDiagnosis() != null) {
                    optometricExam.setIcdCode2(icondCodeService.getRecord(optometricExam.getColorVisionDiagnosis()));
                }
                apOptometricExamService.populateLovFields(optometricExam, lang);
            }

            response.setObject(optometricExams);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/save-treadmill-stress", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveTreadmillStressTest(@RequestBody ApTreadmillStress treadmillStress ,
                                                @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                @jakarta.annotation.Nullable @RequestHeader String lang

    ) {
        try {
            ParentResponse<ApTreadmillStress> response = new ParentResponse<>();
            apTreadmillStressService.saveRecord(treadmillStress);
            response.setObject(treadmillStress);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @GetMapping(value = "/treadmill-stress-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTreadmillStressList(@RequestParam Map<String, String> queryParams,
                                                   @Nullable @RequestHeader String facility_id,
                                                   @Nullable @RequestHeader String access_token,
                                                   @Nullable @RequestHeader Integer access_level,
                                                   @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApTreadmillStress>> response = new ParentResponse<>();

            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }

            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);

            List<ApTreadmillStress> treadmillStresses = apTreadmillStressService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_treadmill_stress where " + whereForTotal);

            for (ApTreadmillStress treadmillStresse : treadmillStresses) {
                if (treadmillStresse.getCreatedBy() != null) {
                    treadmillStresse.setCreateByUser(apUserService.getRecord(treadmillStresse.getCreatedBy()));
                }
                if (treadmillStresse.getUpdatedBy() != null) {
                    treadmillStresse.setUpdateByUser(apUserService.getRecord(treadmillStresse.getUpdatedBy()));
                }
                if (treadmillStresse.getDeletedBy() != null) {
                    treadmillStresse.setDeleteByUser(apUserService.getRecord(treadmillStresse.getDeletedBy()));
                }

                apTreadmillStressService.populateLovFields(treadmillStresse, lang);
            }

            response.setObject(treadmillStresses);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/save-complaint-symptoms", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveComplaintSymptoms(@RequestBody ApComplaintSymptoms complaintSymptoms ,
                                                     @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                     @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                     @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                     @jakarta.annotation.Nullable @RequestHeader String lang

    ) {
        try {
            ParentResponse<ApComplaintSymptoms> response = new ParentResponse<>();
            apComplaintSymptomsService.saveRecord(complaintSymptoms);
            response.setObject(complaintSymptoms);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(value = "/complaint-symptoms-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getComplaintSymptomsList(@RequestParam Map<String, String> queryParams,
                                                    @Nullable @RequestHeader String facility_id,
                                                    @Nullable @RequestHeader String access_token,
                                                    @Nullable @RequestHeader Integer access_level,
                                                    @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApComplaintSymptoms>> response = new ParentResponse<>();
            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);

            List<ApComplaintSymptoms> complaintSymptomsList = apComplaintSymptomsService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_complaint_symptoms where " + whereForTotal);

            for (ApComplaintSymptoms complaintSymptomse : complaintSymptomsList) {
                if (complaintSymptomse.getCreatedBy() != null) {
                    complaintSymptomse.setCreateByUser(apUserService.getRecord(complaintSymptomse.getCreatedBy()));
                }
                if (complaintSymptomse.getUpdatedBy() != null) {
                    complaintSymptomse.setUpdateByUser(apUserService.getRecord(complaintSymptomse.getUpdatedBy()));
                }
                if (complaintSymptomse.getDeletedBy() != null) {
                    complaintSymptomse.setDeleteByUser(apUserService.getRecord(complaintSymptomse.getDeletedBy()));
                }
                apComplaintSymptomsService.populateLovFields(complaintSymptomse, lang);
            }

            response.setObject(complaintSymptomsList);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @PostMapping(value = "/save-electrocardiogram-ecg", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveElectrocardiogramEcg(@RequestBody ApElectrocardiogramEcg electrocardiogramEcg ,
                                                     @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                     @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                     @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                     @jakarta.annotation.Nullable @RequestHeader String lang

    ) {
        try {
            ParentResponse<ApElectrocardiogramEcg> response = new ParentResponse<>();
            apElectrocardiogramEcgService.saveRecord(electrocardiogramEcg);
            response.setObject(electrocardiogramEcg);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @GetMapping(value = "/electrocardiogram-ecg-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getElectrocardiogramEcgList(@RequestParam Map<String, String> queryParams,
                                                    @Nullable @RequestHeader String facility_id,
                                                    @Nullable @RequestHeader String access_token,
                                                    @Nullable @RequestHeader Integer access_level,
                                                    @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApElectrocardiogramEcg>> response = new ParentResponse<>();

            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }

            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);

            List<ApElectrocardiogramEcg> electrocardiogramEcgs = apElectrocardiogramEcgService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_electrocardiogram_ecg where " + whereForTotal);

            for (ApElectrocardiogramEcg electrocardiogramEcg : electrocardiogramEcgs) {
                if (electrocardiogramEcg.getCreatedBy() != null) {
                    electrocardiogramEcg.setCreateByUser(apUserService.getRecord(electrocardiogramEcg.getCreatedBy()));
                }
                if (electrocardiogramEcg.getUpdatedBy() != null) {
                    electrocardiogramEcg.setUpdateByUser(apUserService.getRecord(electrocardiogramEcg.getUpdatedBy()));
                }
                if (electrocardiogramEcg.getDeletedBy() != null) {
                    electrocardiogramEcg.setDeleteByUser(apUserService.getRecord(electrocardiogramEcg.getDeletedBy()));
                }

                apElectrocardiogramEcgService.populateLovFields(electrocardiogramEcg, lang);
            }

            response.setObject(electrocardiogramEcgs);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @PostMapping(value = "/discharge-inpatient-encounter", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> DischargeEncounterInpatient(@RequestBody ApEncounter apEncounter,
                                                         @Nullable @RequestHeader String facility_id,
                                                         @Nullable @RequestHeader String access_token,
                                                         @Nullable @RequestHeader Integer access_level,
                                                         @Nullable @RequestHeader String lang,
                                                         @Nullable @RequestHeader String screenKey
    ) {
        try {
            ParentResponse<ApEncounter> response = new ParentResponse<>();
            apEncounter.setDischarge(true); // TO CLOSE INPATIENT ENCOUNTER
            apEncounterService.saveRecord(apEncounter);
            apEncounterService.populateLovFields(apEncounter, lang);
            ApPatient patient = apPatientService.getRecord(apEncounter.getPatientKey());
            ApAdmitOutpatientInpatient admitOutpatientInpatient = apAdmitOutpatientInpatientService.getList("to_encounter_key = '"+apEncounter.getKey()+"'").get(0);
            ApBed apBed = apBedService.getRecord(admitOutpatientInpatient.getBedKey());
            apBed.setStatusLkey("5258572711068224");
            apBedService.saveRecord(apBed);
            apPatientService.populateLovFields(patient, lang);

            apEncounter.setPatientObject(patient);
            response.setObject(apEncounter);
            response.setMsg("Visit Discharged Successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @PostMapping(value = "/admit-outpatient-inpatient", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> admitOutpatientInpatient(@RequestBody ApAdmitOutpatientInpatient admitOutpatientInpatient,
                                                      @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                      @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                      @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                      @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApAdmitOutpatientInpatient> response = new ParentResponse<>();
            ApEncounter encounter = apEncounterService.getRecord(admitOutpatientInpatient.getFromEncounterKey());

            BigDecimal waitingEncounterCount = DS.executeDecimalResultQuery(
                    "SELECT COUNT(0) FROM ap_encounter WHERE patient_key = " +"'"+encounter.getPatientKey()+"' AND encounter_status_lkey = "+"'5256965920133084'"
            );
            System.out.println("waitingEncounterCount"+waitingEncounterCount);
            if (waitingEncounterCount.compareTo(BigDecimal.ZERO) > 0)  {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                        .body(Map.of("message", "This Patient Has One More Encounter On The Waiting List."));
            }

            if (admitOutpatientInpatient.getInpatientDepartmentKey() == null || admitOutpatientInpatient.getInpatientDepartmentKey().isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                        .body(Map.of("message", "Please Select Department."));
            }


            if (admitOutpatientInpatient.getPhysicianKey() == null || admitOutpatientInpatient.getPhysicianKey().isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                        .body(Map.of("message", "Please Select Physician"));
            }

            ApAdmitOutpatientInpatient admit = apAdmitOutpatientInpatientService.admitToInpatient(encounter, admitOutpatientInpatient);
            response.setObject(admit);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }
    @GetMapping(value = "/waiting_encounter-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> waitingEncounterList(@RequestParam Map<String, String> queryParams,
                                                  @Nullable @RequestHeader String facility_id,
                                                  @Nullable @RequestHeader String access_token,
                                                  @Nullable @RequestHeader Integer access_level,
                                                  @Nullable @RequestHeader String lang) {

        try {
            ParentResponse<List<ApEncounter>> response = new ParentResponse<>();

            if ("true".equals(queryParams.get("ignore"))) {
                response.setObject(Collections.emptyList());
                return ResponseEntity.ok(response);
            }

            ListRequest listRequest   = new ListRequest(queryParams);
            String       where        = listRequest.buildWhereStatement();
            String       whereForTotal= listRequest.buildWhereStatement(true, false, false, false);

            List<ApEncounter> encounters = apEncounterService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery(
                    "SELECT COUNT(0) FROM ap_encounter WHERE " + whereForTotal
            );

            for (ApEncounter encounter : encounters) {
                encounter.setPractitionerObject(
                        apPractitionerService.getRecord(encounter.getPhysicianKey())
                );

                if (encounter.getResourceKey() != null) {
                    if ("2039534205961578".equals(encounter.getResourceTypeLkey())) {
                        ApDepartment dep = apDepartmentService.getRecord(encounter.getDepartmentKey());
                        if (dep != null) {
                            encounter.setDepartmentName(dep.getName());
                        }
                    }
                    encounter.setResourceObject(
                            apEncounterService.getResource(
                                    encounter.getResourceTypeLkey(),
                                    encounter.getResourceKey(),
                                    lang
                            )
                    );
                }

                ApAdmitOutpatientInpatient admit =
                        apAdmitOutpatientInpatientService.getRecordByToEncounterKey(encounter.getKey());
                encounter.setAdmitRecord(admit);
                if (admit != null) {
                    apAdmitOutpatientInpatientService.populateLovFields(admit, lang);
                }

                ApPatient patient = apPatientService.getRecord(encounter.getPatientKey());
                apPatientService.populateLovFields(patient, lang);
                encounter.setPatientObject(patient);

                apEncounterService.populateLovFields(encounter, lang);
            }

            apEncounterService.processPatientObservationStatus(encounters);

            response.setObject(encounters);
            response.setExtraNumeric(totalRecord);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("waitingEncounterList failed", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
    @PostMapping(value = "/patient_admission_from_waiting_list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> savePatientAdmissionFromWaitingList(@RequestBody ApAdmitOutpatientInpatient admitOutpatientInpatient ,
                                                                 @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                                 @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                                 @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                                 @jakarta.annotation.Nullable @RequestHeader String lang

    ) {
        try {
            ParentResponse<ApAdmitOutpatientInpatient> response = new ParentResponse<>();
            ApEncounter apEncounter = apEncounterService.getRecord(admitOutpatientInpatient.getToEncounterKey());
            apEncounter.setEncounterStatusLkey("91063195286200");   //TODO CONVERT KEY TO CODE
            List<ApResources> resourcesList = apResourcesService
                    .getList("resource_key = '" + admitOutpatientInpatient.getAdmissionDepartmentKey() + "'");

            if (resourcesList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                        .body(Map.of("message", "Admission Department not found in resources."));
            }

            ApResources resources = resourcesList.get(0);
            apEncounter.setResourceKey(resources.getKey());
            if (apEncounter.getDepartmentKey() != null) {
                apEncounter.setQueueNumber(apEncounterService.getLatestQueueNumber(new Date(), apEncounter.getDepartmentKey()));
            }
            String patientKey = apEncounter.getPatientKey();

            Map<String, Integer> counts =apEncounterService.countNewInpatientOrOngoingVisits(patientKey);

            int countOngoing = counts.getOrDefault("count_status_ongoing", 0);
            int countResourceAndStatus = counts.getOrDefault("count_resource_and_status", 0);
            int countOutpatientNewEncounter = counts.getOrDefault("count_status_outpatient_new", 0);
            if (countOngoing > 0 && apEncounter.getResourceTypeLkey().equals("4217389643435490")) {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                        .body(Map.of("message", "The Patient has Ongoing Encounter."));
            }
            if (countResourceAndStatus > 0 && apEncounter.getResourceTypeLkey().equals("4217389643435490")) {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                        .body(Map.of("message", "Patient is already inpatient."));
            }

            if (countResourceAndStatus > 0 ) {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                        .body(Map.of("message", "This Patient Has Inpatient Encounter"));
            }
            if(countOutpatientNewEncounter > 0 && apEncounter.getResourceTypeLkey().equals("4217389643435490")){
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                        .body(Map.of("message", "This Patient Has New Outpatient Encounter"));
            }

            if(admitOutpatientInpatient.getBedKey().isEmpty()){
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                        .body(Map.of("message", "Please Select a Bed"));
            }
            if(admitOutpatientInpatient.getRoomKey().isEmpty()){
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                        .body(Map.of("message", "Please Select a Room"));
            }
            if(admitOutpatientInpatient.getAdmissionDepartmentKey() ==null||admitOutpatientInpatient.getAdmissionDepartmentKey().isEmpty()){
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                        .body(Map.of("message", "Please Select  Admission Department"));
            }
            ApBed apBed = apBedService.getRecord(admitOutpatientInpatient.getBedKey());
            apBed.setStatusLkey("5258252390107597");
            apBedService.saveRecord(apBed);
            apEncounterService.saveRecord(apEncounter);
            apAdmitOutpatientInpatientService.saveRecord(admitOutpatientInpatient);
            response.setObject(admitOutpatientInpatient);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @GetMapping(value = "/inpatient-encounter-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> inpatientEncounterList(@RequestParam Map<String, String> queryParams,
                                                    @RequestHeader("department_key") String depKey,
                                                    @Nullable @RequestHeader String facility_id,
                                                    @Nullable @RequestHeader String access_token,
                                                    @Nullable @RequestHeader Integer access_level,
                                                    @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApEncounter>> response = new ParentResponse<>();

            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }

            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            List<ApEncounter> encounters = apEncounterService.getList(where);
            List<ApEncounter> filteredEncounters = new ArrayList<>();

            for (ApEncounter encounter : encounters) {
                encounter.setPractitionerObject(apPractitionerService.getRecord(encounter.getPhysicianKey()));

                if (encounter.getResourceKey() != null) {
                    if (encounter.getResourceTypeLkey().equals("4217389643435490")) {
                        ApResources apResources = apResourcesService.getRecord(encounter.getResourceKey());
                        ApDepartment department = apDepartmentService.getRecord(apResources.getResourceKey());
                        if (department != null) {
                            encounter.setDepartmentName(department.getName());
                            String departmentKey = department.getKey();
                            if (depKey != null && !depKey.isBlank() && !departmentKey.equals(depKey)) {
                                continue;
                            }

                        }
                    }
                    encounter.setResourceObject(apEncounterService.getResource(encounter.getResourceTypeLkey(), encounter.getResourceKey(), lang));
                }

                if (encounter.getPractitionerObject() != null) {
                    apPractitionerService.populateLovFields(encounter.getPractitionerObject(), lang);
                }

                ApPatient patient = apPatientService.getRecord(encounter.getPatientKey());
                patient.setHasAllergy(apPatientService.getHasAllergy(encounter.getPatientKey()));
                patient.setHasWarning(apPatientService.getHasWarning(encounter.getPatientKey()));
                apPatientService.populateLovFields(patient, lang);
                encounter.setPatientObject(patient);

                encounter.setDiagnosis(apEncounterService.getDiagnosis(encounter.getKey()));
                encounter.setHasOrder(apEncounterService.getHasOrder(encounter.getKey()));
                encounter.setHasPrescription(apEncounterService.getHasPrescription(encounter.getKey()));
                encounter.setHasAllergy(apEncounterService.getHasAllergy(encounter.getKey()));
                encounter.setHasObservation(apEncounterService.getHasObservation(encounter.getKey()));
                apEncounterService.populateLovFields(encounter, lang);

                ApAdmitOutpatientInpatient admitObject = apAdmitOutpatientInpatientService
                        .getList("to_encounter_key = '" + encounter.getKey() + "'").get(0);

                encounter.setApBed(apBedService.getRecord(admitObject.getBedKey()));
                apBedService.populateLovFields(encounter.getApBed(), lang);

                encounter.setApRoom(apRoomService.getRecord(admitObject.getRoomKey()));
                apRoomService.populateLovFields(encounter.getApRoom(), lang);

                filteredEncounters.add(encounter);
                ApAdmitOutpatientInpatient admit =
                        apAdmitOutpatientInpatientService.getRecordByToEncounterKey(encounter.getKey());
                encounter.setAdmitRecord(admit);
                if (admit != null) {
                    apAdmitOutpatientInpatientService.populateLovFields(admit, lang);
                }

            }

            apEncounterService.processPatientObservationStatus(filteredEncounters);
            response.setObject(filteredEncounters);
            response.setExtraNumeric(new BigDecimal(filteredEncounters.size()));

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/save-bed-transaction", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveBedTransaction(@RequestBody ApBedTransactions bedTransactions ,
                                                                 @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                                 @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                                 @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                                 @jakarta.annotation.Nullable @RequestHeader String lang

    ) {
        try {
            ParentResponse<ApBedTransactions> response = new ParentResponse<>();
            if( bedTransactions.getToBedKey() == null ||bedTransactions.getToBedKey().isEmpty()){
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                        .body(Map.of("message", "Please Select a Bed"));
            }
            if(bedTransactions.getToRoomKey() == null || bedTransactions.getToRoomKey().isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                        .body(Map.of("message", "Please Select a Room"));
            }
            ApAdmitOutpatientInpatient admitOutpatientInpatient = apAdmitOutpatientInpatientService.getList("to_encounter_key = '"+bedTransactions.getEncounterKey()+"'").get(0);
            ApBed firstBed = apBedService.getRecord(admitOutpatientInpatient.getBedKey());
            firstBed.setStatusLkey("5258572711068224");
            apBedService.saveRecord(firstBed);
            ApBed secondBed = apBedService.getRecord(bedTransactions.getToBedKey());
            secondBed.setStatusLkey("5258252390107597");
            apBedService.saveRecord(secondBed);

            admitOutpatientInpatient.setBedKey(bedTransactions.getToBedKey());
            admitOutpatientInpatient.setRoomKey(bedTransactions.getToRoomKey());
            apAdmitOutpatientInpatientService.saveRecord(admitOutpatientInpatient);

            apBedTransactionsService.saveRecord(bedTransactions);
            response.setObject(bedTransactions);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @GetMapping(value = "/bed-transactions-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> bedTransactionsList(@RequestParam Map<String, String> queryParams,
                                                    @Nullable @RequestHeader String facility_id,
                                                    @Nullable @RequestHeader String access_token,
                                                    @Nullable @RequestHeader Integer access_level,
                                                    @Nullable @RequestHeader String lang) {
        try {

            ParentResponse<List<ApBedTransactions>> response = new ParentResponse<>();

            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }

            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false,false);
            List<ApBedTransactions> bedTransactions = apBedTransactionsService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_bed where " + whereForTotal);
            for (ApBedTransactions transaction : bedTransactions) {
                if(transaction.getFromRoomKey()!=null){
                    transaction.setFromRoom(apRoomService.getRecord(transaction.getFromRoomKey()));
                    apRoomService.populateLovFields(transaction.getFromRoom(),lang);
                }
                if(transaction.getToRoomKey()!=null){
                    transaction.setToRoom(apRoomService.getRecord(transaction.getToRoomKey()));
                    apRoomService.populateLovFields(transaction.getToRoom(),lang);
                }
                if(transaction.getFromBedKey() !=null){
                    transaction.setFromBed(apBedService.getRecord(transaction.getFromBedKey()));
                    apBedService.populateLovFields(transaction.getFromBed(),lang);
                }
                if(transaction.getToBedKey() !=null){
                    transaction.setToBed(apBedService.getRecord(transaction.getToBedKey()));
                    apBedService.populateLovFields(transaction.getToBed(),lang);
                }
                if(transaction.getPatientKey() !=null){
                    transaction.setPatient(apPatientService.getRecord(transaction.getPatientKey()));
                    apPatientService.populateLovFields(transaction.getPatient(),lang);
                }
                ApAdmitOutpatientInpatient admitOutpatientInpatient = apAdmitOutpatientInpatientService.getList("to_encounter_key = '"+transaction.getEncounterKey()+"'").get(0);
                transaction.setAdmitOutpatientInpatient(admitOutpatientInpatient);
                apAdmitOutpatientInpatientService.populateLovFields(transaction.getAdmitOutpatientInpatient(),lang);
            }

            response.setObject(bedTransactions);
            response.setExtraNumeric(totalRecord);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);

        }
    }
    @PostMapping(value = "/save-pain-assessment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> savePainAssessment(@RequestBody ApPainAssessment painAssessment ,
                                                   @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                   @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                   @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                   @jakarta.annotation.Nullable @RequestHeader String lang

    ) {
        try {
            ParentResponse<ApPainAssessment> response = new ParentResponse<>();
            apPainAssessmentService.saveRecord(painAssessment);
            response.setObject(painAssessment);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @GetMapping(value = "/pain-assessment-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPainAssessmentList(@RequestParam Map<String, String> queryParams,
                                                      @Nullable @RequestHeader String facility_id,
                                                      @Nullable @RequestHeader String access_token,
                                                      @Nullable @RequestHeader Integer access_level,
                                                      @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApPainAssessment>> response = new ParentResponse<>();

            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }

            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);

            List<ApPainAssessment> painAssessments = apPainAssessmentService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_pain_assessment where " + whereForTotal);

            for (ApPainAssessment  painAssessment : painAssessments) {
                apPainAssessmentService.populateLovFields( painAssessment, lang);
            }

            response.setObject(painAssessments);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @PostMapping(value = "/save-inpatient-chief-complain", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveChiefComplain(@RequestBody ApInpatientChiefComplain chiefComplain ,
                                                @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                @jakarta.annotation.Nullable @RequestHeader String lang

    ) {
        try {
            ParentResponse<ApInpatientChiefComplain> response = new ParentResponse<>();
            apInpatientChiefComplainService.saveRecord(chiefComplain);
            response.setObject(chiefComplain);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @GetMapping(value = "/inpatient-chief-complain-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getInpatientChiefComplainList(@RequestParam Map<String, String> queryParams,
                                                   @Nullable @RequestHeader String facility_id,
                                                   @Nullable @RequestHeader String access_token,
                                                   @Nullable @RequestHeader Integer access_level,
                                                   @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApInpatientChiefComplain>> response = new ParentResponse<>();

            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }

            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);

            List<ApInpatientChiefComplain> chiefComplains = apInpatientChiefComplainService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_inpatient_chief_complain where " + whereForTotal);

            for (ApInpatientChiefComplain  chiefComplain : chiefComplains) {

                apInpatientChiefComplainService.populateLovFields( chiefComplain, lang);
            }

            response.setObject(chiefComplains);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/save-general-assessment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveGeneralAssessment(@RequestBody ApGeneralAssessment generalAssessment ,
                                               @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                               @jakarta.annotation.Nullable @RequestHeader String access_token,
                                               @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                               @jakarta.annotation.Nullable @RequestHeader String lang

    ) {
        try {
            ParentResponse<ApGeneralAssessment> response = new ParentResponse<>();
            apGeneralAssessmentService.saveRecord(generalAssessment);
            response.setObject(generalAssessment);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @GetMapping(value = "/general-assessment-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getGeneralAssessmentList(@RequestParam Map<String, String> queryParams,
                                                           @Nullable @RequestHeader String facility_id,
                                                           @Nullable @RequestHeader String access_token,
                                                           @Nullable @RequestHeader Integer access_level,
                                                           @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApGeneralAssessment>> response = new ParentResponse<>();

            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }

            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);

            List<ApGeneralAssessment> generalAssessments = apGeneralAssessmentService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_general_assessment where " + whereForTotal);

            for (ApGeneralAssessment  generalAssessment : generalAssessments) {

                apGeneralAssessmentService.populateLovFields(generalAssessment, lang);
            }

            response.setObject(generalAssessments);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @PostMapping(value = "/save-functional-assessment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveFunctionalAssessment(@RequestBody ApFunctionalAssessment functionalAssessment ,
                                                   @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                   @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                   @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                   @jakarta.annotation.Nullable @RequestHeader String lang

    ) {
        try {
            ParentResponse<ApFunctionalAssessment> response = new ParentResponse<>();
            apFunctionalAssessmentService.saveRecord(functionalAssessment);
            response.setObject(functionalAssessment);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @GetMapping(value = "/functional-assessment-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> geFunctionalAssessmentList(@RequestParam Map<String, String> queryParams,
                                                      @Nullable @RequestHeader String facility_id,
                                                      @Nullable @RequestHeader String access_token,
                                                      @Nullable @RequestHeader Integer access_level,
                                                      @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApFunctionalAssessment>> response = new ParentResponse<>();

            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }

            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);

            List<ApFunctionalAssessment> functionalAssessments = apFunctionalAssessmentService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_functional_assessment where " + whereForTotal);

            for (ApFunctionalAssessment  functionalAssessment : functionalAssessments) {

                apFunctionalAssessmentService.populateLovFields(functionalAssessment, lang);
            }

            response.setObject(functionalAssessments);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @PostMapping(value = "/save-medication-reconciliation", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveMedicationReconciliation(@RequestBody ApMedicationReconciliation medicationReconciliation ,
                                                          @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                          @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                          @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                          @jakarta.annotation.Nullable @RequestHeader String lang

    ) {
        try {
            ParentResponse<ApMedicationReconciliation> response = new ParentResponse<>();
            apMedicationReconciliationService.saveRecord(medicationReconciliation);
            response.setObject(medicationReconciliation);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @GetMapping(value = "/medication-reconciliation-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMedicationReconciliationList(@RequestParam Map<String, String> queryParams,
                                                             @Nullable @RequestHeader String facility_id,
                                                             @Nullable @RequestHeader String access_token,
                                                             @Nullable @RequestHeader Integer access_level,
                                                             @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApMedicationReconciliation>> response = new ParentResponse<>();

            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }

            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);

            List<ApMedicationReconciliation> medicationReconciliations = apMedicationReconciliationService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_medication_reconciliation where " + whereForTotal);
            for (ApMedicationReconciliation  medicationReconciliation : medicationReconciliations) {
                medicationReconciliation.setApActiveIngredient(apActiveIngredientService.getRecord(medicationReconciliation.getActiveIngredientKey()));
                apActiveIngredientService.populateLovFields(medicationReconciliation.getApActiveIngredient(), lang);
                apMedicationReconciliationService.populateLovFields(medicationReconciliation, lang);
            }

            response.setObject(medicationReconciliations);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @PostMapping(value = "/save-transfer-patient", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveMedicationReconciliation(@RequestBody ApTransferPatient apTransferPatient ,
                                                          @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                          @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                          @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                          @jakarta.annotation.Nullable @RequestHeader String lang

    ) {
        try {
            ParentResponse<ApTransferPatient> response = new ParentResponse<>();
            apTransferPatientService.saveRecord(apTransferPatient);
            response.setObject(apTransferPatient);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

}