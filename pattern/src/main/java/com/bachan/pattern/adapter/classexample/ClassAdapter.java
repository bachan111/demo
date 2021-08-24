package com.bachan.pattern.adapter.classexample;

//类适配器类
public class ClassAdapter extends ClassAdaptee implements ClassTarget{
    @Override
    public void request() {
        specificRequest();
    }
}
