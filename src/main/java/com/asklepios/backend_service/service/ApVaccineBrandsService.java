package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.ApVaccine;
import com.asklepios.backend_service.model.generated.pojo.ApVaccineBrands;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApVaccineBrandsDAO;

@Service
@Slf4j
public class ApVaccineBrandsService extends ApVaccineBrandsDAO implements Serializable {

    public void deactive_avtice_VaccineBrand(ApVaccineBrands record) throws SQLException {
        try (
                Connection con = DS.getConnection();
                PreparedStatement ps = con.prepareStatement("update ap_vaccine_brands set  is_valid = "+ !record.getIsValid()+" where key = ?");
        ) {
            ps.setString(1, record.getKey());
            ps.executeUpdate();
        }
    }
}