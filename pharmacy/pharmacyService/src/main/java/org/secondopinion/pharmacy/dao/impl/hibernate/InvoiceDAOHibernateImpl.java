package org.secondopinion.pharmacy.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.secondopinion.enums.InvoiceStatusEnum;
import org.secondopinion.pharmacy.domain.BaseInvoice;
import org.secondopinion.pharmacy.dto.Invoice;
import org.secondopinion.pharmacy.dto.InvoicesSerchDTO;
import org.secondopinion.request.Response;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Repository
public class InvoiceDAOHibernateImpl extends BaseInvoiceDAOHibernate {

	@Override
	@Transactional(readOnly = true)
	public List<Invoice> readByInvoiceForFillRequest(Long prescriptionFillRequestId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Invoice.FIELD_prescriptionFillRequestId, prescriptionFillRequestId));
		criterions.add(Restrictions.eq(Invoice.FIELD_active, 'Y'));

		return findByCrieria(criterions);
	}

	@Override
	@Transactional(readOnly = true)
	public Invoice readByInvoiceId(Long invoiceId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Invoice.FIELD_invoiceId, invoiceId));
		criterions.add(Restrictions.eq(Invoice.FIELD_active, 'Y'));
		List<Invoice> invoices = findByCrieria(criterions);
		if (CollectionUtils.isEmpty(invoices)) {
			return null;
		}
		return invoices.get(0);
	}

	@Override
	@Transactional
	public void save(Invoice invoice) {
		if (Objects.isNull(invoice.getInvoiceId())) {
			invoice.setActive('Y');
		}
		super.save(invoice);
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<Invoice>> getInvoicesByStatus(Long pharmacyId, InvoiceStatusEnum invoiceStatusEnum,
			Integer pageNum, Integer maxResults) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Invoice.FIELD_pharmacyaddressId, pharmacyId));
		criterions.add(Restrictions.eq(Invoice.FIELD_invoicestatus, invoiceStatusEnum.name()));
		return findByCrieria(criterions, buildOrders(), pageNum, maxResults);

	}

	private List<Order> buildOrders() {
		List<Order> orders = new ArrayList<>();
		orders.add(Order.desc(Invoice.FIELD_lastUpdatedTs));

		return orders;
	}
	@Override
	@Transactional
	public List<Invoice> findShipeeingDetail(Long invoiceId, Long patientId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Invoice.FIELD_invoiceId, invoiceId));
		criterions.add(Restrictions.eq(Invoice.FIELD_active, 'Y'));
		criterions.add(Restrictions.eq(Invoice.FIELD_patientid, patientId));
		List<Invoice> invoices = findByCrieria(criterions);
		return invoices;
	}

	@Override
	@Transactional
	public Response<List<Invoice>> getinvoiceForSerchcritiria(InvoicesSerchDTO invoicesSerchDTO) {
		List<Criterion> criterions = new ArrayList<>();

		if (Objects.nonNull(invoicesSerchDTO.getPharmacyaddressId())) {
			criterions
					.add(Restrictions.eq(BaseInvoice.FIELD_pharmacyaddressId, invoicesSerchDTO.getPharmacyaddressId()));
		}

		if (Objects.nonNull(invoicesSerchDTO.getPatientid())) {
			criterions.add(Restrictions.eq(BaseInvoice.FIELD_patientid, invoicesSerchDTO.getPatientid()));
		}
		if (Objects.nonNull(invoicesSerchDTO.getPharmacyname())) {
			criterions.add(Restrictions.eq(BaseInvoice.FIELD_pharmacyname, invoicesSerchDTO.getPharmacyname()));
		}
		if (Objects.nonNull(invoicesSerchDTO.getInvoiceId())) {
			criterions.add(Restrictions.eq(BaseInvoice.FIELD_invoiceId, invoicesSerchDTO.getInvoiceId()));
		}
		if (Objects.nonNull(invoicesSerchDTO.getInvoicestatus())) {
			criterions.add(Restrictions.eq(BaseInvoice.FIELD_invoicestatus, invoicesSerchDTO.getInvoicestatus()));
		}
		if (Objects.nonNull(invoicesSerchDTO.getPatientid())) {
			criterions.add(Restrictions.eq(BaseInvoice.FIELD_patientid, invoicesSerchDTO.getPatientid()));
		}
		criterions.add(Restrictions.eq(BaseInvoice.FIELD_active, 'Y'));
		return findByCrieria(criterions, null, invoicesSerchDTO.getPageNum(), invoicesSerchDTO.getMaxResults());
	}

	@Override
	@Transactional
	public Double findTotelRevenu(Long pharmacyaddressId) {
		String aliasName = "totalPatients";
		String sqlQuery = "select  sum(" + BaseInvoice.FIELD_total + ") " + aliasName + " from invoice "
				+ " where " + BaseInvoice.FIELD_pharmacyaddressId + " = " + pharmacyaddressId;

		Double totalRevenue = 0.0d;
		Map<String, Type> scalarMapping = new HashMap<>();
		scalarMapping.put(aliasName, StandardBasicTypes.DOUBLE);
		List<Map<String, Object>> resultList = findBySqlQueryMapTransformer(sqlQuery, null, scalarMapping);
		if (!CollectionUtils.isEmpty(resultList)) {
			Map<String, Object> resultMap = resultList.get(0);
			totalRevenue = (Double) resultMap.get(aliasName);

		}
		return totalRevenue;
	}

}