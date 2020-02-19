package com.ksyun.ket.model;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetTaskListResult {

	private String StartDate;
	private String EndDate;
	private int Marker;
	private int Count;
	private int Total;
	private String ErrMsg;
	private int ErrNum;
	private List<TaskInfo> TaskInfo;


	public String getStartDate() {
		return StartDate;
	}

	public void setStartDate(String startDate) {
		StartDate = startDate;
	}

	public String getEndDate() {
		return EndDate;
	}

	public void setEndDate(String endDate) {
		EndDate = endDate;
	}

	public int getMarker() {
		return Marker;
	}

	public void setMarker(int marker) {
		Marker = marker;
	}

	public int getCount() {
		return Count;
	}

	public void setCount(int count) {
		Count = count;
	}

	public int getTotal() {
		return Total;
	}

	public void setTotal(int total) {
		Total = total;
	}

	public String getErrMsg() {
		return ErrMsg;
	}

	public void setErrMsg(String errMsg) {
		ErrMsg = errMsg;
	}

	public int getErrNum() {
		return ErrNum;
	}

	public void setErrNum(int errNum) {
		ErrNum = errNum;
	}

	public void setTaskInfo(List<TaskInfo> taskInfo) {
		TaskInfo = taskInfo;
	}

	public List<TaskInfo> getTaskInfo() {
		return TaskInfo;
	}

	public void setTaskInfoList(java.util.Collection<TaskInfo> taskInfoList) {
		if (taskInfoList != null) {
			TaskInfo = new ArrayList<TaskInfo>(taskInfoList);
		}
	}

	public void addTask(TaskInfo... taskinfos) {
		if (TaskInfo == null) {
			TaskInfo = new ArrayList<TaskInfo>();
		}
		for (TaskInfo taskinfo : taskinfos) {
			TaskInfo.add(taskinfo);
		}
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
