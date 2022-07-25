package org.secondopinion.diagnosticcenter.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.secondopinion.diagnosticcenter.domain.BaseInvoice;
import org.secondopinion.diagnosticcenter.dto.Invoice;
import org.secondopinion.diagnosticcenter.dto.InvoicesSerchDTO;

import org.secondopinion.request.Response;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Repository
public class InvoiceDAOHibernateImpl extends BaseInvoiceDAOHibernate{
	
	
	
	@Override
	@Transactional
   public void save(Invoice invoice) {
	   if(Objects.isNull(invoice.getInvoiceId())) {
		   invoice.setActive('Y');
	   }
	   super.save(invoice);
   }

	@Override
	@Transactional(readOnly=true)
	public Response<List<Invoice>> getInvoicesByStatus(Long diagnosticcenterId,
			Integer pageNum, Integer maxResults) {
		List<Criterion> criterions = new ArrayList<>();
		
		if(Objects.isNull(diagnosticcenterId)) {
			throw new IllegalArgumentException("diagnosticcenterId can not be null.");
		}
		criterions.add(Restrictions.eq(Invoice.FIELD_diagnosticCenterAddressId, diagnosticcenterId));
		
		return findByCrieria(criterions, Arrays.asList(Order.desc(Invoice.FIELD_invoiceId)), pageNum, maxResults);
	}

	@Override
	@Transactional(readOnly=true)
	public Double getTotalRevenue(Long diagnosticCenterAddressid, String invoiceStatus) {
		String aliasName = "totalPatients";
		String sqlQuery = "select distinct sum(" + Invoice.FIELD_amount + ") " + aliasName + " from invoice "
				+ " where " 
				+ Invoice.FIELD_diagnosticCenterAddressId + " = " + diagnosticCenterAddressid 
				+ " and " 
				+ Invoice.FIELD_invoicestatus + " = '"+ invoiceStatus + "'";
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
	public Response<List<Invoice>> getinvoiceForSerchcritiria(InvoicesSerchDTO invoicesSerchDTO) {
		List<Criterion> criterions = new ArrayList<>();

		if (Objects.nonNull(invoicesSerchDTO.getDiagnosticCenterAddressId())) {
			criterions.add(Restrictions.eq(BaseInvoice.FIELD_diagnosticCenterAddressId, invoicesSerchDTO.getDiagnosticCenterAddressId()));
		}
		
		if (Objects.nonNull(invoicesSerchDTO.getDiagnosticCenterAppointmentId())) {
			criterions.add(Restrictions.eq(BaseInvoice.FIELD_diagnosticCenterAppointmentId, invoicesSerchDTO.getDiagnosticCenterAppointmentId()));
		}
		if (Objects.nonNull(invoicesSerchDTO.getDiagnosticCenterName())) {
			criterions.add(Restrictions.eq(BaseInvoice.FIELD_diagnosticCenterName, invoicesSerchDTO.getDiagnosticCenterName()));
		}
		if (Objects.nonNull(invoicesSerchDTO.getInvoiceId())) {
			criterions.add(Restrictions.eq(BaseInvoice.FIELD_invoiceId,invoicesSerchDTO.getInvoiceId()));
		}
		if (Objects.nonNull(invoicesSerchDTO.getInvoicestatus())) {
			criterions.add(Restrictions.eq(BaseInvoice.FIELD_diagnosticCenterAddressId,invoicesSerchDTO.getInvoicestatus()));
		}
		if (Objects.nonNull(invoicesSerchDTO.getInvoiceDate())) {
			criterions.add(Restrictions.eq(BaseInvoice.FIELD_invoiceDate, invoicesSerchDTO.getInvoiceDate()));
		}
		criterions.add(Restrictions.eq(BaseInvoice.FIELD_active, 'Y'));
		return findByCrieria(criterions, null, invoicesSerchDTO.getPageNum(),
				invoicesSerchDTO.getMaxResults());
	}
}