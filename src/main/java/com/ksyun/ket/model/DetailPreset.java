package com.ksyun.ket.model;

import org.apache.http.NameValuePair;

public class DetailPreset {
	private String PresetType;
	private String Description;
	private String CreateTime;
	private String UpdateTime;
	
	private NameValuePair Param;

	public String getPresetType() {
		return PresetType;
	}

	public void setPresetType(String presetType) {
		PresetType = presetType;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	public String getUpdateTime() {
		return UpdateTime;
	}

	public void setUpdateTime(String updateTime) {
		UpdateTime = updateTime;
	}

	public NameValuePair getParam() {
		return Param;
	}

	public void setParam(NameValuePair param) {
		Param = param;
	}

	
}
