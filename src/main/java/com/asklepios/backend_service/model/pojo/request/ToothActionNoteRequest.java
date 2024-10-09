package com.asklepios.backend_service.model.pojo.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ToothActionNoteRequest {
    String toothActionKey;
    String note;
}
