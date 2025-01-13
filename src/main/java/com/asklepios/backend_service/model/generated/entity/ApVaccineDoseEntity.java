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
public class ApVaccineDoseEntity implements Serializable {

	private String key;
	private BigDecimal fromAge;
	private BigDecimal toAge;
	private String fromAgeUnitLkey;
	private ApLovValues fromAgeUnitLvalue;
	private String toAgeUnitLkey;
	private ApLovValues toAgeUnitLvalue;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private String vaccineKey;
	private String doseNameLkey;
	private ApLovValues doseNameLvalue;
	private Boolean isBooster = false;
	private ApVaccineDoseEntity translatedObject;

}