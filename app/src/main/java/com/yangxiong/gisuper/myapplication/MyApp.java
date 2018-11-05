package com.yangxiong.gisuper.myapplication;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by yangxiong on 2018/10/31.
 */
public class MyApp extends Application {
    private static MyApp context;
    private static GoogleAnalytics sAnalytics;
    private static Tracker sTracker;

    public static MyApp getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate( );
        context = this;
        sAnalytics = GoogleAnalytics.getInstance(this);
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        //...在这里设置strategy的属性，在bugly初始化时传入
        strategy.setAppChannel("baidu");  //设置渠道
        strategy.setAppVersion("1.0.9");      //App的版本
        strategy.setAppPackageName("com.yangxiong.gisuper.myapplication");  //App的包名
        CrashReport.initCrashReport(context, "d7fa670b9e", true, strategy);
        CrashReport.setIsDevelopmentDevice(context, BuildConfig.DEBUG);
    }
    /**
     * Gets the default {@link Tracker} for this {@link Application}.
     * @return tracker
     */
    synchronized public Tracker getDefaultTracker() {
        // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
        if (sTracker == null) {
            sTracker = sAnalytics.newTracker(R.xml.global_tracker);
        }

        return sTracker;
    }

}
