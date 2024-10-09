package com.asklepios.backend_service.model.generated.entity;

import java.io.Serializable;
import lombok.extern.slf4j.Slf4j;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import com.asklepios.backend_service.model.generated.pojo.ApLovValues;

@Getter
@Setter
@Slf4j
public class ApDvmRuleEntity implements Serializable {

	private String key;
	private String screenMetadataKey;
	private String ruleDescription;
	private String fieldKey;
	private String fieldName;
	private String fieldDataType;
	private Boolean isFieldLov = false;
	private Boolean isFieldRef = false;
	private String ruleType;
	private String ruleValue;
	private String ruleValueTwo;
	private Boolean isDependant = false;
	private String dependantRuleCheck;
	private String dependantRuleKey;
	private String validationType;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private ApDvmRuleEntity translatedObject;

}