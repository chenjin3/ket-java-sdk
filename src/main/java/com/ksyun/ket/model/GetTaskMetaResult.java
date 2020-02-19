package com.ksyun.ket.model;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetTaskMetaResult {
	
	private String errMsg;
	private int ErrNum;
	private List<MetaInfo> metaList;
	
	private String StartDate;
	private String EndDate;
	private int Marker;
	private int Count;
	private int Total;
	
	
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public int getErrNum() {
		return ErrNum;
	}
	public void setErrNum(int errNum) {
		ErrNum = errNum;
	}
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
	public void setMetaList(
			List<MetaInfo> metaList) {
		this.metaList = metaList;
	}
	public List<MetaInfo> getMetaList() {
		return metaList;
	}
	public void setMetaInfo(java.util.Collection<MetaInfo> metainfoList) {
		if (metainfoList != null) {
			metaList = new ArrayList<MetaInfo>(metainfoList);
        }
	}

	public void addMeta(MetaInfo... metaInfos){
		if (metaList == null) {
			metaList = new ArrayList<MetaInfo>();
        }
		for(MetaInfo metainfo:metaInfos){
			metaList.add(metainfo);
		}
	}


	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}
