package com.wangw.goodhelp;

import android.app.Application;

import com.exlogcat.L;

/**
 * Created by wangw on 16/4/19.
 */
public class ExApplication extends Application {

    private static ExApplication mInstatnce;

    public static ExApplication getInstatnce(){
        return mInstatnce;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstatnce = this;

        onInitLog();
    }

    private void onInitLog() {
        L.init("GOODHELP")
        .hideThreadInfo()
        .methodCount(2);

    }


}
