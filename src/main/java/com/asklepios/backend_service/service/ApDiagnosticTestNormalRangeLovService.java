package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.asklepios.backend_service.model.generated.dao.ApGenericMedicationRoaDAO;
import com.asklepios.backend_service.model.generated.pojo.ApDiagnosticTestNormalRangeLov;
import com.asklepios.backend_service.model.generated.pojo.ApGenericMedicationRoa;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApDiagnosticTestNormalRangeLovDAO;

@Service
@Slf4j
public class ApDiagnosticTestNormalRangeLovService extends ApDiagnosticTestNormalRangeLovDAO implements Serializable {

    public void saveLOV(List<String> lovKey, String normalRangeKey) throws SQLException {
        try{
            if(lovKey.isEmpty())  {
                return;
            }
            List <ApDiagnosticTestNormalRangeLov> apDiagnosticTestNormalRangeLovList = getList("normal_range_key='"+normalRangeKey+"'");
            List <String> existingLovKeys = new ArrayList<>();
            for (ApDiagnosticTestNormalRangeLov apDiagnosticTestNormalRangeLov : apDiagnosticTestNormalRangeLovList) {
                existingLovKeys.add(apDiagnosticTestNormalRangeLov.getLovLkey());
                if (!lovKey.contains(apDiagnosticTestNormalRangeLov.getLovLkey())) {
                    new ApDiagnosticTestNormalRangeLovDAO().deleteRecord(apDiagnosticTestNormalRangeLov);
                }
            }
            for(String lov : lovKey) {
                if (!existingLovKeys.contains(lov)) {
                    ApDiagnosticTestNormalRangeLov apDiagnosticTestNormalRangeLov = new ApDiagnosticTestNormalRangeLov();
                    apDiagnosticTestNormalRangeLov.setNormalRangeKey(normalRangeKey);
                    apDiagnosticTestNormalRangeLov.setLovLkey(lov);
                    new ApDiagnosticTestNormalRangeLovDAO().saveRecord(apDiagnosticTestNormalRangeLov);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }
}