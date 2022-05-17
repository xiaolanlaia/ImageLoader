package com.renxing.moduleImageLoader.loaderStrategy.glide.transformation;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

/**
 * @author :  WuJianFeng
 * @date :  2022/5/16 15:27
 * todo 圆角矩形边界连接处冲突 圆角与边连接处有冲突问题
 */
public class BorderRoundTransform extends BitmapTransformation {

    private final String ID = getClass().getName();
    private Paint mBorderPaint;
    private float borderWidth;
    private int borderColor;
    private float mRadius;


    public BorderRoundTransform(float borderWidth, int borderColor, float cornerWidth) {
        this.borderWidth = borderWidth;
        this.borderColor = borderColor;
        mRadius = Resources.getSystem().getDisplayMetrics().density * cornerWidth;
        mBorderPaint = new Paint();
        mBorderPaint.setColor(borderColor);
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setStrokeWidth(borderWidth);
        mBorderPaint.setDither(true);

    }

    private Bitmap circleCrop(BitmapPool bitmapPool, Bitmap source) {
        Bitmap result = bitmapPool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        }

        //画图
        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        //设置 Shader
        paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        //----绘制圆角矩形
        RectF rectFs = new RectF(0, 0, source.getWidth(), source.getHeight()); //显示矩形时 具体的宽高 大小数值需要自己调

        canvas.drawRoundRect(rectFs, mRadius, mRadius, paint);

        /************************描边*********************/

        //----绘制圆角矩形
        RectF rectF = new RectF(0, 0, source.getWidth(), source.getHeight()); //显示矩形时 具体的宽高 大小数值需要自己调

        canvas.drawRoundRect(rectF, mRadius, mRadius, mBorderPaint);

        return result;
    }

    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(ID.getBytes(CHARSET));
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof BorderRoundTransform;
    }

    @Override
    public int hashCode() {
        return ID.hashCode();
    }

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
//        return null;
        return circleCrop(pool, toTransform);

    }
}
