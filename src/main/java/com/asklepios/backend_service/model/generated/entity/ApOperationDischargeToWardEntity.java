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
public class ApOperationDischargeToWardEntity implements Serializable {

	private String key;
	private String operationRequestKey;
	private Boolean returnToDifferentWard = false;
	private String designationLkey;
	private ApLovValues designationLvalue;
	private BigDecimal transferTime;
	private String receivingNurseKey;
	private String finalNotes;
	private Boolean patientIdBandRechecked = false;
	private String transportModeLkey;
	private ApLovValues transportModeLvalue;
	private String createdBy;
	private BigDecimal createdAt;
	private String updatedBy;
	private BigDecimal updatedAt;
	private String deletedBy;
	private BigDecimal deletedAt;
	private Boolean isvalid = false;
	private ApOperationDischargeToWardEntity translatedObject;

}