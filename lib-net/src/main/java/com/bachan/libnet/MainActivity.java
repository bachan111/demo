package com.bachan.libnet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import rx.Observer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void testNet(View view) {
        Business.getTop(0, 20, new Observer<movieTopReq>() {
            @Override
            public void onCompleted() {
                Log.e("main","完成");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("main",e.toString());
            }

            @Override
            public void onNext(movieTopReq movieTopReq) {
                Log.e("main",movieTopReq.toString());
            }
        });
    }
}