package com.yangxiong.gisuper.myapplication.utils;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 *
 *
 是否留出顶部状态栏边距
 android:clipToPadding="true"
 android:fitsSystemWindows="true"

 android:configChanges="orientation|keyboardHidden|screenSize"
 android:theme="@style/Theme.AppCompat.Light.NoActionBar"

 * Created by yangxiong on 2018/11/1.
 */
public class StatusBarUtil {
    public static void setStatusBarColor(Activity context, int color) {
        Window window = context.getWindow( );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        window.getDecorView( ).setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
        }

    }
}
