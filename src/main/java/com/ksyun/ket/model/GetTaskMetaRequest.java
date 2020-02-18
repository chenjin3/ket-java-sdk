package com.ksyun.ket.model;


public class GetTaskMetaRequest {
	private String TaskID = "";
	
	private int StartDate;
	
	private int EndDate;
	
	private int Marker;
	
	private int Limit;
	
	public GetTaskMetaRequest(){
		Marker = -1;
		Limit = -1;
		StartDate = -1;
		EndDate = -1;
	}


	public String getTaskID() {
		return TaskID;
	}

	public void setTaskID(String taskID) {
		TaskID = taskID;
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
	
	
}
