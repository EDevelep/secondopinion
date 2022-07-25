package org.secondopinion.utils;

import java.util.Objects;

import org.videoroomotpphone.ServiceTypeEnum;
import org.videoroomotpphone.dto.OtpDTO;
import org.videoroomotpphone.otp.OTPConnectorService;
import org.videoroomotpphone.otp.OtpService;
import org.videoroomotpphone.phone.PhoneConnectorEnum;

public class OtpUtil {

	public static OtpToPhoneDTO buildOtpToPhoneDTO(String cellNumber, String actionName) {
		OtpToPhoneDTO otpToPhoneDTO = new OtpToPhoneDTO();
		otpToPhoneDTO.setPhoneNumber(cellNumber);
		otpToPhoneDTO.setActionName(actionName);
		otpToPhoneDTO.setAsTextMessage(true);
		otpToPhoneDTO.setPhoneConnector(PhoneConnectorEnum.TWILIO_PHONE);
		otpToPhoneDTO.setServiceTypeEnum(ServiceTypeEnum.TWILIO);
		otpToPhoneDTO.setWebsiteName("CureMetric");
		otpToPhoneDTO.setCallbackURI("http://localhost:4200");
		return otpToPhoneDTO;
	}

	public static Integer otpToPhone(String cellNumber, String actionName) {
		OtpToPhoneDTO otpToPhoneDTO = buildOtpToPhoneDTO(cellNumber,  actionName) ;
		if(Objects.isNull(otpToPhoneDTO.getPhoneNumber() )) {
			throw new IllegalArgumentException("Phone number is required");
		}
		OtpService otpService = OtpService.getInstance();
		Integer otp = otpService.generateOTP(otpToPhoneDTO.getPhoneNumber());

		OtpDTO otpDTO = new OtpDTO();
		otpDTO.setServiceTypeEnum(otpToPhoneDTO.getServiceTypeEnum());
		otpDTO.setToKey(otpToPhoneDTO.getPhoneNumber());
		otpDTO.setCallbackURI(otpToPhoneDTO.getCallbackURI());

		String messageBody = null;
		if(otpToPhoneDTO.getAsTextMessage() != null && otpToPhoneDTO.getAsTextMessage()) {
			messageBody = otpService.buildNewOTPTextMessageForPhone(otp, otpToPhoneDTO.getActionName(),  otpToPhoneDTO.getWebsiteName());
		} else if (otpToPhoneDTO.getAsVoiceMessage() != null && otpToPhoneDTO.getAsVoiceMessage()) {
			messageBody = otpService.buildNewOTPVoiceMessageForPhone(otp, otpToPhoneDTO.getActionName(),  otpToPhoneDTO.getWebsiteName());
		}
		otpDTO.setMessageBody(messageBody);
		//String result = OTPConnectorService.getInstance().sendTextMessage(otpDTO, otpToPhoneDTO.getPhoneConnector().name());
		return otp;
	}
}
