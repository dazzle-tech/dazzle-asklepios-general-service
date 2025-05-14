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
    private   ApLovValuesService apLovValuesService;

    public void patientRelations(String firstKey, String secondKey, String firstRelation, String lang) throws SQLException {
        ApPatient firstPatient = apPatientService.getRecord(firstKey);
        if (firstPatient == null) return;

        apPatientService.populateLovFields(firstPatient, lang);
        if (firstPatient.getGenderLvalue() == null) return;

        String firstGenderCode = firstPatient.getGenderLvalue().getValueCode();
        if (firstGenderCode == null) return;

        ApPatient secondPatient = apPatientService.getRecord(secondKey);
        if (secondPatient == null) return;

        apPatientService.populateLovFields(secondPatient, lang);
        if (secondPatient.getGenderLvalue() == null) return;

        String secondGenderCode = secondPatient.getGenderLvalue().getValueCode();
        if (secondGenderCode == null) return;

        ApLovValues firstRelations = apLovValuesService.getRecord(firstRelation);
        if (firstRelations == null) return;

        String firstRelationCode = firstRelations.getValueCode();
        if (firstRelationCode == null) return;

        String secondRelationCode = getRelationByConditions(firstGenderCode, secondGenderCode, firstRelationCode);
        if (secondRelationCode == null) return;

        String secondRelationKey = getLovValueKey(secondRelationCode);
        if (secondRelationKey == null) return;

        ApPatientRelation patientRelation = new ApPatientRelation();
        patientRelation.setPatientKey(secondKey);
        patientRelation.setRelativePatientKey(firstKey);
        patientRelation.setRelationTypeLkey(secondRelationKey);
        saveRecord(patientRelation);
    }

    public String getRelationByConditions(String firstGenderCode, String secondGenderCode ,String firstRelationCode) throws SQLException {
        String sql = "SELECT * FROM ap_relations_matrix WHERE first_patient_gender = ? AND second_patient_gender = ? AND first_relation_code = ?";
        try (
                Connection con = DS.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, firstGenderCode);
            ps.setString(2, secondGenderCode);
            ps.setString(3, firstRelationCode);
            ResultSet rs = ps.executeQuery();

            ApRelationsMatrix record = new ApRelationsMatrix();
            if (rs.next()) {
                record.setKey(rs.getString("key"));
                record.setFirstPatientGender(rs.getString("first_patient_gender"));
                record.setSecondPatientGender(rs.getString("second_patient_gender"));
                record.setFirstRelationCode(rs.getString("first_relation_code"));
                record.setSecondRelationCode(rs.getString("second_relation_code"));
                record.setCreatedBy(rs.getString("created_by"));
                record.setUpdatedBy(rs.getString("updated_by"));
                record.setDeletedBy(rs.getString("deleted_by"));
                record.setCreatedAt(rs.getBigDecimal("created_at"));
                record.setUpdatedAt(rs.getBigDecimal("updated_at"));
                record.setDeletedAt(rs.getBigDecimal("deleted_at"));
            } else {
                return null;
            }
            return record.getSecondRelationCode();
        }

    }
    public String getLovValueKey(String code) throws SQLException {
        String sql = "SELECT * FROM ap_lov_values WHERE value_code = ?";
        try (
                Connection con = DS.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ApLovValues record = new ApLovValues();
                record.setKey(rs.getString("key"));
                record.setLovKey(rs.getString("lov_key"));
                record.setLovCode(rs.getString("lov_code"));
                record.setValueCode(rs.getString("value_code"));
                record.setLovDisplayVale(rs.getString("lov_display_vale"));
                record.setLoveCustomCode(rs.getString("love_custom_code"));
                record.setValueDescription(rs.getString("value_description"));
                record.setValueColor(rs.getString("value_color"));
                record.setValueIcon(rs.getString("value_icon"));
                record.setValueOrder(rs.getBigDecimal("value_order"));
                record.setIsdefault(rs.getBoolean("isdefault"));
                record.setSeededData(rs.getBoolean("seeded_data"));
                record.setForInternalUser(rs.getBoolean("for_internal_user"));
                record.setSpecificForScreenId(rs.getString("specific_for_screen_id"));
                record.setParentValueId(rs.getString("parent_value_id"));
                record.setCreatedBy(rs.getString("created_by"));
                record.setUpdatedBy(rs.getString("updated_by"));
                record.setDeletedBy(rs.getString("deleted_by"));
                record.setCreatedAt(rs.getBigDecimal("created_at"));
                record.setUpdatedAt(rs.getBigDecimal("updated_at"));
                record.setDeletedAt(rs.getBigDecimal("deleted_at"));
                record.setIsValid(rs.getBoolean("is_valid"));
                return record.getKey();
            } else {
                return null;
            }
        }
    }}
