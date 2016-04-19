package com.wangw.goodhelp.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.roger.catloadinglibrary.CatLoadingView;
import com.wangw.goodhelp.R;
import com.wangw.goodhelp.UserManager;
import com.wangw.goodhelp.ui.activitys.LoginActivity;
import com.wangw.goodhelp.ui.views.TitleBarView;

/**
 * Created by wangw on 2016/4/19.
 */
public class BaseActivity extends FragmentActivity {

    protected TitleBarView mTitle;
    protected CatLoadingView mLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void initTitleBar(String title){
        mTitle = (TitleBarView) findViewById(R.id.titlebar);
        if(mTitle != null)
            mTitle.setTitle(title);
    }

    protected void showLoading(){
        if(mLoading == null){
            mLoading = new CatLoadingView();
        }
        mLoading.show(getSupportFragmentManager(),"");
    }

    protected void jumpToLogin(){
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    protected boolean isLogin(){
        return UserManager.isLogin();
    }

}
