package com.yangxiong.gisuper.myapplication.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.yangxiong.gisuper.myapplication.R;
import com.yangxiong.gisuper.myapplication.RecordedListBean;
import com.yangxiong.gisuper.myapplication.net.RetrofitManager;
import com.yangxiong.gisuper.myapplication.utils.TitleBarUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity {

    protected final String TAG = this.getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        TitleBarUtils.setStatusBarColor(this, Color.TRANSPARENT);

    }

    @OnClick({R.id.btn_frame, R.id.btn_translate, R.id.btn_retrofit})
    public void onClick(View v) {
        switch (v.getId( )) {
            case R.id.btn_frame:
                startActivity(new Intent(this, FrameActivity.class));
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
