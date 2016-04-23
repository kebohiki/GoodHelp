package com.wangw.goodhelp.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.exlogcat.L;
import com.wangw.goodhelp.common.Constants;
import com.wangw.goodhelp.model.DowloadResult;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by wangw on 16/4/23.
 */
public class FileUtils {

    private static final int CACHESIZE = 1024*200;

    public static boolean isExternalStorageWritable(){
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    public static File getImageCachePath(Context context){
        File file;
        if(isExternalStorageWritable()){
            file =  new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),Constants.IMAGES_CACHE);
        }else {
            file = new File(context.getFilesDir(),Constants.IMAGES_CACHE);
        }

        if (!file.exists())
            file.mkdirs();
        return file;
    }

    public static long getFileDirSize(File file){
        long size = 0;
        if(file.exists()) {
            if (file.isFile()) {
                size = file.length();
            } else if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    size += getFileDirSize(files[i]);
                }
            }
        }
        return size;
    }

    public static boolean clearCache(File cacheDir){
        if(cacheDir != null && cacheDir.exists()) {
            if (cacheDir.isFile()) {
                return cacheDir.delete();
            }else if(cacheDir.isDirectory()){
                File[] files = cacheDir.listFiles();
                if(files != null && files.length != 0){
                    for (int i = 0;i < files.length;i++){
                        clearCache(files[i]);
                    }
                    return cacheDir.delete();
                }else {
                    return cacheDir.delete();
                }
            }
        }
        return true;
    }

    public static DowloadResult downloadFile(Context context,String fileURL,String cachePath){
        DowloadResult result = new DowloadResult();
        String fileName = fileURL.substring(fileURL.lastIndexOf("/")+1, fileURL.length());
        File file= new File(cachePath,fileName);
        result.file = file;
        if(file.exists()){
            result.result = true;
        }else {
//            FutureTarget<File> future = Glide.with(context)
//                    .load(url)
//                    .downloadOnly(0, 0);
//            try {
//                result.file = future.get();
//                result.result = result.file.exists();
//            } catch (Exception e) {
//                result.result = false;
//                e.printStackTrace();
//            }

            BufferedInputStream in = null;
            BufferedOutputStream out = null;
            try {
                URL url = new URL(fileURL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                in = new BufferedInputStream(conn.getInputStream(),CACHESIZE);

                out = new BufferedOutputStream(new FileOutputStream(file),CACHESIZE);
                int count =0;
                while ((count = in.read()) != -1){
                    out.write(count);
                }
                out.flush();
                closeIO(in);
                closeIO(out);
                result.result = true;
                //TODO 如果刷新图库，每张图片会多浪费300 - 500毫秒时间
//                refreshMediaStore(context,file);
            } catch (Exception e) {
                result.result = false;
                e.printStackTrace();
            }finally {
                closeIO(in);
                closeIO(out);
            }


        }
        return result;
    }

    public static void closeIO(Closeable io){
        if(io != null){
            try {
                io.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static long start;
    public static void refreshMediaStore(Context context,File file){
        start = System.currentTimeMillis();
        // 把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), file.getName(), null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)
        ));
        L.d("耗时：%s",(System.currentTimeMillis() - start));
    }

}
