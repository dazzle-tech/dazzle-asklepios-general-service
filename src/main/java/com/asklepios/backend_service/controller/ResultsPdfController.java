package com.asklepios.backend_service.controller;

import com.asklepios.backend_service.service.ApScreenService;
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
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/pas")
@RequiredArgsConstructor
@Slf4j
public class ResultsPdfController {

    private final ApScreenService screenService;

    @PostMapping("/generate-results-pdf")
    public ResponseEntity<?> generateResultsPdf(@RequestBody Map<String, Object> requestData) {
        try {
            log.info("=== Received Results PDF generation request ===");
            log.info("Request data keys: {}", requestData.keySet());

            // Validate request data
            if (!requestData.containsKey("patientInfo") ||
                    !requestData.containsKey("results")) {
                log.error("Missing required fields in request");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(createErrorResponse("Missing required fields: patientInfo or results"));
            }

            // Log data for debugging
            Map<String, Object> patientInfo = (Map<String, Object>) requestData.get("patientInfo");
            List<Map<String, Object>> results = (List<Map<String, Object>>) requestData.get("results");

            log.info("Patient Name: {}", patientInfo != null ? patientInfo.get("name") : "N/A");
            log.info("Results Count: {}", results != null ? results.size() : 0);

            // Generate PDF
            log.info("Starting PDF generation...");
            byte[] pdfBytes = screenService.generateResultsPdf(requestData);

            if (pdfBytes == null || pdfBytes.length == 0) {
                log.error("Generated PDF is empty");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(createErrorResponse("Failed to generate PDF - empty result"));
            }

            // Generate filename with timestamp
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String timestamp = sdf.format(new Date());
            String patientName = patientInfo != null && patientInfo.get("name") != null
                    ? String.valueOf(patientInfo.get("name")).replaceAll("[^a-zA-Z0-9]", "_")
                    : "Patient";
            String fileName = String.format("Lab_Results_%s_%s.pdf", patientName, timestamp);

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
            log.error("Unexpected error generating Results PDF: {}", e.getMessage(), e);
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(createErrorResponse("Failed to generate Results PDF: " + e.getMessage()));
        }
    }

    @GetMapping("/results-health")
    public ResponseEntity<Map<String, String>> healthCheck() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "Lab Results PDF Generator");
        response.put("timestamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        log.info("Health check called - Service is UP");
        return ResponseEntity.ok(response);
    }

    private Map<String, String> createErrorResponse(String message) {
        Map<String, String> error = new HashMap<>();
        error.put("error", message);
        error.put("timestamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return error;
    }
}