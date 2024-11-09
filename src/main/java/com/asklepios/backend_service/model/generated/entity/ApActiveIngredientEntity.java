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
public class ApActiveIngredientEntity implements Serializable {

	private String key;
	private String code;
	private String name;
	private Boolean hasSalt = false;
	private String saltLkey;
	private ApLovValues saltLvalue;
	private String medicalCategoryLkey;
	private ApLovValues medicalCategoryLvalue;
	private Boolean isControlled = false;
	private String controlledLkey;
	private ApLovValues controlledLvalue;
	private Boolean hasSynonyms = false;
	private String chemicalFormula;
	private String drugTypeLkey;
	private ApLovValues drugTypeLvalue;
	private String drugClassLkey;
	private ApLovValues drugClassLvalue;
	private Boolean hasBlackBoxWarning = false;
	private String blackBoxWarning;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private String mechanismOfAction;
	private String toxicityMaximumDose;
	private String toxicityMaximumDosePerUnitLkey;
	private ApLovValues toxicityMaximumDosePerUnitLvalue;
	private String toxicityDetails;
	private String pregnancyCategoryLkey;
	private ApLovValues pregnancyCategoryLvalue;
	private String pregnancyNotes;
	private String lactationRiskLkey;
	private ApLovValues lactationRiskLvalue;
	private String lactationRiskNotes;
	private Boolean doseAdjustmentRenal = false;
	private Boolean doseAdjustmentHepatic = false;
	private String pharmaAbsorption;
	private String pharmaRouteOfElimination;
	private String pharmaVolumeOfDistribution;
	private String pharmaHalfLife;
	private String pharmaProteinBinding;
	private String pharmaClearance;
	private String pharmaMetabolism;
	private String doseAdjPugA;
	private String doseAdjPugB;
	private String doseAdjPugC;
	private String doseAdjRenalOne;
	private String doseAdjRenalTwo;
	private String doseAdjRenalThree;
	private String doseAdjRenalFour;
	private ApActiveIngredientEntity translatedObject;

}