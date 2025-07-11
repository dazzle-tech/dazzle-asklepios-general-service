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
public class ApRepositioningEntity implements Serializable {

	private String key;
	private String patientKey;
	private String encounterKey;
	private String newPositionLkey;
	private ApLovValues newPositionLvalue;
	private Boolean positionChangeSuccessful = false;
	private String notes;
	private BigDecimal expectedNextRepositioning;
	private String timeUnitLkey;
	private ApLovValues timeUnitLvalue;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private String statusLkey;
	private ApLovValues statusLvalue;
	private String cancellationReason;
	private ApRepositioningEntity translatedObject;

}