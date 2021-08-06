package com.bachan.pattern.decorator.example2;

public class Client {
    public static void main(String[] args) {
        //客户端使用
        BaseDiscount vipDiscountBusiness, festivalDiscountBusiness;
        DiscountDecorator vipDiscountDecorator, festivalDiscountDecorator;

        vipDiscountBusiness = new VIPDiscountBusiness();
        festivalDiscountBusiness = new FestivalDiscountBusiness();
        vipDiscountDecorator = new VIPDiscountDecorator(vipDiscountBusiness);
        festivalDiscountDecorator = new FestivalDiscountDecorator(festivalDiscountBusiness);

        vipDiscountDecorator.discountForVip();
        festivalDiscountDecorator.discountForFestival();
    }
}
