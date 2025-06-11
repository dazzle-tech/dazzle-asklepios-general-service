package com.asklepios.backend_service.model.generated.pojo;

import java.io.Serializable;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import lombok.Getter;
import lombok.Setter;
import com.asklepios.backend_service.model.generated.entity.ApDiagnosticOrderTestsEntity;

@Getter
@Setter
@Slf4j
public class ApDiagnosticOrderTests extends ApDiagnosticOrderTestsEntity implements Serializable {
    private   ApDiagnosticTest test;
    private List<ApDiagnosticTestProfile> profileList;
    private String orderId;
    private ApDiagnosticOrders order;

}