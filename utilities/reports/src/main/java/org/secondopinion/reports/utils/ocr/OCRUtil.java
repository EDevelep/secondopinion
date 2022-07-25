package org.secondopinion.reports.utils.ocr;

import java.io.File;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class OCRUtil {
	
	public static void readText(String fileName) {
		Tesseract tesseract = new Tesseract();
		tesseract.setDatapath("C://Ram//temp//TessData");
		try {
			String str = tesseract.doOCR(new File(fileName));
			System.out.println(str);
		} catch (TesseractException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
