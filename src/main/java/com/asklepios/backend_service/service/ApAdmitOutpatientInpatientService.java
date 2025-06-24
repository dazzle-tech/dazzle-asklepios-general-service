package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.sql.*;
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

    public ApAdmitOutpatientInpatient getRecordByToEncounterKey(String key) throws SQLException {
        try (
                Connection con = DS.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from ap_admit_outpatient_inpatient where to_encounter_key = '"+key+"'");) {
            ApAdmitOutpatientInpatient record = new ApAdmitOutpatientInpatient();
            if(rs.next()){
                record.setKey(rs.getString("key"));
                record.setToEncounterKey(rs.getString("to_encounter_key"));
                record.setFromEncounterKey(rs.getString("from_encounter_key"));
                record.setInpatientDepartmentKey(rs.getString("inpatient_department_key"));
                record.setPhysicianKey(rs.getString("physician_key"));
                record.setAdmissionNotes(rs.getString("admission_notes"));
                record.setCreatedBy(rs.getString("created_by"));
                record.setUpdatedBy(rs.getString("updated_by"));
                record.setDeletedBy(rs.getString("deleted_by"));
                record.setCreatedAt(rs.getBigDecimal("created_at"));
                record.setUpdatedAt(rs.getBigDecimal("updated_at"));
                record.setDeletedAt(rs.getBigDecimal("deleted_at"));
                record.setAdmitSourceLkey(rs.getString("admit_source_lkey"));
            } else { record = null; }
            return record;
        }
    }
}
