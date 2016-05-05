package com.wangw.goodhelp.ui.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebViewClient;

import com.wangw.goodhelp.R;
import com.wangw.goodhelp.base.BaseActivity;
import com.wangw.goodhelp.ui.views.ProgressWebView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WebViewActivity extends BaseActivity {

    @Bind(R.id.baseweb_webview)
    ProgressWebView mBasewebWebview;

    public static void jumpTo(Activity from,String title,String url){
        Intent intent = new Intent(from,WebViewActivity.class);
        intent.putExtra("title",title);
        intent.putExtra("url",url);
        from.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);

        onInitView();



    }

    private void onInitView() {
        String title = getIntent().getStringExtra("title");
        initTitleBar(title);
        mTitle.showBackView(true);


        mBasewebWebview.getSettings().setJavaScriptEnabled(true);
        mBasewebWebview.setWebViewClient(new WebViewClient());

        String url = getIntent().getStringExtra("url");
        mBasewebWebview.loadUrl(url);


    }
}
