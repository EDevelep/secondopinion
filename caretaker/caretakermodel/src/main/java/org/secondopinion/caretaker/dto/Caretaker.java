package org.secondopinion.caretaker.dto;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.secondopinion.utils.DateUtil;
import org.secondopinion.utils.StringUtil;
import org.secondopinioncaretaker.domain.BaseCaretaker;

@SuppressWarnings({ "serial" })
@Entity
@Table(name = "caretaker")
public class Caretaker extends BaseCaretaker {

	private Personaldetail personaldetail;

	@Transient
	public String getFullName() {

		return getFirstName() + " " + getLastName();
	}

	public void markInActive() {
		setActive('N');
	}

	public void markAsActive() {
		setActive('Y');
		setActivatedDate(DateUtil.getDate());

	}

	public static void ValidateCareTaker(Caretaker caretaker) {
		if (StringUtil.isNullOrEmpty(caretaker.getEmailId()) || StringUtil.isNullOrEmpty(caretaker.getPassword())
				|| StringUtil.isNullOrEmpty(caretaker.getCellNumber())) {
			throw new IllegalArgumentException(" Please provide valid [" + BaseCaretaker.FIELD_emailId + ","
					+ BaseCaretaker.FIELD_password + BaseCaretaker.FIELD_cellNumber + "]");
		}

	}

	@Transient
	public Personaldetail getPersonaldetail() {
		return personaldetail;
	}

	public void setPersonaldetail(Personaldetail personaldetail) {
		this.personaldetail = personaldetail;
	}

	public static Caretaker buildUpdateCaretaker(Caretaker dbcaretaker, Caretaker caretaker) {

		dbcaretaker.setMiddleName(caretaker.getMiddleName());
		dbcaretaker.setHomeNumber(caretaker.getHomeNumber());
		dbcaretaker.setTotalExperience(caretaker.getTotalExperience());
		dbcaretaker.setFirstName(caretaker.getFirstName());
		dbcaretaker.setLastName(caretaker.getLastName());
		return dbcaretaker;
	}

}