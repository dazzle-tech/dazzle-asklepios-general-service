package com.asklepios.backend_service.model.generated.pojo;

import java.io.Serializable;
import lombok.extern.slf4j.Slf4j;
import lombok.Getter;
import lombok.Setter;
import com.asklepios.backend_service.model.generated.entity.ApEncounterVaccinationEntity;

@Getter
@Setter
@Slf4j
public class ApEncounterVaccination extends ApEncounterVaccinationEntity implements Serializable {
ApVaccine vaccine;
ApVaccineDose vaccineDose;
ApVaccineBrands vaccineBrands;
ApUser createByUser;
ApUser updateByUser;
ApUser deleteByUser;
ApUser reviewedByUser;
}