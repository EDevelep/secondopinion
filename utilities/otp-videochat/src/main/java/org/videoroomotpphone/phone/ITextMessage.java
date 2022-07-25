package org.videoroomotpphone.phone;

public interface ITextMessage<INPUT, OUTPUT> {
	OUTPUT otpSendByMessage(INPUT input);
	
}
