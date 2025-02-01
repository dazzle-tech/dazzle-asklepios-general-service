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
import com.asklepios.backend_service.model.generated.pojo.ApActiveIngredientIndication;
import com.asklepios.backend_service.model.generated.entity.ApActiveIngredientIndicationEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApActiveIngredientIndicationDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApActiveIngredientIndication getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_active_ingredient_indication where key = '"+key+"'");) {
ApActiveIngredientIndication record = new ApActiveIngredientIndication();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setActiveIngredientKey(rs.getString("active_ingredient_key"));
record.setIndication(rs.getString("indication"));
record.setIsOffLabel(rs.getBoolean("is_off_label"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setIcdCodeKey(rs.getString("icd_code_key"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApActiveIngredientIndication record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_active_ingredient_indication set key = ?, active_ingredient_key = ?, indication = ?, is_off_label = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ?, icd_code_key = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getActiveIngredientKey());
ps.setString(3, record.getIndication());
ps.setBoolean(4, record.getIsOffLabel());
ps.setString(5, record.getCreatedBy());
ps.setString(6, record.getUpdatedBy());
ps.setString(7, record.getDeletedBy());
ps.setBigDecimal(8, record.getCreatedAt());
ps.setBigDecimal(9, record.getUpdatedAt());
ps.setBigDecimal(10, record.getDeletedAt());
ps.setBoolean(11, record.getIsValid());
ps.setString(12, record.getIcdCodeKey());
ps.setString(13, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApActiveIngredientIndication record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_active_ingredient_indication set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApActiveIngredientIndication> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_active_ingredient_indication where "+ where);) {
List<ApActiveIngredientIndication> list = new ArrayList<ApActiveIngredientIndication>();
while(rs.next()){
ApActiveIngredientIndication record = new ApActiveIngredientIndication();
record.setKey(rs.getString("key"));
record.setActiveIngredientKey(rs.getString("active_ingredient_key"));
record.setIndication(rs.getString("indication"));
record.setIsOffLabel(rs.getBoolean("is_off_label"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setIcdCodeKey(rs.getString("icd_code_key"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApActiveIngredientIndication record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_active_ingredient_indication values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getActiveIngredientKey());
ps.setString(3, record.getIndication());
ps.setBoolean(4, record.getIsOffLabel());
ps.setString(5, record.getCreatedBy());
ps.setString(6, record.getUpdatedBy());
ps.setString(7, record.getDeletedBy());
ps.setBigDecimal(8, record.getCreatedAt());
ps.setBigDecimal(9, record.getUpdatedAt());
ps.setBigDecimal(10, record.getDeletedAt());
ps.setBoolean(11, record.getIsValid());
ps.setString(12, record.getIcdCodeKey());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApActiveIngredientIndicationEntity entity, String lang) {
        Class<?> myClass = ApActiveIngredientIndicationEntity.class;
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
public void translateObject(ApActiveIngredientIndicationEntity entity, String lang) {
        ApActiveIngredientIndicationEntity translated = (ApActiveIngredientIndicationEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}