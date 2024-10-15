package com.asklepios.backend_service.controller;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.*;
import com.asklepios.backend_service.model.pojo.request.ListRequest;
import com.asklepios.backend_service.model.pojo.response.ParentResponse;
import com.asklepios.backend_service.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dvm")
//@CrossOrigin
@Slf4j
public class DvmController implements Serializable {

    private final AuthService authService;
    private final ApMetadataService apMetadataService;
    private final ApMetadataFieldService apMetadataFieldService;
    private final ApScreenMetadataService apScreenMetadataService;
    private final ApDvmRuleService apDvmRuleService;

    public DvmController(AuthService authService, ApMetadataService apMetadataService, ApMetadataFieldService apMetadataFieldService, ApScreenMetadataService apScreenMetadataService, ApDvmRuleService apDvmRuleService) {
        this.authService = authService;
        this.apMetadataService = apMetadataService;
        this.apMetadataFieldService = apMetadataFieldService;
        this.apScreenMetadataService = apScreenMetadataService;
        this.apDvmRuleService = apDvmRuleService;
    }


    @GetMapping(value = "/metadata-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listMetadata(@RequestParam Map<String, String> queryParams,
                                       @Nullable @RequestHeader String access_token,
                                       @Nullable @RequestHeader String facility_id,
                                       @Nullable @RequestHeader Integer access_level,
                                       @Nullable @RequestHeader String lang) {
        ParentResponse<List<ApMetadata>> response = new ParentResponse<>();
        try {
            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false,false);
            List<ApMetadata> list = apMetadataService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_metadata where " + whereForTotal);
            response.setObject(list);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.addGeneralError(e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping(value = "/metadata-fields-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listMetadataFields(@RequestParam Map<String, String> queryParams,
                                                @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApMetadataField>> response = new ParentResponse<>();
            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false,false);
            List<ApMetadataField> list = apMetadataFieldService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_metadata_field where " + whereForTotal);
            response.setObject(list);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(value = "/screen-metadata-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listScreenMetadata(@RequestParam Map<String, String> queryParams,
                                             @Nullable @RequestHeader String access_token,
                                             @Nullable @RequestHeader String facility_id,
                                             @Nullable @RequestHeader Integer access_level,
                                             @Nullable @RequestHeader String lang) {
        ParentResponse<List<ApScreenMetadata>> response = new ParentResponse<>();
        try {
            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false,false);
            List<ApScreenMetadata> list = apScreenMetadataService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_screen_metadata where " + whereForTotal);
            for (ApScreenMetadata screenMetadata : list) {
                ApMetadata metadata = apMetadataService.getRecord(screenMetadata.getMetadataKey());
                screenMetadata.setMetadataObject(metadata);
                screenMetadata.setMetadataObjectName(metadata.getObjectName());
            }
            response.setObject(list);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.addGeneralError(e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @PostMapping(value = "/save-screen-metadata", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveScreenMetadata(@RequestBody ApScreenMetadata screenMetadata,
                                                @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApScreenMetadata> response = new ParentResponse<>();
            apScreenMetadataService.saveRecord(screenMetadata);
            response.setObject(screenMetadata);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }


    @GetMapping(value = "/dvm-rule-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listDvmRules(@RequestParam Map<String, String> queryParams,
                                             @Nullable @RequestHeader String access_token,
                                             @Nullable @RequestHeader String facility_id,
                                             @Nullable @RequestHeader Integer access_level,
                                             @Nullable @RequestHeader String lang) {
        ParentResponse<List<ApDvmRule>> response = new ParentResponse<>();
        try {
            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false,false);
            List<ApDvmRule> list = apDvmRuleService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_dvm_rule where " + whereForTotal);
            response.setObject(list);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.addGeneralError(e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @PostMapping(value = "/save-dvm-rule", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveDvmRule(@RequestBody ApDvmRule apDvmRule,
                                                @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApDvmRule> response = new ParentResponse<>();
            apDvmRuleService.saveRecord(apDvmRule);
            response.setObject(apDvmRule);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }


}
