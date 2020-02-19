package com.ksyun.ket.model;

import java.util.ArrayList;
import java.util.List;

public class FlowDataInfo {
	private String Preset;
	private String DstBucket;
	private String DstDir;
	private String DstObjectKey;
	private String DstAcl;
	private List<SrcInfo> SrcInfoList;
	private String ExtParam;

	public void setSrcInfoList(java.util.Collection<SrcInfo> srcInfoList) {
		if (srcInfoList != null) {
			SrcInfoList = new ArrayList<SrcInfo>(srcInfoList);
		}
	}

	public void addSrcInfoList(SrcInfo... scrinfos) {
		if (SrcInfoList == null) {
			SrcInfoList = new ArrayList<SrcInfo>();
		}
		for (SrcInfo srcInfo : scrinfos) {
			SrcInfoList.add(srcInfo);
		}
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

	public List<SrcInfo> getSrcInfoList() {
		return SrcInfoList;
	}

	public void setSrcInfoList(List<SrcInfo> srcInfoList) {
		SrcInfoList = srcInfoList;
	}

	public String getExtParam() {
		return ExtParam;
	}

	public void setExtParam(String extParam) {
		ExtParam = extParam;
	}

	public String getDstAcl() {
		return DstAcl;
	}

	public void setDstAcl(String dstAcl) {
		DstAcl = dstAcl;
	}

}
