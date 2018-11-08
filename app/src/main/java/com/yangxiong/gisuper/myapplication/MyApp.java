package com.yangxiong.gisuper.myapplication;

import android.app.Application;
import android.content.Context;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
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
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.black_overlay, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }
}
