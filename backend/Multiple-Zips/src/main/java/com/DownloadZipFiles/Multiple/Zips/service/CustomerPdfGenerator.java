package com.DownloadZipFiles.Multiple.Zips.service;



import java.io.ByteArrayOutputStream;

import org.springframework.stereotype.Service;

import com.DownloadZipFiles.Multiple.Zips.ReportGenerator;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class CustomerPdfGenerator implements ReportGenerator {
	
	@Override
	 public String getEntity() {
	        return "customer";
	    }
	@Override
	    public String getFormat() {
	        return "pdf";
	    }
	@Override
	public byte[] generate(){
		
		try {
		
		Document document=new Document();
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		
		PdfWriter.getInstance(document,out);
		document.open();
		document.add(new Paragraph("Customer Deatil"));
		document.add(new Paragraph("Name: Harsha"));
		document.add(new Paragraph("Email: harsha@gmail.com"));
		document.close();
		
		return out.toByteArray();
		
		}
		catch(Exception e) {
			throw new RuntimeException("Failed to generate pdf"+e);
		}		
	}
}
