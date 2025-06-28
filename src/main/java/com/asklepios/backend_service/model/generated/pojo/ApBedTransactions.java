package com.asklepios.backend_service.model.generated.pojo;

import java.io.Serializable;
import lombok.extern.slf4j.Slf4j;
import lombok.Getter;
import lombok.Setter;
import com.asklepios.backend_service.model.generated.entity.ApBedTransactionsEntity;

@Getter
@Setter
@Slf4j
public class ApBedTransactions extends ApBedTransactionsEntity implements Serializable {
    ApRoom fromRoom;
    ApRoom toRoom;
    ApBed fromBed;
    ApBed toBed;
    ApPatient patient;
    ApAdmitOutpatientInpatient admitOutpatientInpatient;
}