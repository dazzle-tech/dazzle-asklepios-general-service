package com.asklepios.backend_service.controller;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.*;
import com.asklepios.backend_service.model.pojo.request.ListRequest;
import com.asklepios.backend_service.model.pojo.response.ParentResponse;
import com.asklepios.backend_service.service.*;
import com.asklepios.backend_service.model.generated.pojo.ApOperationRequests;
import com.asklepios.backend_service.service.ApOperationRequestsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/operation")

@Slf4j
public class OperationRequestsController {

    @Autowired
    private final ApOperationRequestsService apOperationRequestsService;
    private final ApOperationSetupService operationSetupService;
    private final ApOperationCodingService operationCodingService;
    private final ApOperationPriceListService operationPriceListService;
   private final ApOperationAnesthesiaCarePlanService operationAnesthesiaCarePlanService;
   private final ApPreOperationChecklistService apPreOperationChecklistService;
   private final ApPatientService apPatientService;
   private final ApEncounterService apEncounterService;
   private  final ApIcdCodeService apIcdCodeService;
   private final ApOperationStaffService apOperationStaffService;
   private final ApUserService apUserService;
   private final ApOperationAnesthesiaInductionMonitoringService apOperationAnesthesiaInductionMonitoringService;
   private final ApOperationNameLogService apOperationNameLogService;
   private final ApOperationInductionService apOperationInductionService;
   private final ApOperationPatientArrivalService apOperationPatientArrivalService;
   private final ApOperationPreMedicationService apOperationPreMedicationService;
   private final ApOperationIntraoperativeMonitoringService apOperationIntraoperativeMonitoringService;
   private final ApPreOperativeTimeoutService apPreOperativeTimeoutService;
   private final ApLovValuesService apLovValuesService;
    public OperationRequestsController(
            ApOperationRequestsService apOperationRequestsService, ApOperationSetupService operationSetupService,
            ApOperationCodingService operationCodingService,
            ApOperationPriceListService operationPriceListService, ApOperationAnesthesiaCarePlanService operationAnesthesiaCarePlanService, ApPreOperationChecklistService apPreOperationChecklistService, ApPatientService apPatientService, ApEncounterService apEncounterService, ApIcdCodeService apIcdCodeService, ApOperationStaffService apOperationStaffService, ApUserService apUserService, ApOperationAnesthesiaInductionMonitoringService apOperationAnesthesiaInductionMonitoringService, ApOperationNameLogService apOperationNameLogService, ApOperationInductionService apOperationInductionService, ApOperationPatientArrivalService apOperationPatientArrivalService, ApOperationPreMedicationService apOperationPreMedicationService, ApOperationIntraoperativeMonitoringService apOperationIntraoperativeMonitoringService, ApPreOperativeTimeoutService apPreOperativeTimeoutService, ApLovValuesService apLovValuesService
    ) {
        this.apOperationRequestsService = apOperationRequestsService;
        this.operationSetupService = operationSetupService;
        this.operationCodingService = operationCodingService;
        this.operationPriceListService = operationPriceListService;
        this.operationAnesthesiaCarePlanService = operationAnesthesiaCarePlanService;
        this.apPreOperationChecklistService = apPreOperationChecklistService;
        this.apPatientService = apPatientService;
        this.apEncounterService = apEncounterService;
        this.apIcdCodeService = apIcdCodeService;
        this.apOperationStaffService = apOperationStaffService;
        this.apUserService = apUserService;
        this.apOperationAnesthesiaInductionMonitoringService = apOperationAnesthesiaInductionMonitoringService;
        this.apOperationNameLogService = apOperationNameLogService;
        this.apOperationInductionService = apOperationInductionService;
        this.apOperationPatientArrivalService = apOperationPatientArrivalService;
        this.apOperationPreMedicationService = apOperationPreMedicationService;
        this.apOperationIntraoperativeMonitoringService = apOperationIntraoperativeMonitoringService;
        this.apPreOperativeTimeoutService = apPreOperativeTimeoutService;
        this.apLovValuesService = apLovValuesService;
    }

