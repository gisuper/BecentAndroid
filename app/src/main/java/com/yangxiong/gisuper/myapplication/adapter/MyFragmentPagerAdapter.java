package com.yangxiong.gisuper.myapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yangxiong.gisuper.myapplication.fragment.FourFragment;
import com.yangxiong.gisuper.myapplication.fragment.OneFragment;
import com.yangxiong.gisuper.myapplication.fragment.ThreeFragment;
import com.yangxiong.gisuper.myapplication.fragment.TwoFragment;

/**
 * Created by yangxiong on 2018/11/2.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new OneFragment();
                break;
            case 1:
                fragment = new TwoFragment();
                break;
            case 2:
                fragment = new ThreeFragment();
                break;
            case 3:
                fragment = new FourFragment();
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
