package com.asklepios.backend_service.model.DTO;

import lombok.Data;

import java.util.List;

public class ResourceAvailabilityRequestDTO {
    private String facility;
    private String resource;
    private List<AvailabilityEntryDTO> availability;


    public String getFacility() { return facility; }
    public void setFacility(String facility) { this.facility = facility; }
    public String getResource() { return resource; }
    public void setResource(String resource) { this.resource = resource; }
    public List<AvailabilityEntryDTO> getAvailability() { return availability; }
    public void setAvailability(List<AvailabilityEntryDTO> availability) { this.availability = availability; }
}
