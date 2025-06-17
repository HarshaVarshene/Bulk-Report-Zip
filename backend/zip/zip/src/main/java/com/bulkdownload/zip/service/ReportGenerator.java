package com.bulkdownload.zip.service;

import java.util.List;
import java.util.Map;


import com.bulkdownload.zip.model.ReportFile;

public interface ReportGenerator {
    ReportFile generateReport(String entityName, List<Map<String, Object>> data) throws Exception;
}

