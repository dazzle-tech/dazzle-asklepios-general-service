package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.ApAttachment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApAttachmentDAO;

@Service
@Slf4j
public class ApAttachmentService extends ApAttachmentDAO implements Serializable {


    public List<ApAttachment> getAttachmentsList(String where) throws SQLException {
        if (where == null || where.isEmpty()) where = "1=1";
        try (
                Connection con = DS.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from ap_attachment_light where "+ where);) {
            List<ApAttachment> list = new ArrayList<ApAttachment>();
            while(rs.next()){
                ApAttachment record = new ApAttachment();
                record.setKey(rs.getString("key"));
                record.setAttachmentType(rs.getString("attachment_type"));
                record.setReferenceObjectKey(rs.getString("reference_object_key"));
                record.setExtraDetails(rs.getString("extra_details"));
                record.setFileName(rs.getString("file_name"));
                record.setContentType(rs.getString("content_type"));
                record.setCreatedBy(rs.getString("created_by"));
                record.setUpdatedBy(rs.getString("updated_by"));
                record.setDeletedBy(rs.getString("deleted_by"));
                record.setCreatedAt(rs.getBigDecimal("created_at"));
                record.setUpdatedAt(rs.getBigDecimal("updated_at"));
                record.setDeletedAt(rs.getBigDecimal("deleted_at"));
                record.setIsValid(rs.getBoolean("is_valid"));
                list.add(record);
            }
            return list;
        }
    }
}