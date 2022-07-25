package org.secondopinion.request;

import org.secondopinion.enums.Status;

public interface IRequestInfo {
	String getRequestId();
	Status getRequestStatus();
	String getRequestedBy();
	String getAppId();
	String getRequestMsg();
}