    @PostMapping(value = "/save-operation-request", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveOperationRequests(@RequestBody ApOperationRequests request,
                                                   @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                   @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                   @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                   @jakarta.annotation.Nullable @RequestHeader String lang

    ) {
        try {

            ParentResponse<ApOperationRequests> response = new ParentResponse<>();
            if (request.getKey() == null && apOperationRequestsService
                    .getList("encounter_key = '" + request.getEncounterKey() + "' AND patient_key = '" + request.getPatientKey() + "' AND status_lkey = '3621653475992516'")
                    .size() > 0) {
                response.setMsg("You can't add a new operation because there is already one in request state.");
                response.setObject(new ApOperationRequests());
                return ResponseEntity.ok(response);
            }
            apOperationRequestsService.saveRecord(request);
            response.setMsg("Saved Successfuly");
            response.setObject(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @GetMapping(value = "/operation-request-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOperationRequestsList(@RequestParam Map<String, String> queryParams,
                                                      @RequestHeader(required = false) String facility_id,
                                                      @RequestHeader(required = false) String access_token,
                                                      @RequestHeader(required = false) Integer access_level,
                                                      @RequestHeader(required = false) String lang) {
        try {
            ParentResponse<List<ApOperationRequests>> response = new ParentResponse<>();

            if ("true".equals(queryParams.get("ignore"))) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }

            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);

            List<ApOperationRequests> list = apOperationRequestsService.getList(where);
            BigDecimal total = DS.executeDecimalResultQuery("SELECT COUNT(0) FROM ap_operation_requests WHERE " + whereForTotal);

            for (ApOperationRequests item : list) {
                item.setPatient(apPatientService.getRecord(item.getPatientKey()));
                apPatientService.populateLovFields(item.getPatient(), lang);
                item.setEncounter(apEncounterService.getRecord(item.getEncounterKey()));
                apEncounterService.populateLovFields(item.getEncounter(), lang);
                item.setDiagnosis(apIcdCodeService.getRecord(item.getDiagnosisKey()));
                apIcdCodeService.populateLovFields(item.getDiagnosis(), lang);
                apOperationRequestsService.populateLovFields(item, lang);
            }

            response.setObject(list);
            response.setExtraNumeric(total);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @GetMapping(value = "/operation-request-by-status", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRequestedOperationByEncounterAndPatient(@RequestParam String encounterKey,
                                                                        @RequestParam String patientKey,
                                                                        @RequestHeader(required = false) String lang) {
        try {
            ParentResponse<ApOperationRequests> response = new ParentResponse<>();
            // TODO  status lvalue for request status
            String where = String.format(
                    "encounter_key = '%s' AND patient_key = '%s' AND status_lkey = '3621653475992516'",
                    encounterKey, patientKey
            );

            List<ApOperationRequests> list = apOperationRequestsService.getList(where);

            if (list.isEmpty()) {
                response.setMsg("No request found");
                return ResponseEntity.ok(response);
            }

            ApOperationRequests request = list.get(0);
            apOperationRequestsService.populateLovFields(request, lang);

            response.setObject(request);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping(value = "/operation-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> operationList(@RequestParam Map<String, String> queryParams,
                                           @RequestHeader(required = false) String lang) {
        try {
            ParentResponse<List<ApOperationSetup>> response = new ParentResponse<>();
            if ("true".equals(queryParams.get("ignore"))) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false);
            List<ApOperationSetup> list = operationSetupService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_operation_setup where " + whereForTotal);
            for (ApOperationSetup item : list) {

                operationSetupService.populateLovFields(item, lang);
            }
            response.setObject(list);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error fetching operation list", e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping(value = "/save-operation", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveOperation(@RequestBody ApOperationSetup apOperationSetup) {
        try {
            ParentResponse<ApOperationSetup> response = new ParentResponse<>();

            operationSetupService.saveRecord(apOperationSetup);

            response.setObject(apOperationSetup);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error saving operation", e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping(value = "/remove-operation", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeOperation(@RequestBody ApOperationSetup apOperationSetup) {
        try {
            ParentResponse<ApOperationSetup> response = new ParentResponse<>();
            operationSetupService.deleteRecord(apOperationSetup);
            response.setObject(apOperationSetup);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error deleting operation", e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping(value = "/operation-coding-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> operationCodingList(@RequestParam Map<String, String> queryParams,
                                                 @RequestHeader(required = false) String lang) {
        try {
            ParentResponse<List<ApOperationCoding>> response = new ParentResponse<>();
            if ("true".equals(queryParams.get("ignore"))) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false);
            List<ApOperationCoding> list = operationCodingService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_operation_coding where " + whereForTotal);
            for (ApOperationCoding item : list) {
                operationCodingService.populateLovFields(item, lang);
            }
            response.setObject(list);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error fetching operation coding list", e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping(value = "/save-operation-coding", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveOperationCoding(@RequestBody ApOperationCoding apOperationCoding) {
        try {
            ParentResponse<ApOperationCoding> response = new ParentResponse<>();
            operationCodingService.saveRecord(apOperationCoding);
            response.setObject(apOperationCoding);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error saving operation coding", e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping(value = "/remove-coding", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteOperationCoding(@RequestBody ApOperationCoding apOperationCoding) {
        try {
            ParentResponse<ApOperationCoding> response = new ParentResponse<>();
            operationCodingService.deleteCoding(apOperationCoding);
            response.setObject(apOperationCoding);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error deleting operation coding", e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping(value = "/operation-price-list-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> operationPriceList(@RequestParam Map<String, String> queryParams,
                                                @RequestHeader(required = false) String lang) {
        try {
            ParentResponse<List<ApOperationPriceList>> response = new ParentResponse<>();
            if ("true".equals(queryParams.get("ignore"))) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false);
            List<ApOperationPriceList> list = operationPriceListService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_operation_price_list where " + whereForTotal);
            for (ApOperationPriceList item : list) {
                operationPriceListService.populateLovFields(item, lang);
            }
            response.setObject(list);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error fetching operation price list", e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping(value = "/save-operation-price-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveOperationPriceList(@RequestBody ApOperationPriceList apOperationPriceList) {
        try {
            ParentResponse<ApOperationPriceList> response = new ParentResponse<>();
            operationPriceListService.saveRecord(apOperationPriceList);
            response.setObject(apOperationPriceList);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error saving operation price list", e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping(value = "/remove-price-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteOperationPriceList(@RequestBody ApOperationPriceList apOperationPriceList) {
        try {
            ParentResponse<ApOperationPriceList> response = new ParentResponse<>();
            operationPriceListService.deletePriceList(apOperationPriceList);
            response.setObject(apOperationPriceList);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error deleting operation price list", e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping(value = "/operation-care-plan-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> operationCarePlan(@RequestParam Map<String, String> queryParams,
                                                @RequestHeader(required = false) String lang) {
        try {
            ParentResponse<List<ApOperationAnesthesiaCarePlan>> response = new ParentResponse<>();
            if ("true".equals(queryParams.get("ignore"))) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false);
            List<ApOperationAnesthesiaCarePlan> list = operationAnesthesiaCarePlanService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_operation_anesthesia_care_plan where " + whereForTotal);
            for (ApOperationAnesthesiaCarePlan item : list) {
                operationAnesthesiaCarePlanService.populateLovFields(item, lang);
            }
            response.setObject(list);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error fetching operation price list", e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping(value = "/save-operation-care-plan", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveOperationCarePlan(@RequestBody ApOperationAnesthesiaCarePlan apOperationAnesthesiaCarePlan) {
        try {
            ParentResponse<ApOperationAnesthesiaCarePlan> response = new ParentResponse<>();
            operationAnesthesiaCarePlanService.saveRecord(apOperationAnesthesiaCarePlan);
            response.setObject(apOperationAnesthesiaCarePlan);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error saving operation price list", e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


    @PostMapping(value = "/save-pre-operation-checklist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> savePreOperationChecklist(@RequestBody ApPreOperationChecklist apPreOperationChecklist) {
        try {
            ParentResponse<ApPreOperationChecklist> response = new ParentResponse<>();
            apPreOperationChecklistService.saveRecord(apPreOperationChecklist);
            response.setObject(apPreOperationChecklist);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error saving operation coding", e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


    @GetMapping(value = "/pre-operation-checklist-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> PreOperationChecklistList(@RequestParam Map<String, String> queryParams,
                                               @RequestHeader(required = false) String lang) {
        try {
            ParentResponse<List<ApPreOperationChecklist>> response = new ParentResponse<>();
            if ("true".equals(queryParams.get("ignore"))) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false);
            List<ApPreOperationChecklist> list = apPreOperationChecklistService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_pre_operation_checklist where " + whereForTotal);
            for (ApPreOperationChecklist item : list) {
                apPreOperationChecklistService.populateLovFields(item, lang);
            }
            response.setObject(list);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error fetching operation checklist", e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @GetMapping("/get-checklist-list-by-operation-id")
    public ResponseEntity<?> getOperationChecklistList(
            @RequestParam("operation_key") String operationKey,
            @RequestHeader(name = "lang", required = false) String lang) throws SQLException {

        ParentResponse<ApPreOperationChecklist> response = new ParentResponse<>();


        String where = "operation_key = '" + operationKey + "' ORDER BY created_at DESC";

        List<ApPreOperationChecklist> checklistList = apPreOperationChecklistService.getList(where);


        if (checklistList != null && !checklistList.isEmpty()) {
            ApPreOperationChecklist latestChecklist = checklistList.get(0);
            apPreOperationChecklistService.populateLovFields(latestChecklist, lang);
            response.setObject(latestChecklist);
        } else {
            response.setObject(new ApPreOperationChecklist());
        }

        return ResponseEntity.ok(response);
    }


    @GetMapping(value = "/operation-staff-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOperationStaffList(@RequestParam Map<String, String> queryParams,
                                                   @RequestHeader(required = false) String facility_id,
                                                   @RequestHeader(required = false) String access_token,
                                                   @RequestHeader(required = false) Integer access_level,
                                                   @RequestHeader(required = false) String lang) {
        try {
            ParentResponse<List<ApOperationStaff>> response = new ParentResponse<>();

            if ("true".equals(queryParams.get("ignore"))) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }

            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);

            List<ApOperationStaff> list = apOperationStaffService
.getList(where);
            BigDecimal total = DS.executeDecimalResultQuery("SELECT COUNT(0) FROM ap_operation_staff WHERE " + whereForTotal);

            for (ApOperationStaff item : list) {
                item.setUser(apUserService.getRecord(item.getUserKey()));
                apOperationStaffService
.populateLovFields(item, lang);
            }

            response.setObject(list);
            response.setExtraNumeric(total);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/save-operation-staff", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveOperationStaff(@RequestBody ApOperationStaff request,
                                                @RequestHeader(required = false) String facility_id,
                                                @RequestHeader(required = false) String ap_icd_codeaccess_token,
                                                @RequestHeader(required = false) Integer access_level,
                                                @RequestHeader(required = false) String lang) {
        try {
            ParentResponse<ApOperationStaff> response = new ParentResponse<>();
            apOperationStaffService
.saveRecord(request);
            response.setObject(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @DeleteMapping(value = "/delete-operation-staff", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteOperationStaff(@RequestParam String key) {
        try {
            ApOperationStaff staff = apOperationStaffService
.getRecord(key);
            ParentResponse<ApOperationStaff> response = new ParentResponse<>();
            if (staff == null) {
                response.setMsg("Staff record not found");
                response.setObject(null);
                return ResponseEntity.ok(response);
            }

            apOperationStaffService.deleteRecord(staff);

            response.setMsg("Deleted successfully");
            response.setObject(null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/save-operation-name-log", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveOperationNameLog(@RequestBody ApOperationNameLog log) {
        try {
            ParentResponse<ApOperationNameLog> response = new ParentResponse<>();
            apOperationNameLogService.saveRecord(log);
            response.setObject(log);
            return ResponseEntity.ok(response);
        } catch (Exception e) {

            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping(value = "/operation-name-log-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOperationNameLogList(@RequestParam Map<String, String> queryParams,

                                                     @RequestHeader(required = false) String lang) {
        try {
            ParentResponse<List<ApOperationNameLog>> response = new ParentResponse<>();
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false);
            List<ApOperationNameLog> list = apOperationNameLogService.getList(where);
            BigDecimal total = DS.executeDecimalResultQuery("select count(0) from ap_operation_name_log where " + whereForTotal);
            response.setObject(list);
            response.setExtraNumeric(total);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error fetching name log list", e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @PostMapping(value = "/save-operation-induction", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveOperationInduction(@RequestBody ApOperationInduction record) {
        try {
            ParentResponse<ApOperationInduction> response = new ParentResponse<>();
            apOperationInductionService.saveRecord(record);
            response.setObject(record);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error saving induction", e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping(value = "/operation-induction-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOperationInductionList(@RequestParam Map<String, String> queryParams,
                                                       @RequestHeader(required = false) String lang) {
        try {
            ParentResponse<List<ApOperationInduction>> response = new ParentResponse<>();
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false);
            List<ApOperationInduction> list = apOperationInductionService.getList(where);
            BigDecimal total = DS.executeDecimalResultQuery("select count(0) from ap_operation_induction where " + whereForTotal);
            response.setObject(list);
            response.setExtraNumeric(total);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error fetching induction list", e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @PostMapping(value = "/save-operation-patient-arrival", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveOperationPatientArrival(@RequestBody ApOperationPatientArrival record) {
        try {
            ParentResponse<ApOperationPatientArrival> response = new ParentResponse<>();
            apOperationPatientArrivalService.saveRecord(record);
            response.setObject(record);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error saving patient arrival", e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping(value = "/operation-patient-arrival-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOperationPatientArrivalList(@RequestParam Map<String, String> queryParams,
                                                            @RequestHeader(required = false) String lang) {
        try {
            ParentResponse<List<ApOperationPatientArrival>> response = new ParentResponse<>();
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false);
            List<ApOperationPatientArrival> list = apOperationPatientArrivalService.getList(where);
            BigDecimal total = DS.executeDecimalResultQuery("select count(0) from ap_operation_patient_arrival where " + whereForTotal);
            response.setObject(list);
            response.setExtraNumeric(total);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error fetching patient arrival list", e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @PostMapping(value = "/save-operation-pre-medication", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveOperationPreMedication(@RequestBody ApOperationPreMedication record) {
        try {
            ParentResponse<ApOperationPreMedication> response = new ParentResponse<>();
            apOperationPreMedicationService.saveRecord(record);
            response.setObject(record);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error saving pre-medication", e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping(value = "/operation-pre-medication-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOperationPreMedicationList(@RequestParam Map<String, String> queryParams,
                                                           @RequestHeader(required = false) String lang) {
        try {
            ParentResponse<List<ApOperationPreMedication>> response = new ParentResponse<>();
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false);
            List<ApOperationPreMedication> list = apOperationPreMedicationService.getList(where);
            BigDecimal total = DS.executeDecimalResultQuery("select count(0) from ap_operation_pre_medication where " + whereForTotal);
            response.setObject(list);
            response.setExtraNumeric(total);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error fetching pre-medication list", e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @PostMapping(value = "/save-intraoperative-monitoring", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveIntraoperativeMonitoring(@RequestBody ApOperationIntraoperativeMonitoring record) {
        try {
            ParentResponse<ApOperationIntraoperativeMonitoring> response = new ParentResponse<>();
            apOperationIntraoperativeMonitoringService.saveRecord(record);
            response.setObject(record);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error saving intraoperative monitoring", e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping(value = "/intraoperative-monitoring-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getIntraoperativeMonitoringList(@RequestParam Map<String, String> queryParams,
                                                             @RequestHeader(required = false) String lang) {
        try {
            ParentResponse<List<ApOperationIntraoperativeMonitoring>> response = new ParentResponse<>();
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false);
            List<ApOperationIntraoperativeMonitoring> list = apOperationIntraoperativeMonitoringService.getList(where);
            BigDecimal total = DS.executeDecimalResultQuery("select count(0) from ap_operation_intraoperative_monitoring where " + whereForTotal);
            response.setObject(list);
            response.setExtraNumeric(total);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error fetching intraoperative monitoring list", e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping(value = "/save-pre-operative-timeout", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> savePreOperativeTimeout(@RequestBody ApPreOperativeTimeout record) {
        try {
            ParentResponse<ApPreOperativeTimeout> response = new ParentResponse<>();
            if (record.getKey() == null || record.getKey().isEmpty()) {
                System.out.println("IIIIIIIIIIIIIII");
                ApOperationRequests request = apOperationRequestsService.getRecord(record.getOperationRequestKey());
                // ToDo key for PROC_INPROGRESS from PROC_STATUS lovs
                request.setOperationStatusLkey("3622377660614958");

                apOperationRequestsService.saveRecord(request);
            }

            apPreOperativeTimeoutService.saveRecord(record);
            response.setObject(record);
            return ResponseEntity.ok(response);
        } catch (Exception e) {

            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping(value = "/pre-operative-timeout-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPreOperativeTimeout(@RequestParam Map<String, String> queryParams,
                                                           @RequestHeader(required = false) String lang) {
        try {
            ParentResponse<List<ApPreOperativeTimeout>> response = new ParentResponse<>();
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false);
            List<ApPreOperativeTimeout> list = apPreOperativeTimeoutService.getList(where);
            BigDecimal total = DS.executeDecimalResultQuery("select count(0) from ap_pre_operative_timeout where " + whereForTotal);
            response.setObject(list);
            response.setExtraNumeric(total);
            return ResponseEntity.ok(response);
        } catch (Exception e) {

            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
