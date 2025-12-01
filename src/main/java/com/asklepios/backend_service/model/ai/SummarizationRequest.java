package com.asklepios.backend_service.model.ai;

public class SummarizationRequest {
    private String text;

    public SummarizationRequest() {}

    public SummarizationRequest(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
