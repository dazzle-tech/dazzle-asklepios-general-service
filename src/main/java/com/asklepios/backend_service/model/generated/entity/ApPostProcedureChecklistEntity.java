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
public class ApPostProcedureChecklistEntity implements Serializable {

	private String key;
	private String procedureKey;
	private Boolean nauseaVomiting = false;
	private Boolean awakeAndOriented = false;
	private Boolean toleratingOralFluids = false;
	private Boolean ambulatingIndependently = false;
	private Boolean voidedUrine = false;
	private Boolean noActiveBleeding = false;
	private Boolean painScore4 = false;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isvalid = false;
	private ApPostProcedureChecklistEntity translatedObject;

}