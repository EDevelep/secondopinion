package org.secondopinion.fileconfig.connector;

public interface IWriter<INPUT, OUTPUT> {
	OUTPUT write(INPUT input);
}
