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
public class ApDentalPlannedTreatmentEntity implements Serializable {

	private String key;
	private String patientKey;
	private String encounterKey;
	private String type;
	private BigDecimal visitNumber;
	private String cdtKey;
	private String toothKey;
	private String note;
	private String surfaceLkey;
	private ApLovValues surfaceLvalue;
	private String billingTypeLkey;
	private ApLovValues billingTypeLvalue;
	private BigDecimal fees;
	private BigDecimal insurance;
	private BigDecimal discount;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private String statusLkey;
	private ApLovValues statusLvalue;
	private String source;
	private String sourceKey;
	private ApDentalPlannedTreatmentEntity translatedObject;

}