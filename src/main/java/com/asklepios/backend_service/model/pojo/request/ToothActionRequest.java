package com.asklepios.backend_service.model.pojo.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ToothActionRequest {
    String key;
    String toothKey;
    String actionKey;
    String operation;
    String surface;
    String note;
    String cdtKey;
    boolean existing;
}
