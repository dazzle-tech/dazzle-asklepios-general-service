package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.asklepios.backend_service.database.DS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApProcedureDAO;

@Service
@Slf4j
public class ApProcedureService extends ApProcedureDAO implements Serializable {

    public String getProcedureName(String Key) throws SQLException {
        String result = "";
        String query = "SELECT name FROM ap_procedure_setup WHERE key = ?";

        try (
                Connection con = DS.getConnection();
                PreparedStatement ps = con.prepareStatement(query)
        ) {
            ps.setString(1, Key);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String name= rs.getString("name");

                    result = name;
                } else {

                    result = " ";
                }
            }
        } catch (SQLException e) {

            e.printStackTrace();
            throw e;
        }
        return result;
    }
}