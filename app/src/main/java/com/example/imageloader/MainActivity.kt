package com.example.imageloader

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.moduleimage.ImageLoader
import com.example.moduleimage.loaderStrategy.glide.GlideRoundedCornersTransform
import com.example.moduleimage.loaderStrategy.glide.ImageLoaderGlide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_1.setOnClickListener {
            val url = "https://tse2-mm.cn.bing.net/th/id/OIP-C.6BuL00jxYoG2oq1k17jqjwHaF5?pid=ImgDet&rs=1"
            val bitmap4 = (ContextCompat.getDrawable(this, R.mipmap.img4) as BitmapDrawable).bitmap
            ImageLoader.loadRoundedCornersImage(url, test_iv, 200f, GlideRoundedCornersTransform.CornerType.TOP_RIGHT_BOTTOM_RIGHT_BOTTOM_LEFT)
        }
    }
}