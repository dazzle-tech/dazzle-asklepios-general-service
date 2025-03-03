package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.ApDiagnosticCoding;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApDiagnosticCodingDAO;

@Service
@Slf4j
public class ApDiagnosticCodingService extends ApDiagnosticCodingDAO implements Serializable {
    public void deleteCoding(ApDiagnosticCoding record) throws SQLException {
        try (
                Connection con = DS.getConnection();
                PreparedStatement ps = con.prepareStatement("delete from ap_diagnostic_coding  where key = ?");
        ) {
            ps.setString(1, record.getKey());
            ps.executeUpdate();
        }
    }

}