package com.wangw.goodhelp.ui.activitys;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.exlogcat.L;
import com.wangw.goodhelp.R;
import com.wangw.goodhelp.base.BaseActivity;
import com.wangw.goodhelp.common.Constants;
import com.wangw.goodhelp.model.DownloadResult;
import com.wangw.goodhelp.ui.adapter.ImageGalleryAdapter;
import com.wangw.goodhelp.utils.FileUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ImageGalleryActivity extends BaseActivity implements View.OnLongClickListener {

    @Bind(R.id.pager)
    ViewPager mPager;

    private AlertDialog mDialog;
    private String mCachePath;

    public static void jumTo(Activity from,String[] keys,int selectIndex){
        Intent intent = new Intent(from,ImageGalleryActivity.class);
        intent.putExtra("selectindex",selectIndex);
        intent.putExtra("keys", keys);
        from.startActivity(intent);
    }

    private ImageGalleryAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_gallery);
        mCachePath = FileUtils.getImageCachePath(this).getAbsolutePath();
        ButterKnife.bind(this);
        onInitView();
    }

    private void onInitView() {

        int selectIndex = getIntent().getIntExtra("selectindex",0);
        String[] keys = getIntent().getStringArrayExtra("keys");

        mAdapter = new ImageGalleryAdapter(this,keys);
        mPager.setAdapter(mAdapter);
        mPager.setCurrentItem(selectIndex);

        mPager.setOnLongClickListener(this);
    }


    @Override
    public boolean onLongClick(View v) {
        showAlert();
        return true;
    }

    private void showAlert(){
        if(mDialog == null) {
            View view = LayoutInflater.from(this).inflate(R.layout.layout_alert_download, null);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mDialog != null && mDialog.isShowing())
                        mDialog.hide();
                    String key = mAdapter.getItem(mPager.getCurrentItem());
                    onDownLoadImage(Constants.BASEIMAGEURL+key);
                }
            });
            mDialog = new AlertDialog.Builder(this)
                    .setView(view)
                    .setCancelable(true)
                    .create();
        }
        mDialog.show();
    }

    private void onDownLoadImage(final String url){
        Observable.create(new Observable.OnSubscribe<DownloadResult>() {
            @Override
            public void call(Subscriber<? super DownloadResult> subscriber) {
                DownloadResult result = FileUtils.downloadFile(ImageGalleryActivity.this,url,mCachePath);
                subscriber.onNext(result);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DownloadResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        L.e("保存图片失败 ： %s",e.getMessage());
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(DownloadResult downloadResult) {
                        if(downloadResult.result){
                            showToast("已保存到"+downloadResult.file.getParent()+"文件夹");
                        }else {
                            showToast("下载失败，请稍后重试");
                        }
                    }
                });
    }

}
