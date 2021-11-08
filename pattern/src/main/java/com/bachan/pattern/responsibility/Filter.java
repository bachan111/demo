package com.bachan.pattern.responsibility;

public interface Filter {
    void doFilter(Request request,FilterChain filterChain);
}
