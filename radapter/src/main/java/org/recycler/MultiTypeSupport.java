package org.recycler;

/**
 * description：多布局适配
 * <p/>
 * Created by TIAN FENG on 2017/12/28.
 * QQ：27674569
 * Email: 27674569@qq.com
 * Version：1.0
 */
public interface MultiTypeSupport<T> {
    // 根据当前位置或者条目数据返回布局
    int getLayoutId(T item, int position);
}

