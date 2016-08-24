package com.hui.myclock;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.lang.ref.SoftReference;
import java.util.HashMap;

/**
 * 图片缓存原理
 * Created by liuhui on 16/7/26.
 */
public class ReferenceText {
    private HashMap<String,SoftReference<Bitmap>> imageCache = new HashMap<>();

    public void addBitmapToCache(String path){
        // 强引用的Bitmap对象
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        // 软引用的Bitmap对象
        SoftReference<Bitmap> bitmapCache = new SoftReference<>(bitmap);
        // 添加该对象到Map中使其缓存
        imageCache.put(path,bitmapCache);
    }

    public Bitmap getBitmapFromCache(String path){
        // 从缓存中取软引用的Bitmap对象
        SoftReference<Bitmap> softBitmap = imageCache.get(path);

        // 判断是否存在软引用
        if (softBitmap==null){
            return null;
        }
        // 取出Bitmap对象，如果由于内存不足Bitmap被回收，将取得空
        Bitmap bitmap = softBitmap.get();
        return bitmap;
    }

    public static void main(String args[]){

    }
}
