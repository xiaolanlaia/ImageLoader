package com.example.imageloader

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.moduleimage.ImageLoader
import com.example.moduleimage.loaderStrategy.control.LoaderStrategyFactory
import com.example.moduleimage.loaderStrategy.glide.GlideRoundedCornersTransform
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
    }

    val url = "https://t7.baidu.com/it/u=1595072465,3644073269&fm=193&f=GIF"
    val imgId = R.mipmap.img4
    val placeHoldId = R.mipmap.ic_launcher
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
        }

    }
}