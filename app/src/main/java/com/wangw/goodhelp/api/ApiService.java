package com.wangw.goodhelp.api;

import com.wangw.goodhelp.model.Response;
import com.wangw.goodhelp.model.TopicInfo;
import com.wangw.goodhelp.model.UserInfo;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by wangw on 2016/4/20.
 */
public interface ApiService {



    @FormUrlEncoded
    @POST("getAllTopics")
    Observable<Response<List<TopicInfo>>> getAllTopics(@FieldMap RequestMap params);

    @FormUrlEncoded
    @POST("userLogin")
    Observable<Response<UserInfo>> userLogin(@FieldMap RequestMap params);

    @FormUrlEncoded
    @POST("userRegister")
    Observable<Response<UserInfo>> userRegister(@FieldMap Map<String,String> params);


}
