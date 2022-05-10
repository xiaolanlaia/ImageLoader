package com.example.moduleimage.old;

import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;

/**
 * @author yzk
 * @datetime 2021-02-08 16:30 GMT+8
 * @email hang7171@163.com
 * @detail :
 */
@GlideModule
public class AzalGlideModule extends AppGlideModule {

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {

        MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context).build();
        builder.setMemoryCache(new LruResourceCache(calculator.getMemoryCacheSize()/2));
        builder.setBitmapPool(new LruBitmapPool(calculator.getBitmapPoolSize()/2));
        long totalSize = FileUtils.getFsAvailableSize(context.getCacheDir().getAbsolutePath());
        long size1G = 1024 * 1024 * 1024;
        long size3G = size1G * 3;
        File cacheDir = new File(context.getCacheDir(), "azal_image");
        if (totalSize > size3G) {
            builder.setDiskCache(new InternalCacheDiskCacheFactory(context, "azal_image", size3G));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                builder.setDefaultRequestOptions(new RequestOptions().format(DecodeFormat.PREFER_RGB_565));
            } else {
                builder.setDefaultRequestOptions(new RequestOptions().format(DecodeFormat.PREFER_ARGB_8888));
            }

            return;
        }
        long size100M = 1024 * 1024 * 100;
        if (totalSize < size100M) {
            return;
        }
        long fileSize = 0;
        if (cacheDir.exists()) {
            fileSize = FileUtils.getFolderSize(cacheDir);
        }
        long newCacheSize = fileSize + totalSize - 1024 * 1024 * 100;
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, "azal_image", newCacheSize));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setDefaultRequestOptions(new RequestOptions().format(DecodeFormat.PREFER_RGB_565));
        } else {
            builder.setDefaultRequestOptions(new RequestOptions().format(DecodeFormat.PREFER_ARGB_8888));
        }
    }
}
