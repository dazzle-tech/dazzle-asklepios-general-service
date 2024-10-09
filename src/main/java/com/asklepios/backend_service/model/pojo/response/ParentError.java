package com.asklepios.backend_service.model.pojo.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class ParentError {
    String fieldId;
    String errorMessage;
}
