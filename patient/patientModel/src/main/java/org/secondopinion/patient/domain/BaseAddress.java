package org.secondopinion.patient.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.secondopinion.utils.threadlocal.AppThreadLocal;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.patient.dto.Address;
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

@SuppressWarnings({ "serial" })
@MappedSuperclass
public abstract class BaseAddress extends BaseDomainObject<Long> {

	public static final String FIELD_addressId = "addressId";
	public static final String FIELD_userId = "userId";
	public static final String FIELD_street1 = "street1";
	public static final String FIELD_street2 = "street2";
	public static final String FIELD_city = "city";
	public static final String FIELD_state = "state";
	public static final String FIELD_country = "country";
	public static final String FIELD_zip = "zip";
	public static final String FIELD_longitudes = "longitudes";
	public static final String FIELD_latitude = "latitude";
	public static final String FIELD_contactName = "contactName";
	public static final String FIELD_contactPhoneNumber = "contactPhoneNumber";
	public static final String FIELD_secondaryaddress = "secondaryaddress";
	public static final String FIELD_isPrimary = "isPrimary";
	public static final String FIELD_primaryaddress = "primaryaddress";
	public static final String FIELD_postalLocation = "postalLocation";
	public static final String FIELD_active = "active";
	public static final String FIELD_createdBy = "createdBy";
	public static final String FIELD_createdDate = "createdDate";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Long addressId;
	private Long userId;
	private String street1;
	private String street2;
	private String city;
	private String state;
	private String country;
	private String zip;
	private Long longitudes;
	private Long latitude;
	private String contactName;
	private String contactPhoneNumber;
	private String secondaryaddress;
	private Character isPrimary;
	private String primaryaddress;
	private Character active;
	private String postalLocation;

	public void setAddressId(Long _addressId) {
		this.addressId = _addressId;
	}

	@Id
	@Column(name = "addressId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getAddressId() {
		return this.addressId;
	}

	public void setUserId(Long _userId) {
		this.userId = _userId;
	}

	@NotNull
	@Column(name = "userId")
	public Long getUserId() {
		return this.userId;
	}

	public void setStreet1(String _street1) {
		this.street1 = _street1;
	}

	@Length(max = 150)
	@Column(name = "street1")
	public String getStreet1() {
		return this.street1;
	}

	public void setStreet2(String _street2) {
		this.street2 = _street2;
	}

	@Length(max = 150)
	@Column(name = "street2")
	public String getStreet2() {
		return this.street2;
	}

	public void setCity(String _city) {
		this.city = _city;
	}

	@Length(max = 150)
	@Column(name = "city")
	public String getCity() {
		return this.city;
	}

	public void setState(String _state) {
		this.state = _state;
	}

	@Length(max = 45)
	@Column(name = "state")
	public String getState() {
		return this.state;
	}

	public void setCountry(String _country) {
		this.country = _country;
	}

	@Length(max = 100)
	@Column(name = "country")
	public String getCountry() {
		return this.country;
	}

	public void setZip(String _zip) {
		this.zip = _zip;
	}

	@Length(max = 45)
	@Column(name = "zip")
	public String getZip() {
		return this.zip;
	}

	public void setLongitudes(Long _longitudes) {
		this.longitudes = _longitudes;
	}

	@Column(name = "longitudes")
	public Long getLongitudes() {
		return this.longitudes;
	}

	public void setLatitude(Long _latitude) {
		this.latitude = _latitude;
	}

	@Column(name = "latitude")
	public Long getLatitude() {
		return this.latitude;
	}

	public void setContactName(String _contactName) {
		this.contactName = _contactName;
	}

	@Length(max = 255)
	@Column(name = "contactName")
	public String getContactName() {
		return this.contactName;
	}

	public void setContactPhoneNumber(String _contactPhoneNumber) {
		this.contactPhoneNumber = _contactPhoneNumber;
	}

	@Length(max = 255)
	@Column(name = "contactPhoneNumber")
	public String getContactPhoneNumber() {
		return this.contactPhoneNumber;
	}

	public void setSecondaryaddress(String _secondaryaddress) {
		this.secondaryaddress = _secondaryaddress;
	}

	@Length(max = 255)
	@Column(name = "secondaryaddress")
	public String getSecondaryaddress() {
		return this.secondaryaddress;
	}

	public void setIsPrimary(Character _isPrimary) {
		this.isPrimary = _isPrimary;
	}

	@Column(name = "isPrimary")
	public Character getIsPrimary() {
		return this.isPrimary;
	}

	public void setPrimaryaddress(String _primaryaddress) {
		this.primaryaddress = _primaryaddress;
	}

	@Column(name = "postalLocation")
	public String getPostalLocation() {
		return postalLocation;
	}

	public void setPostalLocation(String postalLocation) {
		this.postalLocation = postalLocation;
	}

	@Length(max = 255)
	@Column(name = "primaryaddress")
	public String getPrimaryaddress() {
		return this.primaryaddress;
	}

	public void setActive(Character _active) {
		this.active = _active;
	}

