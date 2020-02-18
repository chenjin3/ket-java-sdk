package com.ksyun.ket.model;

import com.alibaba.fastjson.JSONObject;

public class GetPresetDetailResult {
	private int ErrNum;
	private String ErrMsg;
	
	private DetailPreset PresetDetail;

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

	public DetailPreset getPresetDetail() {
		return PresetDetail;
	}

	public void setPresetDetail(DetailPreset presetDetail) {
		PresetDetail = presetDetail;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}
