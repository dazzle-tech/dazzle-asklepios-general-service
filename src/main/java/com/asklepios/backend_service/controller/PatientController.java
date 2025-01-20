package com.asklepios.backend_service.controller;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.entity.ApPatientEntity;
import com.asklepios.backend_service.model.generated.pojo.*;
import com.asklepios.backend_service.model.pojo.ValidationResult;
import com.asklepios.backend_service.model.pojo.request.ListRequest;
import com.asklepios.backend_service.model.pojo.response.ApAllergiesResponse;
import com.asklepios.backend_service.model.pojo.response.ApPatientSecondaryDocsResponce;
import com.asklepios.backend_service.model.pojo.response.ParentResponse;
import com.asklepios.backend_service.service.*;
import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pas")
@CrossOrigin
@Slf4j
public class PatientController {

    private final ApPatientService apPatientService;
    private final PublicServices publicServices;
    private final ValidationService validationService;
    private final ApPatientAllergiesService apPatientAllergiesService;
    private final ApAllergensService apAllergensService;
    private final ApPatientRelationService apPatientRelationService;
    private final ApPatientInsuranceService apPatientInsuranceService;
    private final ApPatientSecondaryDocumentsService apPatientSecondaryDocumentsService;
    private final ApPatientInsuranceCoverageService apPatientInsuranceCoverageService;
    private final ApPatientAdministrativeWarningsService apPatientAdministrativeWarningsService;
    private final ApAgeGroupService apAgeGroupService;
    private final ApLovValuesService apLovValuesService;
    private final ApUserService apUserService;
    public PatientController(ApPatientService apPatientService, RestTemplate restTemplate, PublicServices publicServices, ValidationService validationService, ApPatientAllergiesService apPatientAllergiesService, ApAllergensService apAllergensService, ApPatientRelationService apPatientRelationService, ApPatientInsuranceService apPatientInsuranceService, ApPatientSecondaryDocumentsService apPatientSecondaryDocumentsService, ApPatientInsuranceCoverageService apPatientInsuranceCoverageService, ApPatientAdministrativeWarningsService apPatientAdministrativeWarningsService, ApAgeGroupService apAgeGroupService, ApLovValuesService apLovValuesService, ApUserService apUserService) {
        this.apPatientService = apPatientService;
        this.publicServices = publicServices;
        this.validationService = validationService;
        this.apPatientAllergiesService = apPatientAllergiesService;
        this.apAllergensService = apAllergensService;
        this.apPatientRelationService = apPatientRelationService;
        this.apPatientInsuranceService = apPatientInsuranceService;
        this.apPatientSecondaryDocumentsService = apPatientSecondaryDocumentsService;
        this.apPatientInsuranceCoverageService = apPatientInsuranceCoverageService;
        this.apPatientAdministrativeWarningsService = apPatientAdministrativeWarningsService;
        this.apAgeGroupService = apAgeGroupService;
        this.apLovValuesService = apLovValuesService;
        this.apUserService = apUserService;
    }

    @PostMapping(value = "/get-patient", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPatient(@RequestParam("patient_id") String patient_id, @Nullable @RequestHeader String facility_id, @Nullable @RequestHeader String access_token, @Nullable @RequestHeader Integer access_level, @Nullable @RequestHeader String lang) {
        try {
            List<ApPatient> patientList = apPatientService.getList("key='" + patient_id + "' ");
            if (patientList.isEmpty()) {
                return ResponseEntity.status(404).body(publicServices.getMessageFromRedis("NOT_FOUND", lang, "Patient Record Not Found"));
            }
            if (patientList.size() > 1) {
                return ResponseEntity.status(404).body(publicServices.getMessageFromRedis("TOO_MANY_RECORDS", lang, "Query Returned More Than One Record"));
            }
            if (patientList.get(1).getAccessLevel().doubleValue() > access_level) {
                return ResponseEntity.status(404).body(publicServices.getMessageFromRedis("LOW_ACCESS_LEVEL", lang, "Your Access Level is Below the Needed Level"));
            }

            return ResponseEntity.ok(patientList.get(1));

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);

        }

    }

