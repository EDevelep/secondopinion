package org.secondopinion.fileconfig.minio.service;

import java.io.InputStream;
import java.util.Objects;

import org.secondopinion.fileconfig.connector.IReader;
import org.secondopinion.fileconfig.connector.WriteReadReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.minio.MinioClient;

public class MinioReader implements IReader<String, InputStream>{

	private static final Logger logger = LoggerFactory.getLogger(MinioReader.class);
	
	private MinioClient minioClient = null;
	private String bucketName;
	public MinioReader(MinioClient minioClient, String bucketName) {
		this.minioClient = minioClient;
		this.bucketName = bucketName;
	}
	
	@Override
	public InputStream read(String input) {
		
		try {
			String fileWithFolder = WriteReadReference.decrypt(input);
			
			String[] fileSplitArray = fileWithFolder.split(WriteReadReference.FILE_SEPARATOR);
			String objectName = stringArrayToDelimited(fileSplitArray, WriteReadReference.FILE_SEPARATOR);
			
            return minioClient.getObject(bucketName, objectName);
        } catch (Exception ex) {
        	String errorMessage = "error [" + ex.getMessage() + "] occurred while getting the file from minio [" + input + "] ";
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
	}
	
	public static String stringArrayToDelimited(String[] values,
			String delimiter) {

		if (Objects.isNull(values ) || values.length == 0) {
			return "";
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < values.length; i++) {
			if (i > 0) {
				sb.append(delimiter);
			}
			sb.append(values[i]);
		}

		return sb.toString();
	}

}
