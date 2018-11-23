package com.yangxiong.gisuper.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.yangxiong.gisuper.myapplication.R;
import com.yangxiong.gisuper.myapplication.utils.StatusBarUtil;

public class AppBarLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setStatusBarColor(this, getResources().getColor(R.color.transparent));
        setContentView(R.layout.activity_app_bar_layout);

        Toolbar toolbar =  findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
       // StatusBarUtil.setStatusBarColor(this, getResources().getColor(R.color.transparent));
    }
}
