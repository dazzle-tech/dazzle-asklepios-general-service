package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.ApPatientObservationSummary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApPatientObservationSummaryDAO;

@Service
@Slf4j
public class ApPatientObservationSummaryService extends ApPatientObservationSummaryDAO implements Serializable {
    public ApPatientObservationSummary getRecordByPatientKeyVisitKey(String patientKey, String visitKey) {
        System.out.println("inside Update");
        String specificKeyQuery = "SELECT key FROM ap_patient_observation_summary WHERE patient_key = ? AND visit_key = ?";
        ApPatientObservationSummary sRecord = null; // Initialize the object to return
        try (Connection conn = DS.getConnection();
             PreparedStatement stmt = conn.prepareStatement(specificKeyQuery)) {

            stmt.setString(1, patientKey);  // Set the patient_key parameter
            stmt.setString(2, visitKey);    // Set the visit_key parameter

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String key = rs.getString("key");
                    System.out.println("Key found: " + key);

                    // Fetch the full record based on the key
                    sRecord = getRecord(key);
                } else {
                    System.out.println("No record found with the specified patient_key and visit_key.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sRecord; // Return the record (could be null if not found)
    }
    public ApPatientObservationSummary getRecordByVisit(String visitKey) {
        String specificKeyQuery = "SELECT key FROM ap_patient_observation_summary WHERE visit_key = ?";
        ApPatientObservationSummary sRecord = null;
        try (Connection conn = DS.getConnection();
             PreparedStatement stmt = conn.prepareStatement(specificKeyQuery)) {
             stmt.setString(1, visitKey);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String key = rs.getString("key");
                    System.out.println("Key found: " + key);
                    sRecord = getRecord(key);
                } else {
                    System.out.println("No record found with the specified visit_key.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sRecord;
    }


}