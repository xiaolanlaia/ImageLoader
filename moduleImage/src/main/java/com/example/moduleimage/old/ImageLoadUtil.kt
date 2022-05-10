package com.example.moduleimage.old

import android.app.Activity
import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.moduleimage.imageUtils.DisplayUtils
import com.renxing.image.old.ImageLoadInterface

/**
 * author : Irene
 * e-mail : 821970751@qq.com
 * date   : 2020/3/20 15:02
 * desc   :
 */
object ImageLoadUtil {

    var imageLoadInterface: ImageLoadInterface = ImageLoadImpl()

    /**
     * 图片加载
     *
     * @param url 路径
     * @param iv  显示控件
     */
    fun loadAvatar(mContext: Context, url: String, iv: ImageView) {
        imageLoadInterface.loadAvatar(mContext,url,iv)
    }

    /**
     * 图片加载
     *
     * @param mContext
     * @param url
     * @param iv
     */
    fun loadAvatarSmall(mContext: Context, url: String, iv: ImageView, withPX: Int, heightPX: Int) {
        imageLoadInterface.loadAvatarSmall(mContext,url,iv,withPX,heightPX)
    }


    /**
     * 图片加载
     *
     * @param url 路径
     * @param iv  显示控件
     */
    fun loadIv(mContext: Context, url: String, iv: ImageView) {
        imageLoadInterface.loadIv(mContext,url,iv)
    }

    fun loadIvCenterInside(mContext: Context, id: Int, iv: ImageView) {
        imageLoadInterface.loadIvCenterInside(mContext,id,iv)
    }

    fun loadIv(mContext: Context, id: Int, iv: ImageView) {

        imageLoadInterface.loadIv(mContext,id,iv)
       }

    fun loadIvCenterInside(mContext: Context, url: String, iv: ImageView) {
        imageLoadInterface.loadIvCenterInside(mContext,url,iv)
    }

    fun loadIvCenterInsideNoMemory(mContext: Context, url: String, iv: ImageView, withPX: Int, heightPX: Int) {
        imageLoadInterface.loadIvCenterInsideNoMemory(mContext,url,iv,withPX,heightPX)
    }

    fun loadIvNoType(mContext: Context, url: String, iv: ImageView) {
        imageLoadInterface.loadIvNoType(mContext,url,iv)
    }

    fun loadIv(mContext: Context, url: String, iv: ImageView, placeholderAndErrorId: Int) {
        imageLoadInterface.loadIv(mContext,url,iv,placeholderAndErrorId)
    }

    fun loadIvHomeMixFragment(mContext: Context, url: String, iv: ImageView, placeholderAndErrorId: Int) {
        imageLoadInterface.loadIvHomeMixFragment(mContext,url,iv,placeholderAndErrorId)
    }

    fun loadIvHomeFragment(mContext: Context, url: String, iv: ImageView, placeholderAndErrorId: Int) {
        imageLoadInterface.loadIvHomeFragment(mContext,url,iv,placeholderAndErrorId)
    }

    fun loadIvNoMemoryCacheInChatRoom(mContext: Context, url: String, iv: ImageView, placeholderAndErrorId: Int) {
        imageLoadInterface.loadIvNoMemoryCacheInChatRoom(mContext,url,iv,placeholderAndErrorId)
    }

    fun loadIvCircleNoCache(mContext: Context, uri: Uri, iv: ImageView) {
        imageLoadInterface.loadIvCircleNoCache(mContext,uri,iv)
    }

    /**
     * 不走缓存
     *
     * @param url
     * @param iv
     */
    fun loadIvNoCache(mContext: Context, url: String, iv: ImageView) {
        imageLoadInterface.loadIvNoCache(mContext,url,iv)
    }

    fun loadIvNoCache(mContext: Context, uri: Uri, iv: ImageView) {
        imageLoadInterface.loadIvNoCache(mContext,uri,iv)
    }

    /**
     * 不走缓存
     *
     * @param url
     * @param iv
     */
    fun loadIvNoMemoryCache(mContext: Context, url: String, iv: ImageView) {
        imageLoadInterface.loadIvNoMemoryCache(mContext,url,iv)
    }

    /**
     * 增加圆角
     *
     * @param url 路径
     * @param iv  显示控件
     */
    fun loadIvWithRoundDP(mContext: Context, url: String, iv: ImageView, defaultIv: Int, dp: Int) {
        imageLoadInterface.loadIvWithRoundDP(mContext,url,iv,defaultIv,dp)
    }

