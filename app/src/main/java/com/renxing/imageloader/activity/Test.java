package com.renxing.imageloader.activity;

import android.graphics.Bitmap;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.FutureTarget;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/**
 * @author :  WuJianFeng
 * @date :  2022/6/7 18:55
 */
public class Test {
//    public void fetchImage(@NotNull Resource resource, @NotNull Function1<? super Bitmap, Unit> function1) {
//        try {
//            String tag = resource.getTag();
//            if (paramMap.containsKey(tag)) {
//                String imageUrl = paramMap.get(tag) + "?imageView2/1/w/" + DisplayUtils.dp2px(360) + "/h/" + DisplayUtils.dp2px(360) + "/interlace/1/q/75";
//                FutureTarget<Bitmap> bitmapFutureTarget = Glide.with(AppConfig.context)
//                        .asBitmap()
//                        .format(DecodeFormat.PREFER_ARGB_8888)
//                        .load(imageUrl)
//                        .diskCacheStrategy(DiskCacheStrategy.ALL)
//                        .submit();
//                Bitmap bitmap = bitmapFutureTarget.get(2000L, TimeUnit.MILLISECONDS);
//                function1.invoke(bitmap);
//                Glide.with(AppConfig.context).clear(bitmapFutureTarget);
//            } else {
//                function1.invoke(null);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            function1.invoke(null);
//        }
//    }
}
