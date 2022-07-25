package org.secondopinion.caretaker.dto;

public class ProfileCompletedDTO {
	private char ispersonalDetailCompleted;
	private char isFeeDetailCompleted;
	private char isAddressCompleted;

	public char getIspersonalDetailCompleted() {
		return ispersonalDetailCompleted;
	}

	public void setIspersonalDetailCompleted(char ispersonalDetailCompleted) {
		this.ispersonalDetailCompleted = ispersonalDetailCompleted;
	}

	public char getIsFeeDetailCompleted() {
		return isFeeDetailCompleted;
	}

	public void setIsFeeDetailCompleted(char isFeeDetailCompleted) {
		this.isFeeDetailCompleted = isFeeDetailCompleted;
	}

	public char getIsAddressCompleted() {
		return isAddressCompleted;
	}

	public void setIsAddressCompleted(char isAddressCompleted) {
		this.isAddressCompleted = isAddressCompleted;
	}
}
