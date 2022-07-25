package org.videoroomotpphone.phone;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.videoroomotpphone.dto.OtpDTO;
import org.videoroomotpphone.twilio.TwilioUtil;


public class PhoneTextMessage implements ITextMessage<OtpDTO, Object>{

	private static final Logger LOG = LoggerFactory.getLogger(PhoneTextMessage.class);
	
	@Override
	public String otpSendByMessage(OtpDTO otpDTO) {
		String toKey = otpDTO.getToKey();
		String messageBody = otpDTO.getMessageBody();
		String callbackUri=otpDTO.getCallbackURI();
		
		switch (otpDTO.getServiceTypeEnum()) {
		case TWILIO:
			
			String response= TwilioUtil.INSTANCE.sendMessage(toKey, messageBody, callbackUri);
			LOG.info("OTP sent successfully to :  {}", toKey);
			return response;
		default:
			throw new IllegalArgumentException("Service Type not found");
		}
		
		
		
	}

	

}
