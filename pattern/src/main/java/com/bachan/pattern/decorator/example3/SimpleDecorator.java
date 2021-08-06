package com.bachan.pattern.decorator.example3;

public class SimpleDecorator extends Coffee{
    @Override
    public int getPrice() {
        return 99;
    }

    @Override
    public String getName() {
        return "simple";
    }
}
