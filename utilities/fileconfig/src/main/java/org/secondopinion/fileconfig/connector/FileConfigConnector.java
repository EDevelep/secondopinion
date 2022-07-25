package org.secondopinion.fileconfig.connector;

import java.util.Properties;

import org.secondopinion.fileconfig.connector.config.IConnectorConfig;
import org.secondopinion.fileconfig.minio.MinioConfig;
import org.secondopinion.fileconfig.minio.RemoteFileConfig;

public enum FileConfigConnector {
	MINIO(new MinioConfig()), S3(new MinioConfig()), REMOTE(new RemoteFileConfig());
	
	@SuppressWarnings("rawtypes")
	private final IConnectorConfig connectorConfig;
	
	@SuppressWarnings("rawtypes")
	private FileConfigConnector(IConnectorConfig connectorConfig) {
		this.connectorConfig = connectorConfig;
	}
	
	@SuppressWarnings("rawtypes")
	public static IReader getReader(String type, Properties properties) {
		FileConfigConnector connector = FileConfigConnector.valueOf(type);
		return connector.connectorConfig.configureReadConnector(properties);
	}
	
	@SuppressWarnings("rawtypes")
	public static IWriter getWriter(String type, Properties properties) {
		FileConfigConnector connector = FileConfigConnector.valueOf(type);
		return connector.connectorConfig.configureWriteConnector(properties);
	}
	
}
