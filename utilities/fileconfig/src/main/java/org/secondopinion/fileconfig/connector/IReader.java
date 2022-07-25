package org.secondopinion.fileconfig.connector;

public interface IReader<INPUT, OUTPUT> {
	OUTPUT read(INPUT input);
}
