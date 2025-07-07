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
public class ApPreOperativeTimeoutEntity implements Serializable {

	private String key;
	private String operationRequestKey;
	private Date timeoutStartTime = new Date();
	private String initiatedBy;
	private Boolean patientIdentityConfirmed = false;
	private Boolean surgicalSiteConfirmed = false;
	private Boolean procedureConfirmed = false;
	private Boolean consentFormPresent = false;
	private Boolean anesthesiaMachineChecked = false;
	private Boolean medicationPrepared = false;
	private Boolean allergyRiskReviewed = false;
	private Boolean difficultAirwayRisk = false;
	private Boolean asaClassification = false;
	private Boolean bloodLossExpected = false;
	private Boolean bloodUnitsAvailable = false;
	private Boolean equipmentAvailable = false;
	private Boolean imagingDisplayed = false;
	private Boolean instrumentCountPrepared = false;
	private Boolean teamIntroductionComplete = false;
	private String specialConcerns;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private String encounterKey;
	private String patientKey;
	private ApPreOperativeTimeoutEntity translatedObject;

}