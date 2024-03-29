package com.renxing.moduleImageLoader.loaderStrategy.glide.placeholder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author :  WuJianFeng
 * @date :  2022/5/13 15:43
 */
public class CircleRoundDrawable extends Drawable {
    /**
     * 画笔
     */

    private Paint paint = new Paint();
    /**
     * 图片宽与高的最小值
     */
    private int mWidth;
    /**
     * 矩形
     */
    private RectF rectF;
    /**
     * 半径
     */
    private int radius;
    /**
     * 默认圆角
     */
    private int roundAngle = 10;
    /**
     * 位图
     */
    private final Bitmap bitmap;
    /**
     * 默认为圆形
     */
    private int type=1;

    public static final int TYPE_ROUND = 1;
    public static final int TYPE_CIRCLE = 2;


    public CircleRoundDrawable(Context context, int resID) {
        this(BitmapFactory.decodeResource(context.getResources(), resID), 0, 0);
    }

    public CircleRoundDrawable(Context context, int resID, int w, int h) {
        this(BitmapFactory.decodeResource(context.getResources(), resID), w, h);
    }

    public CircleRoundDrawable(Bitmap oldBitmap, int w, int h) {
        if (w != 0) {
            Matrix matrix = new Matrix();
            float scaleWidth = ((float) w / oldBitmap.getWidth());
            float scaleHeight = ((float) h / oldBitmap.getHeight());
            matrix.postScale(scaleWidth, scaleHeight);
            this.bitmap = Bitmap.createBitmap(oldBitmap, 0, 0, oldBitmap.getWidth(), oldBitmap.getHeight(),
                    matrix, true);
        } else {
            this.bitmap = oldBitmap;
        }

        if (this.bitmap == null) {
            return;
        }

        paint.setAntiAlias(true);//抗锯齿
        paint.setDither(true);//抖动,不同屏幕尺的使用保证图片质量

        ///位图渲染器
        BitmapShader bitmapShader = new BitmapShader(this.bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(bitmapShader);
        mWidth = Math.min(this.bitmap.getWidth(), this.bitmap.getHeight());
        //初始化半径
        radius = mWidth / 2;
    }

    /***
     * 设置圆角
     * @param roundAngle 百分比
     */
    public CircleRoundDrawable setRoundAngle(float roundAngle) {
        this.roundAngle = (int) roundAngle;
        return this;
    }

    public CircleRoundDrawable setType(int type) {
        this.type = type;
        return this;
    }

    /**
     * drawable将被绘制在画布上的区域
     *
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
        //绘制区域
        rectF = new RectF(left, top, right, bottom);
    }

    /**
     * 核心方法
     *
     * @param canvas
     */
    @Override
    public void draw(@NonNull Canvas canvas) {
        if (type == TYPE_CIRCLE) {
            canvas.drawCircle(mWidth / 2, mWidth / 2, radius, paint);
        } else{
            canvas.drawRoundRect(rectF, roundAngle, roundAngle, paint);
        }

    }

    @Override
    public void setAlpha(int i) {
        if (paint != null){
            paint.setAlpha(i);
        }
        invalidateSelf();//更新设置

    }

    @Override
    public int getIntrinsicHeight() {
        if (type == TYPE_CIRCLE) {
            return mWidth;
        }
        if (bitmap == null){
            return 0;
        }
        return bitmap.getHeight();
    }

    @Override
    public int getIntrinsicWidth() {
        if (type == TYPE_CIRCLE) {
            return mWidth;
        }
        if (bitmap == null){
            return 0;
        }
        return bitmap.getWidth();
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
        invalidateSelf();//更行设置

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
