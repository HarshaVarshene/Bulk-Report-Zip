package com.DownloadZipFiles.Multiple.Zips.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;

import com.DownloadZipFiles.Multiple.Zips.ReportGenerator;

@Service
public class ProjectExcelGenerator implements ReportGenerator {

    @Override
    public String getEntity() {
        return "project";
    }

    @Override
    public String getFormat() {
        return "excel";
    }

    @Override
    public byte[] generate() {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Projects");

            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Project Name");
            header.createCell(1).setCellValue("Status");

            Row row = sheet.createRow(1);
            row.createCell(0).setCellValue("Zip File Project");
            row.createCell(1).setCellValue("In Progress");

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            return out.toByteArray();
            
        } catch (IOException e) {
            throw new RuntimeException("Failed to generate project Excel", e);
        }
    }
}