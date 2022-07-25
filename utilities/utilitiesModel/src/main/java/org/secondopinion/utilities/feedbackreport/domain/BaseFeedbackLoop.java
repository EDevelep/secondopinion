package org.secondopinion.utilities.feedbackreport.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.utilities.feedbackreport.dto.FeedbackLoop;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal;

@MappedSuperclass
public abstract class BaseFeedbackLoop extends BaseDomainObject<Long> {

	public static final String FIELD_feedbackLoopId = "feedbackLoopId";
	public static final String FIELD_clientId = "clientId";
	public static final String FIELD_userId = "userId";
	public static final String FIELD_feedBackType = "feedBackType";
	public static final String FIELD_status = "status";
	public static final String FIELD_description = "description";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";
	public static final String FIELD_userAgent = "userAgent";
	public static final String FIELD_os = "os";
	public static final String FIELD_browser = "browser";
	public static final String FIELD_ipAddress = "ipAddress";
	public static final String FIELD_primaryContact = "primaryContact";
	public static final String FIELD_device = "device";
	public static final String FIELD_os_version = "os_version";
	public static final String FIELD_browser_version = "browser_version";
	public static final String FIELD_latitude = "latitude";
	public static final String FIELD_longitudes = "longitudes";
	public static final String FIELD_timestamp = "timestamp";
	public static final String FIELD_token = "token";
	public static final String FIELD_feedbackTime = "feedbackTime";
	public static final String FIELD_patientName = "patientName";

	private Long feedbackLoopId;
	private Long clientId;
	private Long userId;
	private String feedBackType;
	private String status;
	private String description;
	private String userAgent;
	private String os;
	private String browser;
	private String device;
	private String os_version;
	private String browser_version;
	private double latitude;
	private double longitudes;
	private Long timestamp;
	private String token;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", shape = Shape.STRING)
	private Date feedbackTime;
	private String ipAddress;
	private String primaryContact;
	private String patientName;

	public void setFeedbackLoopId(Long _feedbackLoopId) {
		this.feedbackLoopId = _feedbackLoopId;
	}

