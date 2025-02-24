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
public class ApOptometricExamEntity implements Serializable {

	private String key;
	private String patientKey;
	private String encounterKey;
	private String medicalHistoryLkey;
	private ApLovValues medicalHistoryLvalue;
	private String testReason;
	private String performedWithLkey;
	private ApLovValues performedWithLvalue;
	private BigDecimal distanceAcuity;
	private BigDecimal rightEyeOd;
	private BigDecimal leftEyeOd;
	private BigDecimal rightEyeOs;
	private BigDecimal leftEyeOs;
	private BigDecimal nearAcuity;
	private String pinholeTestResultLkey;
	private ApLovValues pinholeTestResultLvalue;
	private BigDecimal numberOfPlatesTested;
	private BigDecimal correctAnswersCount;
	private String deficiencyTypeLkey;
	private ApLovValues deficiencyTypeLvalue;
	private BigDecimal rightEyeSphere;
	private BigDecimal leftEyeSphere;
	private BigDecimal rightCylinder;
	private BigDecimal leftCylinder;
	private BigDecimal rightAxis;
	private BigDecimal leftAxis;
	private BigDecimal rightEye;
	private BigDecimal leftEye;
	private String measurementMethod;
	private BigDecimal timeOfMeasurement;
	private BigDecimal cornealThickness;
	private String glaucomaRiskAssessmentLkey;
	private ApLovValues glaucomaRiskAssessmentLvalue;
	private Boolean fundoscopySlitlampDone = false;
	private String examFindings;
	private String visionDiagnosis;
	private String colorVisionDiagnosis;
	private String recommendations;
	private String additionalNotes;
	private Boolean followUpRequired = false;
	private BigDecimal followUpDate;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private String cancellationReason;
	private String statusLkey;
	private ApLovValues statusLvalue;
	private ApOptometricExamEntity translatedObject;

}