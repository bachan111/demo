# rxjava

1. 背压：指在异步场景中，被观察者发送事件速度远快于观察者的处理速度的情况下，一种告诉上游的被观察者降低发送速度的策略。


 ## 1. Observable创建方式：
 1. create()方式创建 ：这里传入了一个 ObservableOnSubscribe对象作为参数，它的作用相当于一个计划表，
 当 Observable被订阅的时候，ObservableOnSubscribe的subscribe()方法会自动被调用，事件序列就会依照设定依次触发（对于上面的代码，就是观察者Observer 将会被调用一次 onNext()）。
 这样，由被观察者调用了观察者的回调方法，就实现了由被观察者向观察者的事件传递，即观察者模式。
 ```
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
```
 2. just方式 ： 使用just( )，将为你创建一个Observable并自动为你调用onNext( )发射数据。通过just( )方式 直接触发onNext()，just中传递的参数将直接在Observer的onNext()方法中接收到。
 ```
Observable<String> observable = Observable.just("Hello");
 ```
 3. fromIterable()方式 : 使用fromIterable()，遍历集合，发送每个item。相当于多次回调onNext()方法，每次传入一个item。
  注意：Collection接口是Iterable接口的子接口，所以所有Collection接口的实现类都可以作为Iterable对象直接传入fromIterable()方法。

 ```
 List<String> list = new ArrayList<String>();
        for(int i =0;i<10;i++){
            list.add("Hello"+i);
        }
        Observable<String> observable = Observable.fromIterable((Iterable<String>) list);
 ```

 4. defer()方式 : 当观察者订阅时，才创建Observable，并且针对每个观察者创建都是一个新的Observable。以何种方式创建这个Observable对象，当满足回调条件后，就会进行相应的回调。
 
 ```
Observable<String> observable = Observable.defer(new Callable<ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> call() throws Exception {
                return Observable.just("hello");
            }
        });
 ```

 5. interval( )方式 : 创建一个按固定时间间隔发射整数序列的Observable，可用作定时器。即按照固定2秒一次调用onNext()方法。
 
 ```
 Observable<String> observable = Observable.interval(2, TimeUnit.SECONDS);
 ```

 6. range( )方式: 创建一个发射特定整数序列的Observable，第一个参数为起始值，第二个为发送的个数，如果为0则不发送，负数则抛异常。上述表示发射1到20的数。即调用20次nNext()方法，依次传入1-20数字。

```
Observable<Integer> observable = Observable.range(1,20);
```

 7. timer( )方式 : 创建一个Observable，它在一个给定的延迟后发射一个特殊的值，即表示延迟2秒后，调用onNext()方法。
 
 ```
Observable<Integer> observable = Observable.timer(2, TimeUnit.SECONDS);
 ```

8. repeat( )方式 :创建一个Observable，该Observable的事件可以重复调用。
```
Observable<Integer> observable = Observable.just(123).repeat();
```

除了Observable(被观察者)的创建之外，RxJava 2.x 还提供了多个函数式接口 ，用于实现简便式的观察者模式。以Consumer为例，我们可以实现简便式的观察者模式:

```
Observable.just("hello").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });
```

其中Consumer中的accept()方法接收一个来自Observable的单个值。Consumer就是一个观察者。其他函数式接口可以类似应用。

## 2. RxJava中的操作符

1. map()操作符:map()操作符，就是把原来的Observable对象转换成另一个Observable对象，同时将传输的数据进行一些灵活的操作，方便Observer获得想要的数据形式。
```
Observable<Integer> observable1 = Observable.just("hello").map(new Function<String, Integer>() {
            @org.jetbrains.annotations.NotNull
            @Override
            public Integer apply(@org.jetbrains.annotations.NotNull String s) throws Exception {
                return s.length();
            }
        });
```

2. flatMap()操作符:flatMap()对于数据的转换比map()更加彻底，如果发送的数据是集合，flatmap()重新生成一个Observable对象，并把数据转换成Observer想要的数据形式。它可以返回任何它想返回的Observable对象。
```
  List list = new ArrayList();
        Observable<Integer> observable2 = Observable.just(list).flatMap(new Function<List, ObservableSource<?>>() {
            @org.jetbrains.annotations.NotNull
            @Override
            public ObservableSource<?> apply(@org.jetbrains.annotations.NotNull List list) throws Exception {
                return Observable.fromIterable(list);
            }
        });
```

3. filter()操作符 : filter()操作符根据test()方法中，根据自己想过滤的数据加入相应的逻辑判断，
返回true则表示数据满足条件，返回false则表示数据需要被过滤。最后过滤出的数据将加入到新的Observable对象中，方便传递给Observer想要的数据形式。
```
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
```

4. take()操作符:take()操作符：输出最多指定数量的结果。

```
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
```

5.doOnNext() :doOnNext()允许我们在每次输出一个元素之前做一些额外的事情。

```
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
```

## 3. Scheduler

简介：在不指定线程的情况下， RxJava 遵循的是线程不变的原则，即：在哪个线程调用 subscribe()，就在哪个线程生产事件；在哪个线程生产事件，就在哪个线程消费事件。如果需要切换线程，就需要用到 Scheduler （调度器）。
   在RxJava 中，Scheduler，相当于线程控制器，RxJava 通过它来指定每一段代码应该运行在什么样的线程。RxJava 已经内置了几个 Scheduler ，它们已经适合大多数的使用场景。

1. Schedulers.immediate() ：直接在当前线程运行，相当于不指定线程。这是默认的 Scheduler。
2. Schedulers.newThread() ：总是启用新线程，并在新线程执行操作。
3. Schedulers.io() ：I/O 操作（读写文件、读写数据库、网络信息交互等）所使用的 Scheduler。行为模式和 newThread() 差不多，
区别在于 io() 的内部实现是用一个无数量上限的线程池，可以重用空闲的线程，因此多数情况下 io() 比 newThread() 更有效率。不要把计算工作放在 io() 中，可以避免创建不必要的线程。

subscribeOn(): 指定Observable(被观察者)所在的线程，或者叫做事件产生的线程。
observeOn(): 指定 Observer(观察者)所运行在的线程，或者叫做事件消费的线程。
```
 Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@org.jetbrains.annotations.NotNull ObservableEmitter<Integer> e) throws Exception {
                Log.d("所在的线程：", Thread.currentThread().getName());
                Log.d("发送的数据:", 1 + "");
                e.onNext(1);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@org.jetbrains.annotations.NotNull Integer integer) throws Exception {
                        Log.d("所在的线程：", Thread.currentThread().getName());
                        Log.d("接收到的数据:", "integer:" + integer);
                    }
                });
```

