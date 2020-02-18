package com.ksyun.ket.model;

import java.util.List;


public class Param {
	private String f;

	// avsample & avsnapshot
	private int width;
	private int height;
	private int shortSide;
	// avsample
	private int interval;
	private int spriteflag;
	private int spritew;
	private int spriteh;
	private Video video;
	private Audio audio;
	private int hlsTime;
	private int clearmeta;
	private int intelligentSwitch;
	private int autorotate;
	private int segment_time;
	private int start_segment_num;
	private int start_segment_time;
	private String segfile_acl;
	private String ss;



	public String getF() {
		return f;
	}

	public void setF(String f) {
		this.f = f;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public Audio getAudio() {
		return audio;
	}

	public void setAudio(Audio audio) {
		this.audio = audio;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}


	public int getHlsTime() {
		return hlsTime;
	}

	public void setHlsTime(int hlsTime) {
		this.hlsTime = hlsTime;
	}

	public int getClearmeta() {
		return clearmeta;
	}

	public void setClearmeta(int clearmeta) {
		this.clearmeta = clearmeta;
	}

	public int getShortSide() {
		return shortSide;
	}

	public void setShortSide(int shortSide) {
		this.shortSide = shortSide;
	}

	public int getIntelligentSwitch() {
		return intelligentSwitch;
	}

	public void setIntelligentSwitch(int intelligentSwitch) {
		this.intelligentSwitch = intelligentSwitch;
	}

	public int getAutorotate() {
		return autorotate;
	}

	public void setAutorotate(int autorotate) {
		this.autorotate = autorotate;
	}

	public int getSegment_time() {
		return segment_time;
	}

	public void setSegment_time(int segment_time) {
		this.segment_time = segment_time;
	}

	public int getStart_segment_num() {
		return start_segment_num;
	}

	public void setStart_segment_num(int start_segment_num) {
		this.start_segment_num = start_segment_num;
	}

	public int getStart_segment_time() {
		return start_segment_time;
	}

	public void setStart_segment_time(int start_segment_time) {
		this.start_segment_time = start_segment_time;
	}

	public String getSegfile_acl() {
		return segfile_acl;
	}

	public void setSegfile_acl(String segfile_acl) {
		this.segfile_acl = segfile_acl;
	}


	public int getSpriteflag() {
		return spriteflag;
	}

	public void setSpriteflag(int spriteflag) {
		this.spriteflag = spriteflag;
	}

	public int getSpritew() {
		return spritew;
	}

	public void setSpritew(int spritew) {
		this.spritew = spritew;
	}

	public int getSpriteh() {
		return spriteh;
	}

	public void setSpriteh(int spriteh) {
		this.spriteh = spriteh;
	}

	public String getSs() {
		return ss;
	}

	public void setSs(String ss) {
		this.ss = ss;
	}



}
