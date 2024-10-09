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
import com.asklepios.backend_service.model.generated.pojo.ApTenant;
import com.asklepios.backend_service.model.generated.entity.ApTenantEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApTenantDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApTenant getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_tenant where key = '"+key+"'");) {
ApTenant record = new ApTenant();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setTenantId(rs.getString("tenant_id"));
record.setTenantName(rs.getString("tenant_name"));
record.setTenantType(rs.getString("tenant_type"));
record.setTenantRegistrationDate(rs.getDate("tenant_registration_date"));
record.setTenantExpiryDate(rs.getDate("tenant_expiry_date"));
record.setTenantEmailAddress(rs.getString("tenant_email_address"));
record.setTenantBriefDesc(rs.getString("tenant_brief_desc"));
record.setTenantSecurityToken(rs.getString("tenant_security_token"));
record.setTenantDataGlobal(rs.getBoolean("tenant_data_global"));
record.setTenantSchemaName(rs.getString("tenant_schema_name"));
record.setTenantDbConnstr(rs.getString("tenant_db_connstr"));
record.setTenantDbAdminUser(rs.getString("tenant_db_admin_user"));
record.setTenantLogoPath(rs.getString("tenant_logo_path"));
record.setTenantBackgroundPath(rs.getString("tenant_background_path"));
record.setTenantSlogan(rs.getString("tenant_slogan"));
record.setTenantLoginText(rs.getString("tenant_login_text"));
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
public void updateRecord(ApTenant record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_tenant set key = ?, tenant_id = ?, tenant_name = ?, tenant_type = ?, tenant_registration_date = ?, tenant_expiry_date = ?, tenant_email_address = ?, tenant_brief_desc = ?, tenant_security_token = ?, tenant_data_global = ?, tenant_schema_name = ?, tenant_db_connstr = ?, tenant_db_admin_user = ?, tenant_logo_path = ?, tenant_background_path = ?, tenant_slogan = ?, tenant_login_text = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getTenantId());
ps.setString(3, record.getTenantName());
ps.setString(4, record.getTenantType());
if (record.getTenantRegistrationDate() != null) ps.setDate(5, new java.sql.Date(record.getTenantRegistrationDate().getTime()));
else ps.setDate(5, null); 
if (record.getTenantExpiryDate() != null) ps.setDate(6, new java.sql.Date(record.getTenantExpiryDate().getTime()));
else ps.setDate(6, null); 
ps.setString(7, record.getTenantEmailAddress());
ps.setString(8, record.getTenantBriefDesc());
ps.setString(9, record.getTenantSecurityToken());
ps.setBoolean(10, record.getTenantDataGlobal());
ps.setString(11, record.getTenantSchemaName());
ps.setString(12, record.getTenantDbConnstr());
ps.setString(13, record.getTenantDbAdminUser());
ps.setString(14, record.getTenantLogoPath());
ps.setString(15, record.getTenantBackgroundPath());
ps.setString(16, record.getTenantSlogan());
ps.setString(17, record.getTenantLoginText());
ps.setString(18, record.getCreatedBy());
ps.setString(19, record.getUpdatedBy());
ps.setString(20, record.getDeletedBy());
ps.setBigDecimal(21, record.getCreatedAt());
ps.setBigDecimal(22, record.getUpdatedAt());
ps.setBigDecimal(23, record.getDeletedAt());
ps.setBoolean(24, record.getIsValid());
ps.setString(25, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApTenant record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_tenant set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApTenant> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_tenant where "+ where);) {
List<ApTenant> list = new ArrayList<ApTenant>();
while(rs.next()){
ApTenant record = new ApTenant();
record.setKey(rs.getString("key"));
record.setTenantId(rs.getString("tenant_id"));
record.setTenantName(rs.getString("tenant_name"));
record.setTenantType(rs.getString("tenant_type"));
record.setTenantRegistrationDate(rs.getDate("tenant_registration_date"));
record.setTenantExpiryDate(rs.getDate("tenant_expiry_date"));
record.setTenantEmailAddress(rs.getString("tenant_email_address"));
record.setTenantBriefDesc(rs.getString("tenant_brief_desc"));
record.setTenantSecurityToken(rs.getString("tenant_security_token"));
record.setTenantDataGlobal(rs.getBoolean("tenant_data_global"));
record.setTenantSchemaName(rs.getString("tenant_schema_name"));
record.setTenantDbConnstr(rs.getString("tenant_db_connstr"));
record.setTenantDbAdminUser(rs.getString("tenant_db_admin_user"));
record.setTenantLogoPath(rs.getString("tenant_logo_path"));
record.setTenantBackgroundPath(rs.getString("tenant_background_path"));
record.setTenantSlogan(rs.getString("tenant_slogan"));
record.setTenantLoginText(rs.getString("tenant_login_text"));
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
public String saveRecord(ApTenant record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_tenant values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getTenantId());
ps.setString(3, record.getTenantName());
ps.setString(4, record.getTenantType());
if (record.getTenantRegistrationDate() != null) ps.setDate(5, new java.sql.Date(record.getTenantRegistrationDate().getTime()));
else ps.setDate(5, null); 
if (record.getTenantExpiryDate() != null) ps.setDate(6, new java.sql.Date(record.getTenantExpiryDate().getTime()));
else ps.setDate(6, null); 
ps.setString(7, record.getTenantEmailAddress());
ps.setString(8, record.getTenantBriefDesc());
ps.setString(9, record.getTenantSecurityToken());
ps.setBoolean(10, record.getTenantDataGlobal());
ps.setString(11, record.getTenantSchemaName());
ps.setString(12, record.getTenantDbConnstr());
ps.setString(13, record.getTenantDbAdminUser());
ps.setString(14, record.getTenantLogoPath());
ps.setString(15, record.getTenantBackgroundPath());
ps.setString(16, record.getTenantSlogan());
ps.setString(17, record.getTenantLoginText());
ps.setString(18, record.getCreatedBy());
ps.setString(19, record.getUpdatedBy());
ps.setString(20, record.getDeletedBy());
ps.setBigDecimal(21, record.getCreatedAt());
ps.setBigDecimal(22, record.getUpdatedAt());
ps.setBigDecimal(23, record.getDeletedAt());
ps.setBoolean(24, record.getIsValid());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApTenantEntity entity, String lang) {
        Class<?> myClass = ApTenantEntity.class;
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
public void translateObject(ApTenantEntity entity, String lang) {
        ApTenantEntity translated = (ApTenantEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}