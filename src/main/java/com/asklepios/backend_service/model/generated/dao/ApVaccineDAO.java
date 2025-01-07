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
import com.asklepios.backend_service.model.generated.pojo.ApVaccine;
import com.asklepios.backend_service.model.generated.entity.ApVaccineEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApVaccineDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApVaccine getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_vaccine where key = '"+key+"'");) {
ApVaccine record = new ApVaccine();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setVaccineCode(rs.getString("vaccine_code"));
record.setVaccineName(rs.getString("vaccine_name"));
record.setAtcCode(rs.getString("atc_code"));
record.setTypeLkey(rs.getString("type_lkey"));
record.setRoaLkey(rs.getString("roa_lkey"));
record.setSiteOfAdministration(rs.getString("site_of_administration"));
record.setPostOpeningDuration(rs.getString("post_opening_duration"));
record.setDurationUnitLkey(rs.getString("duration_unit_lkey"));
record.setIndications(rs.getString("indications"));
record.setPossibleReactions(rs.getString("possible_reactions"));
record.setContraindicationsAndPrecautions(rs.getString("contraindications_and_precautions"));
record.setStorageAndHandling(rs.getString("storage_and_handling"));
record.setIsValid(rs.getBoolean("is_valid"));
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
public void updateRecord(ApVaccine record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_vaccine set key = ?, vaccine_code = ?, vaccine_name = ?, atc_code = ?, type_lkey = ?, roa_lkey = ?, site_of_administration = ?, post_opening_duration = ?, duration_unit_lkey = ?, indications = ?, possible_reactions = ?, contraindications_and_precautions = ?, storage_and_handling = ?, is_valid = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getVaccineCode());
ps.setString(3, record.getVaccineName());
ps.setString(4, record.getAtcCode());
ps.setString(5, record.getTypeLkey());
ps.setString(6, record.getRoaLkey());
ps.setString(7, record.getSiteOfAdministration());
ps.setString(8, record.getPostOpeningDuration());
ps.setString(9, record.getDurationUnitLkey());
ps.setString(10, record.getIndications());
ps.setString(11, record.getPossibleReactions());
ps.setString(12, record.getContraindicationsAndPrecautions());
ps.setString(13, record.getStorageAndHandling());
ps.setBoolean(14, record.getIsValid());
ps.setString(15, record.getCreatedBy());
ps.setString(16, record.getUpdatedBy());
ps.setString(17, record.getDeletedBy());
ps.setBigDecimal(18, record.getCreatedAt());
ps.setBigDecimal(19, record.getUpdatedAt());
ps.setBigDecimal(20, record.getDeletedAt());
ps.setString(21, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApVaccine record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_vaccine set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApVaccine> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_vaccine where "+ where);) {
List<ApVaccine> list = new ArrayList<ApVaccine>();
while(rs.next()){
ApVaccine record = new ApVaccine();
record.setKey(rs.getString("key"));
record.setVaccineCode(rs.getString("vaccine_code"));
record.setVaccineName(rs.getString("vaccine_name"));
record.setAtcCode(rs.getString("atc_code"));
record.setTypeLkey(rs.getString("type_lkey"));
record.setRoaLkey(rs.getString("roa_lkey"));
record.setSiteOfAdministration(rs.getString("site_of_administration"));
record.setPostOpeningDuration(rs.getString("post_opening_duration"));
record.setDurationUnitLkey(rs.getString("duration_unit_lkey"));
record.setIndications(rs.getString("indications"));
record.setPossibleReactions(rs.getString("possible_reactions"));
record.setContraindicationsAndPrecautions(rs.getString("contraindications_and_precautions"));
record.setStorageAndHandling(rs.getString("storage_and_handling"));
record.setIsValid(rs.getBoolean("is_valid"));
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
public String saveRecord(ApVaccine record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_vaccine values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getVaccineCode());
ps.setString(3, record.getVaccineName());
ps.setString(4, record.getAtcCode());
ps.setString(5, record.getTypeLkey());
ps.setString(6, record.getRoaLkey());
ps.setString(7, record.getSiteOfAdministration());
ps.setString(8, record.getPostOpeningDuration());
ps.setString(9, record.getDurationUnitLkey());
ps.setString(10, record.getIndications());
ps.setString(11, record.getPossibleReactions());
ps.setString(12, record.getContraindicationsAndPrecautions());
ps.setString(13, record.getStorageAndHandling());
ps.setBoolean(14, record.getIsValid());
ps.setString(15, record.getCreatedBy());
ps.setString(16, record.getUpdatedBy());
ps.setString(17, record.getDeletedBy());
ps.setBigDecimal(18, record.getCreatedAt());
ps.setBigDecimal(19, record.getUpdatedAt());
ps.setBigDecimal(20, record.getDeletedAt());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApVaccineEntity entity, String lang) {
        Class<?> myClass = ApVaccineEntity.class;
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
public void translateObject(ApVaccineEntity entity, String lang) {
        ApVaccineEntity translated = (ApVaccineEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}