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
import com.asklepios.backend_service.model.generated.pojo.ApService;
import com.asklepios.backend_service.model.generated.entity.ApServiceEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApServiceDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApService getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_service where key = '"+key+"'");) {
ApService record = new ApService();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setTypeLkey(rs.getString("type_lkey"));
record.setName(rs.getString("name"));
record.setAbbreviation(rs.getString("abbreviation"));
record.setCode(rs.getString("code"));
record.setCategoryLkey(rs.getString("category_lkey"));
record.setPrice(rs.getBigDecimal("price"));
record.setCurrencyLkey(rs.getString("currency_lkey"));
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
public void updateRecord(ApService record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_service set key = ?, type_lkey = ?, name = ?, abbreviation = ?, code = ?, category_lkey = ?, price = ?, currency_lkey = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getTypeLkey());
ps.setString(3, record.getName());
ps.setString(4, record.getAbbreviation());
ps.setString(5, record.getCode());
ps.setString(6, record.getCategoryLkey());
ps.setBigDecimal(7, record.getPrice());
ps.setString(8, record.getCurrencyLkey());
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
public void deleteRecord(ApService record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_service set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApService> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_service where "+ where);) {
List<ApService> list = new ArrayList<ApService>();
while(rs.next()){
ApService record = new ApService();
record.setKey(rs.getString("key"));
record.setTypeLkey(rs.getString("type_lkey"));
record.setName(rs.getString("name"));
record.setAbbreviation(rs.getString("abbreviation"));
record.setCode(rs.getString("code"));
record.setCategoryLkey(rs.getString("category_lkey"));
record.setPrice(rs.getBigDecimal("price"));
record.setCurrencyLkey(rs.getString("currency_lkey"));
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
public String saveRecord(ApService record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_service values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getTypeLkey());
ps.setString(3, record.getName());
ps.setString(4, record.getAbbreviation());
ps.setString(5, record.getCode());
ps.setString(6, record.getCategoryLkey());
ps.setBigDecimal(7, record.getPrice());
ps.setString(8, record.getCurrencyLkey());
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
public void populateLovFields(ApServiceEntity entity, String lang) {
        Class<?> myClass = ApServiceEntity.class;
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
public void translateObject(ApServiceEntity entity, String lang) {
        ApServiceEntity translated = (ApServiceEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}