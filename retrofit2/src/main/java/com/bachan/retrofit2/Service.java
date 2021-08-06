package com.bachan.retrofit2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Service {
    @GET("user/{user}/repos")
    Call<List<Integer>> listRep(@Path("user") String user);
}
