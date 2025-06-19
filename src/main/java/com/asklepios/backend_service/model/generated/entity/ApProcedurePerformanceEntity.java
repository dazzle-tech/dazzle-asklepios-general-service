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
public class ApProcedurePerformanceEntity implements Serializable {

	private String key;
	private BigDecimal actualStartTime;
	private Boolean anesthesiaUsed = false;
	private String anesthesiaTypeLkey;
	private ApLovValues anesthesiaTypeLvalue;
	private BigDecimal anesthesiaStartTime;
	private BigDecimal anesthesiaEndTime;
	private String anesthesiaAdministeredBy;
	private Boolean timeOut = false;
	private String procedureOutcomeLkey;
	private ApLovValues procedureOutcomeLvalue;
	private String observations;
	private String complicationTypeLkey;
	private ApLovValues complicationTypeLvalue;
	private String complicationSeverityLkey;
	private ApLovValues complicationSeverityLvalue;
	private String actionsTaken;
	private BigDecimal actualEndTime;
	private String additionalNotes;
	private String homeInstructionLkey;
	private ApLovValues homeInstructionLvalue;
	private String homeInstructionNotes;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isvalid = false;
	private ApProcedurePerformanceEntity translatedObject;

}