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
public class ApVaccineEntity implements Serializable {

	private String key;
	private String vaccineCode;
	private String vaccineName;
	private String atcCode;
	private String typeLkey;
	private ApLovValues typeLvalue;
	private String roaLkey;
	private ApLovValues roaLvalue;
	private String siteOfAdministration;
	private String postOpeningDuration;
	private String durationUnitLkey;
	private ApLovValues durationUnitLvalue;
	private String indications;
	private String possibleReactions;
	private String contraindicationsAndPrecautions;
	private String storageAndHandling;
	private Boolean isValid = true;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private ApVaccineEntity translatedObject;

}