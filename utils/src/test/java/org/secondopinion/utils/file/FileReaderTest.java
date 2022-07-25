package org.secondopinion.utils.file;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.secondopinion.utils.threadlocal.Medicine;

public class FileReaderTest {

	@Test
	public void test() {
		FileReader<Medicine, SampleLineParser> reader = new FileReader<>("E:\\drug_file_1.csv");
		reader.setSkipLines(1);
		reader.setPageSize(250);
		reader.initialize();
		
		while(!reader.isEof()) {
			List<Medicine> medicines =  reader.getNext();
			System.out.println(medicines + " Line # " + reader.getLineNumber());
		
			
		}
	}

}
