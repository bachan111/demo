package com.bachan.pattern.decorator.example2;

public class FestivalDiscountBusiness extends BaseDiscount{
    @Override
    public void discount() {
        System.out.println("处理节假日折扣业务");
    }
}
