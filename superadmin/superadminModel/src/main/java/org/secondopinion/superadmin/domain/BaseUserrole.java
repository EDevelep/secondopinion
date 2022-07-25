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
import org.secondopinion.superadmin.dto.Userrole;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.threadlocal.AppThreadLocal;

@SuppressWarnings({"serial"})
@MappedSuperclass
public abstract class BaseUserrole extends BaseDomainObject<Long> {

  public static final String FIELD_userRoleId = "userRoleId";
  public static final String FIELD_roleId = "roleId";
  public static final String FIELD_userId = "userId";
 

  private Long userRoleId;
  private Integer roleId;
  private Long userId;
	
  public void setUserRoleId(Long _userRoleId) {
    this.userRoleId = _userRoleId;
  }

  @Id
  @Column(name = "userRoleId")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long getUserRoleId() {
    return this.userRoleId;
  }

  public void setRoleId(Integer _roleId) {
    this.roleId = _roleId;
  }

  @NotNull
  @Column(name = "roleId")
  public Integer getRoleId() {
    return this.roleId;
  }


  @NotNull
  @Column(name = "userId")
  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    BaseUserrole other = (BaseUserrole) o;
    if (ObjectUtil.isEqual(getRoleId(), other.getRoleId())
        && ObjectUtil.isEqual(getUserId(), other.getUserId())
        && ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy())
        && ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())) {
      return true;
    }
    return false;
  }

  @Override
  public int hashCode() {
    int result = 0;
    result = result + (userRoleId != null ? userRoleId.hashCode() : 0);

    return result;
  }

  public void validate(boolean validatePk) {
    resetValidationMessages();

    List<ValidationMessage> list = new ArrayList<ValidationMessage>();
    if (validatePk) {
      if (this.userRoleId == null) {
        list.add(new ValidationMessage("Field " + FIELD_userRoleId + " cannot be null"));
      }

    }
    if (this.roleId == null) {
      list.add(new ValidationMessage("Field " + FIELD_roleId + " cannot be null"));
    }

    if ((this.userId == null)) {
      list.add(new ValidationMessage("Field " + FIELD_userId + " cannot be null"));
    }


    if (this.isFromDB()) {
      if ((this.lastUpdatedBy != null) && (this.lastUpdatedBy.length() > 100)) {
        list.add(new ValidationMessage(
            "Field " + FIELD_lastUpdatedBy + " cannot be longer than: " + 100));
      }
    }
    if (list.size() > 0)
      setHasValidationErrors(true);

    setValidationMessages(list);

  }

  @Override
  public final void setAuditFields() {
    if (!isFromDB()) {
    }
    this.lastUpdatedBy = AppThreadLocal.getUserName();
    this.lastUpdatedTs = AppThreadLocal.getRequestStartDate();
  }

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append("userRoleId = " + userRoleId + "\n");;
    str.append("roleId = " + roleId + "\n");
    str.append("userId = " + userId + "\n");
    str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
    str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
    return str.toString();
  }

  @Transient
  @Override
  public final boolean isKeyNull() {
    return (userRoleId == null);
  }

  @Transient
  @Override
  public final List<KeyField> getKeyField() {
    List<KeyField> list = new ArrayList<KeyField>();
    list.add(new KeyField(FIELD_userRoleId, getUserRoleId()));
    return list;
  }

  @Transient
  @Override
  public Long getId() {
    return getUserRoleId();
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    Userrole userrole = new Userrole();
    userrole.setFromDB(false);
    userrole.setRoleId(getRoleId());
    userrole.setUserId(getUserId());
    // afterClone(userrole);
    return userrole;
  }
}
