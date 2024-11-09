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
public class ApDiagnosticTestRadiologyEntity implements Serializable {

	private String key;
	private String testKey;
	private String internationalCodingTypeLkey;
	private ApLovValues internationalCodingTypeLvalue;
	private String childCodeLkey;
	private ApLovValues childCodeLvalue;
	private String radCategoryLkey;
	private ApLovValues radCategoryLvalue;
	private String imageDuration;
	private String timeUnitLkey;
	private ApLovValues timeUnitLvalue;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private String labCatalogLkey;
	private ApLovValues labCatalogLvalue;
	private String propertyLkey;
	private ApLovValues propertyLvalue;
	private String systemLkey;
	private ApLovValues systemLvalue;
	private String scaleLkey;
	private ApLovValues scaleLvalue;
	private String reagentsLkey;
	private ApLovValues reagentsLvalue;
	private String methodLkey;
	private ApLovValues methodLvalue;
	private String timingLkey;
	private ApLovValues timingLvalue;
	private String resultType;
	private String resultUnitLkey;
	private ApLovValues resultUnitLvalue;
	private ApDiagnosticTestRadiologyEntity translatedObject;

}