package com.wangw.goodhelp.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.exlogcat.L;
import com.wangw.goodhelp.R;
import com.wangw.goodhelp.base.BaseActivity;
import com.wangw.goodhelp.common.Constants;
import com.wangw.goodhelp.model.DownloadResult;
import com.wangw.goodhelp.model.TopicInfo;
import com.wangw.goodhelp.ui.activitys.ImageGalleryActivity;
import com.wangw.goodhelp.ui.views.ExImageView;
import com.wangw.goodhelp.ui.views.ExNineGridView;
import com.wangw.goodhelp.utils.CommonUtils;
import com.wangw.goodhelp.utils.FileUtils;
import com.wangw.goodhelp.utils.ImageHelp;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by wangw on 16/4/21.
 */
public class TopicListAdapter extends CommonAdapter<TopicInfo> {

    private String mCachePath;

    public TopicListAdapter(Context context) {
        super(context, R.layout.layout_topic_item);
        mCachePath = FileUtils.getImageCachePath(mContext).getAbsolutePath();
    }

    @Override
    public void convert(ViewHolder helper, final TopicInfo item) {
        helper.setText(R.id.tv_nickname, item.getAuthor().getNickname());
        helper.setText(R.id.tv_topic_intro, item.getTopic_intro());

        ImageHelp.loadHeaderImage(mContext, item.getAuthor().getFile().getKey(), (ImageView) helper.getView(R.id.iv_header));

//        NineGridlayout nv = helper.getView(R.id.nineview);
//        nv.setImagesData(item.getFiles());

        final ExNineGridView nv = helper.getView(R.id.nineview);
//        int r = new Random().nextInt(item.getFiles().size());
        nv.setImageList(item.getFiles());

        helper.getView(R.id.iv_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickShareView(item);
            }
        });

        nv.setListener(new ExNineGridView.OnItemClickListener() {
            @Override
            public void onItemClick(ExImageView item, int position) {
                List<TopicInfo.FilesBean> files = nv.getFiles();
                int count = files.size();
                String[] keys = new String[count];
                for (int i = 0 ;i < count;i++){
                    keys[i] = files.get(i).getKey();
                }
                ImageGalleryActivity.jumTo((Activity) mContext,keys,position);
            }
        });

    }

    private void onClickShareView(TopicInfo item) {
        CommonUtils.copy(mContext, item.getTopic_intro());
        List<TopicInfo.FilesBean> files = item.getFiles();
        if (files != null && !files.isEmpty()) {
            showLoading();
            onDownlaodFile(item.getTopic_intro(),files);
        } else {
            CommonUtils.shareToTimeLine(mContext, 0);
//                CommonUtils.shareToTimeLine(mContext,item.getTopic_intro(),new ArrayList<Uri>());
        }
    }

    private void onDownlaodFile(final String topic_intro,  List<TopicInfo.FilesBean> files) {
        Observable.from(files)
                .map(new Func1<TopicInfo.FilesBean, String>() {
                    @Override
                    public String call(TopicInfo.FilesBean filesBean) {
                        return Constants.BASEIMAGEURL + filesBean.getKey();
                    }
                })
                .map(new Func1<String, DownloadResult>() {
                    @Override
                    public DownloadResult call(String s) {
                        return FileUtils.downloadFile(mContext,s, mCachePath);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DownloadResult>() {

                    private int mErrorNum = 0;
                    private ArrayList<Uri> mUris = new ArrayList<Uri>(5);

                    @Override
                    public void onCompleted() {
                        hidenLoading();
                        if (mErrorNum != 0)
                            showToast("下载失败" + mErrorNum + "个文件");
                        CommonUtils.shareToTimeLine(mContext, topic_intro, mUris);
                    }

                    @Override
                    public void onError(Throwable e) {
                        hidenLoading();
                        Toast.makeText(mContext, "下载图片出现异常，请重试", Toast.LENGTH_LONG).show();
                        L.e("下载图片失败：" + e.getMessage());
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(DownloadResult result) {
                        if (!result.result) {
                            mErrorNum++;
                        } else {
                            mUris.add(Uri.fromFile(result.file));
                        }
                    }
                });
    }

    private void showToast(String msg) {
        if (mContext instanceof BaseActivity) {
            ((BaseActivity) mContext).showToast(msg);
        }
    }

    private void showLoading() {
        if (mContext instanceof BaseActivity) {
            ((BaseActivity) mContext).showLoading();
        }
    }

    private void hidenLoading() {
        if (mContext instanceof BaseActivity)
            ((BaseActivity) mContext).hidenLoading();
    }

}
