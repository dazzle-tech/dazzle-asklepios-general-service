package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.ApActiveIngredient;
import com.asklepios.backend_service.model.generated.pojo.ApActiveIngredientRecommendedDosage;
import com.asklepios.backend_service.model.generated.pojo.ApBrandMedicationSubstitutes;
import com.asklepios.backend_service.model.generated.pojo.ApGenericMedication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApBrandMedicationSubstitutesDAO;

@Service
@Slf4j
public class ApBrandMedicationSubstitutesService extends ApBrandMedicationSubstitutesDAO implements Serializable {

    public List<ApGenericMedication> getListOfLinkedBrands(String key) throws SQLException {
        List<ApGenericMedication> list = new ArrayList<>();


        String query = "SELECT DISTINCT gm.* " +
                "FROM ap_brand_medication_substitutes bms " +
                "JOIN ap_generic_medication gm " +
                "    ON gm.key = CASE " +
                "        WHEN bms.brand_key = ? THEN bms.alternative_brand_key " +
                "        WHEN bms.alternative_brand_key = ? THEN bms.brand_key " +
                "    END " +
                "WHERE bms.brand_key = ? OR bms.alternative_brand_key = ?";

        try (
                Connection con = DS.getConnection();
                PreparedStatement ps1 = con.prepareStatement(query)
        ) {

            ps1.setString(1, key);
            ps1.setString(2, key);
            ps1.setString(3, key);
            ps1.setString(4, key);


            try (ResultSet rs = ps1.executeQuery()) {
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
                    record.setMarketingAuthorizationHolder(rs.getString("marketing_authorization_holder"));

                    list.add(record);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return list;
    }
    public void removeRecord(String key) throws SQLException {

        try (Connection con = DS.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM ap_brand_medication_substitutes WHERE key = ?")
        ) {

            ps.setString(1, key);


            int rowsAffected = ps.executeUpdate();


            if (rowsAffected > 0) {
                System.out.println("Record deleted successfully for key: " + key);
            } else {
                System.out.println("No record found to delete with key: " + key);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error occurred while deleting record: " + e.getMessage(), e);
        }
    }



}