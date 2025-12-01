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

import com.asklepios.backend_service.model.generated.pojo.ApTelephonicConsultation;
import com.asklepios.backend_service.model.generated.entity.ApTelephonicConsultationEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApTelephonicConsultationDAO implements Serializable {

    @Autowired
    private PublicServices publicServices;

    public ApTelephonicConsultation getRecord(String key) throws SQLException {
        try (
                Connection con = DS.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from ap_telephonic_consultation where key = '" + key + "'");) {
            ApTelephonicConsultation record = new ApTelephonicConsultation();
            if (rs.next()) {
                record.setKey(rs.getString("key"));
                record.setPatientKey(rs.getString("patient_key"));
                record.setEncounterKey(rs.getString("encounter_key"));
                record.setPhysician(rs.getBigDecimal("physician"));
                record.setDateOfCall(rs.getBigDecimal("date_of_call"));
                record.setConsultationContent(rs.getString("consultation_content"));
                record.setApprovalNumber(rs.getString("approval_number"));
                record.setNotes(rs.getString("notes"));
                record.setExtraDocumentation(rs.getString("extra_documentation"));
                record.setCreatedBy(rs.getString("created_by"));
                record.setUpdatedBy(rs.getString("updated_by"));
                record.setDeletedBy(rs.getString("deleted_by"));
                record.setCreatedAt(rs.getBigDecimal("created_at"));
                record.setUpdatedAt(rs.getBigDecimal("updated_at"));
                record.setDeletedAt(rs.getBigDecimal("deleted_at"));
                record.setIsValid(rs.getBoolean("is_valid"));
                record.setCancellationReason(rs.getString("cancellation_reason"));
            } else {
                record = null;
            }
            return record;
        }
    }

    public void updateRecord(ApTelephonicConsultation record) throws SQLException {
        try (
                Connection con = DS.getConnection();
                PreparedStatement ps = con.prepareStatement("update ap_telephonic_consultation set key = ?, patient_key = ?, encounter_key = ?, physician = ?, date_of_call = ?, consultation_content = ?, approval_number = ?, notes = ?, extra_documentation = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ?,cancellation_reason=?  where key = ?");
        ) {
            record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
            ps.setString(1, record.getKey());
            ps.setString(2, record.getPatientKey());
            ps.setString(3, record.getEncounterKey());
            ps.setBigDecimal(4, record.getPhysician());
            ps.setBigDecimal(5, record.getDateOfCall());
            ps.setString(6, record.getConsultationContent());
            ps.setString(7, record.getApprovalNumber());
            ps.setString(8, record.getNotes());
            ps.setString(9, record.getExtraDocumentation());
            ps.setString(10, record.getCreatedBy());
            ps.setString(11, record.getUpdatedBy());
            ps.setString(12, record.getDeletedBy());
            ps.setBigDecimal(13, record.getCreatedAt());
            ps.setBigDecimal(14, record.getUpdatedAt());
            ps.setBigDecimal(15, record.getDeletedAt());
            ps.setBoolean(16, record.getIsValid());
            ps.setString(17, record.getCancellationReason());
            ps.setString(18, record.getKey());
            ps.executeUpdate();
        }
    }

    public void deleteRecord(ApTelephonicConsultation record) throws SQLException {
        try (
                Connection con = DS.getConnection();
                PreparedStatement ps = con.prepareStatement("update ap_telephonic_consultation set  deleted_at = '" + System.currentTimeMillis() + "' where key = ?");
        ) {
            ps.setString(1, record.getKey());
            ps.executeUpdate();
        }
    }

    public List<ApTelephonicConsultation> getList(String where) throws SQLException {
        if (where == null || where.isEmpty()) where = "1=1";
        try (
                Connection con = DS.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from ap_telephonic_consultation where " + where);) {
            List<ApTelephonicConsultation> list = new ArrayList<ApTelephonicConsultation>();
            while (rs.next()) {
                ApTelephonicConsultation record = new ApTelephonicConsultation();
                record.setKey(rs.getString("key"));
                record.setPatientKey(rs.getString("patient_key"));
                record.setEncounterKey(rs.getString("encounter_key"));
                record.setPhysician(rs.getBigDecimal("physician"));
                record.setDateOfCall(rs.getBigDecimal("date_of_call"));
                record.setConsultationContent(rs.getString("consultation_content"));
                record.setApprovalNumber(rs.getString("approval_number"));
                record.setNotes(rs.getString("notes"));
                record.setExtraDocumentation(rs.getString("extra_documentation"));
                record.setCreatedBy(rs.getString("created_by"));
                record.setUpdatedBy(rs.getString("updated_by"));
                record.setDeletedBy(rs.getString("deleted_by"));
                record.setCreatedAt(rs.getBigDecimal("created_at"));
                record.setUpdatedAt(rs.getBigDecimal("updated_at"));
                record.setDeletedAt(rs.getBigDecimal("deleted_at"));
                record.setIsValid(rs.getBoolean("is_valid"));
                record.setCancellationReason(rs.getString("cancellation_reason"));
                list.add(record);
            }
            return list;
        }
    }

    public String saveRecord(ApTelephonicConsultation record) throws SQLException {
        try (
                Connection con = DS.getConnection();
                PreparedStatement ps = con.prepareStatement("insert into ap_telephonic_consultation values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        ) {
            if (record.getKey() != null && !record.getKey().isEmpty()) {
                updateRecord(record);
                return record.getKey();
            }
            if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
            String key = "" + System.nanoTime();
            record.setKey(key);

            ps.setString(1, key);
            ps.setString(2, record.getPatientKey());
            ps.setString(3, record.getEncounterKey());
            ps.setBigDecimal(4, record.getPhysician());
            ps.setBigDecimal(5, record.getDateOfCall());
            ps.setString(6, record.getConsultationContent());
            ps.setString(7, record.getApprovalNumber());
            ps.setString(8, record.getNotes());
            ps.setString(9, record.getExtraDocumentation());
            ps.setString(10, record.getCreatedBy());
            ps.setString(11, record.getUpdatedBy());
            ps.setString(12, record.getDeletedBy());
            ps.setBigDecimal(13, record.getCreatedAt());
            ps.setBigDecimal(14, record.getUpdatedAt());
            ps.setBigDecimal(15, record.getDeletedAt());
            ps.setBoolean(16, record.getIsValid());
            ps.executeUpdate();
            return key;
        }
    }

    public void populateLovFields(ApTelephonicConsultationEntity entity, String lang) {
        Class<?> myClass = ApTelephonicConsultationEntity.class;
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

    public void translateObject(ApTelephonicConsultationEntity entity, String lang) {
        ApTelephonicConsultationEntity translated = (ApTelephonicConsultationEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}