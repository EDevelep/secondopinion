package org.videoroomotpphone.twilio;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.videoroomotpphone.dto.RoomDTO;
import org.videoroomotpphone.dto.RoomTokenResponse;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.jwt.accesstoken.AccessToken;
import com.twilio.jwt.accesstoken.ChatGrant;
import com.twilio.jwt.accesstoken.VideoGrant;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.chat.v1.Service;
import com.twilio.rest.video.v1.Room;
import com.twilio.rest.video.v1.Room.RoomStatus;
import com.twilio.rest.video.v1.RoomCreator;
import com.twilio.rest.video.v1.RoomFetcher;
import com.twilio.type.PhoneNumber;
import com.twilio.rest.video.v1.room.Participant.Status;
import com.twilio.rest.video.v1.room.ParticipantReader;
import com.twilio.rest.video.v1.room.RoomRecording;
import com.twilio.rest.video.v1.room.Participant;

public class TwilioUtil {

	private static final Logger LOG = LoggerFactory.getLogger(TwilioUtil.class);

	public static final String ACCOUNT_SID = "AC2150e2bf9fbf83985949a176362d136e";
	//"ACfa7e714691a465f15ac51a83bfa40cd3";//
	public static final String AUTH_TOKEN = "81f0d602dc5c29e68bd630c9f51e861e";
	//"fcdb1bdf2b17076e4c352f0c018c0871";//
	private static final String TWILIO_PHONE_NUMBER = "+12017205859";
	
	private static final String TWILIO_API_KEY = "SK3856d1294eac9458512d2c0ed01bc03c";
	private static final String TWILIO_API_SECRET = "RVDtALZSVDM4KtQ0kjw5iutlgCPXrRsD";

	public static final TwilioUtil INSTANCE = new TwilioUtil();

	public TwilioUtil() {
		super();
	}

	public String sendMessage(String toNumber, String messageBody, String callbackUri) {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		Message message = Message.creator(
				new PhoneNumber(toNumber),
				new PhoneNumber(TWILIO_PHONE_NUMBER),
				messageBody)
				.create();
		String sid = message.getSid();
		LOG.info(sid);
		return sid;
	}

	public Call voiceCall(String toNumber, String messageBody, String callbackUri) {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		Call call = Call.creator(
				new PhoneNumber(toNumber),
				new PhoneNumber(TWILIO_PHONE_NUMBER),
				messageBody)
				.setStatusCallback(URI.create(callbackUri))
				.create();
		LOG.info(call.toString());
		return call;
	}

	public Room retrieiveRoomStatusByRoomName(String roomName) {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		RoomFetcher roomFetcher = Room.fetcher(roomName);
		if(Objects.isNull(roomFetcher )) return null;

		Room room = roomFetcher.fetch();
		LOG.info(room.toString());
		return room;
	}

	public Room retrieiveRoomStatusByRoomSID(String roomSID) {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		RoomFetcher roomFetcher = Room.fetcher(roomSID);
		if(Objects.isNull(roomFetcher )) return null;

		Room room = roomFetcher.fetch();
		LOG.info(room.toString());
		return room;
	}
	public Room updateTheRoomStatus(String roomSID, RoomStatus roomStatus) {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		Room room = Room.updater(roomSID,  roomStatus).update();

		LOG.info(room.toString());
		return room;
	}

	public RoomTokenResponse createRoomByRoomType(RoomDTO input) {
		
		RoomTokenResponse roomTokenResponse = new RoomTokenResponse();
		
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		RoomCreator roomCreator = Room.creator()
				.setUniqueName(input.getRoomName())//room-16
				.setStatusCallback(URI.create(input.getCallBackURI()))//status call back url for frontend
				.setEnableTurn(input.isEnableTurn());//enabling room record
		switch (input.getRoomType()) {
		case PEER_TO_PEER:

			roomCreator.setType(Room.RoomType.PEER_TO_PEER);
			break;
		case GROUP:

			roomCreator.setType(Room.RoomType.GROUP);
			roomCreator.setRecordParticipantsOnConnect(input.isRecordEnabled());//Whether to start recording when Participants connect
			break;
		case GROUP_SMALL:

			roomCreator.setType(Room.RoomType.GROUP_SMALL);
			roomCreator.setRecordParticipantsOnConnect(input.isRecordEnabled());//Whether to start recording when Participants connect
			break;
		default:
			new IllegalArgumentException("Invalid Room Type");

		}

		ResourceSet<Room> rooms = Room.reader().setUniqueName(input.getRoomName()).limit(20).read();
		Room room = null;
		for(Room record : rooms) {
			room = record;
		}
		if(Objects.isNull(room)) {
			room= roomCreator.create();
		}
		
		String token = null;
		switch (input.getChatType()) {
		case TEXT_CHAT:
			token = tokenForProggrammableChat(input.getChatIdentity(), room.getUniqueName());
			break;
		case VIDEO_CHAT:
			token = toeknForProgrammableVideoChat(input.getChatIdentity(), room.getUniqueName());
			break;
		default:
			break;
		}
		roomTokenResponse.setRoom(room);
		roomTokenResponse.setTokenForChat(token);
		return roomTokenResponse;
	}

