package com.example.lib_rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        init();
//        test2();
    }

    private void test2(){

        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();//这里处理所有的Rxjava异常
            }
        });
        Retrofit retrofit = create("http://v.juhe.cn/weather/");
        Api api = retrofit.create(Api.class);
        Observable<AllCity> observable = api.getAllCity("北京");
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .flatMap(new Function<AllCity, ObservableSource<City>>() {
                    @Override
                    public ObservableSource<City> apply(AllCity city) throws Exception {
                        ArrayList<City> result = city.getResult();
                        return Observable.fromIterable(result);
                    }
                })
                .filter(new Predicate<City>() {
                    @Override
                    public boolean test(City city) throws Exception {
                        String id = city.getId();
                        if(Integer.parseInt(id)<5){
                            return true;
                        }
                        return false;
                    }
                })
                .subscribe(new Consumer<City>() {
                    @Override
                    public void accept(City city) throws Exception {
                        System.out.println(city);
                    }
                });
    }

//    private void test() {
//        Api api = create("http://v.juhe.cn/weather/").create(Api.class);
//        Observable<AllCity> observable = api.getAllCity("北京");
//        observable
//                .subscribeOn(Schedulers.io())
//                .flatMap(new Function<AllCity, ObservableSource<City>>() {
//                    @Override
//                    public ObservableSource<City> apply( AllCity allCity) throws Exception {
//                        ArrayList<City> cities = allCity.getReason();
//                        return Observable.fromIterable(cities);
//                    }
//                })
//                .filter(new Predicate<City>() {
//                    @Override
//                    public boolean test(City city) throws Exception {
//                        String id = city.getId();
//                        if (Integer.parseInt(id) < 5) {
//                            return true;
//                        }
//                        return false;
//                    }
//                })
//                .subscribe(new Consumer<City>() {
//                    @Override
//                    public void accept( City city) throws Exception {
//                        System.out.println(city.toString());
//                    }
//                });
//    }

    private Retrofit create(String url) {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(9, TimeUnit.SECONDS);
        return new Retrofit.Builder().baseUrl(url)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public void onClickTest(View view) {
        test2();
    }


    /**
     * 使用create( )创建Observable最基本的创建方式。
     * 可以看到，这里传入了一个 ObservableOnSubscribe对象作为参数，
     * 它的作用相当于一个计划表，当 Observable被订阅的时候，ObservableOnSubscribe的subscribe()方法会自动被调用
     * ，事件序列就会依照设定依次触发（对于上面的代码，就是观察者Observer 将会被调用一次 onNext()）。
     * 这样，由被观察者调用了观察者的回调方法，就实现了由被观察者向观察者的事件传递，即观察者模式。
     */
//    private void init() {
//
//        //第一步
//        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(@org.jetbrains.annotations.NotNull ObservableEmitter<String> e) throws Exception {
//                e.onNext("发射数据");
//            }
//        });
//
//        //第二步
//        Observer<String> observer = new Observer<String>() {
//            @Override
//            public void onSubscribe(@org.jetbrains.annotations.NotNull Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(@org.jetbrains.annotations.NotNull String s) {
//                System.out.println("我接收到数据了");
//            }
//
//            @Override
//            public void onError(@org.jetbrains.annotations.NotNull Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        };
//
//        //第三步
//        observable.subscribe(observer);
//
//
//        Observable<Integer> observable1 = Observable.just("hello").map(new Function<String, Integer>() {
//            @org.jetbrains.annotations.NotNull
//            @Override
//            public Integer apply(@org.jetbrains.annotations.NotNull String s) throws Exception {
//                return s.length();
//            }
//        });
//
//        List list = new ArrayList();
//        Observable<Integer> observable2 = Observable.just(list).flatMap(new Function<List, ObservableSource<?>>() {
//            @org.jetbrains.annotations.NotNull
//            @Override
//            public ObservableSource<?> apply(@org.jetbrains.annotations.NotNull List list) throws Exception {
//                return Observable.fromIterable(list);
//            }
//        });
//
//        List<String> list1 = new ArrayList<>();
//        Observable.just(list1).flatMap(new Function<List<String>, ObservableSource<?>>() {
//            @org.jetbrains.annotations.NotNull
//            @Override
//            public ObservableSource<?> apply(@org.jetbrains.annotations.NotNull List<String> strings) throws Exception {
//                return Observable.fromIterable(strings);
//            }
//        }).filter(new Predicate<Object>() {
//            @Override
//            public boolean test(@org.jetbrains.annotations.NotNull Object o) throws Exception {
//                String newStr = (String) o;
//                if (newStr.charAt(5) - '0' > 5) {
//                    return true;
//                }
//                return false;
//            }
//        }).subscribe(new Consumer<Object>() {
//            @Override
//            public void accept(@org.jetbrains.annotations.NotNull Object o) throws Exception {
//                System.out.println((String) o);
//            }
//        });
//
//        Observable.just(list1).flatMap(new Function<List<String>, ObservableSource<?>>() {
//            @org.jetbrains.annotations.NotNull
//            @Override
//            public ObservableSource<?> apply(@org.jetbrains.annotations.NotNull List<String> strings) throws Exception {
//                Observable.fromIterable(strings);
//            }
//        }).take(5).subscribe(new Consumer<Object>() {
//            @Override
//            public void accept(@org.jetbrains.annotations.NotNull Object o) throws Exception {
//                System.out.println((String) o);
//            }
//        });
//
//        Observable.just(list1).flatMap(new Function<List<String>, ObservableSource<?>>() {
//            @org.jetbrains.annotations.NotNull
//            @Override
//            public ObservableSource<?> apply(@org.jetbrains.annotations.NotNull List<String> strings) throws Exception {
//                Observable.fromIterable(strings);
//            }
//        }).take(5).doOnNext(new Consumer<Object>() {
//            @Override
//            public void accept(@org.jetbrains.annotations.NotNull Object o) throws Exception {
//                System.out.println("准备工作");
//            }
//        }).safeSubscribe(new Consumer<Object>() {
//            @Override
//            public void accept(@org.jetbrains.annotations.NotNull Object o) throws Exception {
//                System.out.println((String) s);
//            }
//        });
//
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(@org.jetbrains.annotations.NotNull ObservableEmitter<Integer> e) throws Exception {
//                Log.d("所在的线程：", Thread.currentThread().getName());
//                Log.d("发送的数据:", 1 + "");
//                e.onNext(1);
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(@org.jetbrains.annotations.NotNull Integer integer) throws Exception {
//                        Log.d("所在的线程：", Thread.currentThread().getName());
//                        Log.d("接收到的数据:", "integer:" + integer);
//                    }
//                });
//
//    }


}