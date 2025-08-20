package com.asklepios.backend_service.model.DTO;

import java.util.List;

public class AvailabilityEntryDTO {
    private String day;
    private List<TimeSliceDTO> slices;

    public String getDay() { return day; }
    public void setDay(String day) { this.day = day; }
    public List<TimeSliceDTO> getSlices() { return slices; }
    public void setSlices(List<TimeSliceDTO> slices) { this.slices = slices; }
}