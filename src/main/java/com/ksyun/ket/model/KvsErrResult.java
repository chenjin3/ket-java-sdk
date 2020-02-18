package com.ksyun.ket.model;

import com.alibaba.fastjson.JSONObject;

public class KvsErrResult {
	private int ErrNum;
	private String ErrMsg;
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
		return JSONObject.toJSONString(this);
	}
}
