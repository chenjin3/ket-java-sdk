package com.ksyun.ket.model;


import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetPresetListResult {
	private int errNum;
	private String errMsg;
	private List<Preset> PresetList;


	public int getErrNum() {
		return errNum;
	}

	public void setErrNum(int errNum) {
		this.errNum = errNum;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public List<Preset> getPresetList() {
		return PresetList;
	}

	public void setPresetList(java.util.Collection<Preset> presetList) {
		if (presetList != null) {
			PresetList = new ArrayList<Preset>(presetList);
		}
	}

	public void addPreset(Preset... presets) {
		if (PresetList == null) {
			PresetList = new ArrayList<Preset>();
		}
		for (Preset preset : presets) {
			PresetList.add(preset);
		}
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}


}
