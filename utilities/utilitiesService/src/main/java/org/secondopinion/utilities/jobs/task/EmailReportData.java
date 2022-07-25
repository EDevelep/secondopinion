package org.secondopinion.utilities.jobs.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.secondopinion.userMgmt.dto.Company;


public class EmailReportData {
	//Submissions Data
	//Reports Data
	
	private Date date;
	private Company company;
	private int requirementsCount;
	private int submissionsCount;
	private List<List<Object>> submissionData;
	private List<List<Object>> requirementData;

	public EmailReportData(Company _company) {
		this.company = _company;
	}
	
	public Company getCompany() {
		return company;
	}
	
	public List<List<Object>> getSubmissionData() {
		return submissionData;
	}
	public void setSubmissionData(List<List<Object>> submissionData) {
		this.submissionData = submissionData;
	}
	public List<List<Object>> getRequirementData() {
		return requirementData;
	}
	public void setRequirementData(List<List<Object>> requirementData) {
		this.requirementData = requirementData;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getRequirementsCount() {
		return requirementsCount;
	}
	public void setRequirementsCount(int requirementsCount) {
		this.requirementsCount = requirementsCount;
	}
	public int getSubmissionsCount() {
		return submissionsCount;
	}
	public void setSubmissionsCount(int submissionsCount) {
		this.submissionsCount = submissionsCount;
	}
	
	public String  getSubmissionCountTableStr(){
		return tableFormat(submissionData);
	}
	
	public String  getRequirementCountTableStr(){
		return tableFormat(requirementData);
	}
	
	private String tableFormat(List<List<Object>> list){
		StringBuilder sb = new StringBuilder("<table>");
		List<Object> firstRow = list.remove(0);
		
		sb.append("<tr>");
		
		for(Object col : firstRow){
			sb.append("<th style=\"background-color:gray;>\"").append(col).append("</th>");
		}
		
		sb.append("</tr>");
		
		list.forEach(row -> {
			sb.append("<tr>");
			
			for(Object col : row){
				sb.append("<td>").append(col).append("</td>");
			}
			
			sb.append("</tr>");
		});
		
		sb.append("</table>");
		return sb.toString();
	}
}
