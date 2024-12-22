package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.asklepios.backend_service.database.DS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApAgeGroupDAO;

@Service
@Slf4j
public class ApAgeGroupService extends ApAgeGroupDAO implements Serializable {

    public String getAgeGroupLKey(String birthDate) throws SQLException {
        String ageGroupLKey = null;
        String formattedBirthDate = birthDate;
        try (Connection connection = DS.getConnection();
             Statement statement = connection.createStatement()) {

            String query = "SELECT age_group_lkey, from_age, from_age_unit_lkey, to_age, to_age_unit_lkey, " +
                    "DATE_PART('day', CURRENT_TIMESTAMP - DATE '" + formattedBirthDate + "') as current_age_in_days, " +
                    "CASE " +
                    "    WHEN from_age_unit_lkey = '5760975430600' THEN CAST(from_age AS INTEGER) * 365 " +
                    "    WHEN from_age_unit_lkey = '1463985087863256' THEN CAST(from_age AS INTEGER) " +
                    "    WHEN from_age_unit_lkey = '1464014707373477' THEN CAST(from_age AS INTEGER) * 30 " +
                    "    WHEN from_age_unit_lkey = '1375343788087292' THEN CAST(from_age AS INTEGER) * 7 " +
                    "END as from_age_in_days, " +
                    "CASE " +
                    "    WHEN to_age_unit_lkey = '5760975430600' THEN CAST(to_age AS INTEGER) * 365 " +
                    "    WHEN to_age_unit_lkey = '1463985087863256' THEN CAST(to_age AS INTEGER) " +
                    "    WHEN to_age_unit_lkey = '1464014707373477' THEN CAST(to_age AS INTEGER) * 30 " +
                    "    WHEN to_age_unit_lkey = '1375343788087292' THEN CAST(to_age AS INTEGER) * 7 " +
                    "END as to_age_in_days " +
                    "FROM ap_age_group " +
                    "WHERE DATE_PART('day', CURRENT_TIMESTAMP - DATE '" + formattedBirthDate + "') >= CASE " +
                    "        WHEN from_age_unit_lkey = '5760975430600' THEN CAST(from_age AS INTEGER) * 365 " +
                    "        WHEN from_age_unit_lkey = '1463985087863256' THEN CAST(from_age AS INTEGER) " +
                    "        WHEN from_age_unit_lkey = '1464014707373477' THEN CAST(from_age AS INTEGER) * 30 " +
                    "        WHEN from_age_unit_lkey = '1375343788087292' THEN CAST(from_age AS INTEGER) * 7 " +
                    "    END " +
                    "AND DATE_PART('day', CURRENT_TIMESTAMP - DATE '" + formattedBirthDate + "') <= CASE " +
                    "        WHEN to_age_unit_lkey = '5760975430600' THEN CAST(to_age AS INTEGER) * 365 " +
                    "        WHEN to_age_unit_lkey = '1463985087863256' THEN CAST(to_age AS INTEGER) " +
                    "        WHEN to_age_unit_lkey = '1464014707373477' THEN CAST(to_age AS INTEGER) * 30 " +
                    "        WHEN to_age_unit_lkey = '1375343788087292' THEN CAST(to_age AS INTEGER) * 7 " +
                    "    END " +
                    "LIMIT 1";

            log.info("Executing query: {}", query);
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                ageGroupLKey = resultSet.getString("age_group_lkey");
            }
        } catch (SQLException e) {
            log.error("Error while fetching age group lkey: ", e);
            throw e;
        }

        return ageGroupLKey;
    }




}