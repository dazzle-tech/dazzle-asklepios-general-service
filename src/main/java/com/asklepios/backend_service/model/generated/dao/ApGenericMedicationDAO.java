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
import com.asklepios.backend_service.model.generated.pojo.ApGenericMedication;
import com.asklepios.backend_service.model.generated.entity.ApGenericMedicationEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApGenericMedicationDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApGenericMedication getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_generic_medication where key = '"+key+"'");) {
ApGenericMedication record = new ApGenericMedication();
if(rs.next()){
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
} else { record = null; }
return record;
}
}
public void updateRecord(ApGenericMedication record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_generic_medication set key = ?, generic_name = ?, manufacturer_lkey = ?, usage_instructions = ?, dosage_form_lkey = ?, expires_after_opening = ?, expires_after_opening_value = ?, single_patient_use = ?, price = ?, currency_lkey = ?, price_list_key = ?, cost = ?, storage_requirements = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ?, code = ?, roa_lkey = ?, marketing_authorization_holder = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getGenericName());
ps.setString(3, record.getManufacturerLkey());
ps.setString(4, record.getUsageInstructions());
ps.setString(5, record.getDosageFormLkey());
ps.setBoolean(6, record.getExpiresAfterOpening());
ps.setString(7, record.getExpiresAfterOpeningValue());
ps.setBoolean(8, record.getSinglePatientUse());
ps.setBigDecimal(9, record.getPrice());
ps.setString(10, record.getCurrencyLkey());
ps.setString(11, record.getPriceListKey());
ps.setBigDecimal(12, record.getCost());
ps.setString(13, record.getStorageRequirements());
ps.setString(14, record.getCreatedBy());
ps.setString(15, record.getUpdatedBy());
ps.setString(16, record.getDeletedBy());
ps.setBigDecimal(17, record.getCreatedAt());
ps.setBigDecimal(18, record.getUpdatedAt());
ps.setBigDecimal(19, record.getDeletedAt());
ps.setBoolean(20, record.getIsValid());
ps.setString(21, record.getCode());
ps.setString(22, record.getRoaLkey());
ps.setString(23, record.getMarketingAuthorizationHolder());
ps.setString(24, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApGenericMedication record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_generic_medication set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApGenericMedication> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_generic_medication where "+ where);) {
List<ApGenericMedication> list = new ArrayList<ApGenericMedication>();
while(rs.next()){
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
return list;
}
}
public String saveRecord(ApGenericMedication record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_generic_medication values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getGenericName());
ps.setString(3, record.getManufacturerLkey());
ps.setString(4, record.getUsageInstructions());
ps.setString(5, record.getDosageFormLkey());
ps.setBoolean(6, record.getExpiresAfterOpening());
ps.setString(7, record.getExpiresAfterOpeningValue());
ps.setBoolean(8, record.getSinglePatientUse());
ps.setBigDecimal(9, record.getPrice());
ps.setString(10, record.getCurrencyLkey());
ps.setString(11, record.getPriceListKey());
ps.setBigDecimal(12, record.getCost());
ps.setString(13, record.getStorageRequirements());
ps.setString(14, record.getCreatedBy());
ps.setString(15, record.getUpdatedBy());
ps.setString(16, record.getDeletedBy());
ps.setBigDecimal(17, record.getCreatedAt());
ps.setBigDecimal(18, record.getUpdatedAt());
ps.setBigDecimal(19, record.getDeletedAt());
ps.setBoolean(20, record.getIsValid());
ps.setString(21, record.getCode());
ps.setString(22, record.getRoaLkey());
ps.setString(23, record.getMarketingAuthorizationHolder());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApGenericMedicationEntity entity, String lang) {
        Class<?> myClass = ApGenericMedicationEntity.class;
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
public void translateObject(ApGenericMedicationEntity entity, String lang) {
        ApGenericMedicationEntity translated = (ApGenericMedicationEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}