	/*private <T, R> R printLog(T t, Class<R> clazz) {

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ssSSS").create();
		String jsonBody = gson.toJson(t);
		LOG.info(jsonBody);
		return jsonBody;
	}*/

	public Collection<Room> retrieveAllRooms() {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		List<Room> listOfRooms = new ArrayList<>();
		ResourceSet<Room> rooms = Room.reader()
				//.setUniqueName(roomName).limit(20)
				.read();
		rooms.forEach(room -> listOfRooms.add(room));
		return listOfRooms;
	}

	public Collection<Room> retrieveListOfRoomsByStatus(RoomStatus roomStatus) {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		List<Room> listOfRooms = new ArrayList<>();

		ResourceSet<Room> rooms = Room.reader()
				.setStatus(roomStatus).limit(20).read();
		rooms.forEach(room -> listOfRooms.add(room));
		return listOfRooms;
	}

	//create a token for programmable text chat
	public String tokenForProggrammableChat(String identity, String roomName) {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
	    Service service = Service.creator(roomName).create();
		
	    ChatGrant grant = new ChatGrant();
		grant.setServiceSid(service.getSid());//appConfig.getTwilioChatServiceSID()

		AccessToken accessToken = new AccessToken.Builder(
				ACCOUNT_SID,//appConfig.getTwilioAccountSID()
				TWILIO_API_KEY,//appConfig.getTwilioAPIKey(),
				TWILIO_API_SECRET// appConfig.getTwilioAPISecret()
				).identity(identity).grant(grant).build();
		 String token = accessToken.toJwt();
		 LOG.info(String.format("identity for videochat %s and token %" , identity, token));
		 return token;
	}
	
	//create a token for video chat
	public String toeknForProgrammableVideoChat(String identity/*vishnu-19*/, String roomName/*room-16*/) {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		// Required for all types of tokens

	    // Create Video grant
	    VideoGrant grant = new VideoGrant();
	    grant.setRoom(roomName);

	    // Create access token
	    AccessToken accessToken = new AccessToken.Builder(
	      ACCOUNT_SID,
	      TWILIO_API_KEY,
	      TWILIO_API_SECRET// we have to create it in the twilio console
	    ).identity(identity).grant(grant).build();
	    String token = accessToken.toJwt();
	    LOG.info(String.format("identity for videochat %s and token %s" , identity, token));
	    return token;
	}
	
	//voice chat
	public Call voiceChat(String toNumber) throws URISyntaxException {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);


	    Call call = Call.creator(new PhoneNumber(toNumber), new PhoneNumber(TWILIO_PHONE_NUMBER),
	            new URI("http://demo.twilio.com/docs/voice.xml")).create();//need to chaneg the URI
	    return call;
	}
	
	//get all room records by room sid
	public List<RoomRecording> fetchRecording(String roomSID) {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		List<RoomRecording> roomRecordings = new ArrayList<>();
		ResourceSet<RoomRecording> recordings =
	            RoomRecording.reader(roomSID)
	            .limit(20)
	            .read();

	        for(RoomRecording record : recordings) {
	        	roomRecordings.add(record);
	        }
        return roomRecordings;
	}
	
	public Participant retrieveAParticipant(String uniqueName, String participantName) {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Participant participant = Participant.fetcher(
        		uniqueName, participantName).fetch();
        if(participant != null)
        	LOG.info(participant.toString());
       return participant;
	}
	
	public Participant removeAParticipant(String uniqueName, String participantName) {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

		Participant participant = Participant
            .updater(uniqueName, participantName)
            .setStatus(Participant.Status.DISCONNECTED)
            .update();
		return participant;
	}
	
	public List<Participant> participantsBasedOnStatus(String uniqueName, Status status) {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

		ResourceSet<Participant> participants = null;
        ParticipantReader participantReader= Participant.reader(uniqueName);
        if(Objects.nonNull(status)) {
        	participantReader .setStatus(status);
        }
        if(Objects.nonNull(participantReader)) {
        	participants = participantReader.read();
        }
       
        List<Participant> paricipantList = new ArrayList<>();
        if(Objects.nonNull(participants)) {
	        for (Participant participant : participants) {
	        	paricipantList.add(participant);
	        }
        }
        return paricipantList;
	}

}
