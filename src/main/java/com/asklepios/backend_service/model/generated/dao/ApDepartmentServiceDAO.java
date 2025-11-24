package com.asklepios.backend_service.model.generated.dao;

import java.io.Serializable;

import com.asklepios.backend_service.model.generated.pojo.ApDepartmentServiceService;
import lombok.extern.slf4j.Slf4j;
import lombok.Getter;
import lombok.Setter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.asklepios.backend_service.controller.PublicServices;
import java.lang.reflect.Field;
import com.asklepios.backend_service.model.generated.pojo.ApDepartmentServiceService;
import com.asklepios.backend_service.model.generated.entity.ApDepartmentServiceEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApDepartmentServiceDAO implements Serializable {

    @Autowired
    private PublicServices publicServices;

    public ApDepartmentServiceService getRecord(String key) throws SQLException {
        try (
                Connection con = DS.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from ap_department_service where key = '" + key + "'")
        ) {
            ApDepartmentServiceService record = new ApDepartmentServiceService();
            if (rs.next()) {
                record.setKey(rs.getString("key"));
                record.setDepartmentKey(rs.getString("department_key"));
                record.setServiceKey(rs.getString("service_key"));
                record.setIsActive(rs.getBoolean("is_active"));
                record.setServiceTypeLkey(rs.getString("service_type_lkey"));
                record.setServiceName(rs.getString("service_name"));
                record.setServicePrice(rs.getBigDecimal("service_price"));
                record.setServiceCurrencyLkey(rs.getString("service_currency_lkey"));
            } else {
                record = null;
            }
            return record;
        }
    }

    public void updateRecord(ApDepartmentServiceService record) throws SQLException {
        try (
                Connection con = DS.getConnection();
                PreparedStatement ps = con.prepareStatement(
                        "update ap_department_service " +
                                "set department_key = ?, service_key = ?, is_active = ?, " +
                                "    service_type_lkey = ?, service_name = ?, service_price = ?, service_currency_lkey = ? " +
                                "where key = ?"
                )
        ) {
            ps.setString(1, record.getDepartmentKey());
            ps.setString(2, record.getServiceKey());
            ps.setBoolean(3, record.getIsActive() != null ? record.getIsActive() : true);
            ps.setString(4, record.getServiceTypeLkey());
            ps.setString(5, record.getServiceName());
            ps.setBigDecimal(6, record.getServicePrice());
            ps.setString(7, record.getServiceCurrencyLkey());
            ps.setString(8, record.getKey());
            ps.executeUpdate();
        }
    }

    public void deleteRecord(ApDepartmentServiceService record) throws SQLException {
        // Soft delete via is_active
        try (
                Connection con = DS.getConnection();
                PreparedStatement ps = con.prepareStatement(
                        "update ap_department_service set is_active = false where key = ?"
                )
        ) {
            ps.setString(1, record.getKey());
            ps.executeUpdate();
        }
    }

    public List<ApDepartmentServiceService> getList(String where) throws SQLException {
        if (where == null || where.isEmpty()) where = "1=1";
        try (
                Connection con = DS.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from ap_department_service where " + where)
        ) {
            List<ApDepartmentServiceService> list = new ArrayList<>();
            while (rs.next()) {
                ApDepartmentServiceService record = new ApDepartmentServiceService();
                record.setKey(rs.getString("key"));
                record.setDepartmentKey(rs.getString("department_key"));
                record.setServiceKey(rs.getString("service_key"));
                record.setIsActive(rs.getBoolean("is_active"));
                record.setServiceTypeLkey(rs.getString("service_type_lkey"));
                record.setServiceName(rs.getString("service_name"));
                record.setServicePrice(rs.getBigDecimal("service_price"));
                record.setServiceCurrencyLkey(rs.getString("service_currency_lkey"));
                list.add(record);
            }
            return list;
        }
    }

    public String saveRecord(ApDepartmentServiceService record) throws SQLException {
        try (
                Connection con = DS.getConnection();
                PreparedStatement ps = con.prepareStatement(
                        "insert into ap_department_service " +
                                "(key, department_key, service_key, is_active, service_type_lkey, service_name, service_price, service_currency_lkey) " +
                                "values (?, ?, ?, ?, ?, ?, ?, ?)"
                )
        ) {
            if (record.getKey() != null && !record.getKey().isEmpty()) {
                updateRecord(record);
                return record.getKey();
            }

            String key = "" + System.nanoTime();
            record.setKey(key);
            if (record.getIsActive() == null) record.setIsActive(true);

            ps.setString(1, key);
            ps.setString(2, record.getDepartmentKey());
            ps.setString(3, record.getServiceKey());
            ps.setBoolean(4, record.getIsActive());
            ps.setString(5, record.getServiceTypeLkey());
            ps.setString(6, record.getServiceName());
            ps.setBigDecimal(7, record.getServicePrice());
            ps.setString(8, record.getServiceCurrencyLkey());
            ps.executeUpdate();
            return key;
        }
    }

    public void populateLovFields(ApDepartmentServiceEntity entity, String lang) {
        Class<?> myClass = ApDepartmentServiceEntity.class;
        Field[] fields = myClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            if (fieldName.contains("Lkey")) {
                try {
                    Object fieldValue = field.get(entity);
                    if (fieldValue != null) {
                        String _lovKey = fieldValue.toString();
                        Field valueField = myClass.getDeclaredField(fieldName.replaceAll("Lkey", "Lvalue"));
                        valueField.setAccessible(true);
                        if (lang == null) {
                            valueField.set(entity, publicServices.getFromRedisLovValue(_lovKey));
                        } else {
                            valueField.set(entity, publicServices.getFromRedisLovValue(_lovKey, lang));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void translateObject(ApDepartmentServiceEntity entity, String lang) {
        ApDepartmentServiceEntity translated =
                (ApDepartmentServiceEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}
