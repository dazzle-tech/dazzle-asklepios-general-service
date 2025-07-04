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
public class ApOperationAnesthesiaCarePlanEntity implements Serializable {

	private String key;
	private String encounterKey;
	private String patientKey;
	private Boolean anesthesiaConsentSigned = false;
	private Boolean understandsRisks = false;
	private Boolean previousAnesthesia = false;
	private String anesthesiaHistory;
	private String operationKey;
	private String mallampatiClassificationLkey;
	private ApLovValues mallampatiClassificationLvalue;
	private String airwayGradesLkey;
	private ApLovValues airwayGradesLvalue;
	private String plannedAirwayApproachLkey;
	private ApLovValues plannedAirwayApproachLvalue;
	private String nasalPatencyLkey;
	private ApLovValues nasalPatencyLvalue;
	private BigDecimal thyromentalDistance;
	private BigDecimal mouthOpening;
	private String neckMobility;
	private String facialOrNeckAbnormalities;
	private Boolean beardOrFacialHair = false;
	private Boolean anticipatedDifficultAirway = false;
	private Boolean previousDifficultIntubation = false;
	private String difficultIntubationNotes;
	private String createdBy;
	private BigDecimal createdAt;
	private String updatedBy;
	private BigDecimal updatedAt;
	private String deletedBy;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private ApOperationAnesthesiaCarePlanEntity translatedObject;

}