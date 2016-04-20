package com.wangw.goodhelp.api;

import com.wangw.goodhelp.model.Response;
import com.wangw.goodhelp.model.TopicInfo;
import com.wangw.goodhelp.model.UserInfo;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by wangw on 2016/4/20.
 */
public interface ApiService {



    @POST("getAllTopics")
    Call<Response<TopicInfo>> getAllTopics(@Body Map<String,String> params, Callback<Response<TopicInfo>> callback);

    @POST("userLogin")
    Call<Response<UserInfo>> userLogin(@Body Map<String,String> params);

    @POST("userRegister")
    Call<Response<UserInfo>> userRegister(@Body Map<String,String> params, Callback<Response<UserInfo>> callback);


}
