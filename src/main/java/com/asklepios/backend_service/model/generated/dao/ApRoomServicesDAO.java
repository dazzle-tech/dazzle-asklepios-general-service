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
import com.asklepios.backend_service.model.generated.pojo.ApRoomServices;
import com.asklepios.backend_service.model.generated.entity.ApRoomServicesEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApRoomServicesDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApRoomServices getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_room_services where key = '"+key+"'");) {
ApRoomServices record = new ApRoomServices();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setRoomKey(rs.getString("room_key"));
record.setPrice(rs.getBigDecimal("price"));
record.setBedKey(rs.getString("bed_key"));
record.setRule(rs.getString("rule"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setCurrencyLkey(rs.getString("currency_lkey"));
record.setServiceKey(rs.getString("service_key"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApRoomServices record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_room_services set key = ?, room_key = ?, price = ?, bed_key = ?, rule = ?, is_valid = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, currency_lkey = ?, service_key = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getRoomKey());
ps.setBigDecimal(3, record.getPrice());
ps.setString(4, record.getBedKey());
ps.setString(5, record.getRule());
ps.setBoolean(6, record.getIsValid());
ps.setString(7, record.getCreatedBy());
ps.setString(8, record.getUpdatedBy());
ps.setString(9, record.getDeletedBy());
ps.setBigDecimal(10, record.getCreatedAt());
ps.setBigDecimal(11, record.getUpdatedAt());
ps.setBigDecimal(12, record.getDeletedAt());
ps.setString(13, record.getCurrencyLkey());
ps.setString(14, record.getServiceKey());
ps.setString(15, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApRoomServices record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_room_services set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApRoomServices> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_room_services where "+ where);) {
List<ApRoomServices> list = new ArrayList<ApRoomServices>();
while(rs.next()){
ApRoomServices record = new ApRoomServices();
record.setKey(rs.getString("key"));
record.setRoomKey(rs.getString("room_key"));
record.setPrice(rs.getBigDecimal("price"));
record.setBedKey(rs.getString("bed_key"));
record.setRule(rs.getString("rule"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setCurrencyLkey(rs.getString("currency_lkey"));
record.setServiceKey(rs.getString("service_key"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApRoomServices record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_room_services values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getRoomKey());
ps.setBigDecimal(3, record.getPrice());
ps.setString(4, record.getBedKey());
ps.setString(5, record.getRule());
ps.setBoolean(6, record.getIsValid());
ps.setString(7, record.getCreatedBy());
ps.setString(8, record.getUpdatedBy());
ps.setString(9, record.getDeletedBy());
ps.setBigDecimal(10, record.getCreatedAt());
ps.setBigDecimal(11, record.getUpdatedAt());
ps.setBigDecimal(12, record.getDeletedAt());
ps.setString(13, record.getCurrencyLkey());
ps.setString(14, record.getServiceKey());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApRoomServicesEntity entity, String lang) {
        Class<?> myClass = ApRoomServicesEntity.class;
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
public void translateObject(ApRoomServicesEntity entity, String lang) {
        ApRoomServicesEntity translated = (ApRoomServicesEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}