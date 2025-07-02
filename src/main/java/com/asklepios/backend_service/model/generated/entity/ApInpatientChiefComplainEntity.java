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
public class ApInpatientChiefComplainEntity implements Serializable {

	private String key;
	private String patientKey;
	private String encounterKey;
	private String chiefComplaint;
	private String provocation;
	private String palliation;
	private String qualityLkey;
	private ApLovValues qualityLvalue;
	private String regionLkey;
	private ApLovValues regionLvalue;
	private BigDecimal onsetDateTime;
	private String understanding;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private String cancellationReason;
	private String statusLkey;
	private ApLovValues statusLvalue;
	private String severityLkey;
	private ApLovValues severityLvalue;
	private ApInpatientChiefComplainEntity translatedObject;

}