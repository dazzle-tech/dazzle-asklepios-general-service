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
public class ApVisitWarningEntity implements Serializable {

	private String key;
	private String patientKey;
	private String visitKey;
	private String statusLkey;
	private ApLovValues statusLvalue;
	private String warningTypeLkey;
	private ApLovValues warningTypeLvalue;
	private BigDecimal firstTimeRecorded;
	private String actionTake;
	private String sourceOfInformationLkey;
	private ApLovValues sourceOfInformationLvalue;
	private String notes;
	private String cancellationReason;
	private String resolvedBy;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal resolvedAt;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private String severityLkey;
	private ApLovValues severityLvalue;
	private String warning;
	private ApVisitWarningEntity translatedObject;

}