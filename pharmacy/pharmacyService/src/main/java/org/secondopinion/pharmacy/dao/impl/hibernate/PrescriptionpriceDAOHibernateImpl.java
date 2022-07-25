package org.secondopinion.pharmacy.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.pharmacy.dto.Prescriptionprice;
import org.secondopinion.request.Response;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PrescriptionpriceDAOHibernateImpl extends BasePrescriptionpriceDAOHibernate{

	@Override
	@Transactional(readOnly=true)
	public Response<List<Prescriptionprice>> getRequestedMedicinesofPrescription(Long prescriptionFillRequestId,
			Integer pageNum, Integer maxResults) {
		List<Criterion> criterions = new ArrayList<Criterion>();
		criterions.add(Restrictions.eq(Prescriptionprice.FIELD_prescriptionFillRequestId, prescriptionFillRequestId));
		return findByCrieria(criterions, null, pageNum, maxResults);
	}
	
	@Override
	@Transactional
	public void save(Prescriptionprice prescriptionprice) {
		if(Objects.isNull(prescriptionprice.getPrescriptionPriceId())) {
			prescriptionprice.setActive('Y');
		}
		super.save(prescriptionprice);
	}
}