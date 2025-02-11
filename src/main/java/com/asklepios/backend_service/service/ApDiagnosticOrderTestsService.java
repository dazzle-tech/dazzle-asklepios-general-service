package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.sql.SQLException;

import com.asklepios.backend_service.model.generated.pojo.ApDiagnosticTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApDiagnosticOrderTestsDAO;

@Service
@Slf4j
public class ApDiagnosticOrderTestsService extends ApDiagnosticOrderTestsDAO implements Serializable {
    public ApDiagnosticTest getTest( String key) throws SQLException {
        ApDiagnosticTest test = new ApDiagnosticTestService().getRecord( key );
        return test ;
    }
}