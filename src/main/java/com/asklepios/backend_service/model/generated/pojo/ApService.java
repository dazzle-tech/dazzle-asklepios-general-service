package com.asklepios.backend_service.model.generated.pojo;

import java.io.Serializable;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import lombok.Getter;
import lombok.Setter;
import com.asklepios.backend_service.model.generated.entity.ApServiceEntity;

@Getter
@Setter
@Slf4j
public class ApService extends ApServiceEntity implements Serializable {
    List<ApServiceCdt> linkedProcedures;

}