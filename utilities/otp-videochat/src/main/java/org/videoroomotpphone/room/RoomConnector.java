package org.videoroomotpphone.room;


import org.videoroomotpphone.dto.RoomDTO;

public class RoomConnector implements IRoomConnector<RoomVideoServiceImpl, RoomDTO, Object> {

	@Override
	public RoomVideoServiceImpl room() {
		return new RoomVideoServiceImpl();
	}

}
