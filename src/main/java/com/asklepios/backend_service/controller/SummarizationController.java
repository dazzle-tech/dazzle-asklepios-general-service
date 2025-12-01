package com.asklepios.backend_service.controller;

import com.asklepios.backend_service.model.ai.SummarizationResponse;
import com.asklepios.backend_service.service.SummarizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/encounter")
public class SummarizationController {

    private final SummarizationService summarizationService;

    public SummarizationController(SummarizationService summarizationService) {
        this.summarizationService = summarizationService;
    }

    // Simple request body: { "text": "..." }
    @PostMapping("/api/summarization/summarize")
    public ResponseEntity<?> summarize(@RequestBody Map<String, String> body) {
        String text = body.get("text");
        if (text == null || text.isBlank()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Field 'text' is required"));
        }

        SummarizationResponse response = summarizationService.summarizeText(text);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/summarization/health")
    public Map<String, String> health() {
        return Map.of("status", "OK");
    }
}
