package com.cbf.demo.net;

import com.cbf.demo.bean.LoginResponse;
import com.cbf.demo.bean.Request;
import com.cbf.demo.bean.Response;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author：practicing
 * @date:2021/6/8 0008 17:29
 * @description:
 */
public class Http {
    private static ApiService service = Api.getInstance().create(ApiService.class);

    public static void login(Request request, Observer<Response<LoginResponse>> observer) {
        setSubscribe(service.login(request), observer);
    }

    private static <T> void setSubscribe(Observable<T> observable, Observer<T> observer) {
        observable
//                .compose(ResponseTransformer.handleResult())
                .subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())//子线程访问网络
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .subscribe(observer);
    }
}
