package com.asklepios.backend_service.model.generated.pojo;

import java.io.Serializable;
import lombok.extern.slf4j.Slf4j;
import lombok.Getter;
import lombok.Setter;
import com.asklepios.backend_service.model.generated.entity.ApOptometricExamEntity;

@Getter
@Setter
@Slf4j
public class ApOptometricExam extends ApOptometricExamEntity implements Serializable {
    ApUser createByUser;
    ApUser updateByUser;
    ApUser deleteByUser;
    ApIcdCode icdCode;
    ApIcdCode icdCode2;
}