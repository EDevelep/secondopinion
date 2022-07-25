package org.secondopinion.superadmin.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.superadmin.dto.User;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal;

@MappedSuperclass
public abstract class BaseUser extends BaseDomainObject<Long> {

  public static final String FIELD_userId = "userId";
  public static final String FIELD_userName = "userName";
  public static final String FIELD_firstName = "firstName";
  public static final String FIELD_lastName = "lastName";
  public static final String FIELD_middleName = "middleName";
  public static final String FIELD_emailId = "emailId";
  public static final String FIELD_password = "password";
  public static final String FIELD_cellNumber = "cellNumber";
  public static final String FIELD_officeNumber = "officeNumber";
  public static final String FIELD_homeNumber = "homeNumber";
  public static final String FIELD_primaryContact = "primaryContact";
  public static final String FIELD_emergencycontact = "emergencycontact";
  public static final String FIELD_active = "active";
  public static final String FIELD_activatedDate = "activatedDate";
  public static final String FIELD_operatedByUser = "operatedByUser";
  public static final String FIELD_vitalLastRecordedDate = "vitalLastRecordedDate";
  public static final String FIELD_lastLogin = "lastLogin";
  public static final String FIELD_locked = "locked";
  public static final String FIELD_status = "status";
  public static final String FIELD_nativelanguage = "nativelanguage";
  public static final String FIELD_preferredlanguages = "preferredlanguages";
  public static final String FIELD_retry = "retry";
  public static final String FIELD_ = "addedBy";
  public static final String FIELD_roleId = "roleId";
 
  private Long userId;
  private String userName;
  private String firstName;
  private String lastName;
  private String middleName;
  private String emailId;
  private String password;
  private String cellNumber;
  private String officeNumber;
  private String homeNumber;
  private String primaryContact;
  private String emergencycontact;
  private String addedBy;
  private Character active;
  private Date activatedDate;
  private Long operatedByUser;
  private Date vitalLastRecordedDate;
  private Date lastLogin;
  private Character locked;
  private String status;
  private String nativelanguage;
  private String preferredlanguages;
  private Integer retry;
  private Integer roleId;

  public void setUserId(Long _userId) {
    this.userId = _userId;
  }

  @Id
  @Column(name = "userId")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long getUserId() {
    return this.userId;
  }

  public void setUserName(String _userName) {
    this.userName = _userName;
  }

  @NotNull
  @Length(max = 95)
  @Column(name = "userName")
  public String getUserName() {
    return this.userName;
  }

  public void setFirstName(String _firstName) {
    this.firstName = _firstName;
  }

  @NotNull
  @Length(max = 100)
  @Column(name = "firstName")
  public String getFirstName() {
    return this.firstName;
  }

  public void setLastName(String _lastName) {
    this.lastName = _lastName;
  }

  @NotNull
  @Length(max = 100)
  @Column(name = "lastName")
  public String getLastName() {
    return this.lastName;
  }

  public void setRoleId(Integer _roleId) {
    this.roleId = _roleId;
  }


  @Column(name = "roleId")
  public Integer getRoleId() {
    return this.roleId;
  }

  public void setMiddleName(String _middleName) {
    this.middleName = _middleName;
  }

  @Length(max = 45)
  @Column(name = "middleName")
  public String getMiddleName() {
    return this.middleName;
  }

  public void setEmailId(String _emailId) {
    this.emailId = _emailId;
  }

  @NotNull
  @Length(max = 45)
  @Column(name = "emailId")
  public String getEmailId() {
    return this.emailId;
  }

  public void setPassword(String _password) {
    this.password = _password;
  }

  @NotNull
  @Length(max = 250)
  @Column(name = "password")
  public String getPassword() {
    return this.password;
  }

  public void setCellNumber(String _cellNumber) {
    this.cellNumber = _cellNumber;
  }

  @NotNull
  @Length(max = 45)
  @Column(name = "cellNumber")
  public String getCellNumber() {
    return this.cellNumber;
  }

  public void setOfficeNumber(String _officeNumber) {
    this.officeNumber = _officeNumber;
  }

  @Length(max = 45)
  @Column(name = "officeNumber")
  public String getOfficeNumber() {
    return this.officeNumber;
  }

  public void setHomeNumber(String _homeNumber) {
    this.homeNumber = _homeNumber;
  }

  @Length(max = 45)
  @Column(name = "homeNumber")
  public String getHomeNumber() {
    return this.homeNumber;
  }

