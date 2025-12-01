package com.asklepios.backend_service.model.ai;

import java.util.List;

public class OcrResponse {
    private List<String> text_lines;

    public List<String> getText_lines() {
        return text_lines;
    }

    public void setText_lines(List<String> text_lines) {
        this.text_lines = text_lines;
    }
}
