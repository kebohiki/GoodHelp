package com.wangw.goodhelp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.wangw.goodhelp.ExApplication;

/**
 * Created by wangw on 16/4/19.
 */
public class SpUtils {

    private static SharedPreferences mSp;
    private static final String KEY = "GOODHELP_CONFIG";

    public static void saveBool(String key,Boolean value){
        getSharePreferences()
                .edit()
                .putBoolean(key,value)
                .commit();
    }

    public static boolean getBool(String key,Boolean defaultValue){
        return getSharePreferences().getBoolean(key,defaultValue);
    }

    private static SharedPreferences getSharePreferences(){
        if(mSp == null) {
            synchronized (SpUtils.class) {
                mSp = ExApplication.getInstatnce().getSharedPreferences(KEY, Context.MODE_PRIVATE);
            }
        }
        return mSp;
    }

}
