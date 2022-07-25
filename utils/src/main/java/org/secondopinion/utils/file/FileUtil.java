package org.secondopinion.utils.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.util.Objects;
import java.util.TimeZone;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.util.StringUtils;

import org.secondopinion.utils.DateUtil;

public class FileUtil {
	/**
	 * @param stream
	 */
	public static void closeInputStream(InputStream stream) {
		try {
			if (stream != null)
				stream.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * @param stream
	 */
	public static void closeOutputStream(OutputStream stream) {
		try {
			if (stream != null)
				stream.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * @param reader
	 */
	public static void closeReader(Reader reader) {
		try {
			if (reader != null)
				reader.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * @param writer
	 */
	public static void closeWriter(Writer writer) {
		try {
			if (writer != null)
				writer.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * @param fileName
	 * @return
	 */
	public static String getFileContentsAsString(String fileName) {
		BufferedReader reader = null;
		try {
			StringBuffer contents = new StringBuffer();

			reader = new BufferedReader(new FileReader(fileName));
			String line = null;

			while ((line = reader.readLine()) != null) {
				contents.append(line);
				contents.append(System.getProperty("line.separator"));
			}

			return contents.toString();
		} catch (Exception e) {
			throw new IllegalArgumentException("Error Reading file: ", e);
		}finally{
			closeReader(reader);
		}
	}

	

	public static String getFileContentsAsString(File fileName) {
		BufferedReader reader = null;
		try {
			StringBuffer contents = new StringBuffer();

			reader = new BufferedReader(new FileReader(fileName));
			String line = null;

			while ((line = reader.readLine()) != null) {
				contents.append(line);
				contents.append(System.getProperty("line.separator"));
			}

			return contents.toString();
		} catch (Exception e) {
			throw new IllegalArgumentException("Error Reading file: ", e);
		}finally{
			closeReader(reader);
		}
	}

	/**
	 * Retrieves the entire contents of the specified file
	 * as a string.
	 *
	 * @param filename the name of the file to read.
	 * @return the contents of the file as a string.
	 */
	public static String getFileContentsUsingClassPath(String filename) throws IOException {
		InputStream inputStream = null;
		try{
			inputStream = getInputStreamForFile(filename);
			return getStreamContentsAsString(inputStream);
		}finally{
			closeInputStream(inputStream);
		}
	}

	public static String getStreamContentsAsString(InputStream inputStream) throws IOException{
		StringBuffer buffer = new StringBuffer();
		byte[] buf = new byte[1024];
		int len;
		while ((len = inputStream.read(buf)) > 0) {
			buffer.append(new String(buf, 0, len));
		}
		return buffer.toString();

	}


	public static InputStream getInputStreamForFile(String fileName){
		InputStream inputStream = ClassLoader.getSystemResourceAsStream(fileName);
		if (Objects.isNull(inputStream ))
			throw new IllegalArgumentException("File not found: " + fileName);
		else
			return inputStream;
	}

	/**
	 * @param fileName
	 */
	public static void deleteFile(String fileName){
		File file = new File(fileName);
		if(file.exists()){
			file.delete();
		}
	}

	/**
	 * @param fileName
	 */
	public static void createFile(String fileName){
		File file = new File(fileName);
		if(file.exists()){
			file.delete();
		}

		createDirectoriesAndFile(fileName, file);
	}


	/**
	 * @param fileName
	 */
	public static void createFileIfNotExists(String fileName){
		File file = new File(fileName);
		if(file.exists()){
			return;
		}

		createDirectoriesAndFile(fileName, file);
	}

	private static void createDirectoriesAndFile(String fileName, File file) {
		try {
			createDirectoriesForFile(fileName);
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param fileName
	 */
	private static void createDirectoriesForFile(String fileName){
		String dirName = "";
		if(fileName.contains("\\")){
			dirName = fileName.substring(0, fileName.lastIndexOf("\\"));
			//System.out.println(dirName);
		}else if(fileName.contains("/")){
			dirName = fileName.substring(0, fileName.lastIndexOf("/"));
			//System.out.println(dirName);
		}
		File file = new File(dirName);
		if(!file.exists())
			file.mkdirs();
	}

	/**
	 * @param dirName
	 */
	public static void createDirectories(String dirName){
		new File( dirName ).mkdirs();
		System.out.println("Creating Directory [" + dirName + "]");
	}

	/**
	 * @param fileName
	 * @return
	 */
	public static Writer getFileWriter(String fileName){
		try {
			createFile(fileName);
			return new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
		} catch (IOException e) {
			throw new IllegalArgumentException("Error getting PrintWriter for file " + fileName, e);
		}
	}

	public static Writer getFileWriter(String fileName, boolean append){
		try {
			if(!append){
				createFile(fileName);
			}else{
				createFileIfNotExists(fileName);
			}
			return new PrintWriter(new BufferedWriter(new FileWriter(fileName, append)));
		} catch (IOException e) {
			throw new IllegalArgumentException("Error getting PrintWriter for file " + fileName, e);
		}
	}

	public static class StreamSavedDetails {
		private String fileName;
		private String fileLocation;
		public StreamSavedDetails(String fileName, String fileLocation) {
			this.fileName = fileName;
			this.fileLocation = fileLocation;
		}
		public String getFileName() {
			return fileName;
		}
		public String getFileLocation() {
			return fileLocation;
		}
	}

	public static StreamSavedDetails saveStream(String fileLocation, String uploadedFileName, InputStream stream) {

		String fileName = StringUtils.replace(uploadedFileName, " - ", "_");
		fileName = StringUtils.replace(fileName, "-", "_");
		fileName = StringUtils.replace(fileName, " ", "_");
		fileName = DateUtil.getCurrentDateString(TimeZone.getDefault(), DateUtil.DATE_FORMAT) + fileName;
		try {
			FileUtil.createDirectories(fileLocation);
			File file = new File(fileLocation, fileName);
			Files.copy(stream, file.toPath());
		} catch ( FileAlreadyExistsException fase){
			return new StreamSavedDetails(fileName, fileLocation);
		}catch (IOException e) {
			new IllegalArgumentException("Error saving file: " + e.getMessage(), e);
		}

		return new StreamSavedDetails(fileName, fileLocation);
	}

	// compress the image bytes before storing it in the database
	public static byte[] compressBytes(byte[] data, int byteLength) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[byteLength];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (Exception e) {
			new IllegalArgumentException("Error compressing file: " + e.getMessage(), e);
		}
		return outputStream.toByteArray();
	}
	// uncompress the image bytes before returning it to the angular application
	public static byte[] decompressBytes(byte[] data,  int byteLength) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[byteLength];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (Exception e) {
			new IllegalArgumentException("Error decompressing file: " + e.getMessage(), e);
		} 
		return outputStream.toByteArray();
	}

public static void main(String[] args) {
		
	}
}
