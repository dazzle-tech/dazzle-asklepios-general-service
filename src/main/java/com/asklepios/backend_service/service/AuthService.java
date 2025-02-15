package com.asklepios.backend_service.service;


import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.ApAccessToken;
import com.asklepios.backend_service.model.generated.pojo.ApTenant;
import com.asklepios.backend_service.model.generated.pojo.ApUser;
import com.asklepios.backend_service.model.pojo.request.LoginRequest;
import com.asklepios.backend_service.model.pojo.response.LoginResponse;
import com.asklepios.backend_service.model.pojo.response.ParentResponse;
import com.asklepios.backend_service.util.Utilities;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@Service
public class AuthService {

    private final ApAccessTokenService service;
    private final ApUserService userService;
    private final ApTenantService apTenantService;

    public AuthService(ApAccessTokenService accessTokenService, ApUserService userService, ApTenantService apTenantService) {
        this.service = accessTokenService;
        this.userService = userService;
        this.apTenantService = apTenantService;
    }

    public ResponseEntity login(LoginRequest request) {
        ParentResponse<LoginResponse> response = new ParentResponse<>();
        try (Connection connection = DS.getConnection()) {

            System.out.println(request.getUsername());
            System.out.println(request.getPassword());
            System.out.println(request.getOrgKey());

            PreparedStatement statement = connection.prepareStatement("SELECT u.key  FROM ap_user u INNER JOIN ap_user_facilities uf ON u.key = uf.user_id WHERE u.username = ? AND u.password = ? AND uf.facility_id = ?  ");
             statement.setString(1, request.getUsername());
            statement.setString(2, request.getPassword());
            statement.setString(3, request.getOrgKey());

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ApUser user = userService.getRecord(resultSet.getString(1));
                LoginResponse _response = new LoginResponse();
                _response.setUser(user);
                _response.setToken(issueToken(user.getKey(), false));
                response.setObject(_response);
                response.setMsg("Logged in successfully as: " + user.getFullName());
                return ResponseEntity.ok(response);
            } else {
                response.addGeneralError("Wrong credentials");
                return ResponseEntity.status(401).body(response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            response.addGeneralError(ex.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

    public ResponseEntity autoLogin(String accessToken) {
        ParentResponse<LoginResponse> response = new ParentResponse<>();

        try {
            ApAccessToken token = validateToken(accessToken);
            if (token == null) {
                response.addGeneralError("Invalid token");
                return ResponseEntity.status(401).body(response);
            }
            ApUser user = userService.getRecord(token.getUserKey());
            if (user == null) {
                response.addGeneralError("Token doesn't belong to user");
                return ResponseEntity.status(401).body(response);
            }

            LoginResponse _response = new LoginResponse();
            _response.setToken(token);
            _response.setUser(user);

            response.setMsg("Logged in successfully as: " + user.getFullName());
            response.setObject(_response);

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.addGeneralError(ex.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }


    public ApAccessToken issueToken(String userKey, boolean expires) throws Exception {
        ApAccessToken token = new ApAccessToken();
        token.setUserKey(userKey);
        token.setCreatedAt(BigDecimal.valueOf(System.currentTimeMillis()));
        token.setCanExpire(expires);
        if (token.getCanExpire()) {
            token.setExpiresAt(token.getCreatedAt().add(BigDecimal.valueOf(86400000))); // 24H in milliseconds
        }
        token.setAccessToken(Utilities.generateToken());
        service.saveRecord(token);
        return token;
    }

    public ApAccessToken validateToken(String _token) throws Exception {
        List<ApAccessToken> tokenList = service.getList("access_token = '" + _token + "'");
        if (tokenList == null || tokenList.isEmpty()) {
            return null;
        }

        ApAccessToken token = tokenList.get(0);
        if (token.getCanExpire() && System.currentTimeMillis() > token.getExpiresAt().longValue()) {
            // expired, invalidate and generate new token
            invalidateToken(_token);
            return issueToken(token.getUserKey(), token.getCanExpire());
        }

        // valid
        return token;
    }

    public boolean validateTenantToken(String _token, String _tenant_id) throws Exception {
        List<ApTenant> tokenList = apTenantService.getList("tenant_id = '" + _tenant_id + "' and tenant_security_token='" + _token + "'");
        if (tokenList == null || tokenList.isEmpty()) {
            return false;
        }


        return true;
    }

    public void invalidateToken(String _token) throws Exception {
        DS.executeQuery("delete from ap_access_token where access_token = '" + _token + "'");
    }

}
