package com.wangw.goodhelp.ui.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.wangw.goodhelp.R;
import com.wangw.goodhelp.utils.ScreenUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wangw on 2016/4/27.
 */
public class IndicatorViewPager extends FrameLayout implements ViewPager.OnPageChangeListener {


    @Bind(R.id.viewpager)
    HackyViewPager mViewpager;
    @Bind(R.id.ll_indicators)
    LinearLayout mLlIndicators;

    private int mIndicatorSize;
    private int mGap;
    private View mCurrentIndicatorView;


    public IndicatorViewPager(Context context) {
        super(context);
        onInitView();
    }

    public IndicatorViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        onInitView();
    }

    public IndicatorViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        onInitView();
    }

    private void onInitView() {
        mIndicatorSize = ScreenUtils.dp2px(getContext(),5);
        mGap = ScreenUtils.dp2px(getContext(),4);
        inflate(getContext(), R.layout.layout_indicator_viewpager, this);
        ButterKnife.bind(this);
    }

    public void setIndicators(int length){
        if (length == 0){
            mLlIndicators.removeAllViews();
            mViewpager.removeOnPageChangeListener(this);
        }else {
            onInitIndicatorsView(length);
            mViewpager.addOnPageChangeListener(this);
        }
    }

    private void onInitIndicatorsView(int length){
            mLlIndicators.removeAllViews();
        for (int i=0;i<length;i++){
            View view = getIndicatorView();
            mLlIndicators.addView(view);
        }

    }

    private View getIndicatorView(){
        View view = new View(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mIndicatorSize,mIndicatorSize);
        params.rightMargin = mGap;
        params.leftMargin = mGap;
        view.setLayoutParams(params);
        view.setBackgroundResource(R.drawable.shape_circles_normal);
        return view;
    }

    public HackyViewPager getViewpager(){
        return mViewpager;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setSelectIndicatorView(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    public void setSelectIndicatorView(int position){
        if(position >= 0 && position < mLlIndicators.getChildCount()){
            if(mCurrentIndicatorView != null){
                mCurrentIndicatorView.setBackgroundResource(R.drawable.shape_circles_normal);
            }
            View view = mLlIndicators.getChildAt(position);
            view.setBackgroundResource(R.drawable.shape_circles_selected);
            mCurrentIndicatorView = view;
        }

    }

    public void setCurrentItem(int selectIndex) {
        mViewpager.setCurrentItem(selectIndex);
        setSelectIndicatorView(selectIndex);
    }
}
