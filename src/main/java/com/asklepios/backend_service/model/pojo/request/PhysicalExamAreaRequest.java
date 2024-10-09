package com.asklepios.backend_service.model.pojo.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhysicalExamAreaRequest {
    String key;
    String encounterKey;
    String physicalExamAreaDetailKey;
    String notes;
    String sourceOfAnswer;
    boolean pass;
    String passReason;
}
