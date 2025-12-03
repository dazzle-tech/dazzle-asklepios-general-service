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
public class ApNurseServiceProductEntity implements Serializable {

	private String key;
	private String patientKey;
	private String encounterKey;
	private BigDecimal departmentId;
	private String categoryLkey;
	private ApLovValues categoryLvalue;
	private BigDecimal serviceId;
	private BigDecimal warehouseProductId;
	private BigDecimal quantity;
	private BigDecimal baseUomId;
	private BigDecimal unitPrice;
	private BigDecimal totalPrice;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private BigDecimal brandId;
	private ApNurseServiceProductEntity translatedObject;

}