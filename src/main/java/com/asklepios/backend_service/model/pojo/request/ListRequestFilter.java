package com.asklepios.backend_service.model.pojo.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListRequestFilter {
    String fieldName;
    String operator;
    String value;
}
