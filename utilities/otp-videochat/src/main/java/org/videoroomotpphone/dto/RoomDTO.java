package org.videoroomotpphone.dto;

import org.videoroomotpphone.ServiceTypeEnum;

import com.twilio.rest.video.v1.room.Participant.Status;
import com.twilio.rest.video.v1.Room.RoomStatus;
import com.twilio.rest.video.v1.Room.RoomType;

public class RoomDTO {

	private ServiceTypeEnum serviceTypeEnum;
	private String roomName;
	private String roomSID;
	private String callBackURI;
	private boolean enableTurn;
	private boolean recordEnabled;
	private RoomType roomType;
	private RoomStatus roomStatus;
	private Status participantStatus;
	private String chatIdentity;
	private ChatType chatType;
	private String participantName;
	private String toNumber;
	
	public String getChatIdentity() {
		return chatIdentity;
	}

	public void setChatIdentity(String chatIdentity) {
		this.chatIdentity = chatIdentity;
	}

	public ServiceTypeEnum getServiceTypeEnum() {
		return serviceTypeEnum;
	}

	public void setServiceTypeEnum(ServiceTypeEnum serviceTypeEnum) {
		this.serviceTypeEnum = serviceTypeEnum;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getCallBackURI() {
		return callBackURI;
	}

	public void setCallBackURI(String callBackURI) {
		this.callBackURI = callBackURI;
	}

	public boolean isEnableTurn() {
		return enableTurn;
	}

	public void setEnableTurn(boolean enableTurn) {
		this.enableTurn = enableTurn;
	}

	public boolean isRecordEnabled() {
		return recordEnabled;
	}

	public void setRecordEnabled(boolean recordEnabled) {
		this.recordEnabled = recordEnabled;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public String getRoomSID() {
		return roomSID;
	}

	public void setRoomSID(String roomSID) {
		this.roomSID = roomSID;
	}

	public RoomStatus getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(RoomStatus roomStatus) {
		this.roomStatus = roomStatus;
	}
	
	public ChatType getChatType() {
		return chatType;
	}

	public void setChatType(ChatType chatType) {
		this.chatType = chatType;
	}

	public String getParticipantName() {
		return participantName;
	}

	public void setParticipantName(String participantName) {
		this.participantName = participantName;
	}

	public Status getParticipantStatus() {
		return participantStatus;
	}

	public void setParticipantStatus(Status participantStatus) {
		this.participantStatus = participantStatus;
	}

	public String getToNumber() {
		return toNumber;
	}

	public void setToNumber(String toNumber) {
		this.toNumber = toNumber;
	}

	public enum ChatType {
		TEXT_CHAT, VIDEO_CHAT, AUDIO_CHAT
	}
	
}
