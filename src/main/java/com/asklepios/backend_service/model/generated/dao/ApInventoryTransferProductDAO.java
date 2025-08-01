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
import com.asklepios.backend_service.model.generated.pojo.ApInventoryTransferProduct;
import com.asklepios.backend_service.model.generated.entity.ApInventoryTransferProductEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApInventoryTransferProductDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApInventoryTransferProduct getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_inventory_transfer_product where key = '"+key+"'");) {
ApInventoryTransferProduct record = new ApInventoryTransferProduct();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setTransferKey(rs.getString("transfer_key"));
record.setProductKey(rs.getString("product_key"));
record.setQuentityRequested(rs.getBigDecimal("quentity_requested"));
record.setQuentityApproved(rs.getBigDecimal("quentity_approved"));
record.setLotserialnumber(rs.getString("lotserialnumber"));
record.setIsEffectedWarehouse(rs.getBoolean("is_effected_warehouse"));
record.setNotes(rs.getString("notes"));
record.setTransUomKey(rs.getString("trans_uom_key"));
record.setQuentityRequestedBaseUom(rs.getBigDecimal("quentity_requested_base_uom"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setQuentityApprovedBaseUom(rs.getBigDecimal("quentity_approved_base_uom"));
record.setStatusLkey(rs.getString("status_lkey"));
record.setRejectedReason(rs.getString("rejected_reason"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApInventoryTransferProduct record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_inventory_transfer_product set key = ?, transfer_key = ?, product_key = ?, quentity_requested = ?, quentity_approved = ?, lotserialnumber = ?, is_effected_warehouse = ?, notes = ?, trans_uom_key = ?, quentity_requested_base_uom = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ?, quentity_approved_base_uom = ?, status_lkey = ?, rejected_reason = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getTransferKey());
ps.setString(3, record.getProductKey());
ps.setBigDecimal(4, record.getQuentityRequested());
ps.setBigDecimal(5, record.getQuentityApproved());
ps.setString(6, record.getLotserialnumber());
ps.setBoolean(7, record.getIsEffectedWarehouse());
ps.setString(8, record.getNotes());
ps.setString(9, record.getTransUomKey());
ps.setBigDecimal(10, record.getQuentityRequestedBaseUom());
ps.setString(11, record.getCreatedBy());
ps.setString(12, record.getUpdatedBy());
ps.setString(13, record.getDeletedBy());
ps.setBigDecimal(14, record.getCreatedAt());
ps.setBigDecimal(15, record.getUpdatedAt());
ps.setBigDecimal(16, record.getDeletedAt());
ps.setBoolean(17, record.getIsValid());
ps.setBigDecimal(18, record.getQuentityApprovedBaseUom());
ps.setString(19, record.getStatusLkey());
ps.setString(20, record.getRejectedReason());
ps.setString(21, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApInventoryTransferProduct record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_inventory_transfer_product set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApInventoryTransferProduct> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_inventory_transfer_product where "+ where);) {
List<ApInventoryTransferProduct> list = new ArrayList<ApInventoryTransferProduct>();
while(rs.next()){
ApInventoryTransferProduct record = new ApInventoryTransferProduct();
record.setKey(rs.getString("key"));
record.setTransferKey(rs.getString("transfer_key"));
record.setProductKey(rs.getString("product_key"));
record.setQuentityRequested(rs.getBigDecimal("quentity_requested"));
record.setQuentityApproved(rs.getBigDecimal("quentity_approved"));
record.setLotserialnumber(rs.getString("lotserialnumber"));
record.setIsEffectedWarehouse(rs.getBoolean("is_effected_warehouse"));
record.setNotes(rs.getString("notes"));
record.setTransUomKey(rs.getString("trans_uom_key"));
record.setQuentityRequestedBaseUom(rs.getBigDecimal("quentity_requested_base_uom"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setQuentityApprovedBaseUom(rs.getBigDecimal("quentity_approved_base_uom"));
record.setStatusLkey(rs.getString("status_lkey"));
record.setRejectedReason(rs.getString("rejected_reason"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApInventoryTransferProduct record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_inventory_transfer_product values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getTransferKey());
ps.setString(3, record.getProductKey());
ps.setBigDecimal(4, record.getQuentityRequested());
ps.setBigDecimal(5, record.getQuentityApproved());
ps.setString(6, record.getLotserialnumber());
ps.setBoolean(7, record.getIsEffectedWarehouse());
ps.setString(8, record.getNotes());
ps.setString(9, record.getTransUomKey());
ps.setBigDecimal(10, record.getQuentityRequestedBaseUom());
ps.setString(11, record.getCreatedBy());
ps.setString(12, record.getUpdatedBy());
ps.setString(13, record.getDeletedBy());
ps.setBigDecimal(14, record.getCreatedAt());
ps.setBigDecimal(15, record.getUpdatedAt());
ps.setBigDecimal(16, record.getDeletedAt());
ps.setBoolean(17, record.getIsValid());
ps.setBigDecimal(18, record.getQuentityApprovedBaseUom());
ps.setString(19, record.getStatusLkey());
ps.setString(20, record.getRejectedReason());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApInventoryTransferProductEntity entity, String lang) {
        Class<?> myClass = ApInventoryTransferProductEntity.class;
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
public void translateObject(ApInventoryTransferProductEntity entity, String lang) {
        ApInventoryTransferProductEntity translated = (ApInventoryTransferProductEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}