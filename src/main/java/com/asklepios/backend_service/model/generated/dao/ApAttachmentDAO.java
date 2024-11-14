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
import com.asklepios.backend_service.model.generated.pojo.ApAttachment;
import com.asklepios.backend_service.model.generated.entity.ApAttachmentEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApAttachmentDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApAttachment getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_attachment where key = '"+key+"'");) {
ApAttachment record = new ApAttachment();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setAttachmentType(rs.getString("attachment_type"));
record.setReferenceObjectKey(rs.getString("reference_object_key"));
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
} else { record = null; }
return record;
}
}
public void updateRecord(ApAttachment record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_attachment set key = ?, attachment_type = ?, reference_object_key = ?, extra_details = ?, file_name = ?, content_type = ?, file_content = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getAttachmentType());
ps.setString(3, record.getReferenceObjectKey());
ps.setString(4, record.getExtraDetails());
ps.setString(5, record.getFileName());
ps.setString(6, record.getContentType());
ps.setBytes(7, record.getFileContent());
ps.setString(8, record.getCreatedBy());
ps.setString(9, record.getUpdatedBy());
ps.setString(10, record.getDeletedBy());
ps.setBigDecimal(11, record.getCreatedAt());
ps.setBigDecimal(12, record.getUpdatedAt());
ps.setBigDecimal(13, record.getDeletedAt());
ps.setBoolean(14, record.getIsValid());
ps.setString(15, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApAttachment record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_attachment set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApAttachment> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_attachment where "+ where);) {
List<ApAttachment> list = new ArrayList<ApAttachment>();
while(rs.next()){
ApAttachment record = new ApAttachment();
record.setKey(rs.getString("key"));
record.setAttachmentType(rs.getString("attachment_type"));
record.setReferenceObjectKey(rs.getString("reference_object_key"));
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
list.add(record);
}
return list;
}
}
public String saveRecord(ApAttachment record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_attachment values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getAttachmentType());
ps.setString(3, record.getReferenceObjectKey());
ps.setString(4, record.getExtraDetails());
ps.setString(5, record.getFileName());
ps.setString(6, record.getContentType());
ps.setBytes(7, record.getFileContent());
ps.setString(8, record.getCreatedBy());
ps.setString(9, record.getUpdatedBy());
ps.setString(10, record.getDeletedBy());
ps.setBigDecimal(11, record.getCreatedAt());
ps.setBigDecimal(12, record.getUpdatedAt());
ps.setBigDecimal(13, record.getDeletedAt());
ps.setBoolean(14, record.getIsValid());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApAttachmentEntity entity, String lang) {
        Class<?> myClass = ApAttachmentEntity.class;
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
public void translateObject(ApAttachmentEntity entity, String lang) {
        ApAttachmentEntity translated = (ApAttachmentEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}