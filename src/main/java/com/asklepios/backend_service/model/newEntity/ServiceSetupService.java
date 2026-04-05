package com.asklepios.backend_service.model.newEntity;

import com.asklepios.backend_service.database.DS;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@Slf4j
@Service
public class ServiceSetupService {

    public ServiceSetupRecord getRecord(Long key) throws SQLException {
        String sql = "select * from service where id = ?";

        try (
                Connection con = DS.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setLong(1, key);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }

                ServiceSetupRecord record = new ServiceSetupRecord();

                record.setKey((Long) rs.getObject("id"));
                record.setName(rs.getString("name"));
                record.setAbbreviation(rs.getString("abbreviation"));
                record.setCode(rs.getString("code"));
                record.setCategory(rs.getString("category"));
                record.setPrice(rs.getBigDecimal("price"));
                record.setCurrency(rs.getString("currency"));
                record.setIsActive((Boolean) rs.getObject("is_active"));
                record.setFacilityId((Long) rs.getObject("facility_id"));
                record.setAppointable((Boolean) rs.getObject("appointable"));
                record.setParallelCapacityValue((Integer) rs.getObject("parallel_capacity_value"));
                record.setDefaultDurationMinutes((Integer) rs.getObject("default_duration_minutes"));
                record.setDefaultBufferBeforeMinutes((Integer) rs.getObject("default_buffer_before_minutes"));
                record.setDefaultBufferAfterMinutes((Integer) rs.getObject("default_buffer_after_minutes"));

                return record;
            }
        }
    }
}
