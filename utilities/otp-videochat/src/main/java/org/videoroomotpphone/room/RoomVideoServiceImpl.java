package org.videoroomotpphone.room;


import java.net.URISyntaxException;
import java.util.List;

import org.videoroomotpphone.dto.RoomDTO;
import org.videoroomotpphone.twilio.TwilioUtil;

import com.twilio.rest.video.v1.Room.RoomStatus;
import com.twilio.rest.video.v1.room.RoomRecording;

public class RoomVideoServiceImpl implements IRoomVideo<RoomDTO, Object>{


	@Override
	public Object createRoomByRoomType(RoomDTO input) {
		switch (input.getServiceTypeEnum()) {
		case TWILIO:
			return TwilioUtil.INSTANCE.createRoomByRoomType(input);

		default:
		 throw new IllegalArgumentException("Invalid ServiceType.");
		}
	}

	@Override
	public Object updateTheRoomStatus(RoomDTO input) {
		switch (input.getServiceTypeEnum()) {
		case TWILIO:
			return TwilioUtil.INSTANCE.updateTheRoomStatus(input.getRoomSID(), input.getRoomStatus());

		default:
			throw new IllegalArgumentException("Invalid ServiceType.");
		}
	}

	@Override
	public Object retirieveInProgressRooms(RoomDTO input) {
		switch (input.getServiceTypeEnum()) {
		case TWILIO:
			return TwilioUtil.INSTANCE.retrieveListOfRoomsByStatus(RoomStatus.IN_PROGRESS);

		default:
			throw new IllegalArgumentException("Invalid ServiceType.");
		}
	}

	@Override
	public Object retirieveCompletedRooms(RoomDTO input) {
		switch (input.getServiceTypeEnum()) {
		case TWILIO:
			return TwilioUtil.INSTANCE.retrieveListOfRoomsByStatus(RoomStatus.COMPLETED);

		default:
			throw new IllegalArgumentException("Invalid ServiceType.");
		}
	}

	@Override
	public Object retirieveAllRooms(RoomDTO input) {
		switch (input.getServiceTypeEnum()) {
		case TWILIO:
			return TwilioUtil.INSTANCE.retrieveAllRooms();

		default:
			throw new IllegalArgumentException("Invalid ServiceType.");
		}
	}

	@Override
	public Object retrieiveRoomStatusByRoomName(RoomDTO input) {
		switch (input.getServiceTypeEnum()) {
		case TWILIO:
			return TwilioUtil.INSTANCE.retrieiveRoomStatusByRoomName(input.getRoomName());

		default:
			throw new IllegalArgumentException("Invalid ServiceType.");
		}
	}
	
	@Override
	public Object voiceChat(RoomDTO input) throws URISyntaxException {
		switch (input.getServiceTypeEnum()) {
		case TWILIO:
			return TwilioUtil.INSTANCE.voiceChat(input.getToNumber());

		default:
			throw new IllegalArgumentException("Invalid ServiceType.");
		}
	}
	
	@Override
	public List<RoomRecording> fetchRecording(RoomDTO input) {
		switch (input.getServiceTypeEnum()) {
		case TWILIO:
			return TwilioUtil.INSTANCE.fetchRecording(input.getRoomSID());

		default:
			throw new IllegalArgumentException("Invalid ServiceType.");
		}
	}
	
	@Override
	public Object tokenForProggrammableChat(RoomDTO input) {
		switch (input.getServiceTypeEnum()) {
		case TWILIO:
			return TwilioUtil.INSTANCE.tokenForProggrammableChat(input.getChatIdentity(), input.getRoomName());//https://www.twilio.com/docs/chat/tutorials/chat-application-java-servlets

		default:
			throw new IllegalArgumentException("Invalid ServiceType.");
		}
	}
	
	@Override
	public Object tokenForProgrammableVideo(RoomDTO input) {
		switch (input.getServiceTypeEnum()) {
		case TWILIO:
			return TwilioUtil.INSTANCE.toeknForProgrammableVideoChat(input.getChatIdentity(), input.getRoomName());//https://www.twilio.com/docs/chat/tutorials/chat-application-java-servlets

		default:
			throw new IllegalArgumentException("Invalid ServiceType.");
		}
	}

	@Override
	public Object retrieiveRoomStatusByRoomSID(RoomDTO input) {
		switch (input.getServiceTypeEnum()) {
		case TWILIO:
			return TwilioUtil.INSTANCE.retrieiveRoomStatusByRoomSID(input.getRoomSID());

		default:
			throw new IllegalArgumentException("Invalid ServiceType.");
		}
	}
	
	@Override
	public Object retrieveAParticipant(RoomDTO input) {
		switch (input.getServiceTypeEnum()) {
		case TWILIO:
			return TwilioUtil.INSTANCE.retrieveAParticipant(input.getRoomName(), input.getParticipantName());

		default:
			throw new IllegalArgumentException("Invalid ServiceType.");
		}
	}

	@Override
	public Object removeAParticipant(RoomDTO input) {
		switch (input.getServiceTypeEnum()) {
		case TWILIO:
			return TwilioUtil.INSTANCE.retrieveAParticipant(input.getRoomName(), input.getParticipantName());

		default:
			throw new IllegalArgumentException("Invalid ServiceType.");
		}
	}
	
	@Override
	public Object participantsBasedOnStatus(RoomDTO input) {
		switch (input.getServiceTypeEnum()) {
		case TWILIO:
			return TwilioUtil.INSTANCE.participantsBasedOnStatus(input.getRoomName(), input.getParticipantStatus());

		default:
			throw new IllegalArgumentException("Invalid ServiceType.");
		}
	}
}
