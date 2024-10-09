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
import com.asklepios.backend_service.model.generated.pojo.ApMessages;
import com.asklepios.backend_service.model.generated.entity.ApMessagesEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApMessagesDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApMessages getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_messages where key = '"+key+"'");) {
ApMessages record = new ApMessages();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setMessageId(rs.getString("message_id"));
record.setMessageCode(rs.getString("message_code"));
record.setMessageType(rs.getString("message_type"));
record.setMessageHeader(rs.getString("message_header"));
record.setMessageText(rs.getString("message_text"));
record.setLanguageCode(rs.getString("language_code"));
record.setIsoriginalMessage(rs.getBoolean("isoriginal_message"));
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
public void updateRecord(ApMessages record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_messages set key = ?, message_id = ?, message_code = ?, message_type = ?, message_header = ?, message_text = ?, language_code = ?, isoriginal_message = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getMessageId());
ps.setString(3, record.getMessageCode());
ps.setString(4, record.getMessageType());
ps.setString(5, record.getMessageHeader());
ps.setString(6, record.getMessageText());
ps.setString(7, record.getLanguageCode());
ps.setBoolean(8, record.getIsoriginalMessage());
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
public void deleteRecord(ApMessages record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_messages set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApMessages> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_messages where "+ where);) {
List<ApMessages> list = new ArrayList<ApMessages>();
while(rs.next()){
ApMessages record = new ApMessages();
record.setKey(rs.getString("key"));
record.setMessageId(rs.getString("message_id"));
record.setMessageCode(rs.getString("message_code"));
record.setMessageType(rs.getString("message_type"));
record.setMessageHeader(rs.getString("message_header"));
record.setMessageText(rs.getString("message_text"));
record.setLanguageCode(rs.getString("language_code"));
record.setIsoriginalMessage(rs.getBoolean("isoriginal_message"));
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
public String saveRecord(ApMessages record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_messages values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getMessageId());
ps.setString(3, record.getMessageCode());
ps.setString(4, record.getMessageType());
ps.setString(5, record.getMessageHeader());
ps.setString(6, record.getMessageText());
ps.setString(7, record.getLanguageCode());
ps.setBoolean(8, record.getIsoriginalMessage());
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
public void populateLovFields(ApMessagesEntity entity, String lang) {
        Class<?> myClass = ApMessagesEntity.class;
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
public void translateObject(ApMessagesEntity entity, String lang) {
        ApMessagesEntity translated = (ApMessagesEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}