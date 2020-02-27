package com.ksyun.ket.model;

import java.util.ArrayList;
import java.util.List;

public class SrcInfo {
	private String path;
	private int index;
	private String type;
	private List<Block> block;

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
	
}
