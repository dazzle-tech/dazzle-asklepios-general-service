package com.asklepios.backend_service.service;


import com.asklepios.backend_service.model.generated.entity.ApScreenMetadataEntity;
import com.asklepios.backend_service.model.generated.pojo.ApDvmRule;
import com.asklepios.backend_service.model.pojo.ValidationResult;
import com.asklepios.backend_service.model.pojo.ValidationResultDetail;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidationService {

    private final ApScreenMetadataService apScreenMetadataService;
    private final ApDvmRuleService apDvmRuleService;

    public ValidationService(ApScreenMetadataService apScreenMetadataService, ApDvmRuleService apDvmRuleService) {
        this.apScreenMetadataService = apScreenMetadataService;
        this.apDvmRuleService = apDvmRuleService;
    }

    public ValidationResult validateRecord(String screenKey, String metadataKey, Class recordType, Object record) throws Exception {
        List<String> validationTypes = new ArrayList<>();
        ValidationResult result = new ValidationResult();

        List<ApDvmRule> directRulesList = apDvmRuleService.getList("screen_metadata_key in " +
                "(select key from ap_screen_metadata where screen_key = '" + screenKey + "' and metadata_key = '" + metadataKey + "')" +
                " and is_dependant <> true");
        Map<String, List<ApDvmRule>> directRules = new HashMap<>();
        for (ApDvmRule rule : directRulesList) {
            if (!directRules.containsKey(rule.getFieldName())) {
                directRules.put(rule.getFieldName(), new ArrayList<>());
            }
            directRules.get(rule.getFieldName()).add(rule);
        }

        //validation logic
        Field[] fields = recordType.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            String dbFieldName = fromCamelCaseToDBName(fieldName);

            field.setAccessible(true);
            List<ApDvmRule> fieldRules = directRules.get(dbFieldName);
            if (fieldRules != null)
                for (ApDvmRule rule : fieldRules) {
                    if (rule != null) {
                        // field has a validation rule
                        try {
                            Object fieldValue = field.get(record);
                            if (rule.getRuleType().equals("REQUIRED") && (fieldValue == null || fieldValue.toString().isBlank())) {
                                // required field failed
                                String message = "Required Field";
                                ValidationResultDetail vrd = new ValidationResultDetail(rule.getValidationType(), rule.getRuleType(), message);
                                if (!result.getDetails().containsKey(dbFieldName)) {
                                    result.getDetails().put(dbFieldName, new ArrayList<>());
                                }
                                result.getDetails().get(dbFieldName).add(vrd);
                                validationTypes.add(rule.getValidationType());
                            }

                            if (rule.getRuleType().equals("REGEX") && fieldValue != null && rule.getRuleValue() != null) {
                                // match a regex expression
                                if (!matches(rule.getRuleValue(), fieldValue.toString())) {
                                    String message = "Wrong Format";
                                    ValidationResultDetail vrd = new ValidationResultDetail(rule.getValidationType(), rule.getRuleType(), message);
                                    if (!result.getDetails().containsKey(dbFieldName)) {
                                        result.getDetails().put(dbFieldName, new ArrayList<>());
                                    }
                                    result.getDetails().get(dbFieldName).add(vrd);
                                    validationTypes.add(rule.getValidationType());
                                }
                            }

                            if (rule.getRuleType().equals("MAX_LENGTH") && fieldValue != null && rule.getRuleValue() != null) {
                                // maximum string length validation
                                Integer ruleMaxLengthInteger = Integer.parseInt(rule.getRuleValue());
                                if (fieldValue.toString().length() > ruleMaxLengthInteger) {
                                    String message = "Max " + ruleMaxLengthInteger + " Characters";
                                    ValidationResultDetail vrd = new ValidationResultDetail(rule.getValidationType(), rule.getRuleType(), message);
                                    if (!result.getDetails().containsKey(dbFieldName)) {
                                        result.getDetails().put(dbFieldName, new ArrayList<>());
                                    }
                                    result.getDetails().get(dbFieldName).add(vrd);
                                    validationTypes.add(rule.getValidationType());
                                }
                            }

                            if (rule.getRuleType().equals("MIN_LENGTH") && fieldValue != null && rule.getRuleValue() != null) {
                                // minimum string length validation
                                Integer ruleMinLengthInteger = Integer.parseInt(rule.getRuleValue());
                                if (fieldValue.toString().length() < ruleMinLengthInteger) {
                                    String message = "Minimum " + ruleMinLengthInteger + " Characters";
                                    ValidationResultDetail vrd = new ValidationResultDetail(rule.getValidationType(), rule.getRuleType(), message);
                                    if (!result.getDetails().containsKey(dbFieldName)) {
                                        result.getDetails().put(dbFieldName, new ArrayList<>());
                                    }
                                    result.getDetails().get(dbFieldName).add(vrd);
                                    validationTypes.add(rule.getValidationType());
                                }
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        }

        result.setType(evaluateTypePrecedence(validationTypes));
        return result;
    }

    private String evaluateTypePrecedence(List<String> types) {
        String priority = null;

        for (String type : types) {
            if (type.equals("REJECT")) {
                priority = "ERROR";
                break;
            } else if (type.equals("WARN")) {
                priority = "WARN";
            } else if (priority == null && type.equals("CHECK")) {
                priority = "CHECK";
            }
        }
        return priority;
    }

    private String fromCamelCaseToDBName(String word) {
        StringBuilder finalString = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            char charAt = word.charAt(i);
            if (Character.isUpperCase(charAt)) {
                finalString.append('_').append(Character.toLowerCase(charAt));
            } else {
                finalString.append(charAt);
            }
        }
        return finalString.toString();
    }

    private boolean matches(String regex, String input) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
}
