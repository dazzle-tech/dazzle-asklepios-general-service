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
import com.asklepios.backend_service.model.generated.pojo.ApLovValues;
import com.asklepios.backend_service.model.generated.entity.ApLovValuesEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApLovValuesDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApLovValues getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_lov_values where key = '"+key+"'");) {
ApLovValues record = new ApLovValues();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setLovKey(rs.getString("lov_key"));
record.setLovCode(rs.getString("lov_code"));
record.setValueCode(rs.getString("value_code"));
record.setLovDisplayVale(rs.getString("lov_display_vale"));
record.setLoveCustomCode(rs.getString("love_custom_code"));
record.setValueDescription(rs.getString("value_description"));
record.setValueColor(rs.getString("value_color"));
record.setValueIcon(rs.getString("value_icon"));
record.setValueOrder(rs.getBigDecimal("value_order"));
record.setIsdefault(rs.getBoolean("isdefault"));
record.setSeededData(rs.getBoolean("seeded_data"));
record.setForInternalUser(rs.getBoolean("for_internal_user"));
record.setSpecificForScreenId(rs.getString("specific_for_screen_id"));
record.setParentValueId(rs.getString("parent_value_id"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setScore(rs.getBigDecimal("score"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApLovValues record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_lov_values set key = ?, lov_key = ?, lov_code = ?, value_code = ?, lov_display_vale = ?, love_custom_code = ?, value_description = ?, value_color = ?, value_icon = ?, value_order = ?, isdefault = ?, seeded_data = ?, for_internal_user = ?, specific_for_screen_id = ?, parent_value_id = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ?, score = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getLovKey());
ps.setString(3, record.getLovCode());
ps.setString(4, record.getValueCode());
ps.setString(5, record.getLovDisplayVale());
ps.setString(6, record.getLoveCustomCode());
ps.setString(7, record.getValueDescription());
ps.setString(8, record.getValueColor());
ps.setString(9, record.getValueIcon());
ps.setBigDecimal(10, record.getValueOrder());
ps.setBoolean(11, record.getIsdefault());
ps.setBoolean(12, record.getSeededData());
ps.setBoolean(13, record.getForInternalUser());
ps.setString(14, record.getSpecificForScreenId());
ps.setString(15, record.getParentValueId());
ps.setString(16, record.getCreatedBy());
ps.setString(17, record.getUpdatedBy());
ps.setString(18, record.getDeletedBy());
ps.setBigDecimal(19, record.getCreatedAt());
ps.setBigDecimal(20, record.getUpdatedAt());
ps.setBigDecimal(21, record.getDeletedAt());
ps.setBoolean(22, record.getIsValid());
ps.setBigDecimal(23, record.getScore());
ps.setString(24, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApLovValues record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_lov_values set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApLovValues> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_lov_values where "+ where);) {
List<ApLovValues> list = new ArrayList<ApLovValues>();
while(rs.next()){
ApLovValues record = new ApLovValues();
record.setKey(rs.getString("key"));
record.setLovKey(rs.getString("lov_key"));
record.setLovCode(rs.getString("lov_code"));
record.setValueCode(rs.getString("value_code"));
record.setLovDisplayVale(rs.getString("lov_display_vale"));
record.setLoveCustomCode(rs.getString("love_custom_code"));
record.setValueDescription(rs.getString("value_description"));
record.setValueColor(rs.getString("value_color"));
record.setValueIcon(rs.getString("value_icon"));
record.setValueOrder(rs.getBigDecimal("value_order"));
record.setIsdefault(rs.getBoolean("isdefault"));
record.setSeededData(rs.getBoolean("seeded_data"));
record.setForInternalUser(rs.getBoolean("for_internal_user"));
record.setSpecificForScreenId(rs.getString("specific_for_screen_id"));
record.setParentValueId(rs.getString("parent_value_id"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setScore(rs.getBigDecimal("score"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApLovValues record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_lov_values values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getLovKey());
ps.setString(3, record.getLovCode());
ps.setString(4, record.getValueCode());
ps.setString(5, record.getLovDisplayVale());
ps.setString(6, record.getLoveCustomCode());
ps.setString(7, record.getValueDescription());
ps.setString(8, record.getValueColor());
ps.setString(9, record.getValueIcon());
ps.setBigDecimal(10, record.getValueOrder());
ps.setBoolean(11, record.getIsdefault());
ps.setBoolean(12, record.getSeededData());
ps.setBoolean(13, record.getForInternalUser());
ps.setString(14, record.getSpecificForScreenId());
ps.setString(15, record.getParentValueId());
ps.setString(16, record.getCreatedBy());
ps.setString(17, record.getUpdatedBy());
ps.setString(18, record.getDeletedBy());
ps.setBigDecimal(19, record.getCreatedAt());
ps.setBigDecimal(20, record.getUpdatedAt());
ps.setBigDecimal(21, record.getDeletedAt());
ps.setBoolean(22, record.getIsValid());
ps.setBigDecimal(23, record.getScore());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApLovValuesEntity entity, String lang) {
        Class<?> myClass = ApLovValuesEntity.class;
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
public void translateObject(ApLovValuesEntity entity, String lang) {
        ApLovValuesEntity translated = (ApLovValuesEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}