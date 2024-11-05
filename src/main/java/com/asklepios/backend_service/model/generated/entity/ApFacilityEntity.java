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
public class ApFacilityEntity implements Serializable {

	private String key;
	private String facilityId;
	private String facilityName;
	private String facilityNameOtherLang;
	private String tenantId;
	private Date facilityRegistrationDate = new Date();
	private String facilityEmailAddress;
	private String facilityBriefDesc;
	private String facilityAddressOtherLang;
	private String facilityLogoFile;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private String facilityPhone1;
	private String facilityPhone2;
	private String facilityFax;
	private String facilityAddressId;
	private String facilityTypeLkey;
	private ApLovValues facilityTypeLvalue;
	private String facilityType;
	private String facilityAddress;
	private ApFacilityEntity translatedObject;

}