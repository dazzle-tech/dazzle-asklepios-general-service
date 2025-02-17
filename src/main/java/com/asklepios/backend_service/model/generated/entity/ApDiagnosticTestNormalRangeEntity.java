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
public class ApDiagnosticTestNormalRangeEntity implements Serializable {

	private String key;
	private String testKey;
	private String genderLkey;
	private ApLovValues genderLvalue;
	private BigDecimal ageFrom;
	private String ageFromUnitLkey;
	private ApLovValues ageFromUnitLvalue;
	private BigDecimal ageTo;
	private String ageToUnitLkey;
	private ApLovValues ageToUnitLvalue;
	private String conditionLkey;
	private ApLovValues conditionLvalue;
	private String resultTypeLkey;
	private ApLovValues resultTypeLvalue;
	private String resultText;
	private String resultLovKey;
	private String normalRangeTypeLkey;
	private ApLovValues normalRangeTypeLvalue;
	private BigDecimal rangeFrom;
	private BigDecimal rangeTo;
	private String scaleLkey;
	private ApLovValues scaleLvalue;
	private String reagentsLkey;
	private ApLovValues reagentsLvalue;
	private Boolean criticalValue = false;
	private BigDecimal criticalValueLessThan;
	private BigDecimal criticalValueMoreThan;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private ApDiagnosticTestNormalRangeEntity translatedObject;

}