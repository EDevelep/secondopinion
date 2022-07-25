package org.secondopinions.elasticsearch.dto;

public class Response<T> {
	private String status;
	private String message;
	private T data;
	private String userSession;
	private Long totalResults;
	private Integer pageNum;
	private Integer maxResultsPerPage;
	private String criterions;

	public Response() {
		status = StatusEnum.SUCCESS.name();
	}

	public Response(T data, StatusEnum staus, String message) {
		this.data = data;
		this.status = staus.name();
		this.message = message;
	}

	public Response(T data, StatusEnum status, String message, String userSession) {
		this.data = data;
		this.status = status.name();
		this.message = message;
		this.userSession = userSession;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public enum StatusEnum {
		SUCCESS, FAILURE
	}

	@Override
	public String toString() {
		return "Response [status=" + status + ", message=" + message + ", data=" + data + ", userSession=" + userSession
				+ "]";
	}

	public String getUserSession() {
		return userSession;
	}

	public void setUserSession(String userSession) {
		this.userSession = userSession;
	}

	public Long getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(Long totalResults) {
		this.totalResults = totalResults;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getMaxResultsPerPage() {
		return maxResultsPerPage;
	}

	public void setMaxResultsPerPage(Integer maxResultsPerPage) {
		this.maxResultsPerPage = maxResultsPerPage;
	}

	public void setCriterions(String criterions) {
		this.criterions = criterions;
	}

	public String getCriterions() {
		return criterions;
	}
	/*
	 * public Response(Map<Long, T> map, String message) { Long totalresults = 0L;
	 * Optional<Long> intOptional = map.entrySet().stream().map(es ->
	 * es.getKey()).findAny(); if(intOptional.isPresent()) { totalresults =
	 * intOptional.get(); }
	 * 
	 * T t = null; Optional<T> dataOptional = map.entrySet().stream().map(es ->
	 * es.getValue()).findAny(); if(dataOptional.isPresent()) { t =
	 * dataOptional.get(); }
	 * 
	 * this.data = t; this.status = StatusEnum.SUCCESS.name(); this.message =
	 * message; this.totalResults = totalresults; }
	 */

}
