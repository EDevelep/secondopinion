package org.secondopinion.fileconfig.minio.service;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.secondopinion.fileconfig.connector.FileConfigDTO;
import org.secondopinion.fileconfig.connector.IWriter;
import org.secondopinion.fileconfig.connector.WriteReadReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemoteFileWriter implements IWriter<FileConfigDTO, String> {
	private static final Logger logger = LoggerFactory.getLogger(RemoteFileWriter.class);

	private String baseLocation;

	public RemoteFileWriter(String baseLocation) {
		this.baseLocation = baseLocation;
	}

	@Override
	public String write(FileConfigDTO configDTO) {

		String fileName = configDTO.getFileName();
		String uploadFolder = configDTO.getUploadFolder();

		InputStream inputStream = configDTO.getInputStream();
		
		String fileSeperator = WriteReadReference.FILE_SEPARATOR;

		String uploadedDirectory = baseLocation + fileSeperator + uploadFolder;
		String uploadedFileWithName = uploadedDirectory + fileSeperator + fileName;

		try {

			File file = new File(uploadedFileWithName);
			file.getParentFile().mkdirs();
			file.createNewFile();
			//Java 1.7 nio
			Files.copy(inputStream, Paths.get(uploadedFileWithName), StandardCopyOption.REPLACE_EXISTING);
			return WriteReadReference.encrypt(uploadedFileWithName);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw new RuntimeException(ex.getMessage());
		}

	}

}
