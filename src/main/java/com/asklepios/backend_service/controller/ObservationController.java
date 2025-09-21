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
//@CrossOrigin
@Slf4j
public class ObservationController {

    private final ApPatientService apPatientService;
    private final PublicServices publicServices;
    private final ApEncounterService apEncounterService;
    private final ApPatientObservationService apPatientObservationService;
    private final ApPatientObservationSummaryService apPatientObservationSummaryService;
    private final ApVisitAllergiesService apVisitAllergiesService;
    private final  ApVisitWarningService apVisitWarningService;
    private final ApEncounterVaccinationService apEncounterVaccinationService;
    private final ApVaccineService apVaccineService;
    private final ApVaccineDoseService apVaccineDoseService;
    private final ApVaccineBrandsService apVaccineBrandsService;
    private final ApUserService apUserService;
    public ObservationController(ApPatientService apPatientService, PublicServices publicServices, ApEncounterService apEncounterService, ApPatientObservationService apPatientObservationService, ApPatientObservationSummaryService apPatientObservationSummaryService, ApVisitAllergiesService apVisitAllergiesService, ApVisitWarningService apVisitWarningService, ApEncounterVaccinationService apEncounterVaccinationService, ApVaccineService apVaccineService, ApVaccineDoseService apVaccineDoseService, ApVaccineBrandsService apVaccineBrandsService, ApUserService apUserService) {
        this.apPatientService  = apPatientService;
        this.publicServices = publicServices;
        this.apEncounterService = apEncounterService;
        this.apPatientObservationService = apPatientObservationService;
        this.apPatientObservationSummaryService = apPatientObservationSummaryService;
        this.apVisitAllergiesService = apVisitAllergiesService;
        this.apVisitWarningService = apVisitWarningService;
        this.apEncounterVaccinationService = apEncounterVaccinationService;
        this.apVaccineService = apVaccineService;
        this.apVaccineDoseService = apVaccineDoseService;
        this.apVaccineBrandsService = apVaccineBrandsService;
        this.apUserService = apUserService;
    }


