package com.asklepios.backend_service.model.generated.dao;

import java.io.Serializable;

import com.asklepios.backend_service.model.generated.pojo.ApEncounterServiceService;
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
import com.asklepios.backend_service.model.generated.pojo.ApEncounterServiceService;
import com.asklepios.backend_service.model.generated.entity.ApEncounterServiceEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApEncounterServiceDAO implements Serializable {

    @Autowired
    private PublicServices publicServices;

    public ApEncounterServiceService getRecord(String key) throws SQLException {
        try (
                Connection con = DS.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from ap_encounter_service where key = '" + key + "'")
        ) {
            ApEncounterServiceService record = new ApEncounterServiceService();
            if (rs.next()) {
                record.setKey(rs.getString("key"));
                record.setEncounterKey(rs.getString("encounter_key"));
                record.setPatientKey(rs.getString("patient_key"));
                record.setServiceKey(rs.getString("service_key"));
                record.setServiceTypeLkey(rs.getString("service_type_lkey"));
                record.setServiceName(rs.getString("service_name"));
                record.setServicePrice(rs.getBigDecimal("service_price"));
                record.setServiceCurrencyLkey(rs.getString("service_currency_lkey"));
                record.setEncounterDate(rs.getBigDecimal("encounter_date"));
                record.setCreatedBy(rs.getString("created_by"));
                record.setCreatedAt(rs.getBigDecimal("created_at"));
            } else {
                record = null;
            }
            return record;
        }
    }

    public void updateRecord(ApEncounterServiceService record) throws SQLException {
        try (
                Connection con = DS.getConnection();
                PreparedStatement ps = con.prepareStatement(
                        "update ap_encounter_service " +
                                "set encounter_key = ?, patient_key = ?, service_key = ?, " +
                                "    service_type_lkey = ?, service_name = ?, service_price = ?, service_currency_lkey = ?, " +
                                "    encounter_date = ?, created_by = ?, created_at = ? " +
                                "where key = ?"
                )
        ) {
            ps.setString(1, record.getEncounterKey());
            ps.setString(2, record.getPatientKey());
            ps.setString(3, record.getServiceKey());
            ps.setString(4, record.getServiceTypeLkey());
            ps.setString(5, record.getServiceName());
            ps.setBigDecimal(6, record.getServicePrice());
            ps.setString(7, record.getServiceCurrencyLkey());
            ps.setBigDecimal(8, record.getEncounterDate());
            ps.setString(9, record.getCreatedBy());
            ps.setBigDecimal(10, record.getCreatedAt());
            ps.setString(11, record.getKey());
            ps.executeUpdate();
        }
    }

    public void deleteRecord(ApEncounterServiceService record) throws SQLException {
        try (
                Connection con = DS.getConnection();
                PreparedStatement ps = con.prepareStatement("delete from ap_encounter_service where key = ?")
        ) {
            ps.setString(1, record.getKey());
            ps.executeUpdate();
        }
    }

    public List<ApEncounterServiceService> getList(String where) throws SQLException {
        if (where == null || where.isEmpty()) where = "1=1";
        try (
                Connection con = DS.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from ap_encounter_service where " + where)
        ) {
            List<ApEncounterServiceService> list = new ArrayList<>();
            while (rs.next()) {
                ApEncounterServiceService record = new ApEncounterServiceService();
                record.setKey(rs.getString("key"));
                record.setEncounterKey(rs.getString("encounter_key"));
                record.setPatientKey(rs.getString("patient_key"));
                record.setServiceKey(rs.getString("service_key"));
                record.setServiceTypeLkey(rs.getString("service_type_lkey"));
                record.setServiceName(rs.getString("service_name"));
                record.setServicePrice(rs.getBigDecimal("service_price"));
                record.setServiceCurrencyLkey(rs.getString("service_currency_lkey"));
                record.setEncounterDate(rs.getBigDecimal("encounter_date"));
                record.setCreatedBy(rs.getString("created_by"));
                record.setCreatedAt(rs.getBigDecimal("created_at"));
                list.add(record);
            }
            return list;
        }
    }

    public String saveRecord(ApEncounterServiceService record) throws SQLException {
        try (
                Connection con = DS.getConnection();
                PreparedStatement ps = con.prepareStatement(
                        "insert into ap_encounter_service " +
                                "(key, encounter_key, patient_key, service_key, service_type_lkey, service_name, " +
                                " service_price, service_currency_lkey, encounter_date, created_by, created_at) " +
                                "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
                )
        ) {
            if (record.getKey() != null && !record.getKey().isEmpty()) {
                updateRecord(record);
                return record.getKey();
            }

            String key = "" + System.nanoTime();
            record.setKey(key);

            // default timestamps if null
            BigDecimal now = new BigDecimal(System.currentTimeMillis());
            if (record.getEncounterDate() == null) {
                record.setEncounterDate(now);
            }
            if (record.getCreatedAt() == null) {
                record.setCreatedAt(now);
            }

            ps.setString(1, key);
            ps.setString(2, record.getEncounterKey());
            ps.setString(3, record.getPatientKey());
            ps.setString(4, record.getServiceKey());
            ps.setString(5, record.getServiceTypeLkey());
            ps.setString(6, record.getServiceName());
            ps.setBigDecimal(7, record.getServicePrice());
            ps.setString(8, record.getServiceCurrencyLkey());
            ps.setBigDecimal(9, record.getEncounterDate());
            ps.setString(10, record.getCreatedBy());
            ps.setBigDecimal(11, record.getCreatedAt());
            ps.executeUpdate();
            return key;
        }
    }

    public void populateLovFields(ApEncounterServiceEntity entity, String lang) {
        Class<?> myClass = ApEncounterServiceEntity.class;
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

    public void translateObject(ApEncounterServiceEntity entity, String lang) {
        ApEncounterServiceEntity translated =
                (ApEncounterServiceEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}
