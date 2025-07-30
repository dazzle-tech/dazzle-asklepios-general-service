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
public class ApEmergencyTriageEntity implements Serializable {

	private String key;
	private String patientKey;
	private String encounterKey;
	private String emergencyLevelLkey;
	private ApLovValues emergencyLevelLvalue;
	private Boolean rightEyeLightResponse = false;
	private String rightEyePupilSizeLkey;
	private ApLovValues rightEyePupilSizeLvalue;
	private Boolean leftEyeLightResponse = false;
	private String leftEyePupilSizeLkey;
	private ApLovValues leftEyePupilSizeLvalue;
	private Boolean isPregnancy = false;
	private String historyOfPresentIllness;
	private String additionalNotes;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private String lifeSavingLkey;
	private ApLovValues lifeSavingLvalue;
	private String unresponsiveLkey;
	private ApLovValues unresponsiveLvalue;
	private String highRiskLkey;
	private ApLovValues highRiskLvalue;
	private String avpuScaleLkey;
	private ApLovValues avpuScaleLvalue;
	private String painScoreLkey;
	private ApLovValues painScoreLvalue;
	private String labsLkey;
	private ApLovValues labsLvalue;
	private String imagingLkey;
	private ApLovValues imagingLvalue;
	private String ivFluidsLkey;
	private ApLovValues ivFluidsLvalue;
	private String medicationLkey;
	private ApLovValues medicationLvalue;
	private String ecgLkey;
	private ApLovValues ecgLvalue;
	private String consultationLkey;
	private ApLovValues consultationLvalue;
	private String destinationLkey;
	private ApLovValues destinationLvalue;
	private ApEmergencyTriageEntity translatedObject;

}