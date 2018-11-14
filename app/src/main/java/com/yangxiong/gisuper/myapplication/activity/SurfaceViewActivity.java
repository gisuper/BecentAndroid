package com.yangxiong.gisuper.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;

import com.yangxiong.gisuper.myapplication.widget.DemoSurfaceView;

/**
 * Created by yangxiong on 2018/11/14.
 */
public class SurfaceViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DemoSurfaceView(this));
    }

}
