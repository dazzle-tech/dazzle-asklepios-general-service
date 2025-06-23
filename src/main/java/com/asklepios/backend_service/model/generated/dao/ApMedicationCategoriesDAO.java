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
import com.asklepios.backend_service.model.generated.pojo.ApMedicationCategories;
import com.asklepios.backend_service.model.generated.entity.ApMedicationCategoriesEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApMedicationCategoriesDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApMedicationCategories getRecord(String key) throws SQLException {
try (

Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_medication_categories where key = '"+key+"'");) {
ApMedicationCategories record = new ApMedicationCategories();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setMedCategoriesName(rs.getString("med_categories_name"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApMedicationCategories record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_medication_categories set key = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ?, med_categories_name = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getCreatedBy());
ps.setString(3, record.getUpdatedBy());
ps.setString(4, record.getDeletedBy());
ps.setBigDecimal(5, record.getCreatedAt());
ps.setBigDecimal(6, record.getUpdatedAt());
ps.setBigDecimal(7, record.getDeletedAt());
ps.setBoolean(8, record.getIsValid());
ps.setString(9, record.getMedCategoriesName());
ps.setString(10, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApMedicationCategories record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_medication_categories set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApMedicationCategories> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_medication_categories where "+ where);) {
List<ApMedicationCategories> list = new ArrayList<ApMedicationCategories>();
while(rs.next()){
ApMedicationCategories record = new ApMedicationCategories();
record.setKey(rs.getString("key"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setMedCategoriesName(rs.getString("med_categories_name"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApMedicationCategories record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_medication_categories values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getCreatedBy());
ps.setString(3, record.getUpdatedBy());
ps.setString(4, record.getDeletedBy());
ps.setBigDecimal(5, record.getCreatedAt());
ps.setBigDecimal(6, record.getUpdatedAt());
ps.setBigDecimal(7, record.getDeletedAt());
ps.setBoolean(8, record.getIsValid());
ps.setString(9, record.getMedCategoriesName());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApMedicationCategoriesEntity entity, String lang) {
        Class<?> myClass = ApMedicationCategoriesEntity.class;
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
public void translateObject(ApMedicationCategoriesEntity entity, String lang) {
        ApMedicationCategoriesEntity translated = (ApMedicationCategoriesEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}