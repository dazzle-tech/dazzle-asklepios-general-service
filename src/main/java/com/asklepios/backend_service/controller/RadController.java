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
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/rad")
//@CrossOrigin
@Slf4j
public class RadController {
    @Autowired
    private final ApDiagnosticOrderTestsRadReportService apDiagnosticOrderTestsRadReportService;
    @Autowired
    private final ApDiagnosticOrderTestsReportNotesService apDiagnosticOrderTestsReportNotesService;
    @Autowired
    private final ApDiagnosticOrderTestsService apDiagnosticOrderTestsService;
    @Autowired
    private final ApDiagnosticTestService apDiagnosticTestService;
    @Autowired
    private final ApDiagnosticOrdersService apDiagnosticOrdersService;
    @Autowired
    private final  ApPatientService apPatientService;
    public RadController(ApDiagnosticOrderTestsRadReportService apDiagnosticOrderTestsRadReportService, ApDiagnosticOrderTestsReportNotesService apDiagnosticOrderTestsReportNotesService, ApDiagnosticOrderTestsService apDiagnosticOrderTestsService, ApDiagnosticTestService apDiagnosticTestService, ApDiagnosticOrdersService apDiagnosticOrdersService, ApPatientService apPatientService) {
        this.apDiagnosticOrderTestsRadReportService = apDiagnosticOrderTestsRadReportService;
        this.apDiagnosticOrderTestsReportNotesService = apDiagnosticOrderTestsReportNotesService;
        this.apDiagnosticOrderTestsService = apDiagnosticOrderTestsService;
        this.apDiagnosticTestService = apDiagnosticTestService;
        this.apDiagnosticOrdersService = apDiagnosticOrdersService;
        this.apPatientService = apPatientService;
    }
    @PostMapping(value = "/save-diagnostic-order-tests-rad-report", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveDiagnosticOrderTestsRadReport(@RequestBody ApDiagnosticOrderTestsRadReport request,
                                                            @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                            @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                            @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                            @jakarta.annotation.Nullable @RequestHeader String lang

    ) {
        try {
            ParentResponse<ApDiagnosticOrderTestsRadReport > response = new ParentResponse<>();

            apDiagnosticOrderTestsRadReportService.saveRecord(request);

            response.setObject(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @GetMapping(value = "/diagnostic-order-test-rad-report-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDiagnosticOrderTestResultList(@RequestParam Map<String, String> queryParams,
                                                              @Nullable @RequestHeader String facility_id,
                                                              @Nullable @RequestHeader String access_token,
                                                              @Nullable @RequestHeader Integer access_level,
                                                              @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApDiagnosticOrderTestsRadReport >> response = new ParentResponse<>();

            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }

            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);
            List<ApDiagnosticOrderTestsRadReport > results = apDiagnosticOrderTestsRadReportService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_diagnostic_order_tests_rad_report where " + whereForTotal);
            ApDiagnosticOrderTests test=null;
            for (ApDiagnosticOrderTestsRadReport  o : results) {
                test=apDiagnosticOrderTestsService.getRecord(o.getOrderTestKey());
                o.setTest(test);
                o.getTest().setTest( apDiagnosticTestService.getRecord(o.getMedicalTestKey()));
                o.getTest().setOrder(apDiagnosticOrdersService.getRecord(o.getOrderKey()));
                o.getTest().getOrder().setPatient(apPatientService.getRecord(o.getPatientKey()));
                apDiagnosticOrderTestsRadReportService.populateLovFields(o, lang);

            }
            response.setObject(results);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @GetMapping(value = "/diagnostic-order-tests-report-notes-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDiagnosticOrderTestsReportNotesList(@RequestHeader  String reportid,
                                                                    @Nullable @RequestHeader String facility_id,
                                                                    @Nullable @RequestHeader String access_token,
                                                                    @Nullable @RequestHeader Integer access_level,
                                                                    @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApDiagnosticOrderTestsReportNotes>> response = new ParentResponse<>();



            List<ApDiagnosticOrderTestsReportNotes> list = apDiagnosticOrderTestsReportNotesService.getList(
                    " report_key = '" + reportid + "'"
            );

            for(ApDiagnosticOrderTestsReportNotes all : list){
                apDiagnosticOrderTestsReportNotesService.populateLovFields(all, lang);

            }
            response.setObject(list);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @PostMapping(value = "/save-diagnostic-order-tests-report-notes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveDiagnosticOrderTestsReportNotes(@RequestBody ApDiagnosticOrderTestsReportNotes request,
                                                                 @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                                 @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                                 @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                                 @jakarta.annotation.Nullable @RequestHeader String lang

    ) {
        try {
            ParentResponse<ApDiagnosticOrderTestsReportNotes> response = new ParentResponse<>();
            apDiagnosticOrderTestsReportNotesService.saveRecord(request);
            response.setObject(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @DeleteMapping(value = "/delete-test-reports", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteResults(@RequestParam String testKey) {
        try {

            apDiagnosticOrderTestsRadReportService.deleteRecord(testKey);
            ParentResponse<ApDiagnosticOrderTestsResult> response = new ParentResponse<>();
            response.setMsg("Deleted successfully");
            response.setObject(null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
}
