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
public class ApPreOperationChecklistEntity implements Serializable {

	private String key;
	private String encounterKey;
	private String patientKey;
	private String operationKey;
	private Boolean patientIdentityVerified = false;
	private Boolean consentSurgerySigned = false;
	private Boolean consentAnesthesiaSigned = false;
	private Boolean surgicalProcedureConfirmed = false;
	private Boolean siteOfSurgeryMarked = false;
	private Boolean npoStatusConfirmed = false;
	private Boolean preOpVitalsRecorded = false;
	private Boolean patientBathed = false;
	private Boolean jewelryRemoved = false;
	private Boolean denturesRemovedOrNoted = false;
	private Boolean prosthesisNotedOrRemoved = false;
	private Boolean clothingReplaced = false;
	private Boolean allergiesReviewed = false;
	private Boolean preOpMedsGiven = false;
	private Boolean chronicMedsManaged = false;
	private Boolean anticoagulantsManaged = false;
	private Boolean ivAccessSecured = false;
	private Boolean ivFluidsStarted = false;
	private Boolean bloodProductsPrepared = false;
	private Boolean emrUpdated = false;
	private Boolean labsImagingReviewed = false;
	private Boolean consentFormsAvailable = false;
	private Boolean personalBelongingsSecured = false;
	private Boolean interpreterArranged = false;
	private Boolean voidedOrCatheterPresent = false;
	private Boolean bedInLowestPosition = false;
	private Boolean transferModeArranged = false;
	private Boolean handoffToOrNursePrepared = false;
	private String createdBy;
	private BigDecimal createdAt;
	private String updatedBy;
	private BigDecimal updatedAt;
	private String deletedBy;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private ApPreOperationChecklistEntity translatedObject;

}