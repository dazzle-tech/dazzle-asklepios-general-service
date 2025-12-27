package com.asklepios.backend_service.model.DTO;

import java.util.ArrayList;
import java.util.List;

public class PatientMiniSummaryDto {

    // Major diagnosis as single string
    private String diagnosis;

    // Observation (symptoms + pain etc.)
    private String symptoms;

    // Vitals FREE TEXT
    private String vitals;

    // Allergies
    private List<String> allergies = new ArrayList<>();

    // Warnings as single string (type + warning)
    private String medicalWarnings;

    // ===== getters & setters =====

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getVitals() {
        return vitals;
    }

    public void setVitals(String vitals) {
        this.vitals = vitals;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    public String getMedicalWarnings() {
        return medicalWarnings;
    }

    public void setMedicalWarnings(String medicalWarnings) {
        this.medicalWarnings = medicalWarnings;
    }
}
