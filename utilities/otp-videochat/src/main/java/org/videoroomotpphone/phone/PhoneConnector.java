package org.videoroomotpphone.phone;

import org.videoroomotpphone.dto.OtpDTO;


public class PhoneConnector implements IPhoneConnector<PhoneTextMessage, PhoneVoiceMessage, OtpDTO, Object> {

	@Override
	public PhoneTextMessage otpServiceAsTextMessage(OtpDTO otpDTO) {
		return new PhoneTextMessage();
	}

	@Override
	public PhoneVoiceMessage otpServiceAsVoiceMessage(OtpDTO otpDTO) {
		return new PhoneVoiceMessage();
	}

}
