package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.ApLovValues;
import com.asklepios.backend_service.model.generated.pojo.ApVaccineDose;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApVaccineDoseDAO;

@Service
@Slf4j
public class ApVaccineDoseService extends ApVaccineDoseDAO implements Serializable {
    @Autowired
    ApLovValuesService apLovValuesService;
    public boolean checkRecordByVaccineAndDoses(ApVaccineDose apVaccineDose) throws SQLException {
        String query = """
        SELECT COUNT(*)
        FROM ap_vaccine_dose
        WHERE vaccine_key = ? 
          AND dose_name_lkey = ?
          AND (key IS NULL OR key != ?)
          AND deleted_at IS NULL
    """;

        try (
                Connection con = DS.getConnection();
                PreparedStatement ps = con.prepareStatement(query)
        ) {
            ps.setString(1, apVaccineDose.getVaccineKey());
            ps.setString(2, apVaccineDose.getDoseNameLkey());
            ps.setString(3, apVaccineDose.getKey() == null ? "" : apVaccineDose.getKey()); // في حالة الإضافة، تمرير قيمة فارغة

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // إذا وُجد سجل مكرر
                }
            }
        }
        return false;
    }


    public List<ApLovValues> getLovDisplayValues(String key) throws SQLException {
        List<ApLovValues> lovDisplayValues = new ArrayList<>();

        String query =
                "WITH Group1 AS (" +
                        "    SELECT " +
                        "        lov_display_vale::INTEGER AS value " +
                        "    FROM " +
                        "        ap_lov_values " +
                        "    WHERE " +
                        "        lov_key = '3108804153422360' " +
                        "        AND lov_display_vale ~ '^[0-9]+$' " +
                        "        AND lov_display_vale::INTEGER BETWEEN 1 AND 10 " +
                        "), " +
                        "Group2 AS (" +
                        "    SELECT " +
                        "        key, " +
                        "        lov_display_vale, " +
                        "        lov_key, " +
                        "        lov_code, " +
                        "        value_code, " +
                        "        love_custom_code, " +
                        "        value_description, " +
                        "        is_valid, " +
                        "        CAST(SUBSTRING(lov_display_vale FROM 1 FOR LENGTH(lov_display_vale) - 2) AS INTEGER) AS value " +
                        "    FROM " +
                        "        ap_lov_values " +
                        "    WHERE " +
                        "        lov_key = '3185159026345525' " +
                        "        AND lov_display_vale ~ '^[0-9]+(st|nd|rd|th)$' " +
                        ") " +
                        "SELECT " +
                        "    g2.key, " +
                        "    g2.lov_display_vale, " +
                        "    g2.lov_key, " +
                        "    g2.lov_code, " +
                        "    g2.value_code, " +
                        "    g2.love_custom_code, " +
                        "    g2.value_description, " +
                        "    g2.is_valid " +
                        "FROM " +
                        "    Group1 g1 " +
                        "JOIN " +
                        "    Group2 g2 " +
                        "ON " +
                        "    g2.value <= g1.value " +
                        "WHERE " +
                        "    g1.value = ? " +
                        "ORDER BY " +
                        "    g2.value";

        try (Connection con = DS.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, Integer.parseInt(key));

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ApLovValues record = new ApLovValues();
                    record.setKey(rs.getString("key"));
                    record.setLovKey(rs.getString("lov_key"));
                    record.setLovCode(rs.getString("lov_code"));
                    record.setLovDisplayVale(rs.getString("lov_display_vale"));
                    record.setValueCode(rs.getString("value_code"));
                    record.setLoveCustomCode(rs.getString("love_custom_code"));
                    record.setValueDescription(rs.getString("value_description"));
                    record.setIsValid(rs.getBoolean("is_valid"));

                    lovDisplayValues.add(record);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return lovDisplayValues;
    }
}