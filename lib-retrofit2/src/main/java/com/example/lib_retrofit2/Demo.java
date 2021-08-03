package com.example.lib_retrofit2;

import android.widget.Toast;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Demo {
    private void init(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("url")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<Resopnse> bodyCall = api.post3(new HashMap<>());
        bodyCall.enqueue(new Callback<Resopnse>() {
            @Override
            public void onResponse(Call<Resopnse> call, Response<Resopnse> response) {
                Resopnse res = response.body();
                Toast.makeText(this,res.toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Resopnse> call, Throwable t) {

            }
        });
    }
}
