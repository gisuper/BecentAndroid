package com.yangxiong.gisuper.myapplication.base;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
    protected final String   TAG = this.getClass().getSimpleName();
    protected       View     mRootView;
    private Unbinder mUnbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(null != mRootView) {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if(null != parent) parent.removeView(mRootView);
        }
        return mRootView = inflater.inflate(contentViewLayout(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, mRootView);
        initView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRootView.post(this::initData);
    }

    protected abstract int contentViewLayout();
    protected abstract void initView();
    protected abstract void initData();



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mUnbinder != null) mUnbinder.unbind();
    }



    public final <T extends View> T findViewById(int id) {
        return (T) mRootView.findViewById(id);
    }


    public View getRootView() {
        return mRootView;
    }

}
