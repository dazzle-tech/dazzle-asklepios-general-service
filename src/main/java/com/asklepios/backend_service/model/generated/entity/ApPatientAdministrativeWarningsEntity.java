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
public class ApPatientAdministrativeWarningsEntity implements Serializable {

	private String key;
	private String patientKey;
	private String warningTypeLkey;
	private ApLovValues warningTypeLvalue;
	private String description;
	private String resolutionStatusLkey;
	private ApLovValues resolutionStatusLvalue;
	private Date dateResolved = new Date();
	private String resolvedBy;
	private Date resolutionUndoDate = new Date();
	private String resolvedUndoBy;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private ApPatientAdministrativeWarningsEntity translatedObject;

}