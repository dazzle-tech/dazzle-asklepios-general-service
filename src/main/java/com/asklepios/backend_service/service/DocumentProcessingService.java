package com.asklepios.backend_service.service;

import com.asklepios.backend_service.model.ai.OcrResponse;
import com.asklepios.backend_service.model.ai.ParsingRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class DocumentProcessingService {

    private final RestTemplate restTemplate;

    @Value("${ai.ocr.url}")
    private String ocrUrl;

    @Value("${ai.parsing.url}")
    private String parsingUrl;

    public DocumentProcessingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<String, Object> processDocument(MultipartFile file) throws IOException {
        // 1) Call OCR FastAPI service with the uploaded image
        OcrResponse ocrResponse = callOcrService(file);
        List<String> textLines = (ocrResponse != null && ocrResponse.getText_lines() != null)
                ? ocrResponse.getText_lines()
                : Collections.emptyList();

        // 2) Call parsing FastAPI service with the OCR text lines
        ParsingRequest parsingRequest = new ParsingRequest(textLines);

        ResponseEntity<Map> parsingResponse = restTemplate.postForEntity(
                parsingUrl,
                parsingRequest,
                Map.class
        );

        return parsingResponse.getBody();
    }

    private OcrResponse callOcrService(MultipartFile file) throws IOException {
        // Prepare multipart/form-data request
        ByteArrayResource fileAsResource = new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        };

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        // "file" here MUST match the parameter name in FastAPI: file: UploadFile = File(...)
        body.add("file", fileAsResource);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<OcrResponse> response = restTemplate.exchange(
                ocrUrl,
                HttpMethod.POST,
                requestEntity,
                OcrResponse.class
        );

        return response.getBody();
    }
}
