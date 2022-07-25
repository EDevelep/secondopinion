package org.secondopinion.pharmacy.service;

import java.util.List;
import java.util.Map;

import org.secondopinion.pharmacy.dto.FillPrescriptionRequestDTO;
import org.secondopinion.pharmacy.dto.Invoice;
import org.secondopinion.pharmacy.dto.PrescriptionFillRequestUpdateDTO;
import org.secondopinion.pharmacy.dto.Prescriptionfillrequest;
import org.secondopinion.pharmacy.dto.Prescriptionprice;
import org.secondopinion.pharmacy.dto.Shippingaddress;
import org.secondopinion.request.Response;

public interface IPrescriptionService {
	
	FillPrescriptionRequestDTO fillPrescription(FillPrescriptionRequestDTO request);
	
	Response<List<Prescriptionfillrequest>> getNewPrescriptionFillRequests(Long pharmacyId, Integer pageNum, Integer maxResults);

	void updatePrescriptionfillrequestWithPrices(PrescriptionFillRequestUpdateDTO fillRequestUpdateDTO);

	Response<List<Prescriptionfillrequest>> getAllPrescriptionFillRequests(Long pharmacyId, Integer pageNum,
			Integer maxResults);

	List<Prescriptionprice> getPrescriptionPricesByPFRId(Long prescriptionFillRequestId);

	Invoice getInvoicesByPFRId(Long prescriptionFillRequestId);

	List<Shippingaddress> getShippingAddressesByPFRId(Long prescriptionFillRequestId);

	Map<String, Object> getPharmacyReports(Long phamacyId);

	
	//Receive payment details from patient.
	
	//Send notification to user for payment.
	
	//Send notification about shipping
}