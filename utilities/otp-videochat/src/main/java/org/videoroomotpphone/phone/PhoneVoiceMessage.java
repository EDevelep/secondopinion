package org.videoroomotpphone.phone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.videoroomotpphone.dto.OtpDTO;
import org.videoroomotpphone.twilio.TwilioUtil;

import com.twilio.rest.api.v2010.account.Call;

public class PhoneVoiceMessage implements IVoiceMessage<OtpDTO, Object>{

	private static final Logger LOG = LoggerFactory.getLogger(PhoneVoiceMessage.class);
	
	@Override
	public Call otpSendByVoice(OtpDTO otpDTO) {
		String toKey = otpDTO.getToKey();
		String messageBody = otpDTO.getMessageBody();
		String callbackUri=otpDTO.getCallbackURI();
		
		switch (otpDTO.getServiceTypeEnum()) {
		case TWILIO:
			Call call= TwilioUtil.INSTANCE.voiceCall(toKey, messageBody, callbackUri);
			LOG.info("OTP sent successfully to :  {}", toKey);
			return call;
		default:
			throw new IllegalArgumentException("Service Type not found");
		}
	}
}
