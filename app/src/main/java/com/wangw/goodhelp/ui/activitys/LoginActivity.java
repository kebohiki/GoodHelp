package com.wangw.goodhelp.ui.activitys;

import android.os.Bundle;
import android.view.View;

import com.wangw.goodhelp.R;
import com.wangw.goodhelp.api.ServiceHelper;
import com.wangw.goodhelp.base.BaseActivity;
import com.wangw.goodhelp.model.Response;
import com.wangw.goodhelp.model.UserInfo;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;

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
        onLogin("","");
    }


    private void onLogin(String userName,String pwd){
        showLoading();
        Map<String,String> map = new HashMap<>();
        map.put("username",userName);
        map.put("passwd", pwd);
        ServiceHelper.GetApi().userLogin(map);
    }

}
