package com.wangw.goodhelp.utils;

import android.text.TextUtils;
import android.widget.EditText;

/**
 * Created by wangw on 16/4/20.
 */
public class CommonUtils {

    public static boolean isNotNull(EditText txt){
        if(txt == null){
            return false;
        }

        String v = txt.getText().toString().trim();
        return !TextUtils.isEmpty(v);

    }

    public static boolean isPhoneNum(String num){
        return !TextUtils.isEmpty(num) && num.length() == 11;
    }

}
