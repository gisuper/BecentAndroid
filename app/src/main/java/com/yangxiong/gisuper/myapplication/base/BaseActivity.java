package com.yangxiong.gisuper.myapplication.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public abstract class BaseActivity extends SwipeBackActivity {
    protected final String TAG = this.getClass().getSimpleName();
    protected Context context;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(setConteViewID());
        unbinder = ButterKnife.bind(this);
        SwipeBackLayout mSwipeBackLayout = getSwipeBackLayout( );
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        initView();
        initData();
    }

    protected void startActivity(Class<?> cls) {
        this.startActivity(cls, null,null);
    }

    protected void startActivity(Class<?> cls, String paramKey,String paramValue) {
        Intent intent = new Intent(this, cls);
        intent.putExtra(paramKey, paramValue);
        this.startActivity(intent);
    }

    abstract protected int setConteViewID();
    abstract protected void initView();
    abstract protected void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy( );
        unbinder.unbind();
    }
}
