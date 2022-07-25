package org.secondopinion.caretaker.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.secondopinion.caretaker.dto.Invoice;
import org.secondopinion.caretaker.dto.InvoicesSerchDTO;
import org.secondopinion.request.Response;
import org.secondopinioncaretaker.domain.BaseInvoice;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
@Repository
public class InvoiceDAOHibernateImpl extends BaseInvoiceDAOHibernate {

	@Override
	@Transactional(readOnly = true)
	public Response<List<Invoice>>  getinvoiceForSerchcritiria(InvoicesSerchDTO invoicesSerchDTO) {
			List<Criterion> criterions = new ArrayList<>();

			if (Objects.nonNull(invoicesSerchDTO.getPatientid())) {
				criterions.add(Restrictions.eq(BaseInvoice.FIELD_patientid, invoicesSerchDTO.getPatientid()));
			}
			if (Objects.nonNull(invoicesSerchDTO.getCaretkerid())) {
				criterions.add(Restrictions.eq(BaseInvoice.FIELD_caretekerId, invoicesSerchDTO.getCaretkerid()));
			}
			if (Objects.nonNull(invoicesSerchDTO.getPatientappointmentid())) {
				criterions.add(
						Restrictions.eq(BaseInvoice.FIELD_patientappointmentid, invoicesSerchDTO.getPatientappointmentid()));
			}
			if (Objects.nonNull(invoicesSerchDTO.getCaretakerappointmentid())) {
				criterions
						.add(Restrictions.eq(BaseInvoice.FIELD_caretakerappointmentid, invoicesSerchDTO.getCaretakerappointmentid()));
			}
			if (Objects.nonNull(invoicesSerchDTO.getAmount())) {
				criterions.add(Restrictions.eq(BaseInvoice.FIELD_amount, invoicesSerchDTO.getAmount()));
			}
			if (Objects.nonNull(invoicesSerchDTO.getPatientname())) {
				criterions.add(Restrictions.eq(BaseInvoice.FIELD_patientname, invoicesSerchDTO.getPatientname()));
			}
			if (Objects.nonNull(invoicesSerchDTO.getCaretakername())) {
				criterions.add(Restrictions.eq(BaseInvoice.FIELD_caretakername, invoicesSerchDTO.getCaretakername()));
			}
			if (Objects.nonNull(invoicesSerchDTO.getPaymentReferenceId())) {
				criterions.add(Restrictions.eq(BaseInvoice.FIELD_paymentReferenceId, invoicesSerchDTO.getPaymentReferenceId()));
			}
			criterions.add(Restrictions.eq(BaseInvoice.FIELD_active, 'Y'));

	return findByCrieria(criterions, null, invoicesSerchDTO.getPageNum(),
					invoicesSerchDTO.getMaxResults());

		
	 }
	
	@Override
	@Transactional(readOnly=true)
	public Double getTotalRevenue(Long caretekerId, String invoiceStatus) {
		String aliasName = "totalPatients";
		String sqlQuery = "select distinct sum(" + BaseInvoice.FIELD_amount + ") " + aliasName + " from invoice "
				+ " where " 
				+ BaseInvoice.FIELD_caretekerId + " = " + caretekerId 
				+ " and " 
				+ BaseInvoice.FIELD_invoicestatus + " = '"+ invoiceStatus + "'";
		Double totalRevenue = 0.0d;
		Map<String, Type> scalarMapping = new HashMap<>();
		scalarMapping.put(aliasName, StandardBasicTypes.DOUBLE);
		List<Map<String, Object>> resultList = findBySqlQueryMapTransformer(sqlQuery, null, scalarMapping);
		if(!CollectionUtils.isEmpty(resultList)) {
			Map<String, Object> resultMap = resultList.get(0);
			totalRevenue = (Double) resultMap.get(aliasName);
			
		}
		return totalRevenue;
	}
	
	@Override
	@Transactional
	public void save(Invoice invoice) {
		if(Objects.isNull(invoice.getInvoiceId())) {
			invoice.setActive('Y');
		}
		super.save(invoice);
	}
}