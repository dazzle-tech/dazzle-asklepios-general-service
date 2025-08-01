package com.asklepios.backend_service.model.generated.pojo;

import java.io.Serializable;
import lombok.extern.slf4j.Slf4j;
import lombok.Getter;
import lombok.Setter;
import com.asklepios.backend_service.model.generated.entity.ApInventoryTransferProductEntity;

@Getter
@Setter
@Slf4j
public class ApInventoryTransferProduct extends ApInventoryTransferProductEntity implements Serializable {

         ApInventoryTransfer transferObj;
         ApProducts productObj;
         ApWarehouse fromWarehouseObj;
         ApWarehouse toWarehouseObj;
}