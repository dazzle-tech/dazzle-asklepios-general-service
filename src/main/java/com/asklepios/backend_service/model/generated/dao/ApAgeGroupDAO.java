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
import com.asklepios.backend_service.model.generated.pojo.ApAgeGroup;
import com.asklepios.backend_service.model.generated.entity.ApAgeGroupEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApAgeGroupDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApAgeGroup getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_age_group where key = '"+key+"'");) {
ApAgeGroup record = new ApAgeGroup();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setAgeGroupLkey(rs.getString("age_group_lkey"));
record.setFromAge(rs.getBigDecimal("from_age"));
record.setToAge(rs.getBigDecimal("to_age"));
record.setFromAgeUnitLkey(rs.getString("from_age_unit_lkey"));
record.setToAgeUnitLkey(rs.getString("to_age_unit_lkey"));
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
public void updateRecord(ApAgeGroup record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_age_group set key = ?, age_group_lkey = ?, from_age = ?, to_age = ?, from_age_unit_lkey = ?, to_age_unit_lkey = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getAgeGroupLkey());
ps.setBigDecimal(3, record.getFromAge());
ps.setBigDecimal(4, record.getToAge());
ps.setString(5, record.getFromAgeUnitLkey());
ps.setString(6, record.getToAgeUnitLkey());
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
public void deleteRecord(ApAgeGroup record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_age_group set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApAgeGroup> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_age_group where "+ where);) {
List<ApAgeGroup> list = new ArrayList<ApAgeGroup>();
while(rs.next()){
ApAgeGroup record = new ApAgeGroup();
record.setKey(rs.getString("key"));
record.setAgeGroupLkey(rs.getString("age_group_lkey"));
record.setFromAge(rs.getBigDecimal("from_age"));
record.setToAge(rs.getBigDecimal("to_age"));
record.setFromAgeUnitLkey(rs.getString("from_age_unit_lkey"));
record.setToAgeUnitLkey(rs.getString("to_age_unit_lkey"));
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
public String saveRecord(ApAgeGroup record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_age_group values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getAgeGroupLkey());
ps.setBigDecimal(3, record.getFromAge());
ps.setBigDecimal(4, record.getToAge());
ps.setString(5, record.getFromAgeUnitLkey());
ps.setString(6, record.getToAgeUnitLkey());
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
public void populateLovFields(ApAgeGroupEntity entity, String lang) {
        Class<?> myClass = ApAgeGroupEntity.class;
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
public void translateObject(ApAgeGroupEntity entity, String lang) {
        ApAgeGroupEntity translated = (ApAgeGroupEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}