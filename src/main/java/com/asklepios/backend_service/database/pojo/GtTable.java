package com.asklepios.backend_service.database.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GtTable {
    String tableName;
    List<GtColumn> columns;
}
