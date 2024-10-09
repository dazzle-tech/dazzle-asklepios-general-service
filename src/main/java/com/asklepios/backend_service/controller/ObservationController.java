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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/observation")
@CrossOrigin
@Slf4j
public class ObservationController {

    private final ApPatientService apPatientService;
    private final PublicServices publicServices;
    private final ApEncounterService apEncounterService;
    private final ApPatientObservationService apPatientObservationService;
    private final ApPatientObservationSummaryService apPatientObservationSummaryService;

    public ObservationController(ApPatientService apPatientService, PublicServices publicServices, ApEncounterService apEncounterService, ApPatientObservationService apPatientObservationService, ApPatientObservationSummaryService apPatientObservationSummaryService) {
        this.apPatientService = apPatientService;
        this.publicServices = publicServices;
        this.apEncounterService = apEncounterService;
        this.apPatientObservationService = apPatientObservationService;
        this.apPatientObservationSummaryService = apPatientObservationSummaryService;
    }


    @GetMapping(value = "/observation-summary-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> observationSummaryList(@RequestParam Map<String, String> queryParams,
                                                    @Nullable @RequestHeader String facility_id,
                                                    @Nullable @RequestHeader String access_token,
                                                    @Nullable @RequestHeader Integer access_level,
                                                    @Nullable @RequestHeader String lang) {
        try {

            ParentResponse<List<ApPatientObservationSummary>> response = new ParentResponse<>();

            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }

            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false,false);
            List<ApPatientObservationSummary> list = apPatientObservationSummaryService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_patient_observation_summary where " + whereForTotal);
            for (ApPatientObservationSummary obs : list) {
                apPatientObservationSummaryService.populateLovFields(obs, lang);
            }
            response.setObject(list);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);

        }
    }


    @PostMapping(value = "/save-observation-summary", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveObservationSummary( @RequestBody ApPatientObservationSummary observationSummary,
                                                      @Nullable @RequestHeader String facility_id,
                                                      @Nullable @RequestHeader String access_token,
                                                      @Nullable @RequestHeader Integer access_level,
                                                      @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApPatientObservationSummary> response = new ParentResponse<>();
            apPatientObservationSummaryService.saveRecord(observationSummary);
            response.setObject(observationSummary);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/remove-observation-summary", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeObservationSummary( @RequestBody ApPatientObservationSummary observationSummary,
                                                     @Nullable @RequestHeader String facility_id,
                                                     @Nullable @RequestHeader String access_token,
                                                     @Nullable @RequestHeader Integer access_level,
                                                     @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApPatientObservationSummary> response = new ParentResponse<>();
            apPatientObservationSummaryService.deleteRecord(observationSummary);
            response.setObject(observationSummary);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

}
