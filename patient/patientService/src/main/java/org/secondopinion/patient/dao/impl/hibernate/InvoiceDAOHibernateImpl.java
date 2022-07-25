package org.secondopinion.patient.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.enums.InvoiceStatusEnum;
import org.secondopinion.enums.InvoiceTypeEnum;
import org.secondopinion.patient.dto.Invoice;
import org.secondopinion.patient.dto.InvoiceSearchDTO;
import org.secondopinion.request.Response;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class InvoiceDAOHibernateImpl extends BaseInvoiceDAOHibernate {

	@Override
	@Transactional(readOnly = true)
	public Response<List<Invoice>> getinvoiceForSerchcritiria(InvoiceSearchDTO invoicesSerchDTO) {
		List<Criterion> criterions = new ArrayList<>();
		boolean isInvoiceTypeAdded = false;
		if (Objects.isNull(invoicesSerchDTO.getPatientid())) {
			throw new IllegalArgumentException("patient id can not be null");
		}
		criterions.add(Restrictions.eq(Invoice.FIELD_patientid, invoicesSerchDTO.getPatientid()));
		if (Objects.nonNull(invoicesSerchDTO.getPatientInvoiceId())) {
			criterions.add(Restrictions.eq(Invoice.FIELD_invoiceId, invoicesSerchDTO.getPatientInvoiceId()));
		}
		if (Objects.nonNull(invoicesSerchDTO.getInvoiceStatusEnum())) {
			criterions
					.add(Restrictions.eq(Invoice.FIELD_invoicestatus, invoicesSerchDTO.getInvoiceStatusEnum().name()));
		}
		if (Objects.nonNull(invoicesSerchDTO.getInvoiceTypeEnum())) {
			isInvoiceTypeAdded = addInvoiceType(criterions, invoicesSerchDTO.getInvoiceTypeEnum(), isInvoiceTypeAdded);
		}
		// DOCTOR
		if (Objects.nonNull(invoicesSerchDTO.getDoctorName())) {
			isInvoiceTypeAdded = addInvoiceType(criterions, InvoiceTypeEnum.DOCTOR, isInvoiceTypeAdded);
			criterions.add(Restrictions.ilike(Invoice.FIELD_invoiceentityname, invoicesSerchDTO.getDoctorName(),
					MatchMode.ANYWHERE));
		}
		if (Objects.nonNull(invoicesSerchDTO.getDoctorId())) {
			isInvoiceTypeAdded = addInvoiceType(criterions, InvoiceTypeEnum.DOCTOR, isInvoiceTypeAdded);
			criterions.add(Restrictions.eq(Invoice.FIELD_invoiceentityid, invoicesSerchDTO.getDoctorId()));
		}
		if (Objects.nonNull(invoicesSerchDTO.getDoctorAppointmentId())) {
			isInvoiceTypeAdded = addInvoiceType(criterions, InvoiceTypeEnum.DOCTOR, isInvoiceTypeAdded);
			criterions.add(
					Restrictions.eq(Invoice.FIELD_invoiceentityreferenceid, invoicesSerchDTO.getDoctorAppointmentId()));
		}
		if (Objects.nonNull(invoicesSerchDTO.getPatientAppointmentId())) {
			isInvoiceTypeAdded = addInvoiceType(criterions, InvoiceTypeEnum.DOCTOR, isInvoiceTypeAdded);
			criterions
					.add(Restrictions.eq(Invoice.FIELD_invoicereferenceid, invoicesSerchDTO.getPatientAppointmentId()));
		}
		// PHARMACY
		if (Objects.nonNull(invoicesSerchDTO.getPharmacyName())) {
			isInvoiceTypeAdded = addInvoiceType(criterions, InvoiceTypeEnum.PHARMACY, isInvoiceTypeAdded);
			criterions.add(Restrictions.ilike(Invoice.FIELD_invoiceentityname, invoicesSerchDTO.getPharmacyName(),
					MatchMode.ANYWHERE));
		}
		if (Objects.nonNull(invoicesSerchDTO.getPharmacyaddressId())) {
			isInvoiceTypeAdded = addInvoiceType(criterions, InvoiceTypeEnum.PHARMACY, isInvoiceTypeAdded);
			criterions.add(Restrictions.eq(Invoice.FIELD_invoiceentityid, invoicesSerchDTO.getPharmacyaddressId()));
		}
		if (Objects.nonNull(invoicesSerchDTO.getMedicalPrescriptionId())) {
			isInvoiceTypeAdded = addInvoiceType(criterions, InvoiceTypeEnum.PHARMACY, isInvoiceTypeAdded);
			criterions.add(
					Restrictions.eq(Invoice.FIELD_invoicereferenceid, invoicesSerchDTO.getMedicalPrescriptionId()));
		}
		if (Objects.nonNull(invoicesSerchDTO.getPrescriptionFillRequestId())) {
			addInvoiceType(criterions, InvoiceTypeEnum.PHARMACY, isInvoiceTypeAdded);
			criterions.add(Restrictions.eq(Invoice.FIELD_invoiceentityreferenceid,
					invoicesSerchDTO.getPrescriptionFillRequestId()));
		}
		criterions.add(Restrictions.eq(Invoice.FIELD_active, 'Y'));

		return findByCrieria(criterions, null, invoicesSerchDTO.getPageNum(), invoicesSerchDTO.getMaxResults());
	}

	private boolean addInvoiceType(List<Criterion> criterions, InvoiceTypeEnum invoiceTypeEnum,
			boolean isInvoiceTypeAdded) {
		if (!isInvoiceTypeAdded) {
			criterions.add(Restrictions.eq(Invoice.FIELD_invoicetype, invoiceTypeEnum.name()));
			isInvoiceTypeAdded = true;
		}
		return isInvoiceTypeAdded;

	}

	@Override
	@Transactional(readOnly = true)
	public List<Invoice> findInvoiceByuserId(Long userId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Invoice.FIELD_patientid, userId));
		criterions.add(Restrictions.eq(Invoice.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Invoice> getByTypeAndEntityInvoiceIdAndStatus(String invoicetype, Long entityInvoiceId,
			String invoicestatus) {
		List<Criterion> criterions = new ArrayList<>();

		criterions.add(Restrictions.eq(Invoice.FIELD_invoicetype, invoicetype));
		criterions.add(Restrictions.eq(Invoice.FIELD_entityInvoiceId, entityInvoiceId));
		criterions.add(Restrictions.eq(Invoice.FIELD_invoicestatus, invoicestatus));
		return findByCrieria(criterions);
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
	public Response<List<Invoice>> getRequestPayments(Long forUserId, Integer pageNum, Integer maxResults) {
		List<Criterion> criterions = new ArrayList<>();

		criterions.add(Restrictions.eq(Invoice.FIELD_patientid, forUserId));
		criterions.add(Restrictions.eq(Invoice.FIELD_invoicestatus, InvoiceStatusEnum.REQUEST_PAYMENT.name()));
		return findByCrieria(criterions, null, pageNum, maxResults);
	}

	@Override
	public Response<List<Invoice>> getRequestPaymentDone(Long forUserId, String invoicetype, Integer pageNum,
			Integer maxResults) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Invoice.FIELD_patientid, forUserId));
		criterions.add(Restrictions.eq(Invoice.FIELD_invoicetype, invoicetype));
		criterions.add(Restrictions.eq(Invoice.FIELD_invoicestatus, InvoiceStatusEnum.PAYMENT_DONE.name()));
		return findByCrieria(criterions, null, pageNum, maxResults);
	}

	@Override
	@Transactional
	public Invoice findInvoiceByentityInvoiceId(String invoicetype, Long entityInvoiceId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Invoice.FIELD_entityInvoiceId, entityInvoiceId));
		criterions.add(Restrictions.eq(Invoice.FIELD_invoicetype, invoicetype));
		List<Invoice> invoices = findByCrieria(criterions);
		return invoices.get(0);
	}

	@Override
	@Transactional
	public Response<List<Invoice>> getRequestPaymentsForAll(Long forUserId, Integer pageNum, Integer maxResults) {

		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Invoice.FIELD_patientid, forUserId));
		criterions.add(Restrictions.eq(Invoice.FIELD_invoicetype, InvoiceTypeEnum.PHARMACY.name()));
		criterions.add(Restrictions.eq(Invoice.FIELD_invoicestatus, InvoiceStatusEnum.PAYMENT_DONE.name()));
		return findByCrieria(criterions, null, pageNum, maxResults);
	}

	@Override
	public Response<List<Invoice>> getRequestPaymentsForAllDeliverd(Long patientId, Integer pageNum,
			Integer maxResult) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Invoice.FIELD_patientid, patientId));
		criterions.add(Restrictions.eq(Invoice.FIELD_invoicetype, InvoiceTypeEnum.PHARMACY.name()));
		criterions.add(Restrictions.eq(Invoice.FIELD_invoicestatus, InvoiceStatusEnum.DELIVERED.name()));
		return findByCrieria(criterions, null, pageNum, maxResult);
	}

}