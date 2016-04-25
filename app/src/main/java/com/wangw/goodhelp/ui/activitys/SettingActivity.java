package com.wangw.goodhelp.ui.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wangw.goodhelp.MainActivity;
import com.wangw.goodhelp.R;
import com.wangw.goodhelp.base.BaseActivity;
import com.wangw.goodhelp.common.UserManager;
import com.wangw.goodhelp.ui.views.TitleBarView;
import com.wangw.goodhelp.utils.FileUtils;
import com.wangw.goodhelp.utils.SpUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class SettingActivity extends BaseActivity {

    @Bind(R.id.tv_username)
    TextView mTvUsername;
    @Bind(R.id.progress)
    ProgressBar mProgress;
    @Bind(R.id.tv_size)
    TextView mTvSize;

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
        if (isLogin() && getUserInfo() != null)
            mTvUsername.setText("用户名: " + UserManager.getUserName());
        else {
            mTvUsername.setVisibility(View.GONE);
        }

        Observable.create(new Observable.OnSubscribe<Long>() {
            @Override
            public void call(Subscriber<? super Long> subscriber) {
                subscriber.onNext(FileUtils.getFileDirSize(FileUtils.getImageCachePath(SettingActivity.this)));
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        mProgress.setVisibility(View.GONE);
                        double size = aLong*1.0 / 1024 /1024;
                        mTvSize.setText("清除缓存(约" + String.format("%.2f",size) + "M)");
                    }
                });
    }

    @OnClick(R.id.ll_clearcache)
    public void onClearCache(View view) {
        showLoading();
        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                subscriber.onNext(FileUtils.clearCache(FileUtils.getImageCachePath(SettingActivity.this)));
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        hidenLoading();
                        mTvSize.setText("清除缓存(0M)");
                    }
                });
    }

    @OnClick(R.id.tv_logout)
    public void onClick(View view) {
        UserManager.logout();
        if (MainActivity.mainActivity != null)
            MainActivity.mainActivity.finish();
        jumpToLogin();
    }


}
