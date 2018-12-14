package com.example.imageloader;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by W on 2018/12/13.
 */

public class MemoryCache implements ImageCache {

    /**
     * 图片缓存
     */
    private LruCache<String,Bitmap>mMemoryCache;
    public MemoryCache(){
        initImageCache();
    }

    private void initImageCache(){
        //计算可使用的最大内存
        final int maxMemory = (int)(Runtime.getRuntime().maxMemory() / 1024);
        //取四分之一可用内存作为缓存
        final int cacheSize = maxMemory / 4;
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key,Bitmap bitmap){
                return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
            }
        };
    }
    @Override
    public void put(String url, Bitmap bitmap){
        mMemoryCache.put(url,bitmap);

    }
    @Override
    public Bitmap get(String url){
        return mMemoryCache.get(url);
    }
}
