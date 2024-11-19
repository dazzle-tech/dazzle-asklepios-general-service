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
public class ApAppointmentEntity implements Serializable {

	private String key;
	private String patientKey;
	private String facilityKey;
	private String resourceTypeLkey;
	private ApLovValues resourceTypeLvalue;
	private String resourceLkey;
	private ApLovValues resourceLvalue;
	private String appointmentStart;
	private String instructionsLkey;
	private ApLovValues instructionsLvalue;
	private String notes;
	private String priorityLkey;
	private ApLovValues priorityLvalue;
	private String isReminder;
	private String reminderLkey;
	private ApLovValues reminderLvalue;
	private String consentForm;
	private String referingPhysicianLkey;
	private ApLovValues referingPhysicianLvalue;
	private String externalPhysician;
	private String pricedureLevelLkey;
	private ApLovValues pricedureLevelLvalue;
	private String visitTypeLkey;
	private ApLovValues visitTypeLvalue;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private ApAppointmentEntity translatedObject;

}