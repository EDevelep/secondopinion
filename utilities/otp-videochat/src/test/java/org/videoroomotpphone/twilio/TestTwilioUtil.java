package org.videoroomotpphone.twilio;

import static org.junit.Assert.assertNotNull;

import org.videoroomotpphone.ServiceTypeEnum;
import org.videoroomotpphone.dto.RoomDTO;
import org.videoroomotpphone.dto.RoomTokenResponse;
import org.videoroomotpphone.dto.RoomDTO.ChatType;
import org.videoroomotpphone.room.RoomConnectorEnum;
import org.videoroomotpphone.room.RoomConnectorService;

import com.twilio.rest.video.v1.Room.RoomType;

public class TestTwilioUtil {

	@org.junit.Test
	public void joinRoom() {
		RoomDTO roomDTO = new RoomDTO();
		roomDTO.setRoomName(String.valueOf(19));
		roomDTO.setChatIdentity("vishnu-19");
		roomDTO.setRoomType(RoomType.GROUP_SMALL);
		roomDTO.setRecordEnabled(true);
		roomDTO.setCallBackURI("http://localhost:4200");
		roomDTO.setServiceTypeEnum(ServiceTypeEnum.TWILIO);
		roomDTO.setChatType(ChatType.VIDEO_CHAT);
		RoomTokenResponse rtr = (RoomTokenResponse) RoomConnectorService.INSTANCE.createRoomByRoomType(roomDTO, RoomConnectorEnum.TWILIO_ROOM.name());
		assertNotNull(rtr);
		assertNotNull(rtr.getRoom());
		assertNotNull(rtr.getTokenForChat());
	}
}
