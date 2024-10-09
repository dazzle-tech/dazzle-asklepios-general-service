package com.asklepios.backend_service.model.pojo.response;

import com.asklepios.backend_service.model.generated.pojo.ApAccessToken;
import com.asklepios.backend_service.model.generated.pojo.ApUser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse  {
    ApUser user;
    ApAccessToken token;
}
