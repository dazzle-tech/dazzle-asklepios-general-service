package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.ApUserFacilities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApUserFacilitiesDAO;

@Service
@Slf4j
public class ApUserFacilitiesService extends ApUserFacilitiesDAO implements Serializable {
    public List<ApUserFacilities> getListOfKey(String where) throws SQLException {
        if (where == null || where.isEmpty()) where = "1=1";
        try (
                Connection con = DS.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from ap_user_facilities where "+ where);) {
            List<ApUserFacilities> list = new ArrayList<ApUserFacilities>();
            while(rs.next()){
                ApUserFacilities record = new ApUserFacilities();
                record.setKey(rs.getString("key"));
                record.setUserId(rs.getString("user_id"));
                record.setFacilityId(rs.getString("facility_id"));
                record.setCreatedBy(rs.getString("created_by"));
                record.setUpdatedBy(rs.getString("updated_by"));
                record.setDeletedBy(rs.getString("deleted_by"));
                record.setCreatedAt(rs.getBigDecimal("created_at"));
                record.setUpdatedAt(rs.getBigDecimal("updated_at"));
                record.setDeletedAt(rs.getBigDecimal("deleted_at"));
                record.setIsValid(rs.getBoolean("is_valid"));
                record.setUserKey(rs.getString("user_key"));
                record.setFacilityKey(rs.getString("facility_key"));
                list.add(record);
            }
            return list;
        }
    }
}