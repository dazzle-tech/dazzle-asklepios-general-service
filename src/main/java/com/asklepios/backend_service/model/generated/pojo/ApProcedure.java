package com.asklepios.backend_service.model.generated.pojo;

import java.io.Serializable;
import lombok.extern.slf4j.Slf4j;
import lombok.Getter;
import lombok.Setter;
import com.asklepios.backend_service.model.generated.entity.ApProcedureEntity;

@Getter
@Setter
@Slf4j
public class ApProcedure extends ApProcedureEntity implements Serializable {
     private  String procedureName;
     private  ApFacility facility;
     private ApDepartment department;

}