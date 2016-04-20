package com.wangw.goodhelp;

import com.wangw.goodhelp.model.UserInfo;
import com.wangw.goodhelp.utils.SpUtils;

/**
 * Created by wangw on 16/4/19.
 */
public class UserManager {


    public static void logout(){
        SpUtils.saveBool("login_status",false);
    }

    public static void setLoginSuccess(){
        SpUtils.saveBool("login_status",true);
    }

    public static boolean isLogin(){
       return SpUtils.getBool("login_status",false);
    }

    public static void saveUserInfo(UserInfo info){

    }

    public static UserInfo getUserInfo(){
        return null;
    }

    public static String getUid(){
        return "";
    }


}
