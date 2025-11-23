package com.asklepios.backend_service.service;

import com.asklepios.backend_service.model.generated.dao.ApServiceDAO;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ApServiceService extends ApServiceDAO implements Serializable {

    public byte[] generateDischargePdf(Map<String, Object> dischargeData) {
        try {
            log.info("Building HTML content for Discharge PDF generation with data from frontend");

            // Build HTML content with data from frontend
            String html = buildHtmlContent(dischargeData);

            log.debug("Generated HTML length: {} characters", html.length());

            // Create output stream
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            // Configure converter properties
            ConverterProperties converterProperties = new ConverterProperties();

            // Convert HTML to PDF
            log.info("Converting HTML to PDF...");
            HtmlConverter.convertToPdf(html, outputStream, converterProperties);

            byte[] pdfBytes = outputStream.toByteArray();
            log.info("PDF conversion completed - Size: {} bytes", pdfBytes.length);

            return pdfBytes;

        } catch (Exception e) {
            log.error("Error generating discharge PDF: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to generate PDF: " + e.getMessage(), e);
        }
    }

    private String buildHtmlContent(Map<String, Object> dischargeData) {
        log.debug("Building HTML content with data from frontend");

        // Extract data from the map
        Map<String, Object> patient = (Map<String, Object>) dischargeData.get("patient");
        Map<String, Object> encounter = (Map<String, Object>) dischargeData.get("encounter");
        Map<String, Object> user = (Map<String, Object>) dischargeData.get("user");
        Map<String, Object> facility = (Map<String, Object>) dischargeData.get("facility");
        List<Map<String, Object>> diagnoses = (List<Map<String, Object>>) dischargeData.get("diagnoses");
        List<Map<String, Object>> reviewSystems = (List<Map<String, Object>>) dischargeData.get("reviewSystems");
        List<Map<String, Object>> procedures = (List<Map<String, Object>>) dischargeData.get("procedures");
        List<Map<String, Object>> prescriptions = (List<Map<String, Object>>) dischargeData.get("prescriptions");
        List<Map<String, Object>> diagnosticTests = (List<Map<String, Object>>) dischargeData.get("diagnosticTests");

        // Current date for generation timestamp
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String currentDate = sdfTime.format(new Date());

        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html><html><head>");
        html.append("<meta charset='UTF-8'/>");
        html.append("<meta name='viewport' content='width=device-width, initial-scale=1.0'/>");
        html.append("<title>Discharge Summary Report</title>");
        html.append("<style>").append(getCssStyles()).append("</style>");
        html.append("</head><body>");

        // ===== HEADER WITH PATIENT INFO =====
        html.append("<div class='header'>");
        html.append("<div class='header-main'>");
        html.append("<h1>Discharge Summary Report</h1>");
        html.append("<p class='hospital-name'>").append(safeGet(facility, "name", "Health Organization")).append("</p>");
        html.append("</div>");

        // Patient info in header
        html.append("<div class='patient-header-info'>");
        html.append("<div class='patient-header-row'>");
        html.append("<span class='patient-header-label'>Patient:</span> ");
        html.append("<span class='patient-header-value'>").append(safeGet(patient, "fullName", "")).append("</span>");
        html.append("</div>");
        html.append("<div class='patient-header-row'>");
        html.append("<span class='patient-header-label'>MRN:</span> ");
        html.append("<span class='patient-header-value'>").append(safeGet(patient, "patientMrn", "")).append("</span>");
        html.append("<span class='patient-header-label' style='margin-left: 30px;'>DOB:</span> ");
        html.append("<span class='patient-header-value'>").append(safeGet(patient, "dob", "")).append("</span>");
        html.append("<span class='patient-header-label' style='margin-left: 30px;'>Age:</span> ");
        html.append("<span class='patient-header-value'>") .append(safeGet(patient, "age", "")).append("</span>");
        html.append("</div>");
        html.append("</div>");
        html.append("</div>");


        // ===== 2. ADMISSION DETAILS =====
        html.append("<div class='section'>");
        html.append("<h2 class='section-number'>2. Admission Details</h2>");
        html.append("<div class='info-grid'>");

        html.append("<div class='info-item'><span class='info-label'>Date of Admission:</span> <span class='info-value'>")
                .append(safeGet(encounter, "admissionDate", "")).append("</span></div>");

        html.append("<div class='info-item'><span class='info-label'>Admitting Facility:</span> <span class='info-value'>")
                .append(safeGet(facility, "name", "")).append("</span></div>");

        html.append("<div class='info-item'><span class='info-label'>Admitting Physician:</span> <span class='info-value'>")
                .append(safeGet(user, "fullName", "")).append("</span></div>");

        html.append("</div></div>");

        // ===== 3. DISCHARGE DETAILS =====
        html.append("<div class='section'>");
        html.append("<h2 class='section-number'>3. Discharge Details</h2>");
        html.append("<div class='info-grid'>");

        String dischargeDate = sdf.format(new Date());
        html.append("<div class='info-item'><span class='info-label'>Date of Discharge:</span> <span class='info-value'>")
                .append("</span></div>");

        html.append("<div class='info-item'><span class='info-label'>Discharging Physician:</span> <span class='info-value'>")
                .append("</span></div>");

        html.append("<div class='info-item'><span class='info-label'>Disposition:</span> <span class='info-value'>")
                .append("</span></div>");

        html.append("</div></div>");

        // ===== 4. REASON FOR ADMISSION =====
        html.append("<div class='section'>");
        html.append("<h2 class='section-number'>4. Reason for Admission</h2>");
        html.append("<div class='content-box'>");
        html.append("<p>").append(safeGet(encounter, "chiefComplain", "Not specified")).append("</p>");
        html.append("</div></div>");

        // ===== 5. SIGNIFICANT FINDINGS / DIAGNOSIS =====
        html.append("<div class='section'>");
        html.append("<h2 class='section-number'>5. Significant Findings / Diagnosis</h2>");
        html.append("<div class='content-box'>");

        if (diagnoses != null && !diagnoses.isEmpty()) {
            Map<String, Object> primaryDiag = null;
            List<Map<String, Object>> secondaryDiags = new java.util.ArrayList<>();

            for (Map<String, Object> diag : diagnoses) {
                String diagType = safeGet(diag, "diagnoseType", "");
                if ("Primary".equalsIgnoreCase(diagType)) {
                    primaryDiag = diag;
                } else if ("Secondary".equalsIgnoreCase(diagType)) {
                    secondaryDiags.add(diag);
                }
            }

            if (primaryDiag != null) {
                html.append("<div class='diagnosis-item'><strong>Primary Diagnosis:</strong> ")
                        .append(safeGet(primaryDiag, "icdCode", "")).append(" ")
                        .append(safeGet(primaryDiag, "description", "")).append("</div>");
            }

            if (!secondaryDiags.isEmpty()) {
                html.append("<div class='diagnosis-item'><strong>Secondary Diagnoses:</strong>");
                html.append("<ul>");
                for (Map<String, Object> diag : secondaryDiags) {
                    html.append("<li>").append(safeGet(diag, "icdCode", "")).append(" ")
                            .append(safeGet(diag, "description", "")).append("</li>");
                }
                html.append("</ul>");
                html.append("</div>");
            }
        } else {
            html.append("<p>No diagnosis recorded</p>");
        }
        html.append("</div></div>");

        // ===== 6. SIGNIFICANT FINDINGS (Review of Systems) =====
        html.append("<div class='section'>");
        html.append("<h2 class='section-number'>6. Review of Systems - Significant Findings</h2>");
        html.append("<div class='content-box'>");

        if (reviewSystems != null && !reviewSystems.isEmpty()) {
            html.append("<ul class='course-list'>");
            for (Map<String, Object> item : reviewSystems) {
                String system = safeGet(item, "system", "");
                String detail = safeGet(item, "systemDetail", "");
                String notes = safeGet(item, "notes", "");

                html.append("<li><strong>").append(system).append(":</strong> ");
                if (detail != null && !detail.isEmpty()) {
                    html.append(detail);
                }
                if (notes != null && !notes.isEmpty()) {
                    html.append(" - ").append(notes);
                }
                html.append("</li>");
            }
            html.append("</ul>");
        } else {
            html.append("<p>No significant findings recorded</p>");
        }

        html.append("</div></div>");

        // ===== 7. PROCEDURES AND TREATMENTS =====
        html.append("<div class='section'>");
        html.append("<h2 class='section-number'>7. Procedures and Treatments</h2>");
        html.append("<div class='content-box'>");
        html.append("<ul class='procedures-list'>");

        // Procedures
        if (procedures != null && !procedures.isEmpty()) {
            for (Map<String, Object> proc : procedures) {
                String procName = safeGet(proc, "procedureName", "");
                String procId = safeGet(proc, "procedureId", "");
                if (!procName.isEmpty()) {
                    html.append("<li>").append(procName);
                    if (!procId.isEmpty()) html.append(" (ID: ").append(procId).append(")");
                    html.append("</li>");
                }
            }
        }

        // Diagnostic Tests (Treatments)
        if (diagnosticTests != null && !diagnosticTests.isEmpty()) {
            for (Map<String, Object> test : diagnosticTests) {
                String testName = safeGet(test, "testName", "");
                if (!testName.isEmpty()) {
                    html.append("<li>").append(testName).append("</li>");
                }
            }
        }

        if ((procedures == null || procedures.isEmpty()) && (diagnosticTests == null || diagnosticTests.isEmpty())) {
            html.append("<li>No procedures or treatments recorded</li>");
        }

        html.append("</ul>");
        html.append("</div></div>");

        // ===== 8. MEDICATIONS AT DISCHARGE =====
        html.append("<div class='section'>");
        html.append("<h2 class='section-number'>8. Medications at Discharge</h2>");
        html.append("<div class='content-box medications-box'>");
        html.append("<ul class='medications-list'>");

        if (prescriptions != null && !prescriptions.isEmpty()) {
            for (Map<String, Object> med : prescriptions) {
                String medName = safeGet(med, "medicationName", "");
                String instructions = safeGet(med, "instructions", "");

                if (!medName.isEmpty()) {
                    html.append("<li>").append(medName);
                    if (!instructions.isEmpty()) {
                        html.append(": ").append(instructions);
                    }
                    html.append("</li>");
                }
            }
        } else {
            html.append("<li>No medications prescribed</li>");
        }

        html.append("</ul>");
        html.append("</div></div>");

        // ===== 9-11. STATIC SECTIONS =====
        html.append("<div class='section'>");
        html.append("<h2 class='section-number'>9. Discharge Instructions</h2>");
        html.append("<div class='content-box instructions-box'>");
        html.append("<ul class='instructions-list'>");
        html.append("<li>Continue medications as prescribed.</li>");
        html.append("<li>Avoid smoking and exposure to respiratory irritants.</li>");
        html.append("<li>Use inhaler as needed for shortness of breath.</li>");
        html.append("<li>Seek immediate care if breathing worsens.</li>");
        html.append("</ul>");
        html.append("</div></div>");

        html.append("<div class='section'>");
        html.append("<h2 class='section-number'>10. Follow-up Plan</h2>");
        html.append("<div class='content-box'>");
        html.append("<ul class='followup-list'>");
        html.append("<li>Follow-up appointment with pulmonology in 1 week.</li>");
        html.append("<li>Primary care follow-up in 2–4 weeks.</li>");
        html.append("</ul>");
        html.append("</div></div>");

        html.append("<div class='section'>");
        html.append("<h2 class='section-number'>11. Condition at Discharge</h2>");
        html.append("<div class='content-box'>");
        html.append("<p>Improved</p>");
        html.append("</div></div>");

        // ===== 12. PENDING RESULTS =====
        html.append("<div class='section'>");
        html.append("<h2 class='section-number'>12. Pending Results / Recommendations</h2>");
        html.append("<div class='content-box'>");

        if (diagnosticTests != null && !diagnosticTests.isEmpty()) {
            boolean hasPending = false;
            html.append("<ul>");
            for (Map<String, Object> test : diagnosticTests) {
                String status = safeGet(test, "processingStatus", "");
                String testName = safeGet(test, "testName", "");

                if (!status.equalsIgnoreCase("Completed") && !testName.isEmpty()) {
                    html.append("<li>").append(testName).append(" - Status: ").append(status).append("</li>");
                    hasPending = true;
                }
            }
            html.append("</ul>");

            if (!hasPending) {
                html.append("<p>No pending results</p>");
            }
        } else {
            html.append("<p>No pending results</p>");
        }

        html.append("</div></div>");

        // ===== 13. PROVIDER INFORMATION =====
        html.append("<div class='section'>");
        html.append("<h2 class='section-number'>13. Provider Information</h2>");
        html.append("<div class='info-grid'>");
        html.append("<div class='info-item'><span class='info-label'>Prepared by:</span> <span class='info-value'>")
                .append(safeGet(user, "fullName", "")).append("</span></div>");
        html.append("<div class='info-item'><span class='info-label'>Email:</span> <span class='info-value'>")
                .append(safeGet(user, "email", "")).append("</span></div>");
        html.append("</div></div>");

        // ===== FOOTER =====
        html.append("<div class='footer'>");
        html.append("<p class='footer-text'>This is a confidential medical record - ")
                .append(safeGet(facility, "name", "Health Organization")).append("</p>");
        html.append("<p class='footer-date'>Report Generated: ").append(currentDate).append("</p>");
        html.append("</div>");

        html.append("</body></html>");

        log.debug("HTML content built successfully with data from frontend");
        return html.toString();
    }

    // Helper method to safely get values from map
    private String safeGet(Map<String, Object> map, String key, String defaultValue) {
        if (map == null) return defaultValue;
        Object value = map.get(key);
        return value != null ? value.toString() : defaultValue;
    }

    private String getCssStyles() {
        return """
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }
            
            body {
                font-family: 'Calibri', 'Arial', sans-serif;
                padding: 30px;
                color: #000000;
                line-height: 1.4;
                background: #ffffff;
                font-size: 9pt;
            }
            
            .header {
                margin-bottom: 20px;
                padding-bottom: 15px;
                border-bottom: 2px solid #000000;
            }
            
            .header-main {
                text-align: center;
                margin-bottom: 15px;
            }
            
            .header h1 {
                font-size: 18pt;
                margin-bottom: 5px;
                font-weight: bold;
                color: #000000;
            }
            
            .hospital-name {
                font-size: 10pt;
                color: #333333;
                font-weight: 600;
            }
            
            .patient-header-info {
                background: #f5f5f5;
                padding: 10px 15px;
                border-radius: 5px;
                border-left: 4px solid #4a90e2;
            }
            
            .patient-header-row {
                margin-bottom: 5px;
                font-size: 9.5pt;
            }
            
            .patient-header-row:last-child {
                margin-bottom: 0;
            }
            
            .patient-header-label {
                font-weight: bold;
                color: #000000;
                display: inline-block;
                min-width: 45px;
            }
            
            .patient-header-value {
                color: #333333;
                font-weight: 600;
            }
            
            .section {
                margin-bottom: 15px;
                page-break-inside: avoid;
            }
            
            .section-number {
                font-size: 10pt;
                margin-bottom: 8px;
                font-weight: bold;
                color: #000000;
            }
            
            .info-grid {
                display: grid;
                grid-template-columns: 1fr;
                gap: 5px;
            }
            
            .info-item {
                padding: 4px 0;
                border-bottom: 1px solid #e0e0e0;
            }
            
            .info-label {
                font-weight: bold;
                color: #000000;
                display: inline-block;
                min-width: 180px;
            }
            
            .info-value {
                color: #333333;
            }
            
            .content-box {
                padding: 8px 10px;
                background: #f9f9f9;
                border-left: 3px solid #4a90e2;
                border-radius: 3px;
            }
            
            .content-box p {
                margin: 0;
                line-height: 1.5;
            }
            
            .content-box ul {
                margin: 0;
                padding-left: 18px;
            }
            
            .content-box li {
                margin-bottom: 4px;
                line-height: 1.4;
            }
            
            .diagnosis-item {
                margin-bottom: 8px;
            }
            
            .diagnosis-item strong {
                color: #000000;
            }
            
            .diagnosis-item ul {
                margin-top: 4px;
                margin-left: 18px;
            }
            
            .course-list,
            .procedures-list,
            .medications-list,
            .instructions-list,
            .followup-list {
                list-style-type: disc;
                padding-left: 20px;
            }
            
            .medications-box {
                background: #fff8e1;
                border-left-color: #ff9800;
            }
            
            .medications-list li {
                font-weight: 500;
                color: #000000;
            }
            
            .instructions-box {
                background: #e3f2fd;
                border-left-color: #2196f3;
            }
            
            .instructions-list li {
                font-weight: 600;
                color: #1565c0;
            }
            
            .footer {
                margin-top: 30px;
                padding-top: 12px;
                border-top: 2px solid #cccccc;
                text-align: center;
            }
            
            .footer-text {
                font-size: 8pt;
                color: #666666;
                margin-bottom: 4px;
            }
            
            .footer-date {
                font-size: 7pt;
                color: #999999;
            }
            
            @media print {
                body {
                    padding: 20px;
                }
                
                .section {
                    page-break-inside: avoid;
                }
                
                .content-box {
                    -webkit-print-color-adjust: exact;
                    print-color-adjust: exact;
                }
            }
        """;
    }
}