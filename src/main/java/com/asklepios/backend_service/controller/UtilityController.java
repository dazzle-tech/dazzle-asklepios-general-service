package com.asklepios.backend_service.controller;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.*;
import com.asklepios.backend_service.model.pojo.request.ListRequest;
import com.asklepios.backend_service.model.pojo.response.ParentResponse;
import com.asklepios.backend_service.model.pojo.response.UITranslationResponse;
import com.asklepios.backend_service.service.*;
import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import redis.clients.jedis.Jedis;

@RestController
@RequestMapping("/utility")
@CrossOrigin
@Slf4j
/*
this class will serve as the center point service for all LOVs, Users roles fetching, lookups , and non-transactional objects
 */
public class UtilityController implements Serializable {

    private final ApFacilityService apFacilityService;
    private final ApLovService apLovService;
    private final ApLovValuesService apLovValuesService;
    private final RedisTemplate<String, ApLov> redisTemplateLov;
    private final RedisTemplate<String, String> radisTemplateValues;
    private final RedisTemplate<String, ApTranslation> translationRedisTemplate;
    private final RedisTemplate<String, ApGlobalSettings> redisTemplateGlobalSettings;
    private final RedisTemplate<String, ApMessages> redisTemplateMessages;
    private final ApTranslationService apTranslationService;
    private final ApGlobalSettingsService apGlobalSettingsService;
    private final ApMessagesService apMessagesService;
    private final RedisTemplate<String, ApLovValues> redisTemplateLovValuesList;
    private final Jedis jedis;
    private final PublicServices publicServices;
    private final AuthService authService;

    public UtilityController(ApFacilityService apFacilityService, ApLovService apLovService, ApLovValuesService apLovValuesService, @Qualifier("redisTemplateLov") RedisTemplate<String, ApLov> redisTemplateLov, @Qualifier("stringRedisTemplate1") RedisTemplate<String, String> redisTemplateString, @Qualifier("redisTemplateTranslation") RedisTemplate<String, ApTranslation> translationRedisTemplate, @Qualifier("redisTemplateGlobalSettings") RedisTemplate<String, ApGlobalSettings> redisTemplateGlobalSettings, RedisTemplate<String, ApMessages> redisTemplateMessages, ApTranslationService apTranslationService, ApGlobalSettingsService apGlobalSettingsService, ApMessagesService apMessagesService, RedisTemplate<String, ApLovValues> redisTemplateLovValuesList, Jedis jedis, PublicServices publicServices, AuthService authService) {
        this.apFacilityService = apFacilityService;
        this.apLovService = apLovService;
        this.apLovValuesService = apLovValuesService;
        this.redisTemplateLov = redisTemplateLov;
        this.radisTemplateValues = redisTemplateString;
        this.translationRedisTemplate = translationRedisTemplate;
        this.redisTemplateGlobalSettings = redisTemplateGlobalSettings;
        this.redisTemplateMessages = redisTemplateMessages;
        this.apTranslationService = apTranslationService;
        this.apGlobalSettingsService = apGlobalSettingsService;
        this.apMessagesService = apMessagesService;
        this.redisTemplateLovValuesList = redisTemplateLovValuesList;
        this.jedis = jedis;

        this.publicServices = publicServices;
        this.authService = authService;
    }
    /*this will load all cached data into memory, like Lovs, Lov values, Translations ....*/


    @PostMapping(value = "/get-facility", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFacility(@RequestParam("message_id") String message_id, @Nullable @RequestHeader String facility_id, @Nullable @RequestHeader String access_token, @Nullable @RequestHeader Integer access_level, @Nullable @RequestHeader String lang) {

        try {
            List<ApFacility> record = apFacilityService.getList(" facility_id='" + facility_id + "'");
            if (record == null || record.isEmpty()) {
                return ResponseEntity.status(404).build();
            }
            return ResponseEntity.ok(record);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);

        }
    }

    @PostMapping(value = "/get-message", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMessage(@Nullable @RequestHeader String facility_id, @Nullable @RequestHeader String access_token, @Nullable @RequestHeader Integer access_level, @Nullable @RequestHeader String lang, @RequestParam("message_id") String message_id, @RequestParam("message_id") String expectedMessage) {

        try {


            return ResponseEntity.ok(publicServices.getMessageFromRedis(message_id, lang, expectedMessage));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);

        }
    }

