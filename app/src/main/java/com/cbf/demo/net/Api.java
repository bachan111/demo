package com.cbf.demo.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author：practicing
 * @date:2021/6/8 0008 17:22
 * @description:
 */
public class Api {
    private static final String REQUEST_PATH = "http://192.168.1.33:19000/api/";

    private OkHttpClient getOk() {
        return new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)//连接超时时间
                .writeTimeout(30, TimeUnit.SECONDS)//设置写操作超时时间
                .readTimeout(30, TimeUnit.SECONDS)//设置读操作超时时间
                .build();
    }

    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .client(getOk())//设置使用okhttp网络请求
                .baseUrl(REQUEST_PATH)//设置服务器路径
                .addConverterFactory(GsonConverterFactory.create())//添加转化库，默认是Gson
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//添加回调库，采用RxJava
                .build();
    }

    private static class SingletonHolder {
        private static final Api INSTANCE = new Api();
    }

    public static Api getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public <T> T create(Class<T> service) {
        return getRetrofit().create(service);
    }
}
