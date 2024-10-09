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
public class ApPatientObservationEntity implements Serializable {

	private String key;
	private String patientKey;
	private String visitKey;
	private Date observationDate = new Date();
	private String observationTypeLkey;
	private ApLovValues observationTypeLvalue;
	private String value;
	private String value2;
	private String unitofMeasureLkey;
	private ApLovValues unitofMeasureLvalue;
	private String referencerangeLkey;
	private ApLovValues referencerangeLvalue;
	private String statusLkey;
	private ApLovValues statusLvalue;
	private String methodLkey;
	private ApLovValues methodLvalue;
	private String positionLkey;
	private ApLovValues positionLvalue;
	private String siteLkey;
	private ApLovValues siteLvalue;
	private String equipmentUsedLkey;
	private ApLovValues equipmentUsedLvalue;
	private String comments;
	private String sourceRecordKey;
	private String providerKey;
	private String providerName;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private ApPatientObservationEntity translatedObject;

}