package com.yangxiong.gisuper.myapplication;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yangxiong on 2018/10/31.
 */
public class RetrofitManager {
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


    public static <T> T create(Class<T> clazz) {
        Retrofit retrofit = new Retrofit.Builder( )
                .client(getOkHttpClient( ))
                .baseUrl("https://t-api.xyhj.io/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create( ))
                .addConverterFactory(GsonConverterFactory.create( ))
                .build( );


        return retrofit.create(clazz);
    }
    public static void toSubscribe(Observable o, Subscriber s){
               o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);

    }


  public static void loadRecordListData(String countTime, String searchKey, int page, int pageSize,Subscriber s){
      Observable<RecordedListBean> listData = RetrofitManager.create(ServiceInterface.class).
              loadRecordListData(countTime, searchKey, page, pageSize);
      toSubscribe(listData,s);

  }
}