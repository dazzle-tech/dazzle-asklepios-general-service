package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.ApUserFacilitiyDepartments;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApUserFacilitiyDepartmentsDAO;

@Service
@Slf4j
public class ApUserFacilitiyDepartmentsService extends ApUserFacilitiyDepartmentsDAO implements Serializable {
    public List<ApUserFacilitiyDepartments> getuserDpartmentsViewList(String where) throws SQLException {
        if (where == null || where.isEmpty()) where = "1=1";
        try (
                Connection con = DS.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select UFD.key,F.facility_name facilityName,D.name departmentName,UFD.* from ap_user_facilitiy_departments UFD\n" +
                        "LEFT JOIN ap_facility F on UFD.facilitiy_key = F.key\n" +
                        "LEFT JOIN ap_department D on UFD.department_key = D.key\n" +
                        "where UFD.deleted_at is null and "+ where);) {
            List<ApUserFacilitiyDepartments> list = new ArrayList<ApUserFacilitiyDepartments>();
            while(rs.next()){
                ApUserFacilitiyDepartments record = new ApUserFacilitiyDepartments();
                record.setKey(rs.getString("key"));
                record.setUserKey(rs.getString("user_key"));
                record.setFacilitiyKey(rs.getString("facilitiy_key"));
                record.setDepartmentKey(rs.getString("department_key"));
                record.setCreatedBy(rs.getString("created_by"));
                record.setUpdatedBy(rs.getString("updated_by"));
                record.setDeletedBy(rs.getString("deleted_by"));
                record.setCreatedAt(rs.getBigDecimal("created_at"));
                record.setUpdatedAt(rs.getBigDecimal("updated_at"));
                record.setDeletedAt(rs.getBigDecimal("deleted_at"));
                record.setIsValid(rs.getBoolean("is_valid"));
                record.setDepartmentName(rs.getString("departmentname"));
                record.setFacilityName(rs.getString("facilityname"));

                list.add(record);
            }
            return list;
        }
    }

}