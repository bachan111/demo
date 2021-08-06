package com.bachan.pattern.decorator.example3;

public abstract class Decorator extends Coffee {
    protected Coffee mCoffee;

    public Decorator(Coffee mCoffee) {
        this.mCoffee = mCoffee;
    }
}
