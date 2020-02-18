package com.ksyun.ket.model;

public class TaskParam {
	private String data;
	private String taskID;
	private String pipelineName;
	private String errorCode;
	private String taskStatus;
	private int startDate;
	private int endDate;
	private int startTime;
	private int endTime;
	private int marker;
	private int limit;
	private int startUnixTime;
	private int endUnixTime;
	private int granularity;
	private int resultType;

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public String getData() {
		return data;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getTaskID() {
		return taskID;
	}

	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}

	public int getStartDate() {
		return startDate;
	}

	public void setStartDate(int startDate) {
		this.startDate = startDate;
	}

	public int getEndDate() {
		return endDate;
	}

	public void setEndDate(int endDate) {
		this.endDate = endDate;
	}

	public int getMarker() {
		return marker;
	}

	public void setMarker(int marker) {
		this.marker = marker;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getPipelineName() {
		return pipelineName;
	}

	public void setPipelineName(String pipelineName) {
		this.pipelineName = pipelineName;
	}

	public int getStartUnixTime() {
		return startUnixTime;
	}

	public void setStartUnixTime(int startUnixTime) {
		this.startUnixTime = startUnixTime;
	}

	public int getEndUnixTime() {
		return endUnixTime;
	}

	public void setEndUnixTime(int endUnixTime) {
		this.endUnixTime = endUnixTime;
	}

	public int getGranularity() {
		return granularity;
	}

	public void setGranularity(int granularity) {
		this.granularity = granularity;
	}

	public int getResultType() {
		return resultType;
	}

	public void setResultType(int resultType) {
		this.resultType = resultType;
	}

}