package com.cbf.httpserver;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * @author：practicing
 * @date:2021/4/13 0013 18:38
 * @description:
 */
public class OkHttpUtils {
    private static OkHttpClient client = null;

    public static OkHttpClient getInstance() {

        if (client == null) {
            synchronized (OkHttpUtils.class) {
                if (client == null){
                    client = new OkHttpClient.Builder()
                            .addInterceptor(new LoggerInterceptor())
                            .connectTimeout(15, TimeUnit.SECONDS)
                            .writeTimeout(20, TimeUnit.SECONDS)
                            .readTimeout(20, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
        return client;
    }
}
