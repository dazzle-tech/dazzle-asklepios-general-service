package com.asklepios.backend_service.model.pojo.response;

import com.asklepios.backend_service.model.generated.pojo.ApDentalChart;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class DentalChartResponse implements Serializable {
    ApDentalChart currentChart;
    List<ApDentalChart> historyCharts;
}
