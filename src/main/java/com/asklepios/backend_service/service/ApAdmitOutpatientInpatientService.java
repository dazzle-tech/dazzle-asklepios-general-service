package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.ApAdmitOutpatientInpatient;
import com.asklepios.backend_service.model.generated.pojo.ApEncounter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApAdmitOutpatientInpatientDAO;

@Service
@Slf4j
public class ApAdmitOutpatientInpatientService extends ApAdmitOutpatientInpatientDAO implements Serializable {
    @Autowired
    private ApEncounterService apEncounterService;
    public ApAdmitOutpatientInpatient admitToInpatient(ApEncounter encounter, ApAdmitOutpatientInpatient admitOutpatientInpatient) throws SQLException {
        encounter.setEncounterStatusLkey("91109811181900");  // TODO replace with redis by lov code (ENC_STATUS/CLOSED)
        apEncounterService.saveRecord(encounter);

        ApEncounter newEncounter = new ApEncounter();
        newEncounter.setPatientKey(encounter.getPatientKey());
        newEncounter.setPatientAge(encounter.getPatientAge());
        newEncounter.setEncounterStatusLkey("5256965920133084");  // TODO replace with redis by lov code (ENC_STATUS/WAIT)
        newEncounter.setResourceKey(admitOutpatientInpatient.getPhysicianKey());
        apEncounterService.saveRecord(newEncounter);

        admitOutpatientInpatient.setFromEncounterKey(encounter.getKey());
        admitOutpatientInpatient.setToEncounterKey(newEncounter.getKey());


         saveRecord(admitOutpatientInpatient);

        return admitOutpatientInpatient;
    }
}
