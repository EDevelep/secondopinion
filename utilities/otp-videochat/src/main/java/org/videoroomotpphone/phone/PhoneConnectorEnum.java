package org.videoroomotpphone.phone;


import org.videoroomotpphone.dto.OtpDTO;

public enum PhoneConnectorEnum {
	TWILIO_PHONE(new PhoneConnector());
	
	@SuppressWarnings("rawtypes")
	private final IPhoneConnector phoneConnector;
	
	@SuppressWarnings("rawtypes")
	private PhoneConnectorEnum(IPhoneConnector otpTwilioConnector) {
		this.phoneConnector = otpTwilioConnector;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static ITextMessage otpAsTextMessage(String type, OtpDTO otpDTO) {
		PhoneConnectorEnum connector = PhoneConnectorEnum.valueOf(type);
		return connector.phoneConnector.otpServiceAsTextMessage(otpDTO);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static IVoiceMessage otpAsVoiceMesage(String type, OtpDTO otpDTO) {
		PhoneConnectorEnum connector = PhoneConnectorEnum.valueOf(type);
		return connector.phoneConnector.otpServiceAsVoiceMessage(otpDTO);
	}
	
}
