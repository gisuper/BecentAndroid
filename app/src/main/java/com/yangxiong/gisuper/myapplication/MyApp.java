package com.yangxiong.gisuper.myapplication;

import android.app.Application;
import android.content.Context;

/**
 * Created by yangxiong on 2018/10/31.
 */
public class MyApp extends Application {
    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate( );
        context = this;
    }

}
