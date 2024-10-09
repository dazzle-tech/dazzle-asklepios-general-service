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
public class ApDiagnosticTestEntity implements Serializable {

	private String key;
	private String testTypeLkey;
	private ApLovValues testTypeLvalue;
	private String testName;
	private String internalCode;
	private String internationalCodeOne;
	private String internationalCodeTwo;
	private String internationalCodeThree;
	private Boolean ageSpecific = false;
	private Boolean genderSpecific = false;
	private String genderLkey;
	private ApLovValues genderLvalue;
	private Boolean specialPopulation = false;
	private BigDecimal price;
	private String currencyLkey;
	private ApLovValues currencyLvalue;
	private String specialNotes;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private ApDiagnosticTestEntity translatedObject;

}