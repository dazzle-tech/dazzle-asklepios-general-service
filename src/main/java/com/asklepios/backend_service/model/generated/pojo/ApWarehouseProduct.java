package com.asklepios.backend_service.model.generated.pojo;

import java.io.Serializable;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import lombok.Getter;
import lombok.Setter;
import com.asklepios.backend_service.model.generated.entity.ApWarehouseProductEntity;

@Getter
@Setter
@Slf4j
public class ApWarehouseProduct extends ApWarehouseProductEntity implements Serializable {

    ApProducts productObj;
    String productName;
    String warehouseName;

}