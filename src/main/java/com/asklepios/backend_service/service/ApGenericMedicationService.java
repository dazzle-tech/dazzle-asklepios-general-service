package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.ApGenericMedication;
import com.asklepios.backend_service.model.generated.pojo.ApGenericMedicationRoa;
import com.asklepios.backend_service.model.generated.pojo.ApPractitioner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApGenericMedicationDAO;

@Service
@Slf4j
public class ApGenericMedicationService extends ApGenericMedicationDAO implements Serializable {
    public List<ApGenericMedication> getListapv(String active, String where) throws SQLException {
        if (where == null || where.isEmpty()) where = "1=1";

        String query = "SELECT g.*, " +
                "STRING_AGG(a.name || ' (' || a.atc_code || ')', ', ') AS active_ingredients " +
                "FROM ap_generic_medication g " +
                "LEFT JOIN ap_generic_medication_active_ingredient ga ON g.key = ga.generic_medication_key " +
                "LEFT JOIN ap_active_ingredient a ON ga.active_ingredient_key = a.key " +
                "WHERE (" +
                "  LOWER(a.name) LIKE ? " +
                "  OR LOWER(a.atc_code) LIKE ? " +
                "  OR LOWER(g.generic_name) LIKE ?" +
                ") " +
                "GROUP BY g.key";

        try (
                Connection con = DS.getConnection();
                PreparedStatement ps = con.prepareStatement(query)
        ) {
            String likeValue = "%" + active.toLowerCase() + "%";
            ps.setString(1, likeValue);
            ps.setString(2, likeValue);
            ps.setString(3, likeValue);

            ResultSet rs = ps.executeQuery();

            List<ApGenericMedication> list = new ArrayList<>();

            while (rs.next()) {
                ApGenericMedication record = new ApGenericMedication();
                record.setKey(rs.getString("key"));
                record.setGenericName(rs.getString("generic_name"));
                record.setManufacturerLkey(rs.getString("manufacturer_lkey"));
                record.setUsageInstructions(rs.getString("usage_instructions"));
                record.setDosageFormLkey(rs.getString("dosage_form_lkey"));
                record.setExpiresAfterOpening(rs.getBoolean("expires_after_opening"));
                record.setExpiresAfterOpeningValue(rs.getString("expires_after_opening_value"));
                record.setSinglePatientUse(rs.getBoolean("single_patient_use"));
                record.setPrice(rs.getBigDecimal("price"));
                record.setCurrencyLkey(rs.getString("currency_lkey"));
                record.setPriceListKey(rs.getString("price_list_key"));
                record.setCost(rs.getBigDecimal("cost"));
                record.setStorageRequirements(rs.getString("storage_requirements"));
                record.setCreatedBy(rs.getString("created_by"));
                record.setUpdatedBy(rs.getString("updated_by"));
                record.setDeletedBy(rs.getString("deleted_by"));
                record.setCreatedAt(rs.getBigDecimal("created_at"));
                record.setUpdatedAt(rs.getBigDecimal("updated_at"));
                record.setDeletedAt(rs.getBigDecimal("deleted_at"));
                record.setIsValid(rs.getBoolean("is_valid"));
                record.setCode(rs.getString("code"));
                record.setRoaLkey(rs.getString("roa_lkey"));
                record.setActiveIngredients(rs.getString("active_ingredients"));
                list.add(record);
            }

            return list;
        }
    }


}