    @GetMapping(value = "/patient-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> patientList(@RequestParam Map<String, String> queryParams,
                                         @Nullable @RequestHeader String facility_id,
                                         @Nullable @RequestHeader String access_token,
                                         @Nullable @RequestHeader Integer access_level,
                                         @Nullable @RequestHeader String lang) {
        try {

            ParentResponse<List<ApPatient>> response = new ParentResponse<>();

            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }


            String filters = queryParams.get("filters");
//            if (filters != null && !filters.isEmpty())
            String[] filterParts = filters.split(",");
            String fieldName = filterParts[0];
            String operator = filterParts[1];
            String value = filterParts[2];


            ListRequest listRequest = new ListRequest(queryParams);

            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, true);

            if ("dob".equals(fieldName)) {
                where = where.replace("lower(dob) like", "TO_CHAR(dob, 'YYYY-MM-DD') like");
                whereForTotal = whereForTotal.replace("lower(dob) like", "TO_CHAR(dob, 'YYYY-MM-DD') like");

            } else if ("document_no".equals(fieldName)) {
                where = where.replace("lower(" + fieldName + ") like '%" + value + "%'", "lower(ap_patient." + fieldName + ") like'%" + value + "%' or lower(ap_patient.key) like   (select patient_key from apv_patient_secondary_documents where lower(" + fieldName + ") like '%" + value + "%' )");
                whereForTotal = whereForTotal.replace("lower(" + fieldName + ") like '%" + value + "%'", "lower(ap_patient." + fieldName + ") like'%" + value + "%' or lower(ap_patient.key) like   (select patient_key from apv_patient_secondary_documents where lower(" + fieldName + ") like '%" + value + "%' )");

            }

            List<ApPatient> patients = apPatientService.getList(where);

            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_patient where " + whereForTotal);

