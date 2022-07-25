package org.videoroomotpphone.phone;

public interface IVoiceMessage<INPUT, OUTPUT> {
	OUTPUT otpSendByVoice(INPUT input);
}
