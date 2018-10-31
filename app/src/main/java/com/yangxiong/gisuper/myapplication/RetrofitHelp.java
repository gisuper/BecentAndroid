package com.yangxiong.gisuper.myapplication;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
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
                .client(getOkHttpClient( ))
                .addConverterFactory(GsonConverterFactory.create( ))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create( ))
                .baseUrl("https://t-api.xyhj.io/")
                .build( );
        return build;
    }
    public  <T> T createInterface(Class<T> clazz){
        return buildRetrofit().create(clazz);
    }
    private static volatile OkHttpClient mOkHttpClient;
    private static Cache cache = new Cache(MyApp.getContext( ).getCacheDir( ), 1024 * 1024 * 10);//缓存10mib

    private static OkHttpClient getOkHttpClient() {
        if (mOkHttpClient == null) {
            synchronized (RetrofitManager.class) {
                if (mOkHttpClient == null) {
                    mOkHttpClient = new OkHttpClient.Builder( )
                            .addInterceptor(new OkHttpInterceptor( ))
                            .connectTimeout(6, TimeUnit.SECONDS)//连接超时时间
                            .readTimeout(3, TimeUnit.SECONDS)//读取超时时间
                            .writeTimeout(3, TimeUnit.SECONDS)//写入超时时间
                            .cache(cache)
                            .build( );
                }
            }
        }
        return mOkHttpClient;
    }
}
