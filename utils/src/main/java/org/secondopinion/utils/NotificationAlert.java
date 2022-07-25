package org.secondopinion.utils;

import java.util.Date;

public class NotificationAlert {

	private Long userPrimaryKey;//diagnosticCenterAddressID
	private Long objectKey;
	private String objectName;
	private String message;
	
	
	private Date date;
	private Long appointmentid;
	public NotificationAlert(Long userPrimaryKey, Long objectKey, String objectName, String message, Date date,
			Long appointmentid, Double amount) {
		super();
		this.userPrimaryKey = userPrimaryKey;
		this.objectKey = objectKey;
		this.objectName = objectName;
		this.message = message;
		this.date = date;
		this.appointmentid = appointmentid;
		this.amount = amount;
	}

	private Double amount;
	
	public NotificationAlert() {
	}
	
	public NotificationAlert(Long userPrimaryKey, Long objectKey, String objectName, String message) {
		this.userPrimaryKey = userPrimaryKey;
		this.objectKey = objectKey;
		this.objectName = objectName;
		this.message = message;
	}
	
	public Long getUserPrimaryKey() {
		return userPrimaryKey;
	}

	public void setUserPrimaryKey(Long userPrimaryKey) {
		this.userPrimaryKey = userPrimaryKey;
	}

	public Long getObjectKey() {
		return objectKey;
	}
	public void setObjectKey(Long objectKey) {
		this.objectKey = objectKey;
	}
	public String getMessage() {
		return message;
	}
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getAppointmentid() {
		return appointmentid;
	}

	public void setAppointmentid(Long appotmentid) {
		this.appointmentid = appotmentid;
	}

	
}
