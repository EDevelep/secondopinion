package org.videoroomotpphone.twilio;

import org.videoroomotpphone.ServiceTypeEnum;
import org.videoroomotpphone.dto.OtpDTO;
import org.videoroomotpphone.otp.OTPConnectorService;
import org.videoroomotpphone.otp.OtpService;
import org.videoroomotpphone.phone.PhoneConnectorEnum;

public class TestOtpSend {
	


	@org.junit.Test
	public  void testSampleMessage() {
    	
    	String phoneNumber = "+919051406065";//"+919985747723";//"+919051406065";//"+18628129894";
    	OtpService otpService = OtpService.getInstance();
    	Integer otp = otpService.generateOTP(phoneNumber);
    	
    	OtpDTO otpDTO = new OtpDTO();
    	otpDTO.setServiceTypeEnum(ServiceTypeEnum.TWILIO);
    	otpDTO.setToKey(phoneNumber);
    	otpDTO.setCallbackURI("http://localhost:4200");
    	
    	String messageBody = otpService.buildNewOTPTextMessageForPhone(otp, "Phone Number Verification",  "CureMetric");
    	otpDTO.setMessageBody(messageBody);
    	OTPConnectorService.getInstance().sendTextMessage(otpDTO, PhoneConnectorEnum.TWILIO_PHONE.name());
    	
	}
}
