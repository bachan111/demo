package com.bachan.pattern.decorator.example2;

public class VIPDiscountDecorator extends DiscountDecorator {

    public VIPDiscountDecorator(BaseDiscount component) {
        super(component);
    }

    @Override
    public void discountForVip() {
        component.discount();
        System.out.println("扩展Vip用户折扣业务功能");
    }

    @Override
    public void discountForFestival() {
       //do nothing
    }

    @Override
    public void discount() {
        component.discount();
    }
}
