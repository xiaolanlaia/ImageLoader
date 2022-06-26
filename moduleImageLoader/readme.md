load() 可传入url、id、uri、drawable、colorDrawable

```kotlin
//                into() imageView
//                intoBitmapTarget() 设置自定义目标，返回一个Bitmap资源
//                intoDrawableTarget() 设置自定义目标，返回一个Drawable资源
//                placeholder() 图片加载时的占位图
//                error() 设置加载错误图，没设置的话默认使用placeholder
//                override() 设置加载图片和缓存图片的尺寸
//                diskcacheStrategy() 设置缓存策略
//                registerAnimationCallback() gif图片播放开始和播放结束的回调
//                setLoopCount() gif图片循环播放次数，不设置会默认循环播放
//                rxListener() 图片加载成功与加载失败的回调
//                transitionEnum() 设置图片变换
//                fitCenter() 设置图片缩放方式为fitCenter
//                centerCrop() 设置图片缩放方式为centerCrop
//                centerInside() 设置图片缩放方式为centerInside
//                circleCrop() 设置图片为圆形变换
//                cornerRadius() 设置圆角图的圆角大小
//                borderWidth() 设置圆形边框图的边框宽度
//                borderColor() 设置圆形边框图的边框颜色
//                priority() 设置图片加载的优先级
//                dontAnimate() 禁止动画
//                crossfade() 淡入淡出动画

```

```kotlin

                /**
                 * 加载普通图片
                 */
                fun loadImage(imgLoadParams: ImgLoadParams)
                /**
                 * 加载gif图片
                 */
                fun loadGifImage(imgLoadParams: ImgLoadParams)
                /**
                 * 加载intoBitmapTarget自定义目标，返回一个bitmap对象
                 */
                fun loadBitmapImage(imgLoadParams: ImgLoadParams)
                /**
                 * 加载圆形图
                 */
                fun loadCircleImage(imgLoadParams: ImgLoadParams)
                /**
                 * 加载圆角图
                 */
                fun loadCornersImage(imgLoadParams: ImgLoadParams)
                /**
                 * 加载带边框圆形图
                 */
                fun loadBorderCircleImage(imgLoadParams: ImgLoadParams)
                /**
                 * 清除磁盘缓存
                 */
                fun clearImageDiskCache(context: Context)
                /**
                 * 清除内存，只能在主线程进行
                 */
                fun clearImageMemoryCache(context: Context)
                /**
                 * 取消加载，释放已经加载的资源
                 */
                fun clear(context: Context, imageView: ImageView)
                /**
                 * 取消正在进行的加载，但不清除已完成加载的资源
                 */
                fun pauseRequests(context: Context)
                /**
                 * 重启尚未完成的加载
                 */
                fun resumeRequests(context: Context)
                /**
                 * 清除内存
                 */
                fun clearMemory(context: Context)
                /**
                 * 根据给定的级别清除内存
                 */
                fun trimMemory(context: Context, level: Int)
```