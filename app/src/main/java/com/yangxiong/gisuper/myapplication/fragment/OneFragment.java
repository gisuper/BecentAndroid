package com.yangxiong.gisuper.myapplication.fragment;

import android.util.Log;

import com.yangxiong.gisuper.myapplication.R;
import com.yangxiong.gisuper.myapplication.base.BaseFragment;
import com.yangxiong.gisuper.myapplication.common.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by yangxiong on 2018/11/2.
 */
public class OneFragment extends BaseFragment {

    @Override
    protected int contentViewLayout() {
        return R.layout.fragment_one;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
EventBus.getDefault().post(new MessageEvent());
    }
    @Subscribe
    public void handleSomethingElse(MessageEvent event) {
        Log.e(TAG, "handleSomethingElse: ");
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        Log.e(TAG, "onMessageEvent: ");
    };

    @Override
    public void onStart() {
        super.onStart( );
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop( );
        EventBus.getDefault().unregister(this);
    }
}
