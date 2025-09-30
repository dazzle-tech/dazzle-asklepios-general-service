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
public class ApTeleConsultationCallLogEntity implements Serializable {

	private String id;
	private String startedBy;
	private BigDecimal startedDate;
	private String teleConsultationId;
	private ApTeleConsultationCallLogEntity translatedObject;

}