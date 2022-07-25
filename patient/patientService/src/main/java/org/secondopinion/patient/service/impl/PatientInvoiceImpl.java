package org.secondopinion.patient.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.secondopinion.enums.InvoiceStatusEnum;
import org.secondopinion.enums.InvoiceTypeEnum;
import org.secondopinion.patient.dao.InvoiceDAO;
import org.secondopinion.patient.dao.PatientPaymentDetailsDAO;
import org.secondopinion.patient.dto.Invoice;
import org.secondopinion.patient.dto.InvoiceSearchDTO;
import org.secondopinion.patient.dto.InvoiceStatusDTO;
import org.secondopinion.patient.dto.Notificationalerts;
import org.secondopinion.patient.dto.PatientPaymentDetails;
import org.secondopinion.patient.dto.ViewInvoiceEnumRequset;
import org.secondopinion.patient.dto.Invoice.ViewInvoiceEnum;
import org.secondopinion.patient.service.INotificationalertsService;
import org.secondopinion.patient.service.IPatientInvoice;
import org.secondopinion.request.Response;
import org.secondopinion.utils.NotificationAlert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class PatientInvoiceImpl implements IPatientInvoice {

	@Autowired
	private InvoiceDAO invoicedao;

	@Autowired
	private INotificationalertsService notificationalertsService;

	@Autowired
	private PatientPaymentDetailsDAO patientPaymentDetailsDAO;

	@Override
	@Transactional
	public void savePatientInvoice(Invoice invoice) {
		invoicedao.save(invoice);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Invoice> getAllInvoiceForUser(Long userId) {
		return invoicedao.findInvoiceByuserId(userId);
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<Invoice>> getinvoiceForSerchcritiria(InvoiceSearchDTO invoicesSerchDTO) {
		return invoicedao.getinvoiceForSerchcritiria(invoicesSerchDTO);
	}

	@Override
	@Transactional
	public void updateInvoiceForPaymentByEntityInvoiceIdWithStatus(Invoice invoice) {
		List<Invoice> invoices = invoicedao.getByTypeAndEntityInvoiceIdAndStatus(invoice.getInvoicetype(),
				invoice.getEntityInvoiceId(), InvoiceStatusEnum.NEW_REQUEST.name());
		if (CollectionUtils.isEmpty(invoices)) {
			throw new IllegalArgumentException("Invoice(s) not found.");
		}
		invoices.stream().forEach(in -> {
			in.setActive('Y');
			in.setInvoicestatus(invoice.getInvoicestatus());
			in.setAmount(invoice.getAmount());
			in.setDiscount(invoice.getDiscount());
			NotificationAlert alert = new NotificationAlert(in.getPatientid(), in.getInvoiceId(),
					in.getInvoicetype() + " Invoice " + invoice.getInvoicestatus(),
					"Please pay " + in.getAmount() + " &#8377;  to " + in.getInvoicetype() + ".");
			notificationalertsService.sendNotification(alert);
		});
		invoicedao.save(invoices);

	}

	@Override
	@Transactional
	public void updateInvoiceByEntityInvoiceIdWithStatus(Invoice invoice) {
		List<Invoice> invoices = invoicedao.getByTypeAndEntityInvoiceIdAndStatus(invoice.getInvoicetype(),
				invoice.getEntityInvoiceId(), InvoiceStatusEnum.NEW_REQUEST.name());
		if (CollectionUtils.isEmpty(invoices)) {
			throw new IllegalArgumentException("Invoice(s) not found.");
		}
		invoices.stream().forEach(in -> {
			in.setActive('Y');
			in.setInvoicestatus(invoice.getInvoicestatus());
			NotificationAlert alert = new NotificationAlert(in.getPatientid(), in.getInvoiceId(),
					in.getInvoicetype() + " Invoice " + invoice.getInvoicestatus(),
					"Your order for " + in.getInvoicetype() + " is " + invoice.getInvoicestatus() + ".");
			notificationalertsService.sendNotification(alert);
		});
		invoicedao.save(invoices);

	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<Invoice>> getRequestPayments(Long forUserId, Integer pageNum, Integer maxResults) {

		return invoicedao.getRequestPayments(forUserId, pageNum, maxResults);
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<Invoice>> getRequestPaymentDone(Long forUserId, String invoicetype, Integer pageNum,
			Integer maxResults) {
		return invoicedao.getRequestPaymentDone(forUserId, invoicetype, pageNum, maxResults);
	}

	@Override
	@Transactional
	public Invoice findInvoiceByuserId(Long userId, Long invoiceId) {
		Invoice invoice = invoicedao.findOneByProperty(Invoice.FIELD_patientid, userId);
		if (Objects.isNull(invoice)) {
			throw new IllegalArgumentException("Invoice Is Not Found ");

		}
		PatientPaymentDetails patientPaymentDetails = patientPaymentDetailsDAO
				.findOneByProperty(PatientPaymentDetails.FIELD_patientId, invoice.getPatientid());
		if (Objects.isNull(patientPaymentDetails)) {
			throw new IllegalArgumentException("Invoice Is Not Found ");

		}
		invoice.setPatientPaymentDetails(patientPaymentDetails);
		return invoice;
	}

	@Override
	@Transactional
	public void updateInvoiceShippingByEntityInvoiceIdWithStatus(Invoice invoice) {
		Invoice invoices = invoicedao.findInvoiceByentityInvoiceId(InvoiceTypeEnum.PHARMACY.name(),
				invoice.getEntityInvoiceId());
		if (Objects.isNull(invoice)) {
			throw new IllegalArgumentException("Invoice(s) not found.");
		}

		invoices.setInvoicestatus(invoice.getInvoicestatus());
		invoicedao.save(invoices);
		
		
		InvoiceStatusDTO invoiceStatusDTO=new InvoiceStatusDTO();
		invoiceStatusDTO.setPatientId(invoice.getPatientid());
		invoiceStatusDTO.setStatus(invoices.getInvoicestatus());
		invoiceStatusDTO.setInvoiceId(invoices.getEntityInvoiceId());
       invoiceStatusDTO.setMesssage("You Order  is: "+ invoice.getInvoicestatus()+    " Order Id Is:"+  invoices.getEntityInvoiceId());
		Notificationalerts notificationalert = Notificationalerts.buildNotificationalertsForShipping(invoiceStatusDTO);
		notificationalertsService.saveNotificationalerts(notificationalert);
	}

	@Override
	@Transactional
	public Map<ViewInvoiceEnum, Response<List<Invoice>>> getRequestPaymentsForAll(
			ViewInvoiceEnumRequset viewInvoiceEnumRequset) {
		Map<ViewInvoiceEnum, Response<List<Invoice>>> map = new HashMap<>();
		List<org.secondopinion.patient.dto.ViewInvoiceEnumRequset.ViewInvoiceEnum> viewInvoiceEnums = viewInvoiceEnumRequset.getViewInvoiceEnum();
		viewInvoiceEnums.stream().forEach(vae -> {

			switch (vae) {
			case PAYMENT_DONE:
				map.put(ViewInvoiceEnum.PAYMENT_DONE,
						invoicedao.getRequestPaymentsForAll(viewInvoiceEnumRequset.getPatientId(),
								viewInvoiceEnumRequset.getPageNum(), viewInvoiceEnumRequset.getMaxResult()));
				break;
			case DELIVERED:
				map.put(ViewInvoiceEnum.DELIVERED,
						invoicedao.getRequestPaymentsForAllDeliverd(viewInvoiceEnumRequset.getPatientId(),
								viewInvoiceEnumRequset.getPageNum(), viewInvoiceEnumRequset.getMaxResult()));
				break;
			default:
				break;
			}
		});
		return map;
	}

}
