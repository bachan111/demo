package com.bachan.pattern.adapter.objectexample;

//对象适配器类
public class ObjectAdapter implements ObjectTarget{
    private ObjectAdaptee adaptee;

    public ObjectAdapter(ObjectAdaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        adaptee.specificRequest();
    }
}
