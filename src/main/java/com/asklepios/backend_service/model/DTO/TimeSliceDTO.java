package com.asklepios.backend_service.model.DTO;

import lombok.Data;

@Data
public class TimeSliceDTO {
    private long startTimeMinutes;
    private long endTimeMinutes;
    private boolean isBreak;
}