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
public class ApTreadmillStressEntity implements Serializable {

	private String key;
	private String patientKey;
	private String encounterKey;
	private String indication;
	private BigDecimal preTestSystolicBp;
	private BigDecimal preTestDiastolicBp;
	private String baselineEcgFindingsLkey;
	private ApLovValues baselineEcgFindingsLvalue;
	private String bruceProtocolStageLkey;
	private ApLovValues bruceProtocolStageLvalue;
	private BigDecimal exerciseDuration;
	private BigDecimal maximumHeartRateAchieved;
	private BigDecimal targetHeartRate;
	private String segmentChangeLkey;
	private ApLovValues segmentChangeLvalue;
	private Boolean arrhythmiaNoted = false;
	private String typeLkey;
	private ApLovValues typeLvalue;
	private String testOutcomeLkey;
	private ApLovValues testOutcomeLvalue;
	private BigDecimal postTestSystolicBp;
	private BigDecimal postTestDiastolicBp;
	private BigDecimal recoveryTime;
	private String createdBy;
	private String cardiologistNotes;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private String cancellationReason;
	private String statusLkey;
	private ApLovValues statusLvalue;
	private ApTreadmillStressEntity translatedObject;

}