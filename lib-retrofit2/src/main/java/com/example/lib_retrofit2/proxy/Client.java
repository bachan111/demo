package com.example.lib_retrofit2.proxy;

import com.example.lib_retrofit2.Api;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class Client {
    public static void main(String[] args){
        Api  api = (Api) Proxy.newProxyInstance(
                Api.class.getClassLoader(),
                new Class[]{Api.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("method = " + method.getName() + "   args = " + Arrays.toString(args));
                        return null;
                    }
                }
        );
        System.out.println(api.getClass());
        api.listRepos("user");
    }
}
