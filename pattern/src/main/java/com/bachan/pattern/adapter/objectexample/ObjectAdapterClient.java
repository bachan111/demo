package com.bachan.pattern.adapter.objectexample;

//客户端代码
public class ObjectAdapterClient {
    public static void main(String[] args) {
        System.out.println("对象适配器模式测试：");
        ObjectAdaptee adaptee = new ObjectAdaptee();
        ObjectTarget target = new ObjectAdapter(adaptee);
        target.request();
    }
}
