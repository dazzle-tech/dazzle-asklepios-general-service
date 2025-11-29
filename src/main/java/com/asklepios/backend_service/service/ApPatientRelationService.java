package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.sql.*;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApPatientRelationDAO;

@Service
@Slf4j
public class ApPatientRelationService extends ApPatientRelationDAO implements Serializable {

    @Autowired
    private ApPatientService apPatientService;

    @Autowired
    private ApLovValuesService apLovValuesService;

    public void createReverseRelation(String patientKey, String relativePatientKey,
                                      String relationTypeLkey, String lang) {
        try {
            log.info("Starting reverse relation creation: patient={}, relative={}, relationType={}",
                    patientKey, relativePatientKey, relationTypeLkey);

            // Get first patient and gender
            ApPatient firstPatient = apPatientService.getRecord(patientKey);
            if (firstPatient == null) {
                log.warn("First patient not found: {}", patientKey);
                return;
            }

            apPatientService.populateLovFields(firstPatient, lang);
            if (firstPatient.getGenderLvalue() == null) {
                log.warn("First patient gender not found");
                return;
            }
            String firstGenderCode = firstPatient.getGenderLvalue().getValueCode();
            if (firstGenderCode == null) {
                log.warn("First patient gender code is null");
                return;
            }

            // Get second patient and gender
            ApPatient secondPatient = apPatientService.getRecord(relativePatientKey);
            if (secondPatient == null) {
                log.warn("Second patient not found: {}", relativePatientKey);
                return;
            }

            apPatientService.populateLovFields(secondPatient, lang);
            if (secondPatient.getGenderLvalue() == null) {
                log.warn("Second patient gender not found");
                return;
            }
            String secondGenderCode = secondPatient.getGenderLvalue().getValueCode();
            if (secondGenderCode == null) {
                log.warn("Second patient gender code is null");
                return;
            }

            // Get the original relation code
            ApLovValues firstRelationLov = apLovValuesService.getRecord(relationTypeLkey);
            if (firstRelationLov == null) {
                log.warn("First relation LOV not found: {}", relationTypeLkey);
                return;
            }

            String firstRelationCode = firstRelationLov.getValueCode();
            if (firstRelationCode == null) {
                log.warn("First relation code is null");
                return;
            }

            log.info("Searching matrix: firstGender={}, secondGender={}, firstRelation={}",
                    firstGenderCode, secondGenderCode, firstRelationCode);

            // Find the reverse relation code from the matrix
            String reverseRelationCode = getRelationByConditions(
                    firstGenderCode,
                    secondGenderCode,
                    firstRelationCode
            );

            if (reverseRelationCode == null) {
                log.warn("No reverse relation found in matrix for: {}", firstRelationCode);
                return;
            }

            log.info("Found reverse relation code: {}", reverseRelationCode);

            // Get the LOV key for the reverse relation
            String reverseRelationKey = getLovValueKey(reverseRelationCode);
            if (reverseRelationKey == null) {
                log.warn("Reverse relation key not found for code: {}", reverseRelationCode);
                return;
            }

            log.info("Found reverse relation key: {}", reverseRelationKey);

            // Check if reverse relation already exists
            if (reverseRelationExists(relativePatientKey, patientKey, reverseRelationKey)) {
                log.info("Reverse relation already exists, skipping creation");
                return;
            }

            // Create the reverse relation
            ApPatientRelation reverseRelation = new ApPatientRelation();
            reverseRelation.setPatientKey(relativePatientKey);      // SWAP: relative becomes patient
            reverseRelation.setRelativePatientKey(patientKey);       // SWAP: patient becomes relative
            reverseRelation.setRelationTypeLkey(reverseRelationKey); // Use reverse relation type

            saveRecord(reverseRelation);
            log.info("Successfully created reverse relation with key: {}", reverseRelation.getKey());

        } catch (Exception e) {
            log.error("Error creating reverse relation: {}", e.getMessage(), e);
        }
    }

    /**
     * Check if a relation already exists between two patients
     */
    private boolean reverseRelationExists(String patientKey, String relativeKey,
                                          String relationKey) throws SQLException {
        String sql = "SELECT COUNT(*) FROM ap_patient_relation " +
                "WHERE patient_key = ? " +
                "AND relative_patient_key = ? " +
                "AND relation_type_lkey = ? " +
                "AND deleted_at IS NULL";

        try (Connection con = DS.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, patientKey);
            ps.setString(2, relativeKey);
            ps.setString(3, relationKey);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        }
    }

    /**
     * Get the reverse relation code from the matrix table
     */
    public String getRelationByConditions(String firstGenderCode, String secondGenderCode,
                                          String firstRelationCode) throws SQLException {
        String sql = "SELECT second_relation_code FROM ap_relations_matrix " +
                "WHERE first_patient_gender = ? " +
                "AND second_patient_gender = ? " +
                "AND first_relation_code = ? " +
                "AND deleted_at IS NULL";

        try (Connection con = DS.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, firstGenderCode);
            ps.setString(2, secondGenderCode);
            ps.setString(3, firstRelationCode);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String code = rs.getString("second_relation_code");
                log.debug("Found second_relation_code: {}", code);
                return code;
            }
            log.debug("No matching relation found in matrix");
            return null;
        }
    }

    /**
     * Get the LOV key by value code
     */
    public String getLovValueKey(String code) throws SQLException {
        String sql = "SELECT key FROM ap_lov_values " +
                "WHERE value_code = ? " +
                "AND deleted_at IS NULL";

        try (Connection con = DS.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, code);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String key = rs.getString("key");
                log.debug("Found LOV key: {} for code: {}", key, code);
                return key;
            }
            log.debug("No LOV key found for code: {}", code);
            return null;
        }
    }
}