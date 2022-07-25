package org.videoroomotpphone.room;


import java.net.URISyntaxException;
import org.videoroomotpphone.dto.RoomDTO;


public interface IRoomVideo<INPUT, OUTPUT> {

	OUTPUT createRoomByRoomType(INPUT intput);
	
	public OUTPUT retirieveInProgressRooms(INPUT intput);
	public OUTPUT retirieveCompletedRooms(INPUT intput);
	public OUTPUT retirieveAllRooms(RoomDTO input);
	public OUTPUT retrieiveRoomStatusByRoomName(INPUT intput);
	public OUTPUT retrieiveRoomStatusByRoomSID(INPUT input);

	public OUTPUT tokenForProggrammableChat(RoomDTO input);

	public OUTPUT tokenForProgrammableVideo(RoomDTO input);

	public OUTPUT updateTheRoomStatus(RoomDTO input);

	public OUTPUT retrieveAParticipant(RoomDTO input);

	public OUTPUT removeAParticipant(RoomDTO input);

	public OUTPUT participantsBasedOnStatus(RoomDTO input);

	public OUTPUT voiceChat(RoomDTO input) throws URISyntaxException;
	public OUTPUT fetchRecording(RoomDTO input);
}
