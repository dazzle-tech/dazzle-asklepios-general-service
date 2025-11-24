
package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.dao.ApDepartmentServiceDAO;
import com.asklepios.backend_service.model.generated.dao.ApEncounterServiceDAO;
import com.asklepios.backend_service.model.generated.pojo.ApDepartmentServiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ApEncounterServiceService extends ApEncounterServiceDAO implements Serializable {

    @Autowired
    private ApEncounterServiceDAO apEncounterServiceDAO;
    @Autowired
    private ApDepartmentServiceDAO apDepartmentServiceDAO;

    public void addDepartmentServicesToEncounter(String encounterKey,
                                            String patientKey,
                                            String departmentKey,
                                             String createdBy,
                                                         BigDecimal createdAt) throws SQLException {

       List<ApDepartmentServiceService> DepartmentService= apDepartmentServiceDAO.getList("Department_key= '" + departmentKey + "'");

        if (DepartmentService != null && !DepartmentService.isEmpty()){
           for(ApDepartmentServiceService DepartmentServiceService:DepartmentService){
               com.asklepios.backend_service.model.generated.pojo.ApEncounterServiceService record = new com.asklepios.backend_service.model.generated.pojo.ApEncounterServiceService();
               record.setEncounterKey(encounterKey);
               record.setPatientKey(patientKey);
               record.setServiceKey(DepartmentServiceService.getServiceKey());
               record.setCreatedBy(createdBy);
               record.setCreatedAt(createdAt);
               record.setServiceName(DepartmentServiceService.getServiceName());
               record.setServicePrice(DepartmentServiceService.getServicePrice());
               record.setServiceCurrencyLkey(DepartmentServiceService.getServiceCurrencyLkey());
               apEncounterServiceDAO.saveRecord(record);
           }
       }

    }

}