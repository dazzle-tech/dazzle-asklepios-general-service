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
import com.asklepios.backend_service.model.generated.pojo.ApActiveIngredientAdverseEffect;
import com.asklepios.backend_service.model.generated.entity.ApActiveIngredientAdverseEffectEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApActiveIngredientAdverseEffectDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApActiveIngredientAdverseEffect getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_active_ingredient_adverse_effect where key = '"+key+"'");) {
ApActiveIngredientAdverseEffect record = new ApActiveIngredientAdverseEffect();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setActiveIngredientKey(rs.getString("active_ingredient_key"));
record.setAdverseEffectLkey(rs.getString("adverse_effect_lkey"));
record.setIsOther(rs.getBoolean("is_other"));
record.setOtherDescription(rs.getString("other_description"));
record.setTypeLkey(rs.getString("type_lkey"));
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
public void updateRecord(ApActiveIngredientAdverseEffect record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_active_ingredient_adverse_effect set key = ?, active_ingredient_key = ?, adverse_effect_lkey = ?, is_other = ?, other_description = ?, type_lkey = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getActiveIngredientKey());
ps.setString(3, record.getAdverseEffectLkey());
ps.setBoolean(4, record.getIsOther());
ps.setString(5, record.getOtherDescription());
ps.setString(6, record.getTypeLkey());
ps.setString(7, record.getCreatedBy());
ps.setString(8, record.getUpdatedBy());
ps.setString(9, record.getDeletedBy());
ps.setBigDecimal(10, record.getCreatedAt());
ps.setBigDecimal(11, record.getUpdatedAt());
ps.setBigDecimal(12, record.getDeletedAt());
ps.setBoolean(13, record.getIsValid());
ps.setString(14, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApActiveIngredientAdverseEffect record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_active_ingredient_adverse_effect set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApActiveIngredientAdverseEffect> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_active_ingredient_adverse_effect where "+ where);) {
List<ApActiveIngredientAdverseEffect> list = new ArrayList<ApActiveIngredientAdverseEffect>();
while(rs.next()){
ApActiveIngredientAdverseEffect record = new ApActiveIngredientAdverseEffect();
record.setKey(rs.getString("key"));
record.setActiveIngredientKey(rs.getString("active_ingredient_key"));
record.setAdverseEffectLkey(rs.getString("adverse_effect_lkey"));
record.setIsOther(rs.getBoolean("is_other"));
record.setOtherDescription(rs.getString("other_description"));
record.setTypeLkey(rs.getString("type_lkey"));
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
public String saveRecord(ApActiveIngredientAdverseEffect record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_active_ingredient_adverse_effect values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getActiveIngredientKey());
ps.setString(3, record.getAdverseEffectLkey());
ps.setBoolean(4, record.getIsOther());
ps.setString(5, record.getOtherDescription());
ps.setString(6, record.getTypeLkey());
ps.setString(7, record.getCreatedBy());
ps.setString(8, record.getUpdatedBy());
ps.setString(9, record.getDeletedBy());
ps.setBigDecimal(10, record.getCreatedAt());
ps.setBigDecimal(11, record.getUpdatedAt());
ps.setBigDecimal(12, record.getDeletedAt());
ps.setBoolean(13, record.getIsValid());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApActiveIngredientAdverseEffectEntity entity, String lang) {
        Class<?> myClass = ApActiveIngredientAdverseEffectEntity.class;
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
public void translateObject(ApActiveIngredientAdverseEffectEntity entity, String lang) {
        ApActiveIngredientAdverseEffectEntity translated = (ApActiveIngredientAdverseEffectEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}