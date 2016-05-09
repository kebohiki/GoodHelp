package com.wangw.goodhelp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wangw.goodhelp.api.ApiService;
import com.wangw.goodhelp.api.RequestMap;
import com.wangw.goodhelp.api.ServiceHelper;
import com.wangw.goodhelp.base.BaseActivity;
import com.wangw.goodhelp.model.Response;
import com.wangw.goodhelp.model.TopicInfo;
import com.wangw.goodhelp.ui.activitys.SettingActivity;
import com.wangw.goodhelp.ui.adapter.TopicListAdapter;
import com.wangw.goodhelp.ui.views.RefreshLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity implements RefreshLayout.OnLoadListener, SwipeRefreshLayout.OnRefreshListener {

    public static MainActivity mainActivity;


    @Bind(R.id.lv_topics)
    ListView mLvTopics;
    @Bind(R.id.refreshview)
    RefreshLayout mRefreshview;
    @Bind(R.id.tv_emptyview)
    TextView mTvEmptyview;

    private boolean isExit;
    private TopicListAdapter mAdapter;
    private String mPageSize = "20";
    private int mPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = this;
        if(isLogin()) {
            setContentView(R.layout.activity_main);
            ButterKnife.bind(this);
            initTitleBar("瀚宇轩");
            onInitView();
        }
    }

    private void onInitView() {
        mAdapter = new TopicListAdapter(this);
        mLvTopics.setAdapter(mAdapter);
        mRefreshview.setOnLoadListener(this);
        mRefreshview.setOnRefreshListener(this);
        showLoading();
        onRefresh();
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (!isLogin()) {
            jumpToLogin();
            return;
        }
    }

    @Override
    protected void initTitleBar(String title) {
        super.initTitleBar(title);
        ImageView img = new ImageView(this);
        img.setImageResource(R.mipmap.setting_icon);
        mTitle.addRightView(img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
            }
        });
    }


    private void getTopiListData(final boolean isMore){
        RequestMap map = new RequestMap();
        map.put("page", String.valueOf(mPage));
        map.put("pageSize", String.valueOf(mPageSize));
        map.put("userId", getUid());
        ServiceHelper.getApi().getAllTopics(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<List<TopicInfo>>>() {
                    @Override
                    public void onCompleted() {
                        hidenLoading();
                        mRefreshview.onFinish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        onFail(e);
                        mRefreshview.onFinish();
                    }

                    @Override
                    public void onNext(Response<List<TopicInfo>> topicInfoResponse) {
                        if (topicInfoResponse.isSuccess()) {
                            List<TopicInfo> list = topicInfoResponse.getData();
                            if (list == null && list.isEmpty()) {
                                if (!isMore) {
                                    mLvTopics.setVisibility(View.GONE);
                                    mTvEmptyview.setVisibility(View.VISIBLE);
                                }
                            } else {
                                mLvTopics.setVisibility(View.VISIBLE);
                                mTvEmptyview.setVisibility(View.GONE);
                                if (isMore) {
                                    mAdapter.addMoreDatas(list);
                                } else {
                                    mAdapter.refreshDatas(list);
                                }
                            }
                        } else {
                            showToast(topicInfoResponse.getMessage());
                        }
                    }
                });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            showToast("再按一次退出程序");
            // 利用handler延迟发送更改状态信息
            Observable.empty()
                    .delay(2000, TimeUnit.MILLISECONDS, Schedulers.newThread())
                    .subscribe(new Subscriber<Object>() {
                        @Override
                        public void onCompleted() {
                            isExit = false;
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onNext(Object o) {
                        }
                    });
        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainActivity = null;
    }


    @Override
    public void onLoad() {
        mPage++;
        getTopiListData(true);
    }

    @Override
    public void onRefresh() {
        mPage = 0;
        getTopiListData(false);
    }
}