  public void setPrimaryContact(String _primaryContact) {
    this.primaryContact = _primaryContact;
  }

  @NotNull
  @Length(max = 45)
  @Column(name = "primaryContact")
  public String getPrimaryContact() {
    return this.primaryContact;
  }

  public void setEmergencycontact(String _emergencycontact) {
    this.emergencycontact = _emergencycontact;
  }

  @Length(max = 45)
  @Column(name = "emergencycontact")
  public String getEmergencycontact() {
    return this.emergencycontact;
  }

  public void setActive(Character _active) {
    this.active = _active;
  }

  @NotNull
  @Column(name = "active")
  public Character getActive() {
    return this.active;
  }

  public void setActivatedDate(Date _activatedDate) {
    this.activatedDate = _activatedDate;
  }

  @Column(name = "activatedDate")
  public Date getActivatedDate() {
    return this.activatedDate;
  }

  public void setOperatedByUser(Long _operatedByUser) {
    this.operatedByUser = _operatedByUser;
  }

  @Column(name = "operatedByUser")
  public Long getOperatedByUser() {
    return this.operatedByUser;
  }

  public void setVitalLastRecordedDate(Date _vitalLastRecordedDate) {
    this.vitalLastRecordedDate = _vitalLastRecordedDate;
  }

  @Column(name = "vitalLastRecordedDate")
  public Date getVitalLastRecordedDate() {
    return this.vitalLastRecordedDate;
  }

  public void setLastLogin(Date _lastLogin) {
    this.lastLogin = _lastLogin;
  }

  @Column(name = "lastLogin")
  public Date getLastLogin() {
    return this.lastLogin;
  }

  public void setLocked(Character _locked) {
    this.locked = _locked;
  }

  @Column(name = "locked")
  public Character getLocked() {
    return this.locked;
  }

  public void setStatus(String _status) {
    this.status = _status;
  }

  @Length(max = 145)
  @Column(name = "status")
  public String getStatus() {
    return this.status;
  }

  public void setNativelanguage(String _nativelanguage) {
    this.nativelanguage = _nativelanguage;
  }

  @Length(max = 255)
  @Column(name = "nativelanguage")
  public String getNativelanguage() {
    return this.nativelanguage;
  }

  public void setPreferredlanguages(String _preferredlanguages) {
    this.preferredlanguages = _preferredlanguages;
  }

  @Length(max = 255)
  @Column(name = "preferredlanguages")
  public String getPreferredlanguages() {
    return this.preferredlanguages;
  }

  public void setRetry(Integer _retry) {
    this.retry = _retry;
  }

  @Column(name = "retry")
  public Integer getRetry() {
    return this.retry;
  }

  @Column(name = "addedBy")
  public String getAddedBy() {
    return addedBy;
  }

