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
import com.asklepios.backend_service.model.generated.pojo.ApActiveIngredientRecommendedDosage;
import com.asklepios.backend_service.model.generated.entity.ApActiveIngredientRecommendedDosageEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApActiveIngredientRecommendedDosageDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApActiveIngredientRecommendedDosage getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_active_ingredient_recommended_dosage where key = '"+key+"'");) {
ApActiveIngredientRecommendedDosage record = new ApActiveIngredientRecommendedDosage();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setActiveIngredientKey(rs.getString("active_ingredient_key"));
record.setIndicationLkey(rs.getString("indication_lkey"));
record.setVariableLkey(rs.getString("variable_lkey"));
record.setDosage(rs.getString("dosage"));
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
public void updateRecord(ApActiveIngredientRecommendedDosage record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_active_ingredient_recommended_dosage set key = ?, active_ingredient_key = ?, indication_lkey = ?, variable_lkey = ?, dosage = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getActiveIngredientKey());
ps.setString(3, record.getIndicationLkey());
ps.setString(4, record.getVariableLkey());
ps.setString(5, record.getDosage());
ps.setString(6, record.getCreatedBy());
ps.setString(7, record.getUpdatedBy());
ps.setString(8, record.getDeletedBy());
ps.setBigDecimal(9, record.getCreatedAt());
ps.setBigDecimal(10, record.getUpdatedAt());
ps.setBigDecimal(11, record.getDeletedAt());
ps.setBoolean(12, record.getIsValid());
ps.setString(13, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApActiveIngredientRecommendedDosage record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_active_ingredient_recommended_dosage set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApActiveIngredientRecommendedDosage> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_active_ingredient_recommended_dosage where "+ where);) {
List<ApActiveIngredientRecommendedDosage> list = new ArrayList<ApActiveIngredientRecommendedDosage>();
while(rs.next()){
ApActiveIngredientRecommendedDosage record = new ApActiveIngredientRecommendedDosage();
record.setKey(rs.getString("key"));
record.setActiveIngredientKey(rs.getString("active_ingredient_key"));
record.setIndicationLkey(rs.getString("indication_lkey"));
record.setVariableLkey(rs.getString("variable_lkey"));
record.setDosage(rs.getString("dosage"));
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
public String saveRecord(ApActiveIngredientRecommendedDosage record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_active_ingredient_recommended_dosage values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getActiveIngredientKey());
ps.setString(3, record.getIndicationLkey());
ps.setString(4, record.getVariableLkey());
ps.setString(5, record.getDosage());
ps.setString(6, record.getCreatedBy());
ps.setString(7, record.getUpdatedBy());
ps.setString(8, record.getDeletedBy());
ps.setBigDecimal(9, record.getCreatedAt());
ps.setBigDecimal(10, record.getUpdatedAt());
ps.setBigDecimal(11, record.getDeletedAt());
ps.setBoolean(12, record.getIsValid());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApActiveIngredientRecommendedDosageEntity entity, String lang) {
        Class<?> myClass = ApActiveIngredientRecommendedDosageEntity.class;
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
public void translateObject(ApActiveIngredientRecommendedDosageEntity entity, String lang) {
        ApActiveIngredientRecommendedDosageEntity translated = (ApActiveIngredientRecommendedDosageEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}