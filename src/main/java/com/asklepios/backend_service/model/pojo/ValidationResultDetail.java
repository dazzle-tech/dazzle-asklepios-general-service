package com.asklepios.backend_service.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ValidationResultDetail {
    String validationType;
    String ruleType;
    String message;
}
