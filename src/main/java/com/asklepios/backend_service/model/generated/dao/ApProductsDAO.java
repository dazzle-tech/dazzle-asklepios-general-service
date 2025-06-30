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
import com.asklepios.backend_service.model.generated.pojo.ApProducts;
import com.asklepios.backend_service.model.generated.entity.ApProductsEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApProductsDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApProducts getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_products where key = '"+key+"'");) {
ApProducts record = new ApProducts();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setTypeLkey(rs.getString("type_lkey"));
record.setName(rs.getString("name"));
record.setMedicationKey(rs.getString("medication_key"));
record.setCode(rs.getString("code"));
record.setBarecode(rs.getString("barecode"));
record.setUomGroupKey(rs.getString("uom_group_key"));
record.setBaseUomKey(rs.getString("base_uom_key"));
record.setDispenseUomKey(rs.getString("dispense_uom_key"));
record.setIsBatchManaged(rs.getBoolean("is_batch_managed"));
record.setIsExpiryDateMandatory(rs.getBoolean("is_expiry_date_mandatory"));
record.setIsSerialized(rs.getBoolean("is_serialized"));
record.setIsReusable(rs.getBoolean("is_reusable"));
record.setInventoryTypeLkey(rs.getString("inventory_type_lkey"));
record.setAtcCode(rs.getString("atc_code"));
record.setShelfLife(rs.getBigDecimal("shelf_life"));
record.setLeadTime(rs.getBigDecimal("lead_time"));
record.setErpIntegId(rs.getString("erp_integ_id"));
record.setStartDate(rs.getDate("start_date"));
record.setEndDate(rs.getDate("end_date"));
record.setMaintenanceScheduleTime(rs.getBigDecimal("maintenance_schedule_time"));
record.setMaintenanceScheduleLkey(rs.getString("maintenance_schedule_lkey"));
record.setIsCritical(rs.getBoolean("is_critical"));
record.setIsCalibration(rs.getBoolean("is_calibration"));
record.setIsTraining(rs.getBoolean("is_training"));
record.setAvgCost(rs.getBigDecimal("avg_cost"));
record.setPriceBaseUom(rs.getString("price_base_uom"));
record.setIsControlledSubstance(rs.getBoolean("is_controlled_substance"));
record.setIsAllergyRisk(rs.getBoolean("is_allergy_risk"));
record.setHazardousTag(rs.getString("hazardous_tag"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApProducts record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_products set key = ?, type_lkey = ?, name = ?, medication_key = ?, code = ?, barecode = ?, uom_group_key = ?, base_uom_key = ?, dispense_uom_key = ?, is_batch_managed = ?, is_expiry_date_mandatory = ?, is_serialized = ?, is_reusable = ?, inventory_type_lkey = ?, atc_code = ?, shelf_life = ?, lead_time = ?, erp_integ_id = ?, start_date = ?, end_date = ?, maintenance_schedule_time = ?, maintenance_schedule_lkey = ?, is_critical = ?, is_calibration = ?, is_training = ?, avg_cost = ?, price_base_uom = ?, is_controlled_substance = ?, is_allergy_risk = ?, hazardous_tag = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getTypeLkey());
ps.setString(3, record.getName());
ps.setString(4, record.getMedicationKey());
ps.setString(5, record.getCode());
ps.setString(6, record.getBarecode());
ps.setString(7, record.getUomGroupKey());
ps.setString(8, record.getBaseUomKey());
ps.setString(9, record.getDispenseUomKey());
ps.setBoolean(10, record.getIsBatchManaged());
ps.setBoolean(11, record.getIsExpiryDateMandatory());
ps.setBoolean(12, record.getIsSerialized());
ps.setBoolean(13, record.getIsReusable());
ps.setString(14, record.getInventoryTypeLkey());
ps.setString(15, record.getAtcCode());
ps.setBigDecimal(16, record.getShelfLife());
ps.setBigDecimal(17, record.getLeadTime());
ps.setString(18, record.getErpIntegId());
if (record.getStartDate() != null) ps.setDate(19, new java.sql.Date(record.getStartDate().getTime()));
else ps.setDate(19, null); 
if (record.getEndDate() != null) ps.setDate(20, new java.sql.Date(record.getEndDate().getTime()));
else ps.setDate(20, null); 
ps.setBigDecimal(21, record.getMaintenanceScheduleTime());
ps.setString(22, record.getMaintenanceScheduleLkey());
ps.setBoolean(23, record.getIsCritical());
ps.setBoolean(24, record.getIsCalibration());
ps.setBoolean(25, record.getIsTraining());
ps.setBigDecimal(26, record.getAvgCost());
ps.setString(27, record.getPriceBaseUom());
ps.setBoolean(28, record.getIsControlledSubstance());
ps.setBoolean(29, record.getIsAllergyRisk());
ps.setString(30, record.getHazardousTag());
ps.setString(31, record.getCreatedBy());
ps.setString(32, record.getUpdatedBy());
ps.setString(33, record.getDeletedBy());
ps.setBigDecimal(34, record.getCreatedAt());
ps.setBigDecimal(35, record.getUpdatedAt());
ps.setBigDecimal(36, record.getDeletedAt());
ps.setBoolean(37, record.getIsValid());
ps.setString(38, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApProducts record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_products set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApProducts> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_products where "+ where);) {
List<ApProducts> list = new ArrayList<ApProducts>();
while(rs.next()){
ApProducts record = new ApProducts();
record.setKey(rs.getString("key"));
record.setTypeLkey(rs.getString("type_lkey"));
record.setName(rs.getString("name"));
record.setMedicationKey(rs.getString("medication_key"));
record.setCode(rs.getString("code"));
record.setBarecode(rs.getString("barecode"));
record.setUomGroupKey(rs.getString("uom_group_key"));
record.setBaseUomKey(rs.getString("base_uom_key"));
record.setDispenseUomKey(rs.getString("dispense_uom_key"));
record.setIsBatchManaged(rs.getBoolean("is_batch_managed"));
record.setIsExpiryDateMandatory(rs.getBoolean("is_expiry_date_mandatory"));
record.setIsSerialized(rs.getBoolean("is_serialized"));
record.setIsReusable(rs.getBoolean("is_reusable"));
record.setInventoryTypeLkey(rs.getString("inventory_type_lkey"));
record.setAtcCode(rs.getString("atc_code"));
record.setShelfLife(rs.getBigDecimal("shelf_life"));
record.setLeadTime(rs.getBigDecimal("lead_time"));
record.setErpIntegId(rs.getString("erp_integ_id"));
record.setStartDate(rs.getDate("start_date"));
record.setEndDate(rs.getDate("end_date"));
record.setMaintenanceScheduleTime(rs.getBigDecimal("maintenance_schedule_time"));
record.setMaintenanceScheduleLkey(rs.getString("maintenance_schedule_lkey"));
record.setIsCritical(rs.getBoolean("is_critical"));
record.setIsCalibration(rs.getBoolean("is_calibration"));
record.setIsTraining(rs.getBoolean("is_training"));
record.setAvgCost(rs.getBigDecimal("avg_cost"));
record.setPriceBaseUom(rs.getString("price_base_uom"));
record.setIsControlledSubstance(rs.getBoolean("is_controlled_substance"));
record.setIsAllergyRisk(rs.getBoolean("is_allergy_risk"));
record.setHazardousTag(rs.getString("hazardous_tag"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApProducts record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_products values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getTypeLkey());
ps.setString(3, record.getName());
ps.setString(4, record.getMedicationKey());
ps.setString(5, record.getCode());
ps.setString(6, record.getBarecode());
ps.setString(7, record.getUomGroupKey());
ps.setString(8, record.getBaseUomKey());
ps.setString(9, record.getDispenseUomKey());
ps.setBoolean(10, record.getIsBatchManaged());
ps.setBoolean(11, record.getIsExpiryDateMandatory());
ps.setBoolean(12, record.getIsSerialized());
ps.setBoolean(13, record.getIsReusable());
ps.setString(14, record.getInventoryTypeLkey());
ps.setString(15, record.getAtcCode());
ps.setBigDecimal(16, record.getShelfLife());
ps.setBigDecimal(17, record.getLeadTime());
ps.setString(18, record.getErpIntegId());
if (record.getStartDate() != null) ps.setDate(19, new java.sql.Date(record.getStartDate().getTime()));
else ps.setDate(19, null); 
if (record.getEndDate() != null) ps.setDate(20, new java.sql.Date(record.getEndDate().getTime()));
else ps.setDate(20, null); 
ps.setBigDecimal(21, record.getMaintenanceScheduleTime());
ps.setString(22, record.getMaintenanceScheduleLkey());
ps.setBoolean(23, record.getIsCritical());
ps.setBoolean(24, record.getIsCalibration());
ps.setBoolean(25, record.getIsTraining());
ps.setBigDecimal(26, record.getAvgCost());
ps.setString(27, record.getPriceBaseUom());
ps.setBoolean(28, record.getIsControlledSubstance());
ps.setBoolean(29, record.getIsAllergyRisk());
ps.setString(30, record.getHazardousTag());
ps.setString(31, record.getCreatedBy());
ps.setString(32, record.getUpdatedBy());
ps.setString(33, record.getDeletedBy());
ps.setBigDecimal(34, record.getCreatedAt());
ps.setBigDecimal(35, record.getUpdatedAt());
ps.setBigDecimal(36, record.getDeletedAt());
ps.setBoolean(37, record.getIsValid());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApProductsEntity entity, String lang) {
        Class<?> myClass = ApProductsEntity.class;
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
public void translateObject(ApProductsEntity entity, String lang) {
        ApProductsEntity translated = (ApProductsEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}