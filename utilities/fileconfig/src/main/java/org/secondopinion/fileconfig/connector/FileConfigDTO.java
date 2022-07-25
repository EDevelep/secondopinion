package org.secondopinion.fileconfig.connector;

import java.io.InputStream;

public class FileConfigDTO {
	private String fileName;
	private Long clientId;
	private InputStream inputStream;
	private String uploadFolder;
	private String contentType;
	private Long size;

	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public final String getUploadFolder() {
		return uploadFolder;
	}

	public final void setUploadFolder(String uploadFolder) {
		this.uploadFolder = uploadFolder;
	}

	public final String getContentType() {
		return contentType;
	}

	public final void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public final Long getSize() {
		return size;
	}

	public final void setSize(Long size) {
		this.size = size;
	}

}
