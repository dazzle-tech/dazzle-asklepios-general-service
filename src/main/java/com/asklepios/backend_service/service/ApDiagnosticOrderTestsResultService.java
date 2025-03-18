package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.ApDiagnosticOrderTests;
import com.asklepios.backend_service.model.generated.pojo.ApDiagnosticOrderTestsResult;
import com.asklepios.backend_service.model.generated.pojo.ApDiagnosticTest;
import com.asklepios.backend_service.model.generated.pojo.ApDiagnosticTestNormalRange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApDiagnosticOrderTestsResultDAO;

@Service
@Slf4j
public class ApDiagnosticOrderTestsResultService extends ApDiagnosticOrderTestsResultDAO implements Serializable {

    public ApDiagnosticTestNormalRange getNormalRange(String patientKey, String testKey,boolean isProfile ,String testProfileKey) throws SQLException {
        ApDiagnosticTestNormalRange record = new ApDiagnosticTestNormalRange();
        String query = "SELECT ap.* FROM ap_diagnostic_test_normal_range ap " +
                "JOIN ap_patient p ON p.key = ? " +
                "WHERE ( " +
                "    ? = true AND ap.profile_test_key = ? " +
                "    OR " +
                "    ? = false AND ap.test_key = ? " +
                ") " +
                "AND (ap.gender_lkey = p.gender_lkey OR ap.gender_lkey IS NULL) " +
                "AND ( " +
                "    EXTRACT(DAY FROM AGE(CURRENT_DATE, p.dob)) + " +
                "    EXTRACT(MONTH FROM AGE(CURRENT_DATE, p.dob)) * 30 + " +
                "    EXTRACT(YEAR FROM AGE(CURRENT_DATE, p.dob)) * 365 " +
                ") BETWEEN " +
                "CASE " +
                "    WHEN ap.age_from_unit_lkey = '5760975430600' THEN CAST(ap.age_from AS INTEGER) * 365 " +
                "    WHEN ap.age_from_unit_lkey = '1463985087863256' THEN CAST(ap.age_from AS INTEGER) " +
                "    WHEN ap.age_from_unit_lkey = '1464014707373477' THEN CAST(ap.age_from AS INTEGER) * 30 " +
                "    WHEN ap.age_from_unit_lkey = '1375343788087292' THEN CAST(ap.age_from AS INTEGER) * 7 " +
                "END " +
                "AND " +
                "CASE " +
                "    WHEN ap.age_to_unit_lkey = '5760975430600' THEN CAST(ap.age_to AS INTEGER) * 365 " +
                "    WHEN ap.age_to_unit_lkey = '1463985087863256' THEN CAST(ap.age_to AS INTEGER) " +
                "    WHEN ap.age_to_unit_lkey = '1464014707373477' THEN CAST(ap.age_to AS INTEGER) * 30 " +
                "    WHEN ap.age_to_unit_lkey = '1375343788087292' THEN CAST(ap.age_to AS INTEGER) * 7 " +
                "END " +
                "LIMIT 1;";






        try (Connection connection = DS.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Set the patientKey and testKey parameters
            statement.setString(1,patientKey);
            statement.setBoolean(2,isProfile);
            statement.setString(3, testProfileKey);
            statement.setBoolean(4,isProfile);
            statement.setString(5, testKey);
            System.out.println("query "+query);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    // Extract and return the result
                    record.setKey(rs.getString("key"));
                    record.setTestKey(rs.getString("test_key"));
                    record.setGenderLkey(rs.getString("gender_lkey"));
                    record.setAgeFrom(rs.getBigDecimal("age_from"));
                    record.setAgeFromUnitLkey(rs.getString("age_from_unit_lkey"));
                    record.setAgeTo(rs.getBigDecimal("age_to"));
                    record.setAgeToUnitLkey(rs.getString("age_to_unit_lkey"));
                    record.setConditionLkey(rs.getString("condition_lkey"));
                    record.setResultTypeLkey(rs.getString("result_type_lkey"));
                    record.setResultText(rs.getString("result_text"));
                    record.setResultLovKey(rs.getString("result_lov_key"));
                    record.setNormalRangeTypeLkey(rs.getString("normal_range_type_lkey"));
                    record.setRangeFrom(rs.getBigDecimal("range_from"));
                    record.setRangeTo(rs.getBigDecimal("range_to"));
                    record.setCriticalValue(rs.getBoolean("critical_value"));
                    record.setCriticalValueLessThan(rs.getBigDecimal("critical_value_less_than"));
                    record.setCriticalValueMoreThan(rs.getBigDecimal("critical_value_more_than"));
                    record.setCreatedBy(rs.getString("created_by"));
                    record.setUpdatedBy(rs.getString("updated_by"));
                    record.setDeletedBy(rs.getString("deleted_by"));
                    record.setCreatedAt(rs.getBigDecimal("created_at"));
                    record.setUpdatedAt(rs.getBigDecimal("updated_at"));
                    record.setDeletedAt(rs.getBigDecimal("deleted_at"));
                    record.setIsValid(rs.getBoolean("is_valid"));
                    return record;
                } else {
                    return record;
                }
            }
        }
    }

}