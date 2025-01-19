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
import com.asklepios.backend_service.model.generated.pojo.ApEncounterVaccination;
import com.asklepios.backend_service.model.generated.entity.ApEncounterVaccinationEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApEncounterVaccinationDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApEncounterVaccination getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_encounter_vaccination where key = '"+key+"'");) {
ApEncounterVaccination record = new ApEncounterVaccination();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setVaccineKey(rs.getString("vaccine_key"));
record.setVaccineBrandKey(rs.getString("vaccine_brand_key"));
record.setVaccineDoseKey(rs.getString("vaccine_dose_key"));
record.setVaccineLotNumber(rs.getString("vaccine_lot_number"));
record.setDateAdministered(rs.getBigDecimal("date_administered"));
record.setActualSide(rs.getString("actual_side"));
record.setAdministrationReactions(rs.getString("administration_reactions"));
record.setExternalFacilityName(rs.getString("external_facility_name"));
record.setNotes(rs.getString("notes"));
record.setReviewedBy(rs.getString("reviewed_by"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setReviewedAt(rs.getBigDecimal("reviewed_at"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setCancellationReason(rs.getString("cancellation_reason"));
record.setStatusLkey(rs.getString("status_lkey"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApEncounterVaccination record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_encounter_vaccination set key = ?, patient_key = ?, encounter_key = ?, vaccine_key = ?, vaccine_brand_key = ?, vaccine_dose_key = ?, vaccine_lot_number = ?, date_administered = ?, actual_side = ?, administration_reactions = ?, external_facility_name = ?, notes = ?, reviewed_by = ?, created_by = ?, updated_by = ?, deleted_by = ?, reviewed_at = ?, created_at = ?, updated_at = ?, deleted_at = ?, cancellation_reason = ?, status_lkey = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getEncounterKey());
ps.setString(4, record.getVaccineKey());
ps.setString(5, record.getVaccineBrandKey());
ps.setString(6, record.getVaccineDoseKey());
ps.setString(7, record.getVaccineLotNumber());
ps.setBigDecimal(8, record.getDateAdministered());
ps.setString(9, record.getActualSide());
ps.setString(10, record.getAdministrationReactions());
ps.setString(11, record.getExternalFacilityName());
ps.setString(12, record.getNotes());
ps.setString(13, record.getReviewedBy());
ps.setString(14, record.getCreatedBy());
ps.setString(15, record.getUpdatedBy());
ps.setString(16, record.getDeletedBy());
ps.setBigDecimal(17, record.getReviewedAt());
ps.setBigDecimal(18, record.getCreatedAt());
ps.setBigDecimal(19, record.getUpdatedAt());
ps.setBigDecimal(20, record.getDeletedAt());
ps.setString(21, record.getCancellationReason());
ps.setString(22, record.getStatusLkey());
ps.setString(23, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApEncounterVaccination record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_encounter_vaccination set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApEncounterVaccination> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_encounter_vaccination where "+ where);) {
List<ApEncounterVaccination> list = new ArrayList<ApEncounterVaccination>();
while(rs.next()){
ApEncounterVaccination record = new ApEncounterVaccination();
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setEncounterKey(rs.getString("encounter_key"));
record.setVaccineKey(rs.getString("vaccine_key"));
record.setVaccineBrandKey(rs.getString("vaccine_brand_key"));
record.setVaccineDoseKey(rs.getString("vaccine_dose_key"));
record.setVaccineLotNumber(rs.getString("vaccine_lot_number"));
record.setDateAdministered(rs.getBigDecimal("date_administered"));
record.setActualSide(rs.getString("actual_side"));
record.setAdministrationReactions(rs.getString("administration_reactions"));
record.setExternalFacilityName(rs.getString("external_facility_name"));
record.setNotes(rs.getString("notes"));
record.setReviewedBy(rs.getString("reviewed_by"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setReviewedAt(rs.getBigDecimal("reviewed_at"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setCancellationReason(rs.getString("cancellation_reason"));
record.setStatusLkey(rs.getString("status_lkey"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApEncounterVaccination record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_encounter_vaccination values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getEncounterKey());
ps.setString(4, record.getVaccineKey());
ps.setString(5, record.getVaccineBrandKey());
ps.setString(6, record.getVaccineDoseKey());
ps.setString(7, record.getVaccineLotNumber());
ps.setBigDecimal(8, record.getDateAdministered());
ps.setString(9, record.getActualSide());
ps.setString(10, record.getAdministrationReactions());
ps.setString(11, record.getExternalFacilityName());
ps.setString(12, record.getNotes());
ps.setString(13, record.getReviewedBy());
ps.setString(14, record.getCreatedBy());
ps.setString(15, record.getUpdatedBy());
ps.setString(16, record.getDeletedBy());
ps.setBigDecimal(17, record.getReviewedAt());
ps.setBigDecimal(18, record.getCreatedAt());
ps.setBigDecimal(19, record.getUpdatedAt());
ps.setBigDecimal(20, record.getDeletedAt());
ps.setString(21, record.getCancellationReason());
ps.setString(22, record.getStatusLkey());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApEncounterVaccinationEntity entity, String lang) {
        Class<?> myClass = ApEncounterVaccinationEntity.class;
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
public void translateObject(ApEncounterVaccinationEntity entity, String lang) {
        ApEncounterVaccinationEntity translated = (ApEncounterVaccinationEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}