package com.asklepios.backend_service.controller;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.entity.ApPatientEntity;
import com.asklepios.backend_service.model.generated.pojo.*;
import com.asklepios.backend_service.model.pojo.ValidationResult;
import com.asklepios.backend_service.model.pojo.request.ListRequest;
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
    public EncounterController(ApPatientService apPatientService, RestTemplate restTemplate, PublicServices publicServices, ValidationService validationService, ApEncounterService apEncounterService, ApEncounterAppliedServiceService apEncounterAppliedServiceService, ApServiceService apServiceService, ApReviewOfSystemService apReviewOfSystemService, ApPhysicalExamAreaService apPhysicalExamAreaService, ApIcdCodeService apIcdCodeService, ApPatientDiagnoseService apPatientDiagnoseService, ApPatientPlanService apPatientPlanService, ApPatientEncounterOrderService apPatientEncounterOrderService, ApPrescriptionService apPrescriptionService, ApPrescriptionInstructionService apPrescriptionInstructionService, ApCustomeInstructionsService apCustomeInstructionsService, ApPrescriptionMedicationsService apPrescriptionMedicationsService, ApConsultationOrderService apConsultationOrderService, ApVisitAllergiesService apVisitAllergiesService, ApDrugOrderService apDrugOrderService, ApDrugOrderMedicationsService apDrugOrderMedicationsService, ApProcedureService apProcedureService, ApDiagnosticOrdersService apDiagnosticOrdersService, ApDiagnosticOrderTestsService apDiagnosticOrderTestsService, ApDiagnosticTestService apDiagnosticTestService, ApPractitionerService apPractitionerService, ApDiagnosticOrderTestsNotesService apDiagnosticOrderTestsNotesService, ApDiagnosticOrderTestsSamplesService apDiagnosticOrderTestsSamplesService, ApDiagnosticOrderTestsResultService apDiagnosticOrderTestsResultService, ApDiagnosticOrderTestsResultNotesService apDiagnosticOrderTestsResultNotesService, ApDiagnosticTestNormalRangeService apDiagnosticTestNormalRangeService) {
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
                System.out.println("encounter.getPhysicianKey()--->"+encounter.getPhysicianKey());
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

                BigDecimal lastVisitId = DS.executeDecimalResultQuery("select max(visit_id) from ap_encounter");

                BigDecimal newVisitId;
                System.out.println("lastVisitId"+lastVisitId);
                if (lastVisitId == null) {
                    newVisitId = BigDecimal.valueOf(100);
                } else {
                    newVisitId = lastVisitId.add(BigDecimal.ONE);
                }
                System.out.println("newVisitId"+newVisitId);

                apEncounter.setVisitId(newVisitId.toString());


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
            System.out.println("=============>iam in get prescription");
            List<ApPrescription> prescriptions = apPrescriptionService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_prescription where " + whereForTotal);

            for (ApPrescription pre : prescriptions) {

                apPrescriptionService.populateLovFields(pre, lang);
                pre.setEncounter(apDrugOrderService.getEncounter(pre.getVisitKey()));
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

            System.out.println("========================>"+response.getObject().getKey());
            System.out.println("========================>"+response.getObject().getInstructionsTypeLkey());
            if(request.getInstructionsTypeLkey().equals("3010606785535008")  ){
                System.out.println("========================>iii");


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

            ListRequest listRequest = new ListRequest(queryParams);
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
            System.out.println("+++++++++++++++++++++++++"+prescriptionMedications.getStatusLkey());
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
                o.setEncounter(apDrugOrderService.getEncounter(o.getVisitKey()));
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

            for (ApProcedure all: pro) {

                apProcedureService.populateLovFields(all, lang);
                all.setProcedureName(apProcedureService.getProcedureName(all.getProcedureNameKey()));

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
                o.getEncounter().setDiagnosis(apEncounterService.getDiagnosis( o.getEncounter().getKey()));
                o.getPatient().setHasAllergy(apPatientService.getHasAllergy(o.getPatient().getKey()));
                o.getPatient().setHasWarning(apPatientService.getHasWarning(o.getPatient().getKey()));
                apDiagnosticOrdersService.populateLovFields(o, lang);
                apPatientService.populateLovFields(o.getPatient(), lang);
                apEncounterService.populateLovFields(o.getEncounter(), lang);

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
            boolean flag=false;
            Set<String> statusSet = tests.stream()
                    .map(ApDiagnosticOrderTests::getProcessingStatusLkey)
                    .collect(Collectors.toSet());
            if (statusSet.size() == 1) {

                String value = statusSet.iterator().next();

                String firstElement = statusSet.iterator().next();
                ApDiagnosticOrders order = apDiagnosticOrdersService.getRecord(request.getOrderKey());
                order.setLabStatusLkey(firstElement);
                apDiagnosticOrdersService.saveRecord(order);
            } else {

                ApDiagnosticOrders order = apDiagnosticOrdersService.getRecord(request.getOrderKey());
                order.setLabStatusLkey("6472790341892805");
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


}
