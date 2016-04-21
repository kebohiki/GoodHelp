package com.wangw.goodhelp;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.wangw.goodhelp.base.BaseActivity;
import com.wangw.goodhelp.ui.activitys.SettingActivity;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    public static MainActivity mainActivity;
    private boolean isExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = this;
        setContentView(R.layout.activity_main);
        initTitleBar("微商云助手");
    }




    @Override
    protected void onResume() {
        super.onResume();
        if(!isLogin()){
            jumpToLogin();
            return;
        }
    }

    @Override
    protected void initTitleBar(String title) {
        super.initTitleBar(title);
        ImageView img = new ImageView(this);
        img.setImageResource(R.mipmap.setting_icon);
        mTitle.addRightView(img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            showToast("再按一次退出程序");
            // 利用handler延迟发送更改状态信息
            Observable.empty()
                    .delay(2000, TimeUnit.MILLISECONDS, Schedulers.newThread())
                    .subscribe(new Subscriber<Object>() {
                        @Override
                        public void onCompleted() {
                            isExit = false;
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onNext(Object o) {
                        }
                    });
        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainActivity = null;
    }
}
