package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.ApPatientSecondaryDocuments;
import com.asklepios.backend_service.model.pojo.response.ApAllergiesResponse;
import com.asklepios.backend_service.model.pojo.response.ApPatientSecondaryDocsResponce;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApPatientSecondaryDocumentsDAO;

@Service
@Slf4j
public class ApPatientSecondaryDocumentsService extends ApPatientSecondaryDocumentsDAO implements Serializable {

    public List<ApPatientSecondaryDocuments> getApPatientSecondaryDocsList(String where) throws SQLException {
        System.out.println("where");
        System.out.println(where);

        if (where == null || where.isEmpty()) where = "1=1";
        try (
                Connection con = DS.getConnection();
                Statement st = con.createStatement();

                ResultSet rs = st.executeQuery("select * from apv_patient_secondary_documents where " + where);) {
            List<ApPatientSecondaryDocuments> list = new ArrayList<ApPatientSecondaryDocuments>();
            while (rs.next()) {
                ApPatientSecondaryDocuments record = new ApPatientSecondaryDocuments();
                record.setDocContry(rs.getString("doc_contry"));
                record.setDocType(rs.getString("doc_type"));
                record.setKey(rs.getString("key"));
                record.setDocumentCountryLkey(rs.getString("document_country_lkey"));
                record.setDocumentTypeLkey(rs.getString("document_type_lkey"));
                record.setDocumentNo(rs.getString("document_no"));
                record.setPatientKey(rs.getString("patient_key"));
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