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
import com.asklepios.backend_service.model.generated.pojo.ApOperationSurgicalPreparationIncision;
import com.asklepios.backend_service.model.generated.entity.ApOperationSurgicalPreparationIncisionEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApOperationSurgicalPreparationIncisionDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApOperationSurgicalPreparationIncision getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_operation_surgical_preparation_incision where key = '"+key+"'");) {
ApOperationSurgicalPreparationIncision record = new ApOperationSurgicalPreparationIncision();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setOperationRequestKey(rs.getString("operation_request_key"));
record.setSurgicalSitePreppedWith(rs.getString("surgical_site_prepped_with"));
record.setSitePrepCompletedKey(rs.getString("site_prep_completed_key"));
record.setSiteDriedTime(rs.getBigDecimal("site_dried_time"));
record.setPositionLkey(rs.getString("position_lkey"));
record.setPaddingSafetyApplied(rs.getBoolean("padding_safety_applied"));
record.setInstrumentCountStarted(rs.getBoolean("instrument_count_started"));
record.setFirstInstrumentCountKey(rs.getString("first_instrument_count_key"));
record.setImplantsReady(rs.getBoolean("implants_ready"));
record.setImplantsBarcodeScanned(rs.getBoolean("implants_barcode_scanned"));
record.setSterilityConfirmed(rs.getBoolean("sterility_confirmed"));
record.setDisposableDevicesReady(rs.getBoolean("disposable_devices_ready"));
record.setTimeOfIncision(rs.getBigDecimal("time_of_incision"));
record.setSurgicalStartMarkedKey(rs.getString("surgical_start_marked_key"));
record.setSkinOpenedTime(rs.getBigDecimal("skin_opened_time"));
record.setEstimatedSurgeryDuration(rs.getString("estimated_surgery_duration"));
record.setCreatedBy(rs.getString("created_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsvalid(rs.getBoolean("isvalid"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApOperationSurgicalPreparationIncision record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_operation_surgical_preparation_incision set key = ?, operation_request_key = ?, surgical_site_prepped_with = ?, site_prep_completed_key = ?, site_dried_time = ?, position_lkey = ?, padding_safety_applied = ?, instrument_count_started = ?, first_instrument_count_key = ?, implants_ready = ?, implants_barcode_scanned = ?, sterility_confirmed = ?, disposable_devices_ready = ?, time_of_incision = ?, surgical_start_marked_key = ?, skin_opened_time = ?, estimated_surgery_duration = ?, created_by = ?, created_at = ?, updated_by = ?, updated_at = ?, deleted_by = ?, deleted_at = ?, isvalid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getOperationRequestKey());
ps.setString(3, record.getSurgicalSitePreppedWith());
ps.setString(4, record.getSitePrepCompletedKey());
ps.setBigDecimal(5, record.getSiteDriedTime());
ps.setString(6, record.getPositionLkey());
ps.setBoolean(7, record.getPaddingSafetyApplied());
ps.setBoolean(8, record.getInstrumentCountStarted());
ps.setString(9, record.getFirstInstrumentCountKey());
ps.setBoolean(10, record.getImplantsReady());
ps.setBoolean(11, record.getImplantsBarcodeScanned());
ps.setBoolean(12, record.getSterilityConfirmed());
ps.setBoolean(13, record.getDisposableDevicesReady());
ps.setBigDecimal(14, record.getTimeOfIncision());
ps.setString(15, record.getSurgicalStartMarkedKey());
ps.setBigDecimal(16, record.getSkinOpenedTime());
ps.setString(17, record.getEstimatedSurgeryDuration());
ps.setString(18, record.getCreatedBy());
ps.setBigDecimal(19, record.getCreatedAt());
ps.setString(20, record.getUpdatedBy());
ps.setBigDecimal(21, record.getUpdatedAt());
ps.setString(22, record.getDeletedBy());
ps.setBigDecimal(23, record.getDeletedAt());
ps.setBoolean(24, record.getIsvalid());
ps.setString(25, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApOperationSurgicalPreparationIncision record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_operation_surgical_preparation_incision set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApOperationSurgicalPreparationIncision> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_operation_surgical_preparation_incision where "+ where);) {
List<ApOperationSurgicalPreparationIncision> list = new ArrayList<ApOperationSurgicalPreparationIncision>();
while(rs.next()){
ApOperationSurgicalPreparationIncision record = new ApOperationSurgicalPreparationIncision();
record.setKey(rs.getString("key"));
record.setOperationRequestKey(rs.getString("operation_request_key"));
record.setSurgicalSitePreppedWith(rs.getString("surgical_site_prepped_with"));
record.setSitePrepCompletedKey(rs.getString("site_prep_completed_key"));
record.setSiteDriedTime(rs.getBigDecimal("site_dried_time"));
record.setPositionLkey(rs.getString("position_lkey"));
record.setPaddingSafetyApplied(rs.getBoolean("padding_safety_applied"));
record.setInstrumentCountStarted(rs.getBoolean("instrument_count_started"));
record.setFirstInstrumentCountKey(rs.getString("first_instrument_count_key"));
record.setImplantsReady(rs.getBoolean("implants_ready"));
record.setImplantsBarcodeScanned(rs.getBoolean("implants_barcode_scanned"));
record.setSterilityConfirmed(rs.getBoolean("sterility_confirmed"));
record.setDisposableDevicesReady(rs.getBoolean("disposable_devices_ready"));
record.setTimeOfIncision(rs.getBigDecimal("time_of_incision"));
record.setSurgicalStartMarkedKey(rs.getString("surgical_start_marked_key"));
record.setSkinOpenedTime(rs.getBigDecimal("skin_opened_time"));
record.setEstimatedSurgeryDuration(rs.getString("estimated_surgery_duration"));
record.setCreatedBy(rs.getString("created_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsvalid(rs.getBoolean("isvalid"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApOperationSurgicalPreparationIncision record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_operation_surgical_preparation_incision values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getOperationRequestKey());
ps.setString(3, record.getSurgicalSitePreppedWith());
ps.setString(4, record.getSitePrepCompletedKey());
ps.setBigDecimal(5, record.getSiteDriedTime());
ps.setString(6, record.getPositionLkey());
ps.setBoolean(7, record.getPaddingSafetyApplied());
ps.setBoolean(8, record.getInstrumentCountStarted());
ps.setString(9, record.getFirstInstrumentCountKey());
ps.setBoolean(10, record.getImplantsReady());
ps.setBoolean(11, record.getImplantsBarcodeScanned());
ps.setBoolean(12, record.getSterilityConfirmed());
ps.setBoolean(13, record.getDisposableDevicesReady());
ps.setBigDecimal(14, record.getTimeOfIncision());
ps.setString(15, record.getSurgicalStartMarkedKey());
ps.setBigDecimal(16, record.getSkinOpenedTime());
ps.setString(17, record.getEstimatedSurgeryDuration());
ps.setString(18, record.getCreatedBy());
ps.setBigDecimal(19, record.getCreatedAt());
ps.setString(20, record.getUpdatedBy());
ps.setBigDecimal(21, record.getUpdatedAt());
ps.setString(22, record.getDeletedBy());
ps.setBigDecimal(23, record.getDeletedAt());
ps.setBoolean(24, record.getIsvalid());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApOperationSurgicalPreparationIncisionEntity entity, String lang) {
        Class<?> myClass = ApOperationSurgicalPreparationIncisionEntity.class;
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
public void translateObject(ApOperationSurgicalPreparationIncisionEntity entity, String lang) {
        ApOperationSurgicalPreparationIncisionEntity translated = (ApOperationSurgicalPreparationIncisionEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}