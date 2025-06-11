package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.ApRoom;
import com.asklepios.backend_service.model.generated.pojo.ApVaccine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApRoomDAO;

@Service
@Slf4j
public class ApRoomService extends ApRoomDAO implements Serializable {
    public void deactive_avtice_Record(ApRoom record) throws SQLException {
        try (
                Connection con = DS.getConnection();
                PreparedStatement ps = con.prepareStatement("update ap_room set  is_valid = "+ !record.getIsValid()+" where key = ?");
        ) {
            ps.setString(1, record.getKey());
            ps.executeUpdate();
        }
    }

}