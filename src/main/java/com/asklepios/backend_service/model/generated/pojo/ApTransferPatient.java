package com.asklepios.backend_service.model.generated.pojo;

import java.io.Serializable;
import lombok.extern.slf4j.Slf4j;
import lombok.Getter;
import lombok.Setter;
import com.asklepios.backend_service.model.generated.entity.ApTransferPatientEntity;

@Getter
@Setter
@Slf4j
public class ApTransferPatient extends ApTransferPatientEntity implements Serializable {
ApDepartment fromDepartment;
ApDepartment toDepartment;
ApPatient patient;
ApBed fromBedObject;
ApBed toBedObject;
ApRoom fromRoomObject;
ApRoom toRoomObject;

}