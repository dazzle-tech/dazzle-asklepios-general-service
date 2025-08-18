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
import com.asklepios.backend_service.model.generated.pojo.ApInventoryTransactionProduct;
import com.asklepios.backend_service.model.generated.entity.ApInventoryTransactionProductEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApInventoryTransactionProductDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApInventoryTransactionProduct getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_inventory_transaction_product where key = '"+key+"'");) {
ApInventoryTransactionProduct record = new ApInventoryTransactionProduct();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setInventoryTransKey(rs.getString("inventory_trans_key"));
record.setProductKey(rs.getString("product_key"));
record.setNewQuentity(rs.getBigDecimal("new_quentity"));
record.setLotserialnumber(rs.getString("lotserialnumber"));
record.setNewCost(rs.getBigDecimal("new_cost"));
record.setCurrencyLkey(rs.getString("currency_lkey"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setIsEffectedWarehouse(rs.getBoolean("is_effected_warehouse"));
record.setExpiryDate(rs.getDate("expiry_date"));
record.setNotes(rs.getString("notes"));
record.setTransUomKey(rs.getString("trans_uom_key"));
record.setNewQuentityBaseUom(rs.getBigDecimal("new_quentity_base_uom"));
record.setTotalCost(rs.getBigDecimal("total_cost"));
record.setNewAvgCost(rs.getBigDecimal("new_avg_cost"));
record.setOldAvgCost(rs.getBigDecimal("old_avg_cost"));
record.setStatusLkey(rs.getString("status_lkey"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApInventoryTransactionProduct record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_inventory_transaction_product set key = ?, inventory_trans_key = ?, product_key = ?, new_quentity = ?, lotserialnumber = ?, new_cost = ?, currency_lkey = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ?, is_effected_warehouse = ?, expiry_date = ?, notes = ?, trans_uom_key = ?, new_quentity_base_uom = ?, total_cost = ?, new_avg_cost = ?, old_avg_cost = ?, status_lkey = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getInventoryTransKey());
ps.setString(3, record.getProductKey());
ps.setBigDecimal(4, record.getNewQuentity());
ps.setString(5, record.getLotserialnumber());
ps.setBigDecimal(6, record.getNewCost());
ps.setString(7, record.getCurrencyLkey());
ps.setString(8, record.getCreatedBy());
ps.setString(9, record.getUpdatedBy());
ps.setString(10, record.getDeletedBy());
ps.setBigDecimal(11, record.getCreatedAt());
ps.setBigDecimal(12, record.getUpdatedAt());
ps.setBigDecimal(13, record.getDeletedAt());
ps.setBoolean(14, record.getIsValid());
ps.setBoolean(15, record.getIsEffectedWarehouse());
if (record.getExpiryDate() != null) ps.setDate(16, new java.sql.Date(record.getExpiryDate().getTime()));
else ps.setDate(16, null); 
ps.setString(17, record.getNotes());
ps.setString(18, record.getTransUomKey());
ps.setBigDecimal(19, record.getNewQuentityBaseUom());
ps.setBigDecimal(20, record.getTotalCost());
ps.setBigDecimal(21, record.getNewAvgCost());
ps.setBigDecimal(22, record.getOldAvgCost());
ps.setString(23, record.getStatusLkey());
ps.setString(24, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApInventoryTransactionProduct record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_inventory_transaction_product set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApInventoryTransactionProduct> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_inventory_transaction_product where "+ where);) {
List<ApInventoryTransactionProduct> list = new ArrayList<ApInventoryTransactionProduct>();
while(rs.next()){
ApInventoryTransactionProduct record = new ApInventoryTransactionProduct();
record.setKey(rs.getString("key"));
record.setInventoryTransKey(rs.getString("inventory_trans_key"));
record.setProductKey(rs.getString("product_key"));
record.setNewQuentity(rs.getBigDecimal("new_quentity"));
record.setLotserialnumber(rs.getString("lotserialnumber"));
record.setNewCost(rs.getBigDecimal("new_cost"));
record.setCurrencyLkey(rs.getString("currency_lkey"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setIsEffectedWarehouse(rs.getBoolean("is_effected_warehouse"));
record.setExpiryDate(rs.getDate("expiry_date"));
record.setNotes(rs.getString("notes"));
record.setTransUomKey(rs.getString("trans_uom_key"));
record.setNewQuentityBaseUom(rs.getBigDecimal("new_quentity_base_uom"));
record.setTotalCost(rs.getBigDecimal("total_cost"));
record.setNewAvgCost(rs.getBigDecimal("new_avg_cost"));
record.setOldAvgCost(rs.getBigDecimal("old_avg_cost"));
record.setStatusLkey(rs.getString("status_lkey"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApInventoryTransactionProduct record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_inventory_transaction_product values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getInventoryTransKey());
ps.setString(3, record.getProductKey());
ps.setBigDecimal(4, record.getNewQuentity());
ps.setString(5, record.getLotserialnumber());
ps.setBigDecimal(6, record.getNewCost());
ps.setString(7, record.getCurrencyLkey());
ps.setString(8, record.getCreatedBy());
ps.setString(9, record.getUpdatedBy());
ps.setString(10, record.getDeletedBy());
ps.setBigDecimal(11, record.getCreatedAt());
ps.setBigDecimal(12, record.getUpdatedAt());
ps.setBigDecimal(13, record.getDeletedAt());
ps.setBoolean(14, record.getIsValid());
ps.setBoolean(15, record.getIsEffectedWarehouse());
if (record.getExpiryDate() != null) ps.setDate(16, new java.sql.Date(record.getExpiryDate().getTime()));
else ps.setDate(16, null); 
ps.setString(17, record.getNotes());
ps.setString(18, record.getTransUomKey());
ps.setBigDecimal(19, record.getNewQuentityBaseUom());
ps.setBigDecimal(20, record.getTotalCost());
ps.setBigDecimal(21, record.getNewAvgCost());
ps.setBigDecimal(22, record.getOldAvgCost());
ps.setString(23, record.getStatusLkey());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApInventoryTransactionProductEntity entity, String lang) {
        Class<?> myClass = ApInventoryTransactionProductEntity.class;
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
public void translateObject(ApInventoryTransactionProductEntity entity, String lang) {
        ApInventoryTransactionProductEntity translated = (ApInventoryTransactionProductEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}