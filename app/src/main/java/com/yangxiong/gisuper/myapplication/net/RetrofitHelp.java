package com.yangxiong.gisuper.myapplication.net;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yangxiong on 2018/10/31.
 */
public class RetrofitHelp {
    private RetrofitHelp(){}
    public static RetrofitHelp getInstance(){
        return SingleHelp.retrofitHelp;
    }
    static class SingleHelp{
        private static RetrofitHelp retrofitHelp= new RetrofitHelp();
    }
    private Retrofit buildRetrofit(){
        Retrofit build = new Retrofit.Builder( )
                .addConverterFactory(GsonConverterFactory.create( ))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create( ))
                .baseUrl("https://t-api.xyhj.io/")
                .build( );
        return build;
    }
    public  <T> T createInterface(Class<T> clazz){
        return buildRetrofit().create(clazz);
    }
}
