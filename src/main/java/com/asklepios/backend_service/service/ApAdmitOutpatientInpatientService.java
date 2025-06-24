package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.ApAdmitOutpatientInpatient;
import com.asklepios.backend_service.model.generated.pojo.ApEncounter;
import com.asklepios.backend_service.model.generated.pojo.ApResources;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApAdmitOutpatientInpatientDAO;

@Service
@Slf4j
public class ApAdmitOutpatientInpatientService extends ApAdmitOutpatientInpatientDAO implements Serializable {
    @Autowired
    private ApEncounterService apEncounterService;
    @Autowired
    private ApResourcesService apResourcesService;
    public ApAdmitOutpatientInpatient admitToInpatient(ApEncounter encounter, ApAdmitOutpatientInpatient admitOutpatientInpatient) throws SQLException {
        encounter.setEncounterStatusLkey("91109811181900");  // TODO replace with redis by lov code (ENC_STATUS/CLOSED)
        apEncounterService.saveRecord(encounter);

        ApEncounter newEncounter = new ApEncounter();
        newEncounter.setPatientKey(encounter.getPatientKey());
        newEncounter.setPatientAge(encounter.getPatientAge());
        String where = "resource_key = '" + admitOutpatientInpatient.getInpatientDepartmentKey() + "'";
        List<ApResources> list = apResourcesService.getList(where);



        newEncounter.setResourceTypeLkey("4217389643435490");  // TODO replace with redis by lov code
        newEncounter.setEncounterStatusLkey("5256965920133084");  // TODO replace with redis by lov code (ENC_STATUS/WAIT)

        if(!list.isEmpty()){  ApResources resource = list.get(0); newEncounter.setResourceKey(resource.getKey());}
        apEncounterService.saveRecord(newEncounter);

        admitOutpatientInpatient.setFromEncounterKey(encounter.getKey());
        admitOutpatientInpatient.setToEncounterKey(newEncounter.getKey());


         saveRecord(admitOutpatientInpatient);

        return admitOutpatientInpatient;
    }
}
