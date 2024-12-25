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
public class ApDiagnosticTestLaboratoryEntity implements Serializable {

	private String key;
	private String testKey;
	private String internationalCodingTypeLkey;
	private ApLovValues internationalCodingTypeLvalue;
	private String childCodeLkey;
	private ApLovValues childCodeLvalue;
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
	private BigDecimal testDurationTime;
	private String timeUnitLkey;
	private ApLovValues timeUnitLvalue;
	private String resultType;
	private String resultUnitLkey;
	private ApLovValues resultUnitLvalue;
	private Boolean isProfile = false;
	private String sampleContainerLkey;
	private ApLovValues sampleContainerLvalue;
	private BigDecimal sampleVolume;
	private String sampleVolumeUnitLkey;
	private ApLovValues sampleVolumeUnitLvalue;
	private String tubeColorLkey;
	private ApLovValues tubeColorLvalue;
	private String testDescription;
	private String sampleHandling;
	private BigDecimal turnaroundTime;
	private String turnaroundTimeUnitLkey;
	private ApLovValues turnaroundTimeUnitLvalue;
	private String preparationRequirements;
	private String medicalIndications;
	private String associatedRisks;
	private String testInstructions;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private String labCategoryLkey;
	private ApLovValues labCategoryLvalue;
	private ApDiagnosticTestLaboratoryEntity translatedObject;

}