    @PostMapping(value = "/get-lov", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getLov(@Nullable @RequestHeader String facility_id, @Nullable @RequestHeader String access_token, @Nullable @RequestHeader Integer access_level, @Nullable @RequestHeader String lang, @RequestParam("key") String key) {
        try {

            return ResponseEntity.ok(redisTemplateLov.opsForValue().get("apLov:" + key));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);

        }
    }


    @PostMapping(value = "/get-lov-values", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getLovValues(@Nullable @RequestHeader String facility_id, @Nullable @RequestHeader String access_token, @Nullable @RequestHeader Integer access_level, @Nullable @RequestHeader String lang, @RequestParam("key") String key) {
        try {
            String lovJson = radisTemplateValues.opsForValue().get("apLovValues:" + key);
            publicServices.getTranslationFromRedisLovValues(lovJson, "eng");
            if (lovJson == null) {
                return ResponseEntity.status(404).body(publicServices.getMessageFromRedis("NOT_FOUND", lang, "LOV Values Records Not Found"));
            }
            if (Objects.equals(lang, "eng")) {
                return ResponseEntity.ok(lovJson);
            }
            lovJson = publicServices.getTranslationFromRedisLovValues(lovJson, lang);
            return ResponseEntity.ok(lovJson);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);

        }
    }

    @PostMapping(value = "/get-lov-value", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getLovValue(@Nullable @RequestHeader String facility_id, @Nullable @RequestHeader String access_token, @Nullable @RequestHeader Integer access_level, @Nullable @RequestHeader String lang, @RequestParam("lov_value_key") String key) {

        try {
            ApLovValues lovrecord = (ApLovValues) redisTemplateLovValuesList.opsForValue().get("apLovValuesList:" + key);
            if (lovrecord == null) {
                return ResponseEntity.status(404).body(publicServices.getMessageFromRedis("NOT_FOUND", lang, "LOV Value Record Not Found"));
            }
            if (Objects.equals(lang, "eng")) {
                return ResponseEntity.ok(lovrecord);
            }

            lovrecord = publicServices.getTranslationFromRedisLovValue("LOV", lang, lovrecord);
            return ResponseEntity.ok(lovrecord);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);

        }
    }

    @PostMapping(value = "/get-translation", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTranslation(@Nullable @RequestHeader String facility_id, @Nullable @RequestHeader String access_token, @Nullable @RequestHeader Integer access_level, @Nullable @RequestHeader String lang, @RequestParam("trans_for") String trans_for, @RequestParam("word_key") String word_key) {
        try {

            String catchkey = "Trans:" + trans_for + lang + word_key;
            System.out.println(catchkey);
            return ResponseEntity.ok(translationRedisTemplate.opsForValue().get(catchkey));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);

        }
    }

    @PostMapping(value = "/load-ui-translations")
    public ResponseEntity loadUiTranslations(@Nullable @RequestHeader String facility_id, @Nullable @RequestHeader String access_token, @Nullable @RequestHeader Integer access_level, @Nullable @RequestHeader String lang) {
        ParentResponse<UITranslationResponse> response = new ParentResponse<>();

        try {
            UITranslationResponse translationResponse = new UITranslationResponse();

            translationResponse.setLang(lang);
            translationResponse.setTranslations(apTranslationService.getUiTranslations(lang));

            response.setObject(translationResponse);
            response.setMsg("Language changed");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            response.addGeneralError(e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }


    @PostMapping(value = "/get-setting", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getSetting(@Nullable @RequestHeader String facility_id, @Nullable @RequestHeader String access_token, @Nullable @RequestHeader Integer access_level, @Nullable @RequestHeader String lang, @RequestParam("setting_key") String setting_key) {
        try {

            return ResponseEntity.ok(redisTemplateGlobalSettings.opsForValue().get("Setting:" + setting_key + facility_id));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);

        }
    }

    @GetMapping(value = "/get-lov-values-by-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> lovValueList(@RequestParam String code, @Nullable String parentValueKey,
                                          @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                          @jakarta.annotation.Nullable @RequestHeader String access_token,
                                          @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                          @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApLovValues>> response = new ParentResponse<>();
            String where = "LOV_CODE = '" + code + "'";
            if (parentValueKey != null) {
                where += " and PARENT_VALUE_ID = '" + parentValueKey + "'";
            }
            List<ApLovValues> list = apLovValuesService.getList(where);
            response.setObject(list);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

}
