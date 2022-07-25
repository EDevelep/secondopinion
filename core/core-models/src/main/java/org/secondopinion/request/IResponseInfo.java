package org.secondopinion.request;

import org.secondopinion.enums.Status;

public interface IResponseInfo {
	Status getStatus();
	String getStatsMsg();
}
