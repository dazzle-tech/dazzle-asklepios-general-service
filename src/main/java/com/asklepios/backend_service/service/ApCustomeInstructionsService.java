package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.sql.SQLException;

import com.asklepios.backend_service.model.generated.pojo.ApCustomeInstructions;
import com.asklepios.backend_service.model.generated.pojo.ApPrescriptionMedications;
import com.asklepios.backend_service.model.pojo.response.ParentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApCustomeInstructionsDAO;

@Service
@Slf4j
public class ApCustomeInstructionsService extends ApCustomeInstructionsDAO implements Serializable {


}