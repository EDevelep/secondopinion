package org.secondopinion.reports.dto; 

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.secondopinion.reports.domain.BaseReport;
import org.secondopinion.reports.dto.ReportParameter.PARAMETER;
import org.secondopinion.utils.StringUtil; 

@Entity 
@Table (name="report")
public class Report extends BaseReport{
	public enum REPORT_TYPE{
		COUNTS, GRAPH, STATS;
	}
	
	private List<ReportParameter> reportParameters;
	private Map<String, Object> params;

	
	/**
	 * @return the reportParameters
	 */
	@Transient
	public List<ReportParameter> getReportParameters() {
		return reportParameters;
	}
	
	@Transient
	public boolean isAliasesProvided(){
		return !StringUtil.isNullOrEmpty(getColumnAliases());
	}

	/**
	 * @param reportParameters the reportParameters to set
	 */
	public void setReportParameters(List<ReportParameter> reportParameters) {
		this.reportParameters = reportParameters;
	}

	public String createQuery() {
		StringBuilder sb = new StringBuilder("select");
		sb.append(" ").append(getColumns());
		
		if(!StringUtil.isNullOrEmpty(getSumOnColumn())){
			if(!StringUtil.isNullOrEmpty(getColumns())){
				sb.append(", ");
			}
			sb.append("sum(").append(getSumOnColumn()).append(") " ).append(getSumOnColumn());
		}
		
		if(getCount()!=null &&  getCount() == 'Y'){
			if(!StringUtil.isNullOrEmpty(getColumns()) || !StringUtil.isNullOrEmpty(getSumOnColumn())){
				sb.append(",");
			}
			sb.append(" count(*) count");
		}
		
		sb.append(" from ").append(getSourceName());
		
		if(!StringUtil.isNullOrEmpty(getConditions())){
			sb.append(" where ").append(getConditions());
		}
		if(!StringUtil.isNullOrEmpty(getGroupColumns())){
			sb.append(" group by ").append(getGroupColumns());
		}
		if(!StringUtil.isNullOrEmpty(getOrderByColumns())){
			sb.append(" order by ").append(getOrderByColumns());
		}
		
		return sb.toString();
	}

	public Map<String, Object> prepareReportParams(TimeZone timezone) {
		Map<String, Object> params = new HashMap<>();
		if(this.params != null && this.params.size() > 0){
			params.putAll(this.params);
		}
		
		for(ReportParameter parameter : reportParameters){
			String paramName = parameter.getParameterName();
			if(!params.containsKey(paramName)){
				PARAMETER param = PARAMETER.valueOf(parameter.getParameterValue());
				Object paramVal = param.getParamValue(this, timezone);
				
				params.put(paramName, paramVal);
			}
		}
		return params;
	}

	@Transient
	public int getTransposeColIndex() {
		String trimmedTransposeColName = StringUtil.trim(getTransposeData());
		return StringUtil.getColumnIndex(getTrimmedColumns(), trimmedTransposeColName);
	}

	@Transient
	public int getValueColumnIndex() {
		String valColName = "count";
		if(!StringUtil.isNullOrEmpty(getSumOnColumn())){
			valColName = getSumOnColumn();
		}
		
		return StringUtil.getColumnIndex(getTrimmedColumns(), valColName);
	}
	
	
	@Transient
	private String[] getTrimmedColumns(){
		String[] selectCols = StringUtil.split(getColumns(), ",");
		
		int addIndx = 0;
		if(!StringUtil.isNullOrEmpty(getSumOnColumn())){
			addIndx++;
		}
		
		if(getCount() != null && getCount() == 'Y'){
			addIndx++;
		}
		
		String[] cols = new String[selectCols.length + addIndx];
		int i = 0;
		for(; i<selectCols.length; i++){
			cols[i] = StringUtil.trim(selectCols[i]);
		}
		
		if(!StringUtil.isNullOrEmpty(getSumOnColumn())){
			cols[i] = StringUtil.trim(getSumOnColumn());
			i++;
		}
		
		if(getCount() != null && getCount() == 'Y'){
			cols[i] = "count";
		}
		
		return cols;
	}
	
	@Transient
	public String[] getKeyColArray() {
		return StringUtil.split(getKeyColumns(), ",");
	}
	
	@Transient
	public int[] getKeyColIndices() {
		String[] selectCols =  getTrimmedColumns();
		String[] keyCols = StringUtil.split(getKeyColumns(), ",");
		int[] keyColIndices = new int[keyCols.length];
		
		for(int i =0; i<keyCols.length; i++){
			keyColIndices[i] = StringUtil.getColumnIndex(selectCols, StringUtil.trim(keyCols[i]));
		}
		
		return keyColIndices;
	}
	
	@Transient
	public REPORT_TYPE getReportType(){
		return REPORT_TYPE.valueOf(getType());
	}

	@Transient
	public boolean isGraph() {
		return (getReportType() == REPORT_TYPE.GRAPH);
	}

	@Transient
	public boolean isUserSpecific() {
		for(ReportParameter parameter : reportParameters){
			PARAMETER param = PARAMETER.valueOf(parameter.getParameterValue());
			
			if(param == PARAMETER.PARAM_USER_ID){
				return true;
			}
		}
		return false;
	}

	@Transient
	public boolean isCounts() {
		return (getReportType() == REPORT_TYPE.COUNTS);
	}
	
	@Transient
	public boolean isStats() {
		return (getReportType() == REPORT_TYPE.STATS);
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
}