package org.secondopinion.pharmacy.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.secondopinion.configurations.AppProperties;
import org.secondopinion.dao.exception.DataAccessException;

import org.secondopinion.fileservice.FileService;
import org.secondopinion.pharmacy.dao.InvoiceDAO;
import org.secondopinion.pharmacy.dao.PrescriptionpriceDAO;
import org.secondopinion.pharmacy.dao.ShippingaddressDAO;
import org.secondopinion.pharmacy.domain.BaseInvoice;
import org.secondopinion.pharmacy.dto.Invoice;
import org.secondopinion.pharmacy.dto.Prescriptionfillrequest;
import org.secondopinion.pharmacy.dto.Prescriptionprice;
import org.secondopinion.pharmacy.dto.Shippingaddress;
import org.secondopinion.request.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Repository
public class PrescriptionfillrequestDAOHibernateImpl extends BasePrescriptionfillrequestDAOHibernate {

	@Autowired
	private InvoiceDAO invoiceDAO;

	@Autowired
	private AppProperties appProperties;

	@Autowired
	private PrescriptionpriceDAO prescriptionpriceDAO;

	@Autowired
	private ShippingaddressDAO shippingaddressDAO;

	@Override
	@Transactional(readOnly = true)
	public Response<List<Prescriptionfillrequest>> getAllFillRequests(Long pharmacyId, Character newRequests,
			Integer pageNum, Integer maxResults) {
		List<Criterion> criterions = new ArrayList<>();
		if (Objects.nonNull(newRequests)) {
			criterions.add(Restrictions.eq(Prescriptionfillrequest.FIELD_newRequest, newRequests));
		}

		criterions.add(Restrictions.eq(Prescriptionfillrequest.FIELD_pharmacyaddressId, pharmacyId));
		Response<List<Prescriptionfillrequest>> pfrResponse = findByCrieria(criterions, null, pageNum, maxResults);
		if (Objects.nonNull(pfrResponse)) {
			List<Prescriptionfillrequest> pfrs = pfrResponse.getData();
			if (!CollectionUtils.isEmpty(pfrs)) {
				pfrs.stream().forEach(pfr -> {
					// retrieve images
					pfr.setPrescriptionImage(FileService.readFile(appProperties.getFc(), pfr.getDocumentLocation()));
				});
			}
		}

		return pfrResponse;
	}

	@Override
	@Transactional
	public void save(Prescriptionfillrequest pfr) throws DataAccessException {
		if (Objects.isNull(pfr.getPrescriptionFillRequestId())) {
			pfr.setActive('Y');
		}
		super.save(pfr);

		Invoice invoice = pfr.getInvoice();
		if (Objects.nonNull(invoice)) {
			invoice.setPrescriptionFillRequestId(pfr.getPrescriptionFillRequestId());
			invoiceDAO.save(invoice);
		}

		if (Objects.nonNull(invoice)) {
			Long invoiceId = pfr.getInvoice().getInvoiceId();
			Long prescriptionFillRequestId = pfr.getPrescriptionFillRequestId();

			List<Prescriptionprice> prescriptionprices = pfr.getPrescriptionprices();
			if (!CollectionUtils.isEmpty(prescriptionprices)) {
				pfr.getPrescriptionprices().stream().forEach(n -> {
					n.setInvoiceId(invoiceId);
					n.setPrescriptionFillRequestId(prescriptionFillRequestId);
				});

				prescriptionpriceDAO.save(pfr.getPrescriptionprices());
			}

			Shippingaddress shippingAddress = pfr.getShippingAddress();
			if (Objects.nonNull(shippingAddress)) {
				shippingAddress.setInvoiceId(invoiceId);
				shippingAddress.setPatientid(pfr.getPatientId());
				shippingAddress.setPrescriptionFillRequestId(prescriptionFillRequestId);
				shippingaddressDAO.save(shippingAddress);
			}

		}
	}

	private static final String sqlQuery = "select COUNT( patientId)    from prescriptionfillrequest where pharmacyaddressId=:pharmacyaddressId";

	@Override
	@Transactional
	public Map<String, Object> getPharmacyReports(Long pharmacyaddressId) {
		Map<String, Object> hasMap = new HashMap<>();
		String aliasName = "totalPatients";
		String sqlQuery = "select COUNT(  " + Prescriptionfillrequest.FIELD_patientId + ") " + aliasName
				+ " from prescriptionfillrequest" + " where " + Prescriptionfillrequest.FIELD_pharmacyaddressId + " = "
				+ pharmacyaddressId;

		Long totalPatients = 0L;
		Map<String, Type> scalarMapping = new HashMap<>();
		scalarMapping.put(aliasName, StandardBasicTypes.LONG);
		List<Map<String, Object>> resultList = findBySqlQueryMapTransformer(sqlQuery, null, scalarMapping);
		if (!CollectionUtils.isEmpty(resultList)) {
			Map<String, Object> resultMap = resultList.get(0);
			totalPatients = (Long) resultMap.get(aliasName);

		}
		
		Long oredrs = findTotelorder(pharmacyaddressId);
		Map<String, Object> map = new HashMap<>();

		Double totalRevenue = invoiceDAO.findTotelRevenu(pharmacyaddressId);
		hasMap.put("totalPatients", totalPatients);
		hasMap.put("totalRevenue", totalRevenue);
		hasMap.put("oredrs", oredrs);

		return hasMap;

	}
	
	private Long findTotelorder(Long pharmacyaddressId) {
		String aliasName = "totalPatients";
		String sqlQuery = "select distinct count(" + BaseInvoice.FIELD_prescriptionFillRequestId + ") " + aliasName + " from prescriptionfillrequest "
				+ " where " + BaseInvoice.FIELD_pharmacyaddressId + " = " + pharmacyaddressId;

		Long totalorder = 0L;
		Map<String, Type> scalarMapping = new HashMap<>();
		scalarMapping.put(aliasName, StandardBasicTypes.LONG);
		List<Map<String, Object>> resultList = findBySqlQueryMapTransformer(sqlQuery, null, scalarMapping);
		if (!CollectionUtils.isEmpty(resultList)) {
			Map<String, Object> resultMap = resultList.get(0);
			totalorder = (Long) resultMap.get(aliasName);

		}
		return totalorder;
	}
	

	
}