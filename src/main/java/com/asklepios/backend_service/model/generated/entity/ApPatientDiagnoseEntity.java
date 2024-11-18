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
public class ApPatientDiagnoseEntity implements Serializable {

	private String key;
	private String patientKey;
	private String visitKey;
	private String diagnoseCode;
	private String description;
	private String diagnoseCodingLkey;
	private ApLovValues diagnoseCodingLvalue;
	private Date dateDiagnosed = new Date();
	private Date onsetDate = new Date();
	private String diagnoseStatusLkey;
	private ApLovValues diagnoseStatusLvalue;
	private String diagnoseTypeLkey;
	private ApLovValues diagnoseTypeLvalue;
	private String diagnoseSiteLkey;
	private ApLovValues diagnoseSiteLvalue;
	private String providerTypeLkey;
	private ApLovValues providerTypeLvalue;
	private String providerLkey;
	private ApLovValues providerLvalue;
	private String providerUserName;
	private String providerRoleLkey;
	private ApLovValues providerRoleLvalue;
	private String notes;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private Boolean isMajor = false;
	private Boolean isSuspected = false;
	private ApPatientDiagnoseEntity translatedObject;

}