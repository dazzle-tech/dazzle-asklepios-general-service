package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import com.asklepios.backend_service.model.generated.dao.ApDepartmentDAO;
import com.asklepios.backend_service.model.generated.pojo.ApDepartment;
import com.asklepios.backend_service.model.generated.pojo.ApDiagnosticTest;
import com.asklepios.backend_service.model.generated.pojo.ApPractitioner;
import com.asklepios.backend_service.model.generated.pojo.ApUserFacilities;
import com.asklepios.backend_service.model.pojo.response.ParentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApResourcesDAO;

@Service
@Slf4j
public class ApResourcesService extends ApResourcesDAO implements Serializable {

    public String getResourceName(String resourceTypeKey, String key) throws SQLException {

        if(resourceTypeKey == null || resourceTypeKey.isEmpty()) {
            return null;
        }
        // TODO update status to be a LOV value
        if(resourceTypeKey.equals("2039534205961578")) //Practitioner
        {
            List<ApPractitioner> listPra = new ApPractitionerService().getList("key = '" + key + "'");
            if (listPra != null && !listPra.isEmpty()) {
                return listPra.get(0).getPractitionerFullName();
            }
        }

        // TODO update status to be a LOV value
        else if(resourceTypeKey.equals("2039516279378421")) //Department
        {
            List<ApDepartment> listDep = new ApDepartmentService().getList("key = '"+ key +"'");
            if (listDep != null && !listDep.isEmpty()) {
                return listDep.get(0).getName();
            }

        }

        // TODO update status to be a LOV value
        else if(resourceTypeKey.equals("2039620472612029")) //Medical Test
        {
            List<ApDiagnosticTest> listDia = new ApDiagnosticTestService().getList("key = '"+ key +"'");
            if (listDia != null && !listDia.isEmpty()) {
                return listDia.get(0).getTestName();
            }

        }

        return null ;
    }
    }