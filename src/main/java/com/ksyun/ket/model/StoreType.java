package com.ksyun.ket.model;

public enum StoreType {

    // 因为已经定义了带参数的构造器，所以在列出枚举值时必须传入对应的参数
    OSS("oss"), KSS("kss"), KS3("ks3"), S3("s3");

    // 定义一个 private 修饰的实例变量
    private String type;

    // 定义一个带参数的构造器，枚举类的构造器只能使用 private 修饰
    private StoreType(String type) {
        this.type = type;
    }

    // 定义 get set 方法
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
