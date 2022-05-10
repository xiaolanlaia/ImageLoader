package com.renxing.moduleImageLoader.imageUtils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.NinePatch;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * author : 
 * time   : 
 * desc   :
 * version:
 */
public class LoadDian9TuUtil {

    public static void loadDian9Tu(Context context, ImageView imageView, String imgUrl) {
        if (context == null) {
            return;
        }
        if (context instanceof Activity) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (((Activity) context).isDestroyed()) {
                    return;
                }
            }
        }
        Glide.with(context)
                .asFile()
                .load(imgUrl)
                .into(new CustomTarget<File>() {
                    @Override
                    public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                        try {
                            FileInputStream is = new FileInputStream(resource);
                            setNinePathImage(context, imageView, BitmapFactory.decodeStream(is));
                            is.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });
    }

    public static void setNinePathImage(Context context, ImageView imageView, Bitmap bitmap) {
        if (bitmap == null)
            return;
        byte[] chunk = bitmap.getNinePatchChunk();
        if (NinePatch.isNinePatchChunk(chunk)) {
            NinePatchDrawable patchy = new NinePatchDrawable(context.getResources(), bitmap, chunk, NinePatchChunk.deserialize(chunk).mPaddings, null);
//            imageView.setBackground(patchy);
            Glide.with(context).load(patchy).into(imageView);
        }
    }
}