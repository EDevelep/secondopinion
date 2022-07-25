package org.secondopinion.pharmacy.dto;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.secondopinion.pharmacy.domain.BasePrescriptionprice;

@Entity
@Table(name = "prescriptionprice")
public class Prescriptionprice extends BasePrescriptionprice {

	public static Prescriptionprice buildPrescriptionprice(Medication n, Long patientAppointmentId) {

		Prescriptionprice prescriptionprice = new Prescriptionprice();

		prescriptionprice.setPatientAppointmentId(patientAppointmentId);
		prescriptionprice.setDosage(n.getMedicineUsage());
		prescriptionprice.setPower(n.getPower());
		prescriptionprice.setMedicine(n.getMedicineName());
		prescriptionprice.setMedicalPrescriptionId(n.getMedicalPrescriptionId());
		prescriptionprice.setPatientId(n.getPatientId());
		prescriptionprice.setType(n.getType());
		prescriptionprice.setNumberOfDays(n.getNumberOfDays());
		prescriptionprice.setQuantity(Objects.isNull(n.getQuantity()) ? 0 : n.getQuantity());
		prescriptionprice.setUnitPrice(0.0d);
		prescriptionprice.setCgst(0.0d);
		prescriptionprice.setSgst(0.0d);
		prescriptionprice.setActive('Y');
		prescriptionprice.setTotalPrice(prescriptionprice.getQuantity() * prescriptionprice.getUnitPrice());

		return prescriptionprice;
	}

	public static void buildForUpdate(Prescriptionprice dbPrescriptionPrice, PrescriptionPriceUpdateDTO pp) {
		dbPrescriptionPrice.setQuantity(pp.getQuantity());
		dbPrescriptionPrice.setCgst(pp.getCgst());
		dbPrescriptionPrice.setSgst(pp.getSgst());
		dbPrescriptionPrice.setUnitPrice(pp.getUnitPrice());
		dbPrescriptionPrice.setDiscount(pp.getDiscount());
		Double totalPrice = (Double.valueOf(pp.getQuantity()) * pp.getUnitPrice()) + pp.getCgst() + pp.getCgst();
		dbPrescriptionPrice.setTotalPrice(totalPrice);
	}

	public static Prescriptionprice buildPrescriptionprice(PrescriptionPriceUpdateDTO pp,
			Prescriptionfillrequest dbprescriptionfillrequest) {

		Prescriptionprice prescriptionprice = new Prescriptionprice();
		prescriptionprice.setPatientAppointmentId(dbprescriptionfillrequest.getPatientAppointmentId());
		prescriptionprice.setDosage(pp.getDosage());
		prescriptionprice.setPower(pp.getPower());
		prescriptionprice.setPrescriptionFillRequestId(dbprescriptionfillrequest.getPrescriptionFillRequestId());
		prescriptionprice.setMedicine(pp.getMedicine());
		prescriptionprice.setMedicalPrescriptionId(pp.getMedicalPrescriptionId());
		prescriptionprice.setPatientId(pp.getPatientId());
		prescriptionprice.setType(pp.getType());
		prescriptionprice.setNumberOfDays(pp.getNumberOfDays());
		prescriptionprice.setQuantity(Objects.isNull(pp.getQuantity()) ? 0 : pp.getQuantity());
		prescriptionprice.setUnitPrice(pp.getUnitPrice());
		prescriptionprice.setCgst(pp.getCgst());
		prescriptionprice.setSgst(pp.getSgst());
		prescriptionprice.setDiscount(pp.getDiscount());
		prescriptionprice.setActive('Y');
		prescriptionprice.setTotalPrice(pp.getQuantity() * pp.getUnitPrice());

		return prescriptionprice;
		
	}

}