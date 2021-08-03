package com.example.lib_retrofit2.proxy;

import retrofit2.http.GET;

public interface Itest {
    @GET("/AAA")
    public void add(int a);
}
