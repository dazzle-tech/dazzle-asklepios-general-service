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
public class ApMedicationReconciliationEntity implements Serializable {

	private String key;
	private String patientKey;
	private String encounterKey;
	private String activeIngredientKey;
	private BigDecimal dosage;
	private String dosageLkey;
	private ApLovValues dosageLvalue;
	private String routeLkey;
	private ApLovValues routeLvalue;
	private String frequencyLkey;
	private ApLovValues frequencyLvalue;
	private BigDecimal startDate;
	private BigDecimal lastDoseTaken;
	private String indication;
	private String sourceOfInfo;
	private Boolean medicationAvailableWithPatient = false;
	private Boolean continueInHospital = false;
	private Boolean discrepancyIdentified = false;
	private String actionTaken;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private String statusLkey;
	private ApLovValues statusLvalue;
	private ApMedicationReconciliationEntity translatedObject;

}