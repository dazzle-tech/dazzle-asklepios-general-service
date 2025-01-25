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
import com.asklepios.backend_service.model.generated.pojo.ApBrandMedicationSubstitutes;
import com.asklepios.backend_service.model.generated.entity.ApBrandMedicationSubstitutesEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApBrandMedicationSubstitutesDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApBrandMedicationSubstitutes getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_brand_medication_substitutes where key = '"+key+"'");) {
ApBrandMedicationSubstitutes record = new ApBrandMedicationSubstitutes();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setBrandKey(rs.getString("brand_key"));
record.setAlternativeBrandKey(rs.getString("alternative_brand_key"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setDescription(rs.getString("description"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApBrandMedicationSubstitutes record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_brand_medication_substitutes set key = ?, brand_key = ?, alternative_brand_key = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, description = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getBrandKey());
ps.setString(3, record.getAlternativeBrandKey());
ps.setString(4, record.getCreatedBy());
ps.setString(5, record.getUpdatedBy());
ps.setString(6, record.getDeletedBy());
ps.setBigDecimal(7, record.getCreatedAt());
ps.setBigDecimal(8, record.getUpdatedAt());
ps.setBigDecimal(9, record.getDeletedAt());
ps.setString(10, record.getDescription());
ps.setString(11, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApBrandMedicationSubstitutes record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_brand_medication_substitutes set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApBrandMedicationSubstitutes> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_brand_medication_substitutes where "+ where);) {
List<ApBrandMedicationSubstitutes> list = new ArrayList<ApBrandMedicationSubstitutes>();
while(rs.next()){
ApBrandMedicationSubstitutes record = new ApBrandMedicationSubstitutes();
record.setKey(rs.getString("key"));
record.setBrandKey(rs.getString("brand_key"));
record.setAlternativeBrandKey(rs.getString("alternative_brand_key"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setDescription(rs.getString("description"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApBrandMedicationSubstitutes record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_brand_medication_substitutes values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getBrandKey());
ps.setString(3, record.getAlternativeBrandKey());
ps.setString(4, record.getCreatedBy());
ps.setString(5, record.getUpdatedBy());
ps.setString(6, record.getDeletedBy());
ps.setBigDecimal(7, record.getCreatedAt());
ps.setBigDecimal(8, record.getUpdatedAt());
ps.setBigDecimal(9, record.getDeletedAt());
ps.setString(10, record.getDescription());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApBrandMedicationSubstitutesEntity entity, String lang) {
        Class<?> myClass = ApBrandMedicationSubstitutesEntity.class;
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
public void translateObject(ApBrandMedicationSubstitutesEntity entity, String lang) {
        ApBrandMedicationSubstitutesEntity translated = (ApBrandMedicationSubstitutesEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}