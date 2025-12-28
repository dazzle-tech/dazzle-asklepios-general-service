package com.asklepios.backend_service.controller;

import com.asklepios.backend_service.model.DTO.PrescriptionReportRequestDTO;
import com.asklepios.backend_service.service.PrescriptionReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/observation/prescription")
@RequiredArgsConstructor
public class PrescriptionReportController {

    private final PrescriptionReportService prescriptionReportService;

    @PostMapping(value = "/generate", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generatePrescription(
            @RequestBody PrescriptionReportRequestDTO request,
            @RequestHeader(required = false, name = "facility_id") String facilityId,
            @RequestHeader(required = false, name = "access_level") Integer accessLevel,
            @RequestHeader(required = false, name = "lang") String lang
    ) {
        if (lang == null || lang.isBlank()) lang = "en";


        byte[] pdfBytes = prescriptionReportService.generatePrescriptionPdf(
                request.getPatient(),
                request.getEncounter(),
                facilityId,
                accessLevel,
                request.getPrescriptionKey(),
                lang,
                request.getGenericMedicationList(),
                request.getFacilityName(),
                request.getAuthenticatedUserName(),
                request.getAuthenticatedUserEmail(),
                request.getPredefinedInstructions(),
                request.getCustomInstructions()
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        String encounterKey =
                (request.getEncounter() != null && request.getEncounter().getKey() != null)
                        ? request.getEncounter().getKey()
                        : "unknown";

        headers.setContentDisposition(
                ContentDisposition.inline()
                        .filename("prescription-" + encounterKey + ".pdf")
                        .build()
        );

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}
