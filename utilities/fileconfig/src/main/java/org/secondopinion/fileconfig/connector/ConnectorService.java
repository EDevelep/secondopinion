package org.secondopinion.fileconfig.connector;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import org.secondopinion.fileconfig.connector.WriteReadReference.PropertiesEnum;


public class ConnectorService {

	// Long userId, Long clientId,
	// BucketName
	// The

	@SuppressWarnings("rawtypes")
	Map<String, IWriter> writerMap = new HashMap<String, IWriter>();
	@SuppressWarnings("rawtypes")
	Map<String, IReader> readerMap = new HashMap<String, IReader>();

	@SuppressWarnings("unchecked")
	public <INPUT, OUTPUT> OUTPUT writeStream(INPUT input, Properties properties) {
		String type = properties.getProperty(PropertiesEnum.TYPE.name());
		if(type == null || type.isEmpty()) {
			throw new IllegalArgumentException("type can not be null.");
		}
		List<String> fcTypes = Arrays.stream(FileConfigConnector.values()).map(pge -> pge.name()).collect(Collectors.toList());
		if(!fcTypes.contains(type)) {
			throw new IllegalArgumentException("the type should be in " + fcTypes);
		}
		IWriter<INPUT, OUTPUT> writer;

		if (!writerMap.containsKey(type)) {
			writer = FileConfigConnector.getWriter(type, properties);
			writerMap.put(type, writer);
		} else {
			writer = writerMap.get(type);
		}

		return writer.write(input);
	}

	@SuppressWarnings("unchecked")
	public <INPUT, OUTPUT> OUTPUT readStream(INPUT input, Properties properties) {
		String type = properties.getProperty(PropertiesEnum.TYPE.name());

		IReader<INPUT, OUTPUT> reader;

		if (!readerMap.containsKey(type)) {
			reader = FileConfigConnector.getReader(type, properties);
			readerMap.put(type, reader);
		} else {
			reader = readerMap.get(type);
		}

		return reader.read(input);
	}
}
