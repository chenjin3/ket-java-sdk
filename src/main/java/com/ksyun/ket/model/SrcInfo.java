package com.ksyun.ket.model;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SrcInfo {
	private String path;
	private int index;
	private String type;
	private List<Block> block;
	private List<Auth> auth;

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}


	public List<Block> getBlock() {
		return block;
	}

	public void setBlock(java.util.Collection<Block> block) {
		if (block != null) {
			block = new ArrayList<Block>(block);
		}
	}

	public void addBlockList(Block... blocks) {
		if (block == null) {
			block = new ArrayList<Block>();
		}
		for (Block block : blocks) {
			this.block.add(block);
		}
	}

	public List<Auth> getAuth() {
		return auth;
	}

	public void setAuth(java.util.Collection<Auth> auth_) {
		if (auth != null) {
			auth = new ArrayList<Auth>(auth_);
		}
	}

	public void addAuthList(Auth... auths) {
		if (auth == null) {
			auth = new ArrayList<Auth>();
		}
		for (Auth auth_ : auths) {
			this.auth.add(auth_);
		}
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
}
