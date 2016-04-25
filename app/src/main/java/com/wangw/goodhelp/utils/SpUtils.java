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

    public static void saveString(String key,String value){
        getSharePreferences()
                .edit()
                .putString(key, value)
                .commit();
    }

    public static String getString(String key,String defaultValue){
        return getSharePreferences().getString(key,defaultValue);
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
