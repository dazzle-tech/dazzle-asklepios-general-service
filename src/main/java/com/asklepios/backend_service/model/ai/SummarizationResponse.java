package com.asklepios.backend_service.model.ai;

public class SummarizationResponse {
    private String summary;
    private String error; // optional: if Python sometimes returns {"error": ...}

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
