package com.ksyun.ket.model;

import javafx.concurrent.Task;

public class CreateTasklResult {
	private String TaskID;
	private int ErrNum;
	private String ErrMsg;

	public String getTaskID() {
		return TaskID;
	}
	public void setTaskID(String taskID) {
		TaskID = taskID;
	}
	public int getErrNum() {
		return ErrNum;
	}
	public void setErrNum(int errNum) {
		ErrNum = errNum;
	}
	public String getErrMsg() {
		return ErrMsg;
	}
	public void setErrMsg(String errMsg) {
		ErrMsg = errMsg;
	}

	@Override
	public String toString() {
		return "TaskID=" + TaskID +
				", ErrNum=" + ErrNum +
				", ErrMsg=" + ErrMsg;
	}
}
