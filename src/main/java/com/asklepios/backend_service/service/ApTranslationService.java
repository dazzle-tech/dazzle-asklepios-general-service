package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.asklepios.backend_service.model.generated.pojo.ApTranslation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApTranslationDAO;

@Service
@Slf4j
public class ApTranslationService extends ApTranslationDAO implements Serializable {

    public Map<String, String> getUiTranslations(String lang) throws Exception {
        Map<String, String> map = new HashMap<>();
        List<ApTranslation> list = getList("translation_for = 'UI' and language_key = '" + lang + "'");
        for (ApTranslation translation : list) {
            map.put(translation.getWordKey(), translation.getTranslationText());
        }

        return map;
    }

}