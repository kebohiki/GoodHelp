package com.wangw.goodhelp.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wangw.goodhelp.utils.ImageHelp;

import java.util.List;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by wangw on 16/4/25.
 */
public class ImageGalleryAdapter extends PagerAdapter {

    private String[] mImgs;
    private PhotoView[] mViews;
    private Context mContext;
    public ImageGalleryAdapter(Context context,String[] imgs){
        mContext = context;
        this.mImgs = imgs;
        mViews = new PhotoView[4];
        for (int i = 0;i<4;i++){
            mViews[i] = new PhotoView(mContext);
        }
    }

    @Override
    public int getCount() {
        return mImgs.length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        PhotoView view = getView(position);
        ImageHelp.loadImage(mContext,getItem(position),view);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        PhotoView view = (PhotoView) object;
        view.setImageDrawable(null);
        container.removeView(view);
//        super.destroyItem(container, position, object);
    }

    public String getItem(int position){
        return mImgs[position];
    }

    public PhotoView getView(int position){
        PhotoView view =  new PhotoView(mContext);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(params);
        return view;
//        return mViews[position % mViews.length];
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return true;
    }
}
