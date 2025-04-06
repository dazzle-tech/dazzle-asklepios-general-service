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
import org.springframework.beans.BeanUtils;
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
    private final ApDiagnosticTestService apDiagnosticTestService;
   private  final  ApDiagnosticTestLaboratoryService apDiagnosticTestLaboratoryService;
   private final ApDiagnosticTestProfileService apDiagnosticTestProfileService;
   private final ApLabResultLogService apLabResultLogService;
    public LabController(ApDiagnosticOrderTestsNotesService apDiagnosticOrderTestsNotesService, ApDiagnosticOrderTestsSamplesService apDiagnosticOrderTestsSamplesService, ApDiagnosticOrderTestsResultService apDiagnosticOrderTestsResultService, ApDiagnosticOrderTestsResultNotesService apDiagnosticOrderTestsResultNotesService, ApDiagnosticTestNormalRangeService apDiagnosticTestNormalRangeService, ApDiagnosticTestService apDiagnosticTestService, ApDiagnosticTestLaboratoryService apDiagnosticTestLaboratoryService, ApDiagnosticTestProfileService apDiagnosticTestProfileService, ApLabResultLogService apLabResultLogService) {
        this.apDiagnosticOrderTestsNotesService = apDiagnosticOrderTestsNotesService;
        this.apDiagnosticOrderTestsSamplesService = apDiagnosticOrderTestsSamplesService;
        this.apDiagnosticOrderTestsResultService = apDiagnosticOrderTestsResultService;
        this.apDiagnosticOrderTestsResultNotesService = apDiagnosticOrderTestsResultNotesService;
        this.apDiagnosticTestNormalRangeService = apDiagnosticTestNormalRangeService;
        this.apDiagnosticTestService = apDiagnosticTestService;
        this.apDiagnosticTestLaboratoryService = apDiagnosticTestLaboratoryService;
        this.apDiagnosticTestProfileService = apDiagnosticTestProfileService;
        this.apLabResultLogService = apLabResultLogService;
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
    @PostMapping(value = "/save-diagnostic-tests-result", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveDiagnosticTestsResult(@RequestBody ApDiagnosticOrderTestsResult request,

                                                            @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                            @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                            @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                            @jakarta.annotation.Nullable @RequestHeader String lang

    ) {
        try {
           List <ApDiagnosticTestLaboratory> lab = apDiagnosticTestLaboratoryService.getList(" test_key = '"+request.getMedicalTestKey()+"'");


            ApDiagnosticTest test = apDiagnosticTestService.getRecord(request.getMedicalTestKey());
            if (lab != null && lab.size() > 0) {
                boolean isProfile = lab.get(0).getIsProfile();
                if (isProfile) {
                    List<ApDiagnosticTestProfile> profileList = apDiagnosticTestProfileService.getList(" diagnostic_test_key = '" + request.getMedicalTestKey() + "'");
                    for (ApDiagnosticTestProfile profile : profileList) {
                        ApDiagnosticOrderTestsResult response = new ApDiagnosticOrderTestsResult();
                        BeanUtils.copyProperties(request, response);

                        response.setNormalRangeKey(apDiagnosticOrderTestsResultService.getNormalRange(request.getPatientKey(), request.getMedicalTestKey(),true, profile.getKey()).getKey());
                        response.setIsProfile(true);
                        response.setTestProfileKey(profile.getKey());
                        apDiagnosticOrderTestsResultService.saveRecord(response);
                    }
                    ParentResponse<ApDiagnosticOrderTestsResult> response = new ParentResponse<>();
                      response.setObject(new ApDiagnosticOrderTestsResult());
                    return ResponseEntity.ok(response);
                }
                else {
                    ParentResponse<ApDiagnosticOrderTestsResult> response = new ParentResponse<>();
                    request.setNormalRangeKey(apDiagnosticOrderTestsResultService.getNormalRange(request.getPatientKey(),  request.getMedicalTestKey(),false, request.getTestProfileKey()).getKey());
                    request.setIsProfile(false);
                    apDiagnosticOrderTestsResultService.saveRecord(request);

                    response.setObject(request);
                    return ResponseEntity.ok(response);
                }
            }
            else {
                ParentResponse<ApDiagnosticOrderTestsResult> response = new ParentResponse<>();
                request.setNormalRangeKey(apDiagnosticOrderTestsResultService.getNormalRange(request.getPatientKey(), request.getMedicalTestKey(),false, request.getTestProfileKey()).getKey());
                request.setIsProfile(false);
                apDiagnosticOrderTestsResultService.saveRecord(request);

                response.setObject(request);
                return ResponseEntity.ok(response);
            }
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
                 o.setNormalRange(apDiagnosticTestNormalRangeService.getRecord(o.getNormalRangeKey()));

                List<ApDiagnosticTestNormalRangeLov> lovList = new ApDiagnosticTestNormalRangeLovService().getList("normal_range_key = '" +  o.getNormalRangeKey()+ "' and deleted_at is null");
                if(!lovList.isEmpty()){
                    List<String> lovIds = new ArrayList<>();
                    lovList.forEach(lov -> lovIds.add(lov.getLovLkey()));
                    o.getNormalRange().setLovList(lovIds);
                }

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
                                                  @RequestParam String testProfileKey,
                                                  @RequestParam Boolean isProfile,
                                                  @Nullable @RequestHeader String facility_id,
                                                  @Nullable @RequestHeader String access_token,
                                                  @Nullable @RequestHeader Integer access_level,
                                                  @Nullable @RequestHeader String lang) {
        try {
            ApDiagnosticTest test=apDiagnosticTestService.getRecord(testKey);

            ParentResponse<ApDiagnosticTestNormalRange> response = new ParentResponse<>();

            ApDiagnosticTestNormalRange normalRange=apDiagnosticOrderTestsResultService.getNormalRange(patientKey,testKey,isProfile,testProfileKey);
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
    @PostMapping(value = "/save-lab-result-log", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveLabResultLog(@RequestBody ApLabResultLog request,
                                              @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                              @jakarta.annotation.Nullable @RequestHeader String access_token,
                                              @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                              @jakarta.annotation.Nullable @RequestHeader String lang

    ) {
        try {
            ParentResponse<ApLabResultLog> response = new ParentResponse<>();
            ApDiagnosticOrderTestsResult result=apDiagnosticOrderTestsResultService.getRecord(request.getResultKey());

            System.out.println(request.getResultValue());
            apLabResultLogService.saveRecord(request);

            response.setObject(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }


    @GetMapping(value = "/lab-order-test-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getLabResultLogList(@RequestParam Map<String, String> queryParams,
                                                 @Nullable @RequestHeader String facility_id,
                                                 @Nullable @RequestHeader String access_token,
                                                 @Nullable @RequestHeader Integer access_level,
                                                 @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApLabResultLog>> response = new ParentResponse<>();

            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }

            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);
            List<ApLabResultLog> log = apLabResultLogService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_lab_result_log where " + whereForTotal);

            for (ApLabResultLog all : log) {

                apLabResultLogService.populateLovFields(all,lang );
            }
            response.setObject(log);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
}
