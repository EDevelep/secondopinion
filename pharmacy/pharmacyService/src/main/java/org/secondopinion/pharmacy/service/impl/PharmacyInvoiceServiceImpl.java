package org.secondopinion.pharmacy.service.impl;

import java.util.List;
import java.util.Objects;

import org.secondopinion.enums.InvoiceStatusEnum;
import org.secondopinion.pharmacy.dao.InvoiceDAO;
import org.secondopinion.pharmacy.dao.ShippingaddressDAO;
import org.secondopinion.pharmacy.dto.Invoice;
import org.secondopinion.pharmacy.dto.InvoicesSerchDTO;
import org.secondopinion.pharmacy.dto.PatientInvoiceUpdateDTO;
import org.secondopinion.pharmacy.dto.Shippingaddress;
import org.secondopinion.pharmacy.service.INotificationalertsService;
import org.secondopinion.pharmacy.service.IPharmacyInvoiceService;
import org.secondopinion.pharmacy.service.rest.PatientRestAPIService;
import org.secondopinion.pharmacy.service.rest.PatientShippingUpdateDTO;
import org.secondopinion.request.Response;
import org.secondopinion.utils.NotificationAlert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PharmacyInvoiceServiceImpl implements IPharmacyInvoiceService {

	@Autowired
	private InvoiceDAO invoiceDAO;

	@Autowired
	private ShippingaddressDAO shippingaddressDAO;

	@Autowired
	private PatientRestAPIService patientRestAPIService;

	@Autowired
	private INotificationalertsService notificationalertsService;

	
	@Override
	@Transactional
	public void updateInvoiceAfterPaymentByPatient(Invoice invoice) {
		Invoice dbInvoice = invoiceDAO.readByInvoiceId(invoice.getInvoiceId());
		if (Objects.isNull(dbInvoice)) {
			throw new IllegalArgumentException("Invoice not found.");
		}
		dbInvoice.setPaymentReferenceId(invoice.getPaymentReferenceId());
		dbInvoice.setInvoicestatus(invoice.getInvoicestatus());
		dbInvoice.setTransactiontype(invoice.getTransactiontype());
		dbInvoice.setCardnumber(invoice.getCardnumber());
		dbInvoice.setPaid('Y');
		dbInvoice.setPaidOn(invoice.getPaidOn());
		invoiceDAO.save(dbInvoice);

		Shippingaddress shippingaddress = shippingaddressDAO.findOneByProperty(Shippingaddress.FIELD_invoiceId,
				dbInvoice.getInvoiceId());

		//here we need to add template
		//shipping addres detail will give us  to patient  address detail detail
		//prescriptionprice give the value 
		//in invoice pharmacy address id give pharmacy detail
		notificationalertsService.utiliesMethodForNotification(dbInvoice.getPharmacyaddressId(),
				"Payment Done by Patient", dbInvoice.getInvoiceId(),
				dbInvoice.getPatientname() + " has been paid for the prescription.");

	}

	@Override
	public void deletePharmacyInvoice(Invoice invoice) {
		invoice.setActive('N');
		invoiceDAO.save(invoice);
	}

	@Override
	@Transactional
	public void updateInvoiceByPharmacy(Long invoiceId, InvoiceStatusEnum invoiceStatusEnum) {
		Invoice dbInvoice = invoiceDAO.readByInvoiceId(invoiceId);
		if (Objects.isNull(dbInvoice)) {
			throw new IllegalArgumentException("Invoice not found.");
		}
		dbInvoice.setInvoicestatus(invoiceStatusEnum.name());

		invoiceDAO.save(dbInvoice);
		PatientInvoiceUpdateDTO patientInvoiceUpdateDTO = PatientInvoiceUpdateDTO
				.buildPatientInvoiceUpdateDTO(dbInvoice, InvoiceStatusEnum.CONFIRMED);
		patientRestAPIService.patientInvoiceUpdateWithStatusAPI(patientInvoiceUpdateDTO);

		Shippingaddress shippingaddress = shippingaddressDAO.findOneByProperty(Shippingaddress.FIELD_invoiceId,
				dbInvoice.getInvoiceId());

		// we need send template here to user and pharmacy as well as
		notificationalertsService.utiliesMethodForNotification(dbInvoice.getPharmacyaddressId(),
				"Prescription " + invoiceStatusEnum.name(), dbInvoice.getInvoiceId(),
				dbInvoice.getPatientname() + " has been paid for the prescriptions." + "Prescription "
						+ invoiceStatusEnum.name() + " to deliver to the below address \n "
						+ shippingaddress.toString());

	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<Invoice>> getInvoicesByStatus(Long pharmacyId, InvoiceStatusEnum invoiceStatusEnum,
			Integer pageNum, Integer maxResults) {
		return invoiceDAO.getInvoicesByStatus(pharmacyId, invoiceStatusEnum, pageNum, maxResults);
	}

	@Override
	public void saveInvoiceShipeeingDetail(Invoice invoice) {
		invoice.setActive('Y');
		invoiceDAO.save(invoice);
		PatientShippingUpdateDTO patientInvoiceUpdateDTO=new PatientShippingUpdateDTO();
		patientInvoiceUpdateDTO.setMessage("Hii your Order is SHIPPED By "+invoice.getShippedBy()+ " And  Your Tracking  Id :"+invoice.getTrackingId());
		patientInvoiceUpdateDTO.setPatientid(invoice.getPatientid());
		patientInvoiceUpdateDTO.setPharmacyId(invoice.getPharmacyaddressId());
		patientInvoiceUpdateDTO.setName(invoice.getName());
		patientRestAPIService.patientInvoiceShippingUpdate(patientInvoiceUpdateDTO);
	}

	@Override
	@Transactional
	public List<Invoice> getShipeeingDetail(Long invoiceId, Long patientId) {
		List<Invoice> invoices = invoiceDAO.findShipeeingDetail(invoiceId, patientId);
		return invoices;

	}

	@Override
	@Transactional
	public void updateInvoiceAfterShipped(Long invoiceId, InvoiceStatusEnum invoiceStatusEnum) {
		Invoice invoice = invoiceDAO.findById(invoiceId);
		if (Objects.isNull(invoice)) {
			throw new IllegalArgumentException("Invoice not found.");
		}
		invoice.setInvoicestatus(invoiceStatusEnum.name());
		invoiceDAO.save(invoice);
		PatientInvoiceUpdateDTO patientInvoiceUpdateDTO = PatientInvoiceUpdateDTO
				.buildPatientInvoiceUpdateDTO(invoice, invoiceStatusEnum);
		patientRestAPIService.patientInvoiceShippingUpdateWithStatusAPI(patientInvoiceUpdateDTO);

		
	}

	@Override
	@Transactional
	public Response<List<Invoice>> getinvoiceForSerchcritiria(InvoicesSerchDTO invoicesSerchDTO) {
		Response<List<Invoice>> listinvoice = invoiceDAO.getinvoiceForSerchcritiria(invoicesSerchDTO);
		return listinvoice;
	}

}
