package com.yangxiong.gisuper.myapplication.activity;

import android.graphics.Color;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.yangxiong.gisuper.myapplication.R;
import com.yangxiong.gisuper.myapplication.adapter.MyFragmentPagerAdapter;
import com.yangxiong.gisuper.myapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ViewPagerActivity extends BaseActivity {

    @BindView(R.id.vp_main)
    ViewPager vpMain;
    @BindView(R.id.bt_one)
     Button one;
    @BindView(R.id.bt_two)
     Button two;
    @BindView(R.id.bt_three)
     Button three;
    @BindView(R.id.bt_four)
     Button four;

    @Override
    protected int setConteViewID() {
        return R.layout.activity_view_pager;
    }

    @Override
    protected void initView() {
        //获取ViewPager
        //创建一个FragmentPagerAdapter对象，该对象负责为ViewPager提供多个Fragment
        FragmentPagerAdapter pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        //为ViewPager组件设置FragmentPagerAdapter
        vpMain.setAdapter(pagerAdapter);
        //为viewpager组件绑定时间监听器
        vpMain.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            //当ViewPager显示的Fragment发生改变时激发该方法
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        one.setTextColor(Color.BLUE);
                        two.setTextColor(Color.BLACK);
                        three.setTextColor(Color.BLACK);
                        four.setTextColor(Color.BLACK);
                        break;
                    case 1:
                        one.setTextColor(Color.BLACK);
                        two.setTextColor(Color.BLUE);
                        three.setTextColor(Color.BLACK);
                        four.setTextColor(Color.BLACK);
                        break;
                    case 2:
                        one.setTextColor(Color.BLACK);
                        two.setTextColor(Color.BLACK);
                        three.setTextColor(Color.BLUE);
                        four.setTextColor(Color.BLACK);
                        break;
                    case 3:
                        one.setTextColor(Color.BLACK);
                        two.setTextColor(Color.BLACK);
                        three.setTextColor(Color.BLACK);
                        four.setTextColor(Color.BLUE);
                        break;
                }
                super.onPageSelected(position);
            }
        });

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.bt_one,R.id.bt_two,R.id.bt_three, R.id.bt_four})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_one:
                vpMain.setCurrentItem(0);
                break;
            case R.id.bt_two:
                vpMain.setCurrentItem(1);
                break;
            case R.id.bt_three:
                vpMain.setCurrentItem(2);
                break;
            case R.id.bt_four:
                vpMain.setCurrentItem(3);
                break;
        }
    }

}
