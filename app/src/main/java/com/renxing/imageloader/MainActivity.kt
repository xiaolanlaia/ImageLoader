package com.renxing.imageloader

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.imageloader.R
import com.renxing.moduleImageLoader.ImageLoader
import com.renxing.moduleImageLoader.imageUtils.DisplayUtils
import com.renxing.moduleImageLoader.loaderStrategy.glide.GlideRoundedCornersTransform
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_1.setOnClickListener(this)
        btn_2.setOnClickListener(this)
        btn_3.setOnClickListener(this)
        btn_4.setOnClickListener(this)
        btn_5.setOnClickListener(this)
        btn_6.setOnClickListener(this)
        btn_7.setOnClickListener(this)
        btn_8.setOnClickListener(this)
        loadCircleImage.setOnClickListener(this)
        loadRoundedCornersImage.setOnClickListener(this)
        loadRoundedCornersImage_2.setOnClickListener(this)
        loadRoundedCornersImage_3.setOnClickListener(this)
        load_gif.setOnClickListener(this)
        load_gif_id.setOnClickListener(this)
        load_gif_loop.setOnClickListener(this)
        customTarget.setOnClickListener(this)
        png9.setOnClickListener(this)
    }

//    val url = "https://t7.baidu.com/it/u=1595072465,3644073269&fm=193&f=GIF"
    val urlGif = "https://tse1-mm.cn.bing.net/th/id/R-C.9d17d28183f39907a04ec2a54e3f8dd3?rik=wJ4gqd49C1SP8A&riu=http%3a%2f%2fwww.qqpao.com%2fuploads%2fallimg%2f181116%2f10-1Q116102132.gif&ehk=F5wDRW473O%2bVTC2s3AbfGzPwbvkUFfa390Elf9t4XQI%3d&risl=&pid=ImgRaw&r=0"

    val url = "https://github.com/xiaolanlaia/ImageLoader/blob/main/app/src/main/res/mipmap-xxhdpi/charff.9.png"
    val imgId = R.mipmap.charff
    val placeHoldId = R.mipmap.ic_launcher
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClick(v: View) {

        when (v.id) {
            R.id.btn_1 -> {
                ImageLoader.loadImage(url,test_iv)
            }
            R.id.btn_2 -> {
                ImageLoader.loadImage(imgId,test_iv)

            }
            R.id.btn_3 -> {
                ImageLoader.loadImage(url,test_iv,placeHoldId)

            }
            R.id.btn_4 -> {
                ImageLoader.loadImageWithFitCenter(imgId,test_iv)

            }
            R.id.btn_5 -> {
                ImageLoader.loadImageWithFitCenter(url,test_iv)
            }
            R.id.btn_6 -> {
                ImageLoader.loadImageWithCenterCrop(url,test_iv)

            }
            R.id.btn_7 -> {
                ImageLoader.loadImageWithCenterCrop(imgId,test_iv)

            }
            R.id.btn_8 -> {
                ImageLoader.loadImageWithCenterInside(url,test_iv)

            }
            R.id.loadCircleImage -> {
                ImageLoader.loadCircleImage(url,test_iv)

            }
            R.id.loadRoundedCornersImage -> {
                ImageLoader.loadRoundedCornersImage(url,test_iv,20f)

            }
            R.id.loadRoundedCornersImage_2 -> {
                ImageLoader.loadRoundedCornersImage(url,test_iv,20f, GlideRoundedCornersTransform.CornerType.BOTTOM)

            }
//            R.id.loadRoundedCornersImage_3 -> {
//                ImageLoader.loadRoundedCornersImage(url,test_iv,20f, GlideRoundedCornersTransform.CornerType.ALL)
//
//            }
            R.id.load_gif -> {
                ImageLoader.loadGif(urlGif,test_iv)

            }
            R.id.load_gif_id -> {
                ImageLoader.loadGif(R.mipmap.cat,test_iv)

            }
            R.id.load_gif_loop -> {
                ImageLoader.loadGifWithLoop(urlGif,test_iv)

            }

            R.id.customTarget -> {
                var mHeight = test_iv.height
                if (mHeight == 0) {
                    mHeight = DisplayUtils.dpToPx(14)
                }


                ImageLoader.loadImageWithCustomTarget(this,urlGif,
                    object : CustomTarget<Bitmap>() {
                        override fun onResourceReady(
                            resource: Bitmap,
                            transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
                        ){

                            val scale: Float = resource.height.toFloat() / mHeight
                            val with = (resource.width / scale).toInt()
                            val params = test_iv.layoutParams
                            params.height = mHeight
                            params.width = with
                            test_iv.layoutParams = params
                            test_iv.setImageBitmap(resource)
                            test_iv.visibility = View.VISIBLE
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {}

                    })

            }
            R.id.png9 -> {

                val res = resources

                // Bitmap from resource (Compiled)

//                LoadDian9TuUtil.setNinePathImage(this,test_iv,res_bmp)
//                LoadDian9TuUtil.loadDian9Tu(this,test_iv,url)
//                ImageLoader.load9Png(this,R.mipmap.charff,test_iv)
                ImageLoader.loadImageWithCustomTarget(this,url, object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(bitmap: Bitmap, transition: Transition<in Bitmap>?) {
                        if (bitmap == null) {
                            return
                        }
//                        val scale: Float = drawable / test_iv.height
//                        val with = (drawable.width / scale).toInt()
                        val params = test_iv.getLayoutParams()
                        params.height = test_iv.height
                        params.width = test_iv.width
                        test_iv.setLayoutParams(params)
                        test_iv.setImageBitmap(bitmap)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {}

                })

            }
        }

    }

    fun NinePatch2Bitmap(context: Context, resId: Int, width: Int, height: Int): Bitmap? {
        val drawable: Drawable = context.getResources().getDrawable(resId)
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight())
        drawable.draw(canvas)
        return bitmap
    }
}