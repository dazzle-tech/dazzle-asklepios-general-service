package com.asklepios.backend_service.controller;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.entity.ApPatientEntity;
import com.asklepios.backend_service.model.generated.pojo.*;
import com.asklepios.backend_service.model.pojo.ValidationResult;
import com.asklepios.backend_service.model.pojo.request.ListRequest;
import com.asklepios.backend_service.model.pojo.request.ListRequestAllValues;
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

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.entity.ApPatientEntity;
import com.asklepios.backend_service.model.generated.pojo.*;
        import com.asklepios.backend_service.model.pojo.ValidationResult;
import com.asklepios.backend_service.model.pojo.request.ListRequest;
import com.asklepios.backend_service.model.pojo.request.ListRequestAllValues;
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
@RequestMapping("/procedures")
//@CrossOrigin
@Slf4j
public class ProceduresController {

private final ApProcedureService apProcedureService;
private final ApFacilityService apFacilityService;
private final ApDepartmentService apDepartmentService;
private final ApProcedureRegistrationService apProcedureRegistrationService;
private final ApProcedureStaffService apProcedureStaffService;
    private final ApUserService apUserService;

    public ProceduresController(ApProcedureService apProcedureService, ApFacilityService apFacilityService, ApDepartmentService apDepartmentService, ApProcedureRegistrationService apProcedureRegistrationService, ApProcedureStaffService apProcedureStaffService, ApUserService apUserService) {
        this.apProcedureService = apProcedureService;
        this.apFacilityService = apFacilityService;
        this.apDepartmentService = apDepartmentService;
        this.apProcedureRegistrationService = apProcedureRegistrationService;
        this.apProcedureStaffService = apProcedureStaffService;
        this.apUserService = apUserService;
    }


    @GetMapping(value = "/procedures-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProcedureList(@RequestParam Map<String, String> queryParams,
                                              @Nullable @RequestHeader String facility_id,
                                              @Nullable @RequestHeader String access_token,
                                              @Nullable @RequestHeader Integer access_level,
                                              @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApProcedure>> response = new ParentResponse<>();

            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }

            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);

            List<ApProcedure> pro =  apProcedureService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_procedure where " + whereForTotal);

            for (ApProcedure all : pro) {
                if (all != null) {
                    apProcedureService.populateLovFields(all, lang);
                    if (all.getFacilityKey() != null) {
                        ApFacility facility = apFacilityService.getRecord(all.getFacilityKey());
                        if (facility != null) {
                            apFacilityService.populateLovFields(facility, lang);
                            all.setFacility(facility);
                        }
                    }
                    if (all.getDepartmentKey() != null) {
                        ApDepartment department = apDepartmentService.getRecord(all.getDepartmentKey());
                        if (department != null) {
                            apDepartmentService.populateLovFields(department, lang);
                            all.setDepartment(department);
                        }
                    }
                    if (all.getProcedureNameKey() != null) {
                        String procedureName = apProcedureService.getProcedureName(all.getProcedureNameKey());
                        if (procedureName != null && !procedureName.isEmpty()) {
                            all.setProcedureName(procedureName);
                        }
                    }

                }
            }


            response.setObject(pro);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @PostMapping(value = "/save-procedures", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveProcedure(@RequestBody ApProcedure request,
                                           @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                           @jakarta.annotation.Nullable @RequestHeader String access_token,
                                           @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                           @jakarta.annotation.Nullable @RequestHeader String lang

    ) {
        try {
            if(request.getKey()==null){
                BigDecimal lastpreId = DS.executeDecimalResultQuery("select max(procedure_id) from ap_procedure");
                BigDecimal newpreId;
                if (lastpreId == null) {
                    newpreId= BigDecimal.valueOf(100);
                } else {
                    newpreId = lastpreId.add(BigDecimal.ONE);
                }
                request.setProcedureId(newpreId.toString());}
            ParentResponse<ApProcedure> response = new ParentResponse<>();
            apProcedureService.saveRecord(request);
            response.setObject(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @GetMapping(value = "/procedures-registration-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProcedureRegistrationList(@RequestParam Map<String, String> queryParams,
                                                          @Nullable @RequestHeader String facility_id,
                                                          @Nullable @RequestHeader String access_token,
                                                          @Nullable @RequestHeader Integer access_level,
                                                          @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApProcedureRegistration>> response = new ParentResponse<>();

            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }

            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);

            List<ApProcedureRegistration> pro =  apProcedureRegistrationService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_procedure_registration where " + whereForTotal);

            for (ApProcedureRegistration all : pro) {

                apProcedureRegistrationService.populateLovFields(all, lang);



            }


            response.setObject(pro);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/save-registration-procedures", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveProcedureRegistration(@RequestBody ApProcedureRegistration request,
                                                       @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                       @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                       @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                       @jakarta.annotation.Nullable @RequestHeader String lang

    ) {
        try {

            ParentResponse<ApProcedureRegistration> response = new ParentResponse<>();
            apProcedureRegistrationService.saveRecord(request);
            response.setObject(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @GetMapping(value = "/procedure-staff-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProcedureStaffList(@RequestParam Map<String, String> queryParams,
                                                   @RequestHeader(required = false) String facility_id,
                                                   @RequestHeader(required = false) String access_token,
                                                   @RequestHeader(required = false) Integer access_level,
                                                   @RequestHeader(required = false) String lang) {
        try {
            ParentResponse<List<ApProcedureStaff>> response = new ParentResponse<>();

            if ("true".equals(queryParams.get("ignore"))) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }

            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);

            List<ApProcedureStaff> list = apProcedureStaffService.getList(where);
            BigDecimal total = DS.executeDecimalResultQuery("SELECT COUNT(0) FROM ap_procedure_staff WHERE " + whereForTotal);

            for (ApProcedureStaff item : list) {
                item.setUser(apUserService.getRecord(item.getUserKey()));
                apProcedureStaffService.populateLovFields(item, lang);
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

    @PostMapping(value = "/save-procedure-staff", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveProcedureStaff(@RequestBody ApProcedureStaff request,
                                                @RequestHeader(required = false) String facility_id,
                                                @RequestHeader(required = false) String ap_icd_codeaccess_token,
                                                @RequestHeader(required = false) Integer access_level,
                                                @RequestHeader(required = false) String lang) {
        try {
            ParentResponse<ApProcedureStaff> response = new ParentResponse<>();
            apProcedureStaffService.saveRecord(request);
            response.setObject(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @DeleteMapping(value = "/delete-procedure-staff", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteProcedureStaff(@RequestParam String key) {
        try {
            ApProcedureStaff staff = apProcedureStaffService.getRecord(key);
            ParentResponse<ApProcedureStaff> response = new ParentResponse<>();
            if (staff == null) {
                response.setMsg("Staff record not found");
                response.setObject(null);
                return ResponseEntity.ok(response);
            }

            apProcedureStaffService.deleteRecord(staff);

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
