package com.asklepios.backend_service.model.pojo.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewOfSystemRequest {
    String key;
    String encounterKey;
    String bodySystemDetailKey;
    String notes;
}
