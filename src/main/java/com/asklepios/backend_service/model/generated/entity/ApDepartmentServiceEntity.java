package com.asklepios.backend_service.model.generated.entity;

import java.io.Serializable;
import lombok.extern.slf4j.Slf4j;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import com.asklepios.backend_service.model.generated.pojo.ApLovValues;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@Slf4j
public class ApDepartmentServiceEntity implements Serializable {

    private String key;
    private String departmentKey;
    private String serviceKey;
    private Boolean isActive = true;

    private String serviceTypeLkey;
    private ApLovValues serviceTypeLvalue;

    private String serviceName;
    private BigDecimal servicePrice;

    private String serviceCurrencyLkey;
    private ApLovValues serviceCurrencyLvalue;

    @JsonIgnore
    private ApDepartmentServiceEntity translatedObject;

}
