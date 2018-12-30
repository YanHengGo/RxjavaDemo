package com.yanheng.basicapplication.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DoubanManager {
    private static IDoubanService iDoubanService;

    public synchronized static IDoubanService createDoubanService(){
        if( iDoubanService == null ){
            Retrofit retrofit = createRetrofit();
            iDoubanService = retrofit.create(IDoubanService.class);
        }
        return iDoubanService;
    }

    private static Retrofit createRetrofit(){
        //プリンター機能
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        return new Retrofit.Builder()
                .baseUrl(IDoubanService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }
}
