package com.cbf.demo.jetpack;

import android.os.Bundle;

import com.cbf.demo.R;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    /**
     * onSupportNavigateUp()方法的重写，意味着Activity将它的 back键点击事件的委托出去，
     * 如果当前并非栈中顶部的Fragment, 那么点击back键，返回上一个Fragment。
     *
     * @return
     */
    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
    }
}
