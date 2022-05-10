package com.example.moduleimage.old

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.NinePatchDrawable
import android.net.Uri
import android.os.Looper
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.example.moduleimage.R
import com.example.moduleimage.imageUtils.DisplayUtils
import com.renxing.image.old.CircleBorderTransformation
import com.renxing.image.old.ImageLoadInterface
import com.renxing.image.old.ModuleImageConstant
import java.io.File
import java.io.FileInputStream

class ImageLoadImpl : ImageLoadInterface {
    private fun checkUrl(url: String): Boolean {
        if (TextUtils.isEmpty(url)) {
            return true
        }
        return url.endsWith("null")
    }
    private fun activityFinishedOrDestroyed(mContext: Context): Boolean {
        if (mContext is Activity) {
            if (mContext.isFinishing || mContext.isDestroyed) {
                return true
            }
        }

        return false
    }
    private fun appendUrl(url: String): String {
        var newUrl = ""
        url.run{
            if (startsWith(ModuleImageConstant.HTTP) && (endsWith(ModuleImageConstant.PNG_LOWERCASE) || endsWith(
                    ModuleImageConstant.PNG_UPPERCASE
                )
                        || endsWith(ModuleImageConstant.JPEG_UPPERCASE) || endsWith(
                    ModuleImageConstant.JPEG_LOWERCASE
                )
                        || endsWith(ModuleImageConstant.JPG_UPPERCASE) || endsWith(
                    ModuleImageConstant.JPG_LOWERCASE
                )) && !contains(ModuleImageConstant.QUERY_MARK)) {
                            //将http替换为https
                            if (!startsWith(ModuleImageConstant.HTTPS)){
                                replace(ModuleImageConstant.HTTP, ModuleImageConstant.HTTPS)
                            }
                newUrl = this + ModuleImageConstant.URL_APPEND_STR
            }else{
                return url
            }
        }
        return newUrl
    }

    private fun replaceHttpToHttps(url: String) : String{
        var newUrl = ""
         url.run{
            //将http替换为https
            if (!startsWith(ModuleImageConstant.HTTPS)){
                newUrl = replace(ModuleImageConstant.HTTP, ModuleImageConstant.HTTPS)
            }else{
                return url
            }
        }
        return newUrl
    }

    private fun appendUrl(url: String, width: Int, height: Int, needToPx: Boolean): String {
        var newUrl = ""
        url.run{
            if (!this.contains(ModuleImageConstant.URL_APPEND_WIDTH)) {
                newUrl = if (needToPx) {
                    this + ModuleImageConstant.URL_APPEND_WIDTH + DisplayUtils.dp2px(width.toFloat()) + ModuleImageConstant.URL_APPEND_HEIGHT + DisplayUtils.dp2px(height.toFloat()) + ModuleImageConstant.interlaceStr
                } else {
                    this + ModuleImageConstant.URL_APPEND_WIDTH + width + ModuleImageConstant.URL_APPEND_HEIGHT + height + ModuleImageConstant.interlaceStr
                }
            }
        }
        return newUrl
    }
    /**
     * 图片加载
     *
     * @param url 路径
     * @param iv  显示控件
     */
    override fun loadIv(mContext: Context, url: String, iv: ImageView) {
        loadIv(mContext, url, iv, R.drawable.transparent_bg, -1, -1)
    }

    /**
     * 图片加载
     *
     * @param url       路径
     * @param iv        显示控件
     * @param defaultIv 默认图片id
     */
    @SuppressLint("CheckResult")
    private fun loadIv(mContext: Context, url: String, iv: ImageView, defaultIv: Int, with: Int, height: Int) {
        if (activityFinishedOrDestroyed(mContext)) return
        if (checkUrl(url)) {
            return
        }
        val requestOptions = RequestOptions()
        if (defaultIv > 0) {
            requestOptions.placeholder(defaultIv)
            requestOptions.error(defaultIv)
        }
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop()
        Glide.with(mContext).load(appendUrl(url)).apply(requestOptions).transition(
            DrawableTransitionOptions.withCrossFade()).into(iv)
    }

