package com.wangw.goodhelp;

import android.app.Application;

import com.exlogcat.L;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;
import com.orhanobut.hawk.LogLevel;

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
        initHawk();
    }

    private void initHawk() {
        Hawk.init(this)
                .setEncryptionMethod(HawkBuilder.EncryptionMethod.MEDIUM)
                .setStorage(HawkBuilder.newSqliteStorage(this))
                .setLogLevel(LogLevel.FULL)
                .build();
    }

    private void onInitLog() {
        L.init("GOODHELP")
        .hideThreadInfo()
        .methodCount(2);

    }


}
