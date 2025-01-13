package com.asklepios.backend_service.model.generated.pojo;

import java.io.Serializable;
import lombok.extern.slf4j.Slf4j;
import lombok.Getter;
import lombok.Setter;
import com.asklepios.backend_service.model.generated.entity.ApVaccineDosesIntervalEntity;

@Getter
@Setter
@Slf4j
public class ApVaccineDosesInterval extends ApVaccineDosesIntervalEntity implements Serializable {
ApVaccineDose fromDose;
ApVaccineDose toDose;
}