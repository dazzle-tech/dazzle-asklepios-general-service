package com.asklepios.backend_service.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EncounterLocationResponse {
    private String encounterId;
    private String bedKey;
    private String bedName;
    private String roomKey;
    private String roomName;
}