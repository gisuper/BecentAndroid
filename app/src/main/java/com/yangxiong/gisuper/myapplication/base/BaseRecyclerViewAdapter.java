package com.yangxiong.gisuper.myapplication.base;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerViewAdapter<T, VHH extends BaseItemViewHolder> extends RecyclerView.Adapter<VHH> {
    protected final String  TAG       = this.getClass().getSimpleName();
    protected       Context mContext;
    protected       List<T> mDataList = new ArrayList<>();

    public BaseRecyclerViewAdapter(Context context) {
        this.mContext = context;
        mDataList.clear();
    }

    /**
     * 创建ViewHolder
     * <br/>
     * see:{@link RecyclerView.Adapter#onCreateViewHolder(ViewGroup, int)}
     */
    protected abstract VHH iCreateViewHolder(ViewGroup parent, int viewType);

    /**
     * 绑定ViewHolder视图数据
     * <br/>
     * see:{@link RecyclerView.Adapter#onBindViewHolder(RecyclerView.ViewHolder, int)}
     */
    protected abstract void iBindViewHolder(VHH viewHolder, int position);

    /**
     * 条目点击监听
     *
     * @param view     被点击条目视图
     * @param position 点击条目位置
     * @param item     条目
     */
    protected abstract void onItemClickListener(View view, int position, @NonNull T item);

    /**
     * 条目长按监听
     *
     * @param view     被长按条目视图
     * @param position 长按条目位置
     * @param item     条目
     */
    protected abstract boolean onItemLongClickListener(View view, int position, @NonNull T item);

    /**
     * 加载初始刷新数据
     */
    public void refreshDataList(List<T> dataList) {
        if(dataList == null) return;
        mDataList.clear();
        mDataList.addAll(dataList);
        notifyDataSetChanged();
    }

    /**
     * 加载更多
     */
    public void loadMoreDataList(List<T> dataList) {
        if(dataList == null || dataList.size() == 0) return;
        int sizeBefore = mDataList.size();
        mDataList.addAll(dataList);
        notifyItemRangeInserted(sizeBefore, dataList.size());
    }

    /**
     * 获取当前列表中的数据对象列表
     */
    public List<T> getDataList() {
        return mDataList;
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    /**
     * 获取指定位置的条目对象
     */
    public T getItem(int position) {
        return (position < mDataList.size() && position >= 0) ? mDataList.get(position) : null;
    }

    /**
     * 移除指定位置的条目对象
     */
    public T removeItem(int position) {
        return removeItem(getItem(position));
    }

    /**
     * 移除条目对象
     */
    public T removeItem(T t) {
        if(t != null) {
            int index = mDataList.indexOf(t);
            t = mDataList.remove(index);
            notifyItemRemoved(index);
        }
        return t;
    }

    @NonNull
    @Override
    public VHH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return this.iCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull VHH viewHolder, int position) {
        if(getItem(position) == null) return;
        viewHolder.itemView.setTag(viewHolder.itemView.getId(), position);
        viewHolder.itemView.setOnClickListener(mInnerItemOnclickListener);
        viewHolder.itemView.setOnLongClickListener(mInnerItemOnLongClickListener);
        this.iBindViewHolder(viewHolder, position);
    }

    private View.OnClickListener mInnerItemOnclickListener = view -> {
        int position = (int) view.getTag(view.getId());
        T item = getItem(position);
        if(item != null) onItemClickListener(view, position, getItem(position));
    };

    private View.OnLongClickListener mInnerItemOnLongClickListener = view -> {
        int position = (int) view.getTag(view.getId());
        T item = getItem(position);
        if(item != null) {
            return onItemLongClickListener(view, position, item);
        }
        return false;
    };

}
