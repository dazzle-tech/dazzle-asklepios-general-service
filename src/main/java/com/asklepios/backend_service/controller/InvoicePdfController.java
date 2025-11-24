package com.asklepios.backend_service.controller;

import com.asklepios.backend_service.service.ApAllergensService;
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
public class InvoicePdfController {

    private final ApAllergensService allergensService;

    @PostMapping("/generate-invoice-pdf")
    public ResponseEntity<?> generateInvoicePdf(@RequestBody Map<String, Object> requestData) {
        try {
            log.info("=== Received Invoice PDF generation request ===");
            log.info("Request data keys: {}", requestData.keySet());
            log.info("Request body: {}", requestData);

            // Validate request data - visitInfo is optional now
            if (!requestData.containsKey("patientInfo")) {
                log.error("Missing patientInfo in request");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(createErrorResponse("Missing required field: patientInfo"));
            }

            if (!requestData.containsKey("invoiceInfo")) {
                log.error("Missing invoiceInfo in request");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(createErrorResponse("Missing required field: invoiceInfo"));
            }

            if (!requestData.containsKey("items")) {
                log.error("Missing items in request");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(createErrorResponse("Missing required field: items"));
            }

            // Add visitInfo if not present (for backward compatibility)
            if (!requestData.containsKey("visitInfo")) {
                log.warn("visitInfo not provided, using default values");
                Map<String, String> defaultVisitInfo = new HashMap<>();
                defaultVisitInfo.put("visitDate", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
                defaultVisitInfo.put("visitType", "General");
                requestData.put("visitInfo", defaultVisitInfo);
            }

            // Log data for debugging
            Map<String, Object> patientInfo = (Map<String, Object>) requestData.get("patientInfo");
            Map<String, Object> invoiceInfo = (Map<String, Object>) requestData.get("invoiceInfo");
            List<Map<String, Object>> items = (List<Map<String, Object>>) requestData.get("items");

            log.info("Patient Name: {}", patientInfo != null ? patientInfo.get("name") : "N/A");
            log.info("Invoice Number: {}", invoiceInfo != null ? invoiceInfo.get("invoiceNumber") : "N/A");
            log.info("Items Count: {}", items != null ? items.size() : 0);

            // Generate PDF
            log.info("Starting Invoice PDF generation...");
            byte[] pdfBytes = allergensService.generateInvoicePdf(requestData);

            if (pdfBytes == null || pdfBytes.length == 0) {
                log.error("Generated PDF is empty");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(createErrorResponse("Failed to generate PDF - empty result"));
            }

            // Generate filename with timestamp
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String timestamp = sdf.format(new Date());
            String invoiceNumber = invoiceInfo != null && invoiceInfo.get("invoiceNumber") != null
                    ? String.valueOf(invoiceInfo.get("invoiceNumber")).replaceAll("[^a-zA-Z0-9]", "_")
                    : "INV";
            String fileName = String.format("Invoice_%s_%s.pdf", invoiceNumber, timestamp);

            // Set response headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", fileName);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            headers.setContentLength(pdfBytes.length);
            headers.set("X-Content-Type-Options", "nosniff");

            log.info("Invoice PDF generated successfully - Size: {} bytes, Filename: {}",
                    pdfBytes.length, fileName);
            log.info("=== Invoice PDF generation completed successfully ===");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);

        } catch (Exception e) {
            log.error("Unexpected error generating Invoice PDF: {}", e.getMessage(), e);
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(createErrorResponse("Failed to generate Invoice PDF: " + e.getMessage()));
        }
    }

    @GetMapping("/invoice-health")
    public ResponseEntity<Map<String, String>> healthCheck() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "Invoice PDF Generator");
        response.put("timestamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        log.info("Health check called - Invoice Service is UP");
        return ResponseEntity.ok(response);
    }

    private Map<String, String> createErrorResponse(String message) {
        Map<String, String> error = new HashMap<>();
        error.put("error", message);
        error.put("timestamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return error;
    }
}