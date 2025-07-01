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
public class ApPainAssessmentEntity implements Serializable {

	private String key;
	private String patientKey;
	private String encounterKey;
	private String painDegreeLkey;
	private ApLovValues painDegreeLvalue;
	private String painLocationLkey;
	private ApLovValues painLocationLvalue;
	private String painPatternLkey;
	private ApLovValues painPatternLvalue;
	private String onsetLkey;
	private ApLovValues onsetLvalue;
	private String painScoreLkey;
	private ApLovValues painScoreLvalue;
	private BigDecimal duration;
	private String durationUnitLkey;
	private ApLovValues durationUnitLvalue;
	private String aggravatingFactors;
	private String relievingFactors;
	private String associatedSymptoms;
	private String painManagementGiven;
	private Boolean impactOnFunction = false;
	private Boolean painReassessmentRequired = false;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private String statusLkey;
	private ApLovValues statusLvalue;
	private String cancellationReason;
	private ApPainAssessmentEntity translatedObject;

}