package org.secondopinion.pharmacy.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.secondopinion.utils.threadlocal.AppThreadLocal;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.pharmacy.dto.Inventory;

import org.secondopinion.utils.ObjectUtil;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.hibernate.validator.constraints.Length;

@MappedSuperclass
public abstract class BaseInventory extends BaseDomainObject<Long> {

	public static final String FIELD_inventoryId = "inventoryId";
	public static final String FIELD_pharmacyId = "pharmacyId";
	public static final String FIELD_drugname = "drugname";
	public static final String FIELD_alternate = "alternate";
	public static final String FIELD_manufacturer = "manufacturer";
	public static final String FIELD_mfgdate = "mfgdate";
	public static final String FIELD_expdate = "expdate";
	public static final String FIELD_price = "price";
	public static final String FIELD_potency = "potency";
	public static final String FIELD_drugtype = "drugtype";
	public static final String FIELD_injectable = "injectable";
	public static final String FIELD_drugform = "drugform";
	public static final String FIELD_quantityavailable = "quantityavailable";
	public static final String FIELD_avillabe = "avillabe";
	public static final String FIELD_active = "active";
	public static final String FIELD_prescriptionrequired = "prescriptionrequired";
	public static final String FIELD_createdBy = "createdBy";
	public static final String FIELD_createdDate = "createdDate";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Long inventoryId;
	private Long pharmacyId;
	private String drugname;
	private String alternate;
	private String manufacturer;
	private Date mfgdate;
	private Date expdate;
	private Double price;
	private String potency;
	private String drugtype;
	private String injectable;
	private String drugform;
	private Long quantityavailable;
	private Long avillabe;
	private Character active;
	private Character prescriptionrequired;

	public void setInventoryId(Long _inventoryId) {
		this.inventoryId = _inventoryId;
	}

	@Id
	@Column(name = "inventoryId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getInventoryId() {
		return this.inventoryId;
	}

	public void setPharmacyId(Long _pharmacyId) {
		this.pharmacyId = _pharmacyId;
	}

	@Column(name = "pharmacyId")
	public Long getPharmacyId() {
		return this.pharmacyId;
	}

	public void setDrugname(String _drugname) {
		this.drugname = _drugname;
	}

	@Length(max = 150)
	@Column(name = "drugname")
	public String getDrugname() {
		return this.drugname;
	}

	public void setAlternate(String _alternate) {
		this.alternate = _alternate;
	}

	@Length(max = 150)
	@Column(name = "alternate")
	public String getAlternate() {
		return this.alternate;
	}

	public void setManufacturer(String _manufacturer) {
		this.manufacturer = _manufacturer;
	}

	@Length(max = 150)
	@Column(name = "manufacturer")
	public String getManufacturer() {
		return this.manufacturer;
	}

	public void setMfgdate(Date _mfgdate) {
		this.mfgdate = _mfgdate;
	}

	@Column(name = "mfgdate")
	public Date getMfgdate() {
		return this.mfgdate;
	}

	public void setExpdate(Date _expdate) {
		this.expdate = _expdate;
	}

	@Column(name = "expdate")
	public Date getExpdate() {
		return this.expdate;
	}

	public void setPrice(Double _price) {
		this.price = _price;
	}

	@Column(name = "price")
	public Double getPrice() {
		return this.price;
	}

	public void setPotency(String _potency) {
		this.potency = _potency;
	}

	@Length(max = 45)
	@Column(name = "potency")
	public String getPotency() {
		return this.potency;
	}

	public void setDrugtype(String _drugtype) {
		this.drugtype = _drugtype;
	}

	@Length(max = 150)
	@Column(name = "drugtype")
	public String getDrugtype() {
		return this.drugtype;
	}

	public void setInjectable(String _injectable) {
		this.injectable = _injectable;
	}

	@Length(max = 150)
	@Column(name = "injectable")
	public String getInjectable() {
		return this.injectable;
	}

	public void setDrugform(String _drugform) {
		this.drugform = _drugform;
	}

	@Length(max = 255)
	@Column(name = "drugform")
	public String getDrugform() {
		return this.drugform;
	}

	public void setQuantityavailable(Long _quantityavailable) {
		this.quantityavailable = _quantityavailable;
	}

	@Column(name = "quantityavailable")
	public Long getQuantityavailable() {
		return this.quantityavailable;
	}

	public void setAvillabe(Long _avillabe) {
		this.avillabe = _avillabe;
	}

	@Column(name = "avillabe")
	public Long getAvillabe() {
		return this.avillabe;
	}

	public void setActive(Character _active) {
		this.active = _active;
	}

	@Column(name = "active")
	public Character getActive() {
		return this.active;
	}

	public void setPrescriptionrequired(Character _prescriptionrequired) {
		this.prescriptionrequired = _prescriptionrequired;
	}

