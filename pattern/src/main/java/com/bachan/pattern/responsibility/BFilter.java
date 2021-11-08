package com.bachan.pattern.responsibility;

public class BFilter implements Filter {
    @Override
    public void doFilter(Request request,  FilterChain filterChain) {
        request.setRequestValue(request.getRequestValue().replace("B","嘻嘻"));
        filterChain.doFilter(request,  filterChain);
    }
}
