package org.secondopinion.fileconfig.minio.service;

import org.secondopinion.fileconfig.connector.FileConfigDTO;
import org.secondopinion.fileconfig.connector.IWriter;
import org.secondopinion.fileconfig.connector.WriteReadReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.minio.MinioClient;

public class MinioWriter implements IWriter<FileConfigDTO, String>{
	private static final Logger logger = LoggerFactory.getLogger(MinioWriter.class);
	
	private MinioClient minioClient;
	private String bucketName;
	public MinioWriter(MinioClient minioClient, String bucketName) {
		this.minioClient = minioClient;
		this.bucketName = bucketName;
	}
	
	@Override
	public String write(FileConfigDTO input) {
		String fileName = input.getFileName();
		String uploadFolder = input.getUploadFolder();
		String fileSeperator = WriteReadReference.FILE_SEPARATOR;
		try {
			
            if(!minioClient.bucketExists(bucketName)) {
            	minioClient.makeBucket(bucketName);
            }
            
            StringBuilder uploadedDirectory = new StringBuilder(uploadFolder).append(fileSeperator);
            uploadedDirectory.append(fileName);
            
            minioClient.putObject(bucketName, uploadedDirectory.toString(), input.getInputStream(), input.getSize(), null, null, input.getContentType());
           
            
            return WriteReadReference.encrypt(uploadedDirectory.toString());
        } catch (Exception ex) {
        	String errorMessage = "error [" + ex.getMessage() + "] occurred while uploading [" + fileName + "] ";
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
		
		
	}

}
