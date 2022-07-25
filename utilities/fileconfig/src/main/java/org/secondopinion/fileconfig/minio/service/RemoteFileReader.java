package org.secondopinion.fileconfig.minio.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.secondopinion.fileconfig.connector.IReader;
import org.secondopinion.fileconfig.connector.WriteReadReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RemoteFileReader implements IReader<String, InputStream>{

	private static final Logger logger = LoggerFactory.getLogger(RemoteFileReader.class);
	
	public RemoteFileReader() {
	}
	
	@Override
	public InputStream read(String input) {
		String filePath = null; 
		try {
			filePath = WriteReadReference.decrypt(input);
			File file = new File(filePath);
			if(!file.exists()) {
				//new IllegalArgumentException("File not found : " + file.getName());
				return null;
			}
			return new FileInputStream(file);
        } catch (Exception ex) {
        	String errorMessage = "Error [" + ex.getMessage() + "] occurred while getting the file from FILE_SERVER [" + filePath + "] ";
            logger.error(errorMessage);
           // throw new RuntimeException(errorMessage);
            return null;
        }
	}

}
