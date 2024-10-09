package com.asklepios.backend_service.controller;

import com.asklepios.backend_service.model.generated.pojo.*;
import com.asklepios.backend_service.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nullable;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import java.util.List;

@RestController

@CrossOrigin
@Slf4j
@RequestMapping("/caching")
public class RedisController {

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

    public RedisController(ApFacilityService apFacilityService, ApLovService apLovService, ApLovValuesService apLovValuesService, @Qualifier("redisTemplateLov") RedisTemplate<String, ApLov> redisTemplateLov, @Qualifier("stringRedisTemplate1") RedisTemplate<String, String> redisTemplateString, RedisTemplate<String, ApLov> redisTemplateLov1, @Qualifier("redisTemplateTranslation") RedisTemplate<String, ApTranslation> translationRedisTemplate, @Qualifier("redisTemplateGlobalSettings") RedisTemplate<String, ApGlobalSettings> redisTemplateGlobalSettings, RedisTemplate<String, ApMessages> redisTemplateMessages, ApTranslationService apTranslationService, ApGlobalSettingsService apGlobalSettingsService, ApMessagesService apMessagesService, RedisTemplate<String, ApLovValues> redisTemplateLovValuesList, Jedis jedis) {
        this.apLovService = apLovService;
        this.apLovValuesService = apLovValuesService;
        this.redisTemplateLov = redisTemplateLov1;
        this.radisTemplateValues = redisTemplateString;
        this.translationRedisTemplate = translationRedisTemplate;
        this.redisTemplateGlobalSettings = redisTemplateGlobalSettings;
        this.redisTemplateMessages = redisTemplateMessages;
        this.apTranslationService = apTranslationService;
        this.apGlobalSettingsService = apGlobalSettingsService;
        this.apMessagesService = apMessagesService;
        this.redisTemplateLovValuesList = redisTemplateLovValuesList;
        this.jedis = jedis;
    }

    @PostConstruct
    public void loadDataIntoRedis() {
        try {

            //get all LOVs into the memory
            List<ApLov> listoflov = apLovService.getList("");
            System.out.println("Loading LOVs");
            for (ApLov lov : listoflov) {

                redisTemplateLov.opsForValue().set("apLov:" + lov.getKey(), lov);

                //get all LOVs values into the memory
                List<ApLovValues> x = apLovValuesService.getList("lov_key='" + lov.getKey() + "' order by value_order");
                String listOfvaluesJson = "";
                System.out.println("Loading LOVs Values");
                for (ApLovValues lovs : x) {
                    if (listOfvaluesJson == "")
                        listOfvaluesJson = listOfvaluesJson + new ObjectMapper().writeValueAsString(lovs);
                    else
                        listOfvaluesJson = listOfvaluesJson + "," + new ObjectMapper().writeValueAsString(lovs);

                }
                listOfvaluesJson = "[" + listOfvaluesJson + "]";
                radisTemplateValues.opsForValue().set("apLovValues:" + lov.getKey(), listOfvaluesJson);



            }



            {// get all translations texts and details into memory with translation_for =TRANS
            List<ApTranslation> listoftranslation = apTranslationService.getList(" translation_for='TRANS' LIMIT 10000");
            System.out.println("Loading Translations");
            for (ApTranslation record : listoftranslation) {
                translationRedisTemplate.opsForValue().set("Trans:" + record.getTranslationFor() + record.getLanguageKey() + record.getWordKey(), record);
                //  System.out.println(record.getWordKey());

            }}

            {// get all translations texts and details into memory with translation_for =LOV
            List<ApTranslation> listoftranslation = apTranslationService.getList(" translation_for='LOV' LIMIT 10000");
            System.out.println("Loading Translations");
            for (ApTranslation record : listoftranslation) {

                String catchkey="Trans:" + record.getTranslationFor() + record.getLanguageKey() + record.getWordKey();
                System.out.println(catchkey);
                translationRedisTemplate.opsForValue().set(catchkey, record);
                //  System.out.println(record.getWordKey());

            }}

            {// get all translations texts and details into memory with translation_for =OBJ
                List<ApTranslation> listoftranslation = apTranslationService.getList(" translation_for not in ('TRANS','LOV') LIMIT 10000");
                System.out.println("Loading Translations");
                for (ApTranslation record : listoftranslation) {
                    translationRedisTemplate.opsForValue().set( "TRANS:"+record.getTranslationFor() + record.getLanguageKey() + record.getWordKey(), record);
                    //  System.out.println(record.getWordKey());

                }}

            // get all settings into memory
            System.out.println("Loading Settings");
            List<ApGlobalSettings> listofsettings = apGlobalSettingsService.getList("");
            for (ApGlobalSettings setting : listofsettings) {
                redisTemplateGlobalSettings.opsForValue().set("Setting:" + setting.getSettingKey() + setting.getFacilityKey(), setting);

            }

            // get all messages into memory
            System.out.println("Loading Messages");
            List<ApMessages> listofmessages = apMessagesService.getList("");
            for (ApMessages message : listofmessages) {
                redisTemplateMessages.opsForValue().set("Message:" + message.getMessageId() + " lang:" + message.getLanguageCode(), message);
            }

            // get all list of value values as list  into memory
            System.out.println("Loading Lov Values List");
            List<ApLovValues> listoflovvalues = apLovValuesService.getList("");
            for (ApLovValues lovValue : listoflovvalues) {
                redisTemplateLovValuesList.opsForValue().set("apLovValuesList:" + lovValue.getKey(), lovValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping(value = "/reload-cashing", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> reloadCashing(@Nullable @RequestHeader String facility_id, @Nullable @RequestHeader String access_token, @Nullable @RequestHeader Integer access_level, @Nullable @RequestHeader String lang) {
        try {
            this.loadDataIntoRedis();

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);

        }
        return ResponseEntity.ok("");
    }

    @DeleteMapping("/delete-message")
    public ResponseEntity<String> deleteMessage(@Nullable @RequestHeader String facility_id, @Nullable @RequestHeader String access_token, @Nullable @RequestHeader Integer access_level, @Nullable @RequestHeader String lang, @RequestParam("message_id") String message_id) {
        jedis.exists("Message:" + message_id);
        long deletedCount = jedis.del("Message:" + message_id);
        System.out.println("deleted " + deletedCount);
        if (deletedCount > 0) {
            return ResponseEntity.ok("Deleted successfully.");
        } else {
            return ResponseEntity.status(404).body("Not found or failed to delete.");
        }
    }
}
