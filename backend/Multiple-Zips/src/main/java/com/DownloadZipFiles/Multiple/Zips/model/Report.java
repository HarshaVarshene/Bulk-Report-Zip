package com.DownloadZipFiles.Multiple.Zips.model;

public class Report {
	
	private String entity;
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	private String format;
	
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	@Override
	public String toString() {
		return "Files [fileName=" + entity + ", format=" + format + "]";
	}
	public Report(String fileName, String format) {
		super();
		this.entity = fileName;
		this.format = format;
	}

	
	
	
}
