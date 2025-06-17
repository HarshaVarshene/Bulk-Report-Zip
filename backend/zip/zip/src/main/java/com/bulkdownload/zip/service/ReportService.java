package com.bulkdownload.zip.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bulkdownload.zip.ZipUtility;
import com.bulkdownload.zip.model.ReportFile;
import com.bulkdownload.zip.model.ReportRequest;

@Service
public class ReportService {

    private final ReportGeneratorRegistry registry;

    @Autowired
    public ReportService(ReportGeneratorRegistry registry) {
        this.registry = registry;
    }

    public byte[] generateReports(List<ReportRequest> requests) throws Exception {
        List<ReportFile> files = new ArrayList<>();

        for (ReportRequest request : requests) {
            ReportGenerator generator = registry.getGenerator(request.getFormat());
            String fileName = request.getEntity() + "." + 
                (request.getFormat().equalsIgnoreCase("pdf") ? "pdf" : "xlsx");
            
            ReportFile file = generator.generateReport(
                request.getEntity(), request.getData()
            );

            files.add(new ReportFile(fileName, file.getContent()));
        }

        return ZipUtility.zipFiles(files);
    }
}



