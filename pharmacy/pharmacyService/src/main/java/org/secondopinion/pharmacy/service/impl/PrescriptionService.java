package org.secondopinion.pharmacy.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.secondopinion.enums.InvoiceStatusEnum;

import org.secondopinion.pharmacy.dao.InvoiceDAO;
import org.secondopinion.pharmacy.dao.PharmacyDAO;
import org.secondopinion.pharmacy.dao.PharmacyaddressDAO;
import org.secondopinion.pharmacy.dao.PrescriptionfillrequestDAO;
import org.secondopinion.pharmacy.dao.PrescriptionpriceDAO;
import org.secondopinion.pharmacy.dao.ShippingaddressDAO;

import org.secondopinion.pharmacy.dto.FillPrescriptionRequestDTO;
import org.secondopinion.pharmacy.dto.Invoice;
import org.secondopinion.pharmacy.dto.InvoiceUpdateDTO;
import org.secondopinion.pharmacy.dto.Medication;
import org.secondopinion.pharmacy.dto.PatientInvoiceUpdateDTO;
import org.secondopinion.pharmacy.dto.Pharmacy;
import org.secondopinion.pharmacy.dto.Pharmacyaddress;
import org.secondopinion.pharmacy.dto.PrescriptionFillRequestUpdateDTO;
import org.secondopinion.pharmacy.dto.PrescriptionPriceUpdateDTO;
import org.secondopinion.pharmacy.dto.Prescriptionfillrequest;
import org.secondopinion.pharmacy.dto.Prescriptionprice;
import org.secondopinion.pharmacy.dto.Shippingaddress;
import org.secondopinion.pharmacy.service.INotificationalertsService;
import org.secondopinion.pharmacy.service.IPrescriptionService;
import org.secondopinion.pharmacy.service.rest.PatientPriceUpdateDTO;
import org.secondopinion.pharmacy.service.rest.PatientRestAPIService;
import org.secondopinion.request.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class PrescriptionService implements IPrescriptionService {
	@Autowired
	private PrescriptionfillrequestDAO fillRequestDAO;

	@Autowired
	private PrescriptionpriceDAO prescriptionPriceDAO;

	@Autowired
	private INotificationalertsService notificationalertsService;

	@Autowired
	private InvoiceDAO invoiceDAO;

	@Autowired
	private ShippingaddressDAO shippingaddressDAO;

	@Autowired
	private PharmacyDAO pharmacyDAO;

	@Autowired
	private PharmacyaddressDAO addressDAO;

	@Autowired
	private PatientRestAPIService patientRestAPIService;

	@Value("${taxPercentage}")
	private Integer taxPercentage;

	@Override
	@Transactional
	public FillPrescriptionRequestDTO fillPrescription(FillPrescriptionRequestDTO request) {
		Pharmacyaddress address = addressDAO.findById(request.getPharmacyaddressId());
		if (Objects.isNull(address)) {
			throw new IllegalArgumentException("pharmacy not found.");
		}

		Pharmacy pharmacy = pharmacyDAO.readByPharmacyId(address.getPharmacyId());
		request.setPharmacyName(pharmacy.getName());

		// Prescription Request
		Prescriptionfillrequest pfr = Prescriptionfillrequest.buildFillRequest(request);
		pfr.setActive('Y');

		List<Prescriptionprice> prescriptionprices = new ArrayList<>();

		// Get Medical Prescriptions from the patient service
		List<Medication> pharmacyMedications = request.getMedications();

		if (!CollectionUtils.isEmpty(pharmacyMedications)) {
			prescriptionprices = buildPrescriptionPrices(pharmacyMedications, pfr.getPatientAppointmentId());
		}
		pfr.setPrescriptionprices(prescriptionprices);

		// build invoice
		Invoice invoice = Invoice.buildNewInvoiceForMedicines(pharmacy.getName(), request);
		pfr.setInvoice(invoice);

		Shippingaddress shippingAddress = request.getShippingAddress();
		shippingAddress.setActive('Y');
		pfr.setShippingAddress(shippingAddress);
		fillRequestDAO.save(pfr);

		request.setInvoiceId(pfr.getInvoice().getInvoiceId());
		request.setInvoicestatus(pfr.getInvoice().getInvoicestatus());
		request.setPrescriptionFillRequestId(pfr.getPrescriptionFillRequestId());

		notificationalertsService.utiliesMethodForNotification(pfr.getPharmacyaddressId(), "New Prescription Request",
				pfr.getPrescriptionFillRequestId(),
				"You got a new prescription fill request from " + pfr.getPatientName());

		return request;
	}

	private List<Prescriptionprice> buildPrescriptionPrices(List<Medication> pharmacyMedications,
			Long patientAppointmentId) {
		return pharmacyMedications.stream().map(n -> {
			return Prescriptionprice.buildPrescriptionprice(n, patientAppointmentId);
		}).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<Prescriptionfillrequest>> getNewPrescriptionFillRequests(Long pharmacyId, Integer pageNum,
			Integer maxResults) {
		return fillRequestDAO.getAllFillRequests(pharmacyId, 'Y', pageNum, maxResults);
	}

	@Override
	@Transactional
	public void updatePrescriptionfillrequestWithPrices(PrescriptionFillRequestUpdateDTO fillRequestUpdateDTO) {

		Prescriptionfillrequest prescriptionfillrequest = updatePrescriptionFillRequest(fillRequestUpdateDTO);
		  prescriptionfillrequest.setInvoiceId(fillRequestUpdateDTO.getInvoiceUpdateDTO().getInvoiceId());
		Double prescriptionsTotalPrice = updatePrescriptionPrices(prescriptionfillrequest,
				fillRequestUpdateDTO.getPrescriptionPriceUpdateDTOs());
		Invoice invoice = updatePrescriptionInvoice(prescriptionsTotalPrice,
				fillRequestUpdateDTO.getInvoiceUpdateDTO());

		fillRequestDAO.save(prescriptionfillrequest);
		if (Objects.nonNull(invoice)) {
			PatientInvoiceUpdateDTO patientInvoiceUpdateDTO = PatientInvoiceUpdateDTO
					.buildPatientInvoiceUpdateDTO(invoice, InvoiceStatusEnum.REQUEST_PAYMENT);

			if (fillRequestUpdateDTO.getPrescriptiontype().equals("IMAGE")) {
				PatientPriceUpdateDTO patientPriceUpdateDTO = PatientPriceUpdateDTO.buildObjectForPatient(fillRequestUpdateDTO);
				
				patientRestAPIService.updateMedcineByEntityInvoiceIdWithStatus(patientPriceUpdateDTO);
			}
				patientRestAPIService.updateInvoiceByEntityInvoiceIdWithStatus(patientInvoiceUpdateDTO);
			}

		}

	

	private Invoice updatePrescriptionInvoice(Double prescriptionsTotalPrice, InvoiceUpdateDTO invoiceUpdateDTO) {
		if (Objects.isNull(invoiceUpdateDTO)) {
			throw new IllegalArgumentException("Invoice object can not be null.");
		}
		if (Objects.isNull(invoiceUpdateDTO.getInvoiceId())) {
			throw new IllegalArgumentException("InvoiceId can not be null.");
		}
		if (Objects.isNull(invoiceUpdateDTO.getDiscount())) {
			invoiceUpdateDTO.setDiscount(0.0d);
		}

		Double discount = invoiceUpdateDTO.getDiscount();// 50

		Double invoiceTotalPrice = prescriptionsTotalPrice - discount;
		if (invoiceUpdateDTO.getTotal().equals(invoiceTotalPrice)) {
			throw new IllegalArgumentException("Discount cannot be  more than total price .");
		}

		Invoice dbinvoice = invoiceDAO.findById(invoiceUpdateDTO.getInvoiceId());
		if (Objects.nonNull(dbinvoice)) {
			// dbinvoice.setDiscount(discount);
			dbinvoice.setTotal(invoiceUpdateDTO.getTotal());
			dbinvoice.setInvoicestatus(InvoiceStatusEnum.REQUEST_PAYMENT.name());
		}
		return dbinvoice;
	}

	private Prescriptionfillrequest updatePrescriptionFillRequest(
			PrescriptionFillRequestUpdateDTO fillRequestUpdateDTO) {
		if (Objects.isNull(fillRequestUpdateDTO)) {
			throw new IllegalArgumentException("Object can not be null");
		}
		Long prescriptionFillRequestId = fillRequestUpdateDTO.getPrescriptionFillRequestId();
		if (Objects.isNull(prescriptionFillRequestId)) {
			throw new IllegalArgumentException("prescriptionFillRequestId can not be null");
		}
		Prescriptionfillrequest prescriptionfillrequest = fillRequestDAO.findById(prescriptionFillRequestId);
		if (Objects.isNull(prescriptionfillrequest)) {
			throw new IllegalArgumentException("prescriptionfillrequest is not found.");
		}
		prescriptionfillrequest.setNewRequest('N');
		fillRequestDAO.save(prescriptionfillrequest);

		return prescriptionfillrequest;
	}

	private Double updatePrescriptionPrices(Prescriptionfillrequest prescriptionfillrequest,
			List<PrescriptionPriceUpdateDTO> prescriptionPriceUpdateDTOs) {
		List<Prescriptionprice> prescriptionPrices = prescriptionfillrequest.getPrescriptionprices();
		if (CollectionUtils.isEmpty(prescriptionPrices)) {
			prescriptionPrices = new ArrayList<>();
		}
		if (CollectionUtils.isEmpty(prescriptionPriceUpdateDTOs)) {
			return 0d;
		}
		for (PrescriptionPriceUpdateDTO pp : prescriptionPriceUpdateDTOs) {
			validatePrescriptionPriceUpdateDTO(pp);
			Long prescriptionPriceId = pp.getPrescriptionPriceId();
			if (Objects.nonNull(prescriptionPriceId)) {
				Prescriptionprice dbPrescriptionPrice = prescriptionPriceDAO.findById(prescriptionPriceId);
				if (Objects.isNull(dbPrescriptionPrice)) {
					throw new IllegalArgumentException("Prescriptionprice not found.");
				}
				Prescriptionprice.buildForUpdate(dbPrescriptionPrice, pp);
				prescriptionPrices.add(dbPrescriptionPrice);
			} else {
				Prescriptionprice prescriptionPrice = new Prescriptionprice();
				//here we  need to build precption object
				Prescriptionfillrequest dbprescriptionfillrequest=fillRequestDAO.findById(prescriptionfillrequest.getPrescriptionFillRequestId());
				if(Objects.nonNull(dbprescriptionfillrequest)) {
					prescriptionPrice=Prescriptionprice.buildPrescriptionprice(pp, dbprescriptionfillrequest);
			           prescriptionPrice.setInvoiceId(prescriptionfillrequest.getInvoiceId());
					prescriptionPriceDAO.save(prescriptionPrice);
				}
				//BeanUtils.copyProperties(pp, prescriptionPrice);
				prescriptionPrices.add(prescriptionPrice);
			}
		}
		return prescriptionPrices.stream().mapToDouble(pp -> pp.getTotalPrice()).sum();

	}

	private void validatePrescriptionPriceUpdateDTO(PrescriptionPriceUpdateDTO pp) {
		if (Objects.isNull(pp.getQuantity())) {
			throw new IllegalArgumentException("Quantity can not be null.");
		}
		if (Objects.isNull(pp.getUnitPrice())) {
			throw new IllegalArgumentException("Unitprice can not be null.");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Response<List<Prescriptionfillrequest>> getAllPrescriptionFillRequests(Long pharmacyId, Integer pageNum,
			Integer maxResults) {
		return fillRequestDAO.getAllFillRequests(pharmacyId, null, pageNum, maxResults);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Prescriptionprice> getPrescriptionPricesByPFRId(Long prescriptionFillRequestId) {
		return prescriptionPriceDAO.findByProperty(Prescriptionprice.FIELD_prescriptionFillRequestId,
				prescriptionFillRequestId);
	}

	@Override
	@Transactional(readOnly = true)
	public Invoice getInvoicesByPFRId(Long prescriptionFillRequestId) {
		return invoiceDAO.findOneByProperty(Invoice.FIELD_prescriptionFillRequestId, prescriptionFillRequestId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Shippingaddress> getShippingAddressesByPFRId(Long prescriptionFillRequestId) {
		return shippingaddressDAO.findByProperty(Shippingaddress.FIELD_prescriptionFillRequestId,
				prescriptionFillRequestId);
	}

	@Override
	@Transactional
	public Map<String, Object> getPharmacyReports(Long phamacyId) {
	
		Pharmacy pharmacy=pharmacyDAO.findById(phamacyId);
		if(Objects.isNull(pharmacy)) {
			return null;
		}
		return fillRequestDAO.getPharmacyReports(pharmacy.getPharmacyaddressId());
	}

}