package org.secondopinion.fileconfig.minio;

import java.io.InputStream;
import java.util.Properties;

import org.secondopinion.fileconfig.connector.FileConfigDTO;
import org.secondopinion.fileconfig.connector.WriteReadReference.PropertiesEnum;
import org.secondopinion.fileconfig.connector.config.IConnectorConfig;
import org.secondopinion.fileconfig.minio.service.MinioReader;
import org.secondopinion.fileconfig.minio.service.MinioWriter;

import io.minio.MinioClient;

public class MinioConfig implements IConnectorConfig<MinioWriter, MinioReader, FileConfigDTO, String, String, InputStream>{

	@Override
	public MinioWriter configureWriteConnector(Properties properties) {
		return new MinioWriter(buildMinIoClient( properties), properties.getProperty(PropertiesEnum.BUCKET_NAME.name()));
	}

	@Override
	public MinioReader configureReadConnector(Properties properties) {
		return new MinioReader(buildMinIoClient( properties), properties.getProperty(PropertiesEnum.BUCKET_NAME.name()));
	}

	private MinioClient buildMinIoClient(Properties properties) {
		MinioClient minioClient = null;
		String hostName = properties.getProperty(PropertiesEnum.HOST.name());
		String accessKey = properties.getProperty(PropertiesEnum.ACCES_KEY.name());
		String secretKey = properties.getProperty(PropertiesEnum.SECRET_KEY.name());
		
		try {
			minioClient = new MinioClient(hostName, accessKey, secretKey);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return minioClient;
	}
}
