package com.renxing.moduleImageLoader.loaderStrategy.glide;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

/**
 * author : Irene
 * e-mail : 821970751@qq.com
 * date   : 2020/3/20 15:16
 * desc   :
 */
public class GlideRoundTransform extends BitmapTransformation {


    private static float radius = 0f;
    private boolean isLeftTop = true;
    private boolean isRightTop = true;
    private boolean isLeftBottom = true;
    private boolean isRightBottom = true;

    public GlideRoundTransform() {
        this(4);
    }

    public GlideRoundTransform(float dp) {
        super();
        this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
    }

    /**
     * 需要设置圆角的部分
     *
     * @param leftTop     左上角
     * @param rightTop    右上角
     * @param leftBottom  左下角
     * @param rightBottom 右下角
     */
    public void setNeedCorner(boolean leftTop, boolean rightTop, boolean leftBottom, boolean rightBottom) {
        isLeftTop = leftTop;
        isRightTop = rightTop;
        isLeftBottom = leftBottom;
        isRightBottom = rightBottom;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return roundCrop(pool, toTransform);
    }

    private Bitmap roundCrop(BitmapPool pool, Bitmap source) {
        int bw = source.getWidth();
        int bh = source.getHeight();
        //修正圆角
        Bitmap outBitmap = pool.get(bw, bh, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(outBitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        RectF rectF = new RectF(0.0F, 0.0F, (float) canvas.getWidth(), (float) canvas.getHeight());
        //先绘制圆角矩形
        canvas.drawRoundRect(rectF, this.radius, this.radius, paint);
        //左上角圆角
        if (!isLeftTop) {
            canvas.drawRect(0, 0, radius, radius, paint);
        }
        //右上角圆角
        if (!isRightTop) {
            canvas.drawRect(canvas.getWidth() - radius, 0, canvas.getWidth(), radius, paint);
        }
        //左下角圆角
        if (!isLeftBottom) {
            canvas.drawRect(0, canvas.getHeight() - radius, radius, canvas.getHeight(), paint);
        }
        //右下角圆角
        if (!isRightBottom) {
            canvas.drawRect(canvas.getWidth() - radius, canvas.getHeight() - radius, canvas.getWidth(), canvas.getHeight(), paint);
        }
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(source, 0, 0, paint);
        return outBitmap;
    }


    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {

    }
}
