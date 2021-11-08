package com.example.lib_rxjava;

import io.reactivex.Observable;
import io.reactivex.Observer;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("citys")
    Observable<AllCity> getAllCity(@Query("key") String key);

    Observable<String> listRepos(String user);
}
