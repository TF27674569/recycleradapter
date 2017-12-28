package org.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.recycler.listener.ItemClickListener;
import org.recycler.listener.ItemLongClickListener;

import java.util.List;

/**
 * description：recyclerView 万能适配器
 * <p/>
 * Created by TIAN FENG on 2017/12/28.
 * QQ：27674569
 * Email: 27674569@qq.com
 * Version：1.0
 */
public abstract class RAdapter<T> extends RecyclerView.Adapter<RHolder> {


    // recyclerView图片加载框架
    public static void initImageLoader(RImageLoader imageLoader) {
        RHolder.setImageLoader(imageLoader);
    }

    // 实现数据绑定接口
    protected abstract void onBind(RHolder holder, T item, int position);

    // 条目点击
    public void setOnItemClickListener(ItemClickListener clickListener) {
        mItemClickListener = clickListener;
    }

    // 条目长按
    public void setOnItemLongClickListener(ItemLongClickListener clickListener) {
        mItemLongClickListener = clickListener;
    }

    /*********************************************************************************************************************************************/
    private MultiTypeSupport mMultiTypeSupport;
    private int mLayoutId;
    private List<T> mItemDatas;
    private ItemClickListener mItemClickListener;
    private ItemLongClickListener mItemLongClickListener;

    // 普通处理
    public RAdapter(List<T> itemDatas, int layoutId) {
        mItemDatas = itemDatas;
        mLayoutId = layoutId;
    }

    // 多布局处理
    public RAdapter(List<T> itemDatas, MultiTypeSupport multiTypeSupport) {
        mItemDatas = itemDatas;
        mMultiTypeSupport = multiTypeSupport;
    }

    @Override
    public RHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 这里的viewType为 getItemViewType返回的布局ID
        View itemView = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new RHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RHolder holder, final int position) {

        // 数据绑定
        onBind(holder, mItemDatas.get(position), position);

        // 条目点击
        if (mItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemClickListener(mItemDatas.get(position), position, RAdapter.this);
                }
            });
        }

        // 条目长按
        if (mItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return mItemLongClickListener.ItemLongClickListener(mItemDatas.get(position), position, RAdapter.this);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mItemDatas == null ? 0 : mItemDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        // 判断是否需要处理多布局
        if (mMultiTypeSupport == null) {
            return mLayoutId;
        }
        return mMultiTypeSupport.getLayoutId(mItemDatas.get(position), position);
    }
}
