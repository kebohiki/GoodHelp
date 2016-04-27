package com.wangw.goodhelp.ui.activitys;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
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
import com.wangw.goodhelp.ui.views.HackyViewPager;
import com.wangw.goodhelp.ui.views.IndicatorViewPager;
import com.wangw.goodhelp.utils.FileUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class ImageGalleryActivity extends BaseActivity implements   ImageGalleryAdapter.OnItemLongClickListener {

    @Bind(R.id.pager)
    IndicatorViewPager mPager;

    private Dialog mDialog;
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

        mPager.setIndicators(keys.length);
        mAdapter = new ImageGalleryAdapter(this,keys);
        mPager.getViewpager().setAdapter(mAdapter);
        mAdapter.setListener(this);
        mPager.setCurrentItem(selectIndex);

    }


    @Override
    public void onLongClick(View v) {
        showAlert();
    }

    private void showAlert(){
        if(mDialog == null) {
            View view = LayoutInflater.from(this).inflate(R.layout.layout_alert_download, null);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mDialog != null && mDialog.isShowing())
                        mDialog.dismiss();
                    String key = mAdapter.getItem(mPager.getViewpager().getCurrentItem());
                    onDownLoadImage(Constants.BASEIMAGEURL+key);
                }
            });
            mDialog = new Dialog(this,R.style.AlertDialogStyle);
            mDialog.setContentView(view);
            mDialog.setCancelable(true);
        }
        mDialog.show();
    }

    private void onDownLoadImage(final String url){
        L.d("onDownLoadImage");
        showLoading();
        Observable.create(new Observable.OnSubscribe<DownloadResult>() {
            @Override
            public void call(Subscriber<? super DownloadResult> subscriber) {
                L.d("call");
                DownloadResult result = FileUtils.downloadFile(ImageGalleryActivity.this,url,mCachePath,true);
                subscriber.onNext(result);
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DownloadResult>() {
                    @Override
                    public void onCompleted() {
                        hidenLoading();
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
