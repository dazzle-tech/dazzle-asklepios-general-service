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
public class ApLicenseDetailsEntity implements Serializable {

	private String key;
	private String tenantId;
	private String licenseId;
	private BigDecimal facilityCount;
	private BigDecimal bedCount;
	private BigDecimal userCount;
	private String modules;
	private BigDecimal facilityUsageCount;
	private BigDecimal bedUsageCount;
	private BigDecimal userUsageCount;
	private Date lastUsageDate = new Date();
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private ApLicenseDetailsEntity translatedObject;

}