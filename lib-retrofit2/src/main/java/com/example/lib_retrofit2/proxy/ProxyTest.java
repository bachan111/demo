package com.example.lib_retrofit2.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import retrofit2.http.GET;

public class ProxyTest {
    public static void main(String[] args){
        Itest itest = (Itest) Proxy.newProxyInstance(Itest.class.getClassLoader(), new Class<?>[]{Itest.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Integer a = (Integer) args[0];
                Integer b = (Integer) args[1];
                System.out.println("方法名："+method.getName());
                System.out.println("参数："+a +"  "+b);
                GET get = method.getAnnotation(GET.class);
                System.out.println("注解："+get.value());
                return null;
            }
        });
        itest.add(3,5);
    }
}
