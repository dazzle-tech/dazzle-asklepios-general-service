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
public class ApIcdCodeEntity implements Serializable {

	private String key;
	private String icdVersion;
	private String icdCode;
	private String description;
	private String chapter;
	private String block;
	private String category;
	private String subcategory;
	private String fulldescription;
	private String includes;
	private String excludes1;
	private String excludes2;
	private String useadditionalcode;
	private String codefirst;
	private String codingguidelines;
	private String clinicaldescription;
	private String severity;
	private String synonyms;
	private String abbreviations;
	private String notes;
	private String requireSide;
	private String requireDetails;
	private String linkedWithAge;
	private String linkedWithGender;
	private String linkedWithDisease;
	private String moreSpecification;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private ApIcdCodeEntity translatedObject;

}