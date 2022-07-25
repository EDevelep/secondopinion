package org.secondopinion.fileconfig.connector.config;

import java.util.Properties;

import org.secondopinion.fileconfig.connector.IReader;
import org.secondopinion.fileconfig.connector.IWriter;

public interface IConnectorConfig<T extends IWriter<WRITER_INPUT, WRITER_OUTPUT>, 
						Y extends IReader<READER_INPUT, READER_OUTPUT>, 
						WRITER_INPUT, WRITER_OUTPUT, READER_INPUT, READER_OUTPUT> {
							
	T configureWriteConnector(Properties properties);
	
	Y configureReadConnector(Properties properties);
}