    /**
     * 加载圆形图片
     *
     * @param url 路径
     * @param iv  显示控件
     */
    fun loadCircle(mContext: Context, url: String, iv: ImageView) {
        imageLoadInterface.loadCircle(mContext,url,iv)
    }

    fun loadGifIvOnceNoMemoryInChatRoom(mContext: Context, url: String, iv: ImageView, mOnAnimationStatus: ImageLoadInterface.OnAnimationStatus) {
        imageLoadInterface.loadGifIvOnceNoMemoryInChatRoom(mContext,url,iv,mOnAnimationStatus)
    }

    fun loadGifIvOnceNoMemoryInChatRoom(mContext: Context, resId: Int, iv: ImageView, mOnAnimationStatus: ImageLoadInterface.OnAnimationStatus) {
        imageLoadInterface.loadGifIvOnceNoMemoryInChatRoom(mContext,resId,iv,mOnAnimationStatus)
    }

    /**
     * 循环播放
     */
    fun loadGifForever(mContext: Context, url: String, iv: ImageView) {
        imageLoadInterface.loadGifForever(mContext,url,iv)
    }

    fun loadGifForeverNoCacheInChatRoom(mContext: Context, drawableId: Int, iv: ImageView) {
        imageLoadInterface.loadGifForeverNoCacheInChatRoom(mContext,drawableId,iv)
    }

    /**
     * 清除图片所有缓存
     */
    fun clearImageAllCache(mContext: Context) {
        imageLoadInterface.clearImageAllCache(mContext)
    }

    fun getCacheSizeByte(mContext: Context): Long {

        return imageLoadInterface.getCacheSizeByte(mContext)
    }

    fun loadImageWithCustomTarget(mContext: Context, url: String, customTarget: CustomTarget<Bitmap>) {
        imageLoadInterface.loadImageWithCustomTarget(mContext,url,customTarget)
    }

    fun loadBitmapWithCustomTarget(mContext: Context, url: String, customTarget: CustomTarget<Bitmap>) {
        imageLoadInterface.loadBitmapWithCustomTarget(mContext,url,customTarget)

    }

    fun initChatRoomBackgroundWithDrawable(mContext: Context, url: String, view: View, resId: Int) {
        imageLoadInterface.initChatRoomBackgroundWithDrawable(mContext,url,view,resId)
    }

    fun updateChatRoomBackgroundWithDrawable(mContext: Context, url: String, view: View, resId: Int) {
        imageLoadInterface.updateChatRoomBackgroundWithDrawable(mContext,url,view,resId)
    }

    fun load9PngWithResId(mContext: Context, url: String, view: View, resId: Int) {
        imageLoadInterface.load9PngWithResId(mContext,url,view,resId)
    }

//    private fun getNinePatchDrawable(bitmap: Bitmap, mContext: Context): Drawable {
//        return imageLoadInterface.getNinePatchDrawable(bitmap,mContext)
//    }

    private fun loadDrawableToImageBackground(context: Context, url: String, view: View) {
        if (context is Activity) {
            if (context.isFinishing || context.isDestroyed) {
                return
            }
        }
        Glide.with(context)
                .asDrawable()
                .centerCrop()
                .override(DisplayUtils.displayPxWidth, DisplayUtils.displayPxHeight)
                .load(url)
                .skipMemoryCache(true)
                .into(object : CustomTarget<Drawable>() {
                    override fun onLoadCleared(placeholder: Drawable?) {}
                    override fun onResourceReady(
                        resource: Drawable,
                        transition: Transition<in Drawable>?
                    ) {
                        view.background = resource
                    }
                })
    }

    fun fixAvatarUrl(url: String, withPX: Int, heightPX: Int): String {
        return imageLoadInterface.fixAvatarUrl(url,withPX,heightPX)
    }

    fun loadIvWithCircle(mContext: Context, url: String, iv: ImageView, defaultIv: Int) {
        imageLoadInterface.loadIvWithCircle(mContext,url,iv,defaultIv)

    }

    fun loadIvWithRoundPX(mContext: Context, url: String, iv: ImageView, defaultIv: Int, px: Int) {
        imageLoadInterface.loadIvWithRoundPX(mContext,url,iv,defaultIv,px)

    }

    fun loadIvWithCircleBorder(mContext: Context, url: String, iv: ImageView, defaultIv: Int, borderColor: Int, borderWidth: Float) {
        imageLoadInterface.loadIvWithCircleBorder(mContext,url,iv,defaultIv,borderColor,borderWidth)

    }
}