package org.videoroomotpphone.dto;

import com.twilio.rest.video.v1.Room;

public class RoomTokenResponse {

	private Room room;
	private String tokenForChat;
	
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public String getTokenForChat() {
		return tokenForChat;
	}
	public void setTokenForChat(String tokenForChat) {
		this.tokenForChat = tokenForChat;
	}
	
	
}
