package org.secondopinion.userMgmt.domain; 

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.secondopinion.userMgmt.dto.UserProfilePic;

import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal; 
@MappedSuperclass 
public abstract class BaseUserProfilePic extends BaseDomainObject<Integer> {

	public static final String FIELD_userRoleId = "userProfilePicId";
	public static final String FIELD_userId = "userId";
	public static final String FIELD_profilePic = "profilePic";
	public static final String FIELD_fileName = "fileName";
	public static final String FIELD_contentType = "contentType";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Integer userProfilePicId;
	private String userId;
	private byte[] profilePic;
	private String fileName;
	private String contentType;
	
	public void setUserProfilePicId( Integer  userProfilePicId){
		this.userProfilePicId = userProfilePicId;
	}
	
	@Id
	@Column(name = "userProfilePicId") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getUserProfilePicId(){
		 return this.userProfilePicId;
	}
	
	public void setUserId( String  _userId){
		this.userId = _userId;
	}
	
	@Length(max=45)
	@Column (name="userId")
	public String getUserId(){
		 return this.userId;
	}
	
	public void setProfilePic( byte[] profilePic){
		this.profilePic = profilePic;
	}
	
	@Column (name="profilePic", columnDefinition="MEDIUMBLOB")
	public byte[] getProfilePic(){
		 return this.profilePic;
	}

	/**
	 * @return the fileName
	 */
	@Column (name="fileName")
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the contentType
	 */
	@Column (name="contentType")
	public String getContentType() {
		return contentType;
	}

	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseUserProfilePic other = (BaseUserProfilePic)o;
		if(
			ObjectUtil.isEqual(getUserId(), other.getUserId()) && 
			ObjectUtil.isEqual(getProfilePic(), other.getProfilePic()) && 
			ObjectUtil.isEqual(getFileName(), other.getFileName()) &&
			ObjectUtil.isEqual(getContentType(), other.getContentType()) &&
			ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy()) && 
			ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		int result = 0;
		result = result + (userProfilePicId!= null ? userProfilePicId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk){
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if(validatePk){
		if(this.userProfilePicId == null){
			 list.add(new ValidationMessage("Field " + FIELD_userRoleId+  " cannot be null"));
		}

		}
		if( StringUtil.isNullOrEmpty(this.userId)){
			 list.add(new ValidationMessage("Field " + FIELD_userId+  " cannot be null"));
		}

		if((this.userId != null) && (this.userId.length()>45)){
			 list.add(new ValidationMessage("Field " + FIELD_userId+  " cannot be longer than: " + 45));
		}

		if(this.isFromDB()){
			if( StringUtil.isNullOrEmpty(this.lastUpdatedBy)){
				 list.add(new ValidationMessage("Field " + FIELD_lastUpdatedBy+  " cannot be null"));
			}
		}
		if(this.isFromDB()){
			if((this.lastUpdatedBy != null) && (this.lastUpdatedBy.length()>45)){
				 list.add(new ValidationMessage("Field " + FIELD_lastUpdatedBy+  " cannot be longer than: " + 45));
			}
		}
		if(this.isFromDB()){
			if(this.lastUpdatedTs == null){
				 list.add(new ValidationMessage("Field " + FIELD_lastUpdatedTs+  " cannot be null"));
			}
		}
		if(list.size()>0)setHasValidationErrors(true);

		setValidationMessages(list);
		
	}

	@Override
	public final void setAuditFields() {
		if(!isFromDB()){
		}
		this.lastUpdatedBy = AppThreadLocal.getUserName();
		this.lastUpdatedTs = AppThreadLocal.getRequestStartDate();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BaseUserProfilePic [userProfilePicId=" + userProfilePicId + ", userId=" + userId + ", profilePic="
				+ Arrays.toString(profilePic) + ", fileName=" + fileName + ", contentType=" + contentType + "]";
	}

	@Transient
	@Override
	public final boolean isKeyNull(){
		return (userProfilePicId == null); 
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField(){
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_userRoleId, getUserProfilePicId()));
		return list;
	}

	@Transient
	@Override
	public Integer getId(){
		return getUserProfilePicId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		UserProfilePic userProfilePic = new UserProfilePic();
		userProfilePic.setFromDB(false);
		userProfilePic.setUserId(getUserId());
		userProfilePic.setProfilePic(getProfilePic());
		userProfilePic.setFileName(getFileName());
		userProfilePic.setContentType(getContentType());
		return userProfilePic;
	}
}