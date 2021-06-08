package com.cbf.demo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.cbf.demo.bean.LoginResponse;
import com.cbf.demo.bean.Request;
import com.cbf.demo.bean.Response;
import com.cbf.demo.net.Http;

import org.w3c.dom.Text;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivityAAA";
    private TextView mLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLabel = findViewById(R.id.mLabel);
    }


    public void mNetwork(View view) {
        getNetWorkData();
    }

    private void getNetWorkData() {
        Http.login(new Request("kang003", "zkxy6688", "diting"), new Observer<Response<LoginResponse>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Response<LoginResponse> loginResponseResponse) {
                Log.e(TAG, "response:" + loginResponseResponse.toString());
                mLabel.setText("response:" + loginResponseResponse.toString());
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "error:" + e.getCause());
                mLabel.setText("error:" + e.getCause());
            }

            @Override
            public void onComplete() {

            }
        });
    }

}
