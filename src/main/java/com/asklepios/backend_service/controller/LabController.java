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
@RequestMapping("/lab")
//@CrossOrigin
@Slf4j
public class LabController {
    private  final ApDiagnosticOrderTestsNotesService apDiagnosticOrderTestsNotesService;
    private final  ApDiagnosticOrderTestsSamplesService apDiagnosticOrderTestsSamplesService;
    private final ApDiagnosticOrderTestsResultService apDiagnosticOrderTestsResultService;
    private  final  ApDiagnosticOrderTestsResultNotesService apDiagnosticOrderTestsResultNotesService;
    private final  ApDiagnosticTestNormalRangeService apDiagnosticTestNormalRangeService;

    public LabController(ApDiagnosticOrderTestsNotesService apDiagnosticOrderTestsNotesService, ApDiagnosticOrderTestsSamplesService apDiagnosticOrderTestsSamplesService, ApDiagnosticOrderTestsResultService apDiagnosticOrderTestsResultService, ApDiagnosticOrderTestsResultNotesService apDiagnosticOrderTestsResultNotesService, ApDiagnosticTestNormalRangeService apDiagnosticTestNormalRangeService) {
        this.apDiagnosticOrderTestsNotesService = apDiagnosticOrderTestsNotesService;
        this.apDiagnosticOrderTestsSamplesService = apDiagnosticOrderTestsSamplesService;
        this.apDiagnosticOrderTestsResultService = apDiagnosticOrderTestsResultService;
        this.apDiagnosticOrderTestsResultNotesService = apDiagnosticOrderTestsResultNotesService;
        this.apDiagnosticTestNormalRangeService = apDiagnosticTestNormalRangeService;
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
    @PostMapping(value = "/save-diagnostic-order-tests-result", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveDiagnosticOrderTestsResult(@RequestBody ApDiagnosticOrderTestsResult request,
                                                            @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                            @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                            @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                            @jakarta.annotation.Nullable @RequestHeader String lang

    ) {
        try {
            ParentResponse<ApDiagnosticOrderTestsResult> response = new ParentResponse<>();

            apDiagnosticOrderTestsResultService.saveRecord(request);

            response.setObject(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @GetMapping(value = "/diagnostic-order-test-result-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDiagnosticOrderTestResultList(@RequestParam Map<String, String> queryParams,
                                                              @Nullable @RequestHeader String facility_id,
                                                              @Nullable @RequestHeader String access_token,
                                                              @Nullable @RequestHeader Integer access_level,
                                                              @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApDiagnosticOrderTestsResult>> response = new ParentResponse<>();

            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }

            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);
            List<ApDiagnosticOrderTestsResult> results = apDiagnosticOrderTestsResultService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_diagnostic_order_tests_result where " + whereForTotal);
            for (ApDiagnosticOrderTestsResult o : results) {

                apDiagnosticOrderTestsResultService.populateLovFields(o, lang);

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

    @PostMapping(value = "/save-diagnostic-order-tests-result-notes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveDiagnosticOrderTestsResultNotes(@RequestBody ApDiagnosticOrderTestsResultNotes request,
                                                                 @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                                 @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                                 @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                                 @jakarta.annotation.Nullable @RequestHeader String lang

    ) {
        try {
            ParentResponse<ApDiagnosticOrderTestsResultNotes> response = new ParentResponse<>();
            apDiagnosticOrderTestsResultNotesService.saveRecord(request);
            response.setObject(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @GetMapping(value = "/diagnostic-order-tests-result-notes-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDiagnosticOrderTestsResultNotesList(@RequestHeader  String resultid,
                                                                    @Nullable @RequestHeader String facility_id,
                                                                    @Nullable @RequestHeader String access_token,
                                                                    @Nullable @RequestHeader Integer access_level,
                                                                    @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApDiagnosticOrderTestsResultNotes>> response = new ParentResponse<>();



            List<ApDiagnosticOrderTestsResultNotes> list = apDiagnosticOrderTestsResultNotesService.getList(
                    " result_key = '" + resultid + "'"
            );

            for(ApDiagnosticOrderTestsResultNotes all : list){
                apDiagnosticOrderTestsResultNotesService.populateLovFields(all, lang);

            }
            response.setObject(list);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @GetMapping(value = "/get-result-normal-range", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getResultNormalRange(@RequestParam String patientKey,
                                                  @RequestParam String testKey,
                                                  @Nullable @RequestHeader String facility_id,
                                                  @Nullable @RequestHeader String access_token,
                                                  @Nullable @RequestHeader Integer access_level,
                                                  @Nullable @RequestHeader String lang) {
        try {
            System.out.println("patient Key: " + patientKey +"test Key: " + testKey);
            ParentResponse<ApDiagnosticTestNormalRange> response = new ParentResponse<>();

            ApDiagnosticTestNormalRange normalRange=apDiagnosticOrderTestsResultService.getNormalRange(patientKey,testKey);
            apDiagnosticTestNormalRangeService.populateLovFields(normalRange, lang);

            List<ApDiagnosticTestNormalRangeLov> lovList = new ApDiagnosticTestNormalRangeLovService().getList("normal_range_key = '" + normalRange.getKey() + "' and deleted_at is null");
            if(!lovList.isEmpty()){
                List<String> lovIds = new ArrayList<>();
                lovList.forEach(lov -> lovIds.add(lov.getLovLkey()));
                normalRange.setLovList(lovIds);
            }

            response.setObject(normalRange);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
}
