package org.secondopinion.utils;

import java.net.URISyntaxException;
import java.util.List;

import org.videoroomotpphone.ServiceTypeEnum;
import org.videoroomotpphone.dto.RoomDTO;
import org.videoroomotpphone.dto.RoomDTO.ChatType;
import org.videoroomotpphone.dto.RoomTokenResponse;
import org.videoroomotpphone.room.RoomConnectorEnum;
import org.videoroomotpphone.room.RoomConnectorService;

import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.video.v1.Room;
import com.twilio.rest.video.v1.Room.RoomStatus;
import com.twilio.rest.video.v1.Room.RoomType;
import com.twilio.rest.video.v1.room.Participant;
import com.twilio.rest.video.v1.room.RoomRecording;
import com.twilio.rest.video.v1.room.Participant.Status;

public class VideoChatRoomUtil {

	public static RoomTokenResponse createTwilioRoom(String roomName, String callbackURI, String chatIdentity, String chatType) {
		RoomDTO roomDTO = new RoomDTO();
		roomDTO.setRoomName(roomName);
		roomDTO.setChatIdentity(chatIdentity);
		roomDTO.setRoomType(RoomType.GROUP_SMALL);
		roomDTO.setRecordEnabled(true);
		roomDTO.setCallBackURI(callbackURI);
		roomDTO.setServiceTypeEnum(ServiceTypeEnum.TWILIO);
		roomDTO.setChatType(ChatType.valueOf(chatType));
		return (RoomTokenResponse) RoomConnectorService.INSTANCE.createRoomByRoomType(roomDTO, RoomConnectorEnum.TWILIO_ROOM.name());
		
	}
	
	public static Room retrieiveRoomStatus(String roomName, String roomSID) {
		RoomDTO roomDTO = new RoomDTO();
		roomDTO.setServiceTypeEnum(ServiceTypeEnum.TWILIO);
		if(!StringUtil.isNullOrEmpty(roomName)) {
			roomDTO.setRoomName(roomName);
			return (Room) RoomConnectorService.INSTANCE.retrieiveRoomStatusByRoomName(roomDTO, RoomConnectorEnum.TWILIO_ROOM.name());
		}
		if(!StringUtil.isNullOrEmpty(roomSID)) {
			roomDTO.setRoomName(roomName);
			return (Room) RoomConnectorService.INSTANCE.retrieiveRoomStatusByRoomSID(roomDTO, RoomConnectorEnum.TWILIO_ROOM.name());
		}
		return null;
		
		
		
	}
	
	public static Participant retrieveAParticipant(String roomname, String participantname) {
		RoomDTO roomDTO = new RoomDTO();
		roomDTO.setRoomName(roomname);
		roomDTO.setParticipantName(participantname);
		return (Participant) RoomConnectorService.INSTANCE.retrieveAParticipant(roomDTO, RoomConnectorEnum.TWILIO_ROOM.name());
	}

	public static Participant removeAParticipant(String roomname, String participantname) {
		RoomDTO roomDTO = new RoomDTO();
		roomDTO.setRoomName(roomname);
		roomDTO.setParticipantName(participantname);
		return (Participant) RoomConnectorService.INSTANCE.removeAParticipant(roomDTO, RoomConnectorEnum.TWILIO_ROOM.name());
	}

	@SuppressWarnings("unchecked")
	public static List<Participant> participantsBasedOnStatus(String roomname, Status participantstatus) {
		RoomDTO roomDTO = new RoomDTO();
		roomDTO.setRoomName(roomname);
		roomDTO.setParticipantStatus(participantstatus);
		return (List<Participant>) RoomConnectorService.INSTANCE.participantsBasedOnStatus(roomDTO, RoomConnectorEnum.TWILIO_ROOM.name());
	}

	public static Room updateTheRoomStatus(String roomSID, RoomStatus roomstatus) {
		RoomDTO roomDTO = new RoomDTO();
		roomDTO.setRoomSID(roomSID);
		roomDTO.setRoomStatus(roomstatus);
		return (Room) RoomConnectorService.INSTANCE.updateTheRoomStatus(roomDTO, RoomConnectorEnum.TWILIO_ROOM.name());
	}

	@SuppressWarnings("unchecked")
	public static List<Participant> retrieveRoomParticipants(String roomSID) {
		RoomDTO roomDTO = new RoomDTO();
		roomDTO.setRoomSID(roomSID);
		return (List<Participant>) RoomConnectorService.INSTANCE.retrieveRoomParticipants(roomDTO, RoomConnectorEnum.TWILIO_ROOM.name());
	}
	
	public static Call voiceChat(String toNumber) throws URISyntaxException {
		RoomDTO roomDTO = new RoomDTO();
		roomDTO.setToNumber(toNumber);
		return (Call) RoomConnectorService.INSTANCE.voiceChat(roomDTO, RoomConnectorEnum.TWILIO_ROOM.name());
	}
	
	@SuppressWarnings("unchecked")
	public static List<RoomRecording> fetchRecording(String roomSID) {
		RoomDTO roomDTO = new RoomDTO();
		roomDTO.setRoomSID(roomSID);
		return (List<RoomRecording>) RoomConnectorService.INSTANCE.fetchRecording(roomDTO, RoomConnectorEnum.TWILIO_ROOM.name());
	}
}
