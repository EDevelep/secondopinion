package org.secondopinion.pharmacy.dao; 

import java.util.List;
import java.util.Map;

import org.secondopinion.dao.IDAO;
import org.secondopinion.pharmacy.dto.Prescriptionfillrequest;
import org.secondopinion.request.Response;

public interface PrescriptionfillrequestDAO extends IDAO<Prescriptionfillrequest,Long >{

	Response<List<Prescriptionfillrequest>> getAllFillRequests(Long pharmacyId, Character newRequests, Integer pageNum, Integer maxResults);

	Map<String, Object> getPharmacyReports(Long pharmacyaddressId);
}