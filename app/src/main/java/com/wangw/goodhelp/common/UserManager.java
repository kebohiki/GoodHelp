package com.wangw.goodhelp.common;

import com.orhanobut.hawk.Hawk;
import com.wangw.goodhelp.model.UserInfo;
import com.wangw.goodhelp.utils.SpUtils;

/**
 * Created by wangw on 16/4/19.
 */
public class UserManager {

    private static final String KEY_USERINFO = "userinfo_key";

    public static void logout(){
        SpUtils.saveBool("login_status",false);
        Hawk.remove(KEY_USERINFO);
    }

    public static void setLoginSuccess(String userName){
        SpUtils.saveBool("login_status",true);
        SpUtils.saveString("username", userName);
    }

    public static String getUserName(){
       return SpUtils.getString("username","");
    }

    public static boolean isLogin(){
       return SpUtils.getBool("login_status",false);
    }

    public static void saveUserInfo(UserInfo info){
        Hawk.put(KEY_USERINFO,info);
    }

    public static UserInfo getUserInfo(){
        return Hawk.get(KEY_USERINFO);
    }

    public static String getUid(){
        return hasUserInfo() ? getUserInfo().getUser_id() : "";
    }

    public static boolean hasUserInfo(){
        return getUserInfo() != null;
    }

}
