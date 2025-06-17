package com.bulkdownload.zip.service;

import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import com.bulkdownload.zip.model.ReportFile;


@Component("pdf")
public class PdfReportGenerator implements ReportGenerator {
    private static final String REPORTS_DIR = "reports";

    @Override
    public ReportFile generateReport(String entityName, List<Map<String, Object>> data) throws Exception {
        Files.createDirectories(Paths.get(REPORTS_DIR));

        try (PDDocument document = new PDDocument(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.setLeading(14.5f);
                contentStream.newLineAtOffset(50, 750);
                contentStream.showText("PDF Report for " + entityName);
                contentStream.newLine();

                for (Map<String, Object> row : data) {
                    contentStream.newLine();
                    contentStream.showText(row.toString());
                }

                contentStream.endText();
            }

            document.save(outputStream);
            document.close(); 

            return new ReportFile(entityName + ".pdf", outputStream.toByteArray());
        }
    }

}