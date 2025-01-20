package com.asklepios.backend_service.controller;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.ApAttachment;
import com.asklepios.backend_service.model.generated.pojo.ApPatient;
import com.asklepios.backend_service.model.generated.pojo.ApPatientSecondaryDocuments;
import com.asklepios.backend_service.model.pojo.request.ListRequest;
import com.asklepios.backend_service.model.pojo.response.ParentResponse;
import com.asklepios.backend_service.service.*;
import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/attachment")
//@CrossOrigin
@Slf4j
public class AttachmentController {

    private final ApPatientService apPatientService;
    private final ApAttachmentService apAttachmentService;
    private final ApUserService apUserService;
    //getAttachmentsList
    public AttachmentController(ApPatientService apPatientService, ApAttachmentService apAttachmentService, ApUserService apUserService) {
        this.apPatientService = apPatientService;
        this.apAttachmentService = apAttachmentService;
        this.apUserService = apUserService;
    }

    @GetMapping(value = "/fetch-attachment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> fetchAttachment(@RequestHeader("type") String type,
                                             @RequestHeader("ref_key") String refKey,
                                             @Nullable @RequestHeader String facility_id,
                                             @Nullable @RequestHeader String access_token,
                                             @Nullable @RequestHeader Integer access_level,
                                             @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApAttachment> response = new ParentResponse<>();
            ApAttachment attachment = new ApAttachment();

            // check if such type and reference key exist
            List<ApAttachment> exising = apAttachmentService.getList("attachment_type = '" + type
                    + "' and reference_object_key = '" + refKey + "' and deleted_at is null");
            if (exising != null && !exising.isEmpty()) {
                attachment = exising.get(0);
                response.setObject(attachment);
                return ResponseEntity.ok(response);
            } else {
                // no attachment found, send empty response
                response.setObject(new ApAttachment());
                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(value = "/fetch-attachment-bykey", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> fetchAttachmentByKey(
            @RequestHeader("Key") String Key,
            @Nullable @RequestHeader String facility_id,
            @Nullable @RequestHeader String access_token,
            @Nullable @RequestHeader Integer access_level,
            @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApAttachment> response = new ParentResponse<>();
            ApAttachment attachment = apAttachmentService.getRecord(Key);
            System.out.println("Key "+ Key);
            if (attachment != null) {
                response.setObject(attachment);
            } else {
                response.setObject(new ApAttachment());
            }

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


    @GetMapping(value = "/fetch-attachment-light", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> fetchAttachmentLight(
            @Nullable @RequestHeader("ref_key") String refKey,
            @Nullable @RequestHeader String facility_id,
            @Nullable @RequestHeader String access_token,
            @Nullable @RequestHeader Integer access_level,
            @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApAttachment>> response = new ParentResponse<>();


            List<ApAttachment> attachments = apAttachmentService.getAttachmentsList("reference_object_key = '" + refKey + "'"); // Assuming empty string means no filters
            System.out.println(refKey);
            System.out.println("reference_object_key = '" + refKey + "'");
            response.setObject(attachments);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @GetMapping(value = "/patient-attachment-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> patientAttachmentList(@RequestParam Map<String, String> queryParams,
                                                   @Nullable @RequestHeader String key,
                                                   @Nullable @RequestHeader String access_token,
                                                   @Nullable @RequestHeader Integer access_level,
                                                   @Nullable @RequestHeader String lang) {
        try {

            ParentResponse<List<ApAttachment>> response = new ParentResponse<>();

            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);
            System.out.println("used : " + where);
            List<ApAttachment> attachments = apAttachmentService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_attachment where " + whereForTotal);

            for (ApAttachment all : attachments) {
                apAttachmentService.populateLovFields(all, lang);
                if (all.getCreatedBy() != null) {
                    all.setCreatedByUser(apUserService.getRecord(all.getCreatedBy()));
                }
                if (all.getUpdatedBy() != null) {
                    all.setUpdatedByUser(apUserService.getRecord(all.getUpdatedBy()));
                }
            }
            response.setObject(attachments);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }
    //add  Access_Type_filed
    @PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file,
                                    @RequestHeader("type") String type,
                                    @RequestHeader("ref_key") String refKey,
                                    @Nullable @RequestHeader("details") String details,
                                    @Nullable @RequestHeader("access_type") String accessType,
                                    @Nullable @RequestHeader("created_by") String createdBy,
                                    @Nullable @RequestHeader String facility_id,
                                    @Nullable @RequestHeader String access_token,
                                    @Nullable @RequestHeader Integer access_level,
                                    @Nullable @RequestHeader String lang) {
        System.out.println("access_type"+accessType);
        try {
            ParentResponse<ApAttachment> response = new ParentResponse<>();
            ApAttachment attachment = new ApAttachment();
            System.out.println(details);
            if ("PATIENT_PROFILE_PICTURE".equals(type)) {
                // check if such type and reference key exist
                List<ApAttachment> exising = apAttachmentService.getList("attachment_type = '" + type
                        + "' and reference_object_key = '" + refKey + "'");
                if (exising != null && !exising.isEmpty()) {

                    attachment = exising.get(0);
                    attachment.setDeletedAt(null);

                } else {
                    attachment.setAttachmentType(type);
                    attachment.setReferenceObjectKey(refKey);
                    attachment.setAccessTypeLkey(accessType);
                    attachment.setCreatedBy(createdBy);
                }
            } else {
                // Always create a new attachment for other types
                attachment.setAttachmentType(type);
                attachment.setReferenceObjectKey(refKey);
                attachment.setAccessTypeLkey(accessType);
                attachment.setCreatedBy(createdBy);
            }
            attachment.setFileName(file.getOriginalFilename());
            attachment.setContentType(file.getContentType());
            attachment.setFileContent(file.getBytes());
            attachment.setExtraDetails(details);
            apAttachmentService.saveRecord(attachment);
            response.setObject(attachment);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }


    @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteAttachment(@RequestHeader("key") String key,
                                              @Nullable @RequestHeader String facility_id,
                                              @Nullable @RequestHeader String access_token,
                                              @Nullable @RequestHeader Integer access_level,
                                              @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApAttachment> response = new ParentResponse<>();

            ApAttachment attachment = apAttachmentService.getRecord(key);
            if (attachment != null) {
                apAttachmentService.deleteRecord(attachment);
                response.setObject(attachment);

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

    @PutMapping(value = "/update-Attachment-details", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateAttachmentDetails(
            @RequestHeader("key") String key,
            @RequestHeader("attachmentDetails") String extraDetails,
            @RequestHeader("updatedBy") String updatedBy,
            @RequestHeader("accessType") String accessType,
            @Nullable @RequestHeader String facility_id,
            @Nullable @RequestHeader String access_token,
            @Nullable @RequestHeader Integer access_level,
            @Nullable @RequestHeader String lang) {
        System.out.println("attachmentDetails :"+extraDetails);
        try {
            ParentResponse<ApAttachment> response = new ParentResponse<>();

            ApAttachment attachment = apAttachmentService.getRecord(key);
            if (attachment != null) {
                attachment.setExtraDetails(extraDetails);
                attachment.setUpdatedBy(updatedBy);
                attachment.setAccessTypeLkey(accessType);
                apAttachmentService.updateRecord(attachment);
                response.setObject(attachment);

                return ResponseEntity.ok(response);
            } else {

                return ResponseEntity.status(404).body("Attachment not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
