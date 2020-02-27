package com.ksyun.ket.model;

public class Block {
    private String path; //path
    private String key;
    private String iv;
    private String sha1;
    private String size;

    public Block(String path, String key, String iv) {
        this.path = path;
        this.key = key;
        this.iv = iv;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
