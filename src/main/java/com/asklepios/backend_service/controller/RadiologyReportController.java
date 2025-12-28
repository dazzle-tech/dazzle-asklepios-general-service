package com.asklepios.backend_service.controller;

import com.asklepios.backend_service.model.DTO.RadiologyReportRequestDTO;
import com.asklepios.backend_service.service.RadiologyReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/observation/radiology")
@RequiredArgsConstructor
public class RadiologyReportController {

    private final RadiologyReportService radiologyReportService;

    @PostMapping(value = "/generate", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generateRadiologyReport(
            @RequestBody RadiologyReportRequestDTO request,
            @RequestHeader(required = false, name = "facility_id") String facilityId,
            @RequestHeader(required = false, name = "access_level") Integer accessLevel,
            @RequestHeader(required = false, name = "lang") String lang
    ) {

        byte[] pdf = radiologyReportService.generateRadiologyPdf(
                request,
                facilityId,
                accessLevel,
                lang
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(
                ContentDisposition.inline()
                        .filename("radiology-report-" + request.getReportKey() + ".pdf")
                        .build()
        );

        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
    }
}
