package com.wangw.goodhelp.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.exlogcat.L;
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
    private OnItemLongClickListener mListener;
    public ImageGalleryAdapter(Context context,String[] imgs){
        mContext = context;
        this.mImgs = imgs;
        mViews = new PhotoView[4];
        for (int i = 0;i<4;i++){
            PhotoView view = new PhotoView(mContext);
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(mListener != null)
                        mListener.onLongClick(v);
                    return false;
                }
            });
            mViews[i] = view;
        }
    }

    public void setListener(OnItemLongClickListener listener){
        this.mListener = listener;
    }

    @Override
    public int getCount() {
        return mImgs.length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView view = getView(position);
        ImageHelp.loadImage(mContext,getItem(position),view);
        container.addView(view, ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.MATCH_PARENT);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ImageView view = (ImageView) object;
        view.setImageDrawable(null);
        container.removeView(view);
//        super.destroyItem(container, position, object);
    }

    public String getItem(int position){
        return mImgs[position];
    }

    public ImageView getView(int position){
        return mViews[position % mViews.length];
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public interface  OnItemLongClickListener{
        void onLongClick(View view);
    }
}
