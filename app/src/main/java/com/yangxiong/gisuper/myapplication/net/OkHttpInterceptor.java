package com.yangxiong.gisuper.myapplication.net;


import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by yangxiong on 2018/10/31.
 */

public class OkHttpInterceptor implements Interceptor {
    private static final String TAG="okHttp";
    //设缓存有效期为1天
    @Override
    public Response intercept(Chain chain) throws IOException {
        //从chain对象中可以获取到request和response，想要的数据都可以从这里获取
        Request request=chain.request();
        //增加头部信息
        request=addHeaders(request);
        //打印request日志
        logForRequest(request);
        //获取response对象
        Response response=chain.proceed(request);
        //打印response日志
        logForResponse(response);
            return response.newBuilder()
                    .build();

    }

    private Request addHeaders(Request request) {
        Map<String,String> params=new HashMap<>();
        params.put("sign","12345");
        params.put("time",System.currentTimeMillis()+"");
        Headers headers=Headers.of(params);
        return request.newBuilder().headers(headers).build();

    }

    private void logForResponse(Response response) {
        Log.e(TAG,"response's log---------------------------------start");
        Log.e(TAG,"code: "+response.code());
        Log.e(TAG,"protocol: "+response.protocol());
        Headers headers=response.headers();
        if (headers!=null&&headers.size()!=0){
            Log.e(TAG,headers.toString());
        }
        try {
            //这里不能直接用response.body().string(),因为调用改方法后流就关闭，程序就可能会发生异常
            //我们需要创建出一个新的ResponseBody给应用层调用
            ResponseBody body=response.peekBody(1024*1024);
            if (body!=null){
                Log.e(TAG,"protocol: "+body.string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e(TAG,"response's log---------------------------------end");
    }


    private void logForRequest(Request request) {
        Log.e(TAG,"request's log----------------------------------start");
        Log.e(TAG,"url: "+request.url());
        Log.e(TAG,"method: "+request.method());
        Headers headers=request.headers();
        if (headers!=null&&headers.size()!=0){
            Log.e(TAG,"headers: "+headers.toString());
        }
        RequestBody body=request.body();
        if (body!=null){
            Log.e(TAG,body.toString());
        }
        Log.e(TAG,"request's log----------------------------------end");
    }
}

