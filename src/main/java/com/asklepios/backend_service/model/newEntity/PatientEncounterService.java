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
public class PatientEncounterService {


    public PatientEncounter getRecord(Long key) throws SQLException {
        String sql = "select * from patient_encounters where id = ?";

        try (
                Connection con = DS.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setLong(1, key);

            try (ResultSet rs = ps.executeQuery()) {
                PatientEncounter record = new PatientEncounter();

                if (rs.next()) {
                    record.setKey(rs.getLong("id"));

                    record.setPatientKey(rs.getLong("patient_id"));

                    record.setFollowUpEncounterId(rs.getString("follow_up_encounter_id"));

                    record.setFacilityId(rs.getLong("facility_id"));

                    record.setDepartmentId(rs.getLong("department_id"));

                    record.setPractitionerId(rs.getLong("practitioner_id"));

                    record.setEncounterNumber(rs.getString("encounter_number"));
                    record.setEncounterType(rs.getString("encounter_type"));
                    record.setEncounterReason(rs.getString("encounter_reason"));
                    record.setPriorityLevel(rs.getString("priority"));

                    record.setOriginType(rs.getString("origin_type"));
                    record.setOriginName(rs.getString("origin_name"));
                    record.setNotes(rs.getString("notes"));


                    record.setDepartmentDailySequenceNumber(rs.getInt("department_daily_sequence_number"));

                    record.setEncounterDate(
                            rs.getDate("encounter_date") != null
                                    ? rs.getDate("encounter_date").toString()
                                    : null
                    );

                    record.setStatus(rs.getString("status"));
                    record.setChiefComplaint(rs.getString("chief_complaint"));
                    record.setDischargeType(rs.getString("discharge_type"));

                    record.setDischargeAt(
                            rs.getTimestamp("discharge_at") != null
                                    ? rs.getTimestamp("discharge_at").toString()
                                    : null
                    );
                } else {
                    record = null;
                }

                return record;
            }
        }
    }
}