package com.bachan.pattern.decorator.example2;

public class VIPDiscountBusiness extends BaseDiscount {

    @Override
    public void discount() {
        System.out.println("处理vip用户的折扣业务");
    }
}
