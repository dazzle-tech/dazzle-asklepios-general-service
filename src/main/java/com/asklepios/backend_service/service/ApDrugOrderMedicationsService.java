package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.asklepios.backend_service.database.DS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApDrugOrderMedicationsDAO;

@Service
@Slf4j
public class ApDrugOrderMedicationsService extends ApDrugOrderMedicationsDAO implements Serializable {
    public List<String> getActiveIngredientKeys(String visitKey) throws SQLException {
        String query = "SELECT active_ingredient_key FROM ap_generic_medication_active_ingredient WHERE generic_medication_key = ?";
        List<String> result = new ArrayList<>();

        try (
                Connection con = DS.getConnection();
                PreparedStatement ps = con.prepareStatement(query)
        ) {
            ps.setString(1, visitKey);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(rs.getString("active_ingredient_key"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }

}