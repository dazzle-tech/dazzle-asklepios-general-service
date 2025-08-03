package com.asklepios.backend_service.controller;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.*;
import com.asklepios.backend_service.model.pojo.request.ListRequest;
import com.asklepios.backend_service.model.pojo.response.ParentResponse;
import com.asklepios.backend_service.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/recovery")
@Slf4j
public class RecoveryRoomController {

    @Autowired
    private ApOperationArrivalToRecoveryRoomService apOperationArrivalToRecoveryRoomService;

    @Autowired
    private ApOperationAnesthesiaRecoveryService apOperationAnesthesiaRecoveryService;

    @Autowired
    private ApOperationRecoveryVitalsMonitoringService apOperationRecoveryVitalsMonitoringService;

    @Autowired
    private ApOperationNursingCareInterventionsService apOperationNursingCareService;

    @Autowired
    private ApOperationDischargeReadinessService apOperationDischargeReadinessService;

    @Autowired
    private ApOperationDischargeToWardService apOperationDischargeToWardService;


    // ====== Arrival to Recovery Room ======

    @PostMapping(value = "/save-arrival", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveArrival(@RequestBody ApOperationArrivalToRecoveryRoom request) {
        try {
            ParentResponse<ApOperationArrivalToRecoveryRoom > response = new ParentResponse<>();
            apOperationArrivalToRecoveryRoomService.saveRecord(request);
            response.setObject(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error saving arrival", e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping(value = "/arrival-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getArrivalList(@RequestParam Map<String, String> queryParams,
                                            @RequestHeader(required = false) String lang) {
        try {
            ParentResponse<List<ApOperationArrivalToRecoveryRoom>> response = new ParentResponse<>();
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true,false,false);

            List<ApOperationArrivalToRecoveryRoom> list = apOperationArrivalToRecoveryRoomService.getList(where);
            BigDecimal total = DS.executeDecimalResultQuery("SELECT COUNT(0) FROM ap_operation_arrival_to_recovery_room WHERE " + whereForTotal);

            for (ApOperationArrivalToRecoveryRoom item : list) {
                apOperationArrivalToRecoveryRoomService.populateLovFields(item, lang);
            }

            response.setObject(list);
            response.setExtraNumeric(total);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("Error fetching arrival list", e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping(value = "/arrival-by-operation", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getArrivalByOperation(@RequestParam String operationKey,
                                                   @RequestHeader(required = false) String lang) {
        try {
            ParentResponse<ApOperationArrivalToRecoveryRoom> response = new ParentResponse<>();
            List<ApOperationArrivalToRecoveryRoom> list = apOperationArrivalToRecoveryRoomService.getList("operation_request_key = '" + operationKey + "' ORDER BY created_at DESC LIMIT 1");


            if (!list.isEmpty()) {
                ApOperationArrivalToRecoveryRoom item = list.get(0);
                apOperationArrivalToRecoveryRoomService.populateLovFields(item, lang);
                response.setObject(item);
            }

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("Error fetching arrival by operation", e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }



    // ====== Anesthesia Recovery ======

    @PostMapping(value = "/save-anesthesia-recovery", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveAnesthesiaRecovery(@RequestBody ApOperationAnesthesiaRecovery request) {
        try {
            ParentResponse<ApOperationAnesthesiaRecovery > response = new ParentResponse<>();
            apOperationAnesthesiaRecoveryService.saveRecord(request);
            response.setObject(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error saving anesthesia recovery", e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping(value = "/anesthesia-recovery-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAnesthesiaRecoveryList(@RequestParam Map<String, String> queryParams,
                                                       @RequestHeader(required = false) String lang) {
        try {
            ParentResponse<List<ApOperationAnesthesiaRecovery>> response = new ParentResponse<>();
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true,false,false);

            List<ApOperationAnesthesiaRecovery> list = apOperationAnesthesiaRecoveryService.getList(where);
            BigDecimal total = DS.executeDecimalResultQuery("SELECT COUNT(0) FROM ap_operation_anesthesia_recovery WHERE " + whereForTotal);

            for (ApOperationAnesthesiaRecovery item : list) {
                apOperationAnesthesiaRecoveryService.populateLovFields(item, lang);
            }

            response.setObject(list);
            response.setExtraNumeric(total);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("Error fetching anesthesia recovery list", e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping(value = "/anesthesia-recovery-by-operation", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAnesthesiaRecoveryByOperation(@RequestParam String operationKey,
                                                              @RequestHeader(required = false) String lang) {
        try {
            ParentResponse<ApOperationAnesthesiaRecovery> response = new ParentResponse<>();
            List<ApOperationAnesthesiaRecovery> list = apOperationAnesthesiaRecoveryService.getList("operation_request_key = '" + operationKey + "' ORDER BY created_at DESC LIMIT 1");


            if (!list.isEmpty()) {
                ApOperationAnesthesiaRecovery item = list.get(0);
                apOperationAnesthesiaRecoveryService.populateLovFields(item, lang);
                response.setObject(item);
            }

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("Error fetching anesthesia recovery by operation", e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


    // ====== Continuous Vitals Monitoring ======

    @PostMapping(value = "/save-continuous-vitals", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveContinuousVitals(@RequestBody ApOperationRecoveryVitalsMonitoring request) {
        try {
            ParentResponse<ApOperationRecoveryVitalsMonitoring> response = new ParentResponse<>();
            apOperationRecoveryVitalsMonitoringService.saveRecord(request);
            response.setObject(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error saving continuous vitals", e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping(value = "/continuous-vitals-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getContinuousVitalsList(@RequestParam Map<String, String> queryParams,
                                                     @RequestHeader(required = false) String lang) {
        try {
            ParentResponse<List<ApOperationRecoveryVitalsMonitoring>> response = new ParentResponse<>();
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true,false,false);

            List<ApOperationRecoveryVitalsMonitoring> list =apOperationRecoveryVitalsMonitoringService.getList(where);
            BigDecimal total = DS.executeDecimalResultQuery("SELECT COUNT(0) FROM ap_operation_recovery_vitals_monitoring WHERE " + whereForTotal);

            for (ApOperationRecoveryVitalsMonitoring item : list) {
                apOperationRecoveryVitalsMonitoringService.populateLovFields(item, lang);
            }

            response.setObject(list);
            response.setExtraNumeric(total);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("Error fetching continuous vitals list", e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping(value = "/continuous-vitals-by-operation", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getContinuousVitalsByOperation(@RequestParam String operationKey,
                                                            @RequestHeader(required = false) String lang) {
        try {
            ParentResponse<List<ApOperationRecoveryVitalsMonitoring>> response = new ParentResponse<>();
            List<ApOperationRecoveryVitalsMonitoring> list = apOperationRecoveryVitalsMonitoringService.getList("operation_request_key = '" + operationKey + "'");

            for (ApOperationRecoveryVitalsMonitoring item : list) {
                apOperationRecoveryVitalsMonitoringService.populateLovFields(item, lang);
            }

            response.setObject(list);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("Error fetching continuous vitals by operation", e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


    // ====== Nursing Care & Interventions ======

    @PostMapping(value = "/save-nursing-care", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveNursingCare(@RequestBody ApOperationNursingCareInterventions request) {
        try {
            ParentResponse<ApOperationNursingCareInterventions> response = new ParentResponse<>();
            apOperationNursingCareService.saveRecord(request);
            response.setObject(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error saving nursing care", e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping(value = "/nursing-care-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getNursingCareList(@RequestParam Map<String, String> queryParams,
                                                @RequestHeader(required = false) String lang) {
        try {
            ParentResponse<List<ApOperationNursingCareInterventions>> response = new ParentResponse<>();
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true,false,false);

            List<ApOperationNursingCareInterventions> list = apOperationNursingCareService.getList(where);
            BigDecimal total = DS.executeDecimalResultQuery("SELECT COUNT(0) FROM ap_operation_nursing_care_interventions WHERE " + whereForTotal);

            for (ApOperationNursingCareInterventions item : list) {
                apOperationNursingCareService.populateLovFields(item, lang);
            }

            response.setObject(list);
            response.setExtraNumeric(total);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("Error fetching nursing care list", e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping(value = "/nursing-care-by-operation", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getNursingCareByOperation(@RequestParam String operationKey,
                                                       @RequestHeader(required = false) String lang) {
        try {
            ParentResponse<ApOperationNursingCareInterventions> response = new ParentResponse<>();
            List<ApOperationNursingCareInterventions> list = apOperationNursingCareService.getList("operation_request_key = '" + operationKey + "' ORDER BY created_at DESC LIMIT 1");


            if (!list.isEmpty()) {
                ApOperationNursingCareInterventions item = list.get(0);
                apOperationNursingCareService.populateLovFields(item, lang);
                response.setObject(item);
            }

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("Error fetching nursing care by operation", e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


    // ====== Discharge Readiness Assessment ======
    // ====== Discharge Readiness Assessment ======

    @PostMapping(value = "/save-discharge-readiness", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveDischargeReadiness(@RequestBody ApOperationDischargeReadiness request) {
        try {
            ParentResponse<ApOperationDischargeReadiness> response = new ParentResponse<>();
            apOperationDischargeReadinessService.saveRecord(request);
            response.setObject(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error saving discharge readiness", e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping(value = "/discharge-readiness-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDischargeReadinessList(@RequestParam Map<String, String> queryParams,
                                                       @RequestHeader(required = false) String lang) {
        try {
            ParentResponse<List<ApOperationDischargeReadiness>> response = new ParentResponse<>();
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true,false,false);

            List<ApOperationDischargeReadiness> list = apOperationDischargeReadinessService.getList(where);
            BigDecimal total = DS.executeDecimalResultQuery("SELECT COUNT(0) FROM ap_operation_discharge_readiness WHERE " + whereForTotal);

            for (ApOperationDischargeReadiness item : list) {
                apOperationDischargeReadinessService.populateLovFields(item, lang);
            }

            response.setObject(list);
            response.setExtraNumeric(total);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("Error fetching discharge readiness list", e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping(value = "/discharge-readiness-by-operation", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDischargeReadinessByOperation(@RequestParam String operationKey,
                                                              @RequestHeader(required = false) String lang) {
        try {
            ParentResponse<ApOperationDischargeReadiness> response = new ParentResponse<>();
            List<ApOperationDischargeReadiness> list = apOperationDischargeReadinessService.getList("operation_request_key = '" + operationKey + "' ORDER BY created_at DESC LIMIT 1");


            if (!list.isEmpty()) {
                ApOperationDischargeReadiness item = list.get(0);
                apOperationDischargeReadinessService.populateLovFields(item, lang);
                response.setObject(item);
            }

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("Error fetching discharge readiness by operation", e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


    // ====== Discharge to Ward ======

    @PostMapping(value = "/save-discharge-to-ward", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveDischargeToWard(@RequestBody ApOperationDischargeToWard request) {
        try {
            ParentResponse<ApOperationDischargeToWard> response = new ParentResponse<>();
            apOperationDischargeToWardService.saveRecord(request);
            response.setObject(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error saving discharge to ward", e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping(value = "/discharge-to-ward-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDischargeToWardList(@RequestParam Map<String, String> queryParams,
                                                    @RequestHeader(required = false) String lang) {
        try {
            ParentResponse<List<ApOperationDischargeToWard>> response = new ParentResponse<>();
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true,false,false);

            List<ApOperationDischargeToWard> list = apOperationDischargeToWardService.getList(where);
            BigDecimal total = DS.executeDecimalResultQuery("SELECT COUNT(0) FROM ap_operation_discharge_to_ward WHERE " + whereForTotal);

            for (ApOperationDischargeToWard item : list) {
                apOperationDischargeToWardService.populateLovFields(item, lang);
            }

            response.setObject(list);
            response.setExtraNumeric(total);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("Error fetching discharge to ward list", e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping(value = "/discharge-to-ward-by-operation", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDischargeToWardByOperation(@RequestParam String operationKey,
                                                           @RequestHeader(required = false) String lang) {
        try {
            ParentResponse<ApOperationDischargeToWard> response = new ParentResponse<>();
            List<ApOperationDischargeToWard> list = apOperationDischargeToWardService.getList("operation_request_key = '" + operationKey + "' ORDER BY created_at DESC LIMIT 1");


            if (!list.isEmpty()) {
                ApOperationDischargeToWard item = list.get(0);
                apOperationDischargeToWardService.populateLovFields(item, lang);
                response.setObject(item);
            }
          
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("Error fetching discharge to ward by operation", e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}


