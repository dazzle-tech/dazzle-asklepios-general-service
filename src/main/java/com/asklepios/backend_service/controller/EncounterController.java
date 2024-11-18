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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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


    public EncounterController(ApPatientService apPatientService, RestTemplate restTemplate, PublicServices publicServices, ValidationService validationService, ApEncounterService apEncounterService, ApEncounterAppliedServiceService apEncounterAppliedServiceService, ApServiceService apServiceService, ApReviewOfSystemService apReviewOfSystemService, ApPhysicalExamAreaService apPhysicalExamAreaService, ApIcdCodeService apIcdCodeService, ApPatientDiagnoseService apPatientDiagnoseService, ApPatientPlanService apPatientPlanService) {
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
            List<ApEncounter> encounters = apEncounterService.getListWithDepartmentName(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_encounter where " + whereForTotal);
            for (ApEncounter encounter : encounters) {
                apEncounterService.populateLovFields(encounter, lang);
                ApPatient patient = apPatientService.getRecord(encounter.getPatientKey());
                apPatientService.populateLovFields(patient, lang);
                encounter.setPatientObject(patient);
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
                apEncounter.setEncounterStatusLkey("91073223480100"); // TODO replace with redis by lov code (ENC_STATUS/IN_PROGRESS)


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

            // إذا كانت المعايير تحتوي على "ignore=true"، أعد قائمة فارغة
            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }

            // بناء معايير البحث من queryParams
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);

            // استرداد خطط المرضى بناءً على المعايير
            List<ApPatientPlan> patientPlans = apPatientPlanService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_patient_plan where " + whereForTotal);

            // معالجة كل خطة (إضافة أي معالجات ضرورية)
            for (ApPatientPlan plan : patientPlans) {
                apPatientPlanService.populateLovFields(plan, lang); // افترض أن هذه الطريقة تضيف البيانات المساعدة
            }

            // إعداد الاستجابة النهائية
            response.setObject(patientPlans);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }


}
