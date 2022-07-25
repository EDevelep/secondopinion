package org.secondopinion.doctor.dto;

public class ProfileCompletedDTO {
	private char isAddressCompleted;
	private char isPersonalDetailComplted;
	private char isFeeDetailComplted;
	public char getIsAddressCompleted() {
		return isAddressCompleted;
	}
	public void setIsAddressCompleted(char isAddressCompleted) {
		this.isAddressCompleted = isAddressCompleted;
	}
	public char getIsPersonalDetailComplted() {
		return isPersonalDetailComplted;
	}
	public void setIsPersonalDetailComplted(char isPersonalDetailComplted) {
		this.isPersonalDetailComplted = isPersonalDetailComplted;
	}
	public char getIsFeeDetailComplted() {
		return isFeeDetailComplted;
	}
	public void setIsFeeDetailComplted(char isFeeDetailComplted) {
		this.isFeeDetailComplted = isFeeDetailComplted;
	}

}
