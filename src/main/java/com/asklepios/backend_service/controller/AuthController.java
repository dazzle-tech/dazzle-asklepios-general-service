package com.asklepios.backend_service.controller;

import com.asklepios.backend_service.model.pojo.request.LoginRequest;
import com.asklepios.backend_service.model.pojo.response.ParentResponse;
import com.asklepios.backend_service.service.AuthService;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController implements Serializable {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @SneakyThrows
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@RequestBody LoginRequest request ) {
        return authService.login(request);
    }


    @GetMapping(value = "/autoLogin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity autoLogin(@Nullable @RequestHeader("access_token") String accessToken) {
        return authService.autoLogin(accessToken);
    }

    @PostMapping(value = "/reset-password-phase-1", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity resetPasswordPhaseOne(@RequestHeader("access_token") String accessToken) {
        return authService.autoLogin(accessToken);
    }


    @PostMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity logout(@RequestHeader("access_token") String accessToken) {
        try {
            authService.invalidateToken(accessToken);
            ParentResponse<String> response = new ParentResponse<>();
            response.setObject("Signed out successfully");
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().body(ex.getMessage().isBlank() ? ex : ex.getMessage());
        }
    }
}
