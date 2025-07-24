package com.asklepios.backend_service.model.DTO;

public class EnumOption {
    private String name;
    private String label;

    public EnumOption(String name, String label) {
        this.name = name;
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }
}
