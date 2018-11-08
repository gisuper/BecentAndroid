package com.yangxiong.gisuper.myapplication.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

public abstract class BaseItemViewHolder extends RecyclerView.ViewHolder {

    public BaseItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
