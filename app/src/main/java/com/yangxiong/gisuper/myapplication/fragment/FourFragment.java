package com.yangxiong.gisuper.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yangxiong.gisuper.myapplication.R;

/**
 * Created by yangxiong on 2018/11/2.
 */
public class FourFragment extends android.support.v4.app.Fragment {
    private  final String TAG = this.getClass( ).getSimpleName( );

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_four, container, false);
        Log.e(TAG, "onCreateView: " );
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onActivityCreated: " );
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e(TAG, "onViewCreated: " );
    }

    @Override
    public void onStop() {
        super.onStop( );
        Log.e(TAG, "onStop: " );
    }

    @Override
    public void onStart() {
        super.onStart( );
        Log.e(TAG, "onStart: " );
    }

    @Override
    public void onResume() {
        super.onResume( );
        Log.e(TAG, "onStart: " );
    }

    @Override
    public void onPause() {
        super.onPause( );
        Log.e(TAG, "onStart: " );
    }
}
