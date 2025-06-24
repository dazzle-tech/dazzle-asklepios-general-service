package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.ApBed;
import com.asklepios.backend_service.model.generated.pojo.ApRoom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApBedDAO;

@Service
@Slf4j
public class ApBedService extends ApBedDAO implements Serializable {
    public void deactive_avtice_Record(ApBed record) throws SQLException {
        try (
                Connection con = DS.getConnection();
                PreparedStatement ps = con.prepareStatement("update ap_bed set  is_valid = "+ !record.getIsValid()+" where key = ?");
        ) {
            ps.setString(1, record.getKey());
            ps.executeUpdate();
        }
    }

    public List<Map<String, Object>> getBedsByDepartmentKey(String departmentKey, String lang) throws SQLException {
        List<Map<String, Object>> result = new ArrayList<>();

        String roomQuery = "SELECT key, name FROM ap_room WHERE department_key = ? AND deleted_at IS NULL";
        String bedQuery = "SELECT key, name FROM ap_bed WHERE room_key = ? AND deleted_at IS NULL";

        try (
                Connection con = DS.getConnection();
                PreparedStatement psRoom = con.prepareStatement(roomQuery)
        ) {
            psRoom.setString(1, departmentKey);

            try (ResultSet rsRoom = psRoom.executeQuery()) {
                while (rsRoom.next()) {
                    String roomKey = rsRoom.getString("key");
                    String roomName = rsRoom.getString("name");

                    try (PreparedStatement psBed = con.prepareStatement(bedQuery)) {
                        psBed.setString(1, roomKey);
                        try (ResultSet rsBed = psBed.executeQuery()) {
                            while (rsBed.next()) {
                                Map<String, Object> bedMap = new HashMap<>();
                                bedMap.put("key", rsBed.getString("key"));
                                ApBed apBed = getRecord(rsBed.getString("key"));
                                populateLovFields(apBed, lang);
                                bedMap.put("bed",apBed);
                                bedMap.put("roomName", roomName);
                                result.add(bedMap);
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return result;
    }



}