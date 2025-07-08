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
public class ApTransferPatientEntity implements Serializable {

	private String key;
	private String patientKey;
	private String encounterKey;
	private String fromInpatientDepartmentKey;
	private String toInpatientDepartmentKey;
	private String reasonForTransfer;
	private Boolean urgentTransfer = false;
	private Boolean plannedTransfer = false;
	private String transferNotes;
	private Boolean finalVitalsBeforeTransfer = false;
	private Boolean ivLinesDripsChecked = false;
	private Boolean medicationAdministeredPreTransfer = false;
	private Boolean belongingsSentWithPatient = false;
	private Boolean clinicalHandoverDone = false;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private String statusLkey;
	private ApLovValues statusLvalue;
	private String cancellationReason;
	private String fromRoom;
	private String toRoom;
	private String fromBed;
	private String toBed;
	private String confirmedBy;
	private String confirmedAt;
	private ApTransferPatientEntity translatedObject;

}