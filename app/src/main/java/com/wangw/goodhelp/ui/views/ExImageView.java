package com.wangw.goodhelp.ui.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.wangw.goodhelp.utils.ImageHelp;

/**
 * Created by wangw on 16/4/23.
 */
public class ExImageView extends ImageView {


    private String mURL;
    private boolean mIsAttached;

    public ExImageView(Context context) {
        super(context);
    }

    public ExImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Drawable drawable = getDrawable();
                if(drawable != null){
//                    drawable.mutate().setAlpha(50);
//                    drawable.mutate().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                    drawable.setColorFilter(0x80000000, PorterDuff.Mode.SRC_ATOP);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                Drawable drawable1 = getDrawable();
                if(drawable1 != null){
                    drawable1.clearColorFilter();
                    invalidate();
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onAttachedToWindow() {
        mIsAttached = true;
        setImageURL(mURL);
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        mIsAttached = false;
        setImageDrawable(null);
        super.onDetachedFromWindow();
    }

    public void setImageURL(String url){
        this.mURL = url;
        if(mIsAttached)
        ImageHelp.loadHeaderImage(getContext(),url,this);
    }




}
