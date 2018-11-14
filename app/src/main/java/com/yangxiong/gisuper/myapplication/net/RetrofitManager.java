package com.yangxiong.gisuper.myapplication.net;

import com.yangxiong.gisuper.myapplication.MyApp;
import com.yangxiong.gisuper.myapplication.RecordedListBean;
import com.yangxiong.gisuper.myapplication.utils.NetworkUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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
                            .addNetworkInterceptor(NetCacheInterceptor)
                            .addInterceptor(OfflineCacheInterceptor)
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

    final static Interceptor NetCacheInterceptor = new Interceptor( ) {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request( );
            Response response = chain.proceed(request);
            int onlineCacheTime = 30;
            return response.newBuilder( ).
                    header("Cache-Control", "public, max-age=" + onlineCacheTime)
                    .removeHeader("Pragma").build( );
        }
    };

    final static Interceptor OfflineCacheInterceptor = new Interceptor( ) {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request( );
            if (!NetworkUtil.isNetworkConnected(MyApp.getContext())) {
                int offlineCacheTime = 60;//离线的时候的缓存的过期时间 request = request.newBuilder()
                request = request.newBuilder( )
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + offlineCacheTime).build( );
            }
            return chain.proceed(request);
        }
    };

}