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
public class ApPatientAllergiesEntity implements Serializable {

	private String key;
	private String patientKey;
	private String allergyKey;
	private String allergenTypeLkey;
	private ApLovValues allergenTypeLvalue;
	private String severityLkey;
	private ApLovValues severityLvalue;
	private String reaction;
	private Date dateDiagnosed = new Date();
	private String resolutionStatusLkey;
	private ApLovValues resolutionStatusLvalue;
	private Date dateResolved = new Date();
	private String treatmentPlan;
	private String notes;
	private String sourceOfInfoLkey;
	private ApLovValues sourceOfInfoLvalue;
	private Boolean lifeThreating = false;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private String addedByVisitKey;
	private ApPatientAllergiesEntity translatedObject;

}