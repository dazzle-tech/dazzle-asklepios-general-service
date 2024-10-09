package com.asklepios.backend_service.model.pojo.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ToothServiceRequest {
    String key;
    String toothKey;
    String serviceKey;
    String operation;
}
