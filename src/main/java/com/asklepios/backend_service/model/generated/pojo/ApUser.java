package com.asklepios.backend_service.model.generated.pojo;

import java.io.Serializable;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import lombok.Getter;
import lombok.Setter;
import com.asklepios.backend_service.model.generated.entity.ApUserEntity;

@Getter
@Setter
@Slf4j
public class ApUser extends ApUserEntity implements Serializable {
    private List<String> _facilitiesInput;
    private List<String> _depratmentsInput;
    private String selectedDepartmentsFacilityKey;
}