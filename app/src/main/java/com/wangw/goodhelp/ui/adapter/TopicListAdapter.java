package com.wangw.goodhelp.ui.adapter;

import android.content.Context;

import com.wangw.goodhelp.model.TopicInfo;

import java.util.List;

/**
 * Created by wangw on 16/4/21.
 */
public class TopicListAdapter extends CommonAdapter<TopicInfo> {

    public TopicListAdapter(Context context, List<TopicInfo> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper, TopicInfo item) {

    }
}
