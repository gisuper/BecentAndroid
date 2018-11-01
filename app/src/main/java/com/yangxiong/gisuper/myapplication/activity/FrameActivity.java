package com.yangxiong.gisuper.myapplication.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceView;

import com.yangxiong.gisuper.myapplication.R;
import com.yangxiong.gisuper.myapplication.anim.SilkyAnimation;
import com.yangxiong.gisuper.myapplication.utils.TitleBarUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FrameActivity extends AppCompatActivity {
    @BindView(R.id.sv_frame)
    SurfaceView svFrame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        ButterKnife.bind(this);
        TitleBarUtils.setStatusBarColor(this, Color.TRANSPARENT);
        SilkyAnimation mAnimation=  new SilkyAnimation.Builder(svFrame)
                .setCacheCount(8)//设置常驻内存的缓存数量, 默认5.
                .setFrameInterval(200) //设置帧间隔, 默认100
                .setScaleType(SilkyAnimation.SCALE_TYPE_FIT_END) //设置缩放类型, 默认fit center，与ImageView的缩放模式通用
                .setAnimationListener(null)  //设置动画开始结束状态监听
                /// true->java.lang.IllegalArgumentException: Problem decoding into existing bitmap
                .setSupportInBitmap(false) ///设置是否支持bitmap复用，默认为true
                .setRepeatMode(SilkyAnimation.MODE_INFINITE)//设置循环模式, 默认不循环
                .build();
        mAnimation.start("spark");
    }

}
