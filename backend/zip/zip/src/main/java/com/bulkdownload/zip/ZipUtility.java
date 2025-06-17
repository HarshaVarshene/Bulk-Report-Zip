package com.bulkdownload.zip;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.bulkdownload.zip.model.ReportFile;

public class ZipUtility {
	public static byte[] zipFiles(List<ReportFile> files) throws Exception {
        if (files == null || files.isEmpty()) {
            return new byte[0];
        }

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ZipOutputStream zos = new ZipOutputStream(baos)) {

            for (ReportFile file : files) {
                ZipEntry entry = new ZipEntry(file.getFileName());
                zos.putNextEntry(entry);
                zos.write(file.getContent());
                zos.closeEntry();
            }

            zos.finish();
            return baos.toByteArray();
        }
    }

	}


