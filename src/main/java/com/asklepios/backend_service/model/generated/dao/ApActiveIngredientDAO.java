package com.asklepios.backend_service.model.generated.dao;

import java.io.Serializable;
import lombok.extern.slf4j.Slf4j;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;
import java.lang.System;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.asklepios.backend_service.controller.PublicServices;
import java.lang.reflect.Field;
import com.asklepios.backend_service.model.generated.pojo.ApActiveIngredient;
import com.asklepios.backend_service.model.generated.entity.ApActiveIngredientEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApActiveIngredientDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApActiveIngredient getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_active_ingredient where key = '"+key+"'");) {
ApActiveIngredient record = new ApActiveIngredient();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setCode(rs.getString("code"));
record.setName(rs.getString("name"));
record.setHasSalt(rs.getBoolean("has_salt"));
record.setSaltLkey(rs.getString("salt_lkey"));
record.setMedicalCategoryLkey(rs.getString("medical_category_lkey"));
record.setIsControlled(rs.getBoolean("is_controlled"));
record.setControlledLkey(rs.getString("controlled_lkey"));
record.setHasSynonyms(rs.getBoolean("has_synonyms"));
record.setAtcCode(rs.getString("atc_code"));
record.setDrugTypeLkey(rs.getString("drug_type_lkey"));
record.setDrugClassLkey(rs.getString("drug_class_lkey"));
record.setHasBlackBoxWarning(rs.getBoolean("has_black_box_warning"));
record.setBlackBoxWarning(rs.getString("black_box_warning"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setMechanismOfAction(rs.getString("mechanism_of_action"));
record.setToxicityMaximumDose(rs.getString("toxicity_maximum_dose"));
record.setToxicityMaximumDosePerUnitLkey(rs.getString("toxicity_maximum_dose_per_unit_lkey"));
record.setToxicityDetails(rs.getString("toxicity_details"));
record.setPregnancyCategoryLkey(rs.getString("pregnancy_category_lkey"));
record.setPregnancyNotes(rs.getString("pregnancy_notes"));
record.setLactationRiskLkey(rs.getString("lactation_risk_lkey"));
record.setLactationRiskNotes(rs.getString("lactation_risk_notes"));
record.setDoseAdjustmentRenal(rs.getBoolean("dose_adjustment_renal"));
record.setDoseAdjustmentHepatic(rs.getBoolean("dose_adjustment_hepatic"));
record.setPharmaAbsorption(rs.getString("pharma_absorption"));
record.setPharmaRouteOfElimination(rs.getString("pharma_route_of_elimination"));
record.setPharmaVolumeOfDistribution(rs.getString("pharma_volume_of_distribution"));
record.setPharmaHalfLife(rs.getString("pharma_half_life"));
record.setPharmaProteinBinding(rs.getString("pharma_protein_binding"));
record.setPharmaClearance(rs.getString("pharma_clearance"));
record.setPharmaMetabolism(rs.getString("pharma_metabolism"));
record.setDoseAdjPugA(rs.getString("dose_adj_pug_a"));
record.setDoseAdjPugB(rs.getString("dose_adj_pug_b"));
record.setDoseAdjPugC(rs.getString("dose_adj_pug_c"));
record.setDoseAdjRenalOne(rs.getString("dose_adj_renal_one"));
record.setDoseAdjRenalTwo(rs.getString("dose_adj_renal_two"));
record.setDoseAdjRenalThree(rs.getString("dose_adj_renal_three"));
record.setDoseAdjRenalFour(rs.getString("dose_adj_renal_four"));
record.setChemicalFormula(rs.getString("chemical_formula"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApActiveIngredient record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_active_ingredient set key = ?, code = ?, name = ?, has_salt = ?, salt_lkey = ?, medical_category_lkey = ?, is_controlled = ?, controlled_lkey = ?, has_synonyms = ?, atc_code = ?, drug_type_lkey = ?, drug_class_lkey = ?, has_black_box_warning = ?, black_box_warning = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ?, mechanism_of_action = ?, toxicity_maximum_dose = ?, toxicity_maximum_dose_per_unit_lkey = ?, toxicity_details = ?, pregnancy_category_lkey = ?, pregnancy_notes = ?, lactation_risk_lkey = ?, lactation_risk_notes = ?, dose_adjustment_renal = ?, dose_adjustment_hepatic = ?, pharma_absorption = ?, pharma_route_of_elimination = ?, pharma_volume_of_distribution = ?, pharma_half_life = ?, pharma_protein_binding = ?, pharma_clearance = ?, pharma_metabolism = ?, dose_adj_pug_a = ?, dose_adj_pug_b = ?, dose_adj_pug_c = ?, dose_adj_renal_one = ?, dose_adj_renal_two = ?, dose_adj_renal_three = ?, dose_adj_renal_four = ?, chemical_formula = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getCode());
ps.setString(3, record.getName());
ps.setBoolean(4, record.getHasSalt());
ps.setString(5, record.getSaltLkey());
ps.setString(6, record.getMedicalCategoryLkey());
ps.setBoolean(7, record.getIsControlled());
ps.setString(8, record.getControlledLkey());
ps.setBoolean(9, record.getHasSynonyms());
ps.setString(10, record.getAtcCode());
ps.setString(11, record.getDrugTypeLkey());
ps.setString(12, record.getDrugClassLkey());
ps.setBoolean(13, record.getHasBlackBoxWarning());
ps.setString(14, record.getBlackBoxWarning());
ps.setString(15, record.getCreatedBy());
ps.setString(16, record.getUpdatedBy());
ps.setString(17, record.getDeletedBy());
ps.setBigDecimal(18, record.getCreatedAt());
ps.setBigDecimal(19, record.getUpdatedAt());
ps.setBigDecimal(20, record.getDeletedAt());
ps.setBoolean(21, record.getIsValid());
ps.setString(22, record.getMechanismOfAction());
ps.setString(23, record.getToxicityMaximumDose());
ps.setString(24, record.getToxicityMaximumDosePerUnitLkey());
ps.setString(25, record.getToxicityDetails());
ps.setString(26, record.getPregnancyCategoryLkey());
ps.setString(27, record.getPregnancyNotes());
ps.setString(28, record.getLactationRiskLkey());
ps.setString(29, record.getLactationRiskNotes());
ps.setBoolean(30, record.getDoseAdjustmentRenal());
ps.setBoolean(31, record.getDoseAdjustmentHepatic());
ps.setString(32, record.getPharmaAbsorption());
ps.setString(33, record.getPharmaRouteOfElimination());
ps.setString(34, record.getPharmaVolumeOfDistribution());
ps.setString(35, record.getPharmaHalfLife());
ps.setString(36, record.getPharmaProteinBinding());
ps.setString(37, record.getPharmaClearance());
ps.setString(38, record.getPharmaMetabolism());
ps.setString(39, record.getDoseAdjPugA());
ps.setString(40, record.getDoseAdjPugB());
ps.setString(41, record.getDoseAdjPugC());
ps.setString(42, record.getDoseAdjRenalOne());
ps.setString(43, record.getDoseAdjRenalTwo());
ps.setString(44, record.getDoseAdjRenalThree());
ps.setString(45, record.getDoseAdjRenalFour());
ps.setString(46, record.getChemicalFormula());
ps.setString(47, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApActiveIngredient record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_active_ingredient set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApActiveIngredient> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_active_ingredient where "+ where);) {
List<ApActiveIngredient> list = new ArrayList<ApActiveIngredient>();
while(rs.next()){
ApActiveIngredient record = new ApActiveIngredient();
record.setKey(rs.getString("key"));
record.setCode(rs.getString("code"));
record.setName(rs.getString("name"));
record.setHasSalt(rs.getBoolean("has_salt"));
record.setSaltLkey(rs.getString("salt_lkey"));
record.setMedicalCategoryLkey(rs.getString("medical_category_lkey"));
record.setIsControlled(rs.getBoolean("is_controlled"));
record.setControlledLkey(rs.getString("controlled_lkey"));
record.setHasSynonyms(rs.getBoolean("has_synonyms"));
record.setAtcCode(rs.getString("atc_code"));
record.setDrugTypeLkey(rs.getString("drug_type_lkey"));
record.setDrugClassLkey(rs.getString("drug_class_lkey"));
record.setHasBlackBoxWarning(rs.getBoolean("has_black_box_warning"));
record.setBlackBoxWarning(rs.getString("black_box_warning"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setMechanismOfAction(rs.getString("mechanism_of_action"));
record.setToxicityMaximumDose(rs.getString("toxicity_maximum_dose"));
record.setToxicityMaximumDosePerUnitLkey(rs.getString("toxicity_maximum_dose_per_unit_lkey"));
record.setToxicityDetails(rs.getString("toxicity_details"));
record.setPregnancyCategoryLkey(rs.getString("pregnancy_category_lkey"));
record.setPregnancyNotes(rs.getString("pregnancy_notes"));
record.setLactationRiskLkey(rs.getString("lactation_risk_lkey"));
record.setLactationRiskNotes(rs.getString("lactation_risk_notes"));
record.setDoseAdjustmentRenal(rs.getBoolean("dose_adjustment_renal"));
record.setDoseAdjustmentHepatic(rs.getBoolean("dose_adjustment_hepatic"));
record.setPharmaAbsorption(rs.getString("pharma_absorption"));
record.setPharmaRouteOfElimination(rs.getString("pharma_route_of_elimination"));
record.setPharmaVolumeOfDistribution(rs.getString("pharma_volume_of_distribution"));
record.setPharmaHalfLife(rs.getString("pharma_half_life"));
record.setPharmaProteinBinding(rs.getString("pharma_protein_binding"));
record.setPharmaClearance(rs.getString("pharma_clearance"));
record.setPharmaMetabolism(rs.getString("pharma_metabolism"));
record.setDoseAdjPugA(rs.getString("dose_adj_pug_a"));
record.setDoseAdjPugB(rs.getString("dose_adj_pug_b"));
record.setDoseAdjPugC(rs.getString("dose_adj_pug_c"));
record.setDoseAdjRenalOne(rs.getString("dose_adj_renal_one"));
record.setDoseAdjRenalTwo(rs.getString("dose_adj_renal_two"));
record.setDoseAdjRenalThree(rs.getString("dose_adj_renal_three"));
record.setDoseAdjRenalFour(rs.getString("dose_adj_renal_four"));
record.setChemicalFormula(rs.getString("chemical_formula"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApActiveIngredient record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_active_ingredient values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getCode());
ps.setString(3, record.getName());
ps.setBoolean(4, record.getHasSalt());
ps.setString(5, record.getSaltLkey());
ps.setString(6, record.getMedicalCategoryLkey());
ps.setBoolean(7, record.getIsControlled());
ps.setString(8, record.getControlledLkey());
ps.setBoolean(9, record.getHasSynonyms());
ps.setString(10, record.getAtcCode());
ps.setString(11, record.getDrugTypeLkey());
ps.setString(12, record.getDrugClassLkey());
ps.setBoolean(13, record.getHasBlackBoxWarning());
ps.setString(14, record.getBlackBoxWarning());
ps.setString(15, record.getCreatedBy());
ps.setString(16, record.getUpdatedBy());
ps.setString(17, record.getDeletedBy());
ps.setBigDecimal(18, record.getCreatedAt());
ps.setBigDecimal(19, record.getUpdatedAt());
ps.setBigDecimal(20, record.getDeletedAt());
ps.setBoolean(21, record.getIsValid());
ps.setString(22, record.getMechanismOfAction());
ps.setString(23, record.getToxicityMaximumDose());
ps.setString(24, record.getToxicityMaximumDosePerUnitLkey());
ps.setString(25, record.getToxicityDetails());
ps.setString(26, record.getPregnancyCategoryLkey());
ps.setString(27, record.getPregnancyNotes());
ps.setString(28, record.getLactationRiskLkey());
ps.setString(29, record.getLactationRiskNotes());
ps.setBoolean(30, record.getDoseAdjustmentRenal());
ps.setBoolean(31, record.getDoseAdjustmentHepatic());
ps.setString(32, record.getPharmaAbsorption());
ps.setString(33, record.getPharmaRouteOfElimination());
ps.setString(34, record.getPharmaVolumeOfDistribution());
ps.setString(35, record.getPharmaHalfLife());
ps.setString(36, record.getPharmaProteinBinding());
ps.setString(37, record.getPharmaClearance());
ps.setString(38, record.getPharmaMetabolism());
ps.setString(39, record.getDoseAdjPugA());
ps.setString(40, record.getDoseAdjPugB());
ps.setString(41, record.getDoseAdjPugC());
ps.setString(42, record.getDoseAdjRenalOne());
ps.setString(43, record.getDoseAdjRenalTwo());
ps.setString(44, record.getDoseAdjRenalThree());
ps.setString(45, record.getDoseAdjRenalFour());
ps.setString(46, record.getChemicalFormula());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApActiveIngredientEntity entity, String lang) {
        Class<?> myClass = ApActiveIngredientEntity.class;
        Field[] fields = myClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            if (fieldName.contains("Lkey")) {
                try {
                    Object fieldValue = field.get(entity);
                    if (fieldValue != null) {
                        String _lovKey = fieldValue.toString();
                        Field valueField = myClass.getDeclaredField (fieldName.replaceAll("Lkey", "Lvalue"));
                        valueField.setAccessible(true);
                        if (lang == null) {
                            valueField.set(entity, publicServices.getFromRedisLovValue(_lovKey));
                        } else {
                            valueField.set(entity, publicServices.getFromRedisLovValue(_lovKey,lang));
                        }                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
public void translateObject(ApActiveIngredientEntity entity, String lang) {
        ApActiveIngredientEntity translated = (ApActiveIngredientEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}