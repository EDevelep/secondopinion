package org.secondopinion.utils.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.springframework.util.StringUtils;

import org.secondopinion.utils.DateUtil;

public class ZipUtil {
	/*
	 * Example of reading Zip archive using ZipFile class
	 */

	public static List<String> readUsingZipFile(String fileName, String outputDir) throws IOException {
		Charset CP866 = Charset.forName("CP866");
		final ZipFile file = new ZipFile(fileName, CP866);
		System.out.println("Iterating over zip file : " + fileName);
		Set<String> zipEntries = new HashSet<String>();
		try {
			final Enumeration<? extends ZipEntry> entries = file.entries();
			while (entries.hasMoreElements()) {
				final ZipEntry entry = entries.nextElement();
				System.out.printf("File: %s Size %d  Modified on %TD %n", entry.getName(), entry.getSize(),
						new Date(entry.getTime()));
				String zipEntryName = extractEntry(outputDir, entry, file.getInputStream(entry));
				
				zipEntries.add(zipEntryName);
			}
			System.out.printf("Zip file %s extracted successfully in %s", fileName, outputDir);
		} finally {
			file.close();
		}
		
		return new ArrayList<String>(zipEntries);
	}

	/*
	 * Utility method to read data from InputStream
	 */
	private static String extractEntry(String outputDir, final ZipEntry entry, InputStream is) throws IOException {
		try {
			
			String fileName =   DateUtil.getCurrentDateString(TimeZone.getDefault(), DateUtil.DATE_FORMAT) + StringUtils.replace(entry.getName(), " ", "_");	
			File file = new File(outputDir, fileName);
			if(!file.exists()){
				Files.copy(is, file.toPath());
			}
			
			return fileName;
			
		} catch (IOException ioex) {
			
			throw new IllegalArgumentException("Error reading file:" + entry.getName());
		}

	}
}
