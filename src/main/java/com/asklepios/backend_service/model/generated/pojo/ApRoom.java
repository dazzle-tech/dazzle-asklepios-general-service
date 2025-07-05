package com.asklepios.backend_service.model.generated.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.extern.slf4j.Slf4j;
import lombok.Getter;
import lombok.Setter;
import com.asklepios.backend_service.model.generated.entity.ApRoomEntity;

@Getter
@Setter
@Slf4j
public class ApRoom extends ApRoomEntity implements Serializable {
 ApFacility facility;
 ApDepartment department;
 BigDecimal bedCount;
}