package org.secondopinion.utilities.feedbackreport.dto;

import java.util.List;

public class DashboardCounts {
	private List<Object[]> countsByMonth;
	private List<Object[]> countsByWeek;
	private List<Object[]> countsByDay;
	public List<Object[]> getCountsByMonth() {
		return countsByMonth;
	}
	public void setCountsByMonth(List<Object[]> countsByMonth) {
		this.countsByMonth = countsByMonth;
	}
	public List<Object[]> getCountsByWeek() {
		return countsByWeek;
	}
	public void setCountsByWeek(List<Object[]> countsByWeek) {
		this.countsByWeek = countsByWeek;
	}
	public List<Object[]> getCountsByDay() {
		return countsByDay;
	}
	public void setCountsByDay(List<Object[]> countsByDay) {
		this.countsByDay = countsByDay;
	}
}
