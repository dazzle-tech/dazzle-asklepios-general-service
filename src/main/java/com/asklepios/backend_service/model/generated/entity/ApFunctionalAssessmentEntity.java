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
public class ApFunctionalAssessmentEntity implements Serializable {

	private String key;
	private String patientKey;
	private String encounterKey;
	private Boolean mobilityAmbulation = false;
	private Boolean transferringBedChair = false;
	private Boolean stairClimbingAbility = false;
	private Boolean feeding = false;
	private Boolean toiletingAbility = false;
	private Boolean bathingAbility = false;
	private Boolean dressingAbility = false;
	private Boolean groomingAbility = false;
	private Boolean walkingDistance = false;
	private Boolean balance = false;
	private Boolean urinaryContinence = false;
	private Boolean bowelContinence = false;
	private Boolean useOfAssistiveDevices = false;
	private Boolean needForAssistance = false;
	private Boolean fallHistory = false;
	private Boolean painDuringMovement = false;
	private Boolean needForRehab = false;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private String cancellationReason;
	private String statusLkey;
	private ApLovValues statusLvalue;
	private ApFunctionalAssessmentEntity translatedObject;

}