package util;

import java.io.InputStream;

public class FileDetails {
	private String fileServer;
	private String remoteServerLocation;
	private String host;
	private String accessKey;
	
	private String secretKey;
	private String bucketName;
	private Long userId;
	private String originalFilename;
	private String contentType;
	private InputStream inputStream;
	private Long size;
	private Long primaryKey;
	private String locationReference;
	
	public String getFileServer() {
		return fileServer;
	}
	public void setFileServer(String fileServer) {
		this.fileServer = fileServer;
	}
	public String getRemoteServerLocation() {
		return remoteServerLocation;
	}
	public void setRemoteServerLocation(String remoteServerLocation) {
		this.remoteServerLocation = remoteServerLocation;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getAccessKey() {
		return accessKey;
	}
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public String getBucketName() {
		return bucketName;
	}
	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getOriginalFilename() {
		return originalFilename;
	}
	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	public Long getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(Long primaryKey) {
		this.primaryKey = primaryKey;
	}
	public String getLocationReference() {
		return locationReference;
	}
	public void setLocationReference(String locationReference) {
		this.locationReference = locationReference;
	}
	
}
