package com.yangxiong.gisuper.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.yangxiong.gisuper.myapplication.R;

/**
 * Created by yangxiong on 2018/11/1.
 */
public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().getDecorView().postDelayed(() ->{startActivity(
                new Intent(this,MainActivity.class));}, 3000);
    }
}
