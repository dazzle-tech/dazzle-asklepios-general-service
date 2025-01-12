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
public class ApDrugOrderMedicationsEntity implements Serializable {

	private String key;
	private String patientKey;
	private String visitKey;
	private String drugOrderKey;
	private String genericMedicationsKey;
	private String drugOrderTypeLkey;
	private ApLovValues drugOrderTypeLvalue;
	private String doseUnitLkey;
	private ApLovValues doseUnitLvalue;
	private String roaLkey;
	private ApLovValues roaLvalue;
	private BigDecimal frequency;
	private String priorityLkey;
	private ApLovValues priorityLvalue;
	private String pharmacyDepartmentKey;
	private BigDecimal dose;
	private String notes;
	private String prnIndication;
	private String specialInstructions;
	private String parametersToMonitor;
	private BigDecimal startDateTime;
	private BigDecimal maximumDose;
	private Boolean genericSubstitute = false;
	private Boolean chronicMedication = false;
	private Boolean patientOwnMedication = false;
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
	private String cancellationReason;
	private String indicationUseLkey;
	private ApLovValues indicationUseLvalue;
	private String indicationIcd;
	private String indicationSnomed;
	private String indicationManually;
	private ApDrugOrderMedicationsEntity translatedObject;

}