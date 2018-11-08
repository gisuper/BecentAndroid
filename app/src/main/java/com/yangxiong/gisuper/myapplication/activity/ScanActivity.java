/*Copyright ©2015 TommyLemon(https://github.com/TommyLemon)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

package com.yangxiong.gisuper.myapplication.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Toast;

import com.uuzuche.lib_zxing.ImageUtil;
import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.yangxiong.gisuper.myapplication.R;
import com.yangxiong.gisuper.myapplication.base.BaseActivity;
import com.yangxiong.gisuper.myapplication.utils.PermissionUtil;
import com.yangxiong.gisuper.myapplication.utils.UIUtil;

import butterknife.OnClick;


public class ScanActivity extends BaseActivity {
    public static final int REQUEST_IMAGE = 1001;
    private int mRequestCode = 2001;

    @Override
    protected int setConteViewID() {
        return R.layout.activity_scan;
    }

    @Override
    protected void initView() {
        CaptureFragment captureFragment = new CaptureFragment( );
        captureFragment.setAnalyzeCallback(analyzeCallback);
        getSupportFragmentManager( ).beginTransaction( ).replace(R.id.container, captureFragment).commit( );
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_album})
    public void onClick(View v) {
        switch (v.getId( )) {
            case R.id.tv_album:
                String[] params = PermissionUtil.hasNotPermissions(this, new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE});
                if (params.length == 0) {
                    openAlbum( );
                } else {

                    ActivityCompat.requestPermissions(this, params, mRequestCode);
                }
                break;
        }
    }

    private void openAlbum() {
        Intent intent = new Intent( );
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_IMAGE);
    }

    /**
     * 二维码解析回调函数
     */
    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback( ) {
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
            Intent resultIntent = new Intent( );
            Bundle bundle = new Bundle( );
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS);
            bundle.putString(CodeUtils.RESULT_STRING, result);
            resultIntent.putExtras(bundle);
            ScanActivity.this.setResult(RESULT_OK, resultIntent);
            ScanActivity.this.finish( );
        }

        @Override
        public void onAnalyzeFailed() {
            Intent resultIntent = new Intent( );
            Bundle bundle = new Bundle( );
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED);
            bundle.putString(CodeUtils.RESULT_STRING, "");
            resultIntent.putExtras(bundle);
            ScanActivity.this.setResult(RESULT_OK, resultIntent);
            ScanActivity.this.finish( );
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE) {
            if (data != null) {
                Uri uri = data.getData( );
                try {
                    CodeUtils.analyzeBitmap(ImageUtil.getImageAbsolutePath(this, uri), new CodeUtils.AnalyzeCallback( ) {
                        @Override
                        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                            Intent resultIntent = new Intent( );
                            Bundle bundle = new Bundle( );
                            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS);
                            bundle.putString(CodeUtils.RESULT_STRING, result);
                            resultIntent.putExtras(bundle);
                            ScanActivity.this.setResult(RESULT_OK, resultIntent);
                            ScanActivity.this.finish( );
                        }

                        @Override
                        public void onAnalyzeFailed() {
                            Toast.makeText(ScanActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show( );
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace( );
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == mRequestCode && PermissionUtil.hasNotPermissions(this, permissions).length == 0) {
            openAlbum( );
        } else {
            UIUtil.showToastShort("没有权限");
        }
    }
}