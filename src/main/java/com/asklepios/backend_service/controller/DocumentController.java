package com.asklepios.backend_service.controller;

import com.asklepios.backend_service.service.DocumentProcessingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/encounter")
public class DocumentController {

    private final DocumentProcessingService documentProcessingService;

    public DocumentController(DocumentProcessingService documentProcessingService) {
        this.documentProcessingService = documentProcessingService;
    }

    @PostMapping("/api/passport/parse")
    public ResponseEntity<?> parsePassport(@RequestParam("file") MultipartFile file) {
        try {
            Map<String, Object> result = documentProcessingService.processDocument(file);
            return ResponseEntity.ok(result);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/api/passport/health")
    public Map<String, String> health() {
        return Map.of("status", "OK");
    }
}
