package com.wangw.goodhelp.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wangw.goodhelp.model.TopicInfo;
import com.wangw.goodhelp.utils.ImageHelp;
import com.wangw.goodhelp.utils.ScreenUtils;

import java.util.List;

/**
 * Created by wangw on 16/4/23.
 */
public class ExNineGridView extends ViewGroup implements View.OnClickListener {

    private int mLayoutWidth;
    private List<TopicInfo.FilesBean> mFiles;
    private int mRows;
    private int mCloumns;
    private int mGap;
    private int mTotalWidth;

    public ExNineGridView(Context context) {
        super(context);
        onInit();
    }

    public ExNineGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        onInit();
    }

    private void onInit(){
        mGap = ScreenUtils.dp2px(getContext(),2);
        mTotalWidth = ScreenUtils.getScreenWidth(getContext()) - ScreenUtils.dp2px(getContext(),110);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }

    public void setGap(int gap){
        this.mGap = gap;
        onChildLayout();
    }

    public void setImageList(List<TopicInfo.FilesBean> list){
        if(list != null && !list.isEmpty()){
            onGenerationLayout(list);
        }else if(getChildCount() > 0) {
            removeAllViews();
            mFiles = null;
        }
    }

    private void onGenerationLayout(List<TopicInfo.FilesBean> list) {
        //复用View
        if(mFiles == null){
            for (TopicInfo.FilesBean bean : list){
                addView(generationImageView());
            }
        }else {
            int oldSize = mFiles.size();
            int newSize = list.size();
            if(oldSize > newSize){
                removeViewsInLayout(newSize,oldSize - newSize);
            }else if(oldSize < newSize){
                for (int i =0;i<(newSize - oldSize);i++){
                    addView(generationImageView());
                }
            }
        }
        mFiles = list;
        onChildLayout();
    }

    /**
     * childView布局
     */
    private void onChildLayout() {
        int count = getChildCount();
        countRowsAndCloumns(count);
        int singeSize = (mTotalWidth - (mCloumns -1)) / mCloumns;

        LayoutParams params = getLayoutParams();
        params.height = singeSize * mRows + mGap*(mRows - 1);
        setLayoutParams(params);

        int lef,top,right,bottom;
        for (int i = 0;i < count; i++){
            ExImageView img = (ExImageView) getChildAt(i);
            img.setImageURL(mFiles.get(i).getKey());
            lef = i % mCloumns * (singeSize +mGap);
            top = i / mCloumns * (singeSize + mGap);
            right = lef + singeSize;
            bottom = top + singeSize;
            img.layout(lef,top,right,bottom);
        }
    }



    private ExImageView generationImageView(){
        ExImageView imageView = new ExImageView(getContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(generateDefaultLayoutParams());
        imageView.setOnClickListener(this);
        return imageView;
    }

    /**
     * 计算出几行几列
     * @param count
     */
    private void countRowsAndCloumns(int count) {
        if(count <= 3){
            mCloumns = mRows = 1;
        }else if(count <= 6){
            mRows = 2;
            mCloumns = count == 4 ? 2 : 3;
        }else {
            mCloumns = mRows = 3;
        }
    }


    @Override
    public void onClick(View view) {

    }
}
