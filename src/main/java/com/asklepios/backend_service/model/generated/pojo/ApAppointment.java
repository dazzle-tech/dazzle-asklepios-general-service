package com.asklepios.backend_service.model.generated.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import lombok.Getter;
import lombok.Setter;
import com.asklepios.backend_service.model.generated.entity.ApAppointmentEntity;

@Getter
@Setter
@Slf4j
public class ApAppointment extends ApAppointmentEntity implements Serializable {

    private ApPatient patient;
    private List<String> selectedSlices;
    private Date appointmentDate;

}