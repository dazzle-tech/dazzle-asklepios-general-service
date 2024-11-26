package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import com.asklepios.backend_service.model.generated.pojo.ApDiagnosticTest;
import com.asklepios.backend_service.model.generated.pojo.ApPractitioner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApPatientEncounterOrderDAO;

@Service
@Slf4j
public class ApPatientEncounterOrderService extends ApPatientEncounterOrderDAO implements Serializable {
    public ApDiagnosticTest getTest( String key) throws SQLException {
        ApDiagnosticTest test = new ApDiagnosticTestService().getRecord( key );
        return test ;
    }
    public String getTestName( String key) throws SQLException {
        ApDiagnosticTest test = new ApDiagnosticTestService().getRecord( key );
        return test.getTestName() ;
    }
    public String getOrderType( String key) throws SQLException {
        ApDiagnosticTest test = new ApDiagnosticTestService().getRecord( key );
        return test.getTestTypeLkey() ;
    }
    public String getInternalCode( String key) throws SQLException {
        ApDiagnosticTest test = new ApDiagnosticTestService().getRecord( key );
        return test.getInternalCode() ;
    }
    public String getInternationalCodeOne( String key) throws SQLException {
        ApDiagnosticTest test = new ApDiagnosticTestService().getRecord( key );
        return test.getInternationalCodeOne() ;
    }
    public String getInternationalCodeTwo( String key) throws SQLException {
        ApDiagnosticTest test = new ApDiagnosticTestService().getRecord( key );
        return test.getInternationalCodeTwo() ;
    }
    public String getInternationalCodeThree( String key) throws SQLException {
        ApDiagnosticTest test = new ApDiagnosticTestService().getRecord( key );
        return test.getInternationalCodeThree() ;
    }
}