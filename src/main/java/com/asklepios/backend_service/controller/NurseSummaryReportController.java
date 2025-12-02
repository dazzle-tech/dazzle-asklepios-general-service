package com.asklepios.backend_service.controller;

import com.asklepios.backend_service.model.DTO.NurseSummaryReportRequestDTO;
import com.asklepios.backend_service.service.NurseSummaryReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/observation")
@RequiredArgsConstructor
public class NurseSummaryReportController {

    private final NurseSummaryReportService nurseSummaryReportService;

    @PostMapping(value = "/nurse-summary", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generateNurseSummary(
            @RequestBody NurseSummaryReportRequestDTO request,
            @RequestHeader(required = false, name = "facility_id") String facilityId,
            @RequestHeader(required = false, name = "access_level") Integer accessLevel,
            @RequestHeader(required = false, name = "lang") String lang) {

        byte[] pdfBytes = nurseSummaryReportService.generateNurseSummaryPdf(
                request.getPatient(),
                request.getEncounter(),
                facilityId,
                accessLevel,
                lang
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(
                ContentDisposition.inline()
                        .filename("nurse-summary-" + request.getEncounter().getKey() + ".pdf")
                        .build()
        );

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}
