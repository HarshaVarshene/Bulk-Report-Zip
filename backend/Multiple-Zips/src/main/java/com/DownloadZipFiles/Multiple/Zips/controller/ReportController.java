package com.DownloadZipFiles.Multiple.Zips.controller;


import com.DownloadZipFiles.Multiple.Zips.model.Report;
import com.DownloadZipFiles.Multiple.Zips.service.CustomerPdfGenerator;
import com.DownloadZipFiles.Multiple.Zips.service.ProjectExcelGenerator;
import com.DownloadZipFiles.Multiple.Zips.service.ZipGenerator;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final CustomerPdfGenerator customerPdfGenerator;
    private final ProjectExcelGenerator projectExcelGenerator;
    private final ZipGenerator zipGenerator;

    public ReportController(CustomerPdfGenerator customerPdfGenerator,
                            ProjectExcelGenerator projectExcelGenerator,
                            ZipGenerator zipGenerator) {
        this.customerPdfGenerator = customerPdfGenerator;
        this.projectExcelGenerator = projectExcelGenerator;
        this.zipGenerator = zipGenerator;
    }

    @PostMapping("/download")
    public ResponseEntity<byte[]> downloadReports(@RequestBody List<Report> requests) throws Exception {
        Map<String, byte[]> fileMap = new HashMap<>();

        for (Report req : requests) {
            String entity = req.getEntity().toLowerCase();
            String format = req.getFormat().toLowerCase();
            String fileName = entity + (format.equals("pdf") ? ".pdf" : ".xlsx");

            switch (entity) {
                case "customer":
                    fileMap.put(fileName, customerPdfGenerator.generate());
                    break;
                case "project":
                    fileMap.put(fileName, projectExcelGenerator.generate());
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported entity: " + entity);
            }
        }

        byte[] zipBytes = zipGenerator.createZip(fileMap);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "bulk_reports.zip");

        return ResponseEntity.ok()
                .headers(headers)
                .body(zipBytes);
    }
}
