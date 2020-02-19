package com.ksyun.ket.model;

import com.alibaba.fastjson.JSONObject;

public class FetchMetaInfoResult {
	
	private int ErrNum;
	private String ErrMsg;
	private String MetaInfo;
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
	public String getMetaInfo() {
		return MetaInfo;
	}
	public void setMetaInfo(String metaInfo) {
		MetaInfo = metaInfo;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}
