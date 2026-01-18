package com.asklepios.backend_service.controller;

import com.asklepios.backend_service.model.DTO.PatientReportRequestDTO;
import com.asklepios.backend_service.service.PatientReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pas/report")
@RequiredArgsConstructor
public class PatientReportController {

    private final PatientReportService patientReportService;

    @PostMapping(value = "/generate", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generatePatientReport(
            @RequestBody PatientReportRequestDTO request,
            @RequestHeader(required = false, name = "facility_id") String facilityId,
            @RequestHeader(required = false, name = "access_level") Integer accessLevel,
            @RequestHeader(required = false, name = "lang") String lang
    ) {
        if (lang == null || lang.isBlank()) lang = "en";

        byte[] pdfBytes = patientReportService.generatePatientPdf(request, lang);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        String mrn = (request.getPatient() != null && request.getPatient().getPatientMrn() != null)
                ? request.getPatient().getPatientMrn()
                : "unknown";

        headers.setContentDisposition(
                ContentDisposition.inline()
                        .filename("patient-" + mrn + ".pdf")
                        .build()
        );

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}
