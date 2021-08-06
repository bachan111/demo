package com.bachan.pattern.decorator.example3;

public class SugarDecorator extends Decorator{

    public SugarDecorator(Coffee mCoffee) {
        super(mCoffee);
    }

    @Override
    public int getPrice() {
        return mCoffee.getPrice() + 22;
    }

    @Override
    public String getName() {
        return "SugarDecorator";
    }
}
