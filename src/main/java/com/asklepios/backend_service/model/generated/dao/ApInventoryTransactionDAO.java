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
import com.asklepios.backend_service.model.generated.pojo.ApInventoryTransaction;
import com.asklepios.backend_service.model.generated.entity.ApInventoryTransactionEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApInventoryTransactionDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApInventoryTransaction getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_inventory_transaction where key = '"+key+"'");) {
ApInventoryTransaction record = new ApInventoryTransaction();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setTransTypeLkey(rs.getString("trans_type_lkey"));
record.setWarehouseKey(rs.getString("warehouse_key"));
record.setTransReasonLkey(rs.getString("trans_reason_lkey"));
record.setRemarks(rs.getString("remarks"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setTransId(rs.getString("trans_id"));
record.setDocNum(rs.getBigDecimal("doc_num"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApInventoryTransaction record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_inventory_transaction set key = ?, trans_type_lkey = ?, warehouse_key = ?, trans_reason_lkey = ?, remarks = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ?, trans_id = ?, doc_num = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getTransTypeLkey());
ps.setString(3, record.getWarehouseKey());
ps.setString(4, record.getTransReasonLkey());
ps.setString(5, record.getRemarks());
ps.setString(6, record.getCreatedBy());
ps.setString(7, record.getUpdatedBy());
ps.setString(8, record.getDeletedBy());
ps.setBigDecimal(9, record.getCreatedAt());
ps.setBigDecimal(10, record.getUpdatedAt());
ps.setBigDecimal(11, record.getDeletedAt());
ps.setBoolean(12, record.getIsValid());
ps.setString(13, record.getTransId());
ps.setBigDecimal(14, record.getDocNum());
ps.setString(15, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApInventoryTransaction record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_inventory_transaction set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApInventoryTransaction> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_inventory_transaction where "+ where);) {
List<ApInventoryTransaction> list = new ArrayList<ApInventoryTransaction>();
while(rs.next()){
ApInventoryTransaction record = new ApInventoryTransaction();
record.setKey(rs.getString("key"));
record.setTransTypeLkey(rs.getString("trans_type_lkey"));
record.setWarehouseKey(rs.getString("warehouse_key"));
record.setTransReasonLkey(rs.getString("trans_reason_lkey"));
record.setRemarks(rs.getString("remarks"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setTransId(rs.getString("trans_id"));
record.setDocNum(rs.getBigDecimal("doc_num"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApInventoryTransaction record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_inventory_transaction values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getTransTypeLkey());
ps.setString(3, record.getWarehouseKey());
ps.setString(4, record.getTransReasonLkey());
ps.setString(5, record.getRemarks());
ps.setString(6, record.getCreatedBy());
ps.setString(7, record.getUpdatedBy());
ps.setString(8, record.getDeletedBy());
ps.setBigDecimal(9, record.getCreatedAt());
ps.setBigDecimal(10, record.getUpdatedAt());
ps.setBigDecimal(11, record.getDeletedAt());
ps.setBoolean(12, record.getIsValid());
ps.setString(13, record.getTransId());
ps.setBigDecimal(14, record.getDocNum());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApInventoryTransactionEntity entity, String lang) {
        Class<?> myClass = ApInventoryTransactionEntity.class;
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
public void translateObject(ApInventoryTransactionEntity entity, String lang) {
        ApInventoryTransactionEntity translated = (ApInventoryTransactionEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}