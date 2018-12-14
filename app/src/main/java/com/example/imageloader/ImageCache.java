package com.example.imageloader;

import android.graphics.Bitmap;

/**
 * Created by W on 2018/12/13.
 */

public interface ImageCache {
    public Bitmap get(String url);
    public void put(String url,Bitmap bitmap);
}
