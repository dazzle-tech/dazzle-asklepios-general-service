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
import com.asklepios.backend_service.model.generated.pojo.ApGlobalSettings;
import com.asklepios.backend_service.model.generated.entity.ApGlobalSettingsEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApGlobalSettingsDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApGlobalSettings getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_global_settings where key = '"+key+"'");) {
ApGlobalSettings record = new ApGlobalSettings();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setFacilityKey(rs.getString("facility_key"));
record.setSettingKey(rs.getString("setting_key"));
record.setSettingValue(rs.getString("setting_value"));
record.setSettingCategory(rs.getString("setting_category"));
record.setRequireRestart(rs.getBoolean("require_restart"));
record.setRequirePasscode(rs.getBoolean("require_passcode"));
record.setFixedValue(rs.getBoolean("fixed_value"));
record.setForAdminUse(rs.getBoolean("for_admin_use"));
record.setHiddenSetting(rs.getBoolean("hidden_setting"));
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
public void updateRecord(ApGlobalSettings record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_global_settings set key = ?, facility_key = ?, setting_key = ?, setting_value = ?, setting_category = ?, require_restart = ?, require_passcode = ?, fixed_value = ?, for_admin_use = ?, hidden_setting = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getFacilityKey());
ps.setString(3, record.getSettingKey());
ps.setString(4, record.getSettingValue());
ps.setString(5, record.getSettingCategory());
ps.setBoolean(6, record.getRequireRestart());
ps.setBoolean(7, record.getRequirePasscode());
ps.setBoolean(8, record.getFixedValue());
ps.setBoolean(9, record.getForAdminUse());
ps.setBoolean(10, record.getHiddenSetting());
ps.setString(11, record.getCreatedBy());
ps.setString(12, record.getUpdatedBy());
ps.setString(13, record.getDeletedBy());
ps.setBigDecimal(14, record.getCreatedAt());
ps.setBigDecimal(15, record.getUpdatedAt());
ps.setBigDecimal(16, record.getDeletedAt());
ps.setBoolean(17, record.getIsValid());
ps.setString(18, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApGlobalSettings record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_global_settings set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApGlobalSettings> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_global_settings where "+ where);) {
List<ApGlobalSettings> list = new ArrayList<ApGlobalSettings>();
while(rs.next()){
ApGlobalSettings record = new ApGlobalSettings();
record.setKey(rs.getString("key"));
record.setFacilityKey(rs.getString("facility_key"));
record.setSettingKey(rs.getString("setting_key"));
record.setSettingValue(rs.getString("setting_value"));
record.setSettingCategory(rs.getString("setting_category"));
record.setRequireRestart(rs.getBoolean("require_restart"));
record.setRequirePasscode(rs.getBoolean("require_passcode"));
record.setFixedValue(rs.getBoolean("fixed_value"));
record.setForAdminUse(rs.getBoolean("for_admin_use"));
record.setHiddenSetting(rs.getBoolean("hidden_setting"));
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
public String saveRecord(ApGlobalSettings record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_global_settings values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getFacilityKey());
ps.setString(3, record.getSettingKey());
ps.setString(4, record.getSettingValue());
ps.setString(5, record.getSettingCategory());
ps.setBoolean(6, record.getRequireRestart());
ps.setBoolean(7, record.getRequirePasscode());
ps.setBoolean(8, record.getFixedValue());
ps.setBoolean(9, record.getForAdminUse());
ps.setBoolean(10, record.getHiddenSetting());
ps.setString(11, record.getCreatedBy());
ps.setString(12, record.getUpdatedBy());
ps.setString(13, record.getDeletedBy());
ps.setBigDecimal(14, record.getCreatedAt());
ps.setBigDecimal(15, record.getUpdatedAt());
ps.setBigDecimal(16, record.getDeletedAt());
ps.setBoolean(17, record.getIsValid());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApGlobalSettingsEntity entity, String lang) {
        Class<?> myClass = ApGlobalSettingsEntity.class;
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
public void translateObject(ApGlobalSettingsEntity entity, String lang) {
        ApGlobalSettingsEntity translated = (ApGlobalSettingsEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}