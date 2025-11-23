package com.asklepios.backend_service.service;

import com.asklepios.backend_service.model.generated.dao.ApScreenDAO;
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
public class ApScreenService extends ApScreenDAO implements Serializable {

    public byte[] generateResultsPdf(Map<String, Object> requestData) {
        try {
            log.info("Building HTML content for Results PDF generation");

            // Extract data from request
            Map<String, Object> patientInfo = (Map<String, Object>) requestData.get("patientInfo");
            List<Map<String, Object>> results = (List<Map<String, Object>>) requestData.get("results");

            log.info("Patient: {}", patientInfo != null ? patientInfo.get("name") : "N/A");
            log.info("Results count: {}", results != null ? results.size() : 0);

            // Build HTML content
            String html = buildResultsHtmlContent(patientInfo, results);

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
            log.error("Error generating results PDF: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to generate PDF: " + e.getMessage(), e);
        }
    }

    private String buildResultsHtmlContent(Map<String, Object> patientInfo,
                                           List<Map<String, Object>> results) {
        log.debug("Building HTML content with dynamic data");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String currentDate = sdf.format(new Date());

        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html><html><head>");
        html.append("<meta charset='UTF-8'/>");
        html.append("<meta name='viewport' content='width=device-width, initial-scale=1.0'/>");
        html.append("<title>Laboratory Results Report</title>");
        html.append("<style>").append(getCssStyles()).append("</style>");
        html.append("</head><body>");

        // ===== HEADER =====
        html.append("<div class='header'>");
        html.append("<h1>Laboratory Results Report</h1>");
        html.append("<p class='hospital-name'>Health Organization</p>");
        html.append("</div>");

        // ===== PATIENT INFORMATION =====
        html.append("<div class='section'>");
        html.append("<h2 class='section-title'>Patient Information</h2>");
        html.append("<div class='info-grid'>");

        html.append("<div class='info-item'>");
        html.append("<span class='info-label'>Patient Name:</span> ");
        html.append("<span class='info-value'>").append(getValueOrDefault(patientInfo, "name")).append("</span>");
        html.append("</div>");

        html.append("<div class='info-item'>");
        html.append("<span class='info-label'>Medical Record Number:</span> ");
        html.append("<span class='info-value'>").append(getValueOrDefault(patientInfo, "mrn")).append("</span>");
        html.append("</div>");

        html.append("<div class='info-item'>");
        html.append("<span class='info-label'>Date of Birth:</span> ");
        html.append("<span class='info-value'>").append(getValueOrDefault(patientInfo, "dob")).append("</span>");
        html.append("</div>");

        html.append("<div class='info-item'>");
        html.append("<span class='info-label'>Age:</span> ");
        html.append("<span class='info-value'>").append(getValueOrDefault(patientInfo, "age")).append("</span>");
        html.append("</div>");

        html.append("<div class='info-item'>");
        html.append("<span class='info-label'>Gender:</span> ");
        html.append("<span class='info-value'>").append(getValueOrDefault(patientInfo, "gender")).append("</span>");
        html.append("</div>");

        html.append("</div></div>");

        // ===== RESULTS TABLE =====
        html.append("<div class='section'>");
        html.append("<h2 class='section-title'>Laboratory Test Results</h2>");
        html.append("<div class='table-container'>");
        html.append("<table class='results-table'>");
        html.append("<thead>");
        html.append("<tr>");
        html.append("<th>ORDER ID</th>");
        html.append("<th>Result Date</th>");
        html.append("<th>Test Name</th>");
        html.append("<th>TEST RESULT,UNIT</th>");
        html.append("<th>NORMAL RANGE</th>");
        html.append("<th>MARKER Reviewed Date</th>");
        html.append("</tr>");
        html.append("</thead>");
        html.append("<tbody>");

        if (results != null && !results.isEmpty()) {
            for (Map<String, Object> result : results) {
                html.append("<tr>");
                html.append("<td>").append(getValueOrDefault(result, "orderId")).append("</td>");
                html.append("<td>").append(formatDate(result.get("approvedAt"))).append("</td>");
                html.append("<td>").append(getValueOrDefault(result, "testName")).append("</td>");

                // TEST RESULT,UNIT (نتيجة + وحدة في نفس الخانة)
                html.append("<td>");
                html.append("<span class='result-value'>")
                        .append(getValueOrDefault(result, "resultValue"))
                        .append("</span>");
                html.append(" ");
                html.append("<span class='unit-value'>")
                        .append(getValueOrDefault(result, "unit"))
                        .append("</span>");
                html.append("</td>");

                html.append("<td>").append(getValueOrDefault(result, "normalRange")).append("</td>");

                // MARKER + Reviewed Date في نفس العمود
                html.append("<td class='marker-cell'>");
                html.append(formatMarker(result.get("marker")));
                html.append("<br/>");
                html.append("<span class='review-date'>")
                        .append(getValueOrDefault(result, "reviewDate"))
                        .append("</span>");
                html.append("</td>");

                html.append("</tr>");
            }
        } else {
            // عدد الأعمدة = 6
            html.append("<tr><td colspan='6' class='no-data'>No results available</td></tr>");
        }

        html.append("</tbody>");
        html.append("</table>");
        html.append("</div></div>");

        // ===== FOOTER =====
        html.append("<div class='footer'>");
        html.append("<p class='footer-text'>This is a confidential medical record - Health Organization</p>");
        html.append("<p class='footer-date'>Report Generated: ").append(currentDate).append("</p>");
        html.append("</div>");

        html.append("</body></html>");

        log.debug("HTML content built successfully");
        return html.toString();
    }