	@NotNull
	@Column(name = "active")
	public Character getActive() {
		return this.active;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BaseAddress other = (BaseAddress) o;
		if (ObjectUtil.isEqual(getUserId(), other.getUserId()) && ObjectUtil.isEqual(getStreet1(), other.getStreet1())
				&& ObjectUtil.isEqual(getStreet2(), other.getStreet2())
				&& ObjectUtil.isEqual(getCity(), other.getCity()) && ObjectUtil.isEqual(getState(), other.getState())
				&& ObjectUtil.isEqual(getCountry(), other.getCountry()) && ObjectUtil.isEqual(getZip(), other.getZip())
				&& ObjectUtil.isEqual(getLongitudes(), other.getLongitudes())
				&& ObjectUtil.isEqual(getLatitude(), other.getLatitude())
				&& ObjectUtil.isEqual(getContactName(), other.getContactName())
				&& ObjectUtil.isEqual(getContactPhoneNumber(), other.getContactPhoneNumber())
				&& ObjectUtil.isEqual(getSecondaryaddress(), other.getSecondaryaddress())
				&& ObjectUtil.isEqual(getIsPrimary(), other.getIsPrimary())
				&& ObjectUtil.isEqual(getPrimaryaddress(), other.getPrimaryaddress())
				&& ObjectUtil.isEqual(getActive(), other.getActive()) 
				&& ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy())
				&& ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 0;
		result = result + (addressId != null ? addressId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk) {
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if (validatePk) {
			if (this.addressId == null) {
				list.add(new ValidationMessage("Field " + FIELD_addressId + " cannot be null"));
			}

		}
		if (this.userId == null) {
			list.add(new ValidationMessage("Field " + FIELD_userId + " cannot be null"));
		}

		if ((this.street1 != null) && (this.street1.length() > 150)) {
			list.add(new ValidationMessage("Field " + FIELD_street1 + " cannot be longer than: " + 150));
		}

		if ((this.street2 != null) && (this.street2.length() > 150)) {
			list.add(new ValidationMessage("Field " + FIELD_street2 + " cannot be longer than: " + 150));
		}

		if ((this.city != null) && (this.city.length() > 150)) {
			list.add(new ValidationMessage("Field " + FIELD_city + " cannot be longer than: " + 150));
		}

		if ((this.state != null) && (this.state.length() > 45)) {
			list.add(new ValidationMessage("Field " + FIELD_state + " cannot be longer than: " + 45));
		}

		if ((this.country != null) && (this.country.length() > 100)) {
			list.add(new ValidationMessage("Field " + FIELD_country + " cannot be longer than: " + 100));
		}

		if ((this.zip != null) && (this.zip.length() > 45)) {
			list.add(new ValidationMessage("Field " + FIELD_zip + " cannot be longer than: " + 45));
		}

		if ((this.contactName != null) && (this.contactName.length() > 255)) {
			list.add(new ValidationMessage("Field " + FIELD_contactName + " cannot be longer than: " + 255));
		}

		if ((this.contactPhoneNumber != null) && (this.contactPhoneNumber.length() > 255)) {
			list.add(new ValidationMessage("Field " + FIELD_contactPhoneNumber + " cannot be longer than: " + 255));
		}

		if ((this.secondaryaddress != null) && (this.secondaryaddress.length() > 255)) {
			list.add(new ValidationMessage("Field " + FIELD_secondaryaddress + " cannot be longer than: " + 255));
		}

		if ((this.primaryaddress != null) && (this.primaryaddress.length() > 255)) {
			list.add(new ValidationMessage("Field " + FIELD_primaryaddress + " cannot be longer than: " + 255));
		}

		if (this.active == null) {
			list.add(new ValidationMessage("Field " + FIELD_active + " cannot be null"));
		}

		if (this.isFromDB()) {
			if ((this.lastUpdatedBy != null) && (this.lastUpdatedBy.length() > 255)) {
				list.add(new ValidationMessage("Field " + FIELD_lastUpdatedBy + " cannot be longer than: " + 255));
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
		}
		this.lastUpdatedBy = AppThreadLocal.getUserName();
		this.lastUpdatedTs = AppThreadLocal.getRequestStartDate();
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("addressId = " + addressId + "\n");
		;
		str.append("userId = " + userId + "\n");
		str.append("street1 = " + street1 + "\n");
		str.append("street2 = " + street2 + "\n");
		str.append("city = " + city + "\n");
		str.append("state = " + state + "\n");
		str.append("country = " + country + "\n");
		str.append("zip = " + zip + "\n");
		str.append("longitudes = " + longitudes + "\n");
		str.append("latitude = " + latitude + "\n");
		str.append("contactName = " + contactName + "\n");
		str.append("contactPhoneNumber = " + contactPhoneNumber + "\n");
		str.append("secondaryaddress = " + secondaryaddress + "\n");
		str.append("isPrimary = " + isPrimary + "\n");
		str.append("primaryaddress = " + primaryaddress + "\n");
		str.append("active = " + active + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull() {
		return (addressId == null);
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField() {
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_addressId, getAddressId()));
		return list;
	}

	@Transient
	@Override
	public Long getId() {
		return getAddressId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Address address = new Address();
		address.setFromDB(false);
		address.setUserId(getUserId());
		address.setStreet1(getStreet1());
		address.setStreet2(getStreet2());
		address.setCity(getCity());
		address.setState(getState());
		address.setCountry(getCountry());
		address.setZip(getZip());
		address.setLongitudes(getLongitudes());
		address.setLatitude(getLatitude());
		address.setContactName(getContactName());
		address.setContactPhoneNumber(getContactPhoneNumber());
		address.setSecondaryaddress(getSecondaryaddress());
		address.setIsPrimary(getIsPrimary());
		address.setPrimaryaddress(getPrimaryaddress());
		address.setActive(getActive());
		address.setCreatedBy(getCreatedBy());
		address.setCreatedDate(getCreatedDate());
		// afterClone(address);
		return address;
	}

}