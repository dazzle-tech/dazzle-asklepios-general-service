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
public class ApPatientProblemEntity implements Serializable {

	private String key;
	private String patientKey;
	private String problemCode;
	private String description;
	private String problemCodingLkey;
	private ApLovValues problemCodingLvalue;
	private Date dateDiagnosed = new Date();
	private String problemStatusLkey;
	private ApLovValues problemStatusLvalue;
	private String severityLkey;
	private ApLovValues severityLvalue;
	private Date onSetDate = new Date();
	private String providerTypeLkey;
	private ApLovValues providerTypeLvalue;
	private String providerLkey;
	private ApLovValues providerLvalue;
	private String providerUserName;
	private String providerRoleLkey;
	private ApLovValues providerRoleLvalue;
	private Date resolvedDate = new Date();
	private Date dateAdded = new Date();
	private String notes;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private ApPatientProblemEntity translatedObject;

}