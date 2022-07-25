package org.secondopinion.patient.dto;

import java.math.BigInteger;

public class Dose {
	private BigInteger medicationusageId;
	private boolean dosageAvailable;
	private char dosageConsumed;

	public Dose(BigInteger medicationUsageId, char dosageConsumed) {
		this.medicationusageId = medicationUsageId;
		this.dosageAvailable = true;
		this.dosageConsumed = dosageConsumed;
	}

	public char getDosageConsumed() {
		return dosageConsumed;
	}

	public void setDosageConsumed(char dosageConsumed) {
		this.dosageConsumed = dosageConsumed;
	}

	public BigInteger getMedicationusageId() {
		return medicationusageId;
	}

	public void setMedicationusageId(BigInteger medicationusageId) {
		this.medicationusageId = medicationusageId;
	}

	public void setDosageAvailable(boolean dosageAvailable) {
		this.dosageAvailable = dosageAvailable;
	}

	public boolean isDosageAvailable() {
		return dosageAvailable;
	}

	
}
