package org.secondopinion.pharmacy.dao; 

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.pharmacy.dto.Prescriptionprice;
import org.secondopinion.request.Response;

public interface PrescriptionpriceDAO extends IDAO<Prescriptionprice,Long >{

	Response<List<Prescriptionprice>> getRequestedMedicinesofPrescription(Long prescriptionFillRequestId,
			Integer pageNum, Integer maxResults);
}