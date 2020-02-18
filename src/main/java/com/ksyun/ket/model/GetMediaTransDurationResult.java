package com.ksyun.ket.model;

public class GetMediaTransDurationResult {
	private int ErrNum;
	private String ErrMsg;
	private String StartUnixTime;
	private String EndUnixTime;
	private String Granularity;

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

	public String getStartUnixTime() {
		return StartUnixTime;
	}

	public void setStartUnixTime(String startUnixTime) {
		StartUnixTime = startUnixTime;
	}

	public String getEndUnixTime() {
		return EndUnixTime;
	}

	public void setEndUnixTime(String endUnixTime) {
		EndUnixTime = endUnixTime;
	}

	public String getGranularity() {
		return Granularity;
	}

	public void setGranularity(String granularity) {
		Granularity = granularity;
	}


}
