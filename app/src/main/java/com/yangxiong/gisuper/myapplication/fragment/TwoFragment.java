package com.yangxiong.gisuper.myapplication.fragment;

import com.yangxiong.gisuper.myapplication.R;
import com.yangxiong.gisuper.myapplication.base.BaseFragment;
import com.yangxiong.gisuper.myapplication.common.MessageEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by yangxiong on 2018/11/2.
 */
public class TwoFragment extends BaseFragment {

    @Override
    protected int contentViewLayout() {
        return R.layout.fragment_two;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().post(new MessageEvent());
    }

    @Override
    protected void initData() {

    }
}
