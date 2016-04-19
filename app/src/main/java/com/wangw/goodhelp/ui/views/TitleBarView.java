package com.wangw.goodhelp.ui.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wangw.goodhelp.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wangw on 2016/1/7.
 * 通用的TitleBar
 */
public class TitleBarView extends RelativeLayout {

    @Bind(R.id.lv_title_right)
    LinearLayout mlvTitleRight;
    @Bind(R.id.tv_title)
     TextView mTvTitle;

    private boolean mIsBackFinishEnable;
    private OnClickTitleBarBackCallback mCallBack;
    private TextView mTvRightText;

    public TitleBarView(Context context) {
        super(context);
        onInit();
    }

    public TitleBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        onInit();
    }

    private void onInit() {
        inflate(getContext(), R.layout.layout_title, this);
        ButterKnife.bind(this);
    }

    public void showBackView(Boolean isShow){
        if(isShow){
            mlvTitleRight.setVisibility(VISIBLE);
        }else {
            mlvTitleRight.setVisibility(GONE);
        }
    }

    /**
     * 设置点击返回按钮的回调事件
     *
     * @param callback
     */
    public void setOnClickBackCallback(OnClickTitleBarBackCallback callback) {
        this.mCallBack = callback;
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        this.mTvTitle.setText(title);
    }

    /**
     * 设置标题
     *
     * @param resId
     */
    public void setTitle(int resId) {
        this.mTvTitle.setText(resId);
    }

    /**
     * 设置点击返回按钮后是否finish当前的Activity
     * 注意：默认是true
     *
     * @param enable
     */
    public void setBackFinishEnable(boolean enable) {
        this.mIsBackFinishEnable = enable;
    }

    @OnClick(R.id.lv_title_left)
    public void onClickBack(View view) {
        if (mCallBack != null) {
            mCallBack.onClickBack(view);
        }

        if (mIsBackFinishEnable && getContext() instanceof Activity) {
            ((Activity) getContext()).finish();
        }
    }

    /**
     * 向TitleBar右侧添加自定义View
     *
     * @param view
     */
    public void addRightView(View view) {
        mlvTitleRight.addView(view);
    }

    public void addRightView(View view, LinearLayout.LayoutParams params) {
        view.setLayoutParams(params);
        mlvTitleRight.addView(view);
    }

    private TextView getRightTextView() {
        if (mTvRightText == null) {
            mTvRightText = new TextView(getContext());
            mTvRightText.setTextSize(22);
            mTvRightText.setTextColor(Color.BLACK);
            mlvTitleRight.addView(mTvRightText);
        }
        return mTvRightText;
    }

    public interface OnClickTitleBarBackCallback {
        /**
         * 点击返回View的回调事件
         *
         * @param view
         */
        void onClickBack(View view);
    }


}
