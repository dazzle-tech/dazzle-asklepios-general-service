package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.asklepios.backend_service.database.DS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApDiagnosticOrderTestsRadReportDAO;

@Service
@Slf4j
public class ApDiagnosticOrderTestsRadReportService extends ApDiagnosticOrderTestsRadReportDAO implements Serializable {

    public void deleteRecord(String testKey) {
        String sql = "DELETE FROM ap_diagnostic_order_tests_rad_report WHERE order_test_key = ?";

        try (
                Connection con = DS.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ps.setString(1,testKey);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}