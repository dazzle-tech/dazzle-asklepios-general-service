package com.asklepios.backend_service.service;

import com.asklepios.backend_service.model.DTO.PatientSummaryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class PatientSummaryAIService {

    private final RestTemplate restTemplate;
    private final String summarizationUrl;

    @Autowired
    public PatientSummaryAIService(
            RestTemplate restTemplate,
            @Value("${ai.summarization.url:http://localhost:8003/summarize}")
            String summarizationUrl
    ) {
        this.restTemplate = restTemplate;
        this.summarizationUrl = summarizationUrl;
    }

    public String getClinicalSummary(PatientSummaryDto dto) {

        Map<String, Object> body = new HashMap<>();

        // =====================================================================
        // AGE — بدون أي حساب أو استخراج أرقام
        // =====================================================================
        String rawAge = dto.getAge() == null ? "" : dto.getAge();
        String unit = "years"; // إذا تريد يمكن تغييره أو جعله فارغًا ""

        body.put("Age", rawAge);
        body.put("Age_Unit", unit);

        // =====================================================================
        // BASIC FIELDS
        // =====================================================================
        body.put("Gender", dto.getGender() == null ? "" : dto.getGender());
        body.put("Diagnosis", dto.getDiagnosis() == null ? "" : dto.getDiagnosis());

        // =====================================================================
        // SYMPTOMS (من multiline string إلى List<String>)
        // =====================================================================
        List<String> symptomsList = new ArrayList<>();
        if (dto.getSymptoms() != null && !dto.getSymptoms().isEmpty()) {
            for (String line : dto.getSymptoms().split("\n")) {
                if (!line.isBlank()) {
                    symptomsList.add(line.trim());
                }
            }
        }
        body.put("Symptoms", symptomsList);

        // =====================================================================
        // OTHER LISTS
        // =====================================================================
        body.put("Medications",
                dto.getMedications() == null ? Collections.emptyList() : dto.getMedications());
        body.put("Surgeries",
                dto.getSurgeries() == null ? Collections.emptyList() : dto.getSurgeries());
        body.put("Allergies",
                dto.getAllergies() == null ? Collections.emptyList() : dto.getAllergies());
        body.put("Problems",
                dto.getProblems() == null ? Collections.emptyList() : dto.getProblems());

        // =====================================================================
        // MEDICAL WARNINGS
        // =====================================================================
        List<String> warnings = new ArrayList<>();
        if (dto.getMedicalWarnings() != null && !dto.getMedicalWarnings().isEmpty()) {
            warnings.add(dto.getMedicalWarnings());
        }
        body.put("Medical_Warnings", warnings);

        // =====================================================================
        // VITALS (من multiline string إلى Map<String, String>)
        // =====================================================================
        Map<String, String> vitalsMap = new LinkedHashMap<>();
        if (dto.getVitals() != null && !dto.getVitals().isEmpty()) {
            for (String line : dto.getVitals().split("\n")) {
                if (!line.contains(":"))
                    continue;

                String[] parts = line.split(":", 2);
                String key = parts[0].trim();
                String value = parts[1].trim();

                if (!key.isEmpty() && !value.isEmpty()) {
                    vitalsMap.put(key, value);
                }
            }
        }
        body.put("Vitals", vitalsMap);

        // =====================================================================
        // SEND REQUEST TO FASTAPI
        // =====================================================================
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

            @SuppressWarnings("unchecked")
            Map<String, Object> response =
                    restTemplate.postForObject(summarizationUrl, entity, Map.class);

            if (response == null || !response.containsKey("ClinicalSummary")) {
                return "Clinical summary not available.";
            }

            Object summary = response.get("ClinicalSummary");
            return summary == null ? "Clinical summary not available." : summary.toString();

        } catch (Exception e) {
            return "AI Summary Error: " + e.getMessage();
        }
    }
}
