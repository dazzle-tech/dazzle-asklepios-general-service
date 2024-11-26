package com.asklepios.backend_service.model.generated.pojo;

import java.io.Serializable;
import lombok.extern.slf4j.Slf4j;
import lombok.Getter;
import lombok.Setter;
import com.asklepios.backend_service.model.generated.entity.ApPatientEncounterOrderEntity;

@Getter
@Setter
@Slf4j
public class ApPatientEncounterOrder extends ApPatientEncounterOrderEntity implements Serializable {

    private String testName;
    private String orderTypeLkey;
    private String internalCode;
    private String internationalCodeOne;
    private String internationalCodeTwo ;
    private String internationalCodeThree;

}