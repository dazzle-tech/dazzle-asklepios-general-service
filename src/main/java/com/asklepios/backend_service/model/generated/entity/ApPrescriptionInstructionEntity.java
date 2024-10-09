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
public class ApPrescriptionInstructionEntity implements Serializable {

	private String key;
	private String categoryLkey;
	private ApLovValues categoryLvalue;
	private BigDecimal dose;
	private String unitLkey;
	private ApLovValues unitLvalue;
	private String routLkey;
	private ApLovValues routLvalue;
	private String frequencyLkey;
	private ApLovValues frequencyLvalue;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private ApPrescriptionInstructionEntity translatedObject;

}