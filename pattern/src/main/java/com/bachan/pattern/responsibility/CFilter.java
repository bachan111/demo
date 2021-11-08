package com.bachan.pattern.responsibility;

public class CFilter implements Filter{
    @Override
    public void doFilter(Request request, FilterChain filterChain) {
        request.setRequestValue(request.getRequestValue().replace("C","哈哈"));
        filterChain.doFilter(request,  filterChain);
    }
}
