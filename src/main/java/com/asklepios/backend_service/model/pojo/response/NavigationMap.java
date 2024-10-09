package com.asklepios.backend_service.model.pojo.response;

import com.asklepios.backend_service.model.generated.pojo.ApModule;
import com.asklepios.backend_service.model.generated.pojo.ApScreen;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NavigationMap {
    List<ApScreen> screens;
    List<ApModule> modules;
}
