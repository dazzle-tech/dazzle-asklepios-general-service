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

@Service
@Slf4j
public class ApServiceService extends ApServiceDAO implements Serializable {


    public byte[] generateDischargePdf() {
        try {
            log.info("Building HTML content for Discharge PDF generation with static data");

            // Build HTML content with static data
            String html = buildHtmlContent();

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

    private String buildHtmlContent() {
        log.debug("Building HTML content with static sample data");

        // Current date for generation timestamp
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String currentDate = sdf.format(new Date());

        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html><html><head>");
        html.append("<meta charset='UTF-8'/>");
        html.append("<meta name='viewport' content='width=device-width, initial-scale=1.0'/>");
        html.append("<title>Discharge Summary Report</title>");
        html.append("<style>").append(getCssStyles()).append("</style>");
        html.append("</head><body>");

        // ===== HEADER =====
        html.append("<div class='header'>");
        html.append("<h1>Discharge Summary Report</h1>");
        html.append("<p class='hospital-name'>Health Organization</p>");
        html.append("</div>");

        // ===== PATIENT INFORMATION =====
        html.append("<div class='section'>");
        html.append("<h2 class='section-number'>1. Patient Information</h2>");
        html.append("<div class='info-grid'>");
        html.append("<div class='info-item'><span class='info-label'>Name:</span> <span class='info-value'>Emergency Testing Patient</span></div>");
        html.append("<div class='info-item'><span class='info-label'>Age:</span> <span class='info-value'>57</span></div>");
        html.append("<div class='info-item'><span class='info-label'>Sex:</span> <span class='info-value'>Male</span></div>");
        html.append("<div class='info-item'><span class='info-label'>Medical Record Number:</span> <span class='info-value'>1003</span></div>");
        html.append("</div></div>");

        // ===== ADMISSION DETAILS =====
        html.append("<div class='section'>");
        html.append("<h2 class='section-number'>2. Admission Details</h2>");
        html.append("<div class='info-grid'>");
        html.append("<div class='info-item'><span class='info-label'>Date of Admission:</span> <span class='info-value'>2025-09-02</span></div>");
        html.append("<div class='info-item'><span class='info-label'>Admitting Facility:</span> <span class='info-value'>Health Organization</span></div>");
        html.append("<div class='info-item'><span class='info-label'>Admitting Physician:</span> <span class='info-value'>System Administrator</span></div>");
        html.append("</div></div>");

        // ===== DISCHARGE DETAILS =====
        html.append("<div class='section'>");
        html.append("<h2 class='section-number'>3. Discharge Details</h2>");
        html.append("<div class='info-grid'>");
        html.append("<div class='info-item'><span class='info-label'>Date of Discharge:</span> <span class='info-value'>2025-09-06</span></div>");
        html.append("<div class='info-item'><span class='info-label'>Discharging Physician:</span> <span class='info-value'>System Administrator</span></div>");
        html.append("<div class='info-item'><span class='info-label'>Disposition:</span> <span class='info-value'>Discharged home</span></div>");
        html.append("</div></div>");

        // ===== REASON FOR ADMISSION =====
        html.append("<div class='section'>");
        html.append("<h2 class='section-number'>4. Reason for Admission</h2>");
        html.append("<div class='content-box'>");
        html.append("<p>Acute shortness of breath and chest discomfort</p>");
        html.append("</div></div>");

        // ===== SIGNIFICANT FINDINGS / DIAGNOSIS =====
        html.append("<div class='section'>");
        html.append("<h2 class='section-number'>5. Significant Findings / Diagnosis</h2>");
        html.append("<div class='content-box'>");
        html.append("<div class='diagnosis-item'><strong>Primary Diagnosis:</strong> J44.1 Acute exacerbation of chronic obstructive pulmonary disease (COPD)</div>");
        html.append("<div class='diagnosis-item'><strong>Secondary Diagnoses:</strong>");
        html.append("<ul>");
        html.append("<li>I10 Hypertension</li>");
        html.append("<li>E78.5 Hyperlipidemia</li>");
        html.append("</ul>");
        html.append("</div></div></div>");

        // ===== HOSPITAL COURSE =====
        html.append("<div class='section'>");
        html.append("<h2 class='section-number'>6. Hospital Course</h2>");
        html.append("<div class='content-box'>");
        html.append("<ul class='course-list'>");
        html.append("<li>Patient admitted for COPD exacerbation, treated with steroids, nebulizers, and antibiotics.</li>");
        html.append("<li>Oxygen therapy initiated and gradually weaned.</li>");
        html.append("<li>Clinical symptoms improved steadily with treatment.</li>");
        html.append("</ul>");
        html.append("</div></div>");

        // ===== PROCEDURES AND TREATMENTS =====
        html.append("<div class='section'>");
        html.append("<h2 class='section-number'>7. Procedures and Treatments</h2>");
        html.append("<div class='content-box'>");
        html.append("<ul class='procedures-list'>");
        html.append("<li>Chest X-ray</li>");
        html.append("<li>Nebulized bronchodilators</li>");
        html.append("<li>IV corticosteroids</li>");
        html.append("<li>IV antibiotics</li>");
        html.append("</ul>");
        html.append("</div></div>");

        // ===== MEDICATIONS AT DISCHARGE =====
        html.append("<div class='section'>");
        html.append("<h2 class='section-number'>8. Medications at Discharge</h2>");
        html.append("<div class='content-box medications-box'>");
        html.append("<ul class='medications-list'>");
        html.append("<li>Prednisone 20 mg PO daily × 5 days</li>");
        html.append("<li>Albuterol inhaler: 2 puffs every 6 hours PRN</li>");
        html.append("<li>Lisinopril 10 mg PO daily</li>");
        html.append("<li>Atorvastatin 20 mg PO daily</li>");
        html.append("</ul>");
        html.append("</div></div>");

        // ===== DISCHARGE INSTRUCTIONS =====
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

        // ===== FOLLOW-UP PLAN =====
        html.append("<div class='section'>");
        html.append("<h2 class='section-number'>10. Follow-up Plan</h2>");
        html.append("<div class='content-box'>");
        html.append("<ul class='followup-list'>");
        html.append("<li>Follow-up appointment with pulmonology in 1 week.</li>");
        html.append("<li>Primary care follow-up in 2–4 weeks.</li>");
        html.append("</ul>");
        html.append("</div></div>");

        // ===== CONDITION AT DISCHARGE =====
        html.append("<div class='section'>");
        html.append("<h2 class='section-number'>11. Condition at Discharge</h2>");
        html.append("<div class='content-box'>");
        html.append("<p>Improved; stable on room air</p>");
        html.append("</div></div>");

        // ===== PENDING RESULTS / RECOMMENDATIONS =====
        html.append("<div class='section'>");
        html.append("<h2 class='section-number'>12. Pending Results / Recommendations</h2>");
        html.append("<div class='content-box'>");
        html.append("<p>Sputum culture pending, to be reviewed at follow-up.</p>");
        html.append("</div></div>");

        // ===== PROVIDER INFORMATION =====
        html.append("<div class='section'>");
        html.append("<h2 class='section-number'>13. Provider Information</h2>");
        html.append("<div class='info-grid'>");
        html.append("<div class='info-item'><span class='info-label'>Prepared by:</span> <span class='info-value'>System Administrator</span></div>");
        html.append("<div class='info-item'><span class='info-label'>License No.:</span> <span class='info-value'>A47922</span></div>");
        html.append("</div></div>");

        // ===== FOOTER =====
        html.append("<div class='footer'>");
        html.append("<p class='footer-text'>This is an automated confidential medical record - Health Organization</p>");
        html.append("<p class='footer-date'>Report Generated: ").append(currentDate).append("</p>");
        html.append("</div>");

        html.append("</body></html>");

        log.debug("HTML content built successfully with static data");
        return html.toString();
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
                text-align: center;
                margin-bottom: 20px;
                padding-bottom: 12px;
                border-bottom: 2px solid #000000;
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