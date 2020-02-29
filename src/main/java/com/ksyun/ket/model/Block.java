package com.ksyun.ket.model;

public class Block {
    private String path; //path
    private String key;
    private String iv;
    private String sha1;
    private int size;

    public Block(String path, String key, String iv) {
        this.path = path;
        this.key = key;
        this.iv = iv;
    }

    public Block(String path, String key, String iv, String sha1, int size) {
        this.path = path;
        this.key = key;
        this.iv = iv;
        this.sha1 = sha1;
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public String getSha1() {
        return sha1;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
