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

    private final ApServiceService dischargePdfService;

    @PostMapping("/generate-discharge-pdf")
    public ResponseEntity<?> generateDischargePdf() {
        try {
            log.info("=== Received Discharge PDF generation request ===");
            log.info("Generating Discharge Summary Report with static sample data");

            // Generate PDF with static data
            log.info("Starting PDF generation...");
            byte[] pdfBytes = dischargePdfService.generateDischargePdf();

            if (pdfBytes == null || pdfBytes.length == 0) {
                log.error("Generated PDF is empty");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(createErrorResponse("Failed to generate PDF - empty result"));
            }

            // Generate filename with timestamp
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String timestamp = sdf.format(new Date());
            String fileName = String.format("Discharge_Summary_Report_%s.pdf", timestamp);

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

    @GetMapping("/discharge-health")
    public ResponseEntity<Map<String, String>> healthCheck() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "Discharge PDF Generator");
        response.put("timestamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return ResponseEntity.ok(response);
    }

    private Map<String, String> createErrorResponse(String message) {
        Map<String, String> error = new HashMap<>();
        error.put("error", message);
        error.put("timestamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return error;
    }
}
