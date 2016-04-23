package com.wangw.goodhelp.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.exlogcat.L;
import com.roger.catloadinglibrary.CatLoadingView;
import com.wangw.goodhelp.R;
import com.wangw.goodhelp.common.UserManager;
import com.wangw.goodhelp.model.UserInfo;
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

    public void showLoading(){
        showLoading(true);
    }

    public void showLoading(boolean cancelable){
        if(mLoading == null){
            mLoading = new CatLoadingView();
        }
        mLoading.setCancelable(cancelable);
        try {
            mLoading.show(getSupportFragmentManager(), "");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void hidenLoading(){
        if(mLoading != null){
            try {
                mLoading.dismiss();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        mLoading = null;
    }

    protected void jumpToLogin(){
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    protected boolean isLogin(){
        return UserManager.isLogin();
    }

    protected UserInfo getUserInfo(){
        return UserManager.getUserInfo();
    }

    protected String getUid(){
        return isLogin() ? getUserInfo().getUser_id() : "";
    }

    protected void showToast(int resId){
        Toast.makeText(this,resId,Toast.LENGTH_SHORT).show();
    }

    public void showToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    protected void onFail(Throwable e){
        hidenLoading();
        if(e != null){
            L.e(e.getMessage());
            showToast("网络错误,请稍后重试");
        }
    }

}