    @GetMapping(value = "/observation-summary-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> observationSummaryList(@RequestParam Map<String, String> queryParams,
                                                    @Nullable @RequestHeader String facility_id,
                                                    // // @Nullable @RequestHeader String access_token,
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
                obs.setEncounter(apEncounterService.getRecord(obs.getVisitKey()));
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
    public ResponseEntity<?> saveRoomService(
            @RequestBody ApPatientObservationSummary observationSummary,
            @jakarta.annotation.Nullable @RequestHeader String facility_id,
//            @jakarta.annotation.Nullable @RequestHeader String access_token,
            @jakarta.annotation.Nullable @RequestHeader Integer access_level,
            @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApPatientObservationSummary> response = new ParentResponse<>();
            apPatientObservationSummaryService.saveRecord(observationSummary);
            response.setObject(observationSummary);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body("Internal Server Error: " + e.getMessage());
        }
    }
    @PostMapping(value = "/remove-observation-summary", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeObservationSummary( @RequestBody ApPatientObservationSummary observationSummary,
                                                     @Nullable @RequestHeader String facility_id,
                                                     // // @Nullable @RequestHeader String access_token,
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

    @PostMapping(value = "/save-allergies", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveVisitAllergies(@RequestBody ApVisitAllergies request,
                                                @jakarta.annotation.Nullable @RequestHeader String facility_id,
//                                                @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                @jakarta.annotation.Nullable @RequestHeader String lang,
                                                @jakarta.annotation.Nullable @RequestHeader String screenKey
    ) {
        try {
            ParentResponse<ApVisitAllergies> response = new ParentResponse<>();
            apVisitAllergiesService.saveRecord(request);
            response.setObject(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @GetMapping(value = "/allergies-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getVisitAllergies(@RequestParam Map<String, String> queryParams,
                                               @Nullable @RequestHeader String facility_id,
                                               // // @Nullable @RequestHeader String access_token,
                                               @Nullable @RequestHeader Integer access_level,
                                               @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApVisitAllergies>> response = new ParentResponse<>();

            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }

            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);

            List<ApVisitAllergies> allergies = apVisitAllergiesService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_visit_allergies where " + whereForTotal);

            for (ApVisitAllergies a : allergies) {
                apVisitAllergiesService.populateLovFields(a, lang);
                System.out.println();
                a.setAllergensName(apVisitAllergiesService.getAllergenName(a.getAllergenKey()));
            }

            response.setObject(allergies);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @PostMapping(value = "/save-warnings", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveVisitWarninig(@RequestBody ApVisitWarning request,
                                                @jakarta.annotation.Nullable @RequestHeader String facility_id,
//                                                @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                @jakarta.annotation.Nullable @RequestHeader String lang,
                                                @jakarta.annotation.Nullable @RequestHeader String screenKey
    ) {
        try {
            ParentResponse<ApVisitWarning> response = new ParentResponse<>();
            apVisitWarningService.saveRecord(request);
            response.setObject(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @GetMapping(value = "/warnings-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getVisitWarning(@RequestParam Map<String, String> queryParams,
                                               @Nullable @RequestHeader String facility_id,
                                               // // @Nullable @RequestHeader String access_token,
                                               @Nullable @RequestHeader Integer access_level,
                                               @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApVisitWarning>> response = new ParentResponse<>();

            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }

            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);

            List<ApVisitWarning> warnings = apVisitWarningService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_visit_warning where " + whereForTotal);

            for (ApVisitWarning w : warnings) {

                apVisitWarningService.populateLovFields(w, lang);
            }

            response.setObject(warnings);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @PostMapping(value = "/save-encounter-vaccine", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveEncounterVaccine(@RequestBody ApEncounterVaccination encounterVaccination,
                                                  @jakarta.annotation.Nullable @RequestHeader String facility_id,
//                                                  @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                  @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                  @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApEncounterVaccination> response = new ParentResponse<>();
            apEncounterVaccinationService.saveRecord(encounterVaccination);
            response.setObject(encounterVaccination);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @GetMapping(value = "/encounter-vaccine-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> vaccineDosesList(@RequestParam Map<String, String> queryParams,
                                              @jakarta.annotation.Nullable @RequestHeader String facility_id,
//                                              @jakarta.annotation.Nullable @RequestHeader String access_token,
                                              @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                              @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApEncounterVaccination>> response = new ParentResponse<>();
            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false);
            List<ApEncounterVaccination> list = apEncounterVaccinationService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_encounter_vaccination where " + whereForTotal);
            for (ApEncounterVaccination all : list) {
                apEncounterVaccinationService.populateLovFields(all, lang);
                if (all.getVaccineKey() != null) {
                    all.setVaccine(apVaccineService.getRecord(all.getVaccineKey()));
                }
                if (all.getVaccineDoseKey() != null) {
                    all.setVaccineDose(apVaccineDoseService.getRecord(all.getVaccineDoseKey()));
                }
                if (all.getVaccineBrandKey() != null) {
                    all.setVaccineBrands(apVaccineBrandsService.getRecord(all.getVaccineBrandKey()));
                }
                if (all.getCreatedBy() != null) {
                    all.setCreateByUser(apUserService.getRecord(all.getCreatedBy()));
                }
                if (all.getUpdatedBy() != null) {
                    all.setUpdateByUser(apUserService.getRecord(all.getUpdatedBy()));
                }
                if (all.getDeletedBy() != null) {
                    all.setDeleteByUser(apUserService.getRecord(all.getDeletedBy()));
                }
                if (all.getReviewedBy() != null) {
                    all.setReviewedByUser(apUserService.getRecord(all.getReviewedBy()));
                }

                // Populate LOV fields only if the vaccine is not null
                if (all.getVaccine() != null) {
                    apEncounterVaccinationService.populateLovFields(all, lang);
                    apVaccineService.populateLovFields(all.getVaccine(), lang);
                }
                if (all.getVaccineDose() != null) {
                    apVaccineDoseService.populateLovFields(all.getVaccineDose(), lang);
                }
                if (all.getVaccineBrands() != null) {
                    apVaccineBrandsService.populateLovFields(all.getVaccineBrands(), lang);
                }
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
    @GetMapping(value = "/patient-vaccination-record", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPatientVaccinationRecord(@RequestHeader("patient-key") String patientKey,
                                                         @RequestHeader("is-cancelled") String isCancelled,
                                                         @Nullable @RequestHeader String facility_id,
                                                          // // @Nullable @RequestHeader String access_token,
                                                         @Nullable @RequestHeader Integer access_level,
                                                         @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApVaccine>>response = new ParentResponse<>();
            String where = "patient_key = CAST(" + patientKey + " AS TEXT) AND deleted_at IS " + isCancelled ;

            System.out.println("WHERE Condition: " + where);
            List<ApVaccine> vaccines = apEncounterVaccinationService.getVaccinationRecords(where ,lang);
            response.setObject(vaccines);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
}
