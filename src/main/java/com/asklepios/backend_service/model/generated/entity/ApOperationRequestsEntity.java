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
public class ApOperationRequestsEntity implements Serializable {

	private String key;
	private String facilityKey;
	private String departmentKey;
	private String operationKey;
	private String operationTypeLkey;
	private ApLovValues operationTypeLvalue;
	private String operationLevelLkey;
	private ApLovValues operationLevelLvalue;
	private String priorityLkey;
	private ApLovValues priorityLvalue;
	private String diagnosisKey;
	private String requestStatus;
	private String bodyPartLkey;
	private ApLovValues bodyPartLvalue;
	private String sideOfProcedureLkey;
	private ApLovValues sideOfProcedureLvalue;
	private String plannedAnesthesiaTypeLkey;
	private ApLovValues plannedAnesthesiaTypeLvalue;
	private Boolean needBloodProducts = false;
	private Boolean implantOrDeviceExpected = false;
	private String notes;
	private String createdBy;
	private BigDecimal createdAt;
	private String updatedBy;
	private BigDecimal updatedAt;
	private String cancelledBy;
	private BigDecimal cancelledAt;
	private String deletedBy;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private BigDecimal operationDateTime;
	private String statusLkey;
	private ApLovValues statusLvalue;
	private String encounterKey;
	private String patientKey;
	private BigDecimal submitedAt;
	private String submitedBy;
	private String operationStatusLkey;
	private ApLovValues operationStatusLvalue;
	private BigDecimal startedAt;
	private String startedBy;
	private BigDecimal increaseByMinutes;
	private BigDecimal monitorSlot;
	private String recoveryStatusLkey;
	private ApLovValues recoveryStatusLvalue;
	private String cancellationReason;
	private ApOperationRequestsEntity translatedObject;

}