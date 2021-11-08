package com.bachan.pattern.responsibility;

public class AFilter implements Filter{
    @Override
    public void doFilter(Request request, FilterChain filterChain) {
       String value = request.getRequestValue().replace("A","呵呵");
       request.setRequestValue(value);
       filterChain.doFilter(request, filterChain);
    }
}
