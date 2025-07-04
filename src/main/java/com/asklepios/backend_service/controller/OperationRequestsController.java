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

    public OperationRequestsController(
            ApOperationRequestsService apOperationRequestsService, ApOperationSetupService operationSetupService,
            ApOperationCodingService operationCodingService,
            ApOperationPriceListService operationPriceListService, ApOperationAnesthesiaCarePlanService operationAnesthesiaCarePlanService
    ) {
        this.apOperationRequestsService = apOperationRequestsService;
        this.operationSetupService = operationSetupService;
        this.operationCodingService = operationCodingService;
        this.operationPriceListService = operationPriceListService;
        this.operationAnesthesiaCarePlanService = operationAnesthesiaCarePlanService;
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
}
