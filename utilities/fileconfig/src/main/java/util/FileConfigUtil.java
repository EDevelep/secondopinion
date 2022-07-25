package util;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.secondopinion.fileconfig.connector.ConnectorService;
import org.secondopinion.fileconfig.connector.FileConfigConnector;
import org.secondopinion.fileconfig.connector.FileConfigDTO;
import org.secondopinion.fileconfig.connector.WriteReadReference;
import org.secondopinion.fileconfig.connector.WriteReadReference.PropertiesEnum;


public class FileConfigUtil {
	private static Properties buildPropertiesForRemote(FileConfigConnector fileConnector, String remoteServerLocation) {
		if(isNullOrEmpty(remoteServerLocation)) {
			throw new IllegalArgumentException("Please provide [remote Server Location].");
		}
		Properties properties = new Properties();
		//to do. we have to remove properties and put the object.
		properties.setProperty(PropertiesEnum.TYPE.name(), fileConnector.name());
		properties.setProperty(PropertiesEnum.REMOTE_FILE_SERVER_LOCATION.name(), remoteServerLocation);// "G:\\GIT\\Workspace"
		properties.setProperty(PropertiesEnum.HOST.name(), "");
		properties.setProperty(PropertiesEnum.ACCES_KEY.name(), "");
		properties.setProperty(PropertiesEnum.SECRET_KEY.name(), "");
		properties.setProperty(PropertiesEnum.BUCKET_NAME.name(), "");

		return properties;
	}

	private static Properties buildPropertiesForCloud(FileConfigConnector fileConnector, String host, String accessKey,
			String secretKey, String bucketName) {

		if(isNullOrEmpty(host) || isNullOrEmpty(accessKey) 
				|| isNullOrEmpty(secretKey) || isNullOrEmpty(bucketName)) {
			throw new IllegalArgumentException("Please provide [host, accessKey, secretKey, bucketName].");
		}
		Properties properties = new Properties();
		//to do. we have to remove properties and put the object.
		properties.setProperty(PropertiesEnum.TYPE.name(), fileConnector.name());
		properties.setProperty(PropertiesEnum.HOST.name(), host);
		properties.setProperty(PropertiesEnum.ACCES_KEY.name(), accessKey);
		properties.setProperty(PropertiesEnum.SECRET_KEY.name(), secretKey);
		properties.setProperty(PropertiesEnum.BUCKET_NAME.name(), bucketName);

		return properties;
	}

	private static boolean isNullOrEmpty(String value) {
		return value == null || value.isEmpty();
	}

	private static FileConfigDTO buildConfigDTO(
			FileDetails fileDetails)
			throws IOException {

		FileConfigDTO fileConfigDTO = new FileConfigDTO();

		String fileSeparator = WriteReadReference.FILE_SEPARATOR;
		LocalDate localDate = LocalDate.now();

		StringBuilder buildFileLocation = new StringBuilder(localDate.getYear() + fileSeparator); 
		buildFileLocation.append(localDate.getMonthValue() + fileSeparator);
		buildFileLocation.append(localDate.getDayOfMonth() + fileSeparator);
		buildFileLocation.append( fileDetails.getUserId() + fileSeparator);
		if(fileDetails.getPrimaryKey() != null) {
			buildFileLocation.append(fileDetails.getPrimaryKey());
		}
		fileConfigDTO.setUploadFolder(buildFileLocation.toString());
		fileConfigDTO.setFileName(fileDetails.getOriginalFilename());
		fileConfigDTO.setContentType(fileDetails.getContentType());
		fileConfigDTO.setInputStream(fileDetails.getInputStream());// new FileInputStream(file)
		fileConfigDTO.setSize(fileDetails.getSize());

		return fileConfigDTO;
	}

	private static byte[] buildByteArrayFromInputStream(InputStream is) throws IOException {
		if(Objects.isNull(is )) { return new byte[0]; }
		return IOUtils.toByteArray(is);
	}

	public static byte[] readFile(FileDetails fileDetails) throws IOException {
		Properties properties = null;
		if(FileConfigConnector.REMOTE.name().equals(fileDetails.getFileServer())) {
			properties = buildPropertiesForRemote(FileConfigConnector.REMOTE, fileDetails.getRemoteServerLocation());
		}
		else if(FileConfigConnector.S3.name().equals(fileDetails.getFileServer())) {
			properties = buildPropertiesForCloud(FileConfigConnector.S3, fileDetails.getHost(), fileDetails.getAccessKey(), 
					fileDetails.getSecretKey(), fileDetails.getBucketName());
		}
		else if(FileConfigConnector.MINIO.name().equals(fileDetails.getFileServer())) {
			properties = buildPropertiesForCloud(FileConfigConnector.MINIO, fileDetails.getHost(), 
					fileDetails.getAccessKey(), fileDetails.getSecretKey(),
					fileDetails.getBucketName());
		}
		return buildByteArrayFromInputStream(new ConnectorService().readStream(fileDetails.getLocationReference(), properties));
	}

	//remove arguments and put the object from app properties
	public static String uploadFile(FileDetails fileDetails) throws IOException {

		if(Objects.isNull(fileDetails.getUserId() )) {
			throw new IOException("Please provide userid.");
		}
		
		String fileExtnsion = FilenameUtils.getExtension(fileDetails.getOriginalFilename());
		if(Objects.isNull(FileExtensions.valueOf(fileExtnsion.toUpperCase()) )) {
			throw new IOException("Please upload a valid file which are [" + FileExtensions.values() + "]");
		}
		
		Properties properties = null;
		if(FileConfigConnector.REMOTE.name().equals(fileDetails.getFileServer())) {
			properties = buildPropertiesForRemote(FileConfigConnector.REMOTE, fileDetails.getRemoteServerLocation());
		}
		else if(FileConfigConnector.S3.name().equals(fileDetails.getFileServer())) {
			properties = buildPropertiesForCloud(FileConfigConnector.S3, fileDetails.getHost(), fileDetails.getAccessKey(), fileDetails.getSecretKey(), fileDetails.getBucketName());
		}
		else if(FileConfigConnector.MINIO.name().equals(fileDetails.getFileServer())) {
			properties = buildPropertiesForCloud(FileConfigConnector.MINIO, fileDetails.getHost(), fileDetails.getAccessKey(), fileDetails.getSecretKey(),
					fileDetails.getBucketName());
		}
		FileConfigDTO fileConfigDTO = buildConfigDTO(fileDetails);
		return  new ConnectorService().writeStream(fileConfigDTO, properties);
	}
	
	public enum FileExtensions {
		JPG, JPEG, PNG, PDF
	}
	
	

}
