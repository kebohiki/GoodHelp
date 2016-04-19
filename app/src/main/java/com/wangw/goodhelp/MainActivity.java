package com.wangw.goodhelp;

import android.content.Intent;
import android.os.Bundle;

import com.wangw.goodhelp.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!isLogin()){
            jumpToLogin();
            return;
        }

        setContentView(R.layout.activity_main);

        initTitleBar("微商云助手");



    }
}
