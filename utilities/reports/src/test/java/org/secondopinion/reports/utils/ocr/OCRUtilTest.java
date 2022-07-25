package org.secondopinion.reports.utils.ocr;

import static org.junit.Assert.*;

import org.junit.Test;

public class OCRUtilTest {

	@Test
	public void test() {
		String fileName = "C:\\Ram\\temp\\prescription.jpeg";
		
		OCRUtil.readText(fileName);
	}

}
