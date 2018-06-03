package com.blue.bestdxw.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

/**
 * @author: Administrator
 * @date: 2018/6/3 0003 11:17
 */
public class ImageUtil {
    /**
     * 加载通用网络图片
     * @param context
     * @param url
     * @param place
     * @param error
     * @param view
     */
    public static void loadImageViewFromeNet(Context context, String url, int place, int error, ImageView view){
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(place)
                .error(error);
        Glide.with(context).load(url).apply(options).into(view);
    }
    /**
     * 加载圆形图片
     * @param context
     * @param url
     * @param place
     * @param error
     * @param view
     */
    public static void loadRoundViewFromeNet(Context context, String url, int place, int error, ImageView view){
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(place)
                .error(error)
                .transform(new RoundedCorners(360));
        Glide.with(context).load(url).apply(options).into(view);
    }

    /**
     * 加载本地图片
     * @param context
     * @param uri
     * @param view
     */
    public static void loadImageViewFromeAss(Context context, int uri, ImageView view){
        RequestOptions options = new RequestOptions()
                .centerCrop();
        Glide.with(context).load(uri).apply(options).into(view);
    }    /**
     * 加载本地圆形图片
     * @param context
     * @param uri
     * @param view
     */
    public static void loadRoundViewFromeAss(Context context, int uri, ImageView view){
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .transform(new RoundedCorners(360));
        Glide.with(context).load(uri).apply(options).into(view);
    }
}
