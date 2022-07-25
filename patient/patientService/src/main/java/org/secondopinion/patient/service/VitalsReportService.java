
package org.secondopinion.patient.service;

import java.util.TimeZone;

import org.secondopinion.patient.dto.ForUser;
import org.secondopinion.patient.dto.RELATIONSHIP_TYPE;
import org.secondopinion.patient.service.helper.ReportData;
import org.secondopinion.patient.service.impl.TIME;

public interface VitalsReportService {
	public ReportData executeReport(ForUser foruser, String vital, TIME time, TimeZone timezone,RELATIONSHIP_TYPE relationship_TYPE);
}
