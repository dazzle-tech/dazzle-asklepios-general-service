package com.asklepios.backend_service.model.newEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Slf4j
public class ServiceSetupRecord implements Serializable {

    private Long key;
    private String name;
    private String abbreviation;
    private String code;
    private String category;
    private BigDecimal price;
    private String currency;
    private Boolean isActive;
    private Long facilityId;
    private Boolean appointable;
    private Integer parallelCapacityValue;
    private Integer defaultDurationMinutes;
    private Integer defaultBufferBeforeMinutes;
    private Integer defaultBufferAfterMinutes;
}
