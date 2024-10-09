package com.asklepios.backend_service.model.pojo.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    String username;
    String password;
    String orgKey;
}
