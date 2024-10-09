package com.asklepios.backend_service.model.generated.pojo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import lombok.Getter;
import lombok.Setter;
import com.asklepios.backend_service.model.generated.entity.ApToothActionLogEntity;

@Getter
@Setter
@Slf4j
public class ApToothActionLog extends ApToothActionLogEntity implements Serializable {

    public String getFingerprint() {
        if (getLogTime() != null && getLogOwner() != null)
            return new SimpleDateFormat("yyyy-MM-dd hh:mm").format(new Date(getLogTime().longValue())) + " By " + getLogOwner();
        return "Unkown";
    }

}