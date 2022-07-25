package org.secondopinion.diagnosticcenter.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class ScheduleCriteriaDTO {
	private Long basescheduleId;
	private Long submenuId;
	private Long packageId;
	private Long diagnosticCenteruserId;
	private Long diagnosticCenterAddressId;
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	private Date fromDate;
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	private Date toDate;
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	private Date scheduleDate;
	private Integer weekOfYear;
	private Integer monthNumber;
	private Integer year;
	private ScheduleCalenderEnum scheduleCalenderEnum;
	private Integer pageNum;
	private Integer maxResutls;

	public Long getSubmenuId() {
		return submenuId;
	}

	public void setSubmenuId(Long submenuId) {
		this.submenuId = submenuId;
	}

	public Long getPackageId() {
		return packageId;
	}

	public void setPackageId(Long packageId) {
		this.packageId = packageId;
	}

	public Long getDiagnosticCenteruserId() {
		return diagnosticCenteruserId;
	}

	public void setDiagnosticCenteruserId(Long diagnosticCenteruserId) {
		this.diagnosticCenteruserId = diagnosticCenteruserId;
	}

	public Long getDiagnosticCenterAddressId() {
		return diagnosticCenterAddressId;
	}

	public void setDiagnosticCenterAddressId(Long diagnosticCenterAddressId) {
		this.diagnosticCenterAddressId = diagnosticCenterAddressId;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public ScheduleCalenderEnum getScheduleCalenderEnum() {
		return scheduleCalenderEnum;
	}

	public void setScheduleCalenderEnum(ScheduleCalenderEnum scheduleCalenderEnum) {
		this.scheduleCalenderEnum = scheduleCalenderEnum;
	}

	public Integer getWeekOfYear() {
		return weekOfYear;
	}

	public void setWeekOfYear(Integer weekOfYear) {
		this.weekOfYear = weekOfYear;
	}

	public Integer getMonthNumber() {
		return monthNumber;
	}

	public void setMonthNumber(Integer monthNumber) {
		this.monthNumber = monthNumber;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	public Date getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public Long getBasescheduleId() {
		return basescheduleId;
	}

	public void setBasescheduleId(Long basescheduleId) {
		this.basescheduleId = basescheduleId;
	}

	public enum ScheduleCalenderEnum {
		CURRENT_DAY, CURRENT_WEEK, CURRENT_MONTH,NEXT_WEEK
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getMaxResutls() {
		return maxResutls;
	}

	public void setMaxResutls(Integer maxResutls) {
		this.maxResutls = maxResutls;
	}
}
