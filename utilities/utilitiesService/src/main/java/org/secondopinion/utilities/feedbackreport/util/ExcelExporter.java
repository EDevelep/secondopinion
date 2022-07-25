package org.secondopinion.utilities.feedbackreport.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ExcelExporter {
	public static OutputStream convertToCsvStream(List<Object[]> data){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		data.forEach(row -> {
			try {
				baos.write(toCsvString(row).getBytes());
			} catch (IOException e) {
				throw new IllegalArgumentException("Error creating stream:", e);
			}
		});
		
		
		return baos;
	}
	
	private static String toCsvString(Object[] row){
		String csvRow = "";
		
		for(Object obj : row){
			csvRow = ","+String.valueOf(obj);
		}
		
		return csvRow.substring(1);
	}
}
