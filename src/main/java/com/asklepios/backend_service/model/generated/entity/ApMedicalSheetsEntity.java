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
public class ApMedicalSheetsEntity implements Serializable {

	private String key;
	private String departmentKey;
	private String facilityKey;
	private Boolean patientDashboard = false;
	private Boolean clinicalVisit = false;
	private Boolean diagnosticsOrder = false;
	private Boolean prescription = false;
	private Boolean drugOrder = false;
	private Boolean consultation = false;
	private Boolean procedures = false;
	private Boolean patientHistory = false;
	private Boolean allergies = false;
	private Boolean medicalWarnings = false;
	private Boolean medicationsRecord = false;
	private Boolean psychologicalExam = false;
	private Boolean audiometryPuretone = false;
	private Boolean optometricExam = false;
	private Boolean vaccineReccord = false;
	private Boolean diagnosticsResult = false;
	private Boolean dentalCare = false;
	private Boolean cardiology = false;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private ApMedicalSheetsEntity translatedObject;

}