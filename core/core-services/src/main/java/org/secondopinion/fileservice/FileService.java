package org.secondopinion.fileservice;

import java.io.IOException;

import org.secondopinion.configurations.AppProperties.Fc;
import org.springframework.web.multipart.MultipartFile;

import util.FileConfigUtil;
import util.FileDetails;



public class FileService {
	
	private FileService() {
		
	}
	
	public static String uploadFile(Fc fc, Long userId, MultipartFile multipartFile, Long primaryKey) {//to upload the file to the remote location
		
		if(multipartFile == null) {
			throw new IllegalArgumentException("Multipart file can not be null.");
		}
		
		FileDetails fileDetails = new FileDetails();
		fileDetails.setFileServer(fc.getLocationType());
		fileDetails.setRemoteServerLocation(fc.getRemoteServerLocation());
		fileDetails.setHost(fc.getHost());
		fileDetails.setAccessKey(fc.getAccessKey());
		fileDetails.setSecretKey(fc.getSecretKey());
		fileDetails.setBucketName(fc.getBucketName());
		fileDetails.setUserId(userId);
		fileDetails.setOriginalFilename(multipartFile.getOriginalFilename());
		fileDetails.setContentType(multipartFile.getContentType());
		fileDetails.setSize(multipartFile.getSize());
		fileDetails.setPrimaryKey(primaryKey);
		try {
			fileDetails.setInputStream(multipartFile.getInputStream());
			return FileConfigUtil.uploadFile(fileDetails);
		} catch (IOException e) {
			throw new IllegalArgumentException("Exception while uploading file." + e.getMessage());
		}
	}

	public static byte[] readFile(Fc fc, String locationReference) {//to read the file from the location
		FileDetails fileDetails = new FileDetails();
		fileDetails.setFileServer(fc.getLocationType());
		fileDetails.setRemoteServerLocation(fc.getRemoteServerLocation());
		fileDetails.setHost(fc.getHost());
		fileDetails.setAccessKey(fc.getAccessKey());
		fileDetails.setSecretKey(fc.getSecretKey());
		fileDetails.setBucketName(fc.getBucketName());
		fileDetails.setLocationReference(locationReference);
		try {
			return FileConfigUtil.readFile(fileDetails);
		} catch (IOException e) {
			throw new IllegalArgumentException("Exception while reading the file." + e.getMessage());
		}
	}
}
