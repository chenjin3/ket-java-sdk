package com.ksyun.ket.model;

import java.util.ArrayList;
import java.util.List;

public class TaskInfo {
	private String TaskID;
	private int IsFlow;
	private String Preset;
	private String DstBucket;
	private String DstDir;
	private String DstObjectKey;
	private String TaskStatus;
	private String IsTop;
	private String CreateTime;
	private List<SrcInfo> SrcInfo;
	private List<FlowDataInfo> FlowDataList;
	private String DstAcl;
	private String CbUrl;
	private String CbMethod;
	private String ExtParam;
	private String ErrorMsg;
	private String ErrorCode;

	public int getIsFlow() {
		return IsFlow;
	}

	public void setIsFlow(int isFlow) {
		IsFlow = isFlow;
	}

	public void setFlowDataList(java.util.Collection<FlowDataInfo> flowDataList) {
		if (flowDataList != null) {
			FlowDataList = new ArrayList<FlowDataInfo>(flowDataList);
		}
	}

	public void addFlowDataList(FlowDataInfo... flowDataInfos) {
		if (FlowDataList == null) {
			FlowDataList = new ArrayList<FlowDataInfo>();
		}
		for (FlowDataInfo flowDataInfo : flowDataInfos) {
			FlowDataList.add(flowDataInfo);
		}
	}

	public List<FlowDataInfo> getFlowDataList() {
		return FlowDataList;
	}

	public void setFlowDataList(List<FlowDataInfo> flowDataList) {
		FlowDataList = flowDataList;
	}

	public List<SrcInfo> getSrcInfo() {
		return SrcInfo;
	}

	public void setSrcInfo(java.util.Collection<SrcInfo> srcInfo) {
		if (srcInfo != null) {
			SrcInfo = new ArrayList<SrcInfo>(srcInfo);
		}
	}

	public void addSrcInfoList(SrcInfo... scrinfos) {
		if (SrcInfo == null) {
			SrcInfo = new ArrayList<SrcInfo>();
		}
		for (SrcInfo srcInfo : scrinfos) {
			SrcInfo.add(srcInfo);
		}
	}

	public String getTaskID() {
		return TaskID;
	}

	public void setTaskID(String taskID) {
		TaskID = taskID;
	}

	public String getPreset() {
		return Preset;
	}

	public void setPreset(String preset) {
		Preset = preset;
	}

	public String getDstBucket() {
		return DstBucket;
	}

	public void setDstBucket(String dstBucket) {
		DstBucket = dstBucket;
	}

	public String getDstDir() {
		return DstDir;
	}

	public void setDstDir(String dstDir) {
		DstDir = dstDir;
	}

	public String getDstObjectKey() {
		return DstObjectKey;
	}

	public void setDstObjectKey(String dstObjectKey) {
		DstObjectKey = dstObjectKey;
	}

	public String getTaskStatus() {
		return TaskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		TaskStatus = taskStatus;
	}

	public String getIsTop() {
		return IsTop;
	}

	public void setIsTop(String isTop) {
		IsTop = isTop;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	public String getDstAcl() {
		return DstAcl;
	}

	public void setDstAcl(String dstAcl) {
		DstAcl = dstAcl;
	}

	public String getCbUrl() {
		return CbUrl;
	}

	public void setCbUrl(String cbUrl) {
		CbUrl = cbUrl;
	}

	public String getCbMethod() {
		return CbMethod;
	}

	public void setCbMethod(String cbMethod) {
		CbMethod = cbMethod;
	}

	public String getExtParam() {
		return ExtParam;
	}

	public void setExtParam(String extParam) {
		ExtParam = extParam;
	}

	public String getErrorMsg() {
		return ErrorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		ErrorMsg = errorMsg;
	}

	public String getErrorCode() {
		return ErrorCode;
	}

	public void setErrorCode(String errorCode) {
		ErrorCode = errorCode;
	}

	public void setSrcInfo(List<SrcInfo> srcInfoList) {
		SrcInfo = srcInfoList;
	}

}
