package com.asklepios.backend_service.model.generated.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import lombok.Getter;
import lombok.Setter;
import com.asklepios.backend_service.model.generated.entity.ApDentalChartToothEntity;

@Getter
@Setter
@Slf4j
public class ApDentalChartTooth extends ApDentalChartToothEntity implements Serializable {
    List<ApToothAction> toothActions = new ArrayList<>();
    List<ApToothActionLog> toothHistory = new ArrayList<>();
    List<ApToothService> toothServices = new ArrayList<>();
    List<ApToothCdt> toothCdts = new ArrayList<>();
}