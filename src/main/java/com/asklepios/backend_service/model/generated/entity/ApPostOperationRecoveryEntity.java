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
public class ApPostOperationRecoveryEntity implements Serializable {

	private String key;
	private String encounterKey;
	private String patientKey;
	private BigDecimal arrivalTime;
	private String painScoreLkey;
	private ApLovValues painScoreLvalue;
	private Boolean nausea = false;
	private Boolean vomiting = false;
	private String recoveryStatus;
	private String nursingNotes;
	private String activityLkey;
	private ApLovValues activityLvalue;
	private String respirationLkey;
	private ApLovValues respirationLvalue;
	private String circulationLkey;
	private ApLovValues circulationLvalue;
	private String consciousnessLkey;
	private ApLovValues consciousnessLvalue;
	private String oxygenSaturationLkey;
	private ApLovValues oxygenSaturationLvalue;
	private BigDecimal aldreteScore;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isvalid = false;
	private ApPostOperationRecoveryEntity translatedObject;

}