package org.secondopinion.patient.service.impl;

import java.util.List;
import java.util.Objects;

import org.secondopinion.patient.dao.AppointmentDAO;
import org.secondopinion.patient.dao.MedicalprescriptionDAO;
import org.secondopinion.patient.dao.PatientratingsDAO;
import org.secondopinion.patient.dto.Appointment;
import org.secondopinion.patient.dto.Medicalprescription;
import org.secondopinion.patient.dto.PatientRatingsDTO;
import org.secondopinion.patient.dto.Patientratings;
import org.secondopinion.patient.dto.Patientratings.RatingForEnum;
import org.secondopinion.patient.dto.PatientratingsSearchCriteria;
import org.secondopinion.patient.service.IPatientRating;
import org.secondopinion.patient.service.rest.DiagnosticRestAPIService;
import org.secondopinion.patient.service.rest.DoctorRestAPIService;
import org.secondopinion.patient.service.rest.PharmacyRestAPIService;
import org.secondopinion.request.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class PatientRatingServiceImpl implements IPatientRating {

	@Autowired
	private PatientratingsDAO patientratingsDAO;

	@Autowired
	private DiagnosticRestAPIService diagnosticCenterRestAPIService;

	@Autowired
	private AppointmentDAO appointmentDAO;

	@Autowired
	private MedicalprescriptionDAO medicalprescriptionDAO;

	@Autowired
	private PharmacyRestAPIService pharmacyRestAPIService;

	@Autowired
	private DoctorRestAPIService doctorRestAPIService;

	@Override
	@Transactional
	public void savePatientratings(Patientratings patientratings) {
		Patientratings dbRatings = getPatientratingFromDb(patientratings);
		if (Objects.nonNull(dbRatings)) {
			patientratings.setActive('Y');
			patientratingsDAO.save(dbRatings);
		} else {
			patientratings.setActive('Y');
			patientratingsDAO.save(patientratings);
		}

		PatientRatingsDTO patientratingsdto = new PatientRatingsDTO();
		patientratingsdto.setPatientid(patientratings.getPatientid());
		patientratingsdto.setFeedback(patientratings.getFeedback());
		patientratingsdto.setRating(patientratings.getRating());
		patientratingsdto.setRatingType(patientratings.getRatingType());
		patientratingsdto.setPatientname(patientratings.getPatientname());
		if (patientratings.getRatingFor().equals(RatingForEnum.DOCTOR_APPOINTMENT.name())) {
			Appointment appointment = appointmentDAO.findAppointmentIdById(patientratings.getReferenceId());
			if (Objects.isNull(appointment)) {
				throw new IllegalArgumentException("Appointment not found");
			}
			patientratingsdto.setDoctorid(appointment.getReferenceEntityId());
			patientratingsdto.setAppointmentid(appointment.getReferenceAppointmentId());
			doctorRestAPIService.ratingServiceForDoctor(patientratingsdto);

		}
		if (patientratings.getRatingFor().equals(RatingForEnum.PHARMACY_PRESCRIPTIONS.name())) {
			Medicalprescription medicalprescription = medicalprescriptionDAO
					.findByPrescrptionId(patientratings.getReferenceId());
			if (Objects.isNull(medicalprescription)) {
				throw new IllegalArgumentException("medicalprescription not found");
			}
			//patientratingsdto.setPharmacyaddressId(medicalprescription.getPharmacyaddressId());
			pharmacyRestAPIService.ratingServiceForPharmecy(patientratings);
		}
		if (patientratings.getRatingFor().equals(RatingForEnum.DIAGNOSTIC_CENTER_APPOINTMENT.name())) {
			Appointment appointment = appointmentDAO.findAppointmentIdById(patientratings.getReferenceId());
			if (Objects.isNull(appointment)) {
				throw new IllegalArgumentException("Appointment not found");
			}
			patientratingsdto.setDiagnosticcenterId(appointment.getReferenceEntityId());
			patientratingsdto.setAppointmentid(appointment.getReferenceAppointmentId());
			diagnosticCenterRestAPIService.ratingSericeForDiagnosticCenter(patientratingsdto);
		}
	}

	private Patientratings getPatientratingFromDb(Patientratings ratings) {
		Patientratings dbRatings = null;
		PatientratingsSearchCriteria patientratingsSearchCriteria = new PatientratingsSearchCriteria();
		patientratingsSearchCriteria.setRatingForEnum(RatingForEnum.valueOf(ratings.getRatingFor()));
		patientratingsSearchCriteria.setReferenceId(ratings.getReferenceId());

		Response<List<Patientratings>> doctorRatings = patientratingsDAO
				.getRatingsByCriteria(patientratingsSearchCriteria);
		List<Patientratings> doctorRatingsList = doctorRatings.getData();
		if (!CollectionUtils.isEmpty(doctorRatingsList)) {
			dbRatings = doctorRatingsList.get(0);
			dbRatings.setRating(ratings.getRating());
			dbRatings.setFeedback(ratings.getFeedback());
		}
		return dbRatings;
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<Patientratings>> getRatingsByCriteria(PatientratingsSearchCriteria ratings) {

		return patientratingsDAO.getRatingsByCriteria(ratings);
	}

	@Override
	@Transactional
	public List<Patientratings> getRatingsBydoctorIds(List<Long> doctorIds) {

		return patientratingsDAO.getRatingsBydoctorIds(doctorIds);
	}

}
