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
public class ApConsultationOrderEntity implements Serializable {

	private String key;
	private String patientKey;
	private String visitKey;
	private String consultantSpecialtyLkey;
	private ApLovValues consultantSpecialtyLvalue;
	private String cityLkey;
	private ApLovValues cityLvalue;
	private String preferredConsultantKey;
	private String consultationMethodLkey;
	private ApLovValues consultationMethodLvalue;
	private String consultationTypeLkey;
	private ApLovValues consultationTypeLvalue;
	private String consultationContent;
	private String notes;
	private String statusLkey;
	private ApLovValues statusLvalue;
	private String viewResponse;
	private String resposeStatusLkey;
	private ApLovValues resposeStatusLvalue;
	private BigDecimal submissionDate;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private String cancellationReason;
	private ApConsultationOrderEntity translatedObject;
	private String facilityKey;
	private String departmentKey;
	private String priorityLkey;

}