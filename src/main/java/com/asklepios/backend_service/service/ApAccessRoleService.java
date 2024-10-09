package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import com.asklepios.backend_service.model.generated.pojo.ApAccessRole;
import com.asklepios.backend_service.model.generated.pojo.ApAccessRoleScreen;
import com.asklepios.backend_service.model.generated.pojo.ApScreen;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApAccessRoleDAO;

@Service
@Slf4j
public class ApAccessRoleService extends ApAccessRoleDAO implements Serializable {

}