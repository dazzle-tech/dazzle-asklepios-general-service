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
public class ApOperationPostOpNotesHandoverEntity implements Serializable {

	private String key;
	private String operationRequestKey;
	private String indications;
	private String operativeFindings;
	private String operationPerformedSummary;
	private String variationsFromPlan;
	private String postOpDestinationKey;
	private Boolean oxygenRequired = false;
	private BigDecimal oxygenFlowRate;
	private String specialInstructions;
	private BigDecimal handoverTime;
	private Boolean verbalSummaryGiven = false;
	private String handoverNotes;
	private BigDecimal completedAt;
	private String recoveryConditionLkey;
	private ApLovValues recoveryConditionLvalue;
	private String surgeryStatusLkey;
	private ApLovValues surgeryStatusLvalue;
	private String createdBy;
	private BigDecimal createdAt;
	private String updatedBy;
	private BigDecimal updatedAt;
	private String deletedBy;
	private BigDecimal deletedAt;
	private Boolean isvalid = false;
	private ApOperationPostOpNotesHandoverEntity translatedObject;

}