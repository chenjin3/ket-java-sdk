package com.ksyun.ket.model;



public class GetPlayInfoRequest  {
    private Long VideoId;
    private String Formats;
    private Integer AuthTimeOut;

    public Long getVideoId() {
        return VideoId;
    }

    public void setVideoId(Long videoId) {
        VideoId = videoId;
    }

    public String getFormats() {
        return Formats;
    }

    public void setFormats(String formats) {
        Formats = formats;
    }

    public Integer getAuthTimeOut() {
        return AuthTimeOut;
    }

    public void setAuthTimeOut(Integer authTimeOut) {
        AuthTimeOut = authTimeOut;
    }


}
