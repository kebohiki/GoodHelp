package com.wangw.goodhelp.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wangw on 2016/4/20.
 */
public class ServiceHelper {

    private static final String BASEURL = "http://ningweb.com/product_picture/api/index.php/nightkiss/";

    private static Retrofit mRetrofit;
    private static ApiService mService;

    public static ApiService GetApi(){
        if(mService == null){
            mService = getRetrofit().create(ApiService.class);
        }
        return mService;
    }

    private static Retrofit getRetrofit(){
        if(mRetrofit == null){
            HttpLoggingInterceptor log = new HttpLoggingInterceptor();
            log.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .addNetworkInterceptor(log)
                    .build();
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return mRetrofit;
    }


}
