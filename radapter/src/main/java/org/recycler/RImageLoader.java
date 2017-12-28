package org.recycler;

import android.content.Context;
import android.widget.ImageView;

/**
 * description：适配图片加载框架
 * <p/>
 * Created by TIAN FENG on 2017/12/28.
 * QQ：27674569
 * Email: 27674569@qq.com
 * Version：1.0
 */
public interface RImageLoader {

    void displayImage(Context context, ImageView imageView, String url);
}
