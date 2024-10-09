package com.asklepios.backend_service.model.pojo.response;
import java.io.Serializable;
import lombok.extern.slf4j.Slf4j;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Slf4j
public class ApAllergiesResponse implements Serializable {

    private String patientKey;
    private String allergensKey;
    private String allergenCode;
    private String allergenName;
    private String allergenType;
    private Boolean isValid = true;



}
