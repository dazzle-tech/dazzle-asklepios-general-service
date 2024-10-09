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
import com.asklepios.backend_service.model.generated.pojo.ApAddresses;
import com.asklepios.backend_service.model.generated.entity.ApAddressesEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApAddressesDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApAddresses getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_addresses where key = '"+key+"'");) {
ApAddresses record = new ApAddresses();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setEntityId(rs.getString("entity_id"));
record.setEntityTypeLkey(rs.getString("entity_type_lkey"));
record.setAddressTypeLkey(rs.getString("address_type_lkey"));
record.setStreetAddressLine1(rs.getString("street_address_line1"));
record.setStreetAddressLine2(rs.getString("street_address_line2"));
record.setCountryLkey(rs.getString("country_lkey"));
record.setStateProvinceRegionLkey(rs.getString("state_province_region_lkey"));
record.setCityLkey(rs.getString("city_lkey"));
record.setPostalCode(rs.getString("postal_code"));
record.setAdditionalInfo(rs.getString("additional_info"));
record.setLatitude(rs.getString("latitude"));
record.setLongitude(rs.getString("longitude"));
record.setIsActive(rs.getString("is_active"));
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
public void updateRecord(ApAddresses record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_addresses set key = ?, entity_id = ?, entity_type_lkey = ?, address_type_lkey = ?, street_address_line1 = ?, street_address_line2 = ?, country_lkey = ?, state_province_region_lkey = ?, city_lkey = ?, postal_code = ?, additional_info = ?, latitude = ?, longitude = ?, is_active = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getEntityId());
ps.setString(3, record.getEntityTypeLkey());
ps.setString(4, record.getAddressTypeLkey());
ps.setString(5, record.getStreetAddressLine1());
ps.setString(6, record.getStreetAddressLine2());
ps.setString(7, record.getCountryLkey());
ps.setString(8, record.getStateProvinceRegionLkey());
ps.setString(9, record.getCityLkey());
ps.setString(10, record.getPostalCode());
ps.setString(11, record.getAdditionalInfo());
ps.setString(12, record.getLatitude());
ps.setString(13, record.getLongitude());
ps.setString(14, record.getIsActive());
ps.setString(15, record.getCreatedBy());
ps.setString(16, record.getUpdatedBy());
ps.setString(17, record.getDeletedBy());
ps.setBigDecimal(18, record.getCreatedAt());
ps.setBigDecimal(19, record.getUpdatedAt());
ps.setBigDecimal(20, record.getDeletedAt());
ps.setBoolean(21, record.getIsValid());
ps.setString(22, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApAddresses record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_addresses set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApAddresses> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_addresses where "+ where);) {
List<ApAddresses> list = new ArrayList<ApAddresses>();
while(rs.next()){
ApAddresses record = new ApAddresses();
record.setKey(rs.getString("key"));
record.setEntityId(rs.getString("entity_id"));
record.setEntityTypeLkey(rs.getString("entity_type_lkey"));
record.setAddressTypeLkey(rs.getString("address_type_lkey"));
record.setStreetAddressLine1(rs.getString("street_address_line1"));
record.setStreetAddressLine2(rs.getString("street_address_line2"));
record.setCountryLkey(rs.getString("country_lkey"));
record.setStateProvinceRegionLkey(rs.getString("state_province_region_lkey"));
record.setCityLkey(rs.getString("city_lkey"));
record.setPostalCode(rs.getString("postal_code"));
record.setAdditionalInfo(rs.getString("additional_info"));
record.setLatitude(rs.getString("latitude"));
record.setLongitude(rs.getString("longitude"));
record.setIsActive(rs.getString("is_active"));
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
public String saveRecord(ApAddresses record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_addresses values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getEntityId());
ps.setString(3, record.getEntityTypeLkey());
ps.setString(4, record.getAddressTypeLkey());
ps.setString(5, record.getStreetAddressLine1());
ps.setString(6, record.getStreetAddressLine2());
ps.setString(7, record.getCountryLkey());
ps.setString(8, record.getStateProvinceRegionLkey());
ps.setString(9, record.getCityLkey());
ps.setString(10, record.getPostalCode());
ps.setString(11, record.getAdditionalInfo());
ps.setString(12, record.getLatitude());
ps.setString(13, record.getLongitude());
ps.setString(14, record.getIsActive());
ps.setString(15, record.getCreatedBy());
ps.setString(16, record.getUpdatedBy());
ps.setString(17, record.getDeletedBy());
ps.setBigDecimal(18, record.getCreatedAt());
ps.setBigDecimal(19, record.getUpdatedAt());
ps.setBigDecimal(20, record.getDeletedAt());
ps.setBoolean(21, record.getIsValid());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApAddressesEntity entity, String lang) {
        Class<?> myClass = ApAddressesEntity.class;
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
public void translateObject(ApAddressesEntity entity, String lang) {
        ApAddressesEntity translated = (ApAddressesEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}