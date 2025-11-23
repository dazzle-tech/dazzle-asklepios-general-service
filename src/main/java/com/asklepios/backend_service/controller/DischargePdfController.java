package com.asklepios.backend_service.controller;

import com.asklepios.backend_service.service.ApServiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/pas")
@RequiredArgsConstructor
@Slf4j
public class DischargePdfController {

    private final ApServiceService apServiceService;

    @PostMapping("/generate-discharge-pdf")
    public ResponseEntity<?> generateDischargePdf(@RequestBody Map<String, Object> dischargeData) {
        try {
            log.info("=== Received Discharge PDF generation request ===");
            log.info("Generating Discharge Summary Report with data from frontend");

            // Log received data for debugging
            if (dischargeData != null) {
                log.info("Received discharge data with keys: {}", dischargeData.keySet());
                Map<String, Object> patient = (Map<String, Object>) dischargeData.get("patient");
                if (patient != null) {
                    log.info("Patient: {}", patient.get("fullName"));
                    log.info("MRN: {}", patient.get("patientMrn"));
                }
            } else {
                log.warn("Received null discharge data");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(createErrorResponse("Discharge data is required"));
            }

            // Generate PDF with data from frontend
            log.info("Starting PDF generation...");
            byte[] pdfBytes = apServiceService.generateDischargePdf(dischargeData);

            if (pdfBytes == null || pdfBytes.length == 0) {
                log.error("Generated PDF is empty");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(createErrorResponse("Failed to generate PDF - empty result"));
            }

            // Generate filename with timestamp and patient MRN if available
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String timestamp = sdf.format(new Date());

            String patientMrn = "";
            Map<String, Object> patient = (Map<String, Object>) dischargeData.get("patient");
            if (patient != null && patient.get("patientMrn") != null) {
                patientMrn = "_" + patient.get("patientMrn").toString();
            }

            String fileName = String.format("Discharge_Summary%s_%s.pdf", patientMrn, timestamp);

            // Set response headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", fileName);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            headers.setContentLength(pdfBytes.length);
            headers.set("X-Content-Type-Options", "nosniff");

            log.info("PDF generated successfully - Size: {} bytes, Filename: {}",
                    pdfBytes.length, fileName);
            log.info("=== PDF generation completed successfully ===");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);

        } catch (Exception e) {
            log.error("Unexpected error generating Discharge PDF: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(createErrorResponse("Failed to generate Discharge PDF: " + e.getMessage()));
        }
    }

    // Helper method to create error response
    private Map<String, Object> createErrorResponse(String message) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", message);
        error.put("timestamp", new Date());
        return error;
    }

    @GetMapping("/discharge-health")
    public ResponseEntity<Map<String, String>> healthCheck() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "Discharge PDF Generator");
        response.put("timestamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return ResponseEntity.ok(response);
    }

}
