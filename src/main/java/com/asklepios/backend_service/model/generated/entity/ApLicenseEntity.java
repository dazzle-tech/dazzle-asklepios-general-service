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
public class ApLicenseEntity implements Serializable {

	private String key;
	private String tenantId;
	private String licenseId;
	private String licenseKey;
	private String licenseType;
	private Date startDate = new Date();
	private Date endDate = new Date();
	private Boolean activestatus = false;
	private String uuidHwKeys;
	private String facilityAddress;
	private String facilityLogoFile;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private ApLicenseEntity translatedObject;

}