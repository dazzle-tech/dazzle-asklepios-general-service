package com.asklepios.backend_service.model.generated.pojo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import lombok.Getter;
import lombok.Setter;
import com.asklepios.backend_service.model.generated.entity.ApDentalChartProgressNoteEntity;

@Getter
@Setter
@Slf4j
public class ApDentalChartProgressNote extends ApDentalChartProgressNoteEntity implements Serializable {

    public String getCreateAudit() {
        String fingerprint = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        if (getCreatedAt() != null && getCreatedBy() != null) {
            fingerprint = "Created @ " + sdf.format(new Date(getCreatedAt().longValue())) + " By " + getCreatedBy() + "\n";
        }
        return fingerprint;
    }
    public String getUpdatedAudit() {
        String fingerprint = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        if (getUpdatedAt() != null && getUpdatedBy() != null) {
            fingerprint = "Updated @ " + sdf.format(new Date(getUpdatedAt().longValue())) + " By " + getUpdatedBy() + "\n";
        }
        return fingerprint;
    }

}