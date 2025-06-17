package com.bulkdownload.zip.service;

import java.io.ByteArrayOutputStream;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import com.bulkdownload.zip.model.ReportFile;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;

@Component("excel")
public class ExcelReportGenerator implements ReportGenerator {
    private static final String REPORTS_DIR = "reports";
    @Override
    public ReportFile generateReport(String entityName, List<Map<String, Object>> data) throws Exception {
        Files.createDirectories(Paths.get(REPORTS_DIR));

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet(entityName + " Data");
            int rowIndex = 0;

            if (!data.isEmpty()) {
                Map<String, Object> firstRow = data.get(0);
                Row headerRow = sheet.createRow(rowIndex++);
                int cellIndex = 0;
                for (String key : firstRow.keySet()) {
                    headerRow.createCell(cellIndex++).setCellValue(key);
                }

                for (Map<String, Object> rowData : data) {
                    Row row = sheet.createRow(rowIndex++);
                    cellIndex = 0;
                    for (String key : firstRow.keySet()) {
                        Object value = rowData.get(key);
                        row.createCell(cellIndex++).setCellValue(value != null ? value.toString() : "");
                    }
                }
            }

            workbook.write(outputStream);
            workbook.close();

            return new ReportFile(entityName + ".xlsx", outputStream.toByteArray());
        }
    }

}