    override fun loadIv(mContext: Context, id: Int, iv: ImageView) {
        if (activityFinishedOrDestroyed(mContext)) return
        val requestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.transparent_bg)
            .error(R.drawable.transparent_bg).diskCacheStrategy(DiskCacheStrategy.ALL)
        Glide.with(mContext).load(id).apply(requestOptions).transition(DrawableTransitionOptions.withCrossFade()).into(iv)
    }

    override fun loadIv(mContext: Context, url: String, iv: ImageView, placeholderAndErrorId: Int) {
        if (activityFinishedOrDestroyed(mContext)) return
        if (checkUrl(url)) {
            return
        }
        val requestOptions = RequestOptions()
            .placeholder(placeholderAndErrorId)
            .error(placeholderAndErrorId)
            .centerCrop()
            .dontAnimate()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
        Glide.with(mContext).load(appendUrl(url)).apply(requestOptions).into(iv)
    }

    /**
     * 图片加载
     *
     * @param url 路径
     * @param iv  显示控件
     */
    override fun loadAvatar(mContext: Context, url: String, iv: ImageView) {
        if (activityFinishedOrDestroyed(mContext)) return
        if (checkUrl(url)) {
            return
        }
        loadAvatarSmall(mContext, url, iv)
    }

    /**
     * 图片加载
     *
     * @param mContext
     * @param url
     * @param iv       makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);这个方法的测量imageView的宽高是有问题的，
     * 只有设置了minWidth或者mineHeight或者背景，而且背景是有宽高的时候才能准确测量
     *
     *
     * 先根据七牛Api，设置一个最大跟屏幕宽一样的正方形，暂时解决头像问题吧
     */
    private fun loadAvatarSmall(mContext: Context, url: String, iv: ImageView) {
        if (activityFinishedOrDestroyed(mContext)) return
        if (checkUrl(url)) {
            return
        }
        val requestOptions = RequestOptions()
            .placeholder(R.mipmap.default_photo)
            .error(R.mipmap.default_photo)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
        iv.setBackgroundColor(Color.TRANSPARENT)
        Glide.with(mContext).load(appendUrl(url, 360, 360, true)).apply(requestOptions).transition(DrawableTransitionOptions.withCrossFade()).into(iv)
    }

    /**
     * 图片加载
     *
     * @param mContext
     * @param url
     * @param iv
     */
    override fun loadAvatarSmall(mContext: Context, url: String, iv: ImageView, withPX: Int, heightPX: Int) {
        if (activityFinishedOrDestroyed(mContext)) return
        if (checkUrl(url)) {
            return
        }
        val requestOptions = RequestOptions()
            .placeholder(R.mipmap.default_photo)
            .error(R.mipmap.default_photo)
            .override(withPX, heightPX).diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop()
        iv.setBackgroundColor(Color.TRANSPARENT)
        Glide.with(mContext).load(appendUrl(url, withPX, heightPX, false)).apply(requestOptions).transition(DrawableTransitionOptions.withCrossFade()).into(iv)
    }

    override fun loadIvCenterInside(mContext: Context, id: Int, iv: ImageView) {
        if (activityFinishedOrDestroyed(mContext)) return
        val requestOptions = RequestOptions()
            .centerInside()
            .placeholder(R.drawable.transparent_bg)
            .error(R.drawable.transparent_bg).diskCacheStrategy(DiskCacheStrategy.ALL)
        Glide.with(mContext).load(id).apply(requestOptions).transition(DrawableTransitionOptions.withCrossFade()).into(iv)
    }

    override fun loadIvCenterInside(mContext: Context, url: String, iv: ImageView) {
        if (activityFinishedOrDestroyed(mContext)) return
        if (checkUrl(url)) {
            return
        }
        val requestOptions = RequestOptions()
            .centerInside()
            .placeholder(R.drawable.transparent_bg)
            .error(R.drawable.transparent_bg).diskCacheStrategy(DiskCacheStrategy.ALL)
        Glide.with(mContext).load(appendUrl(url)).apply(requestOptions).transition(DrawableTransitionOptions.withCrossFade()).into(iv)
    }

    override fun loadIvCenterInsideNoMemory(mContext: Context, url: String, iv: ImageView, withPX: Int, heightPX: Int) {
        if (activityFinishedOrDestroyed(mContext)) return
        if (checkUrl(url)) {
            return
        }
        val requestOptions = RequestOptions()
            .centerInside()
            .placeholder(R.drawable.transparent_bg)
            .error(R.drawable.transparent_bg)
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
        Glide
            .with(mContext)
            .load(appendUrl(url, withPX, heightPX, false))
            .apply(requestOptions)
            .into(iv)
    }

    override fun loadIvNoType(mContext: Context, url: String, iv: ImageView) {
        if (activityFinishedOrDestroyed(mContext)) return
        if (checkUrl(url)) {
            return
        }
        loadIvNoType(mContext, appendUrl(url), iv, R.drawable.transparent_bg)
    }
    private fun loadIvNoType(mContext: Context, url: String, iv: ImageView, defaultIv: Int) {
        if (activityFinishedOrDestroyed(mContext)) return
        if (checkUrl(url)) {
            return
        }
        val requestOptions = RequestOptions()
            .placeholder(defaultIv)
            .error(defaultIv)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
        Glide.with(mContext).load(appendUrl(url)).apply(requestOptions).transition(DrawableTransitionOptions.withCrossFade()).into(iv)
    }

    override fun loadIvHomeMixFragment(mContext: Context, url: String, iv: ImageView, placeholderAndErrorId: Int) {
        if (activityFinishedOrDestroyed(mContext)) return
        if (checkUrl(url)) {
            return
        }
        val requestOptions = RequestOptions()
            .placeholder(placeholderAndErrorId)
            .error(placeholderAndErrorId)
            .centerCrop().dontAnimate()
            .skipMemoryCache(true)
        Glide.with(mContext).load(appendUrl(url, 180, 180, true)).apply(requestOptions).into(iv)
    }

    override fun loadIvHomeFragment(mContext: Context, url: String, iv: ImageView, placeholderAndErrorId: Int) {
        if (activityFinishedOrDestroyed(mContext)) return
        if (checkUrl(url)) {
            return
        }
        val requestOptions = RequestOptions()
            .placeholder(placeholderAndErrorId)
            .error(placeholderAndErrorId)
            .centerCrop()
            .dontAnimate()
            .skipMemoryCache(true)

        Glide.with(mContext).load(appendUrl(url, 90, 90, true)).apply(requestOptions).into(iv)
    }

    override fun loadIvNoMemoryCacheInChatRoom(mContext: Context, url: String, iv: ImageView, placeholderAndErrorId: Int) {

        if (activityFinishedOrDestroyed(mContext)) return
        if (checkUrl(url)) {
            return
        }
        val requestOptions = RequestOptions()
            .placeholder(placeholderAndErrorId)
            .error(placeholderAndErrorId)
            .centerCrop()
            .dontAnimate()
            .skipMemoryCache(true)
        Glide.with(mContext).load(appendUrl(url)).apply(requestOptions).into(iv)
    }

    override fun loadIvCircleNoCache(mContext: Context, uri: Uri, iv: ImageView) {
        if (activityFinishedOrDestroyed(mContext)) return
        val requestOptions = RequestOptions
            .circleCropTransform()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
        Glide.with(mContext).load(uri).apply(requestOptions).into(iv)
    }

    /**
     * 不走缓存
     *
     * @param url
     * @param iv
     */
    override fun loadIvNoCache(mContext: Context, url: String, iv: ImageView) {
        if (activityFinishedOrDestroyed(mContext)) return
        if (checkUrl(url)) {
            return
        }
        val requestOptions = RequestOptions
            .centerCropTransform()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
        Glide.with(mContext).load(appendUrl(url)).apply(requestOptions).into(iv)
    }

    override fun loadIvNoCache(mContext: Context, uri: Uri, iv: ImageView) {
        if (activityFinishedOrDestroyed(mContext)) return
        val requestOptions = RequestOptions
            .centerCropTransform()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
        Glide.with(mContext).load(uri).apply(requestOptions).into(iv)
    }

    /**
     * 不走缓存
     *
     * @param url
     * @param iv
     */
    override fun loadIvNoMemoryCache(mContext: Context, url: String, iv: ImageView) {
        if (activityFinishedOrDestroyed(mContext)) return
        if (checkUrl(url)) {
            return
        }
        val requestOptions = RequestOptions
            .centerCropTransform()
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)

        Glide.with(mContext).load(appendUrl(url)).apply(requestOptions).into(iv)
    }

    /**
     * 增加圆角
     *
     * @param url 路径
     * @param iv  显示控件
     */
    override fun loadIvWithRoundDP(mContext: Context, url: String, iv: ImageView, defaultIv: Int, dp: Int) {
        if (activityFinishedOrDestroyed(mContext)) return
        if (checkUrl(url)) {
            return
        }
        loadIvWithRound(mContext, appendUrl(url), iv, defaultIv, -1, -1, dp.toFloat())
    }
    /**
     * 图片加载
     *
     * @param url       路径
     * @param iv        显示控件
     * @param defaultIv 默认图片
     */
    @SuppressLint("CheckResult")
    private fun loadIvWithRound(mContext: Context, url: String, iv: ImageView, defaultIv: Int, with: Int, height: Int, dp: Float) {
        if (activityFinishedOrDestroyed(mContext)) return
        if (checkUrl(url)) {
            return
        }
        val requestOptions = RequestOptions()
        if (defaultIv > 0) {
            requestOptions.placeholder(defaultIv)
            requestOptions.error(defaultIv)
        }
        iv.setBackgroundColor(Color.TRANSPARENT)
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop().transform(
            GlideRoundTransform(dp)
        )
        Glide.with(mContext).load(appendUrl(url)).apply(requestOptions).transition(DrawableTransitionOptions.withCrossFade()).into(iv)
    }

    /**
     * 加载圆形图片
     *
     * @param url 路径
     * @param iv  显示控件
     */
    override fun loadCircle(mContext: Context, url: String, iv: ImageView) {
        if (activityFinishedOrDestroyed(mContext)) return
        loadCircle(mContext, appendUrl(url), iv, R.drawable.transparent_bg)
    }
    /**
     * 加载圆形图片
     *
     * @param url       路径
     * @param iv        显示控件
     * @param defaultIv 默认图片
     */
    @SuppressLint("CheckResult")
    fun loadCircle(mContext: Context, url: String, iv: ImageView, defaultIv: Int) {
        if (activityFinishedOrDestroyed(mContext)) return
        if (checkUrl(url)) {
            return
        }
        val requestOptions = RequestOptions().circleCrop()
        if (defaultIv > 0) {
            requestOptions.placeholder(defaultIv)
            requestOptions.error(defaultIv)
        }
        iv.setBackgroundColor(Color.TRANSPARENT)
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL)
        Glide.with(mContext).load(appendUrl(url)).apply(requestOptions).into(iv)
    }

    override fun loadGifIvOnceNoMemoryInChatRoom(mContext: Context, url: String, iv: ImageView, mOnAnimationStatus: ImageLoadInterface.OnAnimationStatus) {
        if (activityFinishedOrDestroyed(mContext)) return
        if (checkUrl(url)) {
            return
        }
        Glide.with(mContext)
            .asGif()
            .load(url)
            .skipMemoryCache(true)
            .listener(object : RequestListener<GifDrawable> {

                override fun onResourceReady(resource: GifDrawable, model: Any, target: Target<GifDrawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                    resource.setLoopCount(1) //只播放一次
                    resource.registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {
                        override fun onAnimationStart(drawable: Drawable) {
                            super.onAnimationStart(drawable)
                            mOnAnimationStatus.onAnimationStart()
                        }

                        override fun onAnimationEnd(drawable: Drawable) {
                            super.onAnimationEnd(drawable)
                            mOnAnimationStatus.onAnimationEnd()
                        }
                    })
                    return false
                }

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<GifDrawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }
            }).into(iv)
    }

    override fun loadGifIvOnceNoMemoryInChatRoom(mContext: Context, resId: Int, iv: ImageView, mOnAnimationStatus: ImageLoadInterface.OnAnimationStatus) {
        if (activityFinishedOrDestroyed(mContext)) return
        Glide.with(mContext)
            .asGif()
            .load(resId)
            .skipMemoryCache(true)
            .listener(object : RequestListener<GifDrawable> {

                override fun onResourceReady(resource: GifDrawable, model: Any, target: Target<GifDrawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                    resource.setLoopCount(1) //只播放一次
                    resource.registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {
                        override fun onAnimationStart(drawable: Drawable) {
                            super.onAnimationStart(drawable)
                            mOnAnimationStatus.onAnimationStart()
                        }

                        override fun onAnimationEnd(drawable: Drawable) {
                            super.onAnimationEnd(drawable)
                            mOnAnimationStatus.onAnimationEnd()
                        }
                    })
                    return false
                }

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<GifDrawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }
            }).into(iv)
    }

    /**
     * 循环播放
     */
    override fun loadGifForever(mContext: Context, url: String, iv: ImageView) {
        if (activityFinishedOrDestroyed(mContext)) return
        if (checkUrl(url)) {
            return
        }
        Glide.with(mContext).load(url)
            .listener(object : RequestListener<Drawable> {

                override fun onResourceReady(resource: Drawable, model: Any, target: Target<Drawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                    (resource as GifDrawable).setLoopCount(-1)
                    // 计算动画时长
                    resource.registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {
                        override fun onAnimationStart(drawable: Drawable) {
                            super.onAnimationStart(drawable)
                        }

                        override fun onAnimationEnd(drawable: Drawable) {
                            super.onAnimationEnd(drawable)
                        }
                    })
                    return false
                }

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    //todo 此处是否需要将Glide进行clear

                    return false
                }
            }).into(iv)
    }

    override fun loadGifForeverNoCacheInChatRoom(mContext: Context, drawableId: Int, iv: ImageView) {
        if (activityFinishedOrDestroyed(mContext)) return
        Glide.with(mContext)
            .asGif()
            .load(drawableId)
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .listener(object : RequestListener<GifDrawable> {

                override fun onResourceReady(resource: GifDrawable, model: Any, target: Target<GifDrawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                    resource.setLoopCount(-1)
                    // 计算动画时长
                    resource.registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {
                        override fun onAnimationStart(drawable: Drawable) {
                            super.onAnimationStart(drawable)
                        }

                        override fun onAnimationEnd(drawable: Drawable) {
                            super.onAnimationEnd(drawable)
                        }
                    })
                    return false
                }

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<GifDrawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }
            }).into(iv)
    }

    private fun clearImageDiskCache(context: Context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                Thread { Glide.get(context).clearDiskCache() }.start()
            } else {
                Glide.get(context).clearDiskCache()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }    }

    /**
     * 清除图片内存缓存
     */
    private fun clearImageMemoryCache(context: Context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) { //只能在主线程执行
                Glide.get(context).clearMemory()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }    }

    /**
     * 清除图片所有缓存
     */
    override fun clearImageAllCache(context: Context) {
        clearImageDiskCache(context)
        clearImageMemoryCache(context)
        val imageExternalCatchDir = context.externalCacheDir.toString() + ExternalCacheDiskCacheFactory.DEFAULT_DISK_CACHE_DIR
        FileUtils.deleteFolderFile(imageExternalCatchDir, true)
    }

    override fun getCacheSizeByte(context: Context): Long {
        return FileUtils.getFolderSize(File(context.cacheDir, "azal_image"))
    }

    override fun loadImageWithCustomTarget(context: Context, url: String, customTarget: CustomTarget<Bitmap>) {
        if (context is Activity) {
            if (context.isFinishing || context.isDestroyed) {
                return
            }
        }
        if (checkUrl(url)) {
            return
        }
        Glide.with(context)
            .asBitmap()
            .load(appendUrl(url))
            .skipMemoryCache(true)
            .into(customTarget)
    }

    override fun loadBitmapWithCustomTarget(context: Context, url: String, customTarget: CustomTarget<Bitmap>) {
        if (context is Activity) {
            if (context.isFinishing || context.isDestroyed) {
                return
            }
        }
        if (checkUrl(url)) {
            return
        }
        Glide.with(context)
            .asBitmap()
            .load(url)
            .into(customTarget)
    }

    override fun initChatRoomBackgroundWithDrawable(context: Context, url: String, view: View, resId: Int) {
        view.setBackgroundResource(resId)
        if (TextUtils.isEmpty(url)) {
            return
        }
        loadDrawableToImageBackground(context, appendUrl(url), view)
    }
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

    override fun updateChatRoomBackgroundWithDrawable(context: Context, url: String, view: View, resId: Int) {
        if (TextUtils.isEmpty(url)) {
            view.setBackgroundResource(resId)
            return
        }
        loadDrawableToImageBackground(context, appendUrl(url), view)
    }

    override fun load9PngWithResId(context: Context, url: String, view: View, resId: Int) {
        if (TextUtils.isEmpty(url)) {
            view.setBackgroundResource(resId)
            return
        }
        if (context is Activity) {
            if (context.isFinishing || context.isDestroyed) {
                return
            }
        }
        Glide.with(context)
            .asFile()
            .load(url)
            .into(object : CustomTarget<File>() {

                override fun onLoadCleared(placeholder: Drawable?) {}
                override fun onResourceReady(resource: File, transition: Transition<in File>?) {
                    try {
                        val inputStream = FileInputStream(resource)
                        val bitmap = BitmapFactory.decodeStream(inputStream)
                        val drawable = getNinePatchDrawable(bitmap, context)
                        view.background = drawable
                        inputStream.close()
                    } catch (e: Exception) {
                    }
                }
            })
    }

    private fun getNinePatchDrawable(bitmap: Bitmap, context: Context): Drawable {
        val chunk = bitmap.ninePatchChunk
        var ninePatchDrawable: NinePatchDrawable? = null
        ninePatchDrawable = if (NinePatch.isNinePatchChunk(chunk)) {
            NinePatchDrawable(context.resources, bitmap, chunk, Rect(), null)
        } else {
            return BitmapDrawable(context.resources, bitmap)
        }
        return ninePatchDrawable
    }

    override fun loadIvWithRoundPX(mContext: Context, url: String, iv: ImageView, defaultIv: Int, px: Int) {
        if (activityFinishedOrDestroyed(mContext)) return
        if (checkUrl(url)) {
            return
        }
        Glide.with(mContext).load(appendUrl(url)).apply(RequestOptions.bitmapTransform(
            RoundedCorners(px)
        ).placeholder(defaultIv)).into(iv)
    }

    override fun loadIvWithCircle(mContext: Context, url: String, iv: ImageView, defaultIv: Int) {
        if (activityFinishedOrDestroyed(mContext)) return
        if (checkUrl(url)) {
            return
        }
        Glide.with(mContext).load(appendUrl(url)).apply(RequestOptions.bitmapTransform(CircleCrop()).placeholder(defaultIv)).into(iv)
    }

    override fun loadIvWithCircleBorder(mContext: Context, url: String, iv: ImageView, defaultIv: Int, borderColor: Int, borderWidth: Float) {
        if (activityFinishedOrDestroyed(mContext)) return
        if (checkUrl(url)) {
            return
        }
        Glide.with(mContext).load(appendUrl(url)).apply(RequestOptions.bitmapTransform(
            CircleBorderTransformation(borderWidth, borderColor)
        ).placeholder(defaultIv)).into(iv)
    }

    override fun fixAvatarUrl(url: String, withPX: Int, heightPX: Int): String {
        return if (checkUrl(url)) {
            ""
        } else appendUrl(url, withPX, heightPX, false)
    }
}