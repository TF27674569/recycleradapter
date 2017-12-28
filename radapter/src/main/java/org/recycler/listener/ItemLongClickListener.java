package org.recycler.listener;

import android.support.v7.widget.RecyclerView;

/**
 * description：
 * <p/>
 * Created by TIAN FENG on 2017/12/28.
 * QQ：27674569
 * Email: 27674569@qq.com
 * Version：1.0
 */
public interface ItemLongClickListener {
    <T> boolean ItemLongClickListener(T item, int position, RecyclerView.Adapter adapter);
}
