package com.bachan.pattern.adapter.classexample;

//客户端代码
public class ClassAdapterClient {
    public static void main(String[] args) {
        System.out.println("类适配器模式测试：");
        ClassTarget target = new ClassAdapter();
        target.request();
    }
}
