package org.secondopinion.utils.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.secondopinion.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

public class FileReader<OBJECT, READER extends ILineParser<OBJECT>> {

	private int pageSize = 250;
	private BufferedReader fileReader;
	private ILineParser<OBJECT> lineParser;
	private int lineNumber = 0;
	private boolean eof;
	private String currentLine;
	private int skipLines = 0;
   

	 SampleLineParser sampleLineParsernLineParser=new SampleLineParser();
	
	public FileReader(String fileName) {
		try {
			fileReader = new BufferedReader(new java.io.FileReader(new File(fileName)));
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Error loading file", e);
		}
	}

	public FileReader(MultipartFile file) {
		try {
			fileReader = new BufferedReader(new java.io.FileReader(file.getContentType()));
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Error loading file", e);
		}
	}

	public void setSkipLines(int skipLines) {
		this.skipLines = skipLines;
	}

	public void initialize() {
		if (skipLines > 0) {
			for (int i = 0; i < skipLines; i++) {
				try {
					String line = getNextLine();
					System.out.println(line);
				} catch (IOException e) {
					throw new RuntimeException("Error reading file: ", e);
				}
			}
		}
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<OBJECT> getNext() {

		List<OBJECT> list = new ArrayList<>();
		int pageLine = 1;
		try {
			if (lineNumber == 0 ) {
				getNextLine();
			}

			if (!eof) {
				if (pageLine % pageSize == 0) {
					return list;
				}

				OBJECT obj = (OBJECT) sampleLineParsernLineParser.parseLine(currentLine);
				if (obj != null) {
					list.add(obj);
				}
				getNextLine();
				pageLine++;
			}
		} catch (IOException e) {
			throw new RuntimeException("Error reading file: ", e);
		}
		return list;
	}

	private String getNextLine() throws IOException {
		currentLine = fileReader.readLine();
		if (StringUtil.isNullOrEmpty(currentLine)) {
			eof = true;
			return null;
		}
		lineNumber++;

		return currentLine;
	}

	public void closeResource() {
		try {
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isEof() {
		return eof;
	}
	
	public int getLineNumber() {
		return lineNumber;
	}
}
