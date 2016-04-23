package com.wangw.goodhelp.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangw on 16/4/20.
 */
public class CommonUtils {

    public final static String WEIXIN_SNS_MIMETYPE = "vnd.android.cursor.item/vnd.com.tencent.mm.plugin.sns.timeline";//微信朋友圈

    public static boolean isNotNull(EditText txt){
        if(txt == null){
            return false;
        }

        String v = txt.getText().toString().trim();
        return !TextUtils.isEmpty(v);

    }

    public static void copy(Context context,String value){
        ClipboardManager manager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        manager.setPrimaryClip(ClipData.newPlainText(null,value));
    }

    /**
     * 朋友圈
     * @param context
     * @param id
     */
    public static void shareToTimeLine(Context context,int id) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.withAppendedPath(
                        ContactsContract.Data.CONTENT_URI, String.valueOf(id)),
                WEIXIN_SNS_MIMETYPE);
        try {
            context.startActivity(intent);
        }catch (Exception e){
            Toast.makeText(context, "请安装微信客户端后再分享！", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 分享多图到朋友圈，多张图片加文字
     *
     * @param uris
     */
    public static void shareToTimeLine(Context context,String title, ArrayList<Uri> uris) {
        Intent intent = new Intent();
        ComponentName comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");
        intent.setComponent(comp);
        intent.setAction(Intent.ACTION_SEND_MULTIPLE);
        intent.setType("image/*");

        intent.putExtra("Kdescription", title);

        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
        context.startActivity(intent);
    }

}
