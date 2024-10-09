package com.asklepios.backend_service.database.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GtColumn {
    String name;
    String type;
    String references;
}
