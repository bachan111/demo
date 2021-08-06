package com.bachan.retrofit2;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

public class Api {
    private void init(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .build();
        Service service = retrofit.create(Service.class);
        Call<List<Integer>> listRep = service.listRep("ctocat");
    }

}
