package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.sql.SQLException;

import com.asklepios.backend_service.model.generated.pojo.ApDvmRule;
import com.asklepios.backend_service.model.generated.pojo.ApMetadataField;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApDvmRuleDAO;

@Service
@Slf4j
public class ApDvmRuleService extends ApDvmRuleDAO implements Serializable {

    private final ApMetadataFieldService apMetadataFieldService;

    public ApDvmRuleService(ApMetadataFieldService apMetadataFieldService) {
        this.apMetadataFieldService = apMetadataFieldService;
    }

    @Override
    public String saveRecord(ApDvmRule record) throws SQLException {
        // de-normalize field information
        ApMetadataField field = apMetadataFieldService.getRecord(record.getFieldKey());
        if(field != null){
            record.setFieldDataType(field.getDataType());
            record.setFieldName(field.getFieldName());
        }
        record.setIsFieldLov(false);
        record.setIsFieldRef(false);
        return super.saveRecord(record);
    }

}