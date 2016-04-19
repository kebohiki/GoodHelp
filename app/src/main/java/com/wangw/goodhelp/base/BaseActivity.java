package com.wangw.goodhelp.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.wangw.goodhelp.R;
import com.wangw.goodhelp.ui.views.TitleBarView;

/**
 * Created by wangw on 2016/4/19.
 */
public class BaseActivity extends FragmentActivity {

    private TitleBarView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void initTitleBar(String title){
        mTitle = (TitleBarView) findViewById(R.id.titlebar);
        if(mTitle != null)
            mTitle.setTitle(title);
    }

}
