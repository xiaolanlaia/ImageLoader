package com.example.imageloader;

import android.graphics.Bitmap;

/**
 * Created by W on 2018/12/13.
 */

public class DoubleCache implements ImageCache {

    ImageCache mMemoryCache = new MemoryCache();
    ImageCache mDiskCache = new DiskCache();

    /**
     * 先从内存缓存中获取图片，如果没有，再从SD卡中获取
     */
    @Override
    public Bitmap get(String url){
        Bitmap bitmap = mMemoryCache.get(url);
        if (bitmap == null){
            bitmap = mDiskCache.get(url);
        }
        return bitmap;
    }

    public void put(String url,Bitmap bitmap){
        mMemoryCache.put(url,bitmap);
        mDiskCache.put(url,bitmap);
    }

}
