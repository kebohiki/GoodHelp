package com.wangw.goodhelp.model;

/**
 * Created by wangw on 2016/4/20.
 */
public class Response<T> {


    /**
     * error : 0:没有错误 1：有错误
     * message : 错误信息
     * timestamp : 1461150319
     * data : null
     */

    private int error;
    private String message;
    private int timestamp;
    private T data;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess(){
        return error == 0;
    }

}
