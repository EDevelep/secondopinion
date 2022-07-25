package org.videoroomotpphone.otp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.videoroomotpphone.dto.OtpDTO;
import org.videoroomotpphone.phone.ITextMessage;
import org.videoroomotpphone.phone.IVoiceMessage;
import org.videoroomotpphone.phone.PhoneConnectorEnum;

public class OTPConnectorService {

	// Long userId, Long clientId,
	// BucketName
	// The
	
	private OTPConnectorService() {
		
	}
	private static OTPConnectorService instance;
	
	public static OTPConnectorService getInstance() {
		if(instance == null) {
			instance = new OTPConnectorService();
		}
		
		return instance;
	}
	
	@SuppressWarnings("rawtypes")
	Map<String, ITextMessage> textMsgMap = new HashMap<>();
	@SuppressWarnings("rawtypes")
	Map<String, IVoiceMessage> voiceMsMap = new HashMap<>();

	@SuppressWarnings("unchecked")
	public String sendTextMessage(OtpDTO otpDTO, String type) {

		ITextMessage<OtpDTO, String> writer;

		if(type == null || type.isEmpty()) {
			throw new IllegalArgumentException("type can not be null.");
		}
		List<String> pcTypes = Arrays.stream(PhoneConnectorEnum.values()).map(pge -> pge.name()).collect(Collectors.toList());
		if(!pcTypes.contains(type)) {
			throw new IllegalArgumentException("the type should be in " + pcTypes);
		}
		
		if (!textMsgMap.containsKey(type)) {
			writer = PhoneConnectorEnum.otpAsTextMessage(type, otpDTO);
			textMsgMap.put(type, writer);
		} else {
			writer = textMsgMap.get(type);
		}

		return writer.otpSendByMessage(otpDTO);
	}

	@SuppressWarnings("unchecked")
	public String sendVoiceMessage(OtpDTO otpDTO, String type) {
		IVoiceMessage<OtpDTO, String> writer;

		if (!voiceMsMap.containsKey(type)) {
			writer = PhoneConnectorEnum.otpAsVoiceMesage(type, otpDTO);
			voiceMsMap.put(type, writer);
		} else {
			writer = voiceMsMap.get(type);
		}

		return writer.otpSendByVoice(otpDTO);
	}
}
