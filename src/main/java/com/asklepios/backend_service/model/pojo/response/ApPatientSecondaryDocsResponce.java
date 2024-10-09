package com.asklepios.backend_service.model.pojo.response;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import java.io.Serializable;

@Getter
@Setter
@Slf4j

public class ApPatientSecondaryDocsResponce   implements Serializable {
    private String patientKey;
    private String docContry;
    private String docType;
    private String documentNo;


}

