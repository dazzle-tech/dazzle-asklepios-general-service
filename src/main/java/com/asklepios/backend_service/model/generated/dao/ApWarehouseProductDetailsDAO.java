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
import com.asklepios.backend_service.model.generated.pojo.ApWarehouseProductDetails;
import com.asklepios.backend_service.model.generated.entity.ApWarehouseProductDetailsEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApWarehouseProductDetailsDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApWarehouseProductDetails getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_warehouse_product_details where key = '"+key+"'");) {
ApWarehouseProductDetails record = new ApWarehouseProductDetails();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setWarehouseProductKey(rs.getString("warehouse_product_key"));
record.setLotSerialNum(rs.getString("lot_serial_num"));
record.setQuantity(rs.getBigDecimal("quantity"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsvalid(rs.getBoolean("isvalid"));
record.setExpiryDate(rs.getDate("expiry_date"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApWarehouseProductDetails record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_warehouse_product_details set key = ?, warehouse_product_key = ?, lot_serial_num = ?, quantity = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, isvalid = ?, expiry_date = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getWarehouseProductKey());
ps.setString(3, record.getLotSerialNum());
ps.setBigDecimal(4, record.getQuantity());
ps.setString(5, record.getCreatedBy());
ps.setString(6, record.getUpdatedBy());
ps.setString(7, record.getDeletedBy());
ps.setBigDecimal(8, record.getCreatedAt());
ps.setBigDecimal(9, record.getUpdatedAt());
ps.setBigDecimal(10, record.getDeletedAt());
ps.setBoolean(11, record.getIsvalid());
if (record.getExpiryDate() != null) ps.setDate(12, new java.sql.Date(record.getExpiryDate().getTime()));
else ps.setDate(12, null); 
ps.setString(13, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApWarehouseProductDetails record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_warehouse_product_details set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApWarehouseProductDetails> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_warehouse_product_details where "+ where);) {
List<ApWarehouseProductDetails> list = new ArrayList<ApWarehouseProductDetails>();
while(rs.next()){
ApWarehouseProductDetails record = new ApWarehouseProductDetails();
record.setKey(rs.getString("key"));
record.setWarehouseProductKey(rs.getString("warehouse_product_key"));
record.setLotSerialNum(rs.getString("lot_serial_num"));
record.setQuantity(rs.getBigDecimal("quantity"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsvalid(rs.getBoolean("isvalid"));
record.setExpiryDate(rs.getDate("expiry_date"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApWarehouseProductDetails record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_warehouse_product_details values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getWarehouseProductKey());
ps.setString(3, record.getLotSerialNum());
ps.setBigDecimal(4, record.getQuantity());
ps.setString(5, record.getCreatedBy());
ps.setString(6, record.getUpdatedBy());
ps.setString(7, record.getDeletedBy());
ps.setBigDecimal(8, record.getCreatedAt());
ps.setBigDecimal(9, record.getUpdatedAt());
ps.setBigDecimal(10, record.getDeletedAt());
ps.setBoolean(11, record.getIsvalid());
if (record.getExpiryDate() != null) ps.setDate(12, new java.sql.Date(record.getExpiryDate().getTime()));
else ps.setDate(12, null); 
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApWarehouseProductDetailsEntity entity, String lang) {
        Class<?> myClass = ApWarehouseProductDetailsEntity.class;
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
public void translateObject(ApWarehouseProductDetailsEntity entity, String lang) {
        ApWarehouseProductDetailsEntity translated = (ApWarehouseProductDetailsEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}