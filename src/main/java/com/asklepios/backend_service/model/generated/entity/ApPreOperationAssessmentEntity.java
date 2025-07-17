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
public class ApPreOperationAssessmentEntity implements Serializable {

	private String key;
	private String patientKey;
	private String encounterKey;
	private Boolean nopStatusConfirmed = false;
	private String painScoreLkey;
	private ApLovValues painScoreLvalue;
	private Boolean allergiesReviewed = false;
	private Boolean consentForProcedureSigned = false;
	private Boolean consentForAnesthesiaSigned = false;
	private Boolean ivAccessStatus = false;
	private Boolean siteMarkedBySurgeon = false;
	private Boolean labImagingReviewed = false;
	private Boolean anesthetistAssessmentDone = false;
	private String asaClassificationLkey;
	private ApLovValues asaClassificationLvalue;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private ApPreOperationAssessmentEntity translatedObject;

}