	@Id
	@Column(name = "feedbackLoopId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getFeedbackLoopId() {
		return this.feedbackLoopId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	@NotNull
	@Column(name = "clientId")
	public Long getClientId() {
		return this.clientId;
	}

	/**
	 * @return the userAgent
	 */
	@NotNull
	@Column(name = "userAgent")
	public String getUserAgent() {
		return userAgent;
	}

	/**
	 * @param userAgent the userAgent to set
	 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	/**
	 * @return the os
	 */
	@NotNull
	@Column(name = "os")
	public String getOs() {
		return os;
	}

	/**
	 * @param os the os to set
	 */
	public void setOs(String os) {
		this.os = os;
	}

	/**
	 * @return the browser
	 */
	@NotNull
	@Column(name = "browser")
	public String getBrowser() {
		return browser;
	}

	/**
	 * @param browser the browser to set
	 */
	public void setBrowser(String browser) {
		this.browser = browser;
	}

	/**
	 * @return the device
	 */

	@Column(name = "device")
	public String getDevice() {
		return device;
	}

	/**
	 * @param device the device to set
	 */
	public void setDevice(String device) {
		this.device = device;
	}

	/**
	 * @return the os_version
	 */
	@NotNull
	@Column(name = "os_version")
	public String getOs_version() {
		return os_version;
	}

	/**
	 * @param os_version the os_version to set
	 */
	public void setOs_version(String os_version) {
		this.os_version = os_version;
	}

	/**
	 * @return the browser_version
	 */
	@NotNull
	@Column(name = "browser_version")
	public String getBrowser_version() {
		return browser_version;
	}

	/**
	 * @param browser_version the browser_version to set
	 */
	public void setBrowser_version(String browser_version) {
		this.browser_version = browser_version;
	}

	/**
	 * @return the latitude
	 */
	@NotNull
	@Column(name = "latitude")
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitudes
	 */
	@NotNull
	@Column(name = "longitudes")
	public double getLongitudes() {
		return longitudes;
	}

	/**
	 * @param longitudes the longitudes to set
	 */
	public void setLongitudes(double longitudes) {
		this.longitudes = longitudes;
	}

	/**
	 * @return the timestamp
	 */

	@Column(name = "timestamp")
	public Long getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the token
	 */

	@Length(max = 500)
	@Column(name = "token", length = 500)
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the feedbackTime
	 */
	@NotNull
	@Column(name = "feedbackTime")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", shape = Shape.STRING)
	public Date getFeedbackTime() {
		return feedbackTime;
	}

	/**
	 * @param feedbackTime the feedbackTime to set
	 */
	public void setFeedbackTime(Date feedbackTime) {
		this.feedbackTime = feedbackTime;
	}

	public void setUserId(Long _userId) {
		this.userId = _userId;
	}

	@NotNull
	@Column(name = "userId")
	public Long getUserId() {
		return this.userId;
	}

	public void setFeedBackType(String _feedBackType) {
		this.feedBackType = _feedBackType;
	}

	@NotNull
	@Length(max = 45)
	@Column(name = "feedBackType")
	public String getFeedBackType() {
		return this.feedBackType;
	}

	public void setStatus(String _status) {
		this.status = _status;
	}

	@NotNull
	@Length(max = 45)
	@Column(name = "status")
	public String getStatus() {
		return this.status;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@NotNull
	@Length(max = 500)
	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	/**
	 * @return the ipAddress
	 */
	@Column(name = "ipAddress")
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * @param ipAddress the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * @return the primaryContact
	 */
	@Column(name = "primaryContact")
	public String getPrimaryContact() {
		return primaryContact;
	}

	/**
	 * @param primaryContact the primaryContact to set
	 */
	public void setPrimaryContact(String primaryContact) {
		this.primaryContact = primaryContact;
	}

	@Column(name = "patientName")
	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BaseFeedbackLoop other = (BaseFeedbackLoop) o;
		if (ObjectUtil.isEqual(getClientId(), other.getClientId()) && ObjectUtil.isEqual(getUserId(), other.getUserId())
				&& ObjectUtil.isEqual(getFeedBackType(), other.getFeedBackType())
				&& ObjectUtil.isEqual(getStatus(), other.getStatus())
				&& ObjectUtil.isEqual(getDescription(), other.getDescription())
				&& ObjectUtil.isEqual(getUserAgent(), other.getUserAgent())
				&& ObjectUtil.isEqual(getOs(), other.getOs()) && ObjectUtil.isEqual(getBrowser(), other.getBrowser())
				&& ObjectUtil.isEqual(getDevice(), other.getDevice())
				&& ObjectUtil.isEqual(getOs_version(), other.getOs_version())
				&& ObjectUtil.isEqual(getBrowser_version(), other.getBrowser_version())
				&& ObjectUtil.isEqual(getLatitude(), other.getLatitude())
				&& ObjectUtil.isEqual(getLongitudes(), other.getLongitudes())
				&& ObjectUtil.isEqual(getTimestamp(), other.getTimestamp())
				&& ObjectUtil.isEqual(getToken(), other.getToken())
				&& ObjectUtil.isEqual(getFeedbackTime(), other.getFeedbackTime())
				&& ObjectUtil.isEqual(getIpAddress(), other.getIpAddress())
				&& ObjectUtil.isEqual(getPrimaryContact(), other.getPrimaryContact())
				&& ObjectUtil.isEqual(getPatientName(), other.getPatientName())
				&& ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy())
				&& ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 0;
		result = result + (feedbackLoopId != null ? feedbackLoopId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk) {
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if (validatePk) {
			if (this.feedbackLoopId == null) {
				list.add(new ValidationMessage("Field " + FIELD_feedbackLoopId + " cannot be null"));
			}

		}
		if (this.clientId == null) {
			list.add(new ValidationMessage("Field " + FIELD_clientId + " cannot be null"));
		}

		if (this.userId == null) {
			list.add(new ValidationMessage("Field " + FIELD_userId + " cannot be null"));
		}

		if (StringUtil.isNullOrEmpty(this.feedBackType)) {
			list.add(new ValidationMessage("Field " + FIELD_feedBackType + " cannot be null"));
		}

		if ((this.feedBackType != null) && (this.feedBackType.length() > 45)) {
			list.add(new ValidationMessage("Field " + FIELD_feedBackType + " cannot be longer than: " + 45));
		}

		if (this.status == null) {
			list.add(new ValidationMessage("Field " + FIELD_status + " cannot be null"));
		}

		if (StringUtil.isNullOrEmpty(this.description)) {
			list.add(new ValidationMessage("Field " + FIELD_description + " cannot be null"));
		}

		if ((this.description != null) && (this.description.length() > 500)) {
			list.add(new ValidationMessage("Field " + FIELD_description + " cannot be longer than: " + 500));
		}

		if (StringUtil.isNullOrEmpty(this.userAgent)) {
			list.add(new ValidationMessage("Field " + FIELD_userAgent + " cannot be null"));
		}

		if (StringUtil.isNullOrEmpty(this.os)) {
			list.add(new ValidationMessage("Field " + FIELD_os + " cannot be null"));
		}
		if (StringUtil.isNullOrEmpty(this.browser)) {
			list.add(new ValidationMessage("Field " + FIELD_browser + " cannot be null"));
		}

		if (StringUtil.isNullOrEmpty(this.browser_version)) {
			list.add(new ValidationMessage("Field " + FIELD_browser_version + " cannot be null"));
		}
		if (Objects.isNull(this.latitude)) {
			list.add(new ValidationMessage("Field " + FIELD_latitude + " cannot be null"));
		}
		if (Objects.isNull(this.longitudes)) {
			list.add(new ValidationMessage("Field " + FIELD_longitudes + " cannot be null"));
		}

		if (Objects.isNull(this.feedbackTime)) {
			list.add(new ValidationMessage("Field " + FIELD_feedbackTime + " cannot be null"));
		}

		if (this.isFromDB()) {
			if (StringUtil.isNullOrEmpty(this.lastUpdatedBy)) {
				list.add(new ValidationMessage("Field " + FIELD_lastUpdatedBy + " cannot be null"));
			}
		}
		if (this.isFromDB()) {
			if ((this.lastUpdatedBy != null) && (this.lastUpdatedBy.length() > 45)) {
				list.add(new ValidationMessage("Field " + FIELD_lastUpdatedBy + " cannot be longer than: " + 45));
			}
		}
		if (this.isFromDB()) {
			if (this.lastUpdatedTs == null) {
				list.add(new ValidationMessage("Field " + FIELD_lastUpdatedTs + " cannot be null"));
			}
		}
		if (list.size() > 0)
			setHasValidationErrors(true);

		setValidationMessages(list);

	}

	@Override
	@JsonIgnore
	public final void setAuditFields() {
		if (!isFromDB()) {
		}
		this.lastUpdatedBy = AppThreadLocal.getUserName();
		this.lastUpdatedTs = AppThreadLocal.getRequestStartDate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BaseFeedbackLoop [feedbackLoopId=" + feedbackLoopId + ", clientId=" + clientId + ", userId=" + userId
				+ ", feedBackType=" + feedBackType + ", status=" + status + ", description=" + description
				+ ", userAgent=" + userAgent + ", os=" + os + ", browser=" + browser + ", device=" + device
				+ ", os_version=" + os_version + ", browser_version=" + browser_version + ", latitude=" + latitude
				+ ", longitudes=" + longitudes + ", timestamp=" + timestamp + ", token=" + token + ", feedbackTime="
				+ feedbackTime + ", ipAddress=" + ipAddress + ", primaryContact=" + primaryContact + "]";
	}

	@Transient
	@Override
	public final boolean isKeyNull() {
		return (feedbackLoopId == null);
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField() {
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_feedbackLoopId, getFeedbackLoopId()));
		return list;
	}

	@Transient
	@Override
	public Long getId() {
		return getFeedbackLoopId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		FeedbackLoop feedbackLoop = new FeedbackLoop();
		feedbackLoop.setFromDB(false);
		feedbackLoop.setClientId(getClientId());
		feedbackLoop.setUserId(getUserId());
		feedbackLoop.setFeedBackType(getFeedBackType());
		feedbackLoop.setStatus(getStatus());
		feedbackLoop.setDescription(getDescription());
		feedbackLoop.setUserAgent(getUserAgent());
		feedbackLoop.setOs(getOs());
		feedbackLoop.setBrowser(getBrowser());
		feedbackLoop.setDevice(getDevice());
		feedbackLoop.setOs_version(getOs_version());
		feedbackLoop.setBrowser_version(getBrowser_version());
		feedbackLoop.setLatitude(getLatitude());
		feedbackLoop.setLongitudes(getLongitudes());
		feedbackLoop.setTimestamp(getTimestamp());
		feedbackLoop.setToken(getToken());
		feedbackLoop.setFeedbackTime(getFeedbackTime());
		feedbackLoop.setIpAddress(getIpAddress());
		feedbackLoop.setPrimaryContact(getPrimaryContact());
		feedbackLoop.setPatientName(getPatientName());
		return feedbackLoop;
	}
}