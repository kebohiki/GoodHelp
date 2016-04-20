package com.wangw.goodhelp;

import android.content.Intent;
import android.os.Bundle;

import com.wangw.goodhelp.base.BaseActivity;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    private boolean isExit;

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


}
