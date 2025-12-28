package com.asklepios.backend_service.service;

import com.asklepios.backend_service.model.ai.SummarizationRequest;
import com.asklepios.backend_service.model.ai.SummarizationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SummarizationService {

    private final RestTemplate restTemplate;

    @Value("${ai.summary.url}")
    private String summaryUrl;

    public SummarizationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public SummarizationResponse summarizeText(String text) {
        SummarizationRequest request = new SummarizationRequest(text);

        ResponseEntity<SummarizationResponse> response = restTemplate
                .postForEntity(summaryUrl, request, SummarizationResponse.class);

        return response.getBody();
    }
}
