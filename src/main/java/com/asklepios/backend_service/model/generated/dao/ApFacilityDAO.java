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
import com.asklepios.backend_service.model.generated.pojo.ApFacility;
import com.asklepios.backend_service.model.generated.entity.ApFacilityEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApFacilityDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApFacility getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_facility where key = '"+key+"'");) {
ApFacility record = new ApFacility();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setFacilityId(rs.getString("facility_id"));
record.setFacilityName(rs.getString("facility_name"));
record.setFacilityNameOtherLang(rs.getString("facility_name_other_lang"));
record.setTenantId(rs.getString("tenant_id"));
record.setFacilityType(rs.getString("facility_type"));
record.setFacilityRegistrationDate(rs.getDate("facility_registration_date"));
record.setFacilityEmailAddress(rs.getString("facility_email_address"));
record.setFacilityBriefDesc(rs.getString("facility_brief_desc"));
record.setFacilityAddress(rs.getString("facility_address"));
record.setFacilityAddressOtherLang(rs.getString("facility_address_other_lang"));
record.setFacilityLogoFile(rs.getString("facility_logo_file"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setFacilityTypeLkey(rs.getString("facility_type_lkey"));
record.setFacilityAddressId(rs.getString("facility_address_id"));
record.setFacilityPhone1(rs.getString("facility_phone1"));
record.setFacilityPhone2(rs.getString("facility_phone2"));
record.setFacilityFax(rs.getString("facility_fax"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApFacility record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_facility set key = ?, facility_id = ?, facility_name = ?, facility_name_other_lang = ?, tenant_id = ?, facility_type = ?, facility_registration_date = ?, facility_email_address = ?, facility_brief_desc = ?, facility_address = ?, facility_address_other_lang = ?, facility_logo_file = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ?, facility_type_lkey = ?, facility_address_id = ?, facility_phone1 = ?, facility_phone2 = ?, facility_fax = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getFacilityId());
ps.setString(3, record.getFacilityName());
ps.setString(4, record.getFacilityNameOtherLang());
ps.setString(5, record.getTenantId());
ps.setString(6, record.getFacilityType());
if (record.getFacilityRegistrationDate() != null) ps.setDate(7, new java.sql.Date(record.getFacilityRegistrationDate().getTime()));
else ps.setDate(7, null); 
ps.setString(8, record.getFacilityEmailAddress());
ps.setString(9, record.getFacilityBriefDesc());
ps.setString(10, record.getFacilityAddress());
ps.setString(11, record.getFacilityAddressOtherLang());
ps.setString(12, record.getFacilityLogoFile());
ps.setString(13, record.getCreatedBy());
ps.setString(14, record.getUpdatedBy());
ps.setString(15, record.getDeletedBy());
ps.setBigDecimal(16, record.getCreatedAt());
ps.setBigDecimal(17, record.getUpdatedAt());
ps.setBigDecimal(18, record.getDeletedAt());
ps.setBoolean(19, record.getIsValid());
ps.setString(20, record.getFacilityTypeLkey());
ps.setString(21, record.getFacilityAddressId());
ps.setString(22, record.getFacilityPhone1());
ps.setString(23, record.getFacilityPhone2());
ps.setString(24, record.getFacilityFax());
ps.setString(25, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApFacility record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_facility set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApFacility> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_facility where "+ where);) {
List<ApFacility> list = new ArrayList<ApFacility>();
while(rs.next()){
ApFacility record = new ApFacility();
record.setKey(rs.getString("key"));
record.setFacilityId(rs.getString("facility_id"));
record.setFacilityName(rs.getString("facility_name"));
record.setFacilityNameOtherLang(rs.getString("facility_name_other_lang"));
record.setTenantId(rs.getString("tenant_id"));
record.setFacilityType(rs.getString("facility_type"));
record.setFacilityRegistrationDate(rs.getDate("facility_registration_date"));
record.setFacilityEmailAddress(rs.getString("facility_email_address"));
record.setFacilityBriefDesc(rs.getString("facility_brief_desc"));
record.setFacilityAddress(rs.getString("facility_address"));
record.setFacilityAddressOtherLang(rs.getString("facility_address_other_lang"));
record.setFacilityLogoFile(rs.getString("facility_logo_file"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setFacilityTypeLkey(rs.getString("facility_type_lkey"));
record.setFacilityAddressId(rs.getString("facility_address_id"));
record.setFacilityPhone1(rs.getString("facility_phone1"));
record.setFacilityPhone2(rs.getString("facility_phone2"));
record.setFacilityFax(rs.getString("facility_fax"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApFacility record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_facility values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getFacilityId());
ps.setString(3, record.getFacilityName());
ps.setString(4, record.getFacilityNameOtherLang());
ps.setString(5, record.getTenantId());
ps.setString(6, record.getFacilityType());
if (record.getFacilityRegistrationDate() != null) ps.setDate(7, new java.sql.Date(record.getFacilityRegistrationDate().getTime()));
else ps.setDate(7, null); 
ps.setString(8, record.getFacilityEmailAddress());
ps.setString(9, record.getFacilityBriefDesc());
ps.setString(10, record.getFacilityAddress());
ps.setString(11, record.getFacilityAddressOtherLang());
ps.setString(12, record.getFacilityLogoFile());
ps.setString(13, record.getCreatedBy());
ps.setString(14, record.getUpdatedBy());
ps.setString(15, record.getDeletedBy());
ps.setBigDecimal(16, record.getCreatedAt());
ps.setBigDecimal(17, record.getUpdatedAt());
ps.setBigDecimal(18, record.getDeletedAt());
ps.setBoolean(19, record.getIsValid());
ps.setString(20, record.getFacilityTypeLkey());
ps.setString(21, record.getFacilityAddressId());
ps.setString(22, record.getFacilityPhone1());
ps.setString(23, record.getFacilityPhone2());
ps.setString(24, record.getFacilityFax());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApFacilityEntity entity, String lang) {
        Class<?> myClass = ApFacilityEntity.class;
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
public void translateObject(ApFacilityEntity entity, String lang) {
        ApFacilityEntity translated = (ApFacilityEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}