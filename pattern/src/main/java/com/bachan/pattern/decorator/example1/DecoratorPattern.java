package com.bachan.pattern.decorator.example1;

public class DecoratorPattern {
    public static void main(String[] args) {
        Component p = new ConcreteComponent();
        p.operation();

        System.out.println("---------------------------------");

        Component d = new ConcreteDecorator(p);
        d.operation();
    }
}
