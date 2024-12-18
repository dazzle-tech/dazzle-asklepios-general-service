package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.asklepios.backend_service.model.generated.pojo.ApGenericMedication;
import com.asklepios.backend_service.model.generated.pojo.ApGenericMedicationRoa;
import com.asklepios.backend_service.model.generated.pojo.ApPractitioner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApGenericMedicationDAO;

@Service
@Slf4j
public class ApGenericMedicationService extends ApGenericMedicationDAO implements Serializable {

}