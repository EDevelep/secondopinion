package org.videoroomotpphone.room;

public interface IRoomConnector<T extends IRoomVideo<INPUT, OUTPUT>, INPUT, OUTPUT> {

	T room();
			
	
}
