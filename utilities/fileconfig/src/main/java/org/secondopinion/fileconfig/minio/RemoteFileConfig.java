package org.secondopinion.fileconfig.minio;

import java.io.InputStream;
import java.util.Properties;

import org.secondopinion.fileconfig.connector.FileConfigDTO;
import org.secondopinion.fileconfig.connector.WriteReadReference.PropertiesEnum;
import org.secondopinion.fileconfig.connector.config.IConnectorConfig;
import org.secondopinion.fileconfig.minio.service.RemoteFileReader;
import org.secondopinion.fileconfig.minio.service.RemoteFileWriter;

public class RemoteFileConfig implements IConnectorConfig<RemoteFileWriter, RemoteFileReader, FileConfigDTO, String, String, InputStream>{

	@Override
	public RemoteFileWriter configureWriteConnector(Properties properties) {
		return new RemoteFileWriter(getBaseLocation( properties) );
	}

	@Override
	public RemoteFileReader configureReadConnector(Properties properties) {
		return new RemoteFileReader( );
	}
	private String getBaseLocation(Properties properties) {
		String baseLocation = properties.getProperty(PropertiesEnum.REMOTE_FILE_SERVER_LOCATION.name());
		
		return baseLocation;
	}
	

}
