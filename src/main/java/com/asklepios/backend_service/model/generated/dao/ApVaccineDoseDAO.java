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
import com.asklepios.backend_service.model.generated.pojo.ApVaccineDose;
import com.asklepios.backend_service.model.generated.entity.ApVaccineDoseEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApVaccineDoseDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApVaccineDose getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_vaccine_dose where key = '"+key+"'");) {
ApVaccineDose record = new ApVaccineDose();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setFromAge(rs.getBigDecimal("from_age"));
record.setToAge(rs.getBigDecimal("to_age"));
record.setFromAgeUnitLkey(rs.getString("from_age_unit_lkey"));
record.setToAgeUnitLkey(rs.getString("to_age_unit_lkey"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setVaccineKey(rs.getString("vaccine_key"));
record.setDoseNameLkey(rs.getString("dose_name_lkey"));
record.setIsBooster(rs.getBoolean("is_booster"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApVaccineDose record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_vaccine_dose set key = ?, from_age = ?, to_age = ?, from_age_unit_lkey = ?, to_age_unit_lkey = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, vaccine_key = ?, dose_name_lkey = ?, is_booster = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setBigDecimal(2, record.getFromAge());
ps.setBigDecimal(3, record.getToAge());
ps.setString(4, record.getFromAgeUnitLkey());
ps.setString(5, record.getToAgeUnitLkey());
ps.setString(6, record.getCreatedBy());
ps.setString(7, record.getUpdatedBy());
ps.setString(8, record.getDeletedBy());
ps.setBigDecimal(9, record.getCreatedAt());
ps.setBigDecimal(10, record.getUpdatedAt());
ps.setBigDecimal(11, record.getDeletedAt());
ps.setString(12, record.getVaccineKey());
ps.setString(13, record.getDoseNameLkey());
ps.setBoolean(14, record.getIsBooster());
ps.setString(15, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApVaccineDose record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_vaccine_dose set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApVaccineDose> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_vaccine_dose where "+ where);) {
List<ApVaccineDose> list = new ArrayList<ApVaccineDose>();
while(rs.next()){
ApVaccineDose record = new ApVaccineDose();
record.setKey(rs.getString("key"));
record.setFromAge(rs.getBigDecimal("from_age"));
record.setToAge(rs.getBigDecimal("to_age"));
record.setFromAgeUnitLkey(rs.getString("from_age_unit_lkey"));
record.setToAgeUnitLkey(rs.getString("to_age_unit_lkey"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setVaccineKey(rs.getString("vaccine_key"));
record.setDoseNameLkey(rs.getString("dose_name_lkey"));
record.setIsBooster(rs.getBoolean("is_booster"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApVaccineDose record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_vaccine_dose values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setBigDecimal(2, record.getFromAge());
ps.setBigDecimal(3, record.getToAge());
ps.setString(4, record.getFromAgeUnitLkey());
ps.setString(5, record.getToAgeUnitLkey());
ps.setString(6, record.getCreatedBy());
ps.setString(7, record.getUpdatedBy());
ps.setString(8, record.getDeletedBy());
ps.setBigDecimal(9, record.getCreatedAt());
ps.setBigDecimal(10, record.getUpdatedAt());
ps.setBigDecimal(11, record.getDeletedAt());
ps.setString(12, record.getVaccineKey());
ps.setString(13, record.getDoseNameLkey());
ps.setBoolean(14, record.getIsBooster());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApVaccineDoseEntity entity, String lang) {
        Class<?> myClass = ApVaccineDoseEntity.class;
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
public void translateObject(ApVaccineDoseEntity entity, String lang) {
        ApVaccineDoseEntity translated = (ApVaccineDoseEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}