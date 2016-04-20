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
import com.wangw.goodhelp.UserManager;
import com.wangw.goodhelp.api.ServiceHelper;
import com.wangw.goodhelp.base.BaseActivity;
import com.wangw.goodhelp.model.Response;
import com.wangw.goodhelp.model.UserInfo;
import com.wangw.goodhelp.ui.views.TitleBarView;
import com.wangw.goodhelp.utils.CommonUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
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
    @Bind(R.id.tv_agreement)
    TextView mTvAgreement;

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
        if(onCheck())
            onLogin(mEvUsername.getText().toString(), mEvUserpwd.getText().toString());
    }

    private boolean onCheck() {
        if (CommonUtils.isNotNull(mEvUsername)){
            if(CommonUtils.isPhoneNum(mEvUsername.getText().toString())) {
                if (CommonUtils.isNotNull(mEvUserpwd)) {
                    if(mEvUserpwd.length() >= 6 && mEvUserpwd.getText().length() <= 15)
                        return true;
                    else
                        showToast("请输入正确的密码");
                }else {
                    showToast("密码不能为空");
                }
            }else {
                showToast("请输入正确的手机号");
            }
        }else {
            showToast("手机号不能为空");
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
                    .delay(2000, TimeUnit.MILLISECONDS,Schedulers.newThread())
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

    private void onLogin(String userName, String pwd) {
        showLoading(false);
        Map<String, String> map = new HashMap<>();
        map.put("username", userName);
        map.put("passwd", pwd);
        ServiceHelper.GetApi().userLogin(map)
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
                        if(userInfoResponse.isSuccess()){
                            UserManager.setLoginSuccess();
                            UserManager.saveUserInfo(userInfoResponse.getData());
                            LoginActivity.this.startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        }else {
                            showToast(userInfoResponse.getMessage());
                        }
                    }
                });
    }

}
