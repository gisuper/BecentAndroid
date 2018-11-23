package com.yangxiong.gisuper.myapplication.fragment;

import android.support.v7.widget.Toolbar;

import com.yangxiong.gisuper.myapplication.R;
import com.yangxiong.gisuper.myapplication.activity.ViewPagerActivity;
import com.yangxiong.gisuper.myapplication.base.BaseFragment;

/**
 * Created by yangxiong on 2018/11/2.
 */
public class ThreeFragment extends BaseFragment {
    @Override
    protected int contentViewLayout() {
        return R.layout.fragment_three;
    }

    @Override
    protected void initView() {

        Toolbar toolbar =  findViewById(R.id.toolbar);
        ViewPagerActivity viewPagerActivity = (ViewPagerActivity) getActivity();
        //StatusBarUtil.setStatusBarColor(viewPagerActivity, getResources().getColor(R.color.statusbar_bg));
        viewPagerActivity.setSupportActionBar(toolbar);

    }

    @Override
    protected void initData() {

    }

}
