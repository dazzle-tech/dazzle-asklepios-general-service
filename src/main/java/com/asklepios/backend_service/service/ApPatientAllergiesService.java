package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.pojo.response.ApAllergiesResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApPatientAllergiesDAO;

@Service
@Slf4j
public class ApPatientAllergiesService extends ApPatientAllergiesDAO implements Serializable {

    public List<ApAllergiesResponse> getApPatientAllergiesList(String where) throws SQLException {
        if (where == null || where.isEmpty()) where = "1=1";
        try (
                Connection con = DS.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from apv_patient_allergies where "+ where);) {
            List<ApAllergiesResponse> list = new ArrayList<ApAllergiesResponse>();
            while(rs.next()){
                ApAllergiesResponse record = new ApAllergiesResponse();
                record.setPatientKey(rs.getString("patient_key"));
                record.setAllergensKey(rs.getString("allergens_key"));
                record.setAllergenCode(rs.getString("allergen_code"));
                record.setAllergenName(rs.getString("allergen_name"));
                record.setAllergenType(rs.getString("allergen_type"));
                record.setIsValid(rs.getBoolean("is_valid"));
                list.add(record);
            }
            return list;
        }
    }


}