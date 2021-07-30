package com.example.lib_rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    /**
     * 使用create( )创建Observable最基本的创建方式。
     * 可以看到，这里传入了一个 ObservableOnSubscribe对象作为参数，
     * 它的作用相当于一个计划表，当 Observable被订阅的时候，ObservableOnSubscribe的subscribe()方法会自动被调用
     * ，事件序列就会依照设定依次触发（对于上面的代码，就是观察者Observer 将会被调用一次 onNext()）。
     * 这样，由被观察者调用了观察者的回调方法，就实现了由被观察者向观察者的事件传递，即观察者模式。
     */
    private void init() {

        //第一步
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@org.jetbrains.annotations.NotNull ObservableEmitter<String> e) throws Exception {
                e.onNext("发射数据");
            }
        });

        //第二步
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(@org.jetbrains.annotations.NotNull Disposable d) {

            }

            @Override
            public void onNext(@org.jetbrains.annotations.NotNull String s) {
                System.out.println("我接收到数据了");
            }

            @Override
            public void onError(@org.jetbrains.annotations.NotNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        //第三步
        observable.subscribe(observer);


        Observable<Integer> observable1 = Observable.just("hello").map(new Function<String, Integer>() {
            @org.jetbrains.annotations.NotNull
            @Override
            public Integer apply(@org.jetbrains.annotations.NotNull String s) throws Exception {
                return s.length();
            }
        });

        List list = new ArrayList();
        Observable<Integer> observable2 = Observable.just(list).flatMap(new Function<List, ObservableSource<?>>() {
            @org.jetbrains.annotations.NotNull
            @Override
            public ObservableSource<?> apply(@org.jetbrains.annotations.NotNull List list) throws Exception {
                return Observable.fromIterable(list);
            }
        });

        List<String> list1 = new ArrayList<>();
        Observable.just(list1).flatMap(new Function<List<String>, ObservableSource<?>>() {
            @org.jetbrains.annotations.NotNull
            @Override
            public ObservableSource<?> apply(@org.jetbrains.annotations.NotNull List<String> strings) throws Exception {
                return Observable.fromIterable(strings);
            }
        }).filter(new Predicate<Object>() {
            @Override
            public boolean test(@org.jetbrains.annotations.NotNull Object o) throws Exception {
                String newStr = (String)o;
                if (newStr.charAt(5) -'0' >5){
                    return true;
                }
                return false;
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(@org.jetbrains.annotations.NotNull Object o) throws Exception {
                System.out.println((String)o);
            }
        });

        Observable.just(list1).flatMap(new Function<List<String>, ObservableSource<?>>() {
            @org.jetbrains.annotations.NotNull
            @Override
            public ObservableSource<?> apply(@org.jetbrains.annotations.NotNull List<String> strings) throws Exception {
                Observable.fromIterable(strings);
            }
        }).take(5).subscribe(new Consumer<Object>() {
            @Override
            public void accept(@org.jetbrains.annotations.NotNull Object o) throws Exception {
                System.out.println((String)o);
            }
        });

        Observable.just(list1).flatMap(new Function<List<String>, ObservableSource<?>>() {
            @org.jetbrains.annotations.NotNull
            @Override
            public ObservableSource<?> apply(@org.jetbrains.annotations.NotNull List<String> strings) throws Exception {
                Observable.fromIterable(strings);
            }
        }).take(5).doOnNext(new Consumer<Object>() {
            @Override
            public void accept(@org.jetbrains.annotations.NotNull Object o) throws Exception {
                System.out.println("准备工作");
            }
        }).safeSubscribe(new Consumer<Object>(){
            @Override
            public void accept(@org.jetbrains.annotations.NotNull Object o) throws Exception {
                System.out.println((String)s);
            }
        });

    }


}