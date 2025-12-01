package com.asklepios.backend_service.model.ai;

import java.util.List;

public class ParsingRequest {
    private List<String> text_lines;

    public ParsingRequest() {}

    public ParsingRequest(List<String> text_lines) {
        this.text_lines = text_lines;
    }

    public List<String> getText_lines() {
        return text_lines;
    }

    public void setText_lines(List<String> text_lines) {
        this.text_lines = text_lines;
    }
}
