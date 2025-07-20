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
import com.asklepios.backend_service.model.generated.pojo.ApInventoryTransactionAttachment;
import com.asklepios.backend_service.model.generated.entity.ApInventoryTransactionAttachmentEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApInventoryTransactionAttachmentDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApInventoryTransactionAttachment getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_inventory_transaction_attachment where key = '"+key+"'");) {
ApInventoryTransactionAttachment record = new ApInventoryTransactionAttachment();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setAttachmentType(rs.getString("attachment_type"));
record.setExtraDetails(rs.getString("extra_details"));
record.setFileName(rs.getString("file_name"));
record.setContentType(rs.getString("content_type"));
record.setFileContent(rs.getBytes("file_content"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setDetails(rs.getString("details"));
record.setAccessTypeLkey(rs.getString("access_type_lkey"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApInventoryTransactionAttachment record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_inventory_transaction_attachment set key = ?, attachment_type = ?, extra_details = ?, file_name = ?, content_type = ?, file_content = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ?, details = ?, access_type_lkey = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getAttachmentType());
ps.setString(3, record.getExtraDetails());
ps.setString(4, record.getFileName());
ps.setString(5, record.getContentType());
ps.setBytes(6, record.getFileContent());
ps.setString(7, record.getCreatedBy());
ps.setString(8, record.getUpdatedBy());
ps.setString(9, record.getDeletedBy());
ps.setBigDecimal(10, record.getCreatedAt());
ps.setBigDecimal(11, record.getUpdatedAt());
ps.setBigDecimal(12, record.getDeletedAt());
ps.setBoolean(13, record.getIsValid());
ps.setString(14, record.getDetails());
ps.setString(15, record.getAccessTypeLkey());
ps.setString(16, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApInventoryTransactionAttachment record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_inventory_transaction_attachment set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApInventoryTransactionAttachment> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_inventory_transaction_attachment where "+ where);) {
List<ApInventoryTransactionAttachment> list = new ArrayList<ApInventoryTransactionAttachment>();
while(rs.next()){
ApInventoryTransactionAttachment record = new ApInventoryTransactionAttachment();
record.setKey(rs.getString("key"));
record.setAttachmentType(rs.getString("attachment_type"));
record.setExtraDetails(rs.getString("extra_details"));
record.setFileName(rs.getString("file_name"));
record.setContentType(rs.getString("content_type"));
record.setFileContent(rs.getBytes("file_content"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setDetails(rs.getString("details"));
record.setAccessTypeLkey(rs.getString("access_type_lkey"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApInventoryTransactionAttachment record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_inventory_transaction_attachment values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getAttachmentType());
ps.setString(3, record.getExtraDetails());
ps.setString(4, record.getFileName());
ps.setString(5, record.getContentType());
ps.setBytes(6, record.getFileContent());
ps.setString(7, record.getCreatedBy());
ps.setString(8, record.getUpdatedBy());
ps.setString(9, record.getDeletedBy());
ps.setBigDecimal(10, record.getCreatedAt());
ps.setBigDecimal(11, record.getUpdatedAt());
ps.setBigDecimal(12, record.getDeletedAt());
ps.setBoolean(13, record.getIsValid());
ps.setString(14, record.getDetails());
ps.setString(15, record.getAccessTypeLkey());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApInventoryTransactionAttachmentEntity entity, String lang) {
        Class<?> myClass = ApInventoryTransactionAttachmentEntity.class;
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
public void translateObject(ApInventoryTransactionAttachmentEntity entity, String lang) {
        ApInventoryTransactionAttachmentEntity translated = (ApInventoryTransactionAttachmentEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}