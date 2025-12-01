package com.asklepios.backend_service.model.generated.entity;

import java.io.Serializable;
import lombok.extern.slf4j.Slf4j;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Slf4j
public class ApUserDashboardComponents implements Serializable {

    private String key;
    private BigDecimal user_id;
    private String component_key;

}