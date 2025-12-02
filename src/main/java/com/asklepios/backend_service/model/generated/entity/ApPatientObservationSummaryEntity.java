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
public class ApPatientObservationSummaryEntity implements Serializable {

	private String key;
	private String patientKey;
	private String visitKey;
	private Date lastDate = new Date();
	private BigDecimal latesttemperature;
	private BigDecimal latestbpSystolic;
	private BigDecimal latestbpDiastolic;
	private BigDecimal latestheartrate;
	private BigDecimal latestrespiratoryrate;
	private BigDecimal latestoxygensaturation;
	private BigDecimal latestglucoselevel;
	private String latestpainlevelLkey;
	private ApLovValues latestpainlevelLvalue;
	private BigDecimal latestweight;
	private BigDecimal latestheight;
	private BigDecimal latestheadcircumference;
	private BigDecimal latestlength;
	private BigDecimal latestbmi;
	private BigDecimal age;
	private String prevRecordKey;
	private Date plastDate = new Date();
	private BigDecimal platesttemperature;
	private BigDecimal platestbpSystolic;
	private BigDecimal platestbpDiastolic;
	private BigDecimal platestheartrate;
	private BigDecimal platestrespiratoryrate;
	private BigDecimal platestoxygensaturation;
	private BigDecimal platestglucoselevel;
	private String platestpainlevelLkey;
	private ApLovValues platestpainlevelLvalue;
	private BigDecimal platestweight;
	private BigDecimal platestheight;
	private BigDecimal platestheadcircumference;
	private BigDecimal platestlength;
	private BigDecimal platestbmi;
	private BigDecimal page;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private String latestnotes;
	private String platestnotes;
	private String latestpaindescription;
	private String platestpaindescription;
	private BigDecimal latestpainlevel;
	private BigDecimal platestpainlevel;
	private String platesthearingtest;
	private String latesthearingtest;
	private Boolean latestDehydration = false;
	private Boolean platestDehydration = false;
	private Boolean latestNasalFlaring = false;
	private Boolean platestNasalFlaring = false;
	private Boolean latestResponseToLight = false;
	private Boolean platestResponseToLight = false;
	private Boolean latestPupilResponse = false;
	private Boolean platestPupilResponse = false;
	private Boolean latestAbilityToFollowTarget = false;
	private Boolean platestAbilityToFollowTarget = false;
	private Boolean latestColorTesting = false;
	private Boolean platestColorTesting = false;
	private Boolean latestFallRisk = false;
	private Boolean platestFallRisk = false;
	private String latestFallRiskDetails;
	private String platestFallRiskDetails;
	private String latestActionToTake;
	private String platestActionToTake;
	private String latestFunctionalStatus;
	private String platestFunctionalStatus;
	private String latestCognitiveCheck;
	private String platestCognitiveCheck;
	private String reasonOfVisit;
	private ApPatientObservationSummaryEntity translatedObject;

}