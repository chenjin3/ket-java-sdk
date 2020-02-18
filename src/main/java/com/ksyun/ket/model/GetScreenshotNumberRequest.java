package com.ksyun.ket.model;


public class GetScreenshotNumberRequest  {

	private int StartUnixTime;
	private int EndUnixTime;
	private int Granularity;
	private int ResultType;



	public int getStartUnixTime() {
		return StartUnixTime;
	}

	public void setStartUnixTime(int startUnixTime) {
		StartUnixTime = startUnixTime;
	}

	public int getEndUnixTime() {
		return EndUnixTime;
	}

	public void setEndUnixTime(int endUnixTime) {
		EndUnixTime = endUnixTime;
	}

	public int getGranularity() {
		return Granularity;
	}

	public void setGranularity(int granularity) {
		Granularity = granularity;
	}

	public int getResultType() {
		return ResultType;
	}

	public void setResultType(int resultType) {
		ResultType = resultType;
	}

}
