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
import com.asklepios.backend_service.model.generated.pojo.ApWarehouse;
import com.asklepios.backend_service.model.generated.entity.ApWarehouseEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApWarehouseDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApWarehouse getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_warehouse where key = '"+key+"'");) {
ApWarehouse record = new ApWarehouse();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setDepartmentKey(rs.getString("department_key"));
record.setWarehouseName(rs.getString("warehouse_name"));
record.setWarehouseId(rs.getString("warehouse_id"));
record.setIsdefault(rs.getBoolean("isdefault"));
record.setCloseWarehouse(rs.getBoolean("close_warehouse"));
record.setLocationKey(rs.getString("location_key"));
record.setCapacity(rs.getString("capacity"));
record.setWorkingHoursFromTime(rs.getBigDecimal("working_hours_from_time"));
record.setWorkingHoursToTime(rs.getBigDecimal("working_hours_to_time"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsvalid(rs.getBoolean("isvalid"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApWarehouse record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_warehouse set key = ?, department_key = ?, warehouse_name = ?, warehouse_id = ?, isdefault = ?, close_warehouse = ?, location_key = ?, capacity = ?, working_hours_from_time = ?, working_hours_to_time = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, isvalid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getDepartmentKey());
ps.setString(3, record.getWarehouseName());
ps.setString(4, record.getWarehouseId());
ps.setBoolean(5, record.getIsdefault());
ps.setBoolean(6, record.getCloseWarehouse());
ps.setString(7, record.getLocationKey());
ps.setString(8, record.getCapacity());
ps.setBigDecimal(9, record.getWorkingHoursFromTime());
ps.setBigDecimal(10, record.getWorkingHoursToTime());
ps.setString(11, record.getCreatedBy());
ps.setString(12, record.getUpdatedBy());
ps.setString(13, record.getDeletedBy());
ps.setBigDecimal(14, record.getCreatedAt());
ps.setBigDecimal(15, record.getUpdatedAt());
ps.setBigDecimal(16, record.getDeletedAt());
ps.setBoolean(17, record.getIsvalid());
ps.setString(18, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApWarehouse record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_warehouse set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApWarehouse> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_warehouse where "+ where);) {
List<ApWarehouse> list = new ArrayList<ApWarehouse>();
while(rs.next()){
ApWarehouse record = new ApWarehouse();
record.setKey(rs.getString("key"));
record.setDepartmentKey(rs.getString("department_key"));
record.setWarehouseName(rs.getString("warehouse_name"));
record.setWarehouseId(rs.getString("warehouse_id"));
record.setIsdefault(rs.getBoolean("isdefault"));
record.setCloseWarehouse(rs.getBoolean("close_warehouse"));
record.setLocationKey(rs.getString("location_key"));
record.setCapacity(rs.getString("capacity"));
record.setWorkingHoursFromTime(rs.getBigDecimal("working_hours_from_time"));
record.setWorkingHoursToTime(rs.getBigDecimal("working_hours_to_time"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsvalid(rs.getBoolean("isvalid"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApWarehouse record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_warehouse values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getDepartmentKey());
ps.setString(3, record.getWarehouseName());
ps.setString(4, record.getWarehouseId());
ps.setBoolean(5, record.getIsdefault());
ps.setBoolean(6, record.getCloseWarehouse());
ps.setString(7, record.getLocationKey());
ps.setString(8, record.getCapacity());
ps.setBigDecimal(9, record.getWorkingHoursFromTime());
ps.setBigDecimal(10, record.getWorkingHoursToTime());
ps.setString(11, record.getCreatedBy());
ps.setString(12, record.getUpdatedBy());
ps.setString(13, record.getDeletedBy());
ps.setBigDecimal(14, record.getCreatedAt());
ps.setBigDecimal(15, record.getUpdatedAt());
ps.setBigDecimal(16, record.getDeletedAt());
ps.setBoolean(17, record.getIsvalid());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApWarehouseEntity entity, String lang) {
        Class<?> myClass = ApWarehouseEntity.class;
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
public void translateObject(ApWarehouseEntity entity, String lang) {
        ApWarehouseEntity translated = (ApWarehouseEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}