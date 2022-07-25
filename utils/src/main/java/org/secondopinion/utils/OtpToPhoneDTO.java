package org.secondopinion.utils;

import org.videoroomotpphone.ServiceTypeEnum;
import org.videoroomotpphone.phone.PhoneConnectorEnum;

public class OtpToPhoneDTO {

	
	private String phoneNumber;
	private Boolean asTextMessage;
	private Boolean asVoiceMessage;
	private String actionName;
	private String websiteName;
	private ServiceTypeEnum serviceTypeEnum;
	private String callbackURI;
	private PhoneConnectorEnum phoneConnector;
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Boolean getAsTextMessage() {
		return asTextMessage;
	}
	public void setAsTextMessage(Boolean asTextMessage) {
		this.asTextMessage = asTextMessage;
	}
	public Boolean getAsVoiceMessage() {
		return asVoiceMessage;
	}
	public void setAsVoiceMessage(Boolean asVoiceMessage) {
		this.asVoiceMessage = asVoiceMessage;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getWebsiteName() {
		return websiteName;
	}
	public void setWebsiteName(String websiteName) {
		this.websiteName = websiteName;
	}
	public ServiceTypeEnum getServiceTypeEnum() {
		return serviceTypeEnum;
	}
	public void setServiceTypeEnum(ServiceTypeEnum serviceTypeEnum) {
		this.serviceTypeEnum = serviceTypeEnum;
	}
	public String getCallbackURI() {
		return callbackURI;
	}
	public void setCallbackURI(String callbackURI) {
		this.callbackURI = callbackURI;
	}
	public PhoneConnectorEnum getPhoneConnector() {
		return phoneConnector;
	}
	public void setPhoneConnector(PhoneConnectorEnum phoneConnector) {
		this.phoneConnector = phoneConnector;
	}
	
	
}
