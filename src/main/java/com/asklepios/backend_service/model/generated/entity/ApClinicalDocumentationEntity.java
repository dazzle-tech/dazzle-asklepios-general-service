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
public class ApClinicalDocumentationEntity implements Serializable {

	private String key;
	private String patientKey;
	private String encounterKey;
	private String docTypeLkey;
	private ApLovValues docTypeLvalue;
	private String docCategoryLkey;
	private ApLovValues docCategoryLvalue;
	private String docContent;
	private String userKey;
	private String userRoleLkey;
	private ApLovValues userRoleLvalue;
	private String facilityKey;
	private String docStatusLkey;
	private ApLovValues docStatusLvalue;
	private String docFormTypeKey;
	private String docFormKey;
	private Date createdDatetime = new Date();
	private Date approvedDatetime = new Date();
	private String approvedByUserKey;
	private String approvedByUserRoleLkey;
	private ApLovValues approvedByUserRoleLvalue;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private ApClinicalDocumentationEntity translatedObject;

}