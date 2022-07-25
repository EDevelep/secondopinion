package org.videoroomotpphone.dto;

import org.videoroomotpphone.ServiceTypeEnum;

public class OtpDTO {

	private String toKey;
	private String messageBody;
	private String callbackURI;
	private ServiceTypeEnum serviceTypeEnum;
	
	public String getToKey() {
		return toKey;
	}
	public void setToKey(String toKey) {
		this.toKey = toKey;
	}
	public String getMessageBody() {
		return messageBody;
	}
	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}
	public String getCallbackURI() {
		return callbackURI;
	}
	public void setCallbackURI(String callbackURI) {
		this.callbackURI = callbackURI;
	}
	public ServiceTypeEnum getServiceTypeEnum() {
		return serviceTypeEnum;
	}
	public void setServiceTypeEnum(ServiceTypeEnum serviceTypeEnum) {
		this.serviceTypeEnum = serviceTypeEnum;
	}
	
	
	
}
