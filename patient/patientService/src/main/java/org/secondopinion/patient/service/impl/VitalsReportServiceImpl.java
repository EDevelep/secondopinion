
package org.secondopinion.patient.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;

import org.secondopinion.patient.dao.UserDAO;
import org.secondopinion.patient.dao.VitalsDAO;
import org.secondopinion.patient.dto.ACCESS_TYPE;
import org.secondopinion.patient.dto.ForUser;
import org.secondopinion.patient.dto.RELATIONSHIP_TYPE;
import org.secondopinion.patient.dto.Relationship;
import org.secondopinion.patient.service.VitalsReportService;
import org.secondopinion.patient.service.helper.ReportData;
import org.secondopinion.reports.graph.GraphUtil;
import org.secondopinion.reports.graph.data.Table;
import org.secondopinion.utils.JSONHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VitalsReportServiceImpl implements VitalsReportService {

	@Autowired
	private VitalsDAO vitalsDAO;

	@Autowired
	private UserDAO userDAO;

	@Override

	@Transactional
	public ReportData executeReport(ForUser foruser, String vital, TIME time, TimeZone timezone,RELATIONSHIP_TYPE relationship_TYPE) {

		ReportData reportData = new ReportData();
		Long userId = getAssociateUserIdFromForUser(foruser, ACCESS_TYPE.VITALS,relationship_TYPE);

		List<Map<String, Object>> data = vitalsDAO.executeGraphQuery(userId, vital, (Date) time.getDateValue(timezone)); 
		
		
		Table table =  GraphUtil.getTableRepresentationMultiRow(data);
		reportData.setData(JSONHelper.getGsonWithDate().toJson(table));

		return reportData;
	}

	public Long getAssociateUserIdFromForUser(ForUser forUser, ACCESS_TYPE accessDetails,RELATIONSHIP_TYPE relationship_TYPE) {
		Long userId = null;
		if (userDAO.hasUserAccessToDetails(forUser.getUserId(), forUser.getForUserId(), accessDetails,relationship_TYPE)) {
			userId = forUser.getUserId();
		} else if (forUser.isCallForSelf()) {
			userId = forUser.getForUserId();
		}

		if (Objects.isNull(userId)) {
			throw new IllegalArgumentException("Invalid ForUser");
		}
		return userId;
	}
}
