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
public class ApEncounterVaccinationEntity implements Serializable {

	private String key;
	private String patientKey;
	private String encounterKey;
	private BigDecimal vaccineId;
	private BigDecimal vaccineBrandId;
	private BigDecimal vaccineDoseId;
	private String vaccineLotNumber;
	private BigDecimal dateAdministered;
	private String actualSide;
	private String administrationReactions;
	private String externalFacilityName;
	private String notes;
	private String reviewedBy;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal reviewedAt;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private String cancellationReason;
	private String statusLkey;
	private ApLovValues statusLvalue;
	private ApEncounterVaccinationEntity translatedObject;

}