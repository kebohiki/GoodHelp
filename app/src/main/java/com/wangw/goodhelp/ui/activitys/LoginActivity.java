package com.wangw.goodhelp.ui.activitys;

import android.os.Bundle;
import android.view.View;

import com.wangw.goodhelp.R;
import com.wangw.goodhelp.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initTitleBar("登录");

    }


    @OnClick(R.id.btn_login)
    public void onClick(View view){
        showLoading();
    }

}