	@Column(name = "prescriptionrequired")
	public Character getPrescriptionrequired() {
		return this.prescriptionrequired;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BaseInventory other = (BaseInventory) o;
		if (ObjectUtil.isEqual(getPharmacyId(), other.getPharmacyId())
				&& ObjectUtil.isEqual(getDrugname(), other.getDrugname())
				&& ObjectUtil.isEqual(getAlternate(), other.getAlternate())
				&& ObjectUtil.isEqual(getManufacturer(), other.getManufacturer())
				&& ObjectUtil.isEqual(getMfgdate(), other.getMfgdate())
				&& ObjectUtil.isEqual(getExpdate(), other.getExpdate())
				&& ObjectUtil.isEqual(getPrice(), other.getPrice())
				&& ObjectUtil.isEqual(getPotency(), other.getPotency())
				&& ObjectUtil.isEqual(getDrugtype(), other.getDrugtype())
				&& ObjectUtil.isEqual(getInjectable(), other.getInjectable())
				&& ObjectUtil.isEqual(getDrugform(), other.getDrugform())
				&& ObjectUtil.isEqual(getQuantityavailable(), other.getQuantityavailable())
				&& ObjectUtil.isEqual(getAvillabe(), other.getAvillabe())
				&& ObjectUtil.isEqual(getActive(), other.getActive())
				&& ObjectUtil.isEqual(getPrescriptionrequired(), other.getPrescriptionrequired())
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
		result = result + (inventoryId != null ? inventoryId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk) {
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if (validatePk) {
			if (this.inventoryId == null) {
				list.add(new ValidationMessage("Field " + FIELD_inventoryId + " cannot be null"));
			}

		}
		if ((this.drugname != null) && (this.drugname.length() > 150)) {
			list.add(new ValidationMessage("Field " + FIELD_drugname + " cannot be longer than: " + 150));
		}

		if ((this.alternate != null) && (this.alternate.length() > 150)) {
			list.add(new ValidationMessage("Field " + FIELD_alternate + " cannot be longer than: " + 150));
		}

		if ((this.manufacturer != null) && (this.manufacturer.length() > 150)) {
			list.add(new ValidationMessage("Field " + FIELD_manufacturer + " cannot be longer than: " + 150));
		}

		if ((this.potency != null) && (this.potency.length() > 45)) {
			list.add(new ValidationMessage("Field " + FIELD_potency + " cannot be longer than: " + 45));
		}

		if ((this.drugtype != null) && (this.drugtype.length() > 150)) {
			list.add(new ValidationMessage("Field " + FIELD_drugtype + " cannot be longer than: " + 150));
		}

		if ((this.injectable != null) && (this.injectable.length() > 150)) {
			list.add(new ValidationMessage("Field " + FIELD_injectable + " cannot be longer than: " + 150));
		}

		if ((this.drugform != null) && (this.drugform.length() > 255)) {
			list.add(new ValidationMessage("Field " + FIELD_drugform + " cannot be longer than: " + 255));
		}

		if (this.isFromDB()) {
			if ((this.createdBy != null) && (this.createdBy.length() > 45)) {
				list.add(new ValidationMessage("Field " + FIELD_createdBy + " cannot be longer than: " + 45));
			}
		}
		if (this.isFromDB()) {
			if ((this.lastUpdatedBy != null) && (this.lastUpdatedBy.length() > 255)) {
				list.add(new ValidationMessage("Field " + FIELD_lastUpdatedBy + " cannot be longer than: " + 255));
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
		str.append("inventoryId = " + inventoryId + "\n");
		;
		str.append("pharmacyId = " + pharmacyId + "\n");
		str.append("drugname = " + drugname + "\n");
		str.append("alternate = " + alternate + "\n");
		str.append("manufacturer = " + manufacturer + "\n");
		str.append("mfgdate = " + mfgdate + "\n");
		str.append("expdate = " + expdate + "\n");
		str.append("price = " + price + "\n");
		str.append("potency = " + potency + "\n");
		str.append("drugtype = " + drugtype + "\n");
		str.append("injectable = " + injectable + "\n");
		str.append("drugform = " + drugform + "\n");
		str.append("quantityavailable = " + quantityavailable + "\n");
		str.append("avillabe = " + avillabe + "\n");
		str.append("active = " + active + "\n");
		str.append("prescriptionrequired = " + prescriptionrequired + "\n");
		str.append("createdBy = " + createdBy + "\n");
		str.append("createdDate = " + createdDate + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull() {
		return (inventoryId == null);
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField() {
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_inventoryId, getInventoryId()));
		return list;
	}

	@Transient
	@Override
	public Long getId() {
		return getInventoryId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Inventory inventory = new Inventory();
		inventory.setFromDB(false);
		inventory.setPharmacyId(getPharmacyId());
		inventory.setDrugname(getDrugname());
		inventory.setAlternate(getAlternate());
		inventory.setManufacturer(getManufacturer());
		inventory.setMfgdate(getMfgdate());
		inventory.setExpdate(getExpdate());
		inventory.setPrice(getPrice());
		inventory.setPotency(getPotency());
		inventory.setDrugtype(getDrugtype());
		inventory.setInjectable(getInjectable());
		inventory.setDrugform(getDrugform());
		inventory.setQuantityavailable(getQuantityavailable());
		inventory.setAvillabe(getAvillabe());
		inventory.setActive(getActive());
		inventory.setPrescriptionrequired(getPrescriptionrequired());
		inventory.setCreatedDate(getCreatedDate());
		// afterClone(inventory);
		return inventory;
	}
}