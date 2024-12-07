package com.asklepios.backend_service.model.generated.pojo;

import java.io.Serializable;
 import java.math.BigDecimal;
import java.sql.SQLException;

import com.asklepios.backend_service.model.pojo.response.ParentResponse;
import com.asklepios.backend_service.service.ApCustomeInstructionsService;
import com.asklepios.backend_service.service.ApDiagnosticTestService;

import lombok.extern.slf4j.Slf4j;
import lombok.Getter;
import lombok.Setter;
import com.asklepios.backend_service.model.generated.entity.ApPrescriptionMedicationsEntity;

import org.springframework.http.ResponseEntity;


@Getter
@Setter
@Slf4j
public class ApPrescriptionMedications extends ApPrescriptionMedicationsEntity implements Serializable {
     private BigDecimal dose;
    private String frequencyLkey;
    private String roaLkey;
    private String unitLkey;
    private String frequencyLvalue;
    private String unitLvalue;
    private String roaLvalue;





}