            for (ApPatient patient : patients) {

                apPatientService.populateLovFields(patient, lang);
                patient.setHasAllergy(apPatientService.getHasAllergy(patient.getKey()));
            }
            response.setObject(patients);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);

        }

    }

    @PostMapping(value = "/save-patient", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveLovValue(@RequestBody ApPatient apPatient,
                                          @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                          @jakarta.annotation.Nullable @RequestHeader String access_token,
                                          @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                          @jakarta.annotation.Nullable @RequestHeader String lang,
                                          @jakarta.annotation.Nullable @RequestHeader String screenKey) {
        try {
            ParentResponse<ApPatient> response = new ParentResponse<>();

            // Check if the patient already exists
            BigDecimal isExistingPatient = DS.executeDecimalResultQuery("select count(0) from ap_patient where key ='" + apPatient.getKey() + "'");
            System.out.println("isExistingPatient: " + isExistingPatient);

            BigDecimal newPatientMRN;

            // If the patient is new, assign a new MRN
            if (isExistingPatient != null && isExistingPatient.intValue() == 0) {
                BigDecimal lastPatientMRN = DS.executeDecimalResultQuery("select max(patient_mrn) from ap_patient");
                System.out.println("old MRN: " + lastPatientMRN);

                if (lastPatientMRN == null) {
                    newPatientMRN = BigDecimal.valueOf(1000);
                } else {
                    newPatientMRN = lastPatientMRN.add(BigDecimal.ONE);
                }

                apPatient.setPatientMrn(newPatientMRN.toString());
                if (apPatient.getUnknownPatient().equals(true)) {
                    apPatient.setFirstName("Unknown " + newPatientMRN);
                    System.out.println("Unknown " + newPatientMRN);

                }
            }

            // Validate the record
            ValidationResult validationResult = validationService.validateRecord(screenKey, "1705559108200", ApPatientEntity.class, apPatient);
            if (apPatient.isSkipValidation() || validationResult.isPass()) {
                apPatientService.saveRecord(apPatient);
                apPatient.setSkipValidation(false);
                response.setObject(apPatient);
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

    @GetMapping(value = "/patient-allergy-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> patientAllergyList(@RequestParam Map<String, String> queryParams,
                                                @Nullable @RequestHeader String facility_id,
                                                @Nullable @RequestHeader String access_token,
                                                @Nullable @RequestHeader Integer access_level,
                                                @Nullable @RequestHeader String lang) {
        try {

            ParentResponse<List<ApPatientAllergies>> response = new ParentResponse<>();

            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }

            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);
            List<ApPatientAllergies> allergies = apPatientAllergiesService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_patient_allergies where " + whereForTotal);


            for (ApPatientAllergies allergy : allergies) {
                apPatientAllergiesService.populateLovFields(allergy, lang);
                allergy.setAllergyObject(apAllergensService.getRecord(allergy.getAllergyKey()));
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


    @GetMapping(value = "/patient-allergy-view-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> patientAllergyViewList(
            @Nullable @RequestHeader("key") String patientKey,
            @Nullable @RequestHeader String facility_id,
            @Nullable @RequestHeader String access_token,
            @Nullable @RequestHeader Integer access_level,
            @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApAllergiesResponse>> response = new ParentResponse<>();


            List<ApAllergiesResponse> Allergies = apPatientAllergiesService.getApPatientAllergiesList("patient_key = '" + patientKey + "'"); // Assuming empty string means no filters
            System.out.println(patientKey);
            System.out.println("patient_key View = '" + patientKey + "'");
            response.setObject(Allergies);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/save-patient-allergy", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> savePatientAllergy(@RequestBody ApPatientAllergies request,
                                                @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                @jakarta.annotation.Nullable @RequestHeader String lang,
                                                @jakarta.annotation.Nullable @RequestHeader String screenKey
    ) {
        try {
            ParentResponse<ApPatientAllergies> response = new ParentResponse<>();
            if (request.getKey() != null && !request.getKey().isBlank()) {
                // existing
                ApPatientAllergies old = apPatientAllergiesService.getRecord(request.getKey());
                if (!old.getResolutionStatusLkey().equals("9766179572884232")
                        && request.getResolutionStatusLkey().equals("9766179572884232")) {
                    // recent resolution date
                    request.setDateResolved(new Date());
                }
            } else {
                if (request.getResolutionStatusLkey().equals("9766179572884232")) {
                    request.setDateResolved(new Date());
                } else {
                    request.setDateResolved(null);
                }
            }

            ApAllergens allergyObject = apAllergensService.getRecord(request.getAllergyKey());
            if (allergyObject != null) {
                request.setAllergenTypeLkey(allergyObject.getAllergenTypeLkey());
                apPatientAllergiesService.saveRecord(request);
                response.setObject(request);
                return ResponseEntity.ok(response);
            } else {
                response.addGeneralError("Invalid allergen");
                return ResponseEntity.status(400).body(response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/remove-patient-allergy", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removePatientAllergy(@RequestBody ApPatientAllergies request,
                                                  @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                  @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                  @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                  @jakarta.annotation.Nullable @RequestHeader String lang,
                                                  @jakarta.annotation.Nullable @RequestHeader String screenKey
    ) {
        try {
            ParentResponse<ApPatientAllergies> response = new ParentResponse<>();

            int res = DS.executeQuery("delete from ap_patient_allergies where key = '" + request.getKey() + "'");
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

    @PostMapping(value = "/send-verification-otp", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sendVerificationOtp(@RequestHeader String patientId,
                                                 @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                 @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                 @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                 @jakarta.annotation.Nullable @RequestHeader String lang,
                                                 @jakarta.annotation.Nullable @RequestHeader String screenKey
    ) {
        try {
            ParentResponse<ApPatient> response = new ParentResponse<>();
            // TODO implement a better solution on passing metadata key of objects (redis service perhaps based on object name)
            ApPatient patient = apPatientService.getRecord(patientId);

            if (patient == null) {
                response.addGeneralError("patient not found");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            patient.setVerificationOtp("0000"); // TODO generate a random OTP and send setup based SMS/Email

            apPatientService.saveRecord(patient);
            response.setObject(patient);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/verify-verification-otp", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> verifyVerificationOtp(@RequestHeader String patientId, @RequestHeader String otp,
                                                   @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                   @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                   @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                   @jakarta.annotation.Nullable @RequestHeader String lang,
                                                   @jakarta.annotation.Nullable @RequestHeader String screenKey
    ) {
        try {
            ParentResponse<ApPatient> response = new ParentResponse<>();
            // TODO implement a better solution on passing metadata key of objects (redis service perhaps based on object name)
            ApPatient patient = apPatientService.getRecord(patientId);

            if (patient == null) {
                response.addGeneralError("patient not found");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            if (patient.getVerificationOtp() != null && patient.getVerificationOtp().equals(otp)) {
                patient.setVerified(true);
                apPatientService.saveRecord(patient);
                response.setObject(patient);
                return ResponseEntity.ok(response);
            } else {
                response.addGeneralError("incorrect OTP code");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }


    @GetMapping(value = "/patient-relation-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> patientRelationList(@RequestParam Map<String, String> queryParams,
                                                 @Nullable @RequestHeader String key,
                                                 @Nullable @RequestHeader String access_token,
                                                 @Nullable @RequestHeader Integer access_level,
                                                 @Nullable @RequestHeader String lang) {
        try {

            ParentResponse<List<ApPatientRelation>> response = new ParentResponse<>();

            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }

            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);
            where = "  patient_key = '" + key + "' and deleted_at is  null and " + where;
            System.out.println("===================>" + where);
            List<ApPatientRelation> relations = apPatientRelationService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_patient_relation where " + whereForTotal);


            for (ApPatientRelation relation : relations) {
                apPatientRelationService.populateLovFields(relation, lang);
                relation.setRelativePatientObject(apPatientService.getRecord(relation.getRelativePatientKey()));
            }
            response.setObject(relations);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(value = "/patient_secondary_documents_list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> patientSecondaryDocumentsList(
            @Nullable @RequestHeader("key") String patientKey,
            @Nullable @RequestHeader String facility_id,
            @Nullable @RequestHeader String access_token,
            @Nullable @RequestHeader Integer access_level,
            @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApPatientSecondaryDocuments>> response = new ParentResponse<>();


            List<ApPatientSecondaryDocuments> secondaryDocuments = apPatientSecondaryDocumentsService.getApPatientSecondaryDocsList("patient_key = '" + patientKey + "'"); // Assuming empty string means no filters
            System.out.println(patientKey);
            System.out.println("patientKey = '" + patientKey + "'");
            response.setObject(secondaryDocuments);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    //This Update for Above Function
    @GetMapping(value = "/patient-secondary_document_list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> patientSecondaryDocumentList(@RequestParam Map<String, String> queryParams,
                                                 @Nullable @RequestHeader String key,
                                                 @Nullable @RequestHeader String access_token,
                                                 @Nullable @RequestHeader Integer access_level,
                                                 @Nullable @RequestHeader String lang) {
        try {

            ParentResponse<List<ApPatientSecondaryDocuments>> response = new ParentResponse<>();

            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);
            System.out.println("used : " + where);
            List<ApPatientSecondaryDocuments> documents = apPatientSecondaryDocumentsService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_patient_secondary_documents where " + whereForTotal);

            for (ApPatientSecondaryDocuments all : documents) {
                apPatientSecondaryDocumentsService.populateLovFields(all, lang);
                if (all.getCreatedBy() != null) {
                    all.setCreatedByUser(apUserService.getRecord(all.getCreatedBy()));
                }
                if (all.getUpdatedBy() != null) {
                    all.setUpdatedByUser(apUserService.getRecord(all.getUpdatedBy()));
                }
            }
            response.setObject(documents);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @PostMapping(value = "/save-secondary-document", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveSecondaryDocument(
            @RequestBody ApPatientSecondaryDocuments secondaryDocumentsData,
            @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApPatientSecondaryDocuments> response = new ParentResponse<>();
            if (secondaryDocumentsData.getDocumentCountryLkey() == null || secondaryDocumentsData.getDocumentCountryLkey().isBlank()) {
                response.addGeneralError("Document country required");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            if (secondaryDocumentsData.getDocumentTypeLkey() == null || secondaryDocumentsData.getDocumentTypeLkey().isBlank()) {
                response.addGeneralError("Document type required");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            if (secondaryDocumentsData.getDocumentNo() == null || secondaryDocumentsData.getDocumentNo().isBlank()) {
                response.addGeneralError("Document number required");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            // TODO change all of this validation to dynamic via DVM
            apPatientSecondaryDocumentsService.saveRecord(secondaryDocumentsData);
            response.setObject(secondaryDocumentsData);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/save-patient-relation", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> savePatientRelation(@RequestBody ApPatientRelation relation,
                                                 @Nullable @RequestHeader String facility_id,
                                                 @Nullable @RequestHeader String access_token,
                                                 @Nullable @RequestHeader Integer access_level,
                                                 @Nullable @RequestHeader String lang) {
        try {

            ParentResponse<ApPatientRelation> response = new ParentResponse<>();

            if (relation.getKey() != null) {
                ApPatientRelation exists = apPatientRelationService.getRecord(relation.getKey());
                if (exists == null) {
                    response.addGeneralError("relation record not found");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
                }
            }

            // check for duplicate relation with same target patient key
            String checkQuery = "select count(0) from ap_patient_relation" +
                    " where patient_key = '" + relation.getPatientKey()
                    + "' and relation_type_lkey = '" + relation.getRelationTypeLkey()
                    + "' and relative_patient_key = '" + relation.getRelativePatientKey() + "' and deleted_at is null";
            if (relation.getKey() != null) {
                checkQuery += " and key <> '" + relation.getKey() + "'";
            }
            BigDecimal exists = DS.executeDecimalResultQuery(checkQuery);
            if (exists != null && exists.intValue() > 0) {
                response.addGeneralError("such relation already exists");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            if (relation.getRelationTypeLkey() == null || relation.getRelationTypeLkey().isBlank()) {
                response.addGeneralError("relation type required");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            if (relation.getRelativePatientKey() == null || relation.getRelativePatientKey().isBlank()) {
                response.addGeneralError("relative patient is required");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            apPatientRelationService.saveRecord(relation);
            apPatientRelationService.populateLovFields(relation, lang);
            relation.setRelativePatientObject(apPatientService.getRecord(relation.getRelativePatientKey()));
            response.setObject(relation);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }


    }

    @GetMapping(value = "/fetch-patient-insurance", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> fetchPatientInsurance(
            @Nullable @RequestHeader("patientKey") String patientKey,
            @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApPatientInsurance>> response = new ParentResponse<>();

            List<ApPatientInsurance> existing = apPatientInsuranceService.getinsuranceList("patient_key = '" + patientKey
                    + "'  and deleted_at is null");

            System.out.println("patient_key = '" + patientKey + "'  and deleted_at is null");

            if (existing.isEmpty()) {
                response.setObject(existing);
            } else {
                response.setObject(existing);
            }

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/save-patient-insurance", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> savePatientInsurance(@RequestBody ApPatientInsurance Insurance,
                                                  @Nullable @RequestHeader String facility_id,
                                                  @Nullable @RequestHeader String access_token,
                                                  @Nullable @RequestHeader Integer access_level,
                                                  @Nullable @RequestHeader String lang) {
        try {

            ParentResponse<ApPatientInsurance> response = new ParentResponse<>();

            String checkQuery = "select count(0) from apv_patient_insurance" +
                    " where patient_key = '" + Insurance.getPatientKey()
                    + "' and is_valid = true and primary_insurance = true and deleted_at is null and key <> '" + Insurance.getKey() + "'";

            if (Insurance.getPrimaryInsurance()) {
                BigDecimal exists = DS.executeDecimalResultQuery(checkQuery);
                if (exists != null && exists.intValue() > 0) {
                    response.addGeneralError("Primary insurance already exists");
                    System.out.println(exists);
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
                }
            }


            apPatientInsuranceService.saveRecord(Insurance);
            response.setObject(Insurance);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }


    }

    @DeleteMapping(value = "/delete-patient-insurance", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removePatientInsurance(@RequestHeader("key") String key,
                                                    @Nullable @RequestHeader String facility_id,
                                                    @Nullable @RequestHeader String access_token,
                                                    @Nullable @RequestHeader Integer access_level,
                                                    @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApPatientInsurance> response = new ParentResponse<>();
            ApPatientInsurance insurance = apPatientInsuranceService.getRecord(key);
            if (insurance != null) {
                apPatientInsuranceService.deleteRecord(insurance);
                response.setObject(insurance);

                return ResponseEntity.ok((response));

            } else {
                return ResponseEntity.status(404).body(("Attachment not found."));

            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete-patient-Relation", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removePatientRelation(@RequestHeader("key") String key) {
        try {
            ParentResponse<ApPatientRelation> response = new ParentResponse<>();
            ApPatientRelation relation = apPatientRelationService.getRecord(key);
            System.out.println("=============>" + key);
            System.out.println("=============>" + relation);
            if (relation != null) {
                apPatientRelationService.deleteRecord(relation);
                response.setObject(relation);

                return ResponseEntity.ok((response));

            } else {
                return ResponseEntity.status(404).body(("Relation not found."));

            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete-patient-SecondaryDocument", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeSecondaryDocument(@RequestHeader("key") String key) {
        try {
            ParentResponse<ApPatientSecondaryDocuments> response = new ParentResponse<>();
            ApPatientSecondaryDocuments secondaryDocument = apPatientSecondaryDocumentsService.getRecord(key);

            if (secondaryDocument != null) {
                apPatientSecondaryDocumentsService.deleteRecord(secondaryDocument);
                response.setObject(secondaryDocument);

                return ResponseEntity.ok((response));

            } else {
                return ResponseEntity.status(404).body(("Relation not found."));

            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping(value = "/patient-insurance-covg-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> patientInsuranceCovgList(
            @RequestParam Map<String, String> queryParams,
            @Nullable @RequestHeader String key
    ) {
        try {
            ParentResponse<List<ApPatientInsuranceCoverage>> response = new ParentResponse<>();

            if (queryParams.containsKey("ignore") && "true".equals(queryParams.get("ignore"))) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            where = "  patient_insurance_key = '" + key + "' and " + where;
            List<ApPatientInsuranceCoverage> coverage = apPatientInsuranceCoverageService.getListView(where);
            response.setObject(coverage);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/save-patient-insurance-covg", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> savePatientInsuranceCovg(@RequestBody ApPatientInsuranceCoverage Coverage,
                                                      @Nullable @RequestHeader String facility_id,
                                                      @Nullable @RequestHeader String access_token,
                                                      @Nullable @RequestHeader Integer access_level,
                                                      @Nullable @RequestHeader String lang) {
        try {

            ParentResponse<ApPatientInsuranceCoverage> response = new ParentResponse<>();

            apPatientInsuranceCoverageService.saveRecord(Coverage);
            response.setObject(Coverage);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }


    }


    @DeleteMapping(value = "/remove-patient-insurance-covg", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removePatientInsuranceCovg(@RequestHeader("key") String key,
                                                        @Nullable @RequestHeader String facility_id,
                                                        @Nullable @RequestHeader String access_token,
                                                        @Nullable @RequestHeader Integer access_level,
                                                        @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApPatientInsuranceCoverage> response = new ParentResponse<>();
            ApPatientInsuranceCoverage coverage = apPatientInsuranceCoverageService.getRecord(key);
            if (coverage != null) {
                apPatientInsuranceCoverageService.deleteRecord(coverage);
                System.out.println("update ap_patient_insurance_coverage set  deleted_at = '" + System.currentTimeMillis() + "' where key = '" + coverage.getKey() + "'");
                response.setObject(coverage);

                return ResponseEntity.ok((response));

            } else {
                return ResponseEntity.status(404).body(("Coverage not found."));

            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping(value = "/save-patient-administrative-warnings", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> savePatientAdministrativeWarnings(
            @RequestBody ApPatientAdministrativeWarnings administrativeWarnings,
            @Nullable @RequestHeader String facility_id,
            @Nullable @RequestHeader String access_token,
            @Nullable @RequestHeader Integer access_level,
            @Nullable @RequestHeader String lang) {

        try {
            ParentResponse<ApPatientAdministrativeWarnings> response = new ParentResponse<>();

            String key = apPatientAdministrativeWarningsService.saveRecord(administrativeWarnings);
            administrativeWarnings.setKey(key);
            response.setObject(administrativeWarnings);

            // Populate LOV fields if language is specified
            apPatientAdministrativeWarningsService.populateLovFields(administrativeWarnings, lang);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(value = "/fetch-patient-administrative-warnings", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> fetchPatientAdministrativeWarnings(
            @RequestParam Map<String, String> queryParams,
            @jakarta.annotation.Nullable @RequestHeader String facility_id,
            @jakarta.annotation.Nullable @RequestHeader String access_token,
            @jakarta.annotation.Nullable @RequestHeader Integer access_level,
            @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {

            ParentResponse<List<ApPatientAdministrativeWarnings>> response = new ParentResponse<>();

            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }

            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);


            List<ApPatientAdministrativeWarnings> list= apPatientAdministrativeWarningsService.getList(where);

            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_patient_administrative_warnings where " + whereForTotal);

            for (ApPatientAdministrativeWarnings all : list) {
                apPatientAdministrativeWarningsService.populateLovFields(all, lang);
            }

            response.setObject(list);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error fetching patient administrative warnings: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @PostMapping(value = "/update-patient-administrative-warning", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updatePatientAdministrativeWarning(
            @RequestBody ApPatientAdministrativeWarnings administrativeWarning,
            @Nullable @RequestHeader String lang) {

        try {
            ParentResponse<ApPatientAdministrativeWarnings> response = new ParentResponse<>();


            apPatientAdministrativeWarningsService.updateRecord(administrativeWarning);


            response.setObject(administrativeWarning);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error updating patient administrative warning: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @PostMapping(value = "/delete-patient-administrative-warning", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deletePatientAdministrativeWarning(
            @RequestBody ApPatientAdministrativeWarnings administrativeWarning,
            @Nullable @RequestHeader String lang) {

        try {
            ParentResponse<ApPatientAdministrativeWarnings> response = new ParentResponse<>();


            apPatientAdministrativeWarningsService.deleteRecord(administrativeWarning);


            response.setObject(administrativeWarning);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error updating patient administrative warning: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(value ="/age-group-value", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAgeGroupDate(@Nullable @RequestHeader("dob") String date,
                                             @Nullable @RequestHeader String facility_id,
                                             @Nullable @RequestHeader String access_token,
                                             @Nullable @RequestHeader Integer access_level,
                                             @Nullable @RequestHeader String lang){
        try {
            ParentResponse<ApLovValues> response = new ParentResponse<>();
            String ageGroupLKey = apAgeGroupService.getAgeGroupLKey(date);
            System.out.println("Age Group LKey: " + ageGroupLKey);
            apLovValuesService.getRecord(ageGroupLKey);

            response.setObject(apLovValuesService.getRecord(ageGroupLKey));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }}

}





