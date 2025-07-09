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
public class ApDoctorRoundEntity implements Serializable {

	private String key;
	private String patientKey;
	private String encounterKey;
	private BigDecimal roundStartTime;
	private String practitionerKey;
	private String shiftLkey;
	private ApLovValues shiftLvalue;
	private String initialNote;
	private String progressNote;
	private String specialEventNote;
	private String primaryDiagnosis;
	private Boolean major = false;
	private Boolean suspected = false;
	private String clinicalImpression;
	private String secondaryDiagnoses;
	private String patientStatusLkey;
	private ApLovValues patientStatusLvalue;
	private String complicationsNoted;
	private String summaryStatement;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private String statusLkey;
	private ApLovValues statusLvalue;
	private ApDoctorRoundEntity translatedObject;

}