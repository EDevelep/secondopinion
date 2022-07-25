package com.dodaso.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;

import com.dodaso.exception.CodeGenException;

/**
 * @author rswarna
 *
 */
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
			throw new RuntimeException("Error Reading file: ", e);
		}finally{
			closeReader(reader);
		}
	}
	
	/**
	 * @param fileName
	 * @return
	 */
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
			throw new RuntimeException("Error Reading file: ", e);
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
	        StringBuffer buffer = new StringBuffer();
	        byte[] buf = new byte[1024];
	        int len;
	        while ((len = inputStream.read(buf)) > 0) {
	            buffer.append(new String(buf, 0, len));
	        }
	        return buffer.toString();
        }finally{
        	closeInputStream(inputStream);
        }
    }
    
    /**
     * Throws CodeGenException if the file is not found.
     *
     * @param fileName
     * @return
     * @throws EtgException
     */
    public static InputStream getInputStreamForFile(String fileName){
        InputStream inputStream = ClassLoader.getSystemResourceAsStream(fileName);
        if (inputStream == null)
            throw new CodeGenException("File not found: " + fileName);
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
	public static Writer getPrintWriter(String fileName){
		try {
			createFile(fileName);
			return new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
		} catch (IOException e) {
			e.printStackTrace();
			throw new CodeGenException("Error getting PrintWriter for file " + fileName, e);
		}
	}
}
