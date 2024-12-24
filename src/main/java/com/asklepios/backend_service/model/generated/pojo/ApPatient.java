package com.asklepios.backend_service.model.generated.pojo;

import java.io.Serializable;

import com.asklepios.backend_service.controller.PublicServices;
import lombok.extern.slf4j.Slf4j;
import lombok.Getter;
import lombok.Setter;
import com.asklepios.backend_service.model.generated.entity.ApPatientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

@Getter
@Setter
@Slf4j
public class ApPatient extends ApPatientEntity implements Serializable {
    boolean skipValidation = false;
    private boolean hasAllergy;

}