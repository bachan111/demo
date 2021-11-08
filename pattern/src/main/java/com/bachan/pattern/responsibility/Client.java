package com.bachan.pattern.responsibility;

public class Client {

    public static void main(String[] args) {
        Request request = new Request("ACBKJVBOWBOUVCIYWBWNCPBWOVUDOUWBDOVWBPNC");
        FilterChain filterChain = new FilterChain();
        filterChain.addFilter(new AFilter());
        filterChain.addFilter(new BFilter());
        filterChain.addFilter(new CFilter());
        filterChain.doFilter(request,  filterChain);
        System.out.println("结果：" + request.getRequestValue());
    }
}
