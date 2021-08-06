package com.bachan.pattern.decorator.example3;

public class Client {
    public static void main(String[] args) {
        Coffee simple, milk;
        simple = new SimpleDecorator();
        milk = new MilkDecorator(simple);
        int price = milk.getPrice();
        System.out.println("name:" + milk.getName() + "; price:" + price);
    }
}
