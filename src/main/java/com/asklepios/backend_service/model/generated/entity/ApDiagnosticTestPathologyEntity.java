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
public class ApDiagnosticTestPathologyEntity implements Serializable {

	private String key;
	private String testKey;
	private String internationalCodingTypeLkey;
	private ApLovValues internationalCodingTypeLvalue;
	private String childCodeLkey;
	private ApLovValues childCodeLvalue;
	private String pathologyCategoryLkey;
	private ApLovValues pathologyCategoryLvalue;
	private String specimenTypeLkey;
	private ApLovValues specimenTypeLvalue;
	private String analysisProcedureLkey;
	private ApLovValues analysisProcedureLvalue;
	private String turnaroundTime;
	private String timeUnitLkey;
	private ApLovValues timeUnitLvalue;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private String testDescription;
	private String sampleHandling;
	private String medicalLndications;
	private String criticalValues;
	private String preparationRequirements;
	private String associatedRisks;
	private String pathCatalogKey;
	private ApDiagnosticTestPathologyEntity translatedObject;

}