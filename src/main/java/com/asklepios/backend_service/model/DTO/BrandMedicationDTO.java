package com.asklepios.backend_service.model.DTO;

import java.math.BigDecimal;

public class BrandMedicationDTO {

    private BigDecimal id;
    private String name;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
