package com.DownloadZipFiles.Multiple.Zips;

public interface ReportGenerator {
	
	String getEntity(); 
    String getFormat(); 
    byte[] generate() throws Exception;

}
