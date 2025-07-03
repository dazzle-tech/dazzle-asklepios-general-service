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
public class ApGeneralAssessmentEntity implements Serializable {

	private String key;
	private String patientKey;
	private String encounterKey;
	private String positionStatusLkey;
	private ApLovValues positionStatusLvalue;
	private String bodyMovementsLkey;
	private ApLovValues bodyMovementsLvalue;
	private String levelOfConsciousnessLkey;
	private ApLovValues levelOfConsciousnessLvalue;
	private String facialExpressionLkey;
	private ApLovValues facialExpressionLvalue;
	private String speechLkey;
	private ApLovValues speechLvalue;
	private String moodBehaviorLkey;
	private ApLovValues moodBehaviorLvalue;
	private Boolean memoryRecent = false;
	private Boolean memoryRemote = false;
	private Boolean signsOfAgitation = false;
	private Boolean signsOfDepression = false;
	private Boolean signsOfSuicidalIdeation = false;
	private Boolean signsOfSubstanceUse = false;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private String cancellationReason;
	private String statusLkey;
	private ApLovValues statusLvalue;
	private ApGeneralAssessmentEntity translatedObject;

}