package com.asklepios.backend_service.controller;

import com.asklepios.backend_service.model.generated.pojo.ApPatient;
import com.asklepios.backend_service.model.generated.pojo.ApTenant;
import com.asklepios.backend_service.model.pojo.response.ParentResponse;
import com.asklepios.backend_service.service.ApPatientService;
import com.asklepios.backend_service.service.ApTenantService;
import com.asklepios.backend_service.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/general")
public class GeneralController {
    // this class services should be available in all servers
    private final ApTenantService apTenantService;
    private final AuthService authService;
    private final ApPatientService apPatientService;
    private final PublicServices publicServices;

    public GeneralController(ApTenantService apTenantService, AuthService authService, ApPatientService apPatientService, PublicServices publicServices) {
        this.apTenantService = apTenantService;
        this.authService = authService;
        this.apPatientService = apPatientService;
        this.publicServices = publicServices;
    }

    @GetMapping(value = "/test-patient", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ParentResponse> testPatient() throws Exception {
        ParentResponse<ApPatient> response = new ParentResponse<>();

        ApPatient patient = apPatientService.getRecord("1");
        apPatientService.populateLovFields(patient, null);

        response.setData(patient);
        response.addGeneralError("Patient retrieved successfully");
        response.addError("name", "name should be 5 characters");

        publicServices.getObjectTranslation("10", patient, "eng");
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/get-tenant", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getTenant(@RequestParam String tenantId, @RequestHeader String access_token) {
        ParentResponse<ApTenant> response = new ParentResponse<>();
        try {
            if (!authService.validateTenantToken(access_token, tenantId)) {
                return ResponseEntity.status(401).build();
            }
            ApTenant tenant = apTenantService.getRecord(tenantId);
            if (tenant == null) {
                response.addGeneralError("Tenant not found");
            } else {
                response.setObject(tenant);
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

}
