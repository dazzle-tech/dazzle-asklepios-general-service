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
import com.asklepios.backend_service.model.generated.pojo.ApWarehouseProduct;
import com.asklepios.backend_service.model.generated.entity.ApWarehouseProductEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApWarehouseProductDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApWarehouseProduct getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_warehouse_product where key = '"+key+"'");) {
ApWarehouseProduct record = new ApWarehouseProduct();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setWarehouseKey(rs.getString("warehouse_key"));
record.setProductKey(rs.getString("product_key"));
record.setQuantity(rs.getBigDecimal("quantity"));
record.setReOrderQuantity(rs.getBigDecimal("re_order_quantity"));
record.setMiniOrder(rs.getBigDecimal("mini_order"));
record.setMaxOrder(rs.getBigDecimal("max_order"));
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
public void updateRecord(ApWarehouseProduct record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_warehouse_product set key = ?, warehouse_key = ?, product_key = ?, quantity = ?, re_order_quantity = ?, mini_order = ?, max_order = ?, working_hours_from_time = ?, working_hours_to_time = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, isvalid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getWarehouseKey());
ps.setString(3, record.getProductKey());
ps.setBigDecimal(4, record.getQuantity());
ps.setBigDecimal(5, record.getReOrderQuantity());
ps.setBigDecimal(6, record.getMiniOrder());
ps.setBigDecimal(7, record.getMaxOrder());
ps.setBigDecimal(8, record.getWorkingHoursFromTime());
ps.setBigDecimal(9, record.getWorkingHoursToTime());
ps.setString(10, record.getCreatedBy());
ps.setString(11, record.getUpdatedBy());
ps.setString(12, record.getDeletedBy());
ps.setBigDecimal(13, record.getCreatedAt());
ps.setBigDecimal(14, record.getUpdatedAt());
ps.setBigDecimal(15, record.getDeletedAt());
ps.setBoolean(16, record.getIsvalid());
ps.setString(17, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApWarehouseProduct record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_warehouse_product set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApWarehouseProduct> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_warehouse_product where "+ where);) {
List<ApWarehouseProduct> list = new ArrayList<ApWarehouseProduct>();
while(rs.next()){
ApWarehouseProduct record = new ApWarehouseProduct();
record.setKey(rs.getString("key"));
record.setWarehouseKey(rs.getString("warehouse_key"));
record.setProductKey(rs.getString("product_key"));
record.setQuantity(rs.getBigDecimal("quantity"));
record.setReOrderQuantity(rs.getBigDecimal("re_order_quantity"));
record.setMiniOrder(rs.getBigDecimal("mini_order"));
record.setMaxOrder(rs.getBigDecimal("max_order"));
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
public String saveRecord(ApWarehouseProduct record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_warehouse_product values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getWarehouseKey());
ps.setString(3, record.getProductKey());
ps.setBigDecimal(4, record.getQuantity());
ps.setBigDecimal(5, record.getReOrderQuantity());
ps.setBigDecimal(6, record.getMiniOrder());
ps.setBigDecimal(7, record.getMaxOrder());
ps.setBigDecimal(8, record.getWorkingHoursFromTime());
ps.setBigDecimal(9, record.getWorkingHoursToTime());
ps.setString(10, record.getCreatedBy());
ps.setString(11, record.getUpdatedBy());
ps.setString(12, record.getDeletedBy());
ps.setBigDecimal(13, record.getCreatedAt());
ps.setBigDecimal(14, record.getUpdatedAt());
ps.setBigDecimal(15, record.getDeletedAt());
ps.setBoolean(16, record.getIsvalid());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApWarehouseProductEntity entity, String lang) {
        Class<?> myClass = ApWarehouseProductEntity.class;
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
public void translateObject(ApWarehouseProductEntity entity, String lang) {
        ApWarehouseProductEntity translated = (ApWarehouseProductEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}