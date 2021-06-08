package com.cbf.demo.net;

import com.cbf.demo.bean.LoginResponse;
import com.cbf.demo.bean.Request;
import com.cbf.demo.bean.Response;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author：practicing
 * @date:2021/6/8 0008 16:06
 * @description:
 */
public interface ApiService {

    @POST("account/testeeLogin")
    Observable<Response<LoginResponse>> login(@Body Request request);
}
