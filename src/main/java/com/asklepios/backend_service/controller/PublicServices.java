package com.asklepios.backend_service.controller;

import com.asklepios.backend_service.model.generated.pojo.ApLovValues;
import com.asklepios.backend_service.model.generated.pojo.ApMessages;
import com.asklepios.backend_service.model.generated.pojo.ApTranslation;
import jdk.jshell.spi.ExecutionControlProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Slf4j
@Service
public class PublicServices {
    private static Set<String> excludedFields = new HashSet<>();

    static {
        // Initialize the set of excluded fields
        excludedFields.add("key");
        excludedFields.add("createdBy");
        excludedFields.add("firstName");
        excludedFields.add("thirdName");
        excludedFields.add("lastName");
        excludedFields.add("fullName");
        excludedFields.add("updatedBy");
        excludedFields.add("firstNameOtherLang");
        excludedFields.add("thirdNameOtherLang");
        excludedFields.add("deletedBy");
        excludedFields.add("lastNameOtherLang");
        excludedFields.add("fullNameOtherLang");
        excludedFields.add("documentNo");
        excludedFields.add("secondName");
        excludedFields.add("secondNameOtherLang");
        excludedFields.add("patientMrn");
        excludedFields.add("phoneNumber");
        excludedFields.add("mobileNumber");
        excludedFields.add("email");
        excludedFields.add("emergencyContactName");
        excludedFields.add("emergencyContactPhone");
        excludedFields.add("postalCode");
        excludedFields.add("longitude");
        excludedFields.add("isActive");
        excludedFields.add("patientAlias");
    }

    private final RedisTemplate<String, ApMessages> redisTemplateMessages;

    private final RedisTemplate<String, String> radisTemplateValues;
    private final RedisTemplate<String, ApTranslation> apTranslationRedisTemplate;
    private final RedisTemplate<String, ApLovValues> redisTemplateLovValuesList;


    public PublicServices(RedisTemplate<String, ApMessages> redisTemplateMessages, @Qualifier("stringRedisTemplate1") RedisTemplate<String, String> radisTemplateValues, RedisTemplate<String, ApTranslation> apTranslationRedisTemplate, RedisTemplate<String, ApLovValues> redisTemplateLovValuesList) {
        this.redisTemplateMessages = redisTemplateMessages;

        this.radisTemplateValues = radisTemplateValues;
        this.apTranslationRedisTemplate = apTranslationRedisTemplate;
        this.redisTemplateLovValuesList = redisTemplateLovValuesList;
    }

    public String getTranslationObject(String objectName,String key, String fieldName,String lang,String originalValue)
    {
       ApTranslation translation= apTranslationRedisTemplate.opsForValue().get("Trans"+"OBJ:"+objectName+":"+key+":"+fieldName+":"+originalValue+lang);
        if ( translation != null) {
            String trans = translation.getTranslationText();
            if (trans != null)
                return trans;
            else {
                log.warn("no translation found for Obj" + objectName + ":" + fieldName);
                return originalValue;
            }
        }
        return originalValue;
    }
    public ApMessages getMessageFromRedis(String messageId, String lang, String expectedMessage) {
        ApMessages message = redisTemplateMessages.opsForValue().get("Message:" + messageId + " lang:" + lang);
        if (message == null) {
            log.warn(messageId, "Message not found ", lang, expectedMessage);
        }
        return message;
    }

    public ApLovValues getTranslationFromRedisLovValue(String translationFor, String lang, ApLovValues lov) {

        ApTranslation translation = apTranslationRedisTemplate.opsForValue().get("Trans:" + translationFor + lang + lov.getKey());
        if (translation != null) {
            lov.setLovDisplayVale(translation.getTranslationText());

        }
        log.warn(lov.getLovDisplayVale(), "Translation not found ", lang, lov);
        return lov;
    }

    public String getTranslationFromRedisLovValue(String translationFor, String lang, String lov_wordKey) {

        ApTranslation translation = apTranslationRedisTemplate.opsForValue().get("Trans:" + translationFor + lang + lov_wordKey);
        if (translation != null) {
            return translation.getTranslationText();

        }
        log.warn(lov_wordKey, "Translation not found ", lang, lov_wordKey);
        return lov_wordKey;
    }

    public ApLovValues getFromRedisLovValue(String lov_key) {

        ApLovValues lov = redisTemplateLovValuesList.opsForValue().get("apLovValuesList:" + lov_key);
        if (lov != null) {
            return lov;

        }
        log.warn(lov_key, "LOV not found ", lov_key);
        return lov;
    }

    public ApLovValues getFromRedisLovValue(String lov_key, String lang) {

        ApLovValues lov = redisTemplateLovValuesList.opsForValue().get("apLovValuesList:" + lov_key);
        if (lov != null) {
            if (Objects.equals(lang, "eng"))
                return lov;
            else {
                lov = getTranslationFromRedisLovValue("LOV", lang, lov);
            }

        }
        log.warn(lov_key, "LOV not found ", lov_key);
        return lov;
    }

    public String getTranslationFromRedisLovValues(String lovValuesJson, String lang) {


        try {
            ObjectMapper objectMapper = new ObjectMapper();

            List<ApLovValues> list = objectMapper.readValue(lovValuesJson, objectMapper.getTypeFactory().constructCollectionType(List.class, ApLovValues.class));
            for (int i = 0; i < list.size(); i++) {
                //System.out.println(list.get(i).getLovDisplayVale()+" "+list.get(i).getValueDescription());
                list.set(i, getTranslationFromRedisLovValue("LOV", lang, list.get(i)));

            }
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).getLovDisplayVale() + " " + list.get(i).getValueDescription());
                //list.set(i,getTranslationFromRedisLovValue("LOV",lang,list.get(i)));

            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions (e.g., JsonParseException, JsonMappingException, IOException)
            return null;
        }


        return null;
    }

    public Object getObjectTranslation(String key, Object obj, String lang) {

        System.out.println("getObjectTranslation " + obj.getClass().getSimpleName());
        Class<?> objectClass = obj.getClass();
        Class<?> superClass = objectClass.getSuperclass();
        while (superClass != null) {
            Field[] superFields = superClass.getDeclaredFields();
            for (Field superField : superFields) {
                Class<?> fieldType = superField.getType();
                String fieldName = superField.getName();
                if (fieldType == String.class && !fieldName.endsWith("Lkey") && !excludedFields.contains(fieldName)) {
                    try {

                        superField.setAccessible(true);

                        Object fieldValue = superField.get(obj);
                        String fieldTypeName = fieldType.getName();

                        System.out.println("Field Name: " + fieldName + ", Current Value: " + fieldValue + ", Data Type: " + fieldTypeName);

                        superField.set(obj, "TRANSLATED_TBD");
                    } catch (IllegalAccessException e) {
                        // Handle exceptions related to accessing and modifying fields
                        e.printStackTrace();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
            superClass = superClass.getSuperclass();
        }

        return obj;
    }
}
