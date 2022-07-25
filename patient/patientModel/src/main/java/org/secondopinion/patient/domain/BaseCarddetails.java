package org.secondopinion.patient.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.secondopinion.utils.threadlocal.AppThreadLocal;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.patient.dto.Carddetails;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.utils.ObjectUtil;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.hibernate.validator.constraints.Length;

@MappedSuperclass
public abstract class BaseCarddetails extends BaseDomainObject<Long> {

	public static final String FIELD_carddetailsId = "carddetailsId";
	public static final String FIELD_cardtype = "cardtype";
	public static final String FIELD_userId = "userId";
	public static final String FIELD_cardbankname = "cardbankname";
	public static final String FIELD_cardnumber = "cardnumber";
	public static final String FIELD_expmonth = "expmonth";
	public static final String FIELD_active = "active";
	public static final String FIELD_expyear = "expyear";
	public static final String FIELD_username = "username";
	public static final String FIELD_createdBy = "createdBy";
	public static final String FIELD_createdDate = "createdDate";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Long carddetailsId;
	private String cardtype;
	private Long userId;
	private String cardbankname;
	private String cardnumber;
	private Long expmonth;
	private Character active;
	private Long expyear;
	private String username;

	public void setCarddetailsId(Long _carddetailsId) {
		this.carddetailsId = _carddetailsId;
	}

	@Id
	@Column(name = "carddetailsId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCarddetailsId() {
		return this.carddetailsId;
	}

	public void setCardtype(String _cardtype) {
		this.cardtype = _cardtype;
	}

	@Length(max = 180)
	@Column(name = "cardtype")
	public String getCardtype() {
		return this.cardtype;
	}

	public void setUserId(Long _userId) {
		this.userId = _userId;
	}

	@Column(name = "userId")
	public Long getUserId() {
		return this.userId;
	}

	public void setCardbankname(String _cardbankname) {
		this.cardbankname = _cardbankname;
	}

	@NotNull
	@Length(max = 255)
	@Column(name = "cardbankname")
	public String getCardbankname() {
		return this.cardbankname;
	}

	public void setCardnumber(String _cardnumber) {
		this.cardnumber = _cardnumber;
	}

	@Length(max = 255)
	@Column(name = "cardnumber")
	public String getCardnumber() {
		return this.cardnumber;
	}

	public void setExpmonth(Long _expmonth) {
		this.expmonth = _expmonth;
	}

	@NotNull
	@Column(name = "expmonth")
	public Long getExpmonth() {
		return this.expmonth;
	}

	public void setActive(Character _active) {
		this.active = _active;
	}

	@NotNull
	@Column(name = "active")
	public Character getActive() {
		return this.active;
	}

	public void setExpyear(Long _expyear) {
		this.expyear = _expyear;
	}

	@NotNull
	@Column(name = "expyear")
	public Long getExpyear() {
		return this.expyear;
	}

	public void setUsername(String _username) {
		this.username = _username;
	}

	@NotNull
	@Length(max = 255)
	@Column(name = "username")
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BaseCarddetails other = (BaseCarddetails) o;
		if (ObjectUtil.isEqual(getCardtype(), other.getCardtype()) && ObjectUtil.isEqual(getUserId(), other.getUserId())
				&& ObjectUtil.isEqual(getCardbankname(), other.getCardbankname())
				&& ObjectUtil.isEqual(getCardnumber(), other.getCardnumber())
				&& ObjectUtil.isEqual(getExpmonth(), other.getExpmonth())
				&& ObjectUtil.isEqual(getActive(), other.getActive())
				&& ObjectUtil.isEqual(getExpyear(), other.getExpyear())
				&& ObjectUtil.isEqual(getUsername(), other.getUsername())
				&& ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy())
				&& ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 0;
		result = result + (carddetailsId != null ? carddetailsId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk) {
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if (validatePk) {
			if (this.carddetailsId == null) {
				list.add(new ValidationMessage("Field " + FIELD_carddetailsId + " cannot be null"));
			}

		}
		if ((this.cardtype != null) && (this.cardtype.length() > 180)) {
			list.add(new ValidationMessage("Field " + FIELD_cardtype + " cannot be longer than: " + 180));
		}

		if (StringUtil.isNullOrEmpty(this.cardbankname)) {
			list.add(new ValidationMessage("Field " + FIELD_cardbankname + " cannot be null"));
		}

		if ((this.cardbankname != null) && (this.cardbankname.length() > 255)) {
			list.add(new ValidationMessage("Field " + FIELD_cardbankname + " cannot be longer than: " + 255));
		}

		if ((this.cardnumber != null) && (this.cardnumber.length() > 255)) {
			list.add(new ValidationMessage("Field " + FIELD_cardnumber + " cannot be longer than: " + 255));
		}

		if (this.expmonth == null) {
			list.add(new ValidationMessage("Field " + FIELD_expmonth + " cannot be null"));
		}

		if (this.active == null) {
			list.add(new ValidationMessage("Field " + FIELD_active + " cannot be null"));
		}

		if (this.expyear == null) {
			list.add(new ValidationMessage("Field " + FIELD_expyear + " cannot be null"));
		}

		if (StringUtil.isNullOrEmpty(this.username)) {
			list.add(new ValidationMessage("Field " + FIELD_username + " cannot be null"));
		}

		if ((this.username != null) && (this.username.length() > 255)) {
			list.add(new ValidationMessage("Field " + FIELD_username + " cannot be longer than: " + 255));
		}

		if (this.isFromDB()) {
			if ((this.lastUpdatedBy != null) && (this.lastUpdatedBy.length() > 100)) {
				list.add(new ValidationMessage("Field " + FIELD_lastUpdatedBy + " cannot be longer than: " + 100));
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
		str.append("carddetailsId = " + carddetailsId + "\n");
		;
		str.append("cardtype = " + cardtype + "\n");
		str.append("userId = " + userId + "\n");
		str.append("cardbankname = " + cardbankname + "\n");
		str.append("cardnumber = " + cardnumber + "\n");
		str.append("expmonth = " + expmonth + "\n");
		str.append("active = " + active + "\n");
		str.append("expyear = " + expyear + "\n");
		str.append("username = " + username + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull() {
		return (carddetailsId == null);
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField() {
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_carddetailsId, getCarddetailsId()));
		return list;
	}

	@Transient
	@Override
	public Long getId() {
		return getCarddetailsId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Carddetails carddetails = new Carddetails();
		carddetails.setFromDB(false);
		carddetails.setCardtype(getCardtype());
		carddetails.setUserId(getUserId());
		carddetails.setCardbankname(getCardbankname());
		carddetails.setCardnumber(getCardnumber());
		carddetails.setExpmonth(getExpmonth());
		carddetails.setActive(getActive());

		carddetails.setExpyear(getExpyear());
		carddetails.setUsername(getUsername());
		// afterClone(carddetails);
		return carddetails;
	}
}