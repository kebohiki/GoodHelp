package com.wangw.goodhelp.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wangw.goodhelp.R;
import com.wangw.goodhelp.common.Constants;

/**
 * Created by wangw on 16/4/23.
 */
public class ImageHelp {


    public static void loadImage(Context context,String key,ImageView img){
        Glide.with(context)
                .load(Constants.BASEIMAGEURL+key)
                .placeholder(R.color.gray)
                .error(R.color.gray)
                .crossFade()
                .into(img);
    }


    public static void loadHeaderImage(Context context,String key,ImageView img){

        Glide.with(context)
                .load(Constants.BASEIMAGEURL+key)
                .placeholder(R.color.gray)
                .error(R.color.gray)
                .crossFade()
                .into(img);

    }
}
