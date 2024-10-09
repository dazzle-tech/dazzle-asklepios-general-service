package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.ApAddresses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApAddressesDAO;

@Service
@Slf4j
public class ApAddressesService extends ApAddressesDAO implements Serializable {
    public ApAddresses getRecordByentityId(String entityId) throws SQLException {
        try (
                Connection con = DS.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from ap_addresses where entity_id = '"+entityId+"'");) {
            ApAddresses record = new ApAddresses();
            if(rs.next()){
                record.setKey(rs.getString("key"));
                record.setEntityId(rs.getString("entity_id"));
                record.setEntityTypeLkey(rs.getString("entity_type_lkey"));
                record.setAddressTypeLkey(rs.getString("address_type_lkey"));
                record.setStreetAddressLine1(rs.getString("street_address_line1"));
                record.setStreetAddressLine2(rs.getString("street_address_line2"));
                record.setCountryLkey(rs.getString("country_lkey"));
                record.setStateProvinceRegionLkey(rs.getString("state_province_region_lkey"));
                record.setCityLkey(rs.getString("city_lkey"));
                record.setPostalCode(rs.getString("postal_code"));
                record.setAdditionalInfo(rs.getString("additional_info"));
                record.setLatitude(rs.getString("latitude"));
                record.setLongitude(rs.getString("longitude"));
                record.setIsActive(rs.getString("is_active"));
                record.setCreatedBy(rs.getString("created_by"));
                record.setUpdatedBy(rs.getString("updated_by"));
                record.setDeletedBy(rs.getString("deleted_by"));
                record.setCreatedAt(rs.getBigDecimal("created_at"));
                record.setUpdatedAt(rs.getBigDecimal("updated_at"));
                record.setDeletedAt(rs.getBigDecimal("deleted_at"));
                record.setIsValid(rs.getBoolean("is_valid"));
            } else { record = null; }
            return record;
        }
    }
}