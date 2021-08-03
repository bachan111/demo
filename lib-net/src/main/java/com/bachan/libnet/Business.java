package com.bachan.libnet;

import com.bachan.libnet.core.Manager;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Business {
    private static Api api = Manager.getInstance().create(Api.class);

    public static void getTop(int start, int count , Observer<movieTopReq> observer){
        setSubscribe(api.getMovieTop(start, count),observer);
    }

    private static <T> void setSubscribe(Observable<T> observable,Observer<T> observer){
        observable
                .subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
