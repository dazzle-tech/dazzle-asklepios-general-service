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
public class ApPatientSocialHistoryEntity implements Serializable {

	private String key;
	private String patientKey;
	private Boolean currentSmoker = false;
	private BigDecimal smokeStartDate;
	private BigDecimal cigaretteAmount;
	private String cigaretteType;
	private Boolean previousSmoker = false;
	private BigDecimal smokeQuitDate;
	private Boolean exposureToSecondHandSmoke = false;
	private Boolean alcoholConsumption = false;
	private String typeOfAlcohol;
	private BigDecimal alcoholSinceWhen;
	private Boolean substanceUse = false;
	private String routeLkey;
	private ApLovValues routeLvalue;
	private String frequencyLkey;
	private ApLovValues frequencyLvalue;
	private String physicalLimitationLkey;
	private ApLovValues physicalLimitationLvalue;
	private String diagnosedEatingDisordersLkey;
	private ApLovValues diagnosedEatingDisordersLvalue;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private ApPatientSocialHistoryEntity translatedObject;

}