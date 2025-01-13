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
import com.asklepios.backend_service.model.generated.pojo.ApVaccineDosesInterval;
import com.asklepios.backend_service.model.generated.entity.ApVaccineDosesIntervalEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApVaccineDosesIntervalDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApVaccineDosesInterval getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_vaccine_doses_interval where key = '"+key+"'");) {
ApVaccineDosesInterval record = new ApVaccineDosesInterval();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setVaccineKey(rs.getString("vaccine_key"));
record.setFromDoseKey(rs.getString("from_dose_key"));
record.setToDoseKey(rs.getString("to_dose_key"));
record.setIntervalBetweenDoses(rs.getBigDecimal("interval_between_doses"));
record.setUnitLkey(rs.getString("unit_lkey"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApVaccineDosesInterval record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_vaccine_doses_interval set key = ?, vaccine_key = ?, from_dose_key = ?, to_dose_key = ?, interval_between_doses = ?, unit_lkey = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getVaccineKey());
ps.setString(3, record.getFromDoseKey());
ps.setString(4, record.getToDoseKey());
ps.setBigDecimal(5, record.getIntervalBetweenDoses());
ps.setString(6, record.getUnitLkey());
ps.setString(7, record.getCreatedBy());
ps.setString(8, record.getUpdatedBy());
ps.setString(9, record.getDeletedBy());
ps.setBigDecimal(10, record.getCreatedAt());
ps.setBigDecimal(11, record.getUpdatedAt());
ps.setBigDecimal(12, record.getDeletedAt());
ps.setString(13, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApVaccineDosesInterval record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_vaccine_doses_interval set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApVaccineDosesInterval> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_vaccine_doses_interval where "+ where);) {
List<ApVaccineDosesInterval> list = new ArrayList<ApVaccineDosesInterval>();
while(rs.next()){
ApVaccineDosesInterval record = new ApVaccineDosesInterval();
record.setKey(rs.getString("key"));
record.setVaccineKey(rs.getString("vaccine_key"));
record.setFromDoseKey(rs.getString("from_dose_key"));
record.setToDoseKey(rs.getString("to_dose_key"));
record.setIntervalBetweenDoses(rs.getBigDecimal("interval_between_doses"));
record.setUnitLkey(rs.getString("unit_lkey"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApVaccineDosesInterval record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_vaccine_doses_interval values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getVaccineKey());
ps.setString(3, record.getFromDoseKey());
ps.setString(4, record.getToDoseKey());
ps.setBigDecimal(5, record.getIntervalBetweenDoses());
ps.setString(6, record.getUnitLkey());
ps.setString(7, record.getCreatedBy());
ps.setString(8, record.getUpdatedBy());
ps.setString(9, record.getDeletedBy());
ps.setBigDecimal(10, record.getCreatedAt());
ps.setBigDecimal(11, record.getUpdatedAt());
ps.setBigDecimal(12, record.getDeletedAt());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApVaccineDosesIntervalEntity entity, String lang) {
        Class<?> myClass = ApVaccineDosesIntervalEntity.class;
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
public void translateObject(ApVaccineDosesIntervalEntity entity, String lang) {
        ApVaccineDosesIntervalEntity translated = (ApVaccineDosesIntervalEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}