  public void setAddedBy(String addedBy) {
    this.addedBy = addedBy;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    BaseUser other = (BaseUser) o;
    if (ObjectUtil.isEqual(getUserName(), other.getUserName())
        && ObjectUtil.isEqual(getFirstName(), other.getFirstName())
        && ObjectUtil.isEqual(getLastName(), other.getLastName())
        && ObjectUtil.isEqual(getMiddleName(), other.getMiddleName())
        && ObjectUtil.isEqual(getEmailId(), other.getEmailId())
        && ObjectUtil.isEqual(getPassword(), other.getPassword())
        && ObjectUtil.isEqual(getCellNumber(), other.getCellNumber())
        && ObjectUtil.isEqual(getOfficeNumber(), other.getOfficeNumber())
        && ObjectUtil.isEqual(getHomeNumber(), other.getHomeNumber())
        && ObjectUtil.isEqual(getPrimaryContact(), other.getPrimaryContact())
        && ObjectUtil.isEqual(getEmergencycontact(), other.getEmergencycontact())
        && ObjectUtil.isEqual(getActive(), other.getActive())
        && ObjectUtil.isEqual(getActivatedDate(), other.getActivatedDate())
        && ObjectUtil.isEqual(getOperatedByUser(), other.getOperatedByUser())
        && ObjectUtil.isEqual(getVitalLastRecordedDate(), other.getVitalLastRecordedDate())
        && ObjectUtil.isEqual(getLastLogin(), other.getLastLogin())
        && ObjectUtil.isEqual(getLocked(), other.getLocked())
        && ObjectUtil.isEqual(getStatus(), other.getStatus())
        && ObjectUtil.isEqual(getNativelanguage(), other.getNativelanguage())
        && ObjectUtil.isEqual(getPreferredlanguages(), other.getPreferredlanguages())
        && ObjectUtil.isEqual(getRetry(), other.getRetry())
        && ObjectUtil.isEqual(getRoleId(), other.getRoleId())
        && ObjectUtil.isEqual(getCreatedBy(), other.getCreatedBy())
        && ObjectUtil.isEqual(getCreatedDate(), other.getCreatedDate())
        && ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy())
        && ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())) {
      return true;
    }
    return false;
  }

  @Override
  public int hashCode() {
    int result = 0;
    result = result + (userId != null ? userId.hashCode() : 0);

    return result;
  }

  public void validate(boolean validatePk) {
    resetValidationMessages();

    List<ValidationMessage> list = new ArrayList<ValidationMessage>();
    if (validatePk) {
      if (this.userId == null) {
        list.add(new ValidationMessage("Field " + FIELD_userId + " cannot be null"));
      }

    }
    if (StringUtil.isNullOrEmpty(this.userName)) {
      list.add(new ValidationMessage("Field " + FIELD_userName + " cannot be null"));
    }

    if ((this.userName != null) && (this.userName.length() > 95)) {
      list.add(new ValidationMessage("Field " + FIELD_userName + " cannot be longer than: " + 95));
    }

    if (StringUtil.isNullOrEmpty(this.firstName)) {
      list.add(new ValidationMessage("Field " + FIELD_firstName + " cannot be null"));
    }

    if ((this.firstName != null) && (this.firstName.length() > 100)) {
      list.add(
          new ValidationMessage("Field " + FIELD_firstName + " cannot be longer than: " + 100));
    }

    if (StringUtil.isNullOrEmpty(this.lastName)) {
      list.add(new ValidationMessage("Field " + FIELD_lastName + " cannot be null"));
    }

    if ((this.lastName != null) && (this.lastName.length() > 100)) {
      list.add(new ValidationMessage("Field " + FIELD_lastName + " cannot be longer than: " + 100));
    }

    if ((this.middleName != null) && (this.middleName.length() > 45)) {
      list.add(
          new ValidationMessage("Field " + FIELD_middleName + " cannot be longer than: " + 45));
    }

    if (StringUtil.isNullOrEmpty(this.emailId)) {
      list.add(new ValidationMessage("Field " + FIELD_emailId + " cannot be null"));
    }

    if ((this.emailId != null) && (this.emailId.length() > 45)) {
      list.add(new ValidationMessage("Field " + FIELD_emailId + " cannot be longer than: " + 45));
    }

    if (StringUtil.isNullOrEmpty(this.password)) {
      list.add(new ValidationMessage("Field " + FIELD_password + " cannot be null"));
    }

    if ((this.password != null) && (this.password.length() > 250)) {
      list.add(new ValidationMessage("Field " + FIELD_password + " cannot be longer than: " + 250));
    }

    if (StringUtil.isNullOrEmpty(this.cellNumber)) {
      list.add(new ValidationMessage("Field " + FIELD_cellNumber + " cannot be null"));
    }

    if ((this.cellNumber != null) && (this.cellNumber.length() > 45)) {
      list.add(
          new ValidationMessage("Field " + FIELD_cellNumber + " cannot be longer than: " + 45));
    }

    if ((this.officeNumber != null) && (this.officeNumber.length() > 45)) {
      list.add(
          new ValidationMessage("Field " + FIELD_officeNumber + " cannot be longer than: " + 45));
    }

    if ((this.homeNumber != null) && (this.homeNumber.length() > 45)) {
      list.add(
          new ValidationMessage("Field " + FIELD_homeNumber + " cannot be longer than: " + 45));
    }

    if (StringUtil.isNullOrEmpty(this.primaryContact)) {
      list.add(new ValidationMessage("Field " + FIELD_primaryContact + " cannot be null"));
    }

    if ((this.primaryContact != null) && (this.primaryContact.length() > 45)) {
      list.add(
          new ValidationMessage("Field " + FIELD_primaryContact + " cannot be longer than: " + 45));
    }

    if ((this.emergencycontact != null) && (this.emergencycontact.length() > 45)) {
      list.add(new ValidationMessage(
          "Field " + FIELD_emergencycontact + " cannot be longer than: " + 45));
    }

    if (this.active == null) {
      list.add(new ValidationMessage("Field " + FIELD_active + " cannot be null"));
    }

    if ((this.status != null) && (this.status.length() > 145)) {
      list.add(new ValidationMessage("Field " + FIELD_status + " cannot be longer than: " + 145));
    }

    if ((this.nativelanguage != null) && (this.nativelanguage.length() > 255)) {
      list.add(new ValidationMessage(
          "Field " + FIELD_nativelanguage + " cannot be longer than: " + 255));
    }

    if ((this.preferredlanguages != null) && (this.preferredlanguages.length() > 255)) {
      list.add(new ValidationMessage(
          "Field " + FIELD_preferredlanguages + " cannot be longer than: " + 255));
    }

    if (this.isFromDB()) {
      if ((this.createdBy != null) && (this.createdBy.length() > 255)) {
        list.add(
            new ValidationMessage("Field " + FIELD_createdBy + " cannot be longer than: " + 255));
      }
    }
    if (this.isFromDB()) {
      if ((this.lastUpdatedBy != null) && (this.lastUpdatedBy.length() > 255)) {
        list.add(new ValidationMessage(
            "Field " + FIELD_lastUpdatedBy + " cannot be longer than: " + 255));
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
  public final void setAuditFields() {
    if (!isFromDB()) {
      this.createdBy = AppThreadLocal.getUserName();
    }
    this.lastUpdatedBy = AppThreadLocal.getUserName();
    this.lastUpdatedTs = AppThreadLocal.getRequestStartDate();
  }

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append("userId = " + userId + "\n");;
    str.append("userName = " + userName + "\n");
    str.append("firstName = " + firstName + "\n");
    str.append("lastName = " + lastName + "\n");
    str.append("middleName = " + middleName + "\n");
    str.append("emailId = " + emailId + "\n");
    str.append("password = " + password + "\n");
    str.append("cellNumber = " + cellNumber + "\n");
    str.append("officeNumber = " + officeNumber + "\n");
    str.append("homeNumber = " + homeNumber + "\n");
    str.append("primaryContact = " + primaryContact + "\n");
    str.append("emergencycontact = " + emergencycontact + "\n");
    str.append("active = " + active + "\n");
    str.append("activatedDate = " + activatedDate + "\n");
    str.append("operatedByUser = " + operatedByUser + "\n");
    str.append("vitalLastRecordedDate = " + vitalLastRecordedDate + "\n");
    str.append("lastLogin = " + lastLogin + "\n");
    str.append("locked = " + locked + "\n");
    str.append("status = " + status + "\n");
    str.append("nativelanguage = " + nativelanguage + "\n");
    str.append("preferredlanguages = " + preferredlanguages + "\n");
    str.append("retry = " + retry + "\n");
    str.append("createdBy = " + createdBy + "\n");
    str.append("addedBy = " + addedBy + "\n");
    str.append("roleId = " + roleId + "\n");
    str.append("createdDate = " + createdDate + "\n");
    str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
    str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
    return str.toString();
  }

  @Transient
  @Override
  public final boolean isKeyNull() {
    return (userId == null);
  }

  @Transient
  @Override
  public final List<KeyField> getKeyField() {
    List<KeyField> list = new ArrayList<KeyField>();
    list.add(new KeyField(FIELD_userId, getUserId()));
    return list;
  }

  @Transient
  @Override
  public Long getId() {
    return getUserId();
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    User user = new User();
    user.setFromDB(false);
    user.setUserName(getUserName());
    user.setFirstName(getFirstName());
    user.setLastName(getLastName());
    user.setMiddleName(getMiddleName());
    user.setEmailId(getEmailId());
    user.setPassword(getPassword());
    user.setCellNumber(getCellNumber());
    user.setOfficeNumber(getOfficeNumber());
    user.setHomeNumber(getHomeNumber());
    user.setPrimaryContact(getPrimaryContact());
    user.setEmergencycontact(getEmergencycontact());
    user.setActive(getActive());
    user.setActivatedDate(getActivatedDate());
    user.setOperatedByUser(getOperatedByUser());
    user.setVitalLastRecordedDate(getVitalLastRecordedDate());
    user.setLastLogin(getLastLogin());
    user.setLocked(getLocked());
    user.setStatus(getStatus());
    user.setNativelanguage(getNativelanguage());
    user.setPreferredlanguages(getPreferredlanguages());
    user.setAddedBy(getAddedBy());
    user.setRetry(getRetry());
    user.setRoleId(getRoleId());
    user.setCreatedDate(getCreatedDate());
    // afterClone(user);
    return user;
  }

}
