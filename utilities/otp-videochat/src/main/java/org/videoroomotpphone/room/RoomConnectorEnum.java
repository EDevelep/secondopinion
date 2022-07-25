package org.videoroomotpphone.room;

public enum RoomConnectorEnum {

	TWILIO_ROOM(new RoomConnector());
	@SuppressWarnings("rawtypes")
	private IRoomConnector roomConnector;
	@SuppressWarnings("rawtypes")
	private RoomConnectorEnum(IRoomConnector roomConnector) {
		this.roomConnector = roomConnector;
	}
	
	@SuppressWarnings("rawtypes")
	public static IRoomVideo roomConnection(String type) {
		RoomConnectorEnum connector = RoomConnectorEnum.valueOf(type);
		return connector.roomConnector.room();
	}
}
