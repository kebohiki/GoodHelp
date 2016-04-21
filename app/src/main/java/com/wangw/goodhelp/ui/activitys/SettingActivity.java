package com.wangw.goodhelp.ui.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wangw.goodhelp.MainActivity;
import com.wangw.goodhelp.R;
import com.wangw.goodhelp.base.BaseActivity;
import com.wangw.goodhelp.common.UserManager;
import com.wangw.goodhelp.ui.views.TitleBarView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

    @Bind(R.id.titlebar)
    TitleBarView mTitlebar;
    @Bind(R.id.tv_username)
    TextView mTvUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        initTitleBar("设置");
        mTitle.showBackView(true);
        if(isLogin() && getUserInfo() != null)
            mTvUsername.setText("用户名: "+getUserInfo().getUsername());
        else {
            mTvUsername.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.tv_logout)
    public void onClick(View view){
        UserManager.logout();
        if(MainActivity.mainActivity != null)
            MainActivity.mainActivity.finish();
        jumpToLogin();
    }


}
