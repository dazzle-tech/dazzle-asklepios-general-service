package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.asklepios.backend_service.model.generated.pojo.ApActiveIngredient;
import com.asklepios.backend_service.model.generated.pojo.ApActiveIngredientDrugInteraction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApActiveIngredientDrugInteractionDAO;

@Service
@Slf4j
public class ApActiveIngredientDrugInteractionService extends ApActiveIngredientDrugInteractionDAO implements Serializable {


    private final ApActiveIngredientDrugInteractionDAO apActiveIngredientDrugInteractionDAO;
    private final ApActiveIngredientService apActiveIngredientService;

    public ApActiveIngredientDrugInteractionService(@Qualifier("apActiveIngredientDrugInteractionDAO") ApActiveIngredientDrugInteractionDAO apActiveIngredientDrugInteractionDAO, ApActiveIngredientService apActiveIngredientService) {
        super();
        this.apActiveIngredientDrugInteractionDAO = apActiveIngredientDrugInteractionDAO;
        this.apActiveIngredientService = apActiveIngredientService;
    }

    public List<ApActiveIngredientDrugInteraction> getActiveIngredientDrug(String activeKey) throws SQLException {

        List<ApActiveIngredientDrugInteraction> list = apActiveIngredientDrugInteractionDAO.getList("active_ingredient_key ='"+ activeKey +"' or interacted_active_ingredient_key = '"+ activeKey +"'");
//        if(!list.isEmpty()){
//            List<ApActiveIngredient> apActiveIngredients = new ArrayList<ApActiveIngredient>();
//            for(ApActiveIngredientDrugInteraction active : list){
//                 if(!active.getActiveIngredientKey().equals(activeKey)){
//                     apActiveIngredients.add(apActiveIngredientService.getRecord(active.getActiveIngredientKey()));
//                 }else if (!active.getInteractedActiveIngredientKey().equals(activeKey)){
//                     apActiveIngredients.add(apActiveIngredientService.getRecord(active.getInteractedActiveIngredientKey()));
//                 }
//            }
//            return apActiveIngredients;
//        }

        return list ;
    }
}