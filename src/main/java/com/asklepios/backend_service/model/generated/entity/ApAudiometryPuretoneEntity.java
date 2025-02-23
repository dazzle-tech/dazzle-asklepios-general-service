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
public class ApAudiometryPuretoneEntity implements Serializable {

	private String key;
	private String patientKey;
	private String encounterKey;
	private String testEnvironment;
	private String testReason;
	private String earExamFindingsLkey;
	private ApLovValues earExamFindingsLvalue;
	private BigDecimal airConductionFrequenciesLeft;
	private BigDecimal airConductionFrequenciesRight;
	private BigDecimal hearingThresholdsLeft;
	private BigDecimal hearingThresholdsRight;
	private BigDecimal boneConductionFrequenciesLeft;
	private BigDecimal boneConductionFrequenciesRight;
	private BigDecimal boneConductionThresholdsLeft;
	private BigDecimal boneConductionThresholdsRight;
	private Boolean maskedUsed = false;
	private String hearingLossTypeLkey;
	private ApLovValues hearingLossTypeLvalue;
	private String hearingLossDegreeLkey;
	private ApLovValues hearingLossDegreeLvalue;
	private String recommendations;
	private String additionalNotes;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private String cancellationReason;
	private String statusLkey;
	private ApLovValues statusLvalue;
	private ApAudiometryPuretoneEntity translatedObject;

}