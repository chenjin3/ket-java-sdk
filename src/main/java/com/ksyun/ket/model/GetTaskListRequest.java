package com.ksyun.ket.model;


import com.alibaba.fastjson.JSONObject;

public class GetTaskListRequest  {

	private int StartDate;

	private int EndDate;

	private int Marker;

	private int Limit;
	
	private String errorCode = "";

	private String taskStatus = "";
	
	private int StartTime;
	
	private int EndTime;
	
	public int getStartTime() {
		return StartTime;
	}

	public void setStartTime(int startTime) {
		StartTime = startTime;
	}

	public int getEndTime() {
		return EndTime;
	}

	public void setEndTime(int endTime) {
		EndTime = endTime;
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


	public int getStartDate() {
		return StartDate;
	}

	public void setStartDate(int startDate) {
		StartDate = startDate;
	}

	public int getEndDate() {
		return EndDate;
	}

	public void setEndDate(int endDate) {
		EndDate = endDate;
	}

	public int getMarker() {
		return Marker;
	}

	public void setMarker(int marker) {
		Marker = marker;
	}

	public int getLimit() {
		return Limit;
	}

	public void setLimit(int limit) {
		Limit = limit;
	}

	public GetTaskListRequest() {
		Marker = -1;
		Limit = -1;
		StartDate = -1;
		EndDate = -1;
		StartTime = -1;
		EndTime = -1;
	}


	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
