package com.wangw.goodhelp.api;

import java.util.HashMap;

/**
 * Created by wangw on 2016/4/27.
 */
public class RequestMap extends HashMap<String,Object> {

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Entry<String,Object> entry :this.entrySet()){
            builder.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append("&");
        }
        String str = builder.toString();
        str = str.substring(0,str.length() - 2);
        return str;//super.toString();
    }

    @Override
    public String put(String key, Object value) {
        return (String) super.put(key, value);
    }

    public RequestMap putParam(String key,Object value){
        put(key,value);
        return this;
    }


}
