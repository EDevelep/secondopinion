package org.secondopinion.patient.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.dao.exception.DataAccessException;
import org.secondopinion.patient.dto.Carddetails;
import org.secondopinion.patient.dto.PatientPaymentDetails;
import org.secondopinion.utils.AES;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Repository
public class PatientPaymentDetailsDAOHibernateImpl extends BasePatientPaymentDetailsDAOHibernate{


	@Override
	@Transactional
	public void save(  PatientPaymentDetails patientPaymentDetails) throws DataAccessException {
		if(Objects.isNull(patientPaymentDetails.getPatientPaymentDetailsId())) {
			patientPaymentDetails.setActive('Y');
		}
	//	Carddetails carddetails=patientPaymentDetails.getCarddetails();
		try {
			//carddetails.setCardnumber(AES.encrypt(carddetails.getCardnumber()));
		} catch (Exception e) {
		}
	//	patientPaymentDetails.setCarddetails(carddetails);
		super.save(patientPaymentDetails);
	}
	
	
	@Override
	@Transactional(readOnly=true)
	public List<PatientPaymentDetails> getByPatientAndAppointment(Long patientId, Long appointmentId) {
		List<Criterion> criterions = new ArrayList<>();
		if(Objects.nonNull(patientId)) {
			criterions.add(Restrictions.eq(PatientPaymentDetails.FIELD_patientId, patientId));
		} 
		if(Objects.nonNull(appointmentId)) {
			criterions.add(Restrictions.eq(PatientPaymentDetails.FIELD_appointmentId, appointmentId));
		}
		criterions.add(Restrictions.eq(PatientPaymentDetails.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}

	@Override
	@Transactional(readOnly=true)
	public PatientPaymentDetails findPatientPaymentDetailsById(Long patientPaymentDetailsId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(PatientPaymentDetails.FIELD_patientPaymentDetailsId, patientPaymentDetailsId));
		criterions.add(Restrictions.eq(PatientPaymentDetails.FIELD_active, 'Y'));
		List<PatientPaymentDetails> patientPaymentDetails=findByCrieria(criterions);
		if(CollectionUtils.isEmpty(patientPaymentDetails)) return null;
		return patientPaymentDetails.get(0);
	}


	@Override
	@Transactional(readOnly=true)
	public PatientPaymentDetails getByTransactionId(Long patientId, String transactionId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(PatientPaymentDetails.FIELD_patientId, patientId));
		criterions.add(Restrictions.eq(PatientPaymentDetails.FIELD_transactionId, transactionId));
		criterions.add(Restrictions.eq(PatientPaymentDetails.FIELD_active, 'Y'));
		List<PatientPaymentDetails> patientPaymentDetails=findByCrieria(criterions);
		if(CollectionUtils.isEmpty(patientPaymentDetails)) return null;
		return patientPaymentDetails.get(0);
	}
}