    private String getValueOrDefault(Map<String, Object> map, String key) {
        if (map == null || !map.containsKey(key) || map.get(key) == null) {
            return "N/A";
        }
        return String.valueOf(map.get(key));
    }

    private String formatDate(Object date) {
        if (date == null) return "N/A";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            if (date instanceof Date) {
                return sdf.format((Date) date);
            } else if (date instanceof Long) {
                return sdf.format(new Date((Long) date));
            } else {
                return String.valueOf(date);
            }
        } catch (Exception e) {
            return String.valueOf(date);
        }
    }

    private String formatMarker(Object marker) {
        if (marker == null) return "";

        String markerStr = String.valueOf(marker);
        switch (markerStr) {
            case "6730122218786367":
                return "<span class='marker-critical'>⚠</span>";
            case "6731498382453316":
                return "<span class='marker-normal'>Normal</span>";
            case "6730083474405013":
                return "<span class='marker-high'>↑ High</span>";
            case "6730094497387122":
                return "<span class='marker-low'>↓ Low</span>";
            case "6730104027458969":
                return "<span class='marker-critical'>⚠↑</span>";
            case "6730652890616978":
                return "<span class='marker-critical'>⚠↓</span>";
            default:
                return "";
        }
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
                    font-size: 10pt;
                }

                .header {
                    text-align: center;
                    margin-bottom: 25px;
                    padding-bottom: 15px;
                    border-bottom: 3px solid #2196f3;
                }

                .header h1 {
                    font-size: 20pt;
                    margin-bottom: 8px;
                    font-weight: bold;
                    color: #1565c0;
                }

                .hospital-name {
                    font-size: 11pt;
                    color: #333333;
                    font-weight: 600;
                }

                .section {
                    margin-bottom: 20px;
                    page-break-inside: avoid;
                }

                .section-title {
                    font-size: 12pt;
                    margin-bottom: 10px;
                    font-weight: bold;
                    color: #1565c0;
                    border-bottom: 2px solid #e0e0e0;
                    padding-bottom: 5px;
                }

                /* ترتيب بيانات المريض جنب بعض */
                .info-grid {
                    margin-bottom: 10px;
                }

                .info-item {
                    display: inline-block;
                    width: 48%;
                    margin: 4px 1% 0 0;
                    padding: 6px 10px;
                    vertical-align: top;
                    background: #f5f5f5;
                    border-left: 3px solid #2196f3;
                    border-radius: 3px;
                }

                .info-label {
                    font-weight: bold;
                    color: #000000;
                    margin-right: 6px;
                }

                .info-value {
                    color: #333333;
                }

                .table-container {
                    overflow-x: auto;
                    margin-top: 10px;
                }

                .results-table {
                    width: 100%;
                    border-collapse: collapse;
                    font-size: 9pt;
                }

                /* هيدر الجدول بالأسماء المطلوبة */
                .results-table thead th {
                    background: #1565c0;
                    color: #ffffff;
                }

                .results-table th {
                    padding: 10px 8px;
                    text-align: left;
                    font-weight: bold;
                    border: 1px solid #0d47a1;
                }

                .results-table td {
                    padding: 8px;
                    border: 1px solid #e0e0e0;
                }

                .results-table tbody tr:nth-child(even) {
                    background-color: #f9f9f9;
                }

                .results-table tbody tr:hover {
                    background-color: #e3f2fd;
                }

                .result-value {
                    font-weight: 600;
                    color: #1565c0;
                }

                .unit-value {
                    font-size: 8pt;
                }

                .marker-cell {
                    text-align: center;
                    font-weight: bold;
                }

                .marker-normal {
                    color: #4caf50;
                    font-weight: 600;
                }

                .marker-high {
                    color: #ff9800;
                    font-weight: 600;
                }

                .marker-low {
                    color: #2196f3;
                    font-weight: 600;
                }

                .marker-critical {
                    color: #f44336;
                    font-size: 14pt;
                    font-weight: bold;
                }

                .review-date {
                    display: block;
                    font-size: 8pt;
                    font-weight: normal;
                    color: #555555;
                }

                .no-data {
                    text-align: center;
                    padding: 20px;
                    color: #999;
                    font-style: italic;
                }

                .footer {
                    margin-top: 40px;
                    padding-top: 15px;
                    border-top: 2px solid #cccccc;
                    text-align: center;
                }

                .footer-text {
                    font-size: 9pt;
                    color: #666666;
                    margin-bottom: 5px;
                }

                .footer-date {
                    font-size: 8pt;
                    color: #999999;
                }

                @media print {
                    body {
                        padding: 15px;
                    }

                    .section {
                        page-break-inside: avoid;
                    }

                    .results-table {
                        page-break-inside: auto;
                    }

                    .results-table tr {
                        page-break-inside: avoid;
                        page-break-after: auto;
                    }

                    thead {
                        display: table-header-group;
                    }
                }
                """;
    }
}
