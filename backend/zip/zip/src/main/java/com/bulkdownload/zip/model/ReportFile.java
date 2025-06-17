package com.bulkdownload.zip.model;

public class ReportFile {
    private final String fileName;
    private final byte[] content;

    public ReportFile(String fileName, byte[] content) {
        this.fileName = fileName;
        this.content = content;
    }

    public String getFileName() {
        return fileName;
    }

    public byte[] getContent() {
        return content;
    }
}
