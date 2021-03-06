package com.wangw.goodhelp.ui.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wangw.goodhelp.MainActivity;
import com.wangw.goodhelp.R;
import com.wangw.goodhelp.api.RequestMap;
import com.wangw.goodhelp.common.UserManager;
import com.wangw.goodhelp.api.ServiceHelper;
import com.wangw.goodhelp.base.BaseActivity;
import com.wangw.goodhelp.model.Response;
import com.wangw.goodhelp.model.UserInfo;
import com.wangw.goodhelp.ui.views.TitleBarView;
import com.wangw.goodhelp.utils.CommonUtils;
import com.wangw.goodhelp.utils.EncryptUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginActivity extends BaseActivity {

    @Bind(R.id.titlebar)
    TitleBarView mTitlebar;
    @Bind(R.id.ev_username)
    EditText mEvUsername;
    @Bind(R.id.ev_userpwd)
    EditText mEvUserpwd;
    @Bind(R.id.btn_login)
    Button mBtnLogin;

    // 定义一个变量，来标识是否退出
    private static boolean isExit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initTitleBar("登录");
    }


    @OnClick(R.id.btn_login)
    public void onClick(View view) {
        if (onCheck())
            onLogin(mEvUsername.getText().toString(), mEvUserpwd.getText().toString());
    }

    @OnClick(R.id.tv_agreement)
    public void onClickAgreement(View view){
        WebViewActivity.jumpTo(this,"用户手册","http://oxinxian.com/UserAgreement.html");
    }

    private boolean onCheck() {
        if (CommonUtils.isNotNull(mEvUsername)) {
            if (CommonUtils.isNotNull(mEvUserpwd)) {
                return true;
            } else {
                showToast("密码不能为空");
            }
        } else {
            showToast("用户名不能为空");
        }

        return false;

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

    private void onLogin(final String userName, String pwd) {
        showLoading(false);
        RequestMap map = new RequestMap();
        map.put("username", userName);
        map.put("passwd", EncryptUtils.md5(pwd));
        ServiceHelper.getApi().userLogin(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<UserInfo>>() {
                    @Override
                    public void onCompleted() {
                        hidenLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        onFail(e);
                    }

                    @Override
                    public void onNext(Response<UserInfo> userInfoResponse) {
                        hidenLoading();
                        if (userInfoResponse.isSuccess()) {
                            UserManager.setLoginSuccess(userName);
                            UserManager.saveUserInfo(userInfoResponse.getData());
                            LoginActivity.this.startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        } else {
                            showToast(userInfoResponse.getMessage());
                        }
                    }
                });
    }

}
