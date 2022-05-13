package com.renxing.RXImageLoader

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.imageloader.R
import com.renxing.moduleImageLoader.RXImageLoader
import com.renxing.moduleImageLoader.imageUtils.ModuleImageConstant
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
        img_border_url.setOnClickListener(this)
        img_border_id.setOnClickListener(this)
        img_border_url_corner.setOnClickListener(this)
        img_border_id_corner.setOnClickListener(this)
    }

    val url = "https://t7.baidu.com/it/u=1595072465,3644073269&fm=193&f=GIF"
    val urlGif = "https://tse1-mm.cn.bing.net/th/id/R-C.9d17d28183f39907a04ec2a54e3f8dd3?rik=wJ4gqd49C1SP8A&riu=http%3a%2f%2fwww.qqpao.com%2fuploads%2fallimg%2f181116%2f10-1Q116102132.gif&ehk=F5wDRW473O%2bVTC2s3AbfGzPwbvkUFfa390Elf9t4XQI%3d&risl=&pid=ImgRaw&r=0"

//    val url = "https://github.com/xiaolanlaia/RXImageLoader/blob/main/app/src/main/res/mipmap-xxhdpi/charff.9.png"
    val imgId = R.mipmap.charff
    val placeHoldId = R.mipmap.ic_launcher
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClick(v: View) {

        when (v.id) {
            R.id.btn_1 -> {
                RXImageLoader.loadImage("url",test_iv,R.mipmap.default_photo)
            }
            R.id.btn_2 -> {
                RXImageLoader.loadImage("imgId",test_iv)

            }
            R.id.btn_3 -> {
                RXImageLoader.loadImage(url,test_iv,placeHoldId)

            }
            R.id.btn_4 -> {
                RXImageLoader.loadImageWithFitCenter(imgId,test_iv)

            }
            R.id.btn_5 -> {
                RXImageLoader.loadImageWithFitCenter(url,test_iv)
            }
            R.id.btn_6 -> {
                RXImageLoader.loadImageWithCenterCrop(url,test_iv)

            }
            R.id.btn_7 -> {
                RXImageLoader.loadImageWithCenterCrop(imgId,test_iv)

            }
            R.id.btn_8 -> {
                RXImageLoader.loadImageWithCenterInside(url,test_iv)

            }
            R.id.loadCircleImage -> {
                RXImageLoader.loadCircleImage(url,test_iv)

            }
            R.id.loadRoundedCornersImage -> {
                RXImageLoader.loadRoundedCornersImage(url,test_iv,20f)

            }
            R.id.loadRoundedCornersImage_2 -> {
                RXImageLoader.loadRoundedCornersImage(url,test_iv,20f, ModuleImageConstant.CornerType.BOTTOM)

            }
//            R.id.loadRoundedCornersImage_3 -> {
//                RXImageLoader.loadRoundedCornersImage(url,test_iv,20f, CornerType.ALL)
//
//            }
            R.id.load_gif -> {
                RXImageLoader.loadGif(urlGif,test_iv)

            }
            R.id.load_gif_id -> {
                RXImageLoader.loadGif(R.mipmap.cat,test_iv)

            }
            R.id.load_gif_loop -> {
                RXImageLoader.loadGif(urlGif,test_iv)

            }

            R.id.customTarget -> {



                RXImageLoader.loadImageCustomTargetForBitmap(url,test_iv,500,500)

            }
            R.id.png9 -> {

                val res = resources

                // Bitmap from resource (Compiled)

//                LoadDian9TuUtil.setNinePathImage(this,test_iv,res_bmp)
//                LoadDian9TuUtil.loadDian9Tu(this,test_iv,url)
//                RXImageLoader.load9Png(this,R.mipmap.charff,test_iv)
                RXImageLoader.loadImageCustomTargetForBitmap(url,test_iv, 300, 300)

            }

            R.id.img_border_url ->{
                RXImageLoader.loadCircleImageWithBorder(url,test_iv,resources.getColor(R.color.black),2f)
            }
            R.id.img_border_id ->{
                RXImageLoader.loadCircleImageWithBorder(R.mipmap.img4,test_iv,resources.getColor(R.color.black),2f)

            }
            R.id.img_border_url_corner ->{
            }
        }

    }


    fun NinePatch2Bitmap(context: Context, resId: Int, width: Int, height: Int): Bitmap? {
        val drawable: Drawable = context.resources.getDrawable(resId)
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight())
        drawable.draw(canvas)
        return bitmap
    }
}