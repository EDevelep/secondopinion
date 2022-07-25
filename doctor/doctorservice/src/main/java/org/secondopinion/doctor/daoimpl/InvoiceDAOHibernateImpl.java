package org.secondopinion.doctor.daoimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.secondopinion.doctor.domain.BaseInvoice;
import org.secondopinion.doctor.dto.Invoice;
import org.secondopinion.doctor.dto.InvoicesSerchDTO;
import org.secondopinion.request.Response;
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
			if (Objects.nonNull(invoicesSerchDTO.getDoctorid())) {
				criterions.add(Restrictions.eq(BaseInvoice.FIELD_doctorid, invoicesSerchDTO.getDoctorid()));
			}
			if (Objects.nonNull(invoicesSerchDTO.getPatientappointmentid())) {
				criterions.add(
						Restrictions.eq(BaseInvoice.FIELD_patientappointmentid, invoicesSerchDTO.getPatientappointmentid()));
			}
			if (Objects.nonNull(invoicesSerchDTO.getDoctorappointmentid())) {
				criterions
						.add(Restrictions.eq(BaseInvoice.FIELD_doctorappointmentid, invoicesSerchDTO.getDoctorappointmentid()));
			}
			if (Objects.nonNull(invoicesSerchDTO.getAmount())) {
				criterions.add(Restrictions.eq(BaseInvoice.FIELD_amount, invoicesSerchDTO.getAmount()));
			}
			if (Objects.nonNull(invoicesSerchDTO.getPatientname())) {
				criterions.add(Restrictions.eq(BaseInvoice.FIELD_patientname, invoicesSerchDTO.getPatientname()));
			}
			if (Objects.nonNull(invoicesSerchDTO.getDoctorname())) {
				criterions.add(Restrictions.eq(BaseInvoice.FIELD_doctorname, invoicesSerchDTO.getDoctorname()));
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
	public Double getTotalRevenue(Long doctorid, String invoiceStatus) {
		String aliasName = "totalPatients";
		String sqlQuery = "select distinct sum(" + BaseInvoice.FIELD_amount + ") " + aliasName + " from invoice "
				+ " where " 
				+ BaseInvoice.FIELD_doctorid + " = " + doctorid 
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