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
import com.asklepios.backend_service.model.generated.pojo.ApLov;
import com.asklepios.backend_service.model.generated.entity.ApLovEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApLovDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApLov getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_lov where key = '"+key+"'");) {
ApLov record = new ApLov();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setLovCode(rs.getString("lov_code"));
record.setLovName(rs.getString("lov_name"));
record.setLovDescription(rs.getString("lov_description"));
record.setLoveCustomCode(rs.getString("love_custom_code"));
record.setParentLov(rs.getString("parent_lov"));
record.setAutoSelectDefault(rs.getBoolean("auto_select_default"));
record.setDefaultValueId(rs.getString("default_value_id"));
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
public void updateRecord(ApLov record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_lov set key = ?, lov_code = ?, lov_name = ?, lov_description = ?, love_custom_code = ?, parent_lov = ?, auto_select_default = ?, default_value_id = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getLovCode());
ps.setString(3, record.getLovName());
ps.setString(4, record.getLovDescription());
ps.setString(5, record.getLoveCustomCode());
ps.setString(6, record.getParentLov());
ps.setBoolean(7, record.getAutoSelectDefault());
ps.setString(8, record.getDefaultValueId());
ps.setString(9, record.getCreatedBy());
ps.setString(10, record.getUpdatedBy());
ps.setString(11, record.getDeletedBy());
ps.setBigDecimal(12, record.getCreatedAt());
ps.setBigDecimal(13, record.getUpdatedAt());
ps.setBigDecimal(14, record.getDeletedAt());
ps.setBoolean(15, record.getIsValid());
ps.setString(16, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApLov record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_lov set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApLov> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_lov where "+ where);) {
List<ApLov> list = new ArrayList<ApLov>();
while(rs.next()){
ApLov record = new ApLov();
record.setKey(rs.getString("key"));
record.setLovCode(rs.getString("lov_code"));
record.setLovName(rs.getString("lov_name"));
record.setLovDescription(rs.getString("lov_description"));
record.setLoveCustomCode(rs.getString("love_custom_code"));
record.setParentLov(rs.getString("parent_lov"));
record.setAutoSelectDefault(rs.getBoolean("auto_select_default"));
record.setDefaultValueId(rs.getString("default_value_id"));
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
public String saveRecord(ApLov record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_lov values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getLovCode());
ps.setString(3, record.getLovName());
ps.setString(4, record.getLovDescription());
ps.setString(5, record.getLoveCustomCode());
ps.setString(6, record.getParentLov());
ps.setBoolean(7, record.getAutoSelectDefault());
ps.setString(8, record.getDefaultValueId());
ps.setString(9, record.getCreatedBy());
ps.setString(10, record.getUpdatedBy());
ps.setString(11, record.getDeletedBy());
ps.setBigDecimal(12, record.getCreatedAt());
ps.setBigDecimal(13, record.getUpdatedAt());
ps.setBigDecimal(14, record.getDeletedAt());
ps.setBoolean(15, record.getIsValid());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApLovEntity entity, String lang) {
        Class<?> myClass = ApLovEntity.class;
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
public void translateObject(ApLovEntity entity, String lang) {
        ApLovEntity translated = (ApLovEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}