package org.secondopinion.patient.dto;

import java.util.Collection;

public class PrescriptionAllRespnceDTO {
private Collection<MedicalprescriptionDTO> medicalprescriptionDTOs;
private  Collection<MedicalprescriptionTestDTO> medicalprescriptionTestDTOs;
public Collection<MedicalprescriptionDTO> getMedicalprescriptionDTOs() {
	return medicalprescriptionDTOs;
}
public void setMedicalprescriptionDTOs(Collection<MedicalprescriptionDTO> medicalprescriptionDTOs) {
	this.medicalprescriptionDTOs = medicalprescriptionDTOs;
}
public Collection<MedicalprescriptionTestDTO> getMedicalprescriptionTestDTOs() {
	return medicalprescriptionTestDTOs;
}
public void setMedicalprescriptionTestDTOs(Collection<MedicalprescriptionTestDTO> medicalprescriptionTestDTOs) {
	this.medicalprescriptionTestDTOs = medicalprescriptionTestDTOs;
}
}
