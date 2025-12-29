package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.ApActiveIngredient;
import com.asklepios.backend_service.model.generated.pojo.ApCustomeInstructions;
import com.asklepios.backend_service.model.generated.pojo.ApPrescriptionMedications;
import com.asklepios.backend_service.model.pojo.response.ParentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
 import lombok.extern.slf4j.Slf4j;
 import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApPrescriptionMedicationsDAO;

@Service
@Slf4j
public class ApPrescriptionMedicationsService extends ApPrescriptionMedicationsDAO implements Serializable {

     ApCustomeInstructionsService apCustomeInstructionsService ;
    public ResponseEntity<?> saveCustomeInstruction(ApPrescriptionMedications pm,String key) throws SQLException {
        try{
            ApCustomeInstructions customeInstructions = new ApCustomeInstructions();
            customeInstructions.setPrescriptionMedicationsKey(key);
            customeInstructions.setFrequencyLkey(pm.getFrequencyLkey());
            customeInstructions.setDose(pm.getDose());
            customeInstructions.setUnitLkey(pm.getUnitLkey());
            ParentResponse<ApCustomeInstructions> response = new ParentResponse<>();

            apCustomeInstructionsService.saveRecord(customeInstructions);
            response.setObject(customeInstructions);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }

    }

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
    public List<ApActiveIngredient> getListOfActiveIngredient(String key) throws SQLException {
        List<ApActiveIngredient> list = new ArrayList<>();

        String query = "SELECT active_ingredient_key FROM ap_generic_medication_active_ingredient WHERE   deleted_at is null  and  generic_medication_key = ?";
        String query2 = "SELECT * FROM ap_active_ingredient WHERE key = ?";

        try (
                Connection con = DS.getConnection();
                PreparedStatement ps1 = con.prepareStatement(query);
                PreparedStatement ps2 = con.prepareStatement(query2);
        ) {
            // Execute first query to get list of active_ingredient_keys
            ps1.setString(1, key);
            try (ResultSet rs1 = ps1.executeQuery()) {
                while (rs1.next()) {
                    String activeIngredientKey = rs1.getString("active_ingredient_key");

                    // Use the key to execute the second query
                    ps2.setString(1, activeIngredientKey);
                    try (ResultSet rs2 = ps2.executeQuery()) {
                        while (rs2.next()) {
                            ApActiveIngredient record = new ApActiveIngredient();
                            record.setKey(rs2.getString("key"));
                            record.setCode(rs2.getString("code"));
                            record.setName(rs2.getString("name"));
                            record.setHasSalt(rs2.getBoolean("has_salt"));
                            record.setSaltLkey(rs2.getString("salt_lkey"));
                            record.setMedicalCategoryLkey(rs2.getString("medical_category_lkey"));
                            record.setIsControlled(rs2.getBoolean("is_controlled"));
                            record.setControlledLkey(rs2.getString("controlled_lkey"));
                            record.setHasSynonyms(rs2.getBoolean("has_synonyms"));
                            record.setAtcCode(rs2.getString("atc_code"));
                            record.setDrugTypeLkey(rs2.getString("drug_type_lkey"));
                            record.setDrugClassLkey(rs2.getString("drug_class_lkey"));
                            record.setHasBlackBoxWarning(rs2.getBoolean("has_black_box_warning"));
                            record.setBlackBoxWarning(rs2.getString("black_box_warning"));
                            record.setCreatedBy(rs2.getString("created_by"));
                            record.setUpdatedBy(rs2.getString("updated_by"));
                            record.setDeletedBy(rs2.getString("deleted_by"));
                            record.setCreatedAt(rs2.getBigDecimal("created_at"));
                            record.setUpdatedAt(rs2.getBigDecimal("updated_at"));
                            record.setDeletedAt(rs2.getBigDecimal("deleted_at"));
                            record.setIsValid(rs2.getBoolean("is_valid"));
                            record.setMechanismOfAction(rs2.getString("mechanism_of_action"));
                            record.setToxicityMaximumDose(rs2.getString("toxicity_maximum_dose"));
                            record.setToxicityMaximumDosePerUnitLkey(rs2.getString("toxicity_maximum_dose_per_unit_lkey"));
                            record.setToxicityDetails(rs2.getString("toxicity_details"));
                            record.setPregnancyCategoryLkey(rs2.getString("pregnancy_category_lkey"));
                            record.setPregnancyNotes(rs2.getString("pregnancy_notes"));
                            record.setLactationRiskLkey(rs2.getString("lactation_risk_lkey"));
                            record.setLactationRiskNotes(rs2.getString("lactation_risk_notes"));
                            record.setDoseAdjustmentRenal(rs2.getBoolean("dose_adjustment_renal"));
                            record.setDoseAdjustmentHepatic(rs2.getBoolean("dose_adjustment_hepatic"));
                            record.setPharmaAbsorption(rs2.getString("pharma_absorption"));
                            record.setPharmaRouteOfElimination(rs2.getString("pharma_route_of_elimination"));
                            record.setPharmaVolumeOfDistribution(rs2.getString("pharma_volume_of_distribution"));
                            record.setPharmaHalfLife(rs2.getString("pharma_half_life"));
                            record.setPharmaProteinBinding(rs2.getString("pharma_protein_binding"));
                            record.setPharmaClearance(rs2.getString("pharma_clearance"));
                            record.setPharmaMetabolism(rs2.getString("pharma_metabolism"));
                            record.setDoseAdjPugA(rs2.getString("dose_adj_pug_a"));
                            record.setDoseAdjPugB(rs2.getString("dose_adj_pug_b"));
                            record.setDoseAdjPugC(rs2.getString("dose_adj_pug_c"));
                            record.setDoseAdjRenalOne(rs2.getString("dose_adj_renal_one"));
                            record.setDoseAdjRenalTwo(rs2.getString("dose_adj_renal_two"));
                            record.setDoseAdjRenalThree(rs2.getString("dose_adj_renal_three"));
                            record.setDoseAdjRenalFour(rs2.getString("dose_adj_renal_four"));
                            record.setChemicalFormula(rs2.getString("chemical_formula"));

                            list.add(record);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return list;
    }
    public boolean existsChronicByPatientAndBrandExceptKey(String patientKey, BigDecimal brandKey, String currentKey)
            throws SQLException {

        String sql =
                "SELECT 1 " +
                        "FROM ap_prescription_medications " +
                        "WHERE patient_key = ? " +
                        "  AND generic_medications_id = ? " +
                        "  AND chronic_medication = true " +
                        "  AND (is_valid = true OR is_valid IS NULL) " +
                        "  AND key <> ? " +
                        "LIMIT 1";

        try (Connection con = DS.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, patientKey);
            ps.setBigDecimal(2, brandKey);
            ps.setString(3, currentKey);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public boolean existsChronicByPatientAndBrand(String patientKey,BigDecimal brandKey) throws SQLException {
        String sql =
                "SELECT 1 " +
                        "FROM ap_prescription_medications " +
                        "WHERE patient_key = ? " +
                        "  AND generic_medications_id = ? " +
                        "  AND chronic_medication = true " +
                        "  AND (is_valid = true OR is_valid IS NULL) " +
                        "LIMIT 1";

        try (Connection con = DS.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, patientKey);
            ps.setBigDecimal(2, brandKey);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

}