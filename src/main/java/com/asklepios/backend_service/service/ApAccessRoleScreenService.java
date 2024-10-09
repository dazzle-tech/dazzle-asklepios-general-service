package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.ApAccessRole;
import com.asklepios.backend_service.model.generated.pojo.ApAccessRoleScreen;
import com.asklepios.backend_service.model.generated.pojo.ApScreen;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApAccessRoleScreenDAO;

@Service
@Slf4j
public class ApAccessRoleScreenService extends ApAccessRoleScreenDAO implements Serializable {
    private final ApAccessRoleService apAccessRoleService;
    private final ApScreenService apScreenService;

    public ApAccessRoleScreenService(ApAccessRoleService apAccessRoleService, ApScreenService apScreenService) {
        this.apAccessRoleService = apAccessRoleService;
        this.apScreenService = apScreenService;
    }

    public void buildMatrixBasedOnData() throws SQLException {
        List<ApScreen> screens = apScreenService.getList(null);
        List<ApAccessRole> accessRoles = apAccessRoleService.getList(null);
        for (ApScreen screen : screens) {
            for (ApAccessRole accessRole : accessRoles) {
                // check if this combination exists
                BigDecimal check = DS.executeDecimalResultQuery("select count(0) from ap_access_role_screen where screen_key = '"
                        + screen.getKey() + "' and access_role_key = '" + accessRole.getKey() + "'");
                if (check != null && check.intValue() > 0) {
                    // record already exists
                } else {
                    // create a new record
                    ApAccessRoleScreen accessRoleScreen = new ApAccessRoleScreen();
                    accessRoleScreen.setCreatedAt(BigDecimal.valueOf(System.currentTimeMillis()));
                    accessRoleScreen.setAccessRoleKey(accessRole.getKey());
                    accessRoleScreen.setScreenKey(screen.getKey());
                    accessRoleScreen.setCanRead(false);
                    accessRoleScreen.setCanWrite(false);
                    accessRoleScreen.setCanDelete(false);
                    saveRecord(accessRoleScreen);
                }

            }
        }
    }

}