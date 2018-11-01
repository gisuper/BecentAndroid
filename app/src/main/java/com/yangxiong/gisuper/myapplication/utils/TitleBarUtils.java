package com.yangxiong.gisuper.myapplication.utils;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.yangxiong.gisuper.myapplication.R;

/**
 * Created by yangxiong on 2018/11/1.
 */
public class TitleBarUtils {
    public static void setStatusBarColor(Activity context, int color) {
        Window window = context.getWindow( );
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.getDecorView( ).setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(color);
    }

    public static void setStatusBarLayoutStyle(Activity context, boolean isChange) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ((AppCompatActivity) context).getWindow( ).setStatusBarColor(Color.TRANSPARENT);
            //透明状态栏
            ((AppCompatActivity) context).getWindow( ).addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            ((AppCompatActivity) context).getWindow( ).addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            SystemBarTintManager tintManager = new SystemBarTintManager(((AppCompatActivity) context));
            // 激活状态栏
            tintManager.setStatusBarTintEnabled(true);
            //判断是否需要更改状态栏颜色
            if (isChange) {
                tintManager.setStatusBarTintResource(R.color.translate_bg);
            } else {
                tintManager.setStatusBarTintResource(R.color.actionbar_bg);
            }
            ViewGroup mContentView = (ViewGroup) ((AppCompatActivity) context).getWindow( ).findViewById(((AppCompatActivity) context).getWindow( ).ID_ANDROID_CONTENT);
            View mChildView = mContentView.getChildAt(0);
            if (mChildView != null) {
                //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View .
                // 预留出系统 View 的空间.
                //  mChildView.setFitsSystemWindows(true);
            }
        }
    }
}
