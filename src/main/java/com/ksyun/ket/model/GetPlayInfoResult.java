package com.ksyun.ket.model;

import java.util.List;

public class GetPlayInfoResult {
    private String RequestId;

    private VideoBase VideoBase;

    private List<PlayInfo> PlayInfoList;

    public String getRequestId() {
        return RequestId;
    }

    public void setRequestId(String requestId) {
        RequestId = requestId;
    }

    public VideoBase getVideoBase() {
        return VideoBase;
    }

    public void setVideoBase(VideoBase videoBase) {
        VideoBase = videoBase;
    }

    public List<PlayInfo> getPlayInfoList() {
        return PlayInfoList;
    }

    public void setPlayInfoList(List<PlayInfo> playInfoList) {
        PlayInfoList = playInfoList;
    }
}
