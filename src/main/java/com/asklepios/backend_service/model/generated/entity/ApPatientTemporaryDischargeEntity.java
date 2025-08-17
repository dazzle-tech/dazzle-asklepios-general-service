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
public class ApPatientTemporaryDischargeEntity implements Serializable {

	private String key;
	private String encounterKey;
	private String patientKey;
	private String reasonForTemporaryDischarge;
	private String typeLkey;
	private ApLovValues typeLvalue;
	private BigDecimal expectedReturnAt;
	private Boolean consentTaken = false;
	private String billingApprovalStatusLkey;
	private ApLovValues billingApprovalStatusLvalue;
	private BigDecimal returnAt;
	private Boolean bedRetained = false;
	private String comments;
	private String roomKey;
	private String bedKey;
	private String notes;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private String fromRoom;
	private String fromBed;
	private ApPatientTemporaryDischargeEntity translatedObject;

}