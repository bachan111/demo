package com.bachan.libnet;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface Api {
    @GET("top250")
    Observable<movieTopReq> getMovieTop(@Query("start") int start, @Query("count") int count);


}
