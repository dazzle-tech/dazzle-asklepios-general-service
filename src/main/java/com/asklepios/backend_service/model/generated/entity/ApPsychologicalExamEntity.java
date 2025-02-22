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
public class ApPsychologicalExamEntity implements Serializable {

	private String key;
	private String patientKey;
	private String encounterKey;
	private String testTypeLkey;
	private ApLovValues testTypeLvalue;
	private String reason;
	private BigDecimal testDuration;
	private String unitLkey;
	private ApLovValues unitLvalue;
	private String scoreLkey;
	private ApLovValues scoreLvalue;
	private String resultInterpretationLkey;
	private ApLovValues resultInterpretationLvalue;
	private String clinicalObservations;
	private String treatmentPlan;
	private String additionalNotes;
	private Boolean requireFollowUp = false;
	private BigDecimal followUpDate;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private String cancellationReason;
	private String statusLkey;
	private ApLovValues statusLvalue;
	private ApPsychologicalExamEntity translatedObject;

}