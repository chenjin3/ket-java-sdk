package com.ksyun.ket.model;

import com.alibaba.fastjson.JSONObject;

public class GetTaskByTaskIDResult {
	private String ErrMsg;
	private int ErrNum;
	private TaskInfo TaskInfo;
	

	public TaskInfo getTaskInfo() {
		return TaskInfo;
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

	public void setTaskInfo(TaskInfo taskInfo) {
		TaskInfo = taskInfo;
	}


	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
