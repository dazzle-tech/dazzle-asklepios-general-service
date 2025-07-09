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
public class ApOperationIntraoperativeEventsEntity implements Serializable {

	private String key;
	private String operationRequestKey;
	private String operationNotes;
	private Boolean conversionOccurred = false;
	private Boolean conversionType = false;
	private String conversionTypeNote;
	private String incisionType;
	private BigDecimal estimatedBloodLossMl;
	private String surgicalComplicationLkey;
	private ApLovValues surgicalComplicationLvalue;
	private String surgicalComplicationNotes;
	private String specimensTaken;
	private Boolean safetyPauseTaken = false;
	private BigDecimal firstCountTime;
	private String firstCountByKey;
	private BigDecimal secondCountTime;
	private String secondCountByKey;
	private Boolean finalCountVerified = false;
	private Boolean countDiscrepancy = false;
	private String countDiscrepancyAction;
	private Boolean unexpectedEventOccurred = false;
	private String eventDescription;
	private String teamResponse;
	private String eventOutcome;
	private String complicationSeverityLkey;
	private ApLovValues complicationSeverityLvalue;
	private BigDecimal skinClosureTime;
	private BigDecimal surgeryEndTime;
	private String createdBy;
	private BigDecimal createdAt;
	private String updatedBy;
	private BigDecimal updatedAt;
	private String deletedBy;
	private BigDecimal deletedAt;
	private Boolean isvalid = false;
	private BigDecimal urineOutput;
	private String actualOperationPerformed;
	private ApOperationIntraoperativeEventsEntity translatedObject;

}