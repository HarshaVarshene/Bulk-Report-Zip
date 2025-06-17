package com.bulkdownload.zip.model;

import java.util.List;
import java.util.Map;

public class ReportRequest {
    private String entity;
    private String format;
    private List<Map<String, Object>> data;

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public List<Map<String, Object>> getData() {
        return data;
    }

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }
}