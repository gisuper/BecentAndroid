package com.yangxiong.gisuper.myapplication.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.yangxiong.gisuper.myapplication.MyApp;
import com.yangxiong.gisuper.myapplication.R;
import com.yangxiong.gisuper.myapplication.RecordedListBean;
import com.yangxiong.gisuper.myapplication.base.BaseActivity;
import com.yangxiong.gisuper.myapplication.net.RetrofitManager;
import com.yangxiong.gisuper.myapplication.utils.TitleBarUtils;

import butterknife.OnClick;
import rx.Subscriber;

public class MainActivity extends BaseActivity {


    private Tracker defaultTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TitleBarUtils.setStatusBarColor(this, Color.TRANSPARENT);
        MyApp context = MyApp.getContext( );
        defaultTracker = context.getDefaultTracker( );

    }

    @Override
    protected int setConteViewID() {
        return R.layout.activity_main;
    }

    @Override
    protected void onResume() {
        super.onResume( );
        defaultTracker.send(new HitBuilders.EventBuilder()
                .setCategory("Action123")
                .setAction("Share123")
                .build());

        defaultTracker.setScreenName("Image~" + "MainActivity");
        defaultTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @OnClick({R.id.btn_frame, R.id.btn_translate, R.id.btn_retrofit,R.id.btn_viewpager})
    public void onClick(View v) {
        switch (v.getId( )) {
            case R.id.btn_frame:
                startActivity(new Intent(this, FrameActivity.class));
                break;
            case R.id.btn_viewpager:
                startActivity(new Intent(this, ViewPagerActivity.class));
                break;
            case R.id.btn_translate:
                startActivity(new Intent(this, TranslateActivity.class));
                break;
            case R.id.btn_retrofit:
                RetrofitManager.loadRecordListData("20181031", "", 1, 20,
                        new Subscriber<RecordedListBean>( ) {
                            @Override
                            public void onCompleted() {
                                Log.e(TAG, "onCompleted: ");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e(TAG, "onError: ");
                            }

                            @Override
                            public void onNext(RecordedListBean recordedListBean) {
                                Log.e(TAG, "recordedListBean: " + recordedListBean.getPayload( ).size( ));
                            }
                        });
                break;
        }
    }

}
