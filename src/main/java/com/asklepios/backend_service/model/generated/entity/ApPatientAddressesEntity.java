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
public class ApPatientAddressesEntity implements Serializable {

	private String key;
	private String patientKey;
	private String addressTypeLkey;
	private ApLovValues addressTypeLvalue;
	private String streetAddressLine1;
	private String streetAddressLine2;
	private String countryLkey;
	private ApLovValues countryLvalue;
	private String stateProvinceRegionLkey;
	private ApLovValues stateProvinceRegionLvalue;
	private String cityLkey;
	private ApLovValues cityLvalue;
	private String postalCode;
	private String additionalInfo;
	private BigDecimal latitude;
	private String longitude;
	private String isActive;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private ApPatientAddressesEntity translatedObject;

}