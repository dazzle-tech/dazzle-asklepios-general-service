package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.ApActiveIngredientDrugInteraction;
import com.asklepios.backend_service.model.generated.pojo.ApIcdCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApActiveIngredientIndicationDAO;

@Service
@Slf4j
public class ApActiveIngredientIndicationService extends ApActiveIngredientIndicationDAO implements Serializable {

    private final ApIcdCodeService apIcdCodeService;

    public ApActiveIngredientIndicationService(ApIcdCodeService apIcdCodeService) {
        super();
        this.apIcdCodeService = apIcdCodeService;
    }

    public String getICD(String IcdCode) throws SQLException {
        List<ApIcdCode> list = apIcdCodeService.getList("key ='"+ IcdCode +"'");
        if(!list.isEmpty()){
           return list.get(0).getIcdCode() + "," + list.get(0).getDescription() ;
        }
        return null;
    }
}