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
public class ApPrescriptionMedicationsEntity implements Serializable {

	private String key;
	private String patientKey;
	private String visitKey;
	private String prescriptionKey;
	private String genericMedicationsKey;
	private BigDecimal numberOfRefills;
	private String refillInterval;
	private String instructionsTypeLkey;
	private ApLovValues instructionsTypeLvalue;
	private String instructions;
	private String notes;
	private String parametersToMonitor;
	private Date validUtil = new Date();
	private BigDecimal maximumDose;
	private Boolean genericSubstitute = false;
	private Boolean chronicMedication = false;
	private String administrationInstructions;
	private BigDecimal duration;
	private String durationTypeLkey;
	private ApLovValues durationTypeLvalue;
	private String statusLkey;
	private ApLovValues statusLvalue;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private BigDecimal refillIntervalValue;
	private String refillIntervalUnitLkey;
	private ApLovValues refillIntervalUnitLvalue;
	private String indicationManually;
	private String indicationUseLkey;
	private ApLovValues indicationUseLvalue;
	private String indicationIcd;
	private ApPrescriptionMedicationsEntity translatedObject;

}