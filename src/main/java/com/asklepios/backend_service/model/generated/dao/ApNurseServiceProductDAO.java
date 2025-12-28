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
import com.asklepios.backend_service.model.generated.pojo.ApNurseServiceProduct;
import com.asklepios.backend_service.model.generated.entity.ApNurseServiceProductEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApNurseServiceProductDAO implements Serializable {

    @Autowired private PublicServices publicServices;
    public ApNurseServiceProduct getRecord(String key) throws SQLException {
        try (
                Connection con = DS.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from ap_nurse_service_product where key = '"+key+"'");) {
            ApNurseServiceProduct record = new ApNurseServiceProduct();
            if(rs.next()){
                record.setKey(rs.getString("key"));
                record.setPatientKey(rs.getString("patient_key"));
                record.setEncounterKey(rs.getString("encounter_key"));
                record.setDepartmentId(rs.getBigDecimal("department_id"));
                record.setCategoryLkey(rs.getString("category_lkey"));
                record.setServiceId(rs.getBigDecimal("service_id"));
                record.setWarehouseProductId(rs.getBigDecimal("warehouse_product_id"));
                record.setQuantity(rs.getBigDecimal("quantity"));
                record.setBaseUomId(rs.getBigDecimal("base_uom_id"));
                record.setUnitPrice(rs.getBigDecimal("unit_price"));
                record.setTotalPrice(rs.getBigDecimal("total_price"));
                record.setCreatedBy(rs.getString("created_by"));
                record.setUpdatedBy(rs.getString("updated_by"));
                record.setDeletedBy(rs.getString("deleted_by"));
                record.setCreatedAt(rs.getBigDecimal("created_at"));
                record.setUpdatedAt(rs.getBigDecimal("updated_at"));
                record.setDeletedAt(rs.getBigDecimal("deleted_at"));
                record.setIsValid(rs.getBoolean("is_valid"));
                record.setBrandId(rs.getBigDecimal("brand_id"));
                record.setPriceListId(rs.getBigDecimal("price_list_id"));
                record.setPriceListItemId(rs.getBigDecimal("price_list_item_id"));
                record.setPriceListItemPrice(rs.getBigDecimal("price_list_item_price"));
                record.setPriceListItemTotalPrice(rs.getBigDecimal("price_list_item_total_price"));
            } else { record = null; }
            return record;
        }
    }
    public void updateRecord(ApNurseServiceProduct record) throws SQLException {
        try (
                Connection con = DS.getConnection();
                PreparedStatement ps = con.prepareStatement("update ap_nurse_service_product set key = ?, patient_key = ?, encounter_key = ?, department_id = ?, category_lkey = ?, service_id = ?, warehouse_product_id = ?, quantity = ?, base_uom_id = ?, unit_price = ?, total_price = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ?, brand_id = ?, price_list_id = ?, price_list_item_id = ?, price_list_item_price = ?, price_list_item_total_price = ? where key = ?");
        ) {
            record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
            ps.setString(1, record.getKey());
            ps.setString(2, record.getPatientKey());
            ps.setString(3, record.getEncounterKey());
            ps.setBigDecimal(4, record.getDepartmentId());
            ps.setString(5, record.getCategoryLkey());
            ps.setBigDecimal(6, record.getServiceId());
            ps.setBigDecimal(7, record.getWarehouseProductId());
            ps.setBigDecimal(8, record.getQuantity());
            ps.setBigDecimal(9, record.getBaseUomId());
            ps.setBigDecimal(10, record.getUnitPrice());
            ps.setBigDecimal(11, record.getTotalPrice());
            ps.setString(12, record.getCreatedBy());
            ps.setString(13, record.getUpdatedBy());
            ps.setString(14, record.getDeletedBy());
            ps.setBigDecimal(15, record.getCreatedAt());
            ps.setBigDecimal(16, record.getUpdatedAt());
            ps.setBigDecimal(17, record.getDeletedAt());
            ps.setBoolean(18, record.getIsValid());
            ps.setBigDecimal(19, record.getBrandId());
            ps.setBigDecimal(20, record.getPriceListId());
            ps.setBigDecimal(21, record.getPriceListItemId());
            ps.setBigDecimal(22, record.getPriceListItemPrice());
            ps.setBigDecimal(23, record.getPriceListItemTotalPrice());
            ps.setString(24, record.getKey());
            ps.executeUpdate();
        }
    }
    public void deleteRecord(ApNurseServiceProduct record) throws SQLException {
        try (
                Connection con = DS.getConnection();
                PreparedStatement ps = con.prepareStatement("update ap_nurse_service_product set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
        ) {
            ps.setString(1, record.getKey());
            ps.executeUpdate();
        }
    }
    public List<ApNurseServiceProduct> getList(String where) throws SQLException {
        if (where == null || where.isEmpty()) where = "1=1";
        try (
                Connection con = DS.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from ap_nurse_service_product where "+ where);) {
            List<ApNurseServiceProduct> list = new ArrayList<ApNurseServiceProduct>();
            while(rs.next()){
                ApNurseServiceProduct record = new ApNurseServiceProduct();
                record.setKey(rs.getString("key"));
                record.setPatientKey(rs.getString("patient_key"));
                record.setEncounterKey(rs.getString("encounter_key"));
                record.setDepartmentId(rs.getBigDecimal("department_id"));
                record.setCategoryLkey(rs.getString("category_lkey"));
                record.setServiceId(rs.getBigDecimal("service_id"));
                record.setWarehouseProductId(rs.getBigDecimal("warehouse_product_id"));
                record.setQuantity(rs.getBigDecimal("quantity"));
                record.setBaseUomId(rs.getBigDecimal("base_uom_id"));
                record.setUnitPrice(rs.getBigDecimal("unit_price"));
                record.setTotalPrice(rs.getBigDecimal("total_price"));
                record.setCreatedBy(rs.getString("created_by"));
                record.setUpdatedBy(rs.getString("updated_by"));
                record.setDeletedBy(rs.getString("deleted_by"));
                record.setCreatedAt(rs.getBigDecimal("created_at"));
                record.setUpdatedAt(rs.getBigDecimal("updated_at"));
                record.setDeletedAt(rs.getBigDecimal("deleted_at"));
                record.setIsValid(rs.getBoolean("is_valid"));
                record.setBrandId(rs.getBigDecimal("brand_id"));
                record.setPriceListId(rs.getBigDecimal("price_list_id"));
                record.setPriceListItemId(rs.getBigDecimal("price_list_item_id"));
                record.setPriceListItemPrice(rs.getBigDecimal("price_list_item_price"));
                record.setPriceListItemTotalPrice(rs.getBigDecimal("price_list_item_total_price"));
                list.add(record);
            }
            return list;
        }
    }
    public String saveRecord(ApNurseServiceProduct record) throws SQLException {
        try (
                Connection con = DS.getConnection();
                PreparedStatement ps = con.prepareStatement("insert into ap_nurse_service_product values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        ) {
            if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
            if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
            String key = "" + System.nanoTime();
            record.setKey(key);

            ps.setString(1, key);
            ps.setString(2, record.getPatientKey());
            ps.setString(3, record.getEncounterKey());
            ps.setBigDecimal(4, record.getDepartmentId());
            ps.setString(5, record.getCategoryLkey());
            ps.setBigDecimal(6, record.getServiceId());
            ps.setBigDecimal(7, record.getWarehouseProductId());
            ps.setBigDecimal(8, record.getQuantity());
            ps.setBigDecimal(9, record.getBaseUomId());
            ps.setBigDecimal(10, record.getUnitPrice());
            ps.setBigDecimal(11, record.getTotalPrice());
            ps.setString(12, record.getCreatedBy());
            ps.setString(13, record.getUpdatedBy());
            ps.setString(14, record.getDeletedBy());
            ps.setBigDecimal(15, record.getCreatedAt());
            ps.setBigDecimal(16, record.getUpdatedAt());
            ps.setBigDecimal(17, record.getDeletedAt());
            ps.setBoolean(18, record.getIsValid());
            ps.setBigDecimal(19, record.getBrandId());
            ps.setBigDecimal(20, record.getPriceListId());
            ps.setBigDecimal(21, record.getPriceListItemId());
            ps.setBigDecimal(22, record.getPriceListItemPrice());
            ps.setBigDecimal(23, record.getPriceListItemTotalPrice());
            ps.executeUpdate();
            return key;
        }
    }
    public void populateLovFields(ApNurseServiceProductEntity entity, String lang) {
        Class<?> myClass = ApNurseServiceProductEntity.class;
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
    public void translateObject(ApNurseServiceProductEntity entity, String lang) {
        ApNurseServiceProductEntity translated = (ApNurseServiceProductEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}