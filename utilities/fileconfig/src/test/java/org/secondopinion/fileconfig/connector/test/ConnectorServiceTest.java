package org.secondopinion.fileconfig.connector.test;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;
import org.secondopinion.fileconfig.connector.FileConfigConnector;
import org.secondopinion.fileconfig.connector.FileConfigDTO;
import org.secondopinion.fileconfig.connector.WriteReadReference.PropertiesEnum;
import org.secondopinion.fileconfig.connector.ConnectorService;

public class ConnectorServiceTest {

	
	private static String baseFolder = "G:\\GIT\\Workspace";
	// Long userId, Long clientId,
	// BucketName
	// The
	
	private Properties buildProperties(String connectorType, String remoteServerLocation, 
			String host, String accessKey, String secretKey, String bucketName) {
		
		Properties properties = new Properties();
		properties.setProperty(PropertiesEnum.TYPE.name(), connectorType);
		properties.setProperty(PropertiesEnum.REMOTE_FILE_SERVER_LOCATION.name(), remoteServerLocation);//"G:\\GIT\\Workspace"
		properties.setProperty(PropertiesEnum.HOST.name(), host);
		properties.setProperty(PropertiesEnum.ACCES_KEY.name(), accessKey);
		properties.setProperty(PropertiesEnum.SECRET_KEY.name(), secretKey);
		properties.setProperty(PropertiesEnum.BUCKET_NAME.name(), bucketName);
		
		return properties;
	}
	
	private FileConfigDTO buildConfigDTO(String uploadFolder, String fileName, InputStream inputStream, Long size) {
		FileConfigDTO fileConfigDTO = new FileConfigDTO();
		fileConfigDTO.setUploadFolder("22");
		fileConfigDTO.setFileName(fileName);
		fileConfigDTO.setContentType("png");
		fileConfigDTO.setInputStream(inputStream);//new FileInputStream(file)
		fileConfigDTO.setSize(size);
		
		return fileConfigDTO;
	}
	
	private FileConfigDTO buildFileCigDTOForTest() throws FileNotFoundException {
		File file = new File("C:\\Users\\Qontrack\\Desktop\\File\\sample-pdf-download-10-mb.pdf");
		return buildConfigDTO(baseFolder, file.getName(), new FileInputStream( file), file.length());
	}
	
	private Properties buildRemoteProperties() {
		return buildProperties(FileConfigConnector.REMOTE.name(), baseFolder, "", "", "", "");
	}
	
	private Properties buildMinioProperties() {
		return buildProperties(FileConfigConnector.MINIO.name(), "", "http://192.168.1.158:9001/"/*host*/, " admin"/*accesskey*/, "qontrack@123"/*seccretKey*/, "first"/*bucketName*/);
	}
	
	@Test
	public void testFileUploadToRemoteServer() throws FileNotFoundException {
		String uploadReference = new ConnectorService().writeStream(buildFileCigDTOForTest(), buildRemoteProperties());
		assertNotNull(uploadReference);//sCz8wh/ehjimzM6ExCIP1I/ryAlIfNFbsGp9u8Lw9e6IudCSUuTj5ZouMBTk+UJO
	}

	@Test
	public void testFileReadFromRemoteServer() throws IOException {
		InputStream inputStream = new ConnectorService().readStream("sCz8wh/ehjimzM6ExCIP1I/ryAlIfNFbsGp9u8Lw9e6IudCSUuTj5ZouMBTk+UJO", buildRemoteProperties());
		assertNotNull(inputStream);//
		inputStream.close();
	}
	
	@Test
	public void testFileUploadToMinioServer() throws FileNotFoundException {
		String uploadReference = new ConnectorService().writeStream(buildFileCigDTOForTest(), buildMinioProperties());
		assertNotNull(uploadReference);//ZZAzT6N/FBPvXmMtXGkmtAsd3i4zUwvaZ5ngFbizZsM=
	}

	@Test
	public void testFileReadFromMinioServer() throws IOException {
		InputStream inputStream = new ConnectorService().readStream("ZZAzT6N/FBPvXmMtXGkmtAsd3i4zUwvaZ5ngFbizZsM=", buildMinioProperties());
		assertNotNull(inputStream);//
		inputStream.close();
	}

}
