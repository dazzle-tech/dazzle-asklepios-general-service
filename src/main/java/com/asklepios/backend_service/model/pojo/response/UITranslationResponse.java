package com.asklepios.backend_service.model.pojo.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class UITranslationResponse {
    String lang;
    Map<String, String> translations;
}
