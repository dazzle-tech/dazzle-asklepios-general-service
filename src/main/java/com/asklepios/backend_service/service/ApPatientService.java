package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.ApPatient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApPatientDAO;

@Service
@Slf4j
public class ApPatientService extends ApPatientDAO implements Serializable {

    @Override
    public String saveRecord(ApPatient record) throws SQLException {
        String fullName = "";

        if (record.getFirstName() != null && !record.getFirstName().isBlank()) {
            fullName += record.getFirstName() + " ";
        }
        if (record.getSecondName() != null && !record.getSecondName().isBlank()) {
            fullName += record.getSecondName() + " ";
        }
        if (record.getThirdName() != null && !record.getThirdName().isBlank()) {
            fullName += record.getThirdName() + " ";
        }
        if (record.getLastName() != null && !record.getLastName().isBlank()) {
            fullName += record.getLastName() + " ";
        }
        record.setFullName(fullName);

        String fullNameOtherLang = "";

        if (record.getFirstNameOtherLang() != null && !record.getFirstNameOtherLang().isBlank()) {
            fullNameOtherLang += record.getFirstNameOtherLang() + " ";
        }
        if (record.getSecondNameOtherLang() != null && !record.getSecondNameOtherLang().isBlank()) {
            fullNameOtherLang += record.getSecondNameOtherLang() + " ";
        }
        if (record.getThirdNameOtherLang() != null && !record.getThirdNameOtherLang().isBlank()) {
            fullNameOtherLang += record.getThirdNameOtherLang() + " ";
        }
        if (record.getLastNameOtherLang() != null && !record.getLastNameOtherLang().isBlank()) {
            fullNameOtherLang += record.getLastNameOtherLang() + " ";
        }
        record.setFullNameOtherLang(fullNameOtherLang);


        return super.saveRecord((record));
    }
    public boolean getHasAllergy(String patientKey) throws SQLException {
        String query = "SELECT COUNT(patient_key) AS count FROM ap_visit_allergies WHERE patient_key= ?";

        try (
                Connection con = DS.getConnection();
                PreparedStatement ps = con.prepareStatement(query)
        ) {
            ps.setString(1, patientKey);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt("count");

                    return count > 0;
                }
            }
        } catch (SQLException e) {

            e.printStackTrace();
            throw e;
        }
        return false;
    }
    public boolean getHasWarning(String patientKey) throws SQLException {
        String query = "SELECT COUNT(patient_key) AS count FROM ap_visit_warning WHERE patient_key= ?";

        try (
                Connection con = DS.getConnection();
                PreparedStatement ps = con.prepareStatement(query)
        ) {
            ps.setString(1, patientKey);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt("count");

                    return count > 0;
                }
            }
        } catch (SQLException e) {

            e.printStackTrace();
            throw e;
        }
        return false;
    }
}