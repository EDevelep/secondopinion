package org.videoroomotpphone.room;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.videoroomotpphone.dto.RoomDTO;


public class RoomConnectorService {

	// Long userId, Long clientId,
	// BucketName
	// The

	private RoomConnectorService() {
		
	}
	
	public static final RoomConnectorService INSTANCE = new  RoomConnectorService();
	
	@SuppressWarnings("rawtypes")
	Map<String, IRoomVideo> roomConnectorMap = new HashMap<>();


	public Object createRoomByRoomType(RoomDTO roomDTO, String type) {

		return getRoomVideoService(type).createRoomByRoomType(roomDTO);
	}

	@SuppressWarnings("unchecked")
	private IRoomVideo<RoomDTO, String> getRoomVideoService(String type) {
		IRoomVideo<RoomDTO, String> roomVideo;

		if(type == null || type.isEmpty()) {
			throw new IllegalArgumentException("type can not be null.");
		}
		List<String> rcTypes = Arrays.stream(RoomConnectorEnum.values()).map(pge -> pge.name()).collect(Collectors.toList());
		if(!rcTypes.contains(type)) {
			throw new IllegalArgumentException("the type should be in " + rcTypes);
		}
		
		if (!roomConnectorMap.containsKey(type)) {
			roomVideo = RoomConnectorEnum.roomConnection(type);
			roomConnectorMap.put(type, roomVideo);
		} else {
			roomVideo = roomConnectorMap.get(type);
		}
		return roomVideo;
	}

	public Object retrieiveRoomStatusByRoomName(RoomDTO roomDTO, String type) {
		return getRoomVideoService(type).retrieiveRoomStatusByRoomName(roomDTO);
	}


	public Object retrieiveRoomStatusByRoomSID(RoomDTO roomDTO, String type) {
		return getRoomVideoService(type).retrieiveRoomStatusByRoomSID(roomDTO);
	}

	public Object retrieveAParticipant(RoomDTO roomDTO, String type) {
		return getRoomVideoService(type).retrieveAParticipant(roomDTO);
	}


	public Object removeAParticipant(RoomDTO roomDTO, String type) {
		return getRoomVideoService(type).removeAParticipant(roomDTO);
	}


	public Object participantsBasedOnStatus(RoomDTO roomDTO, String type) {
		return getRoomVideoService(type).participantsBasedOnStatus(roomDTO);
	}
	
	public Object updateTheRoomStatus(RoomDTO roomDTO, String type) {
		return  getRoomVideoService(type).updateTheRoomStatus(roomDTO);
	}

	public Object retrieveRoomParticipants(RoomDTO roomDTO, String type) {
		return getRoomVideoService(type).participantsBasedOnStatus(roomDTO);
	}
	
	public Object voiceChat(RoomDTO roomDTO, String type) throws URISyntaxException {
		return getRoomVideoService(type).voiceChat(roomDTO);
	}
	
	public Object fetchRecording(RoomDTO roomDTO, String type) {
		return getRoomVideoService(type).fetchRecording(roomDTO);
	}

}
