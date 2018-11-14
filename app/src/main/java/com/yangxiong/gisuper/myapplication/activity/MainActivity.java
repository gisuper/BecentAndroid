package com.yangxiong.gisuper.myapplication.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.yangxiong.gisuper.myapplication.MyApp;
import com.yangxiong.gisuper.myapplication.R;
import com.yangxiong.gisuper.myapplication.RecordedListBean;
import com.yangxiong.gisuper.myapplication.base.BaseActivity;
import com.yangxiong.gisuper.myapplication.net.RetrofitManager;
import com.yangxiong.gisuper.myapplication.utils.PermissionUtil;
import com.yangxiong.gisuper.myapplication.utils.TitleBarUtils;
import com.yangxiong.gisuper.myapplication.utils.UIUtil;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;

public class MainActivity extends BaseActivity {
    private int mRequestCode = 2001;

    private Tracker defaultTracker;

    @BindView(R.id.tv_scan_result)
    TextView tvScanResult;
    @Override
    protected int setConteViewID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        TitleBarUtils.setStatusBarColor(this, Color.TRANSPARENT);
        MyApp context = MyApp.getContext( );
        defaultTracker = context.getDefaultTracker( );
    }

    @Override
    protected void initData() {

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

    @OnClick({R.id.btn_frame, R.id.btn_translate, R.id.btn_retrofit, R.id.btn_viewpager, R.id.btn_qrcode,R.id.btn_sv})
    public void onClick(View v) {
        switch (v.getId( )) {
            case R.id.btn_sv:
                startActivity(SurfaceViewActivity.class);
                break;
            case R.id.btn_qrcode:
                String[] params = PermissionUtil.hasNotPermissions(this, new String[]{Manifest.permission.CAMERA});
                if (params.length == 0) {
                    startActivityForResult(new Intent(this, ScanActivity.class), 100);
                }else {
                    ActivityCompat.requestPermissions(MainActivity.this, params, mRequestCode);
                }
                break;
            case R.id.btn_frame:
                startActivity(FrameActivity.class);
                break;
            case R.id.btn_viewpager:
                startActivity(ViewPagerActivity.class);
                break;
            case R.id.btn_translate:
                startActivity(TranslateActivity.class);
                break;
            case R.id.btn_retrofit:
                RetrofitManager.loadRecordListData("20181114", "", 1, 20,
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            try {
                Bundle bundle;
                if (null != data && (bundle = data.getExtras( )) != null) {
                    if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                        String result = bundle.getString(CodeUtils.RESULT_STRING);
                        if (!TextUtils.isEmpty(result)) {
                            if (result.contains(":")) {
                                result = result.split(":")[1];
                            }
                            tvScanResult.setText(result);
                        }
                    } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                        UIUtil.showToastShort("scan_error_hint");
                    }
                }
            } catch (Exception e) {
                UIUtil.showToastShort("scan_error_hint");
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == mRequestCode && PermissionUtil.hasNotPermissions(this, permissions).length == 0) {
            startActivityForResult(new Intent(this, ScanActivity.class), 100);
        } else {
            UIUtil.showToastShort("没有权限");
        }
    }
}
