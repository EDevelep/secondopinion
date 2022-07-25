package org.videoroomotpphone.phone;


public interface IPhoneConnector<T extends ITextMessage<INPUT, OUTPUT>, Y extends IVoiceMessage<INPUT, OUTPUT>, INPUT, OUTPUT> {

	T otpServiceAsTextMessage(INPUT properties);
	Y otpServiceAsVoiceMessage(INPUT properties);
			
	
}
