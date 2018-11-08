package com.yangxiong.gisuper.myapplication.fragment;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
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
        RefreshLayout refreshLayout = (RefreshLayout)findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(refreshlayout ->{
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
        });
        refreshLayout.setOnLoadMoreListener(refreshlayout ->{
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
        });
    }

    @Override
    protected void initData() {

    }
}
